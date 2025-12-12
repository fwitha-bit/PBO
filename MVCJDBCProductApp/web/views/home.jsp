<%-- 
    Document   : home
    Created on : 12 Dec 2025, 14.41.21
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String base = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>
    <link rel="stylesheet" href="<%= base %>/css/style.css">
</head>
<body>
    <div class="card">
        <h2>Product Management</h2>
        <p>Selamat datang di Aplikasi Manajemen Produk.</p>
        <button onclick="location.href='<%= base %>/product?menu=list'">Lihat Daftar Produk</button>
        <button onclick="location.href='<%= base %>/product?menu=add'">Tambah Produk Baru</button>
        <button onclick="location.href='<%= base %>/product?menu=custom'">Statistik Custom View</button>
        <h3>Cari Produk</h3>
        <form method="get" action="<%= base %>/product">
            <input type="hidden" name="menu" value="search">
            <input type="text" name="keyword" placeholder="Nama atau Kategori">
            <button type="submit">Cari</button>
        </form>
    </div>
</body>
</html>