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
import sit.int303.demo.model.ProductLine;
import sit.int303.demo.model.OrderDetail;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import sit.int303.demo.model.Product;
import sit.int303.demo.model.controller.exceptions.IllegalOrphanException;
import sit.int303.demo.model.controller.exceptions.NonexistentEntityException;
import sit.int303.demo.model.controller.exceptions.PreexistingEntityException;
import sit.int303.demo.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author bas
 */
public class ProductJpaController implements Serializable {

  public ProductJpaController(UserTransaction utx, EntityManagerFactory emf) {
    this.utx = utx;
    this.emf = emf;
  }
  private UserTransaction utx = null;
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Product product) throws PreexistingEntityException, RollbackFailureException, Exception {
    if (product.getOrderDetailCollection() == null) {
      product.setOrderDetailCollection(new ArrayList<OrderDetail>());
    }
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      ProductLine productline = product.getProductline();
      if (productline != null) {
        productline = em.getReference(productline.getClass(), productline.getProductline());
        product.setProductline(productline);
      }
      Collection<OrderDetail> attachedOrderDetailCollection = new ArrayList<OrderDetail>();
      for (OrderDetail orderDetailCollectionOrderDetailToAttach : product.getOrderDetailCollection()) {
        orderDetailCollectionOrderDetailToAttach = em.getReference(orderDetailCollectionOrderDetailToAttach.getClass(), orderDetailCollectionOrderDetailToAttach.getOrderDetailPK());
        attachedOrderDetailCollection.add(orderDetailCollectionOrderDetailToAttach);
      }
      product.setOrderDetailCollection(attachedOrderDetailCollection);
      em.persist(product);
      if (productline != null) {
        productline.getProductCollection().add(product);
        productline = em.merge(productline);
      }
      for (OrderDetail orderDetailCollectionOrderDetail : product.getOrderDetailCollection()) {
        Product oldProductOfOrderDetailCollectionOrderDetail = orderDetailCollectionOrderDetail.getProduct();
        orderDetailCollectionOrderDetail.setProduct(product);
        orderDetailCollectionOrderDetail = em.merge(orderDetailCollectionOrderDetail);
        if (oldProductOfOrderDetailCollectionOrderDetail != null) {
          oldProductOfOrderDetailCollectionOrderDetail.getOrderDetailCollection().remove(orderDetailCollectionOrderDetail);
          oldProductOfOrderDetailCollectionOrderDetail = em.merge(oldProductOfOrderDetailCollectionOrderDetail);
        }
      }
      utx.commit();
    } catch (Exception ex) {
      try {
        utx.rollback();
      } catch (Exception re) {
        throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
      }
      if (findProduct(product.getProductcode()) != null) {
        throw new PreexistingEntityException("Product " + product + " already exists.", ex);
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Product product) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Product persistentProduct = em.find(Product.class, product.getProductcode());
      ProductLine productlineOld = persistentProduct.getProductline();
      ProductLine productlineNew = product.getProductline();
      Collection<OrderDetail> orderDetailCollectionOld = persistentProduct.getOrderDetailCollection();
      Collection<OrderDetail> orderDetailCollectionNew = product.getOrderDetailCollection();
      List<String> illegalOrphanMessages = null;
      for (OrderDetail orderDetailCollectionOldOrderDetail : orderDetailCollectionOld) {
        if (!orderDetailCollectionNew.contains(orderDetailCollectionOldOrderDetail)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain OrderDetail " + orderDetailCollectionOldOrderDetail + " since its product field is not nullable.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      if (productlineNew != null) {
        productlineNew = em.getReference(productlineNew.getClass(), productlineNew.getProductline());
        product.setProductline(productlineNew);
      }
      Collection<OrderDetail> attachedOrderDetailCollectionNew = new ArrayList<OrderDetail>();
      for (OrderDetail orderDetailCollectionNewOrderDetailToAttach : orderDetailCollectionNew) {
        orderDetailCollectionNewOrderDetailToAttach = em.getReference(orderDetailCollectionNewOrderDetailToAttach.getClass(), orderDetailCollectionNewOrderDetailToAttach.getOrderDetailPK());
        attachedOrderDetailCollectionNew.add(orderDetailCollectionNewOrderDetailToAttach);
      }
      orderDetailCollectionNew = attachedOrderDetailCollectionNew;
      product.setOrderDetailCollection(orderDetailCollectionNew);
      product = em.merge(product);
      if (productlineOld != null && !productlineOld.equals(productlineNew)) {
        productlineOld.getProductCollection().remove(product);
        productlineOld = em.merge(productlineOld);
      }
      if (productlineNew != null && !productlineNew.equals(productlineOld)) {
        productlineNew.getProductCollection().add(product);
        productlineNew = em.merge(productlineNew);
      }
      for (OrderDetail orderDetailCollectionNewOrderDetail : orderDetailCollectionNew) {
        if (!orderDetailCollectionOld.contains(orderDetailCollectionNewOrderDetail)) {
          Product oldProductOfOrderDetailCollectionNewOrderDetail = orderDetailCollectionNewOrderDetail.getProduct();
          orderDetailCollectionNewOrderDetail.setProduct(product);
          orderDetailCollectionNewOrderDetail = em.merge(orderDetailCollectionNewOrderDetail);
          if (oldProductOfOrderDetailCollectionNewOrderDetail != null && !oldProductOfOrderDetailCollectionNewOrderDetail.equals(product)) {
            oldProductOfOrderDetailCollectionNewOrderDetail.getOrderDetailCollection().remove(orderDetailCollectionNewOrderDetail);
            oldProductOfOrderDetailCollectionNewOrderDetail = em.merge(oldProductOfOrderDetailCollectionNewOrderDetail);
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
        String id = product.getProductcode();
        if (findProduct(id) == null) {
          throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
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
      Product product;
      try {
        product = em.getReference(Product.class, id);
        product.getProductcode();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      Collection<OrderDetail> orderDetailCollectionOrphanCheck = product.getOrderDetailCollection();
      for (OrderDetail orderDetailCollectionOrphanCheckOrderDetail : orderDetailCollectionOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the OrderDetail " + orderDetailCollectionOrphanCheckOrderDetail + " in its orderDetailCollection field has a non-nullable product field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      ProductLine productline = product.getProductline();
      if (productline != null) {
        productline.getProductCollection().remove(product);
        productline = em.merge(productline);
      }
      em.remove(product);
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

  public List<Product> findProductEntities() {
    return findProductEntities(true, -1, -1);
  }

  public List<Product> findProductEntities(int maxResults, int firstResult) {
    return findProductEntities(false, maxResults, firstResult);
  }

  private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Product.class));
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

  public Product findProduct(String id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Product.class, id);
    } finally {
      em.close();
    }
  }

  public int getProductCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Product> rt = cq.from(Product.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
  public List<Product> findByProductName(String productName) {
    EntityManager em = getEntityManager();
    try {
      Query query = em.createNamedQuery("Product.findByProductname");
      query.setParameter("productname", "%" +  productName + "%");
      return query.getResultList();
    } finally {
      em.close();
    }
    
  }
}
