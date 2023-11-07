package org.e_commerce_db.model;

public class OrderDetail {
    private int id;
    private int orderId;
    private int ProductId;
    private int count;
public OrderDetail(){

}
    public OrderDetail(int id, int orderId, int productId, int count) {
        this.id = id;
        this.orderId = orderId;
        this.ProductId = productId;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return ProductId;
    }

    public int getCount() {
        return count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
