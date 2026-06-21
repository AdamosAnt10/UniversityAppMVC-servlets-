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
import model.StudentDAO;
import model.Students;
import model.ProfessorsDAO;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SecretaryDAO secretaryDAO = new SecretaryDAO();
    private StudentDAO studentDAO = new StudentDAO();
    private ProfessorsDAO professorsDAO = new ProfessorsDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        // ελεγχος αν είναι γραμματεία
        try {
            Secretaries secretary = secretaryDAO.authenticate(username, password);
            if (secretary != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("SecretaryName", secretary.getname());
                session.setAttribute("SecretarySurname", secretary.getsurname());
                session.setAttribute("role", "secretary");

                if (rememberMe != null) {
                    Cookie cookie = new Cookie("SecretaryUsername", username);
                    cookie.setMaxAge(12 * 60 * 60);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }

                response.sendRedirect(request.getContextPath() + "/SecretaryServlet");
                return;
            }
        } catch (Exception e) {
            
        }

        // ελεγχος αν ειναι καθηγητης
        try {
            Professors professor = professorsDAO.authenticate(username, password);
            if (professor != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("ProfessorName", professor.getname());
                session.setAttribute("ProfessorSurname", professor.getsurname());
                session.setAttribute("ProfessorDepartment", professor.getDepartment());
                session.setAttribute("ProfessorSpecialty", professor.getSpecialty());
                session.setAttribute("role", "professor");

                if (rememberMe != null) {
                    Cookie cookie = new Cookie("savedProfessorUsername", username);
                    cookie.setMaxAge(12 * 60 * 60);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }

                response.sendRedirect(request.getContextPath() + "/views/dashboardprof.jsp");
                return;
            }
        } catch (Exception e) {
           
        }

        // ελεγχος αν ειναι φοιτητης
        try {
            Students student = studentDAO.authenticate(username, password);
            if (student != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("StudentAM", student.getregistrationNumber());
                session.setAttribute("StudentName", student.getname());
                session.setAttribute("StudentSurname", student.getsurname());
                session.setAttribute("StudentDepartment", student.getDepartment());
                session.setAttribute("role", "student");

                if (rememberMe != null) {
                    Cookie cookie = new Cookie("savedStudentAM", 
                                   String.valueOf(student.getregistrationNumber()));
                    cookie.setMaxAge(12 * 60 * 60);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }

                response.sendRedirect(request.getContextPath() + "/views/dashboard.jsp");
                return;
            }
        } catch (Exception e) {
            
        }

        // 4. ΑΝ ΔΕΝ ΒΡΕΘΗΚΕ ΠΟΥΘΕΝΑ
        request.setAttribute("errorMessage", "Λάθος username ή password.");
        request.getRequestDispatcher("/views/loginstud.jsp").forward(request, response);
    }
}