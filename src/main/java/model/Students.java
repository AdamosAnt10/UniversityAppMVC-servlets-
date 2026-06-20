
package model;



public class Students extends Users{
	private final int registrationNumber;
	
	public Students(String username , String name , String surname , String department , int registrationNumber) {
		super(username, name, surname, department);
		this.registrationNumber=registrationNumber;
		
	}
	
	public int getregistrationNumber() {
		return registrationNumber;
	}
	
	
		
		
	}
	


