import java.util.Scanner;
public class Main
{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        BooksCustomers BC = new BooksCustomers();
        
        
        displayMenu();
        int option = keyboard.nextInt();
        
        while(option != 7){
            if (option == 1){BC.addBook(getParm("title"), 
							 Double.valueOf(getParm("price")));}
            else if (option == 2){BC.orderBook(getParm("title"), getParm("name"), Integer.valueOf(getParm("copies")));}
            else if (option == 3){BC.sellBook(getParm("title"));}
            else if (option == 4){BC.cancelBook(getParm("title"), getParm("name"));}
            else if (option == 5){BC.bookInfo(getParm("title"));}
            else if (option == 6){BC.allInfo();}
            else{System.out.println("Invalid Option");}
            
            displayMenu();
            option = keyboard.nextInt();
        }
        
        
    }
    private static void displayMenu(){
        System.out.println("1 - Add a new book\n" +
                           "2 - Order a book\n" +
                           "3 - Sell a book\n" +
                           "4 - Cancel a book\n" +
                           "5 - Information about a book\n" +
                           "6 - Information about all books\n" +
                           "7 - Close the store. All data will be lost.\n" + 
                           "Choose an option - ");
    }
    
    
    private static String getParm(String parm){
        Scanner keyboard = new Scanner(System.in);
        String toReturn = "";
        if(parm == "title"){
            System.out.println("Enter title of book: ");
            toReturn = keyboard.nextLine();
        }
        else if (parm == "name"){
            System.out.println("Enter name of customer: ");
            toReturn = keyboard.nextLine();
        }
        else if (parm == "copies"){
            System.out.println("Enter number of copies: ");
            toReturn = String.valueOf(keyboard.nextInt());
        }
        else if (parm == "price"){
            System.out.println("Enter price of book: ");
            toReturn = String.valueOf(keyboard.nextDouble());
        }
        else{
            System.out.println("Weird Error, you better contact IT");
        }
        return toReturn;
    }
}
