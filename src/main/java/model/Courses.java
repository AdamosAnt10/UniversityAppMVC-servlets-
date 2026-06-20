package model;

public class Courses {
	private String courseId;
	private String courseName;
	
	public Courses(String courseId , String courseName) {
		this.courseId=courseId;
		this.courseName=courseName;
	}
	
	public void setcourseId(String courseId) {
		this.courseId=courseId; 
		}
	public void setcourseName(String courseName) {
		this.courseName=courseName;
	}
	
	
	public String getcourseId() {
		return courseId;
	}
	public String getcourseName() {
		return courseName;
	}
	

}
	
