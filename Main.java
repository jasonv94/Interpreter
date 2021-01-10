import java.io.IOException;
import java.util.Scanner;

public class Main {

	static Scanner read = new Scanner(System.in);
	
	
	
/***
 * 
 * Main for reading the program.
 * File is used by run configurations-src/file.txt
 * @param args
 * @throws NumberFormatException
 * @throws IOException
 * @throws SyntaxError 
 */
public static void main(String args[]) throws NumberFormatException, IOException, SyntaxError {
	
	
	String file = "file.txt";

	//using command line String file=args[0];
	Espresso input= new Espresso();
	input.fileRead(file);
	
	
	
	
}
}
