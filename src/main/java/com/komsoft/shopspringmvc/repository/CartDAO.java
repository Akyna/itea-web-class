package com.komsoft.shopspringmvc.repository;

import com.komsoft.shopspringmvc.exception.DataBaseException;
import com.komsoft.shopspringmvc.model.CartModel;

import java.util.List;

public interface CartDAO {

    CartModel save(CartModel cart) throws DataBaseException;
    CartModel getById(long id) throws DataBaseException;
    CartModel getUnpaidCartByUserId(long userId) throws DataBaseException;
//    for shopping history
    List<CartModel> getAllByUserId(long userId) throws DataBaseException;

}
