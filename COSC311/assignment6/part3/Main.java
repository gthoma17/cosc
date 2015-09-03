import java.util.Scanner;
public class Main
{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        hashtable ht = new hashtable();
        String pathIn;
        String pathOut;
        long startTime;
        long stopTime;
        int probes;
        
        displayMenu();
        int option = keyboard.nextInt();
        
        while(option != 2){
            if(option == 1){
                pathIn = getParm("in");
                
                startTime = System.currentTimeMillis();
                
                ht.upload(pathIn);
                
                stopTime = System.currentTimeMillis();
                
                System.out.println("Start time: " + startTime);
                System.out.println("Stop time: " + stopTime);
                System.out.println("Run time: " + (stopTime - startTime));
                System.out.println("Total probes: " + ht.probes);
                System.out.println("Total count: " + ht.count);
                System.out.println("Total words: " + ht.words);
                System.out.println("Average number of probes: " + (Double.valueOf((double)ht.probes / (double)ht.count)));
                
                ht.reset();
            }
            
            displayMenu();
            option = keyboard.nextInt();
        }
        
        
    }
    private static void displayMenu(){
        System.out.println("1 - Test the hash table on a file\n" +
                           "2 - Exit\n" +
                           "Choose an option - ");
    }
    private static String getParm(String parm){
        Scanner keyboard = new Scanner(System.in);
        String toReturn = "";
        if(parm == "in"){
            System.out.println("Enter the input file path: ");
            toReturn = keyboard.nextLine();
        }
        return toReturn;
    }
}
