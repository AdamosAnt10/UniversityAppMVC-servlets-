package servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Grades;
import model.Professors;
import model.ProfessorsDAO;

@WebServlet("/ProfessorServlet")
public class ProfessorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProfessorsDAO professorsDAO = new ProfessorsDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        
       
        if ("addGrade".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("username") == null) {
                response.sendRedirect(request.getContextPath() + "/views/loginstud.jsp");
                return;
            }

            String username = (String) session.getAttribute("username");
            String regNumStr = request.getParameter("registration_number");
            String gradeStr = request.getParameter("grade");

            try {
                // Παίρνουμε αυτόματα το course_id του καθηγητή
                String courseId = professorsDAO.getCourseIdByProfessor(username);
                
                if (courseId == null) {
                    request.setAttribute("errorMessage", "Δεν έχετε ανατεθεί σε κανένα μάθημα.");
                    request.getRequestDispatcher("/views/dashboardprof.jsp").forward(request, response);
                    return;
                }

                int registrationNumber = Integer.parseInt(regNumStr);
                float grade = Float.parseFloat(gradeStr);

                professorsDAO.setGrades(registrationNumber, courseId, grade);

                request.setAttribute("successMessage", "Ο βαθμός καταχωρήθηκε επιτυχώς!");
                request.getRequestDispatcher("/views/dashboardprof.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Μη έγκυρη μορφή αριθμού μητρώου ή βαθμού.");
                request.getRequestDispatcher("/views/dashboardprof.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Σφάλμα κατά την καταχώρηση: " + e.getMessage());
                request.getRequestDispatcher("/views/dashboardprof.jsp").forward(request, response);
            }
            return;
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String currentprof = null;
        String currentspec = null; 
        HttpSession session = request.getSession(false);
        
        
        if (session != null && session.getAttribute("username") != null) {
            currentprof = (String) session.getAttribute("username");
            currentspec = (String) session.getAttribute("ProfessorSpecialty"); 
        }
        
        
        if (currentprof == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("savedProfessorUsername")) {
                        currentprof = c.getValue();
                    }
                    if (c.getName().equals("savedProfessorSpecialty")) {
                        try {
                            currentspec = URLDecoder.decode(c.getValue(), StandardCharsets.UTF_8);
                        } catch (Exception e) {
                            currentspec = c.getValue(); 
                        }
                    }
                }
                
                if (currentprof != null) {
                    session = request.getSession(true);
                    session.setAttribute("username", currentprof);
                    session.setAttribute("ProfessorSpecialty", currentspec); 
                }
            }
        }
        
        
        if (currentprof == null) {
            response.sendRedirect(request.getContextPath() + "/views/loginstud.jsp");
            return;
        }
        
        
        String courseName = request.getParameter("courseName");
     
        if (courseName == null) {
            request.getRequestDispatcher("/views/dashboardprof.jsp").forward(request, response);
            return;
        }

       
        courseName = courseName.trim();
        if (currentspec != null)
            currentspec = currentspec.trim();

        if (currentspec == null || !currentspec.equalsIgnoreCase(courseName)) {
            request.setAttribute("errorMessage", "Δεν έχετε δικαίωμα να δείτε τους βαθμούς αυτού του μαθήματος.");
            request.getRequestDispatcher("/views/dashboardprof.jsp").forward(request, response);
            return;
        }
        
       
        try {
            List<Grades> grades = professorsDAO.viewCoursesGrades(courseName);
            request.setAttribute("grades", grades);
            request.getRequestDispatcher("/views/viewcoursesgrades.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Σφάλμα συστήματος: " + e.getMessage());
            request.getRequestDispatcher("/views/dashboardprof.jsp").forward(request, response);
        }
    }
}