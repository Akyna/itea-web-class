package com.komsoft.shopspringmvc.service;

import com.komsoft.shopspringmvc.exception.DataBaseException;
import com.komsoft.shopspringmvc.factory.DAOFactory;
import com.komsoft.shopspringmvc.model.CartModel;
import org.springframework.stereotype.Service;

@Service
public class CartMicroServiceService {
    private final DAOFactory daoFactory;

    public CartMicroServiceService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

     public CartModel getUnpaidCartByUser(long userId) throws DataBaseException {
        CartModel cart = null;
        cart = daoFactory.getCartDAO().getUnpaidCartByUserId(userId);
        return cart;
    }

    public CartModel saveCart(CartModel cart) throws DataBaseException {
        return daoFactory.getCartDAO().save(cart);
    }

}
