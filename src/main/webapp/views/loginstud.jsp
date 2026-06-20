<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="el">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Σύνδεση - Πανεπιστήμιο</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f1e8;
            margin: 0;
            padding: 0;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        nav {
            background-color: #0d1f3c;
            padding: 15px 30px;
        }

        nav a {
            color: white;
            text-decoration: none;
            font-size: 18px;
            font-weight: bold;
        }

        .page-wrap {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 30px 15px;
        }

        .login-card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.15);
            width: 100%;
            max-width: 420px;
            padding: 40px 35px;
        }

        .login-card h2 {
            color: #0d1f3c;
            margin-bottom: 6px;
            font-size: 22px;
        }

        .login-card p {
            color: #777;
            font-size: 13px;
            margin-bottom: 25px;
        }

        .error-msg {
            background: #fdf0f0;
            border: 1px solid #f5c6c6;
            color: #c0392b;
            padding: 10px;
            border-radius: 4px;
            font-size: 13px;
            margin-bottom: 15px;
        }

        label {
            display: block;
            font-size: 13px;
            font-weight: bold;
            color: #333;
            margin-bottom: 5px;
            margin-top: 15px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            font-family: Arial, sans-serif;
            box-sizing: border-box;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            border-color: #c8972a;
            outline: none;
        }

        .remember-row {
            display: flex;
            align-items: center;
            margin-top: 15px;
            margin-bottom: 20px;
            gap: 6px;
            font-size: 13px;
            color: #555;
        }

        .remember-row label {
            margin: 0;
            font-weight: normal;
        }

        .btn-submit {
            width: 100%;
            padding: 12px;
            background-color: #0d1f3c;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 15px;
            font-family: Arial, sans-serif;
            font-weight: bold;
            cursor: pointer;
        }

        .btn-submit:hover {
            background-color: #1a3560;
        }

        .back-link {
            text-align: center;
            margin-top: 20px;
        }

        .back-link a {
            font-size: 13px;
            color: #777;
            text-decoration: none;
        }

        .back-link a:hover {
            color: #c8972a;
        }

        footer {
            background-color: #0d1f3c;
            color: rgba(255,255,255,0.4);
            text-align: center;
            padding: 15px;
            font-size: 12px;
        }
    </style>
</head>
<body>


<div class="page-wrap">
    <div class="login-card">

        <h2>Σύνδεση</h2>
        <p>Εισάγετε τα στοιχεία σας για να συνδεθείτε</p>

        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="error-msg">${errorMessage}</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/LoginServlet" method="POST">

            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Username" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Κωδικός πρόσβασης" required>

            <div class="remember-row">
                <input type="checkbox" name="rememberMe" id="rememberMe">
                <label for="rememberMe">Να με θυμάσαι</label>
            </div>

            <button type="submit" class="btn-submit">Σύνδεση</button>

        </form>

        <div class="back-link">
            <a href="${pageContext.request.contextPath}/views/index.jsp">← Επιστροφή στην αρχική</a>
        </div>

    </div>
</div>

<footer>
    &copy; 2026 Πανεπιστήμιο
</footer>

</body>
</html>