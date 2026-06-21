<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Εγγραφή Φοιτητή</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f1e8;
            margin: 0;
            padding: 0;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .content {
            flex: 1;
            padding: 30px 20px;
            display: flex;
            justify-content: center;
        }

        .form-container {
            width: 100%;
            max-width: 520px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 3px 12px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .header {
            background-color: #0d1f3c;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .header h2 {
            margin: 0;
            font-size: 1.4rem;
        }

        .form-wrapper {
            padding: 30px 35px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
            color: #333;
            font-size: 0.95rem;
        }

        input[type="text"],
        input[type="number"],
        input[type="password"] {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1rem;
            margin-bottom: 18px;
            box-sizing: border-box;
        }

        input:focus {
            outline: none;
            border-color: #0d1f3c;
            box-shadow: 0 0 0 3px rgba(13, 31, 60, 0.1);
        }

        .btn {
            padding: 12px 24px;
            background-color: #0d1f3c;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 15px;
            font-weight: bold;
            cursor: pointer;
            margin-right: 10px;
        }

        .btn:hover {
            background-color: #1a3560;
        }

        .btn-secondary {
            background-color: #f8f5ec;
            color: #333;
            border: 1px solid #ccc;
        }

        .btn-secondary:hover {
            background-color: #e8e0d0;
        }

        footer {
            background-color: #0d1f3c;
            color: rgba(255,255,255,0.45);
            text-align: center;
            padding: 14px;
            font-size: 12px;
        }
    </style>
</head>
<body>

<div class="content">
    <div class="form-container">

        <!-- Header -->
        <div class="header">
            <h2>Εγγραφή Νέου Φοιτητή</h2>
        </div>

        <!-- Form -->
        <div class="form-wrapper">

            <c:if test="${not empty errorMessage}">
                <p style="color:red; text-align:center;">${errorMessage}</p>
            </c:if>

            <form action="${pageContext.request.contextPath}/SecretaryServlet" method="POST">
                <input type="hidden" name="action" value="createstudent" />

                <label for="reg_num">Αριθμός Μητρώου (ΑΜ):</label>
                <input type="number" id="reg_num" name="registration_number" required />

                <label for="username">Username Φοιτητή:</label>
                <input type="text" id="username" name="username" required />

                <label for="name">Όνομα:</label>
                <input type="text" id="name" name="name" required />

                <label for="surname">Επώνυμο:</label>
                <input type="text" id="surname" name="surname" required />

                <label for="department">Τμήμα:</label>
                <input type="text" id="department" name="department" required />

                <label for="password">Κωδικός Πρόσβασης (Password):</label>
                <input type="password" id="password" name="password" required />

                <br><br>
                <button type="submit" class="btn">Εγγραφή Φοιτητή</button>
            </form>

            <br>
            <a href="${pageContext.request.contextPath}/views/dashboardsec.jsp">
                <button type="button" class="btn btn-secondary">Ακύρωση / Επιστροφή στο Dashboard</button>
            </a>
        </div>
    </div>
</div>

<footer>
    &copy; 2026 Πανεπιστήμιο
</footer>

</body>
</html>