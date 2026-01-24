package controllers;

import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Product;

@WebServlet(name = "ProductController", urlPatterns = {"product"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Cek session login
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String menu = request.getParameter("menu");

        // Jika menu null atau kosong, default ke view
        if (menu == null || menu.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/product?menu=view");
            return;
        }

        Product productModel = new Product();

        switch (menu) {
            case "view" -> {
                ArrayList<Product> products = productModel.get();
                request.setAttribute("products", products);
                request.getRequestDispatcher("/product/view.jsp").forward(request, response);
            }
            case "add" -> {
                request.getRequestDispatcher("/product/add.jsp").forward(request, response);
            }
            case "edit" -> {
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty()) {
                    try {
                        Product product = productModel.find(idParam);
                        if (product != null) {
                            request.setAttribute("product", product);
                            request.getRequestDispatcher("/product/edit.jsp").forward(request, response);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        // id tidak valid
                    }
                }
                // Jika id tidak ada atau produk tidak ditemukan, kembali ke view
                response.sendRedirect(request.getContextPath() + "/product?menu=view");
            }
            default -> {
                response.sendRedirect(request.getContextPath() + "/product?menu=view");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Cek session lagi untuk POST
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String action = request.getParameter("action");
        Product productModel = new Product();

        try {
            if ("add".equals(action)) {
                String name = request.getParameter("nama");
                String hargaStr = request.getParameter("harga");

                if (name == null || name.trim().isEmpty() || hargaStr == null || hargaStr.trim().isEmpty()) {
                    // Validasi sederhana
                    response.sendRedirect(request.getContextPath() + "/product?menu=add&error=1");
                    return;
                }

                double price = Double.parseDouble(hargaStr);
                productModel.setName(name.trim());
                productModel.setPrice(price);
                productModel.insert();

            } else if ("edit".equals(action)) {
                String idStr = request.getParameter("id");
                String name = request.getParameter("nama");
                String hargaStr = request.getParameter("harga");

                if (idStr == null || name == null || hargaStr == null) {
                    response.sendRedirect(request.getContextPath() + "/product?menu=view");
                    return;
                }

                int id = Integer.parseInt(idStr);
                double price = Double.parseDouble(hargaStr);

                productModel.setId(id);
                productModel.setName(name.trim());
                productModel.setPrice(price);
                productModel.update();

            } else if ("delete".equals(action)) {
                String idStr = request.getParameter("id");
                if (idStr != null && !idStr.isEmpty()) {
                    int id = Integer.parseInt(idStr);
                    productModel.setId(id);
                    productModel.delete();
                }
            }

            // Setelah semua operasi berhasil, kembali ke dashboard
            response.sendRedirect(request.getContextPath() + "/product?menu=view");

        } catch (NumberFormatException e) {
            // Jika parse harga atau id gagal, kembali ke view dengan error (opsional)
            response.sendRedirect(request.getContextPath() + "/product?menu=view&error=parse");
        }
    }
}