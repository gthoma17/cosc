import java.util.Scanner;
import java.util.Random;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Main
{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
             
        displayMenu();
        int option = keyboard.nextInt();
        long seed;
        int[] a;
        
        while(option != 4){
            if (option == 1){
                try {
                    genFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (option == 2){
                try {
                    displayFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (option == 3){
                try {
                    sortFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{System.out.println("Invalid Option");}
            
            displayMenu();
            option = keyboard.nextInt();
        }
        
        
    }
    private static void displayMenu(){
        System.out.println("1 - Generate a binary file full of doubles\n" +
                           "2 - Display a binary file full of doubles\n" +
                           "3 - Sort a binary file full of doubles\n" +
                           "4 - Exit\n" +
                           "Choose an option - ");
    }
    
    private static void genFile() throws IOException{
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Enter the path to an output file");
        String path = keyboard.nextLine();
        System.out.println("Enter a the desired number of doubles");
        int n = keyboard.nextInt();
        System.out.println("Enter an RNG seed");
        long seed = keyboard.nextLong();
        
        Random rand = new Random(seed);
        RandomAccessFile file = new RandomAccessFile(path, "rw");

        for(int i=0; i<n*8; i=i+8){
            file.seek(i);
            file.writeDouble(rand.nextDouble());
        }
    }
    
    private static void displayFile() throws IOException{
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Enter the path to a file");
        String path = keyboard.nextLine();
        System.out.println("Enter a the number of doubles");
        int n = keyboard.nextInt();
        
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        
        for(int i=0; i<n*8; i=i+8){
            file.seek(i);
            System.out.println(file.readDouble());
        }
    }
    
    private static void sortFile() throws IOException{
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Enter the path to a file");
        String path = keyboard.nextLine();
        System.out.println("Enter a the number of doubles");
        int n = keyboard.nextInt();
        
        long startTime = System.currentTimeMillis();
        InsertionSort.sort(path, n);
        long stopTime = System.currentTimeMillis();
        
        System.out.println("Time required to sort: " + (stopTime - startTime));
    }
}