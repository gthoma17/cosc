import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
public class main
{
    public static int evaluate(String input){
        int leftOperand, rightOperand, result;
        char operator;
        String token;
        String reversedInput = "";
        
        //make integer stacks
        Stack<Integer> stack = new Stack<Integer>();

        
        //make string tokenizer
        StringTokenizer Ftokenizer = new StringTokenizer(input);
        
        //while there are more tokens
        while(Ftokenizer.hasMoreTokens()){
            reversedInput = Ftokenizer.nextToken() + " " + reversedInput;
        }
        StringTokenizer Rtokenizer = new StringTokenizer(reversedInput);
        
        while(Rtokenizer.hasMoreTokens()){
            token = Rtokenizer.nextToken();
            if(isNumber(token)){ //push numbers to the stack
                stack.push(Integer.parseInt(token));

            }
            else{ //perform operations when you encounter an operand
                operator = token.charAt(0);
                rightOperand = stack.pop();
                leftOperand = stack.pop();
                stack.push(evaluate(operator, leftOperand, rightOperand)); //push the result
            }
        }
        return stack.pop();
    }
    
    private static boolean isNumber(String token){
        char first = token.charAt(0);
        if(Character.isDigit(first))
            return true;
        else
            return false;   
    }
    
    private static int evaluate(char operator, int leftOp, int rightOp){
        int result;
        if (operator == '+')
            result = leftOp + rightOp;
        else if (operator == '-')
            result = leftOp - rightOp;
        else if (operator == '*')
            result = leftOp * rightOp;
        else if (operator == '%')
            result = leftOp % rightOp;
        else
            result = leftOp / rightOp;
        return result;
    }
    
    private static void displayMenu(){
        System.out.println("Enter a prefix equation to have it solved \n" +
                           "Enter 'q' to quit\n");
    }
    
    public static void main(String [ ] args){
        Scanner keyboard = new Scanner(System.in);    
        displayMenu();
        String option = keyboard.nextLine();
        
        while(!option.contains("q")){
            System.out.println(evaluate(option));
            displayMenu();
            option = keyboard.nextLine();
            
        }
            
    }
}

