/**
 * Κεντρική κλάση Users.
 * Αποτελεί την υπερκλάση για όλους τους χρήστες του συστήματος (Students,Professors,Secretaies).
 * Περιλαμβάνει τα βασικά στοιχεία(username,name,surname,department) και έναν μετρητή συνολικών χρηστών(usersCounter).
 */
package model;

public class Users {
	private String username;
	private String name;
	private String surname;
	private String department;
	private static int usersCounter = 0;
	
	public Users(String username , String name , String surname , String department){
		this.username=username;
		this.name=name;
		this.surname=surname;
		this.department=department;
	}
		
	
	public String getusername() {
		return  username;
		
	}
	public String getname() {
		return name;
		
	}
	public String getsurname() {
		return surname;
		
	}
	public String getDepartment() {
		return  department;
	}
	
	
	public void setusername(String username) {
		this.username=username;
	}
	public void setname(String name) {
		this.name=name;
	}
	public void setsurname(String surname) {
		this.surname=surname;
	}
	public void setdepartment(String department) {
		this.department=department;
	}


}
