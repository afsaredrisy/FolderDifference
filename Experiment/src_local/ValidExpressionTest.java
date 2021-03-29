package co.zoho.test;

import java.util.Stack;

public class ValidExpressionTest {

	
	public static boolean isExpressionValid(String str) {
		Stack<Character> s = new Stack<>();
		
		for(int i=0;i<str.length();i++) {
				char c = str.charAt(i);
				
				if(c=='{' || c=='(' || c=='[') {
					s.push(c);
				}
				else if(c == '}' || c==']' || c==')') {
					char open = s.pop();
					if(!isVlaidParanthesis(open,c)) {
						
						return false;
					}
				}
				else {
					return false;
				}
				
		}
		return true;
		
		
	}
	
	private static boolean isVlaidParanthesis(char open, char close) {
		return (open == '(' && close == ')') || (open=='{' && close == '}') || (open == '[' && close ==']');
	}
	
	public static void main(String[] args) {
		String str = "([])";
		if(isExpressionValid(str)) {
			System.out.println("First Input is valid expression");
		}
		else {
			System.out.println("First Input is not valid expression");
		}
		
		
		String str2 = "{()]";
		if(isExpressionValid(str2)) {
			System.out.println("Second Input is valid expression");
		}
		else {
			System.out.println("Second Input is not valid expression");
		}

	}

}

/*
 * 
 * TIme Complexity : O(n)
 * Space Complexit : O(n)
 */
