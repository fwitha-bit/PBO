<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java Server Page - Login & Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f4f4f4;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .tabs {
            text-align: center;
            margin: 30px 0;
        }
        .tabs a {
            padding: 12px 30px;
            margin: 0 10px;
            text-decoration: none;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            font-size: 18px;
        }
        .tabs a:hover {
            background-color: #0056b3;
        }
        .tabs a.active {
            background-color: #28a745;
        }
        .content {
            max-width: 400px;
            margin: 0 auto;
            padding: 30px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }
        input[type="email"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-top: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            margin-top: 25px;
            padding: 12px 20px;
            width: 100%;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

    <h1>Selamat Datang di Aplikasi JSP</h1>

    <div class="tabs">
        <a href="index.jsp?page=login" 
           <%= "login".equals(request.getParameter("page")) || request.getParameter("page") == null ? "class='active'" : "" %>>Login</a>
        <a href="index.jsp?page=register" 
           <%= "register".equals(request.getParameter("page")) ? "class='active'" : "" %>>Register</a>
    </div>

    <div class="content">
        <%
            String pageParam = request.getParameter("page");
            if ("login".equals(pageParam) || pageParam == null) {
        %>
            <jsp:include page="login.jsp" />
        <%
            } else if ("register".equals(pageParam)) {
        %>
            <jsp:include page="register.jsp" />
        <%
            }
        %>
    </div>

</body>
</html>
