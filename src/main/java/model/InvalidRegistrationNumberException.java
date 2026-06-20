/**
 * Custom Exception.
 * Χρησιμοποιείται για να εντοπίζει σφάλματα κατά την εισαγωγή του Αριθμού Μητρώου (π.χ. όταν δίνονται γράμματα αντί για αριθμοί).
 */

package model;

public class InvalidRegistrationNumberException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidRegistrationNumberException(String message) {
        super(message);

} }
