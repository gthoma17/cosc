import java.util.Scanner;

public class Main
{  
    private static void displayMenu(){
        System.out.println("Enter 1 to mess with a linked list \n" +
                           "Enter 2 to mess with an array list \n" +
                           "Enter 'q' to quit\n");
    }
    
    private static void listMenu(){
        System.out.println("Enter b to Print the list \n" +
                           "Enter c to Sum the list \n" +
                           "Enter d to Zero the list \n" +
                           "Enter e to OddPrint the list \n" +
                           "Enter f to SkipPrint the list \n" +
                           "Enter g to ReversePrint the list \n" +
                           "Enter q to return to the main menu");
    }
    
    public static void main(String [ ] args){
        Scanner keyboard = new Scanner(System.in);  
        displayMenu();
        String option = keyboard.nextLine();
        
        while(!option.contains("q")){
            if(option.contains("1")){linkedMenu();}
            if(option.contains("2")){arrayMenu();}
            
            displayMenu();
            option = keyboard.nextLine();
        }   
    }
    
    public static void linkedMenu(){
        Scanner keyboard = new Scanner(System.in);
        linkList ll = new linkList();
        listMenu();
        String option = keyboard.nextLine();
        while(!option.contains("q")){
            if(option.contains("b")){
                ll.print();
                System.out.println();
            }
            if(option.contains("c")){
                System.out.println(ll.sum());
            }
            if(option.contains("d")){
                System.out.println(ll.zero());
            }
            if(option.contains("e")){
                ll.printOdd();
                System.out.println();
            }
            if(option.contains("f")){
                ll.printSkip();
                System.out.println();
            }
            if(option.contains("g")){
                ll.printReverse();
                System.out.println();
            }
            
            listMenu();
            option = keyboard.nextLine();
        }   
    }
    
    public static void arrayMenu(){
        Scanner keyboard = new Scanner(System.in);
        arrayList al = new arrayList();
        listMenu();
        String option = keyboard.nextLine();
        while(!option.contains("q")){
            if(option.contains("b")){
                al.print();
                System.out.println();
            }
            if(option.contains("c")){
                System.out.println(al.sum());
            }
            if(option.contains("d")){
                System.out.println(al.zero());
            }
            if(option.contains("e")){
                al.printOdd();
                System.out.println();
            }
            if(option.contains("f")){
                al.printSkip();
                System.out.println();
            }
            if(option.contains("g")){
                al.printReverse();
                System.out.println();
            }
            
            listMenu();
            option = keyboard.nextLine();
        }   
    }
}