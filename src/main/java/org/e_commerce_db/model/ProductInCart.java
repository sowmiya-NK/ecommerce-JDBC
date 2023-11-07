package org.e_commerce_db.model;

public class ProductInCart {
    private String productTitle;
    private int price;

    public ProductInCart(String productTitle, int count) {
        this.productTitle = productTitle;
        this.price = count;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public int getPrice() {
        return price;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setPrice(int count) {
        this.price = count;
    }
}
