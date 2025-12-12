/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author ASUS
 */


import models.DBConnection;
import models.Product;
import jakarta.servlet.ServletException; 
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductServlet extends HttpServlet {
    private DBConnection db;

    @Override
    public void init() throws ServletException {
        String realDbPath = getServletContext().getRealPath("/WEB-INF/data/product.db");
        db = new DBConnection(realDbPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String menu = req.getParameter("menu");
        try {
            if (menu == null || "list".equals(menu) || "search".equals(menu)) {
                String keyword = req.getParameter("keyword");
                Integer minStock = parseInt(req.getParameter("min_stock"), null);
                Integer maxStock = parseInt(req.getParameter("max_stock"), null);
                List<Product> products = Product.get(db, keyword, minStock, maxStock);
                req.setAttribute("products", products);
                req.setAttribute("keyword", keyword);
                req.setAttribute("minStock", minStock);
                req.setAttribute("maxStock", maxStock);
                req.getRequestDispatcher("/views/list_product.jsp").forward(req, resp);
            } else if ("add".equals(menu)) {
                req.getRequestDispatcher("/views/form_product.jsp").forward(req, resp);
            } else if ("edit".equals(menu)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Product p = Product.find(db, id);
                if (p == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                req.setAttribute("product", p);
                req.getRequestDispatcher("/views/form_product.jsp").forward(req, resp);
            } else if ("custom".equals(menu)) {
                req.getRequestDispatcher("/views/view_custom.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String menu = req.getParameter("menu");
        try {
            Product p = new Product();
            p.setName(req.getParameter("name").trim());
            p.setCategory(req.getParameter("category").trim());  // Input text biasa
            p.setPrice(Double.parseDouble(req.getParameter("price")));
            p.setStock(Integer.parseInt(req.getParameter("stock")));
            p.setDescription(req.getParameter("description").trim());

            String err = validate(p);
            if (err != null) {
                req.setAttribute("error", err);
                req.setAttribute("product", p);
                req.getRequestDispatcher("/views/form_product.jsp").forward(req, resp);
                return;
            }

            if ("add".equals(menu)) {
                Product.insert(db, p);
                req.setAttribute("message", "Produk ditambahkan");
            } else if ("edit".equals(menu)) {
                p.setId(Integer.parseInt(req.getParameter("id")));
                Product.update(db, p);
                req.setAttribute("message", "Produk diubah");
            } else if ("delete".equals(menu)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Product.delete(db, id);
                req.setAttribute("message", "Produk dihapus");
            }
            List<Product> products = Product.get(db, null, null, null);
            req.setAttribute("products", products);
            req.getRequestDispatcher("/views/list_product.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private String validate(Product p) {
        if (p.getName().isBlank()) return "Nama tidak boleh kosong";
        if (p.getCategory().isBlank()) return "Kategori tidak boleh kosong";  
        if (p.getPrice() < 0) return "Harga >= 0";
        if (p.getStock() < 0) return "Stock >= 0";
        return null;
    }

    private Integer parseInt(String s, Integer def) {
        if (s == null || s.trim().isEmpty()) return def;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }
}