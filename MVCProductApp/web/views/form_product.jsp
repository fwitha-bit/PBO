<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Product"%>
<% 
    String base = request.getContextPath();
    Product product = (Product) request.getAttribute("product");
    boolean edit = (product != null && product.getId() > 0);
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= edit ? "Edit" : "Tambah" %> Produk</title>
    <style>
        body {font-family: Arial; margin:40px;}
        .error {color:red; font-weight:bold; margin:10px 0;}
        label {display:block; margin-top:10px;}
        input, textarea {width:400px; padding:8px;}
        button {margin-top:20px; padding:10px 20px;}
    </style>
</head>
<body>
    <h2><%= edit ? "Edit" : "Tambah" %> Produk</h2>

    <% if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <form method="post" action="<%=base%>/product">
        <input type="hidden" name="action" value="<%= edit ? "edit" : "add" %>">
        <% if (edit) { %>
            <input type="hidden" name="id" value="<%=product.getId()%>">
        <% } %>

        <label>Nama Produk</label>
        <input type="text" name="name" value="<%= product!=null ? (product.getName()!=null?product.getName():"") : "" %>" required>

        <label>Kategori</label>
        <input type="text" name="category" value="<%= product!=null ? (product.getCategory()!=null?product.getCategory():"") : "" %>" required>

        <label>Harga</label>
        <input type="number" step="0.01" name="price" value="<%= product!=null && product.getPrice()>0 ? product.getPrice() : "" %>" required>

        <label>Stok</label>
        <input type="number" name="stock" value="<%= product!=null && product.getStock()>=0 ? product.getStock() : "" %>" required>

        <label>Deskripsi</label>
        <textarea name="description"><%= product!=null && product.getDescription()!=null ? product.getDescription() : "" %></textarea>

        <button type="submit"><%= edit ? "Simpan Perubahan" : "Tambah Produk" %></button>
    </form>

    <p><a href="<%=base%>/product?action=list">Kembali ke Daftar</a> | <a href="<%=base%>/home">Home</a></p>
</body>
</html>