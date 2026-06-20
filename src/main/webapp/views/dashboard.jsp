<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Καλώς ήρθες</title>
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

        .content {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 40px 20px;
        }

        .dash-wrap {
            width: 100%;
            max-width: 480px;
            text-align: center;
        }

        .success-msg {
            color: green;
            font-size: 1.1rem;
            margin-bottom: 10px;
        }

        .student-name {
            font-size: 1.5rem;
            font-weight: bold;
            color: #0d1f3c;
            margin-bottom: 6px;
        }

        .student-info {
            color: #555;
            font-size: 0.95rem;
            margin-bottom: 4px;
        }

        .divider {
            border: none;
            border-top: 1px solid #ccc;
            margin: 25px auto;
            width: 60px;
        }

        .btn {
            display: block;
            width: 100%;
            padding: 12px;
            margin-bottom: 12px;
            background-color: #0d1f3c;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 15px;
            font-family: Arial, sans-serif;
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
            color: #777;
            border: 1px solid #ccc;
            font-weight: normal;
        }

        .btn-secondary:hover {
            background-color: #e8e8e8;
            color: #444;
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

<div class="content">
    <div class="dash-wrap">

        <p class="success-msg">✓ Επιτυχής Σύνδεση</p>
        <p class="student-name">${sessionScope.StudentName} ${sessionScope.StudentSurname}</p>
        <p class="student-info">Α.Μ.: ${sessionScope.StudentAM}</p>
        <p class="student-info">Τμήμα: ${sessionScope.StudentDepartment}</p>

        <hr class="divider">

        <a href="/UniversityAppMVC/StudentServlet" class="btn">Εμφάνιση αναλυτικής βαθμολογίας</a>
       <a href="${pageContext.request.contextPath}/LogoutServlet" class="btn btn-secondary">Αποσύνδεση</a>

    </div>
</div>

<footer>&copy; 2026 Πανεπιστήμιο</footer>

</body>
</html>