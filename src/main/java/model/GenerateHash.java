package model;
import org.mindrot.jbcrypt.BCrypt;

public class GenerateHash {
    public static void main(String[] args) {
        // Βάλε όσα passwords θέλεις
        String[] passwords = {"pass123" ,"pass123"};
        
        for (String p : passwords) {
            System.out.println(p + " --> " + BCrypt.hashpw(p, BCrypt.gensalt()));
        }
    }
}