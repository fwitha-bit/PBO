<%-- 
    Document   : index
    Created on : 10 Dec 2025, 00.58.21
    Author     : ASUS
--%>

<%@page import="java.sql.*, moduls.TestKoneksiJDBC"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TP Modul 13 - Input Identitas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #74ebd5, #acb6e5);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .card { max-width: 500px; border-radius: 20px; box-shadow: 0 15px 35px rgba(0,0,0,0.3); }
        .btn-kirim { background: linear-gradient(45deg, #ff6b6b, #ee5a52); border: none; border-radius: 50px; padding: 12px 50px; font-weight: bold; }
        .btn-kirim:hover { transform: translateY(-3px); box-shadow: 0 10px 20px rgba(255,107,107,0.4); }
        .hasil { background: rgba(255,255,255,0.9); border-radius: 15px; padding: 20px; margin-top: 20px; }
    </style>
</head>
<body>

<div class="container">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white text-center py-4">
            <h2><i class="bi bi-person-check"></i> Identitas Praktikan</h2>
            <p class="mb-0">Modul 13 - Java Servlet & JDBC</p>
        </div>
        <div class="card-body p-5">

            <form method="post" class="text-center">
                <div class="mb-4">
                    <label class="form-label fw-bold">NIM :</label>
                    <input type="text" name="nim" class="form-control form-control-lg text-center" placeholder="Masukkan NIM" required>
                </div>
                <div class="mb-4">
                    <label class="form-label fw-bold">Nama :</label>
                    <input type="text" name="nama" class="form-control form-control-lg text-center" placeholder="Masukkan Nama" required>
                </div>
                <button type="submit" class="btn btn-kirim text-white">Kirim</button>
            </form>

            <%
                String nim = request.getParameter("nim");
                String nama = request.getParameter("nama");

                if (nim != null && nama != null && !nim.trim().isEmpty()) {
                    Connection conn = null;
                    PreparedStatement ps = null;
                    try {
                        conn = TestKoneksiJDBC.getConnection();
                        String sql = "INSERT INTO praktikan (nim, nama) VALUES (?, ?)";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, nim.trim());
                        ps.setString(2, nama.trim());
                        ps.executeUpdate();
            %>
                        <div class="hasil mt-4 animate__animated animate__fadeIn">
                            <h4 class="text-success"><i class="bi bi-check-circle"></i> Data berhasil disimpan!</h4>
                            <hr>
                            <p class="mb-1"><strong>NIM Anda adalah:</strong> <%= nim %></p>
                            <p><strong>Nama Anda adalah:</strong> <%= nama %></p>
                        </div>
            <%
                    } catch (Exception e) {
                        out.println("<div class='alert alert-danger mt-3'>Error: " + e.getMessage() + "</div>");
                    } finally {
                        try { if(ps != null) ps.close(); } catch(Exception e){}
                        try { if(conn != null) conn.close(); } catch(Exception e){}
                    }
                }
            %>

        </div>
        <div class="card-footer text-center text-muted">
            <small>&copy; 2025 PEMROGRAMAN BERORIENTASI OBJEK</small>
        </div>
    </div>
</div>

</body>
</html>