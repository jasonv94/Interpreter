import java.util.Stack;

public class Evaluation {
	
	/***
	 * 
	 * create the table that will be used to store values for
	 * variable characters
	 * 
	 * 
	 */
	public Variable [] variable_table;
	public void initialize() {
		variable_table= new Variable['z'+1];
		for(int i=0;i<='z';i++) {
			variable_table[i]=new Variable();
		}
	}
/***
 * 
 * Helper function used to evaluate precedence for
 * converting to postfix takes in operator parameter
 * 
 * 
 */
	 public int precedence(char operator) 
	    { 
	        switch (operator) 
	        { 
	        case '+': 
	        case '-': 
	        return 1; 
	       
	        case '*': 
	        case '/':
	        return 2; 

	        } 
	        
	        return Integer.MIN_VALUE;	    
	        
	    } 

	/***
	 * used below for reference in creating algorithm
	 * https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
	 * 
	 * 
	 * 
	 * @param infix
	 * @return result into postfix expression
	 */
     public String infixToPostfix(String infix) {

        String result = "";
        Stack<Character> pfix = new Stack<Character>();
        for(int i=0;i<infix.length();i++) {
        	char value=infix.charAt(i);
        	if(Character.isLetterOrDigit(value)) {
        		result+=value;
       
        	}else if(value=='(') {
        		pfix.push(value);
     
        	}
        	else if(value==')') {
        		while(pfix.peek()!='(') {
        			result+=pfix.pop();
        		}
        		pfix.pop();
        	}else {
        		while(!pfix.isEmpty()&& precedence(value) <= precedence(pfix.peek())&& (pfix.peek()!='(')) {
        			result+=pfix.pop();
        		}
        		pfix.push(value);
        		
        	}
        }  while (!pfix.isEmpty()) {
    		result+=pfix.pop();
    		}
    		return result;

        }
     
     public int operator(char a,int b,int c) {
    	 if(a=='+') {	
    	 return c+b;
    	 }else if(a=='-') {

    	 	return c-b;
    	 }
    	 else if(a=='*') {

    	 	return c*b;
    	 }
    	 else if(a=='/') {
    	 	return c/b;
    	 }
    	 else
    	 System.out.println();
    	 return Integer.MIN_VALUE;
    	 	
    	 }
     
     /***
      * evaluate postfix expressions takes in a result which is in postfix form
      * integer stack is used to ensure integer values are placed and not split
      *up into seperate digits i.e 10 would equal 1 0 if character
      * @param result value in postfix
      * @return
      */
      int evaluatePostfix(String result) {
    	Stack<Integer> table=new Stack<Integer>();
    	
    	int final_value;
    	for(int i=0;i<result.length();i++) {
    		char value=result.charAt(i);
    		  if(Character.isAlphabetic(value)) {
              	try {
					table.push(variable_table[value].getValue());
				} catch (UndefinedVariableException e) {
					e.getMessage();
				}
              	continue;
              }
              if(Character.isDigit(value)) {
              	int p=Character.getNumericValue(value);
              	table.push(p);
              }else {
    			 int op1=table.pop();
    			 
    			 if(table.isEmpty()) {
    				 return Integer.MIN_VALUE;
    			 }
    			
    			 int op2=table.pop();
    			 int res=operator(value,op1,op2);
    			 table.push(res);	
    		}	
    	}
    	 
    	 final_value=table.pop();
    	 return final_value;
    	 
    	 
     }



}
