import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.Stack;
import java.util.Scanner;

public class main
{
   public main(){
    }
   private class paran{
       char type;
       int line;
       int index;
       
       private paran(char type, int line, int index){
           this.type = type;
           this.line = line;
           this.index = index;
       }
       public String toString(){
           return type + " line # " + line + " char # " + index;
        }
   }
   
   public boolean isBalanced(String path){
       Stack<paran> stack = new Stack<paran>(); //_the_ stack
       char ch;
       int index = 0;
       int lineNum = 0;
       boolean balanced = true;
       paran popped;
       paran push;
       try{
            FileReader fileReader = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(fileReader);
            String line = null;
            while ((line = br.readLine()) != null) {
                index = 0;
                lineNum++;
                while(index < line.length()){
                    //get char
                    ch = line.charAt(index);
           
                    //push open parans
                    if (ch == '(' || ch == '[' || ch == '{' ){
                        push = new paran(ch,lineNum,index);
                        stack.push(push);
                    }
                    //pop/error check close parans
                    else if (ch == ')' || ch == ']' || ch == '}' ){
                        //error checking
                        if(stack.isEmpty()){
                            System.out.println("Lonely right paranthasis: " + ch + " line # " +
                                                lineNum + " char # " + index);
                            balanced = false;
                            break;
                        }
                        else if(!match(stack.pop(), ch, lineNum, index)){
                            balanced = false;
                            break;
                        }
                    }
                    else; //else we don't care
                    index++;
                }
                if(!balanced){
                    break;
                }
            }
        }catch (Exception e) {
            System.out.println(e.getClass());
        } 
        if(!stack.isEmpty() && balanced){
            balanced = false;
            popped = stack.pop();
            System.out.println("Lonely left paranthasis: " + popped.toString());
        }
       return (balanced);
   }
   
   private static boolean match(paran left, char right, int lineNum, int index){
       if((left.type == '(' && right == ')')
       ||(left.type == '[' && right == ']')
       ||(left.type == '{' && right == '}')){
            return true;
        }
       else{
            System.out.println("Mismatched paranthasis, left: " + left.toString() + 
                               " right: " + right + " line # " + lineNum + " char # " + index);
            return false; 
        }
   }
    
    private static void displayMenu(){
        System.out.println("Enter a the path to have a file evaluated \n" +
                           "Enter 'q' to quit\n");
    }
    
    public static void main(String [ ] args){
        Scanner keyboard = new Scanner(System.in);    
        displayMenu();
        String option = keyboard.nextLine();
        boolean answer;
        main bt = new main();
        
        while(!option.contains("q")){
            if(bt.isBalanced(option)){
                System.out.println("All paranthesis are balanced");
            }
            displayMenu();
            option = keyboard.nextLine();
            
        }
            
    }
}
