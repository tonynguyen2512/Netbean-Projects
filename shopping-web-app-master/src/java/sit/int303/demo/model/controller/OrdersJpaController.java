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
import sit.int303.demo.model.Customer;
import sit.int303.demo.model.OrderDetail;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import sit.int303.demo.model.Orders;
import sit.int303.demo.model.controller.exceptions.IllegalOrphanException;
import sit.int303.demo.model.controller.exceptions.NonexistentEntityException;
import sit.int303.demo.model.controller.exceptions.PreexistingEntityException;
import sit.int303.demo.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author bas
 */
public class OrdersJpaController implements Serializable {

  public OrdersJpaController(UserTransaction utx, EntityManagerFactory emf) {
    this.utx = utx;
    this.emf = emf;
  }
  private UserTransaction utx = null;
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Orders orders) throws PreexistingEntityException, RollbackFailureException, Exception {
    if (orders.getOrderDetailCollection() == null) {
      orders.setOrderDetailCollection(new ArrayList<OrderDetail>());
    }
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Customer customernumber = orders.getCustomernumber();
      if (customernumber != null) {
        customernumber = em.getReference(customernumber.getClass(), customernumber.getCustomernumber());
        orders.setCustomernumber(customernumber);
      }
      Collection<OrderDetail> attachedOrderDetailCollection = new ArrayList<OrderDetail>();
      for (OrderDetail orderDetailCollectionOrderDetailToAttach : orders.getOrderDetailCollection()) {
        orderDetailCollectionOrderDetailToAttach = em.getReference(orderDetailCollectionOrderDetailToAttach.getClass(), orderDetailCollectionOrderDetailToAttach.getOrderDetailPK());
        attachedOrderDetailCollection.add(orderDetailCollectionOrderDetailToAttach);
      }
      orders.setOrderDetailCollection(attachedOrderDetailCollection);
      em.persist(orders);
      if (customernumber != null) {
        customernumber.getOrdersCollection().add(orders);
        customernumber = em.merge(customernumber);
      }
      for (OrderDetail orderDetailCollectionOrderDetail : orders.getOrderDetailCollection()) {
        Orders oldOrdersOfOrderDetailCollectionOrderDetail = orderDetailCollectionOrderDetail.getOrders();
        orderDetailCollectionOrderDetail.setOrders(orders);
        orderDetailCollectionOrderDetail = em.merge(orderDetailCollectionOrderDetail);
        if (oldOrdersOfOrderDetailCollectionOrderDetail != null) {
          oldOrdersOfOrderDetailCollectionOrderDetail.getOrderDetailCollection().remove(orderDetailCollectionOrderDetail);
          oldOrdersOfOrderDetailCollectionOrderDetail = em.merge(oldOrdersOfOrderDetailCollectionOrderDetail);
        }
      }
      utx.commit();
    } catch (Exception ex) {
      try {
        utx.rollback();
      } catch (Exception re) {
        throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
      }
      if (findOrders(orders.getOrdernumber()) != null) {
        throw new PreexistingEntityException("Orders " + orders + " already exists.", ex);
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Orders orders) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Orders persistentOrders = em.find(Orders.class, orders.getOrdernumber());
      Customer customernumberOld = persistentOrders.getCustomernumber();
      Customer customernumberNew = orders.getCustomernumber();
      Collection<OrderDetail> orderDetailCollectionOld = persistentOrders.getOrderDetailCollection();
      Collection<OrderDetail> orderDetailCollectionNew = orders.getOrderDetailCollection();
      List<String> illegalOrphanMessages = null;
      for (OrderDetail orderDetailCollectionOldOrderDetail : orderDetailCollectionOld) {
        if (!orderDetailCollectionNew.contains(orderDetailCollectionOldOrderDetail)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain OrderDetail " + orderDetailCollectionOldOrderDetail + " since its orders field is not nullable.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      if (customernumberNew != null) {
        customernumberNew = em.getReference(customernumberNew.getClass(), customernumberNew.getCustomernumber());
        orders.setCustomernumber(customernumberNew);
      }
      Collection<OrderDetail> attachedOrderDetailCollectionNew = new ArrayList<OrderDetail>();
      for (OrderDetail orderDetailCollectionNewOrderDetailToAttach : orderDetailCollectionNew) {
        orderDetailCollectionNewOrderDetailToAttach = em.getReference(orderDetailCollectionNewOrderDetailToAttach.getClass(), orderDetailCollectionNewOrderDetailToAttach.getOrderDetailPK());
        attachedOrderDetailCollectionNew.add(orderDetailCollectionNewOrderDetailToAttach);
      }
      orderDetailCollectionNew = attachedOrderDetailCollectionNew;
      orders.setOrderDetailCollection(orderDetailCollectionNew);
      orders = em.merge(orders);
      if (customernumberOld != null && !customernumberOld.equals(customernumberNew)) {
        customernumberOld.getOrdersCollection().remove(orders);
        customernumberOld = em.merge(customernumberOld);
      }
      if (customernumberNew != null && !customernumberNew.equals(customernumberOld)) {
        customernumberNew.getOrdersCollection().add(orders);
        customernumberNew = em.merge(customernumberNew);
      }
      for (OrderDetail orderDetailCollectionNewOrderDetail : orderDetailCollectionNew) {
        if (!orderDetailCollectionOld.contains(orderDetailCollectionNewOrderDetail)) {
          Orders oldOrdersOfOrderDetailCollectionNewOrderDetail = orderDetailCollectionNewOrderDetail.getOrders();
          orderDetailCollectionNewOrderDetail.setOrders(orders);
          orderDetailCollectionNewOrderDetail = em.merge(orderDetailCollectionNewOrderDetail);
          if (oldOrdersOfOrderDetailCollectionNewOrderDetail != null && !oldOrdersOfOrderDetailCollectionNewOrderDetail.equals(orders)) {
            oldOrdersOfOrderDetailCollectionNewOrderDetail.getOrderDetailCollection().remove(orderDetailCollectionNewOrderDetail);
            oldOrdersOfOrderDetailCollectionNewOrderDetail = em.merge(oldOrdersOfOrderDetailCollectionNewOrderDetail);
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
        Integer id = orders.getOrdernumber();
        if (findOrders(id) == null) {
          throw new NonexistentEntityException("The orders with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Orders orders;
      try {
        orders = em.getReference(Orders.class, id);
        orders.getOrdernumber();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The orders with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      Collection<OrderDetail> orderDetailCollectionOrphanCheck = orders.getOrderDetailCollection();
      for (OrderDetail orderDetailCollectionOrphanCheckOrderDetail : orderDetailCollectionOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Orders (" + orders + ") cannot be destroyed since the OrderDetail " + orderDetailCollectionOrphanCheckOrderDetail + " in its orderDetailCollection field has a non-nullable orders field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      Customer customernumber = orders.getCustomernumber();
      if (customernumber != null) {
        customernumber.getOrdersCollection().remove(orders);
        customernumber = em.merge(customernumber);
      }
      em.remove(orders);
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

  public List<Orders> findOrdersEntities() {
    return findOrdersEntities(true, -1, -1);
  }

  public List<Orders> findOrdersEntities(int maxResults, int firstResult) {
    return findOrdersEntities(false, maxResults, firstResult);
  }

  private List<Orders> findOrdersEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Orders.class));
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

  public Orders findOrders(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Orders.class, id);
    } finally {
      em.close();
    }
  }

  public int getOrdersCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Orders> rt = cq.from(Orders.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
