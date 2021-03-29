package co.zoho.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

class ExpressionUtility{
	public static int Prec(char ch) 
    { 
        switch (ch) 
        { 
        case '+': 
        case '-': 
            return 1; 
       
        case '*': 
        case '/': 
            return 2; 
       
        case '^': 
            return 3; 
        } 
        return -1; 
    } 
	
	public static String infixToPostfix(String exp) 
    { 
        
        String result = new String(""); 
          
        
        Stack<Character> stack = new Stack<>(); 
          
        for (int i = 0; i<exp.length(); ++i) 
        { 
            char c = exp.charAt(i); 
              
    
            if (Character.isLetterOrDigit(c)) 
                result += c; 

            else if (c == '(') 
                stack.push(c); 

            else if (c == ')') 
            { 
                while (!stack.isEmpty() &&  
                        stack.peek() != '(') 
                    result += stack.pop(); 
                  
                    stack.pop(); 
            } 
            else 
            { 
                while (!stack.isEmpty() && Prec(c)  
                         <= Prec(stack.peek())){ 
                    
                    result += stack.pop(); 
             } 
                stack.push(c); 
            } 
       
        } 
       
        while (!stack.isEmpty()){ 
            if(stack.peek() == '(') 
                return "Invalid Expression"; 
            result += stack.pop(); 
         } 
        return result; 
    } 
	
	
	public static int evaluatePostfix(String exp) 
    { //System.out.println(exp);
        Stack<Integer> stack=new Stack<>(); 
          
        for(int i=0;i<exp.length();i++) 
        { 
            char c=exp.charAt(i); 
              
            if(Character.isDigit(c)) 
            stack.push(c - '0'); 
              
            else
            { 
                int val1 = stack.pop(); 
                
                int val2 = stack.pop(); 
                  
                switch(c) 
                { 
                    case '+': 
                    stack.push(val2+val1); 
                    break; 
                      
                    case '-': 
                    stack.push(val2- val1); 
                    break; 
                      
                    case '/': 
                    stack.push(val2/val1); 
                    break; 
                      
                    case '*': 
                    stack.push(val2*val1); 
                    break; 
              } 
            } 
        } 
        return stack.pop();     
    } 

}


class ScriptingProcessor{
	
	Map<String,Integer> variables = new HashMap<>();
	
	public void execute(String statement) {
		if(statement.contains("=")) {
			String[] exprs = statement.split("=");
			int val = calculateExpr(exprs[1]);
			variables.put(exprs[0], val);
			System.out.println(val);
		}
		else if(statement.contains("Print(")) {
			String var = statement.charAt(6)+"";
			if(variables.containsKey(var)) {
				System.out.println(variables.get(var));
			}
			else {
				System.out.println("Variable "+var+" is undefined");
			}
		}
		else {
			System.out.println("Invalid command.");
		}
		
	}
	
	
	private int calculateExpr(String str) {
		String exp = replaceValriables(str.toCharArray());
		String postFix = ExpressionUtility.infixToPostfix(exp);
		int value = ExpressionUtility.evaluatePostfix(postFix);
		return value;
	}
	private  String replaceValriables(char [] expr) {
		
		for(int i=0;i<expr.length;i++) {
			if(isVariable(expr[i])) {
				
				int value = variables.getOrDefault(expr[i]+"", 0);
				
				expr[i] = (char)(value+'0');
				//System.out.println(expr[i]+" value "+value);
			}
		}
		return new String(expr);
		
		
	}
	
	private  boolean isVariable(char c) {
		return Character.isAlphabetic(c);
	}
	
	
}

public class ScriptLanguage {

	public static void main(String[] args) {
		ScriptingProcessor script = new ScriptingProcessor();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter exit to exit the application.");
		while(true) {
			String str = scanner.nextLine();
			
			if(str.equals("exit") || str.equals("Exit")) {
				break;
			}
			script.execute(str);
			
		}
		scanner.close();

	}

}
/*
Input:
 
Enter exit to exit the application.
a=3+4
7
b=a+(5*3)+2
24
Print(b)
24
Print(a)
7
Exit


Instructions : 
Enter above input line by line with enter/return and see output 
 
 
 */
