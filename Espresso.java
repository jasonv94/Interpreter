import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 
 * 
 * Main file that acts as the interpreter
 * 
 */
public class Espresso {

	
	public  String equal_output;
	public  String print_output;
	public  String print_output_final;
	/**
	 * 
	 * @param value
	 * @return
	 */
	public  boolean isNumeric(String value) {
		if(value.matches("[0-9]+")) {
			return true;
		}else {
			return false;
		}
	}
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public void fileRead(String message) throws NumberFormatException, IOException, SyntaxError {
			SyntaxError exc=new SyntaxError();
			Evaluation evaluate = new Evaluation();
			evaluate.initialize();
			Scanner sc = new Scanner(new File(message));
			int lineNo=0;
			while(sc.hasNext()) {
			++lineNo;
			String readLine=sc.nextLine().replaceAll(" ", "");
			
			if(readLine.contains("read")) {
				System.out.println("Enter a value for "+readLine.charAt(4));
				int value = 0;
				try {
					value=Integer.valueOf(reader.readLine());
				}catch(Exception e) {
					System.out.println("Value must be numeric");
					System.exit(0);
				}
			    
				
				
				
				evaluate.variable_table[readLine.charAt(4)].setValue(value);
			}else if(readLine.contains("=")) {
				if(readLine.charAt(1)!='='||Character.isDigit(readLine.charAt(0))) {
					System.out.println(readLine);///ensure only single variable to take values
					System.out.println("ERROR ONLY SINGLE VARIABLE ALLOWED");
					System.exit(0);
				}
				
				equal_output="";
				
				//start at index 2 as two account for space for first value and equals sign
				
				if(isNumeric(readLine)) {/////if only numeric values i.e x=2+2
					for(int j=2;j<readLine.length();j++) {
					equal_output+=readLine.charAt(j);
					}
					int another=Integer.parseInt(equal_output);
					evaluate.variable_table[readLine.charAt(0)].setValue(another);
				}else {
				
				

				for(int i=2;i<readLine.length();i++) {
					if(Character.isAlphabetic(readLine.charAt(i))){
					
					try {
						int conv = evaluate.variable_table[readLine.charAt(i)].getValue();
						equal_output+=Integer.toString(conv);
					} catch (UndefinedVariableException e) {
						System.out.println("Error at line "+lineNo+" "+" "+readLine+" "+readLine.charAt(i)+" "+e.getMessage());
						System.exit(0);
					}
					
				}else{
					
					equal_output+=readLine.charAt(i);
				}
			}	
			 evaluate.variable_table[readLine.charAt(0)].setValue(evaluate.evaluatePostfix(evaluate.infixToPostfix(equal_output)));
				} 
			}else if(readLine.contains("print")) {
				print_output="";
				//start at index 5 to account for the letters of print values are 

				for(int i=5;i<readLine.length();i++) {
				print_output+=readLine.charAt(i);
			    
			    }
				print_output_final=evaluate.infixToPostfix(print_output);
				int final_result=evaluate.evaluatePostfix(print_output_final);
				if(final_result==Integer.MIN_VALUE) {
					System.out.println(exc.getMessage()+ " "+readLine+" on line "+lineNo);
					System.exit(0);
				}
				System.out.println(final_result);
					
			}else {
				
				System.out.println(lineNo+ " "+ exc.getMessage()+" "+readLine+" is not a valid parameter");
			
			}
			
			
			
			}

	
	
			
		}
	
}
