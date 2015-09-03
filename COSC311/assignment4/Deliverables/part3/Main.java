import java.util.Scanner;

public class Main
{  
    private static void displayMenu(){
        System.out.println("Press enter to solve a two-tone towers of hanoi problem \n" +
                           "Enter 'q' to quit\n");
    }
    
    public static void main(String [ ] args){
        Scanner keyboard = new Scanner(System.in);  
        int disks;
        displayMenu();
        String option = keyboard.nextLine();
        
        while(!option.contains("q")){
            System.out.println("Enter the number of disks there are of each color: ");
            disks = Integer.valueOf(keyboard.nextLine());
            
            hanoiSolver hs = new hanoiSolver(disks);
            
            displayMenu();
            option = keyboard.nextLine();
        }   
    }
}