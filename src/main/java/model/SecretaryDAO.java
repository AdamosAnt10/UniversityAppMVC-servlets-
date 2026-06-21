package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class SecretaryDAO {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/mydb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "postgres";
    
    private static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
    
    // 1. AUTHENTICATE
    public static Secretaries authenticate(String username, String password) throws Exception {
        String sql = "SELECT * FROM secretaries WHERE username=?";
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, username);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String hashFromDB = rs.getString("password");
                    
                    if (BCrypt.checkpw(password, hashFromDB)) {
                        return new Secretaries(
                                username,
                                rs.getString("name"),
                                rs.getString("surname"),
                                "Γραμματεία",
                                rs.getString("office_phone_number"),
                                ""
                        );
                    }
                }
            }
        }
        return null;
    }
    
    // 2. VIEW ALL COURSES WITH TEACHERS
    public List<Professors> viewAllCoursesWithTeachers() throws Exception {
        List<Professors> list = new ArrayList<>();
        
        String sql = "SELECT c.course_id, c.coursename, p.name, p.surname " +
                     "FROM courses c " +
                     "LEFT JOIN professors p ON c.professorsusername = p.username";
                     
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                String profName = rs.getString("name") != null ? rs.getString("name") : "Δεν ορίστηκε";
                String profSurname = rs.getString("surname") != null ? rs.getString("surname") : "";
                
                Professors prof = new Professors(
                        null,
                        profName,
                        profSurname,
                        rs.getString("course_id"),
                        rs.getString("coursename")
                );
                list.add(prof);
            }
        }
        return list;
    }
    
    // 3. CREATE COURSE
    public void createCourse(String courseID, String courseName, String professorUsername) throws Exception {
        String sql = "INSERT INTO courses (course_id, coursename, professorsusername) VALUES (?, ?, ?)";
        
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, courseID);
            pstmt.setString(2, courseName);
            pstmt.setString(3, professorUsername);
            
            pstmt.executeUpdate();
        }
    }
 // CHECK IF USERNAME EXISTS (σε όλους τους πίνακες χρηστών)
    public boolean usernameExists(String username) throws Exception {
        String sql = "SELECT username FROM professors WHERE username=? " +
                     "UNION SELECT username FROM students WHERE username=? " +
                     "UNION SELECT username FROM secretaries WHERE username=?";
        
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, username);
            pstmt.setString(3, username);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
    // 4. CREATE PROFESSOR
    public void createProfessor(String username, String name, String surname, 
                                 String department, String specialty, String password) throws Exception {
    	if (usernameExists(username)) {
    	    throw new Exception("Το username '" + username + "' υπάρχει ήδη.");
    	}
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        
        String sql = "INSERT INTO professors (username, name, surname, department, specialty, password) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, name);
            pstmt.setString(3, surname);
            pstmt.setString(4, department);
            pstmt.setString(5, specialty);
            pstmt.setString(6, hashedPassword);
            
            pstmt.executeUpdate();
        }
    }
    
    // 5. CREATE STUDENT
    public void createStudent(String username, String name, String surname, 
                               String department, int registrationNumber, String password) throws Exception {
    	 if (usernameExists(username)) {
    	        throw new Exception("Το username '" + username + "' υπάρχει ήδη.");
    	    }
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        
        String sql = "INSERT INTO students (registration_number, username, name, surname, department, password) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setInt(1, registrationNumber);
            pstmt.setString(2, username);
            pstmt.setString(3, name);
            pstmt.setString(4, surname);
            pstmt.setString(5, department);
            pstmt.setString(6, hashedPassword);
            
            pstmt.executeUpdate();
        }
    }
    
    // 6. ASSIGN PROFESSOR TO COURSE
    public void assignProfessorToCourse(String courseID, String professorUsername) throws Exception {
        String sql = "UPDATE courses SET professorsusername=? WHERE course_id=?";
        
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, professorUsername);
            pstmt.setString(2, courseID);
            
            pstmt.executeUpdate();
        }
    }
}