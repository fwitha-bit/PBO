<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, models.Product"%>
<% 
    String base = request.getContextPath();
    List<Product> products = (List<Product>) request.getAttribute("products");
    String message = (String) request.getAttribute("message");
    String keyword = (String) request.getAttribute("keyword");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Daftar Produk</title>
    <style>
        body {font-family: Arial; margin:40px;}
        table {width:100%; border-collapse: collapse;}
        th, td {border:1px solid #ccc; padding:8px; text-align:left;}
        th {background:#f0f0f0;}
        .msg {color:green; font-weight:bold;}
        .danger {background:#d32f2f; color:white; border:none; padding:6px;}
    </style>
</head>
<body>
    <h2>Daftar Produk</h2>
    <% if (message != null) { %>
        <p class="msg"><%= message %></p>
    <% } %>

    <form method="get" style="margin:10px 0;">
        <input type="hidden" name="action" value="search">
        <input type="text" name="keyword" value="<%=keyword!=null?keyword:""%>" placeholder="Cari...">
        <button type="submit">Cari</button>
        <a href="<%=base%>/product?action=add">Tambah Produk</a>
        <a href="<%=base%>/home">Home</a>
    </form>

    <table>
        <tr>
            <th>ID</th><th>Nama</th><th>Kategori</th><th>Harga</th><th>Stok</th><th>Deskripsi</th><th>Aksi</th>
        </tr>
        <% for (Product p : products) { %>
        <tr>
            <td><%=p.getId()%></td>
            <td><%=p.getName()%></td>
            <td><%=p.getCategory()%></td>
            <td><%=p.getPrice()%></td>
            <td><%=p.getStock()%></td>
            <td><%=p.getDescription()%></td>
            <td>
                <a href="<%=base%>/product?action=edit&id=<%=p.getId()%>">Edit</a>
                <form method="post" style="display:inline;" onsubmit="return confirm('Hapus?')">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%=p.getId()%>">
                    <button class="danger" type="submit">Delete</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>