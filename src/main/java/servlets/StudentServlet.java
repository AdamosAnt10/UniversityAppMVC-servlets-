package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Grades;
import model.StudentDAO;
import model.Students;

import java.io.IOException;
import java.util.List;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    } 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        Integer currentAM = null;
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("StudentAM") != null) {
            currentAM = (Integer) session.getAttribute("StudentAM");
        }

        if (currentAM == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("savedStudentAM")) {
                        currentAM = Integer.parseInt(c.getValue());
                        session = request.getSession(true);
                        session.setAttribute("StudentAM", currentAM);
                        break;
                    }
                }
            }
        }

        if (currentAM == null) {
            response.sendRedirect(request.getContextPath() + "/views/loginstud.jsp");
            return;
        }

        String action = request.getParameter("action");
        
        try {
            // 1. ΒΑΘΜΟΛΟΓΙΑ ΑΝΑ ΕΞΑΜΗΝΟ
            if ("bySemester".equals(action)) {
                int semester = Integer.parseInt(request.getParameter("semester"));
                List<Grades> grades = studentDAO.getGradesBySemester(currentAM, semester);
                request.setAttribute("grades", grades);
                request.setAttribute("semester", semester);
                request.setAttribute("filterType", "semester");
                request.getRequestDispatcher("/views/grades.jsp").forward(request, response);
                return;
            }

            // 2. ΣΥΝΟΛΙΚΗ ΒΑΘΜΟΛΟΓΙΑ
            if ("average".equals(action)) {
                List<Grades> grades = studentDAO.getGradesByAM(currentAM);
                double average = studentDAO.getAverageGrade(currentAM);
                request.setAttribute("grades", grades);
                request.setAttribute("average", average);
                request.setAttribute("filterType", "average");
                request.getRequestDispatcher("/views/grades.jsp").forward(request, response);
                return;
            }

            // 3. ΟΛΕΣ ΟΙ ΒΑΘΜΟΛΟΓΙΕΣ (default)
            List<Grades> grades = studentDAO.getGradesByAM(currentAM);
            request.setAttribute("grades", grades);
            request.setAttribute("StudentAM", currentAM);
            request.setAttribute("studentName", session.getAttribute("StudentName"));
            request.setAttribute("filterType", "all");
            request.getRequestDispatcher("/views/grades.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Σφάλμα: " + e.getMessage());
            request.getRequestDispatcher("/views/loginstud.jsp").forward(request, response);
        }
    } 
} 