/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ASUS
 */


import jakarta.servlet.http.HttpServletRequest; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private String category;  
    private double price;
    private int stock;
    private String description;

    // === ctor & getters/setters (tetap) ===
    public Product() {}
    public Product(int id, String name, String category, double price, int stock, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }  // getter untuk category TEXT
    public void setCategory(String category) { this.category = category; }  // setter untuk input text
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // toModel dari ResultSet (tanpa JOIN categories)
    public static Product toModel(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setCategory(rs.getString("category"));  // Langsung dari kolom category TEXT
        p.setPrice(rs.getDouble("price"));
        p.setStock(rs.getInt("stock"));
        p.setDescription(rs.getString("description"));
        return p;
    }

    // Get all or filtered (search keyword nama/category + filter stock)
    public static List<Product> get(DBConnection db, String keyword, Integer minStock, Integer maxStock) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT id, name, category, price, stock, description FROM products WHERE 1=1");
        List<String> params = new ArrayList<>();  // Untuk PreparedStatement

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (LOWER(name) LIKE ? OR LOWER(category) LIKE ?)");
            String like = "%" + keyword.toLowerCase() + "%";
            params.add(like);
            params.add(like);
        }
        if (minStock != null) {
            sql.append(" AND stock >= ?");
            params.add(minStock.toString());
        }
        if (maxStock != null) {
            sql.append(" AND stock <= ?");
            params.add(maxStock.toString());
        }
        sql.append(" ORDER BY id");

        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            // Set parameters
            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
                list.add(toModel(rs));
            }
            return list;
        }
    }

    // Find by id (tanpa JOIN)
    public static Product find(DBConnection db, int id) throws SQLException {
        String sql = "SELECT id, name, category, price, stock, description FROM products WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? toModel(rs) : null;
        }
    }

    // Insert
    public static Product insert(DBConnection db, Product p) throws SQLException {
        String sql = "INSERT INTO products (name, category, price, stock, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            ps.setString(1, p.getName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getDescription());
            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) p.setId(rs.getInt(1));
                }
            }
            conn.commit();
            return p;
        }
    }

    // Update
    public static boolean update(DBConnection db, Product p) throws SQLException {
        String sql = "UPDATE products SET name=?, category=?, price=?, stock=?, description=? WHERE id=?";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setString(1, p.getName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getDescription());
            ps.setInt(6, p.getId());
            int affected = ps.executeUpdate();
            conn.commit();
            return affected > 0;
        }
    }

    // Delete
    public static boolean delete(DBConnection db, int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id=?";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ps.setInt(1, id);
            int affected = ps.executeUpdate();
            conn.commit();
            return affected > 0;
        }
    }
}