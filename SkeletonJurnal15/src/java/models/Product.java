package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product extends Model<Product> {
    private int id;
    private String name;
    private double price;

    public Product() {
       this.table = "products";       
        this.primaryKey = "id";
    }
    
    public Product(int id, String name, double price) {
        this.table = "products";        
        this.primaryKey = "id";

        
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Product toModel(ResultSet rs) {
        try {
            
            return new Product(
                rs.getInt("id"),          // kolom id
                rs.getString("name"),     // kolom name/nama
                rs.getDouble("price")     // kolom price/harga
            );
        } catch (SQLException e) {
            System.out.println("Error converting ResultSet to Product: " + e.getMessage());
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
