import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Main
{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
             
        displayMenu();
        int option = keyboard.nextInt();
        long seed;
        int[] a;
        
        while(option != 6){
            if (option == 1){
                a = genArray();
                writeFile(a);
                
                long startTime = System.currentTimeMillis();
                SelectionSort.sort(a);
                long stopTime = System.currentTimeMillis();
                
                writeFile(a);
                System.out.println("Time required to sort: " + (stopTime - startTime));
            }
            else if (option == 2){
                a = genArray();
                writeFile(a);
                
                long startTime = System.currentTimeMillis();
                InsertionSort.sort(a);
                long stopTime = System.currentTimeMillis();
                
                writeFile(a);
                System.out.println("Time required to sort: " + (stopTime - startTime));
            }
            else if (option == 3){
                a = genArray();
                writeFile(a);
                
                long startTime = System.currentTimeMillis();
                MergeSort.sort(a);
                long stopTime = System.currentTimeMillis();
                
                writeFile(a);
                System.out.println("Time required to sort: " + (stopTime - startTime));
            }
            else if (option == 4){
                a = genArray();
                writeFile(a);
                
                long startTime = System.currentTimeMillis();
                QuickSort.sort(a);
                long stopTime = System.currentTimeMillis();
                
                writeFile(a);
                System.out.println("Time required to sort: " + (stopTime - startTime));
            }
            else if (option == 5){
                a = genArray();
                writeFile(a);
                
                long startTime = System.currentTimeMillis();
                HeapSort.sort(a);
                long stopTime = System.currentTimeMillis();
                
                writeFile(a);
                System.out.println("Time required to sort: " + (stopTime - startTime));
            }
            else{System.out.println("Invalid Option");}
            
            displayMenu();
            option = keyboard.nextInt();
        }
        
        
    }
    private static void displayMenu(){
        System.out.println("1 - Sort an array with Selection Sort\n" +
                           "2 - Sort an array with Insertion Sort\n" +
                           "3 - Sort an array with Merge Sort\n" +
                           "4 - Sort an array with Quick Sort\n" +
                           "5 - Sort an array with Heap Sort\n" +
                           "6 - Exit\n" +
                           "Choose an option - ");
    }
    
    private static int[] genArray(){
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Enter a the desired number of integers");
        int n = keyboard.nextInt();
        System.out.println("Enter an RNG seed");
        long seed = keyboard.nextLong();
        
        int[] a = new int[n];
        Random rand = new Random(seed);
        
        for(int i=0; i<a.length; i++){
            a[i] = (rand.nextInt(1000000000)+1);
        }
        
        return a;
    }
    
    private static void writeFile(int[] a){
        String output = "";
        for(int i=0; i<a.length; i++){
            output = output + String.format("%s %n", Integer.toString(a[i]));
        }
        
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the path to an output file");
        String path = keyboard.nextLine();
        
        try{
            // Create file 
            FileWriter fstream = new FileWriter(path);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(output);
            //Close the output stream
            out.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}