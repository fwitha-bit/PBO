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

@WebServlet(name = "ProductController", urlPatterns = {"/product"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        HttpSession session = request.getSession();

        
        if (session.getAttribute("user") == null) {
            
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String menu = request.getParameter("menu");

 
        if (menu == null || menu.isEmpty()) {
           
            response.sendRedirect("/product/view.jsp");
            return;
        }

        Product productModel = new Product();

        if ("view".equals(menu)) {
            ArrayList<Product> products = productModel.get();
            request.setAttribute("products", products);
            request.getRequestDispatcher("/product/view.jsp").forward(request, response);

        } else if ("add".equals(menu)) {
            request.getRequestDispatcher("/product/add.jsp").forward(request, response);

        } else if ("edit".equals(menu)) {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                Product product = productModel.find(idParam);
                if (product != null) {
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("/product/edit.jsp").forward(request, response);
                    return;
                }
            }
            response.sendRedirect("product?menu=view");

        } else {
            response.sendRedirect("product?menu=view");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String action = request.getParameter("action");
        Product productModel = new Product();

        if ("add".equals(action)) {
            String name = request.getParameter("nama");
            double price = Double.parseDouble(request.getParameter("harga"));
            productModel.setName(name);
            productModel.setPrice(price);
            productModel.insert();

        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("nama");
            double price = Double.parseDouble(request.getParameter("harga"));
            productModel.setId(id);
            productModel.setName(name);
            productModel.setPrice(price);
            productModel.update();

        
        } else if ("delete".equals(action)) {
            
            int id = Integer.parseInt(request.getParameter("id"));
            productModel.setId(id);
            productModel.delete();
        }

       
        response.sendRedirect("product?menu=view");
    }
}