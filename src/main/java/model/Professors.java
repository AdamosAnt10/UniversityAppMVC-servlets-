/**
 * Η κλάση Professors κληρονομείται από την Users.
 * Αναπαριστά τους καθηγητές του Πανεπιστημίου, με επιπλέον χαρακτηριστικό την ειδικότητά τους(specialty).
 */

package model;

import model.Courses;
import model.Users;

public class Professors extends Users {
	private String specialty;
	
	public Professors(String username, String name, String surname, String department, String specialty) {
        super(username, name, surname, department); 
        this.specialty = specialty;
    }
	public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    
    public void setGrades(Courses course) {
    	System.out.println("ΚΑΘΗΓΗΤΗΣ (" + this.getname() + "): Η βαθμολογία για το μάθημα " + course.getcourseName() + " περάστηκε επιτυχώς στο σύστημα.");
    	
    }
    public void viewCoursesGrades(Courses course) {
    	System.out.println("ΚΑΘΗΓΗΤΗΣ (" + this.getname() + "): Εμφάνιση βαθμολογιών για τους φοιτητές που παρακολουθούν το μάθημα " + course.getcourseName() + ".");
    }

}
