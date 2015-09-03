import java.util.Scanner;

public class Main
{  
    private static void displayMenu(){
        System.out.println("Enter the path to maze file to have it evaluated \n" +
                           "Enter 'q' to quit\n");
    }
    
    public static void main(String [ ] args){
        Scanner keyboard = new Scanner(System.in);   
        displayMenu();
        String option = keyboard.nextLine();
        
        while(!option.contains("q")){
            maze Maze = new maze(option);
            Maze.solve();
            displayMenu();
            option = keyboard.nextLine();
        }
            
    }
}
