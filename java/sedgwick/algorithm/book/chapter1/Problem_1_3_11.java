package sedgwick.algorithm.book.chapter1;

import java.util.Stack;

public class Problem_1_3_11 {
    public static void main(String[] args){
        String postfixExpr = "1 2 + 3 4 - * 5 /";// equal to : ( 1 + 2 ) * ( 3 - 4 ) / 5
        evaluatePostFix(postfixExpr);
    }

    public static void evaluatePostFix(String postfixExpr){
        // in a single pass over all elements of the expr
        // start processing from left to right on encountering the operators
        Stack<String> operandStack = new Stack<>();
        String[] splits = postfixExpr.split(" ");
        for(int i=0; i<splits.length; i++){
            String token = splits[i];

            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
                // pop two operands from the stack and calculate
                // push the result to the stack
                //MISTAKE: first pop will give right operand, not the left one.
                String result = performOperation(operandStack.pop(), token, operandStack.pop());
                operandStack.push(result);
            }else{
                // push operands on stack
                operandStack.push(token);
            }
        }
        // print the result:
        System.out.printf("Result: %.2f",Double.parseDouble(operandStack.pop()));//the last element remaining will be the result.
    }
    private static String performOperation(String right, String operator, String left){
        switch (operator){
            case "+":
                return  ""+(Double.parseDouble(left) + Double.parseDouble(right));
            case "-":
                return  ""+(Double.parseDouble(left) - Double.parseDouble(right));
            case "*":
                return  ""+(Double.parseDouble(left) * Double.parseDouble(right));
            case "/":
                return  ""+(Double.parseDouble(left) / Double.parseDouble(right));
            default:
                return "";
        }
    }
}
