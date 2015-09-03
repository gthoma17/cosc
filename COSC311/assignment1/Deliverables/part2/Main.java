import java.util.Scanner;
public class Main
{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        StuData SD = new StuData();
        
        
        displayMenu();
        int option = keyboard.nextInt();
        
        while(option != 7){
            if (option == 1){SD.addStu(getParm("name"), 
							 Integer.valueOf(getParm("id")), 
							 Double.valueOf(getParm("gpa")), 
							 Integer.valueOf(getParm("age")));}
            else if (option == 2){SD.delStu(Integer.valueOf(getParm("id")));}
            else if (option == 3){SD.getStu(Integer.valueOf(getParm("id")));}
            else if (option == 4){SD.printAll();}
            else if (option == 5){SD.outFile(getParm("path"));}
            else if (option == 6){SD.inFile(getParm("path"));}
            else{System.out.println("Invalid Option");}
            
            displayMenu();
            option = keyboard.nextInt();
        }
        
        
        
    }
    private static void displayMenu(){
        System.out.println("1 - Add a new Student\n" +
                           "2 - Delete a Student\n" +
                           "3 - Print a Student's information\n" +
                           "4 - Print all Student information\n" +
                           "5 - Save all Student information to a file\n" +
                           "6 - Restore Student information from a file\n" +
                           "7 - Close the program, All student data " + 
						      "will be lost. You could get fired...\n" + 
                           "Choose an option - ");
    }

    
    private static String getParm(String parm){
        Scanner keyboard = new Scanner(System.in);
        String toReturn = "";
        if(parm == "id"){
            System.out.println("Enter Student ID: ");
            toReturn = String.valueOf(keyboard.nextInt());
        }
        else if (parm == "gpa"){
            System.out.println("Enter Student GPA: ");
            toReturn = String.valueOf(keyboard.nextDouble());
        }
        else if (parm == "age"){
            System.out.println("Enter Student Age: ");
            toReturn = String.valueOf(keyboard.nextInt());
        }
        else if (parm == "name"){
            System.out.println("Enter Student's Last Name: ");
            toReturn = keyboard.nextLine();
        }
        else if (parm == "path"){
            System.out.println("Enter full path to the file: ");
            toReturn = keyboard.nextLine();
        }
        else{
            System.out.println("Weird Error, you better contact IT");
        }
        return toReturn;
    }
}
