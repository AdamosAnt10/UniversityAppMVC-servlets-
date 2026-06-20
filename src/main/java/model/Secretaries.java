/**
 * Η κλάση Secretaries κληρονομείται από την Users.
 * Έχει το τηλέφωνο της γραμματείας(officePhoneNumber)
 * Έχει μεθόδους όπου κάνουν εγγραφή του φοιτητή, εγγραφή καθηγητή, δημιουργία μαθήματος και την λίστα με τους βαθμούς του κάθε φοιτητη. 
 */

package model;

import model.Courses;
import model.Professors;
import model.Students;
import model.Users;

public class Secretaries extends Users {
	private String officePhoneNumber;
	private String professorUsername;
	
	public Secretaries(String username , String name , String surname , String department , String officePhoneNumber , String professorUsername) {
		super(username , name , surname , department);
		this.officePhoneNumber=officePhoneNumber;
		this.professorUsername=professorUsername;
	}
	
	public void setofficePhoneNumber(String officePhoneNumber) {
		this.officePhoneNumber=officePhoneNumber;
	}
	public void setprofessorUsername(String professorUsername) {
		this.professorUsername=professorUsername;
	}
	
	public String getofficePhoneNumber() {
		return officePhoneNumber;
	}
	public String getprofessorUsername() {
		return professorUsername;
	}
	
	public void createStudentRecord(String username , String name , String surname , String department , int registrationNumber) {
		Students newStudent=new Students(username , name , surname , department , registrationNumber);
		System.out.println("ΓΡΑΜΜΑΤΕΙΑ: Η εγγραφή του φοιτητή "+newStudent.getname()+" δημιουργήθηκε επιτυχώς");
		}
	public void createProfessorsRecord(String username, String name, String surname, String department, String specialty) {
		Professors newProfessor=new Professors(username,name,surname,department,specialty);
		System.out.println("Γραμματεία: Η εγγραφή του καθηγητή "+newProfessor.getname()+ " δημιουργήθηκε επιτυχώς");
	}
	public void createCourses(String courseId , String courseName, String professorUsername) {
		Courses newCourse = new Courses(courseId,courseName);
		System.out.println("Το μάθημα "+newCourse.getcourseName()+" αποθηκεύθηκε επιτυχώς");
	}
	
	public void AssignProfessor(Professors prof,Courses course) {
		System.out.println("ΓΡΑΜΜΑΤΕΙΑ: Ο καθηγητής " + prof.getname() + " ανατέθηκε επιτυχώς στο μάθημα " + course.getcourseName() + ".");
		
	}
	public void createStudentGrading(Courses course) {
		System.out.println("ΓΡΑΜΜΑΤΕΙΑ: Η λίστα των φοιτητών για το μάθημα " + course.getcourseName() + " δημιουργήθηκε και είναι έτοιμη για βαθμολόγηση.");
	}
	
	
	
	
	
		
	}


