package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private ProductRepository repo;

    @Override
    public void init() throws ServletException {
        repo = new ProductRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action == null || "list".equals(action)) {
            req.setAttribute("products", repo.findAll());
            req.getRequestDispatcher("/views/list_product.jsp").forward(req, resp);

        } else if ("search".equals(action)) {
            String kw = req.getParameter("keyword");
            req.setAttribute("products", repo.search(kw));
            req.setAttribute("keyword", kw);
            req.getRequestDispatcher("/views/list_product.jsp").forward(req, resp);

        } else if ("add".equals(action)) {
            req.getRequestDispatcher("/views/form_product.jsp").forward(req, resp);

        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id") == null ? "-1" : req.getParameter("id"));
            Product p = repo.findById(id);
            if (p != null) req.setAttribute("product", p);
            req.getRequestDispatcher("/views/form_product.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        String name = req.getParameter("name");
        String category = req.getParameter("category");
        String priceStr = req.getParameter("price");
        String stockStr = req.getParameter("stock");
        String desc = req.getParameter("description");

        // Object sementara untuk mempertahankan input saat error
        Product temp = new Product();
        temp.setName(name);
        temp.setCategory(category);
        temp.setDescription(desc);

        // Validasi
        String error = validate(name, category, priceStr, stockStr);
        if (error != null) {
            temp.setPrice(priceStr==null ? 0 : parseDouble(priceStr));
            temp.setStock(stockStr==null ? 0 : parseInt(stockStr));
            req.setAttribute("error", error);
            req.setAttribute("product", temp);
            if ("edit".equals(action)) temp.setId(parseInt(req.getParameter("id")));
            req.getRequestDispatcher("/views/form_product.jsp").forward(req, resp);
            return;
        }

        double price = Double.parseDouble(priceStr);
        int stock = Integer.parseInt(stockStr);

        if ("add".equals(action)) {
            repo.add(new Product(0, name, category, price, stock, desc));
            req.setAttribute("message", "Produk berhasil ditambahkan");
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Product p = repo.findById(id);
            if (p != null) {
                p.setName(name); p.setCategory(category); p.setPrice(price);
                p.setStock(stock); p.setDescription(desc);
                req.setAttribute("message", "Produk berhasil diubah");
            }
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            repo.delete(id);
            req.setAttribute("message", "Produk berhasil dihapus");
        }

        req.setAttribute("products", repo.findAll());
        req.getRequestDispatcher("/views/list_product.jsp").forward(req, resp);
    }

    private String validate(String n, String c, String p, String s) {
        if (n==null || n.trim().isEmpty()) return "Nama wajib diisi";
        if (c==null || c.trim().isEmpty()) return "Kategori wajib diisi";
        try { if (Double.parseDouble(p) < 0) return "Harga tidak boleh negatif"; }
        catch(Exception e) { return "Harga harus angka valid"; }
        try { if (Integer.parseInt(s) < 0) return "Stok tidak boleh negatif"; }
        catch(Exception e) { return "Stok harus angka valid"; }
        return null;
    }
    private int parseInt(String s) { try{return Integer.parseInt(s);}catch(Exception e){return 0;} }
    private double parseDouble(String s) { try{return Double.parseDouble(s);}catch(Exception e){return 0;} }
}