package org.e_commerce_db.model;

import java.util.ArrayList;

public class Cart {
    private int id;
    private int productId;
    private int userId;
    private int count;

    public Cart(int id, int productId, int userId, int count) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCount() {
        return count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
