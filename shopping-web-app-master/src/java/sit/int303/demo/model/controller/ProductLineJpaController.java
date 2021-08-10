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
import sit.int303.demo.model.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import sit.int303.demo.model.ProductLine;
import sit.int303.demo.model.controller.exceptions.IllegalOrphanException;
import sit.int303.demo.model.controller.exceptions.NonexistentEntityException;
import sit.int303.demo.model.controller.exceptions.PreexistingEntityException;
import sit.int303.demo.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author bas
 */
public class ProductLineJpaController implements Serializable {

  public ProductLineJpaController(UserTransaction utx, EntityManagerFactory emf) {
    this.utx = utx;
    this.emf = emf;
  }
  private UserTransaction utx = null;
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(ProductLine productLine) throws PreexistingEntityException, RollbackFailureException, Exception {
    if (productLine.getProductCollection() == null) {
      productLine.setProductCollection(new ArrayList<Product>());
    }
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Collection<Product> attachedProductCollection = new ArrayList<Product>();
      for (Product productCollectionProductToAttach : productLine.getProductCollection()) {
        productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getProductcode());
        attachedProductCollection.add(productCollectionProductToAttach);
      }
      productLine.setProductCollection(attachedProductCollection);
      em.persist(productLine);
      for (Product productCollectionProduct : productLine.getProductCollection()) {
        ProductLine oldProductlineOfProductCollectionProduct = productCollectionProduct.getProductline();
        productCollectionProduct.setProductline(productLine);
        productCollectionProduct = em.merge(productCollectionProduct);
        if (oldProductlineOfProductCollectionProduct != null) {
          oldProductlineOfProductCollectionProduct.getProductCollection().remove(productCollectionProduct);
          oldProductlineOfProductCollectionProduct = em.merge(oldProductlineOfProductCollectionProduct);
        }
      }
      utx.commit();
    } catch (Exception ex) {
      try {
        utx.rollback();
      } catch (Exception re) {
        throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
      }
      if (findProductLine(productLine.getProductline()) != null) {
        throw new PreexistingEntityException("ProductLine " + productLine + " already exists.", ex);
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(ProductLine productLine) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      ProductLine persistentProductLine = em.find(ProductLine.class, productLine.getProductline());
      Collection<Product> productCollectionOld = persistentProductLine.getProductCollection();
      Collection<Product> productCollectionNew = productLine.getProductCollection();
      List<String> illegalOrphanMessages = null;
      for (Product productCollectionOldProduct : productCollectionOld) {
        if (!productCollectionNew.contains(productCollectionOldProduct)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain Product " + productCollectionOldProduct + " since its productline field is not nullable.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
      for (Product productCollectionNewProductToAttach : productCollectionNew) {
        productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getProductcode());
        attachedProductCollectionNew.add(productCollectionNewProductToAttach);
      }
      productCollectionNew = attachedProductCollectionNew;
      productLine.setProductCollection(productCollectionNew);
      productLine = em.merge(productLine);
      for (Product productCollectionNewProduct : productCollectionNew) {
        if (!productCollectionOld.contains(productCollectionNewProduct)) {
          ProductLine oldProductlineOfProductCollectionNewProduct = productCollectionNewProduct.getProductline();
          productCollectionNewProduct.setProductline(productLine);
          productCollectionNewProduct = em.merge(productCollectionNewProduct);
          if (oldProductlineOfProductCollectionNewProduct != null && !oldProductlineOfProductCollectionNewProduct.equals(productLine)) {
            oldProductlineOfProductCollectionNewProduct.getProductCollection().remove(productCollectionNewProduct);
            oldProductlineOfProductCollectionNewProduct = em.merge(oldProductlineOfProductCollectionNewProduct);
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
        String id = productLine.getProductline();
        if (findProductLine(id) == null) {
          throw new NonexistentEntityException("The productLine with id " + id + " no longer exists.");
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
      ProductLine productLine;
      try {
        productLine = em.getReference(ProductLine.class, id);
        productLine.getProductline();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The productLine with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      Collection<Product> productCollectionOrphanCheck = productLine.getProductCollection();
      for (Product productCollectionOrphanCheckProduct : productCollectionOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This ProductLine (" + productLine + ") cannot be destroyed since the Product " + productCollectionOrphanCheckProduct + " in its productCollection field has a non-nullable productline field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      em.remove(productLine);
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

  public List<ProductLine> findProductLineEntities() {
    return findProductLineEntities(true, -1, -1);
  }

  public List<ProductLine> findProductLineEntities(int maxResults, int firstResult) {
    return findProductLineEntities(false, maxResults, firstResult);
  }

  private List<ProductLine> findProductLineEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(ProductLine.class));
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

  public ProductLine findProductLine(String id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(ProductLine.class, id);
    } finally {
      em.close();
    }
  }

  public int getProductLineCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<ProductLine> rt = cq.from(ProductLine.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
