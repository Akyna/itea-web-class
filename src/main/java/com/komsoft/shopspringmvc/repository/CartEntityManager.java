package com.komsoft.shopspringmvc.repository;

import com.komsoft.shopspringmvc.exception.DataBaseException;
import com.komsoft.shopspringmvc.model.CartModel;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CartEntityManager implements CartDAO {
    EntityManager entityManager = Persistence.createEntityManagerFactory("shopspring").createEntityManager();

    @Override
    public CartModel save(CartModel cart) {
        entityManager.getTransaction().begin();
        CartModel savedCart = entityManager.merge(cart);
        entityManager.getTransaction().commit();
        return savedCart;
    }

    @Override
    public CartModel getById(long id) throws DataBaseException {
        return entityManager.find(CartModel.class, id);
    }

    @Override
    public CartModel getUnpaidCartByUserId(long userId) {
        TypedQuery<CartModel> query = entityManager.createNamedQuery("Cart.getUnpaidCartByUserId", CartModel.class);
        query.setParameter("userId", userId);
        CartModel savedCart = null;
        if (query.getResultList().size() > 0) {
            savedCart = query.getResultList().get(0);
        }
        return savedCart;
    }

    @Override
    public List<CartModel> getAllByUserId(long userId) {
        TypedQuery<CartModel> query = entityManager.createNamedQuery("Cart.getAllByUserId", CartModel.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
