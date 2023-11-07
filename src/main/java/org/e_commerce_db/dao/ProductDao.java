package org.e_commerce_db.dao;

import org.e_commerce_db.db.DBConnection;
import org.e_commerce_db.model.Category;
import org.e_commerce_db.model.Product;
import org.e_commerce_db.model.ProductInCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private static Connection connection;
    private static String GET_PRODUCT="SELECT id,productName,price,categoryId FROM product WHERE deleteFlag=false";
    private static String GET_PRODUCT_IN_CART="SELECT productName,price FROM product WHERE deleteFlag=false AND id=?";
    public ProductDao(){
        connection= DBConnection.getConnection();
    }


    public ArrayList<Product> getProducts() throws SQLException {
        ArrayList<Product> products=new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_PRODUCT);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("productName");
            int price = resultSet.getInt("price");
            int categoryId = resultSet.getInt("categoryId");
            Product product = new Product(id, name,price,categoryId);
            products.add(product);
        }
        resultSet.close();
        statement.close();

        return products;
    }

    public List<ProductInCart> getCartProduct(int id) throws SQLException {
        List<ProductInCart> productInCarts = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_IN_CART);
        statement.setInt(1, id); // Set the parameter for the query
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("productName");
            int price = resultSet.getInt("price");
            ProductInCart productInCart = new ProductInCart(name, price);
            productInCarts.add(productInCart);
        }
        resultSet.close();
        statement.close();

        return productInCarts;
    }

}
