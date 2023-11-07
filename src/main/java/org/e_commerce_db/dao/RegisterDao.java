package org.e_commerce_db.dao;

import org.e_commerce_db.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
    private static Connection connection;
    private static String ADD_USER="INSERT INTO user(email,password) VALUES(?,?)";

    public RegisterDao(){
        connection= DBConnection.getConnection();
    }

    public void addUser(String email,String password) throws SQLException {
        PreparedStatement statement= connection.prepareStatement(ADD_USER);
        statement.setString(1,email);
        statement.setString(2,password);
        statement.executeUpdate();

    }
}
