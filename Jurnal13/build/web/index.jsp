<%-- 
    Document   : index
    Created on : 10 Dec 2025, 14.57.52
    Author     : ASUS
--%>

<%@page import="db.JDBC"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Formulir Barang</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #f8f9fa; padding-top: 40px; }
        .card { box-shadow: 0 4px 12px rgba(0,0,0,0.1); }
        h1, h2 { color: #0d6efd; }
    </style>
</head>
<body>
<div class="container">

    <div class="text-center mb-5">
        <h1 class="display-5 fw-bold">Formulir Barang</h1>
        <p class="text-muted">Jurnal Modul 13 – Servlet & JDBC</p>
    </div>

    <!-- Form Input -->
    <div class="card mb-5">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Input Barang Baru</h4>
        </div>
        <div class="card-body">
            <form action="BarangController" method="POST">
                <div class="row g-3">
                    <div class="col-md-5">
                        <label class="form-label">Nama Barang</label>
                        <input type="text" name="nama" class="form-control" required>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">Jumlah</label>
                        <input type="number" name="jumlah" class="form-control" min="1" required>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">Harga (Rp)</label>
                        <input type="number" step="0.01" name="harga" class="form-control" min="0" required>
                    </div>
                    <div class="col-md-1 d-flex align-items-end">
                        <button type="submit" class="btn btn-success w-100">Simpan</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- Daftar Barang -->
    <div class="card">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Daftar Barang</h4>
        </div>
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover mb-0">
                    <thead class="table-light">
                        <tr>
                            <th width="80">ID</th>
                            <th>Nama Barang</th>
                            <th width="120">Jumlah</th>
                            <th width="180">Harga</th>
                            <th width="120">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            JDBC db = new JDBC();
                            List<Map<String, Object>> daftar = db.getData();
                            for (Map<String, Object> b : daftar) {
                        %>
                        <tr>
                            <td><%= b.get("id") %></td>
                            <td><strong><%= b.get("nama") %></strong></td>
                            <td class="text-center"><%= b.get("jumlah") %></td>
                            <td class="text-end">Rp <%= String.format("%,.2f", b.get("harga")) %></td>
                            <td class="text-end">Rp <%= String.format("&,.2f", b.get("harga")+("jumlah")) %></td>
                        </tr>
                        <%
                            }
                            db.disconnect();
                        %>
                    </tbody>
                </table>
                <% if (daftar.isEmpty()) { %>
                    <div class="text-center py-5 text-muted">Belum ada data barang.</div>
                <% } %>
            </div>
        </div>
    </div>

    <div class="text-center mt-5 text-muted">
        <small>© 2025 – Praktikum PBO – Fakultas Informatika Telkom University</small>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>