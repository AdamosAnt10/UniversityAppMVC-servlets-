package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Professors;
import model.Secretaries;
import model.SecretaryDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/SecretaryServlet")
public class SecretaryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private SecretaryDAO secretaryDAO = new SecretaryDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        
        
        if ("assignprofessor".equals(action)) {
            String courseID = request.getParameter("course_id");
            String profUsername = request.getParameter("professor_username");
            
            try {
                secretaryDAO.assignProfessorToCourse(courseID, profUsername);
                request.setAttribute("successMessage", "Ο καθηγητής ανατέθηκε επιτυχώς!");
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Σφάλμα κατά την ανάθεση: " + e.getMessage());
            }
            request.getRequestDispatcher("/views/dashboardsec.jsp").forward(request, response);
            return; 
        }
        
       
        if ("createCourse".equals(action)) {
            String courseID = request.getParameter("course_id");
            String courseName = request.getParameter("course_name");
            String profUsername = request.getParameter("professor_username");
            
            try {
                secretaryDAO.createCourse(courseID, courseName, profUsername);
                request.setAttribute("successMessage", "Το μάθημα δημιουργήθηκε επιτυχώς!");
            } catch(Exception e) {
                request.setAttribute("errorMessage", "Σφάλμα κατά τη δημιουργία: " + e.getMessage());
            }
            request.getRequestDispatcher("/views/dashboardsec.jsp").forward(request, response);
            return;
        }
        
        
        if ("createprofessor".equals(action)) {
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String department = request.getParameter("department");
            String specialty = request.getParameter("specialty");
            String password = request.getParameter("password"); 
            
            try {
                secretaryDAO.createProfessor(username, name, surname, department, specialty, password);
                request.setAttribute("successMessage", "Ο καθηγητής δημιουργήθηκε επιτυχώς!");
            } catch(Exception e) {
                request.setAttribute("errorMessage", "Σφάλμα κατά τη δημιουργία: " + e.getMessage());
            }
            request.getRequestDispatcher("/views/createprofessor.jsp").forward(request, response);
            return;
        }
        
       
        if ("createstudent".equals(action)) {
            String am = request.getParameter("registration_number");
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String department = request.getParameter("department");
            String password = request.getParameter("password");
            
            try {
                int registrationNumber = Integer.parseInt(am);
                secretaryDAO.createStudent(username, name, surname, department, registrationNumber, password);
                request.setAttribute("successMessage", "Ο μαθητής δημιουργήθηκε επιτυχώς!");
            } catch(Exception e) {
                request.setAttribute("errorMessage", "Σφάλμα κατά τη δημιουργία: " + e.getMessage());
            }
            request.getRequestDispatcher("/views/createstudent.jsp").forward(request, response);
            return;
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
      
        String currentsec = null;
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("username") != null) {
            currentsec = (String) session.getAttribute("username");
        }
        
        if (currentsec == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("SecretaryUsername")) {
                        currentsec = c.getValue();
                        session = request.getSession(true);
                        session.setAttribute("username", currentsec);
                    }
                }
            }
        }
        
        if (currentsec == null) {
            response.sendRedirect(request.getContextPath() + "/views/loginstud.jsp");
            return;
        }
        
        String action1 = request.getParameter("action");
        
       
        if ("viewcourses".equals(action1)) {
            try {
                List<Professors> coursesList = secretaryDAO.viewAllCoursesWithTeachers();
                request.setAttribute("coursesList", coursesList);
                request.getRequestDispatcher("/views/viewcourses.jsp").forward(request, response);
                return; // Σταματάει εδώ η doGet
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Σφάλμα κατά τη φόρτωση των μαθημάτων: " + e.getMessage());
                request.getRequestDispatcher("/views/dashboardsec.jsp").forward(request, response);
                return;
            }
        }
        
        
        if (session.getAttribute("successMessage") != null) {
            request.setAttribute("successMessage", session.getAttribute("successMessage"));
            session.removeAttribute("successMessage");
        }
        if (session.getAttribute("errorMessage") != null) {
            request.setAttribute("errorMessage", session.getAttribute("errorMessage"));
            session.removeAttribute("errorMessage");
        }

        request.getRequestDispatcher("/views/dashboardsec.jsp").forward(request, response);
    } 
}