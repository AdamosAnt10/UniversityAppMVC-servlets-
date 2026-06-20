package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class StudentDAO {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/mydb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "postgres";
    
    private Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
    
    // 1. AUTHENTICATE
    public Students authenticate(String username, String password) throws Exception {
        String sql = "SELECT * FROM students WHERE username=?";
        
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String hashFromDB = rs.getString("password");
                    
                    if (BCrypt.checkpw(password, hashFromDB)) {
                        return new Students(
                            username,
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("department"),
                            rs.getInt("registration_number")
                        );
                    }
                }
            }
        }
        return null;
    }
    
    // 2. GET GRADES BY AM
    public List<Grades> getGradesByAM(int registrationNumber) throws Exception {
        List<Grades> grades = new ArrayList<>();
        String sql = "SELECT g.*, s.name, s.surname FROM grades g " +
                "JOIN students s ON g.registration_number = s.registration_number " +
                "WHERE g.registration_number = ?";
        
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setInt(1, registrationNumber);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    grades.add(new Grades(
                        rs.getString("course_id"),
                        rs.getDouble("grade"),
                        registrationNumber,
                        rs.getString("name"),
                        rs.getString("surname")
                    ));
                }
            }
        }
        return grades;
    }
 // Βαθμολογία ανά εξάμηνο
    public List<Grades> getGradesBySemester(int registrationNumber, int semester) throws Exception {
        List<Grades> grades = new ArrayList<>();
        String sql = "SELECT g.*, s.name, s.surname FROM grades g " +
                     "JOIN students s ON g.registration_number = s.registration_number " +
                     "WHERE g.registration_number = ? AND g.semester = ?";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, registrationNumber);
            pstmt.setInt(2, semester);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    grades.add(new Grades(
                        rs.getString("course_id"),
                        rs.getDouble("grade"),
                        registrationNumber,
                        rs.getString("name"),
                        rs.getString("surname")
                    ));
                }
            }
        }
        return grades;
    }

    // Συνολικός μέσος όρος
    public double getAverageGrade(int registrationNumber) throws Exception {
        String sql = "SELECT AVG(grade) as average FROM grades WHERE registration_number = ?";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, registrationNumber);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("average");
                }
            }
        }
        return 0.0;
    }
}