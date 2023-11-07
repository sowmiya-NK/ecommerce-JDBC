package org.e_commerce_db.dao;

import org.e_commerce_db.db.DBConnection;
import org.e_commerce_db.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static Connection connection;
    private static String LOGIN_QUERY="SELECT id, email, password,roleId FROM user WHERE email=? and password=?";


    public UserDao() {
        connection= DBConnection.getConnection();
    }

    public User validateUser(String email,String password){
        User user=null;
        try{
            PreparedStatement statement=connection.prepareStatement(LOGIN_QUERY);
            statement.setString(1,email);
            statement.setString(2,password);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user=new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("roleId"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
