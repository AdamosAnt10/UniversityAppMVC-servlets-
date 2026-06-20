package model;

public class Grades {
	private String course;
	private double grade;
	private int registrationNumber;
	private String studentName;
	private String studentSurname;
	
	public Grades(String course , double grade , int registrationNumber,String studentName,String studentSurname) {
		this.course=course;
		this.grade=grade;
		this.registrationNumber=registrationNumber;
		this.studentName=studentName;
		this.studentSurname=studentSurname;
	}
	
	public void setcourse(String course) {
		this.course=course;
	}
	public void setgrade(double grade) {
		this.grade=grade;
	}
	public void setregistrationNumber(int registrationNumber) {
		this.registrationNumber=registrationNumber;
	}
	public void setStudentName(String studentName) {
		this.studentName=studentName;
	}
	public void setStudentSurname(String studentSurname) {
		this.studentSurname=studentSurname;
	}
	
	
	public String getcourse() {
		return course;
	}
	public double getgrade() {
		return grade;
	}
	public int getregistrationNumber() {
		return registrationNumber;
	}
	public String getstudentName() {
		return studentName;
	}
	public String getstudentSurname() {
		return studentSurname;
	}
	
}
