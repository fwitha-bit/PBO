<%-- 
    Document   : form_product
    Created on : 12 Dec 2025, 14.42.33
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="models.Product" %>
<% 
    String base = request.getContextPath(); 
    Product product = (Product) request.getAttribute("product");
    String error = (String) request.getAttribute("error");
    boolean editing = (product != null && product.getId() > 0);
%>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title><%= editing ? "Edit" : "Tambah" %> Produk</title>
    <link rel="stylesheet" href="<%= base %>/css/style.css">
</head>
<body>
    <div class="card">
        <h2><%= editing ? "Edit" : "Tambah" %> Produk Baru</h2>
        <% if (error != null) { %><p class="error"><%= error %></p><% } %>
        <form method="post" action="<%= base %>/product">
            <input type="hidden" name="menu" value="<%= editing ? "edit" : "add" %>">
            <% if (editing) { %><input type="hidden" name="id" value="<%= product.getId() %>"><% } %>
            <label>Nama Produk</label>
            <input type="text" name="name" value="<%= editing ? product.getName() : "" %>" required>
            <label>Kategori</label>
            <input type="text" name="category" value="<%= editing ? product.getCategory() : "" %>" placeholder="Misal: Elektronik" required>  <!-- Input text -->
            <label>Harga</label>
            <input type="number" step="0.01" name="price" value="<%= editing ? product.getPrice() : "" %>" required>
            <label>Stock</label>
            <input type="number" name="stock" value="<%= editing ? product.getStock() : "" %>" required>
            <label>Deskripsi</label>
            <textarea name="description"><%= editing ? product.getDescription() : "" %></textarea>
            <button type="submit">Simpan Produk</button>
        </form>
        <button onclick="location.href='<%= base %>/product?menu=list'">Kembali ke Daftar</button>
    </div>
</body>
</html>