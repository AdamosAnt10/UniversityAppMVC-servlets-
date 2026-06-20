<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Grades" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Βαθμολογίες Μαθήματος</title>
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

        .grades-container {
            width: 100%;
            max-width: 820px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 3px 12px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .header {
            background-color: #0d1f3c;
            color: white;
            padding: 18px;
            text-align: center;
        }

        .header h2 {
            margin: 0;
            font-size: 1.35rem;
        }

        /* Ίδιο ακριβώς στυλ με τη σελίδα του φοιτητή */
        .prof-info {
            padding: 14px 25px;
            font-size: 0.95rem;
            color: #444;
        }

        .prof-info strong {
            color: #222;
            font-weight: 600;
        }

        .table-wrapper {
            padding: 25px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            background-color: #0d1f3c;
            color: white;
            padding: 13px 12px;
            text-align: left;
        }

        td {
            padding: 13px 12px;
            border-bottom: 1px solid #e5e5e5;
        }

        tr:hover {
            background-color: #f8f5ec;     /* Ίδιο hover χρώμα */
        }

        .grade {
            font-weight: bold;
            text-align: center;
            width: 100px;
        }

        .btn {
            padding: 11px 24px;
            background-color: #0d1f3c;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 15px;
            font-weight: bold;
            cursor: pointer;
            margin-top: 20px;
        }

        .btn:hover {
            background-color: #1a3560;
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
    <div class="grades-container">

        <div class="header">
            <h2>Βαθμολογίες Μαθήματος</h2>
        </div>

        <!-- Professor Info - Ίδιο στυλ με τον φοιτητή -->
        <div class="prof-info">
            Καθηγητής: <strong><%= session.getAttribute("ProfessorName") %> <%= session.getAttribute("ProfessorSurname") %></strong>
            Ειδικότητα: <strong><%= session.getAttribute("ProfessorSpecialty") %></strong>
        </div>

        <div class="table-wrapper">
            <%
                List<Grades> gradesList = (List<Grades>) request.getAttribute("grades");
                if (gradesList == null || gradesList.isEmpty()) {
            %>
                <p style="color: red; text-align: center; padding: 20px;">
                    Δεν βρέθηκαν καταχωρημένοι βαθμοί για αυτό το μάθημα.
                </p>
            <%
                } else {
            %>
                <table>
                    <thead>
                        <tr>
                            <th>Α.Μ. Φοιτητή</th>
                            <th>Όνομα</th>
                            <th>Επώνυμο</th>
                            <th style="text-align: center;">Βαθμός</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Grades g : gradesList) {
                        %>
                            <tr>
                                <td><%= g.getregistrationNumber() %></td>
                                <td><%= g.getstudentName() %></td>
                                <td><%= g.getstudentSurname() %></td>
                                <td class="grade"><%= g.getgrade() %></td>
                            </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            <%
                }
            %>

            <br>
            <a href="${pageContext.request.contextPath}/views/dashboardprof.jsp">
                <button type="button" class="btn">← Επιστροφή στο Dashboard</button>
            </a>
        </div>
    </div>
</div>

<footer>
    &copy; 2026 Πανεπιστήμιο
</footer>

</body>
</html>