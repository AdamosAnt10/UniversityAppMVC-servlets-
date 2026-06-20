<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Professors" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Γραμματείας</title>
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
            padding: 40px 20px;
            display: flex;
            justify-content: center;
        }

        .dash-container {
            width: 100%;
            max-width: 520px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .header {
            background-color: #0d1f3c;
            color: white;
            padding: 22px;
            text-align: center;
        }

        .header h2 {
            margin: 0;
            font-size: 1.45rem;
        }

        .dash-content {
            padding: 30px 35px;
            text-align: center;
        }

        .success-msg {
            color: green;
            font-size: 1.1rem;
            margin-bottom: 8px;
        }

        .secretary-name {
            font-size: 1.35rem;
            font-weight: bold;
            color: #0d1f3c;
            margin: 10px 0 4px 0;
        }

        .role {
            color: #555;
            font-size: 0.98rem;
            margin-bottom: 20px;
        }

        .divider {
            border: none;
            border-top: 1px solid #eee;
            margin: 25px auto;
            width: 70px;
        }

        .btn {
            display: block;
            width: 100%;
            padding: 13px;
            margin-bottom: 12px;
            background-color: #0d1f3c;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 15px;
            font-weight: bold;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
        }

        .btn:hover {
            background-color: #1a3560;
        }

        .btn-secondary {
            background-color: transparent;
            color: #555;
            border: 1px solid #ccc;
            font-weight: normal;
        }

        .btn-secondary:hover {
            background-color: #f0e9d8;
            color: #333;
        }

        .error-msg { 
            color: red; 
            margin: 10px 0; 
            font-size: 14px; 
        }

        .success-info { 
            color: green; 
            margin: 10px 0; 
            font-size: 14px; 
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
    <div class="dash-container">

        <!-- Header -->
        <div class="header">
            <h2>Dashboard Γραμματείας</h2>
        </div>

        <!-- Main Content -->
        <div class="dash-content">

            <p class="success-msg">✓ Επιτυχής Σύνδεση</p>
            <p class="secretary-name">
                <%= session.getAttribute("SecretaryName") %> <%= session.getAttribute("SecretarySurname") %>
            </p>
            <p class="role">Γραμματεία</p>

            <% if (request.getAttribute("errorMessage") != null) { %>
                <p class="error-msg"><%= request.getAttribute("errorMessage") %></p>
            <% } %>

            <% if (session.getAttribute("successMessage") != null) { %>
                <p class="success-info"><%= session.getAttribute("successMessage") %></p>
                <% session.removeAttribute("successMessage"); %>
            <% } %>

            <hr class="divider">

            <!-- Buttons -->
            <form action="${pageContext.request.contextPath}/SecretaryServlet" method="GET">
                <input type="hidden" name="action" value="viewcourses" />
                <button type="submit" class="btn">Εμφάνιση Λίστας Μαθημάτων</button>
            </form>

            <form action="${pageContext.request.contextPath}/views/createcourse.jsp" method="GET">
                <button type="submit" class="btn">Δημιουργία Νέου Μαθήματος</button>
            </form>

            <form action="${pageContext.request.contextPath}/views/assignprofessor.jsp" method="GET">
                <button type="submit" class="btn">Ανάθεση Καθηγητή σε Μάθημα</button>
            </form>

            <form action="${pageContext.request.contextPath}/views/createprofessor.jsp" method="GET">
                <button type="submit" class="btn">Εγγραφή Νέου Καθηγητή</button>
            </form>

            <form action="${pageContext.request.contextPath}/views/createstudent.jsp" method="GET">
                <button type="submit" class="btn">Εγγραφή Νέου Φοιτητή</button>
            </form>

            <hr class="divider">

         <a href="${pageContext.request.contextPath}/LogoutServlet" class="btn btn-secondary">Αποσύνδεση</a>
        </div>
    </div>
</div>

<footer>
    &copy; 2026 Πανεπιστήμιο
</footer>

</body>
</html>