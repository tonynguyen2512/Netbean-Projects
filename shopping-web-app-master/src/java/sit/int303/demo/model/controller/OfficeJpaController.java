/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.demo.model.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sit.int303.demo.model.Employee;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import sit.int303.demo.model.Office;
import sit.int303.demo.model.controller.exceptions.IllegalOrphanException;
import sit.int303.demo.model.controller.exceptions.NonexistentEntityException;
import sit.int303.demo.model.controller.exceptions.PreexistingEntityException;
import sit.int303.demo.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author bas
 */
public class OfficeJpaController implements Serializable {

  public OfficeJpaController(UserTransaction utx, EntityManagerFactory emf) {
    this.utx = utx;
    this.emf = emf;
  }
  private UserTransaction utx = null;
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Office office) throws PreexistingEntityException, RollbackFailureException, Exception {
    if (office.getEmployeeCollection() == null) {
      office.setEmployeeCollection(new ArrayList<Employee>());
    }
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Collection<Employee> attachedEmployeeCollection = new ArrayList<Employee>();
      for (Employee employeeCollectionEmployeeToAttach : office.getEmployeeCollection()) {
        employeeCollectionEmployeeToAttach = em.getReference(employeeCollectionEmployeeToAttach.getClass(), employeeCollectionEmployeeToAttach.getEmployeenumber());
        attachedEmployeeCollection.add(employeeCollectionEmployeeToAttach);
      }
      office.setEmployeeCollection(attachedEmployeeCollection);
      em.persist(office);
      for (Employee employeeCollectionEmployee : office.getEmployeeCollection()) {
        Office oldOfficecodeOfEmployeeCollectionEmployee = employeeCollectionEmployee.getOfficecode();
        employeeCollectionEmployee.setOfficecode(office);
        employeeCollectionEmployee = em.merge(employeeCollectionEmployee);
        if (oldOfficecodeOfEmployeeCollectionEmployee != null) {
          oldOfficecodeOfEmployeeCollectionEmployee.getEmployeeCollection().remove(employeeCollectionEmployee);
          oldOfficecodeOfEmployeeCollectionEmployee = em.merge(oldOfficecodeOfEmployeeCollectionEmployee);
        }
      }
      utx.commit();
    } catch (Exception ex) {
      try {
        utx.rollback();
      } catch (Exception re) {
        throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
      }
      if (findOffice(office.getOfficecode()) != null) {
        throw new PreexistingEntityException("Office " + office + " already exists.", ex);
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Office office) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Office persistentOffice = em.find(Office.class, office.getOfficecode());
      Collection<Employee> employeeCollectionOld = persistentOffice.getEmployeeCollection();
      Collection<Employee> employeeCollectionNew = office.getEmployeeCollection();
      List<String> illegalOrphanMessages = null;
      for (Employee employeeCollectionOldEmployee : employeeCollectionOld) {
        if (!employeeCollectionNew.contains(employeeCollectionOldEmployee)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain Employee " + employeeCollectionOldEmployee + " since its officecode field is not nullable.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      Collection<Employee> attachedEmployeeCollectionNew = new ArrayList<Employee>();
      for (Employee employeeCollectionNewEmployeeToAttach : employeeCollectionNew) {
        employeeCollectionNewEmployeeToAttach = em.getReference(employeeCollectionNewEmployeeToAttach.getClass(), employeeCollectionNewEmployeeToAttach.getEmployeenumber());
        attachedEmployeeCollectionNew.add(employeeCollectionNewEmployeeToAttach);
      }
      employeeCollectionNew = attachedEmployeeCollectionNew;
      office.setEmployeeCollection(employeeCollectionNew);
      office = em.merge(office);
      for (Employee employeeCollectionNewEmployee : employeeCollectionNew) {
        if (!employeeCollectionOld.contains(employeeCollectionNewEmployee)) {
          Office oldOfficecodeOfEmployeeCollectionNewEmployee = employeeCollectionNewEmployee.getOfficecode();
          employeeCollectionNewEmployee.setOfficecode(office);
          employeeCollectionNewEmployee = em.merge(employeeCollectionNewEmployee);
          if (oldOfficecodeOfEmployeeCollectionNewEmployee != null && !oldOfficecodeOfEmployeeCollectionNewEmployee.equals(office)) {
            oldOfficecodeOfEmployeeCollectionNewEmployee.getEmployeeCollection().remove(employeeCollectionNewEmployee);
            oldOfficecodeOfEmployeeCollectionNewEmployee = em.merge(oldOfficecodeOfEmployeeCollectionNewEmployee);
          }
        }
      }
      utx.commit();
    } catch (Exception ex) {
      try {
        utx.rollback();
      } catch (Exception re) {
        throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
      }
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        String id = office.getOfficecode();
        if (findOffice(id) == null) {
          throw new NonexistentEntityException("The office with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Office office;
      try {
        office = em.getReference(Office.class, id);
        office.getOfficecode();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The office with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      Collection<Employee> employeeCollectionOrphanCheck = office.getEmployeeCollection();
      for (Employee employeeCollectionOrphanCheckEmployee : employeeCollectionOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Office (" + office + ") cannot be destroyed since the Employee " + employeeCollectionOrphanCheckEmployee + " in its employeeCollection field has a non-nullable officecode field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      em.remove(office);
      utx.commit();
    } catch (Exception ex) {
      try {
        utx.rollback();
      } catch (Exception re) {
        throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Office> findOfficeEntities() {
    return findOfficeEntities(true, -1, -1);
  }

  public List<Office> findOfficeEntities(int maxResults, int firstResult) {
    return findOfficeEntities(false, maxResults, firstResult);
  }

  private List<Office> findOfficeEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Office.class));
      Query q = em.createQuery(cq);
      if (!all) {
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
      }
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public Office findOffice(String id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Office.class, id);
    } finally {
      em.close();
    }
  }

  public int getOfficeCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Office> rt = cq.from(Office.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
