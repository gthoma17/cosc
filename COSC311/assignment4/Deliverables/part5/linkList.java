import java.util.Random;
public class linkList
{
    node head;
    
    private class node{
        int data;
        node next;
        public node(int data){
            this.data = data;
        }
        public node(int data, node next){
            this.data = data;
            this.next = next;
        }
    }

    public linkList()
    {
        Random rand = new Random();
        this.head = null;
        for (int i=0;i<20;i++){
            this.addFirst(rand.nextInt(101)); populate 20 cells with random
        }
    }

    private void addFirst(int data){
        head = new node(data, head);
    }
    
    public void print(){ //method B
        if(head != null){
            print(head);
        }
        else{
            System.out.println("Error, nothing to print");
        }
    }
    
    public int sum(){ //method C
        if(head != null){
            return sum(head);
        }
        else{
            System.out.println("Error, nothing to sum");
            return -1;
        }
    }
    
    public boolean zero(){ //method D
        return zero(head);
    }
    
    public void printOdd(){ //method E
        if(head != null){
            printOdd(head);
        }
        else{
            System.out.println("Error, nothing to print");
        }
    }
    
    public void printSkip(){ //method F
        if(head != null){
            printSkip(head);
        }
        else{
            System.out.println("Error, nothing to print");
        }
    }
    
    public void printReverse(){//method G
        if(head != null){
            printReverse(head);
        }
        else{
            System.out.println("Error, nothing to print");
        }
    }
    
    private void print(node n){ //private method B
        System.out.print(n.data + ">"); //print current cell
        if(n.next != null){
            print(n.next); //keep on truckin' till the end
        }
    }
    
    private int sum(node n){ //private method C
        if(n.next == null){
            return n.data; //if this is the end, return yourself
        }
        else{
            return n.data + sum(n.next); // otherwise, add yourself to everyone else
        }
    }
    
    private boolean zero(node n){ //private method D
        if(n.data == 0){ //found zero, we're done here
            return true;
        }
        else if(n.next == null){ //we've reached the end without finding a zero
            return false;
        }
        else{
            return zero(n.next); //keep on truckin'
        }
    }
    
    private void printOdd(node n){ //private method E
        if(n.data % 2 != 0){
            
            System.out.print(n.data + ">"); //print if odd
        }
        
        if(n.next != null){
            printOdd(n.next); //keep on truckin'
        }
    }
    
    private void printSkip(node n){ //private method F
        System.out.print(n.data + ">"); //print yourself
        if(n.next != null){
            if(n.next.next != null){
                printSkip(n.next.next); //go print the cell after next
            }
        }
    }
    
    private void printReverse(node n){ //private method G
        if(n.next != null){
            printReverse(n.next); //crank through to the end
        }
        System.out.print("<" + n.data); // then start printing
    }
 }
