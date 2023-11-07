package org.e_commerce_db.dao;

import org.e_commerce_db.db.DBConnection;
import org.e_commerce_db.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDao {
    private static Connection connection;
    private static String GET_CATEGORY="SELECT id,categoryName FROM category WHERE deleteFlag=false";

    public CategoryDao(){
        connection= DBConnection.getConnection();
    }


    public ArrayList<Category> getCategories() throws SQLException {
        ArrayList<Category> categories=new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_CATEGORY);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("categoryName");
            Category category = new Category(id, name);
            categories.add(category);
        }
        resultSet.close();
        statement.close();

        return categories;
    }
}
