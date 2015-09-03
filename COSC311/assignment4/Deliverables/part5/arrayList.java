import java.util.Random;
public class arrayList
{
    int[] arr;

    public arrayList()
    {
        Random rand = new Random();
        this.arr = new int[20]; //make array of len 20
        for (int i=0;i<20;i++){
            this.arr[i] = rand.nextInt(101); //populate it with random numbers
        }
    }
    
    public void print(){ //method B
        print(0);
    }
    
    public int sum(){ //method C
        return sum(0);
    }
    
    public boolean zero(){ //method D
        return zero(0);
    }
    
    public void printOdd(){ //method E
        printOdd(0);
    }
    
    public void printSkip(){ //method F
        printSkip(0);
    }
    
    public void printReverse(){//method G
        printReverse(0);
    }
    
    private void print(int n){ //private method B
        System.out.print("[" + arr[n] + "]"); //print current cell
        if(n < arr.length - 1){ //if this isn't the end
            print(n+1); // keep on crankin'
        }
    }
    
    private int sum(int n){ //private method C
        if(n == arr.length - 1){
            return arr[n]; // if this is the end, return yourself
        }
        else{
            return arr[n] + sum(n+1); // otherwise add yourself to the rest of the array
        }
    }
    
    private boolean zero(int n){ //private method D
        if(arr[n] == 0){ //found zero, we're done here
            return true;
        }
        else if(n == arr.length - 1){ //we've reached the end without finding a zero
            return false;
        }
        else{
            return zero(n+1); // keep on crankin'
        }
    }
    
    private void printOdd(int n){ //private method E
        if(arr[n] % 2 != 0){
            System.out.print("[" + arr[n] + "]"); //print on numbers
        }
        
        if(n < arr.length - 1){
            printOdd(n+1); // keep on crankin'
        }
    }
    
    private void printSkip(int n){ //private method F
        System.out.print("[" + arr[n] + "]"); //print current cell
        if(n + 1 < arr.length - 1){
            printSkip(n+2); //go to the cell after next
        }
    }
    
    private void printReverse(int n){ //private method G
        if(n < arr.length - 1){
            printReverse(n+1); //crank through to the end
        }
        System.out.print("[" + arr[n] + "]"); // then start printing
    }
 }
