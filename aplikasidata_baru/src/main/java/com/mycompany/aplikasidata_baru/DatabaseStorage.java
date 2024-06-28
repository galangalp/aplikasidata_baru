/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplikasidata_baru;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class DatabaseStorage implements DataStorage{
    private Connection connection;

    public DatabaseStorage(String databasePath) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void writeData(String data) {
        try(PreparedStatement delete_Statement = connection.prepareStatement("DELETE FROM data");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO data VALUES (?)")) {
            delete_Statement.executeUpdate();
            statement.setString(1, data);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readData() {
        StringBuilder sb = new StringBuilder();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM data")
        ) {
           while(resultSet.next()) {
               sb.append(resultSet.getString("value"));
           }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    
}