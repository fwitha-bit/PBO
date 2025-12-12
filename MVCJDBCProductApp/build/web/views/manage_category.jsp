<%-- 
    Document   : view_custom
    Created on : 12 Dec 2025, 14.42.44
    Author     : ASUS
--%>

<<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, models.Category" %>
<% 
    String base = request.getContextPath(); 
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Kelola Kategori</title>
    <link rel="stylesheet" href="<%= base %>/css/style.css">
</head>
<body>
    <div class="card">
        <h2>Kelola Kategori</h2>
        <% if (message != null) { %><p class="message"><%= message %></p><% } %>
        <button onclick="location.href='<%= base %>/category?menu=add'">Tambah Kategori Baru</button>
        <table>
            <tr><th>Nama Kategori</th><th>Aksi</th></tr>
            <% for (Category c : categories) { %>
            <tr>
                <td><%= c.getName() %></td>
                <td>
                    <form method="post" action="<%= base %>/category" style="display:inline;" onsubmit="return confirm('Hapus kategori ini? Produk terkait akan dihapus.')">
                        <input type="hidden" name="menu" value="delete">
                        <input type="hidden" name="id" value="<%= c.getId() %>">
                        <button class="danger" type="submit">Hapus</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <a href="<%= base %>/home">Kembali ke Home</a>
    </div>
</body>
</html>
