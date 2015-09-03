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
            this.addFirst(rand.nextInt(101));
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
        System.out.print(n.data + ">");
        if(n.next != null){
            print(n.next);
        }
    }
    
    private int sum(node n){ //private method C
        if(n.next == null){
            return n.data;
        }
        else{
            return n.data + sum(n.next);
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
            return zero(n.next);
        }
    }
    
    private void printOdd(node n){ //private method E
        if(n.data % 2 != 0){
            
            System.out.print(n.data + ">");
        }
        
        if(n.next != null){
            printOdd(n.next);
        }
    }
    
    private void printSkip(node n){ //private method F
        System.out.print(n.data + ">");
        if(n.next != null){
            if(n.next.next != null){
                printSkip(n.next.next);
            }
        }
    }
    
    private void printReverse(node n){ //private method G
        if(n.next != null){
            printReverse(n.next);
        }
        System.out.print("<" + n.data);
    }
 }
