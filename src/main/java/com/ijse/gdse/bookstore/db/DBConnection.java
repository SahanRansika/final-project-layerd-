package com.ijse.gdse.bookstore.db;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bookstore" , "root", "Ijse@1234");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(e.getMessage());
            alert.setHeaderText("Error");
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(e.getMessage());
            alert.setHeaderText("Error");
        }
    }
    public static DBConnection getInstance(){
        if(dbConnection == null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
    public Connection getConnection(){
        return connection;
    }
}
