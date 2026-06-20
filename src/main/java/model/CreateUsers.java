package model;

import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;


public class CreateUsers {
	public static void main(String[] args) throws IOException {
		Users U1 = new Users("user01", "Γιάννης", "Παπαδόπουλος", "Πληροφορικής");
		Professors P1 = new Professors("user22" , "Μιχάλης" , "Ανδρέου" , "Λογιστική" , "Εργαστηρίου" );
		Secretaries G1 = new Secretaries ("user31" , "Γεωργία" , "Αντωνίου" , "Πληροφορικής" , "2107894444" , "2341");
		
		boolean isAmValid=false;
		Scanner sc = new Scanner(System.in);
		
		
		try {
			System.out.println("Δώσε το username του φοιτητή");
			String typedusername=sc.nextLine();
			
			System.out.println("Δώσε το όνομα του φοιτητή");
			String typedname=sc.nextLine();
			
			System.out.println("Δώσε το επίθετο του φοιτητή");
			String typedsurname=sc.nextLine();
			
			System.out.println("Δώσε τη σχολή του φοιτητή");
			String typeddepartment=sc.nextLine();
			
			if(typedusername.isEmpty() || typedname.isEmpty() || typedsurname.isEmpty() || typeddepartment.isEmpty()) {
				throw new Exception("Λάθος, κανένα από αυτά τα πεδία δεν μπορεί να είναι άδειο");
			}
			
			System.out.println("Δώσε το ΑΜ του φοιτητή");
			int typedregistrationNumber = 0; 
			
		
			while(!isAmValid) {
				try { 
					if(!sc.hasNextInt()) {
						sc.next(); 
						throw new InvalidRegistrationNumberException("Το ΑΜ πρέπει να είναι αριθμοί");
					}
					
					typedregistrationNumber = sc.nextInt();
					isAmValid = true;
					
				} catch (InvalidRegistrationNumberException e) {
					System.out.println("\nΠΡΟΒΛΗΜΑ ΜΗΤΡΩΟΥ: " + e.getMessage());
					System.out.println("Δώσε ΞΑΝΑ το ΑΜ του φοιτητή:");
				}
			} 
			
			
			
			Students S1=new Students(typedusername , typedname , typedsurname , typeddepartment , typedregistrationNumber);
			
			System.out.println("\nΤα στοιχεία δημιουργήθηκαν και αποθηκεύτηκαν επιτυχώς\n");
			
			System.out.println("Στοιχεία Χρήστη: ");
			System.out.println("Username: "+ U1.getusername() +"  Όνομα" +U1.getname() + "  Επίθετο: " + U1.getsurname() + "  Τμήμα "+U1.getDepartment() );
			
			System.out.println("Στοιχεία μαθητή");
			System.out.println("Username: " + S1.getusername()+"  Όνομα "+S1.getname()+"  Επίθετο: "+S1.getsurname()+"  Τμήμα: "+S1.getDepartment()+"  ΑΜ: "+S1.getregistrationNumber());
			
			System.out.println("Στοιχεία καθηγητή: ");
			System.out.println("Username: " + P1.getusername()+"  Όνομα "+P1.getname()+"  Επίθετο: "+P1.getsurname()+"  Τμήμα: "+P1.getDepartment()+ "  Ειδικότητα "+P1.getSpecialty());
			
			System.out.println("Στοιχεία γραμματείας:");
			System.out.println("Username: " + G1.getusername()+"  Όνομα "+G1.getname()+"  Επίθετο: "+G1.getsurname()+"  Τμήμα: "+G1.getDepartment()+ "  Τηλέφωνο γραφείου "+G1.getofficePhoneNumber());
			 
		} 
		catch (Exception e) {
			
			System.out.println("\nΓΕΝΙΚΟ ΣΦΑΛΜΑ: " + e.getMessage()); 
		}
		try {
		Path filePath = Paths.get("students.txt");
		List<String>txtlines = Files.readAllLines(filePath);
		for(String line : txtlines) {
			try {
				String data[]=line.split(",");
				String fileUsername = data[0];
				String fileName = data[1];
				String fileSurname = data[2];
				String fileDepartment = data[3];
				int fileAM = Integer.parseInt(data[4]);
				
				Students fileStudents = new Students(fileUsername , fileName , fileSurname , fileDepartment , fileAM);
				
				System.out.println("Ο χρήστης με ΑΜ: " + fileAM + " καταχωρήθηκε επιτυχώς");
			}
			
			catch(Exception e) {
				System.out.println("Σφάλμα τύπου δεδομένων");
			} }
		
		}catch(IOException e) {
			System.out.println("Σφάλμα αναγνώρισης αρχείου");
		} 
		
		sc.close();} }
		
		
		
		
	



