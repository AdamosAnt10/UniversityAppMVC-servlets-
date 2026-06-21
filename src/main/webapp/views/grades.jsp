<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Αναλυτική Βαθμολογία</title>
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
            padding: 30px 20px;
            display: flex;
            justify-content: center;
        }

        .grades-container {
            width: 100%;
            max-width: 760px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 3px 12px rgba(0, 0, 0, 0.08);
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

        .student-info {
            padding: 14px 25px;
            font-size: 0.95rem;
            color: #444;
            background-color: #fafafa;
        }

        .student-info strong {
            color: #222;
            font-weight: 600;
        }

        .filter-bar {
            padding: 15px 25px;
            background-color: #f0f0f0;
            display: flex;
            align-items: center;
            gap: 10px;
            flex-wrap: wrap;
        }

        .filter-bar form {
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .filter-bar select {
            padding: 8px 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-family: Arial, sans-serif;
            font-size: 14px;
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
            padding: 13px 10px;
            text-align: left;
        }

        td {
            padding: 13px 10px;
            border-bottom: 1px solid #e5e5e5;
        }

        tr:hover {
            background-color: #f8f9fa;
        }

        .grade {
            font-weight: bold;
            text-align: center;
        }

        .average-box {
            background-color: #0d1f3c;
            color: white;
            padding: 12px 20px;
            border-radius: 4px;
            margin-bottom: 20px;
            font-size: 15px;
        }

        .btn {
            padding: 11px 24px;
            background-color: #0d1f3c;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            font-weight: bold;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #1a3560;
        }

        .btn-secondary {
            padding: 8px 16px;
            background-color: #0d1f3c;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            font-family: Arial, sans-serif;
            cursor: pointer;
        }

        .btn-secondary:hover {
            background-color: #1a3560;
        }

        .no-grades {
            text-align: center;
            color: #777;
            padding: 20px;
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
    <div class="grades-container">

        <div class="header">
            <h2>Αναλυτική Βαθμολογία</h2>
        </div>

        <div class="student-info">
            Φοιτητής: <strong>${studentName}</strong>
        </div>

       
        <div class="filter-bar">

            - Ολες οι βαθμολογίες -->
            <form action="${pageContext.request.contextPath}/StudentServlet" method="GET">
                <button type="submit" class="btn-secondary">Όλες οι Βαθμολογίες</button>
            </form>

           
		<form action="${pageContext.request.contextPath}/StudentServlet" method="GET">
    		<input type="hidden" name="action" value="bySemester">
    		<select name="semester" onchange="this.form.submit()">
        		<option value="">-- Επιλέξτε Εξάμηνο --</option>
        		<option value="1" ${semester == 1 ? 'selected' : ''}>1ο Εξάμηνο</option>
        		<option value="2" ${semester == 2 ? 'selected' : ''}>2ο Εξάμηνο</option>
        		<option value="3" ${semester == 3 ? 'selected' : ''}>3ο Εξάμηνο</option>
        		<option value="4" ${semester == 4 ? 'selected' : ''}>4ο Εξάμηνο</option>
        		<option value="5" ${semester == 5 ? 'selected' : ''}>5ο Εξάμηνο</option>
        		<option value="6" ${semester == 6 ? 'selected' : ''}>6ο Εξάμηνο</option>
        		<option value="7" ${semester == 7 ? 'selected' : ''}>7ο Εξάμηνο</option>
        		<option value="8" ${semester == 8 ? 'selected' : ''}>8ο Εξάμηνο</option>
    </select>
</form>

           
            <form action="${pageContext.request.contextPath}/StudentServlet" method="GET">
                <input type="hidden" name="action" value="average">
                <button type="submit" class="btn-secondary">Μέσος Όρος</button>
            </form>

        </div>

        <div class="table-wrapper">

            <c:if test="${not empty errorMessage}">
                <p style="color:red; text-align:center;">${errorMessage}</p>
            </c:if>

            
            <c:if test="${filterType == 'average'}">
                <div class="average-box">
                    Συνολικός Μέσος Όρος: <strong><c:out value="${String.format('%.2f', average)}"/></strong>
                </div>
            </c:if>

           
            <c:if test="${filterType == 'semester'}">
                <p style="color:#0d1f3c; font-weight:bold;">
                    Βαθμολογία ${semester}ου Εξαμήνου
                </p>
            </c:if>

            <table>
                <thead>
                    <tr>
                        <th>Αριθμός Μητρώου</th>
                        <th>Κωδικός Μαθήματος</th>
                        <th style="text-align:center;">Βαθμός</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty grades}">
                            <c:forEach items="${grades}" var="g">
                                <tr>
                                    <td>${g.registrationNumber}</td>
                                    <td>${g.course}</td>
                                    <td class="grade">${g.grade}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="3" class="no-grades">Δεν βρέθηκαν βαθμολογίες.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

            <a href="${pageContext.request.contextPath}/views/dashboard.jsp">
                <button type="button" class="btn" style="margin-top:20px;">Επιστροφή στο Dashboard</button>
            </a>

        </div>
    </div>
</div>

<footer>
    &copy; 2026 Πανεπιστήμιο
</footer>

</body>
</html>