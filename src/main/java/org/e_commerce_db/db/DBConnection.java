package org.e_commerce_db.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String connectionUrl="jdbc:mysql://localhost:3306/e_commerce_db";

    private static final String userName="root";
    private static final String password="root";

    public static Connection getConnection(){
        Connection connection=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(connectionUrl,userName,password);
//            System.out.println("Connection: "+!connection.isClosed());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
