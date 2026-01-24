<%@page import="models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
    ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
%>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard Barang</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container my-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <div>
                <h2 class="text-primary fw-bold mb-1">Daftar Barang</h2>
                <p class="text-muted">Selamat datang, <strong><%= userSession.getAttribute("user") %></strong></p>
            </div>
            <div>
                <a href="<%= request.getContextPath() %>/product?menu=add" class="btn btn-success me-2">
                    Tambah Barang
                </a>
                <form method="POST" action="<%= request.getContextPath() %>/AuthController" style="display: inline;">
                    <input type="hidden" name="action" value="logout">
                    <button type="submit" class="btn btn-danger">Logout</button>
                </form>
            </div>
        </div>

        <div class="card shadow">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-striped table-hover align-middle">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nama</th>
                                <th>Harga</th>
                                <th class="text-center">Aksi</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                if (products != null && !products.isEmpty()) {
                                    for (Product product : products) {
                            %>
                            <tr>
                                <td><%= product.getId() %></td>
                                <td><%= product.getName() %></td>
                                <td>Rp <%= String.format("%,.0f", product.getPrice()) %></td>
                                <td class="text-center">
                                    <a href="<%= request.getContextPath() %>/product?menu=edit&id=<%= product.getId() %>"
                                       class="btn btn-warning btn-sm me-1">Edit</a>
                                    <form method="POST" action="<%= request.getContextPath() %>/product" style="display: inline;">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="id" value="<%= product.getId() %>">
                                        <button type="submit" class="btn btn-danger btn-sm"
                                                onclick="return confirm('Apakah Anda yakin ingin menghapus barang ini?');">
                                            Hapus
                                        </button>
                                    </form>
                                </td>
                            </tr>
                            <%
                                    }
                                } else {
                            %>
                            <tr>
                                <td colspan="4" class="text-center py-4 text-muted">
                                    <em>Belum ada data barang. Silakan tambah barang baru.</em>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>