<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="el">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Πανεπιστήμιο - Αρχική</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Source+Sans+3:wght@300;400;600&display=swap" rel="stylesheet">
    
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Source Sans 3', sans-serif;
            background: #f5f1e8;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            line-height: 1.6;
        }

        /* Hero Image */
        .hero-img {
            width: 100%;
            max-height: 260px;
            object-fit: cover;
        }

        /* Content */
        .content {
            max-width: 760px;
            margin: 0 auto;
            padding: 50px 1.5rem 60px;
            text-align: center;
            flex: 1; /* Σπρώχνει το footer στο τέλος */
        }

        .content h1 {
            font-family: 'Playfair Display', serif;
            font-size: 2.3rem;
            color: #0d1f3c;
            margin-bottom: 24px;
        }

        .content p {
            color: #5a6a7e;
            font-size: 1.05rem;
            margin-bottom: 18px;
        }

        .divider {
            width: 70px;
            height: 3px;
            background: #c8972a;
            margin: 30px auto;
        }

        .btn-login {
            display: inline-block;
            margin-top: 20px;
            background: #0d1f3c;
            color: white;
            padding: 14px 38px;
            border-radius: 4px;
            text-decoration: none;
            font-weight: 600;
            font-size: 1rem;
            transition: background 0.3s ease;
        }

        .btn-login:hover {
            background: #1a3560;
        }

        /* Footer */
        footer {
            background-color: #0d1f3c;
            color: rgba(255,255,255,0.7);
            text-align: center;
            padding: 20px;
            font-size: 13px;
            margin-top: auto;
        }
    </style>
</head>
<body>

    <img src="<%= request.getContextPath() %>/images/papei.png" alt="Πανεπιστήμιο" class="hero-img">

    <div class="content">
        <h1>Καλώς ήρθατε στο Πανεπιστημιακό Πληροφοριακό Σύστημα.</h1>
        
        <div class="divider"></div>
        
        <p>Η κεντρική πλατφόρμα διαχείρισης ακαδημαϊκών και διοικητικών υπηρεσιών για φοιτητές, καθηγητές και γραμματεία.</p>

        <a href="loginstud.jsp" class="btn-login">Είσοδος στο Σύστημα →</a>
    </div>

    <footer>
        &copy; 2026 Πανεπιστήμιο
    </footer>

</body>
</html>