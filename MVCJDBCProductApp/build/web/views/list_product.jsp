<%-- 
    Document   : list_product
    Created on : 12 Dec 2025, 14.42.20
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, models.Product" %>
<% 
    String base = request.getContextPath(); 
    List<Product> products = (List<Product>) request.getAttribute("products");
    String message = (String) request.getAttribute("message");
    String keyword = (String) request.getAttribute("keyword");
    Integer minStock = (Integer) request.getAttribute("minStock");
    Integer maxStock = (Integer) request.getAttribute("maxStock");
%>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Daftar Produk</title>
    <link rel="stylesheet" href="<%= base %>/css/style.css">
</head>
<body>
    <div class="card">
        <h2>Daftar Produk</h2>
        <% if (message != null) { %><p class="message"><%= message %></p><% } %>
        <form method="get" action="<%= base %>/product">
            <input type="hidden" name="menu" value="search">
            <input type="text" name="keyword" value="<%= keyword != null ? keyword : "" %>" placeholder="Cari nama/kategori">
            <label>Min Stock</label><input type="number" name="min_stock" value="<%= minStock != null ? minStock : "" %>">
            <label>Max Stock</label><input type="number" name="max_stock" value="<%= maxStock != null ? maxStock : "" %>">
            <button type="submit">Filter</button>
        </form>
        <button onclick="location.href='<%= base %>/product?menu=add'">Tambah Produk</button>
        <button onclick="location.href='<%= base %>/home'">Home</button>
        <table>
            <tr><th>ID</th><th>Nama</th><th>Kategori</th><th>Harga</th><th>Stock</th><th>Deskripsi</th><th>Aksi</th></tr>
            <% if (products != null) { for (Product p : products) { %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getName() %></td>
                <td><%= p.getCategory() %></td>  <!-- Langsung category TEXT -->
                <td>Rp <%= String.format("%,.0f", p.getPrice()) %></td>
                <td><%= p.getStock() %></td>
                <td><%= p.getDescription() %></td>
                <td>
                    <a href="<%= base %>/product?menu=edit&id=<%= p.getId() %>" class="btn btn-small btn-edit">Edit</a>
                    <form method="post" action="<%= base %>/product" style="display:inline;" onsubmit="return confirm('Hapus?')">
                        <input type="hidden" name="menu" value="delete">
                        <input type="hidden" name="id" value="<%= p.getId() %>">
                        <button class="btn btn-small btn-delete" type="submit">Hapus</button>
                    </form>
                </td>
            </tr>
            <% } } else { %>
            <tr><td colspan="7">Tidak ada produk</td></tr>
            <% } %>
        </table>
    </div>
</body>
</html>