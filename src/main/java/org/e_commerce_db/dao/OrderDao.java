package org.e_commerce_db.dao;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.e_commerce_db.db.DBConnection;
import org.e_commerce_db.model.OrderDetail;
import org.e_commerce_db.model.Orders;
import org.e_commerce_db.model.Product;
import org.e_commerce_db.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class OrderDao {
    private static Connection connection;
    private static String PLACE_ORDER = "INSERT INTO orders(userId) VALUES(?)";
    private static String ORDER_ID = "SELECT id from orders ORDER BY id DESC LIMIT 1;";
    private static String INSERT_ORDERDETAIL = "INSERT INTO ORDERDETAIL(orderId,productId,count) VALUES(?,?,?)";
    private static String GET_ORDER = "SELECT id,userId,statusId,date FROM ORDERS WHERE userId=?";
    private static String GET_ORDERDETAIL = "SELECT id,orderId,productId,count FROM orderdetail WHERE orderId=?";

    public OrderDao() {
        connection = DBConnection.getConnection();
    }

    public void createOrder(int userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(PLACE_ORDER);
        statement.setInt(1, userId);
        statement.executeUpdate();
    }

    public int getOrderId() {
        int id = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(ORDER_ID);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void insertOrderDetail(int orderId, int productId, int count) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_ORDERDETAIL);
        statement.setInt(1, orderId);
        statement.setInt(2, productId);
        statement.setInt(3, count);
        statement.executeUpdate();
    }

    public ArrayList<Orders> getUserOrder(int loggedInUser) throws SQLException {
        ArrayList<Orders> orders = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_ORDER);
        statement.setInt(1, loggedInUser);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int userId = resultSet.getInt("userId");
            int statusId = resultSet.getInt("statusId");
            Date date = resultSet.getDate("date");
            Orders order = new Orders(id, userId, statusId, date);
            orders.add(order);
        }
        resultSet.close();
        statement.close();

        return orders;
    }

    public ArrayList<OrderDetail> printOrderDetail(int orderId) throws SQLException {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_ORDERDETAIL);
        statement.setInt(1, orderId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int order = resultSet.getInt("orderId");
            int productId = resultSet.getInt("productId");
            int count = resultSet.getInt("count");
            OrderDetail orderDetail = new OrderDetail(id,order,productId,count);
            orderDetails.add(orderDetail);
        }
        resultSet.close();
        statement.close();

        return orderDetails;
    }
}

