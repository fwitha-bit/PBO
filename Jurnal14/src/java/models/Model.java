package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Model<T> {
    protected String table;
    protected String primaryKey = "id";

    protected Connection conn = null;
    protected Statement stmt = null;
    protected PreparedStatement pstmt = null;
    protected ResultSet rs = null;

    
    public void connect() {
        try {
            
            String url = "jdbc:mysql://localhost:3306/nama_database_kamu";  
            String user = "root";
            String password = "";  

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

        

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Ganti method disconnect() jadi seperti ini (harus null-safe!)
    public void disconnect() {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.out.println("Error close resultset: " + e.getMessage());
        }
        try {
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error close preparedstatement: " + e.getMessage());
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.out.println("Error close statement: " + e.getMessage());
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error close connection: " + e.getMessage());
        }
    }
}