public class SuckerPicker{
    private class node{
        private int num;
        private node next;
        
        private node(int num, node next){
            this.num = num;
            this.next = next;
        }
    } 
    
    private node head;
    
    public SuckerPicker(){
        head = null;
    }
    
    public SuckerPicker(int n, int s, int k){
        head = null;
        this.runSim(n,s,k);
    }
    
    public void add(int num){
        if(head == null){ //special case, empty list
            head = new node(num, null);
        }
        else{ //general case
            head = new node(num, head);
        }
    }
    
    public void delNext(node prev){
        if(head == null){ // special case, empty list.
            System.out.println("Error, empty list. How did you get here?");
            return;
        }
        else if (prev.next == null){// special case, one node left. 
            System.out.println("Error, one node. How did you get here?");
            return;
        }
        else if (prev.next == head){// special case, deleting head
            head = prev.next.next;
            prev.next = prev.next.next;
            return;
        }
        else{
        prev.next = prev.next.next;
        }
    }
    
    public void makeCircle(){
        node tmp = head;
        while(tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = head;
    }
    
    public void clearList(){
        if (head == null){ // special case, empty list.
            return;
        }
        head.next = null; //it will all get garbage collected eventually
        head = null;
    }
    
    public void populateList(int num){
        // we should probably make sure the list is empty first
        this.clearList();
        for(int i=num;i>0;i--){
            this.add(i);
        }
        this.makeCircle();
    }
    
    public String toString(){
        node tmp = head;
        String toReturn = head.num + ", ";
        tmp = tmp.next;
        while(tmp.next != head){
            toReturn = toReturn + tmp.num + ", ";
            tmp= tmp.next;
        }
        toReturn = toReturn + tmp.num;
        return toReturn;
    }
    
    public void runSim(int num, int start, int move){
        boolean debug = false;
        this.populateList(num); // generate list
        // error conditions
        if(move >= num || start >= num){
            System.out.println("Error: bad parameter");   
            return;
        }
        if(head == null){ //empty list. something is wrong
            System.out.println("Error: empty list");   
            return;
        }
        // general case
        else{
            node tmp = head;
            for(int i=1;i<start-1;i++){ //get into position
                tmp = tmp.next;
            }
            
            while(head.next != head){ //until there is one node left
                for(int i=0;i<move-1;i++){
                    tmp = tmp.next;
                }
                if(debug){
                    System.out.println("List prior to removal: " + toString());
                    System.out.println("Removing player # " + tmp.next.num);
                }
                this.delNext(tmp);
            }
            System.out.println("Player #" + head.num + " is the sucker");
            
            this.clearList();
        }
    }
}
