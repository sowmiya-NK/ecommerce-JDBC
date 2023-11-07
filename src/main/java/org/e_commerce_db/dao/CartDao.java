package org.e_commerce_db.dao;

import org.e_commerce_db.db.DBConnection;
import org.e_commerce_db.model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDao {
    private static Connection connection;
    private static String GET_CART="SELECT id,productId,userId,count FROM cart WHERE userId=? AND isOrdered=false";
    private static String ADD_TO_CART="INSERT INTO cart(userId,productId,count) VALUES(?,?,?)";
    private static String UPDATE_CART="UPDATE cart SET count=count+1 WHERE userId=? AND productId=? AND isOrdered=false";
    private static String UPDATE_ORDER_CART="UPDATE cart SET isOrdered=true WHERE userId=? AND productId=? AND isOrdered=false";
    public CartDao(){
        connection= DBConnection.getConnection();
    }

    public ArrayList<Cart> getUserCart(int user) throws SQLException {
        ArrayList<Cart> cart=new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_CART);
        statement.setInt(1,user);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int productId = resultSet.getInt("productId");
            int userId = resultSet.getInt("userId");
            int count = resultSet.getInt("count");
            Cart cartProduct = new Cart(id, productId,userId,count);
            cart.add(cartProduct);
        }
        resultSet.close();
        statement.close();

        return cart;
    }

    public void updateCart(int userId,int productId) throws SQLException {
        PreparedStatement statement= connection.prepareStatement(UPDATE_CART);
        statement.setInt(1,userId);
        statement.setInt(2,productId);
//        statement.setInt(3,count);
        statement.executeUpdate();
    }

    public void addToCart(int userId,int productId,int count) throws SQLException {
        PreparedStatement statement= connection.prepareStatement(ADD_TO_CART);
        statement.setInt(1,userId);
        statement.setInt(2,productId);
        statement.setInt(3,count);
        statement.executeUpdate();
    }

    public void updateOrderCart(int userid, int productId) throws SQLException {
        PreparedStatement statement= connection.prepareStatement(UPDATE_ORDER_CART);
        statement.setInt(1,userid);
        statement.setInt(2,productId);
        statement.executeUpdate();
    }
}
