import java.util.Scanner;

public class Main
{  
    private static void displayMenu(){
        System.out.println("Press enter to input a board to have evaluated \n" +
                           "Enter 'q' to quit\n");
    }
    
    public static void main(String [ ] args){
        Scanner keyboard = new Scanner(System.in);  
        int rows;
        int cols;
        int startRow;
        int startCol;
        displayMenu();
        String option = keyboard.nextLine();
        
        while(!option.contains("q")){
            System.out.println("Enter the number of rows the board has: ");
            rows = Integer.valueOf(keyboard.nextLine());
            System.out.println("Enter the number of columns the board has: ");
            cols = Integer.valueOf(keyboard.nextLine());
            System.out.println("Enter the starting row: ");
            startRow = Integer.valueOf(keyboard.nextLine());
            System.out.println("Enter the starting column: ");
            startCol = Integer.valueOf(keyboard.nextLine());
            
            knightSolver ks = new knightSolver(rows, cols, startRow, startCol);
            ks.solve();
            
            displayMenu();
            option = keyboard.nextLine();
        }   
    }
}