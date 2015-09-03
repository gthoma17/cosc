import java.util.Scanner;
import java.util.Random;
public class Main
{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
             
        displayMenu();
        int option = keyboard.nextInt();
        bst bs = null;
        long seed;
        
        while(option != 4){
            if (option == 1){
                System.out.println("Enter a the desired number of nodes");
                int n = keyboard.nextInt();
                System.out.println("Enter an RNG seed");
                seed = keyboard.nextLong();
                bs = new bst(n,seed);
            }
            else if (option == 2){
                if(bs != null){
                    System.out.println("Height: " + bs.height());
                    System.out.println("Count: " + bs.count());
                }
                else{
                    System.out.println("You havn't initialized your tree yet");
                }
            }
            else if (option == 3){
                if(bs != null){
                    System.out.println("Enter an RNG seed");
                    seed = keyboard.nextLong();
                
                    Random rand = new Random(seed);

                    long start = System.currentTimeMillis();
                    System.out.println("Start: " + start);
                    for(int i=0;i<1000000;i++){
                        bs.search((rand.nextInt(1000000000)+1));
                    }
                    long stop = System.currentTimeMillis();
                    System.out.println("Stop:  " + stop);
                    double time = Double.valueOf(stop-start);
                    System.out.println("Total time required: " + time);
                }
                else{
                    System.out.println("You havn't initialized your tree yet");
                }
            }
            else{System.out.println("Invalid Option");}
            
            displayMenu();
            option = keyboard.nextInt();
        }
        
        
    }
    private static void displayMenu(){
        System.out.println("1 - Create BST\n" +
                           "2 - Display height & # of nodes\n" +
                           "3 - Perform 10^6 random searches\n" +
                           "4 - Exit\n" +
                           "Choose an option - ");
    }
}

