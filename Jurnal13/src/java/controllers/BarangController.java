/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author ASUS
 */



import db.JDBC;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BarangController", urlPatterns = {"/BarangController"})
public class BarangController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Ambil data dari form
        String nama = request.getParameter("nama");
        String jumlahStr = request.getParameter("jumlah");
        String hargaStr = request.getParameter("harga");

        if (nama != null && !nama.trim().isEmpty() &&
            jumlahStr != null && !jumlahStr.isEmpty() &&
            hargaStr != null && !hargaStr.isEmpty()) {

            try {
                int jumlah = Integer.parseInt(jumlahStr);
                double harga = Double.parseDouble(hargaStr);

                // Query INSERT yang aman dari karakter aneh
                String sql = "INSERT INTO barang (nama, jumlah, harga) VALUES (" +
                             "'" + nama.replace("'", "''") + "', " +
                             jumlah + ", " +
                             harga + ")";

                JDBC db = new JDBC();
                db.runQuery(sql);
                db.disconnect();

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Kembali ke halaman utama
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Kalau diakses lewat GET langsung arahkan ke index
        response.sendRedirect("index.jsp");
    }
}