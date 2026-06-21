package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class ProfessorsDAO {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/mydb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "postgres";

    private Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

   
    public Professors authenticate(String username, String password) throws Exception {
        String sql = "SELECT * FROM professors WHERE username=?";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String hashFromDB = rs.getString("password");

                    if (BCrypt.checkpw(password, hashFromDB)) {
                        return new Professors(
                            username,
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("department"),
                            rs.getString("specialty")
                        );
                    }
                }
            }
        }
        return null;
    }

   
    public List<Grades> viewCoursesGrades(String courseName) throws Exception {
        List<Grades> grades = new ArrayList<>();

        String sql = "SELECT c.course_id, s.registration_number, s.name AS studentName, " +
                     "s.surname AS studentSurname, g.grade " +
                     "FROM students s " +
                     "JOIN grades g ON s.registration_number = g.registration_number " +
                     "JOIN courses c ON g.course_id = c.course_id " +
                     "WHERE c.coursename = ?";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, courseName);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    grades.add(new Grades(
                        rs.getString("course_id"),
                        rs.getDouble("grade"),
                        rs.getInt("registration_number"),
                        rs.getString("studentName"),
                        rs.getString("studentSurname")
                    ));
                }
            }
        }
        return grades;
    }

    
    public void setGrades(int registration_number, String course_id, float grade) throws Exception {
        String sql = "INSERT INTO grades(registration_number, course_id, grade) VALUES(?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, registration_number);
            pstmt.setString(2, course_id);
            pstmt.setFloat(3, grade);

            pstmt.executeUpdate();
        }
    }public String getCourseIdByProfessor(String username) throws Exception {
        String sql = "SELECT course_id FROM courses WHERE professorsusername = ?";
        
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("course_id");
                }
            }
        }
        return null;
    }
}