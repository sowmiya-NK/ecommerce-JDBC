package org.e_commerce_db.model;

public class Product {
    private int id;
    private String title;
    private int price;
    private int category;

    public Product(int id, String title, int price, int category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
