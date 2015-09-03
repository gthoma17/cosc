import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.PrintWriter;


public class StuData
{

    
    private class stuNode{
        //************************Class Variables*************************\\
        
        //client parms
        private String name;
        private int age;
        private int id;
        private double gpa;
        
        //programmer parms
        private stuNode next;
        private stuNode prev;
        
        //*************************Constructors **************************\\
        private stuNode(String name, 
                        int id, 
                        double gpa, 
                        int age, 
                        stuNode next, 
                        stuNode prev){
            this.name = name;
            this.id = id;
            this.gpa = gpa;
            this.age = age;
            this.next = next;
            this.prev = prev;
        }
        //************************Public Methods *************************\\
        public String toString(){
            String toReturn = String.format("ID: %-6d  " +
                                            "AGE: %-3d  " +
                                            "GPA: %-1.6f  " +
                                            "LAST NAME: %-20s"
                                            , id, age, gpa, name);
            return toReturn;
        }
    }
    
    //************************Class Variables*************************\\
    
    private stuNode head;
    private stuNode tail;
    
    //*************************Constructors **************************\\
    
    public StuData(){
        head = null;
        tail = null;
    }
    
    public StuData(String path){
        head = null;
        tail = null;
        
        
        try{
            FileReader fileReader = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(fileReader);
            String line = null;
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                this.addStu(parts[8], 
                            Integer.valueOf(parts[1]), 
                            Double.valueOf(parts[5]), 
                            Integer.valueOf(parts[3]));
            }
        }catch (Exception e) {
            System.out.println(e.getClass());
        }
        
        /*try{
            Reader reader = new FileReader(path);
            int data = reader.read();
            while(data != -1){
                System.out.println(data);
                //this.addStu("name", 188, 3.9, 19);
                
                data = reader.read();
            }
        }catch (Exception e) {
            System.out.println(e.getClass());
        }*/
    }
    
    //************************Public Methods *************************\\

    public void addStu(String name, int id, double gpa, int age){
        if(id<0 || id>100000 || gpa<0 || gpa>4.25){ // check that id and gpa are good
            System.out.println("Error, ID number or GPA are incorrect");
            return;
        }
        if (head == null){ //special case, first student
            head = tail = new stuNode(name, id, gpa, age, null, null);
        }
        else if (head.id>id){ //special case, new lowest id #
            head = new stuNode(name, id, gpa, age, head, null);
            head.next.prev = head;
        }
        else if (tail.id<id){ //special case, new highest ID#
            tail = new stuNode(name, id, gpa, age, null, tail);
            tail.prev.next = tail;
        }
        else { // general case
            stuNode tmp = head;
            while(tmp.next.id<id){
                tmp = tmp.next;
            }

            if(tmp.next.id == id){ // check ID isn't in use
                System.out.println("Error: ID already in use");
                return;
            }
            tmp.next = new stuNode(name, id, gpa, age, tmp.next, tmp);
            tmp.next.next.prev = tmp.next;
        }
    }
    public void delStu(int id){
        if(id<0 || id>100000){ // check that id is good
            System.out.println("Error: ID number is incorrect");
            return;
        }
        else if(head == null){ // special case, empty list
            System.out.println("Error: ID not in list");
            return;
        }
        else if(id<head.id || id>tail.id){ //get easy to catch mistakes
            System.out.println("Error: ID not in list");
            return;
        }
        else if(head == tail){ //special case, only one node
            head = tail = null;
        }
        else if(id == head.id){ //special case, delete the head
            head = head.next;
            head.prev = null;
        }
        else if(id == tail.id){ //special case, delete the tail
            tail = tail.prev;
            tail.next = null;
        }
        else { // general case
            stuNode tmp = head;
            while(tmp.next.id<id){
                tmp = tmp.next;
            }

            if(tmp.next.id == id){ // check ID is in use
                tmp.next = tmp.next.next;
                tmp.next.prev = tmp;
            }
            else{
                System.out.println("Error: ID not in list");
                return;
            }
        }
    }
    public void getStu(int id){
        if(head == null){ // special case, empty list
            System.out.println("Error: ID not in list");
            return;
        }
        else if(id<head.id || id>tail.id){ //get easy to catch mistakes that aren't in the list
            System.out.println("Error: ID not in list");
            return;
        }
        stuNode tmp = head;
        
        while(tmp.id != id && tmp.next != null){
            System.out.println(tmp.id + " - " + id);
            if(tmp.id>id){
                System.out.println("Error: ID not in list");
                return;
            }
            tmp = tmp.next;
        }
        if(tmp.next == null && id != tail.id){
            System.out.println("Error: ID not in list");
            return;
        }
        else{System.out.println(tmp.toString());}
    }
    
    
    public void printAll(){
        System.out.println(this.toString());
    }
    public String toString(){
        String toReturn = "";
        if(head == null){ // special case, empty list
            return toReturn;
        }
        for(stuNode tmp=head; tmp.next != null; tmp = tmp.next){
            toReturn = toReturn + tmp.toString() + "\n";
        }
        toReturn = toReturn + tail.toString();
        return toReturn;
    }
    public void outFile(String path){
        try{PrintWriter writer = new PrintWriter(path, "UTF-8");
            if(head == null){
                System.out.println("List is empty...");
            }
            else{
                for(stuNode tmp=head; tmp.next != null; tmp = tmp.next){
                    writer.println(tmp.toString());
                }
                writer.println(tail.toString());
            }
            writer.close();
        }catch (Exception e) {
            System.out.println(e.getClass());
        }
        
    }
    
    public void inFile(String path){
        head = null;
        tail = null;

        try{
            FileReader fileReader = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(fileReader);
            String line = null;
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                this.addStu(parts[8], 
                            Integer.valueOf(parts[1]), 
                            Double.valueOf(parts[5]), 
                            Integer.valueOf(parts[3]));
            }
        }catch (Exception e) {
            System.out.println(e.getClass());
        }
    }
    
    public boolean isEmpty(){
        if(head == null){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void main(String[] args) {
        StuData s = new StuData();
        s.addStu("mark", 5, 3.1, 22);
        s.addStu("chad", 9, 2.1, 21);
        s.addStu("dave", 10, 2.7, 26);
        s.addStu("greg", 3, 3.25, 20);
        s.addStu("trev", 4, 1.1, 22);
        s.addStu("ronn", 1, 2, 22); 
        s.addStu("kenn", 7, 1.5, 22);
        s.addStu("timm", 2, 2.141, 22);
        s.addStu("matt", 6, 2.532, 22);
        s.addStu("mark", 8, 3.123, 22);
        s.printAll();
        s.outFile("C:\\Users\\Greg\\Desktop\\out.txt");
        System.out.println("-----------------------------------------------------");
        StuData t = new StuData("C:\\Users\\Greg\\Desktop\\in.txt");
        t.printAll();
        
    }
    //************************Private Methods*************************\\

 }
