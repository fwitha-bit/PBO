package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "AuthController", urlPatterns = {"/AuthController"})
public class AuthController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");

        
        if ("login".equals(action)) {
            
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
           
            if ("admin".equals(username) && "1234".equals(password)) {
                
                HttpSession session = request.getSession();
                
               
                session.setAttribute("user", username);
                
                // Setelah login sukses, langsung ke dashboard produk
                response.sendRedirect(request.getContextPath() + "/product/view.jsp");
                
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
            
        } else if ("logout".equals(action)) {
            
            // Ambil session yang ada (false agar tidak membuat session baru)
            HttpSession session = request.getSession(false);
            
           
            if (session != null) {
                
                session.invalidate();
            }
            
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            
        } else {
            // Action tidak dikenali, kembali ke halaman login
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}