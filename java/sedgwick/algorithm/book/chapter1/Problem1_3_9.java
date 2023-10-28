package sedgwick.algorithm.book.chapter1;

import java.util.Scanner;
import java.util.Stack;

public class Problem1_3_9 {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter expression without left parentheses: ");
//        String input = scanner.nextLine();
        String input = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        String result = insertParentheses(input);
        System.out.println("Got result : "+result);
    }
    public static String insertParentheses(String expression) {
        Stack<String> stack = new Stack<>();
        expression = expression.replace(" ","");
        for (char c : expression.toCharArray()) {
            if (c == ')') {
                // pop two operands and one operator
                StringBuilder result = new StringBuilder();
                String rightOperand = stack.pop();
                String operator = stack.pop();
                String leftOperand = stack.pop();
                result.append("(").append(leftOperand).append(operator).append(rightOperand).append(")");
                stack.push(result.toString());//treat as a single entry.
            } else if(c=='+' || c=='-'||c=='*'||c=='/') {
                stack.push(String.valueOf(c));
            } else if (c=='(') {

            }else{
                stack.push(String.valueOf(c));
            }
        }

        // The top of the stack should contain the fully parenthesized expression
        return stack.pop();
    }
}
