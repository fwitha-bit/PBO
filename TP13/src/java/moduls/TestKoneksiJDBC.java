/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moduls;

/**
 *
 * @author ASUS
 */



import java.sql.Connection;
import java.sql.DriverManager;

public class TestKoneksiJDBC {
    private static Connection conn;
    
    public static Connection getConnection() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/TP13_Praktikan?useSSL=false&serverTimezone=Asia/Jakarta&allowPublicKeyRetrieval=true";
                String user = "root";
                String pass = "";
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, pass);
            } catch (Exception e) {
                System.out.println("Error koneksi: " + e.getMessage());
            }
        }
        return conn;
    }
}