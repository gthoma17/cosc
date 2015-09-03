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
        
<<<<<<< HEAD:assignment6/Deliverables/part1/Main.java
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
=======
        while(option != 2){
            if(option == 1){
                pathIn = getParm("in");
                pathOut = getParm("out");
                
                startTime = System.currentTimeMillis();
                
                ht.upload(pathIn);
                ht.download(pathOut);
                
                stopTime = System.currentTimeMillis();
                
                System.out.println("Start time: " + startTime);
                System.out.println("Stop time: " + stopTime);
                System.out.println("Run time: " + (stopTime - startTime));
                System.out.println("Total probes: " + ht.probes);
                System.out.println("Total count: " + ht.count);
                System.out.println("Total words: " + ht.words);
                System.out.println("Average number of probes: " + (Double.valueOf((double)ht.probes / (double)ht.count)));
                
                ht.reset();
>>>>>>> FETCH_HEAD:assignment6/part1/Main.java
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
        else if(parm == "out"){
            System.out.println("Enter the output file path: ");
            toReturn = keyboard.nextLine();
        }
        return toReturn;
    }
}
