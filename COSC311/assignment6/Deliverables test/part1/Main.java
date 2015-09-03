import java.util.Scanner;
public class Main
{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        StuData sd = new StuData();
        
        
        displayMenu();
        int option = keyboard.nextInt();
        
        while(option != 8){
            if (option == 1){
                String[] parms = getParm("stuData").split("\\s+");
                sd.insert(Integer.valueOf(parms[0].trim()),
                		  parms[1],
                		  Integer.valueOf(parms[2].trim()),
                		  Double.valueOf(parms[3].trim()));
            }
            else if (option == 2){sd.delete(Integer.valueOf(getParm("id").trim()));}
            else if (option == 3){sd.search(Integer.valueOf(getParm("id").trim()));}
            else if (option == 4){
                String[] parms = getParm("stuData").split("\\s+");
                sd.modify(Integer.valueOf(parms[0].trim()),
                	      parms[1],
                	      Integer.valueOf(parms[2].trim()),
                	      Double.valueOf(parms[3].trim()));
            }
            else if (option == 5){sd.display();}
            else if (option == 6){sd.upload(getParm("path"));}
            else if (option == 6){sd.download(getParm("path"));}
            else{System.out.println("Invalid Option");}
            
            displayMenu();
            option = keyboard.nextInt();
        }
        
        
    }
    private static void displayMenu(){
        System.out.println("1 - Add a student\n" +
                           "2 - Delete a student\n" +
                           "3 - Search for a student\n" +
                           "4 - Modify a student\n" +
                           "5 - Display all students\n" +
                           "6 - Upload a data file\n" +
                           "7 - Download the data file\n" + 
                           "8 - Exit\n" +
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
