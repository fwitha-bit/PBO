/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ASUS
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String driver = "org.sqlite.JDBC";
    private final String url;

    public DBConnection(String absoluteDbPath) {
        this.url = "jdbc:sqlite:" + absoluteDbPath;
    }

    public Connection connect() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC Driver tidak ditemukan", e);
        }
        return DriverManager.getConnection(url);
    }
}