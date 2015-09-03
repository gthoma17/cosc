import java.util.Scanner;

public class Main
{  
    private static void displayMenu(){
        System.out.println("Enter the path to a suduko puzzle to have it evaluated \n" +
                           "Enter 'q' to quit\n");
    }
    
    public static void main(String [ ] args){
        Scanner keyboard = new Scanner(System.in);  
        displayMenu();
        String option = keyboard.nextLine();
        
        while(!option.contains("q")){
            sudukoSolver ss = new sudukoSolver(option);
            ss.solve(0,0);
            
            displayMenu();
            option = keyboard.nextLine();
        }   
    }
}