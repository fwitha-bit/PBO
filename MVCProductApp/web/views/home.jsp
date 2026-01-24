<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String base = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Home - Katalog Produk</title>
    <style>
        body {font-family: Arial, sans-serif; margin: 40px;}
        a {padding: 8px 16px; background: #1976d2; color: white; text-decoration: none; border-radius: 4px;}
        form {margin-top: 20px;}
    </style>
</head>
<body>
    <h2>Selamat Datang di Katalog Produk</h2>
    <p><a href="<%=base%>/product?action=list">Lihat Daftar Produk</a></p>
    <p><a href="<%=base%>/product?action=add">Tambah Produk Baru</a></p>

    <h3>Pencarian Cepat</h3>
    <form action="<%=base%>/product" method="get">
        <input type="hidden" name="action" value="search">
        <input type="text" name="keyword" placeholder="Cari nama atau kategori..." size="40">
        <button type="submit">Cari</button>
    </form>
</body>
</html>