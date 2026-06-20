<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Δημιουργία Μαθήματος</title>
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

        <div class="header">
            <h2>Φόρμα Δημιουργίας Νέου Μαθήματος</h2>
        </div>

        <div class="form-wrapper">
            <form action="${pageContext.request.contextPath}/SecretaryServlet" method="POST">
                <input type="hidden" name="action" value="createCourse" />

                <label for="course_id">Κωδικός Μαθήματος (ID):</label>
                <input type="text" id="course_id" name="course_id" required />

                <label for="course_name">Όνομα Μαθήματος:</label>
                <input type="text" id="course_name" name="course_name" required />

                <label for="professor_username">Username Υπεύθυνου Καθηγητή:</label>
                <input type="text" id="professor_username" name="professor_username" />

                <br><br>
                <button type="submit" class="btn">Δημιουργία Μαθήματος</button>
            </form>

            <br>
            <form action="${pageContext.request.contextPath}/SecretaryServlet" method="GET">
                <button type="submit" class="btn btn-secondary">Ακύρωση / Επιστροφή στο Dashboard</button>
            </form>
        </div>
    </div>
</div>

<footer>
    &copy; 2026 Πανεπιστήμιο
</footer>

</body>
</html>