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
	public  String print_output2;
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
	
	//read through file replace all spaces evaluate statements on lines
	public void fileRead(String message) throws NumberFormatException, IOException, SyntaxError {
			SyntaxError exc=new SyntaxError();
			Evaluation evaluate = new Evaluation();
			evaluate.initialize();
			Scanner sc = new Scanner(new File(message));
			int lineNo=0;
			while(sc.hasNext()) {
			++lineNo;
			String re=sc.nextLine().replaceAll(" ", "");
			
			if(re.contains("read")) {
				System.out.println("Enter a value for "+re.charAt(4));
				int value = 0;
				try {
					value=Integer.valueOf(reader.readLine());
				}catch(Exception e) {
					System.out.println("Value must be numeric");
					System.exit(0);
				}
				
				
				evaluate.variable_table[re.charAt(4)].setValue(value);
			}else if(re.contains("=")) {
				if(re.charAt(1)!='='||Character.isDigit(re.charAt(0))) {
					System.out.println(re);///ensure only single variable to take values
					System.out.println("ERROR ONLY SINGLE VARIABLE ALLOWED");
					System.exit(0);
				}
				
				equal_output="";
				if(isNumeric(re)) {/////if only numeric values i.e x=2+2
					for(int j=2;j<re.length();j++) {
					equal_output+=re.charAt(j);
					}
					int another=Integer.parseInt(equal_output);
					evaluate.variable_table[re.charAt(0)].setValue(another);
				}else {
				//start at index 2 as two account for space for first value and equals sign
				for(int i=2;i<re.length();i++) {
					if(Character.isAlphabetic(re.charAt(i))){
					
					try {
						int conv = evaluate.variable_table[re.charAt(i)].getValue();
						equal_output+=Integer.toString(conv);
					} catch (UndefinedVariableException e) {
						System.out.println("Error at line "+lineNo+" "+" "+re+" "+re.charAt(i)+" "+e.getMessage());
						System.exit(0);
					}
					
				}else{
					
					equal_output+=re.charAt(i);
				}
			}	
			 evaluate.variable_table[re.charAt(0)].setValue(evaluate.evaluatePostfix(evaluate.infixToPostfix(equal_output)));
				} 
			}else if(re.contains("print")) {
				print_output="";
				//start at index 5 to account for the letters of print values are 
				for(int i=5;i<re.length();i++) {
				print_output+=re.charAt(i);
			    
			    }
				print_output2=evaluate.infixToPostfix(print_output);
				int final_result=evaluate.evaluatePostfix(print_output2);
				if(final_result==Integer.MIN_VALUE) {
					System.out.println(exc.getMessage()+ " "+re+" on line "+lineNo);
					System.exit(0);
				}
				System.out.println(final_result);
					
			}else {
				
				System.out.println(lineNo+ " "+ exc.getMessage()+" "+re+" is not a valid parameter");
			
			}
			
			
			
			}

	
	
			
		}
}
