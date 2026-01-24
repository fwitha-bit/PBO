/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author ASUS
 */



import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBC {
    private Connection conn = null;
    private Statement stmt = null;

    // Constructor → otomatis konek ke database
    public JDBC() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/DB_barang?useSSL=false&serverTimezone=Asia/Jakarta", 
                "root", 
                ""          // ganti kalau password MySQL kamu bukan kosong
            );
            stmt = conn.createStatement();
            System.out.println("Koneksi database berhasil!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Untuk INSERT, UPDATE, DELETE
    public void runQuery(String sql) {
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Untuk SELECT → mengembalikan List<Map> biar gampang di JSP
    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT * FROM barang ORDER BY id DESC";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getInt("id"));
                row.put("nama", rs.getString("nama"));
                row.put("jumlah", rs.getInt("jumlah"));
                row.put("harga", rs.getDouble("harga"));
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Tutup koneksi (panggil ini kalau sudah selesai)
    public void disconnect() {
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}