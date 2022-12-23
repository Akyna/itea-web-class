package com.komsoft.shopspringmvc.model;

import com.komsoft.shopspringmvc.dto.ProductDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;

@Entity
@Table(name = "cart")
@NamedQueries(value = {
        @NamedQuery(name = "Cart.getAllByUserId", query = "SELECT c FROM CartModel c WHERE c.userId = :userId"),
        @NamedQuery(name = "Cart.getUnpaidCartByUserId", query = "SELECT c FROM CartModel c WHERE c.userId = :userId AND c.paid = FALSE")
})
public class CartModel implements Serializable {
//    private static final long serialVersionUID = 1841697545037805593L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    private long userId;
    private boolean paid = false;
    private HashMap<ProductDto, Integer> products;

    public CartModel() {
    }

    public CartModel(long userId) {
        this.userId = userId;
        this.products = new HashMap<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public boolean getPaid() {
        return paid;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean isPaid) {
        this.paid = isPaid;
    }

    public HashMap<ProductDto, Integer> getProducts() {
        return products;
    }

    public int size() {
        return products.size();
    }

    public void addProduct(ProductDto productDto, int count) {
        if (this.products == null) {
            this.products = new HashMap<>();
        }
        if (productDto != null) {
            int quantity = this.products.get(productDto) == null ? 0 : this.products.get(productDto);
            quantity += count;
            this.products.put(productDto, quantity);
        }
    }

    public void removeProduct(ProductDto productDto) {
        if (productDto != null && this.products != null) {
            this.products.remove(productDto);
        }
    }

}
