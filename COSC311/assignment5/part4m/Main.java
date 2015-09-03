import java.util.Scanner;
public class Main
{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        bst bs = null;
        int parm;
        
        displayMenu();
        int option = keyboard.nextInt();
        
        while(option != 13){
            if (option == 1){
                int[] in = new int[20];
                for(int i=0;i<20;i++){
                    System.out.println("Enter an integer");
                    in[i] = keyboard.nextInt();
                }
                
                bs = new bst(in);
            }
            else if (option == 2){System.out.println(bs.nodes());}
            else if (option == 3){bs.leaves();}
            else if (option == 4){System.out.println(bs.height());}
            else if (option == 5){System.out.println(bs.odd());}
            else if (option == 6){System.out.println(bs.zero());}
            else if (option == 7){bs.descend();}
            else if (option == 8){bs.ascend();}
            else if (option == 9){System.out.println(bs.minimum());}
            else if (option == 10){
                System.out.println("Enter an integer");
                parm = keyboard.nextInt();
                bs.greater(parm);
            }
            else if (option == 11){
                System.out.println("Enter an node's value");
                parm = keyboard.nextInt();
                System.out.println(bs.level(parm));
            }
            else if (option == 12){
                System.out.println("Enter an node's value");
                parm = keyboard.nextInt();
                bs.path(parm);
            }
            else{System.out.println("Invalid Option");}
            
            displayMenu();
            option = keyboard.nextInt();
        }
        
        
    }
    private static void displayMenu(){
        System.out.println("1  - Enter a new BST\n" +
                           "2  - Count nodes\n" +
                           "3  - Print leaves\n" +
                           "4  - Print height\n" +
                           "5  - Sum odd values\n" +
                           "6  - Check for zero\n" +
                           "7  - Print in descending order\n" + 
                           "8  - Print in ascending order\n" +
                           "9  - Print minimum value\n" +
                           "10 - Greater()\n" +
                           "11 - Find the level of a node\n" +
                           "12 - print the path to a node\n" +
                           "13 - Exit\n" +
                           "Choose an option - ");
    }
    
    
    private static String getParm(String parm){
        Scanner keyboard = new Scanner(System.in);
        String toReturn = "";
        if(parm == "path"){
            System.out.println("Enter the file path: ");
            toReturn = keyboard.nextLine();
        }
        else if (parm == "stuData"){
            System.out.println("Enter student id, name, age, and gpa seperated by space: ");
            toReturn = keyboard.nextLine();
        }
        else if (parm == "id"){
            System.out.println("Enter student ID #: ");
            toReturn = String.valueOf(keyboard.nextInt());
        }
        else{
            System.out.println("Weird Error, you better contact IT");
        }
        return toReturn;
    }
}
