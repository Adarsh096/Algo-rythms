package sedgwick.algorithm.book;

import java.util.Stack;

public class Problem_1_3_10 {
    //Infix to postfix converter
    public static void infixToPostfix(String infixExpr) {
        StringBuilder postfixExpr = new StringBuilder();
        Stack<String> opStack = new Stack<>();

        String[] splits = infixExpr.split(" ");
        for (int i = 0; i < splits.length; i++) {
            String token = splits[i];
            /*
                We will encounter four things:
                    Operator
                    Operands
                    Opening bracket
                    Closing bracket

                Stack: Handles the top part first.
                 So operators on the stack have to be maintained in higher(top) to lower(bottom) precedence.
             */
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                // Handle operators (+, -, *, /)
                while (!opStack.isEmpty() && hasHigherPrecedence(opStack.peek(), token)) {
                    //stack's entry has higher precedence so add to the expr str since it will be done first.
                    postfixExpr.append(opStack.pop()).append(" ");
                }
                opStack.push(token);
            } else if (token.equals("(")) {
                // Push left parentheses onto the operator stack
                opStack.push(token);
            } else if (token.equals(")")) {
                // Handle right parentheses
                //Pop until we reach the opening bracket matching for this closing bracket.
                while (!opStack.isEmpty() && !opStack.peek().equals("(")) {
                    postfixExpr.append(opStack.pop()).append(" ");
                }
                opStack.pop(); // Pop the left parenthesis
            } else {
                // Operand, append to postfix expression
                postfixExpr.append(token).append(" ");
            }
        }

        // Pop any remaining operators from the operator stack
        while (!opStack.isEmpty()) {
            postfixExpr.append(opStack.pop()).append(" ");
        }

        System.out.println("Postfix Expression: " + postfixExpr.toString().trim());
    }

    // Function to check operator precedence
    private static boolean hasHigherPrecedence(String op1, String op2) {
        int precedence1 = getPrecedence(op1);
        int precedence2 = getPrecedence(op2);
        return precedence1 >= precedence2;
    }

    // Function to determine operator precedence
    private static int getPrecedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0; // Default precedence for other characters (operands, parentheses)
        }
    }

    public static void main(String[] args) {
        String infixExpression = "( 1 + 2 ) * ( 3 - 4 ) / 5";
        infixExpression = "( ( x * y ) - ( z / p ) )"; // Postfix Expression: x y * z p / -
        infixExpression = "a + ( b - c ) * d";
        infixExpression = "a - b * c / d * e + f";// without brackets. Postfix Expression: a b c * d / e * - f +
        infixToPostfix(infixExpression);
    }

}
