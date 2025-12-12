<%-- 
    Document   : view_custom
    Created on : 12 Dec 2025, 15.24.59
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.DBConnection"%>
<%@page import="java.sql.*"%>
<%
    
    String realPath = application.getRealPath("/WEB-INF/data/product.db");
    DBConnection db = new DBConnection(realPath);

   
    int totalProduk = 0;
    double totalNilaiStock = 0.0;
    double rataRataHarga = 0.0;
    double hargaTermahal = 0.0;
    double hargaTermurah = 0.0;
    String kategoriTerbanyak = "-";
    int jumlahKategoriTerbanyak = 0;

    try (Connection conn = db.connect();
         Statement stmt = conn.createStatement()) {

        
        ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) AS total FROM products");
        if (rs1.next()) totalProduk = rs1.getInt("total");

        
        ResultSet rs2 = stmt.executeQuery("SELECT SUM(price * stock) AS nilai FROM products");
        if (rs2.next()) totalNilaiStock = rs2.getDouble("nilai");

       
        ResultSet rs3 = stmt.executeQuery("SELECT AVG(price) AS rata FROM products");
        if (rs3.next()) rataRataHarga = rs3.getDouble("rata");

        
        ResultSet rs4 = stmt.executeQuery("SELECT MAX(price) AS max, MIN(price) AS min FROM products");
        if (rs4.next()) {
            hargaTermahal = rs4.getDouble("max");
            hargaTermurah = rs4.getDouble("min");
        }

        
        ResultSet rs5 = stmt.executeQuery(
            "SELECT c.name AS kategori, COUNT(*) AS jumlah " +
            "FROM products p JOIN categories c ON p.category_id = c.id " +
            "GROUP BY c.name ORDER BY jumlah DESC LIMIT 1"
        );
        if (rs5.next()) {
            kategoriTerbanyak = rs5.getString("kategori");
            jumlahKategoriTerbanyak = rs5.getInt("jumlah");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Statistik & Custom View</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <div class="card">
        <h2>Statistik Katalog Produk (Custom View)</h2>

        <%-- Pesan sukses kalau ada --%>
        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null) { %>
            <div class="message"><%= message %></div>
        <% } %>

        <table>
            <tr>
                <th>Deskripsi</th>
                <th>Hasil</th>
            </tr>
            <tr>
                <td>Total Produk</td>
                <td><strong><%= totalProduk %> produk</strong></td>
            </tr>
            <tr>
                <td>Total Nilai Stok (Harga × Stok)</td>
                <td><strong>Rp <%= String.format("%,.2f", totalNilaiStock) %></strong></td>
            </tr>
            <tr>
                <td>Rata-rata Harga Produk</td>
                <td><strong>Rp <%= String.format("%,.2f", rataRataHarga) %></strong></td>
            </tr>
            <tr>
                <td>Harga Termahal</td>
                <td><strong>Rp <%= String.format("%,.2f", hargaTermahal) %></strong></td>
            </tr>
            <tr>
                <td>Harga Termurah</td>
                <td><strong>Rp <%= String.format("%,.2f", hargaTermurah) %></strong></td>
            </tr>
            <tr>
                <td>Kategori dengan Produk Terbanyak</td>
                <td><strong><%= kategoriTerbanyak %> (<%= jumlahKategoriTerbanyak %> produk)</strong></td>
            </tr>
        </table>

        <br>
        <button onclick="location.href='<%= request.getContextPath() %>/home'">Kembali ke Home</button>
        <button onclick="location.href='<%= request.getContextPath() %>/product?menu=list'">Lihat Daftar Produk</button>
    </div>
</body>
</html>
