import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StuData
{
    private class node{
        int id;
        String lastName;
        int age;
        double gpa;
        node left;
        node right;
        
        private node(int id, String lastName, int age, double gpa, node left, node right){
            this.id = id;
            this.lastName = lastName;
            this.age = age;
            this.gpa = gpa;
            this.left = left;
            this.right = right;
        }
    }
    
    node root;
    
    public StuData()
    {
        root = null;
    }

    public boolean insert(int id, String lastName, int age, double gpa){
        if(age < 10 || age > 100){
        	System.out.println("Invalid age");
        	return false;
        }
        else if(id < 1 || id > 10000){
        	System.out.println("Invalid ID");
        	return false;
        }
        else if(gpa < 0.0 || gpa > 4.0){
        	System.out.println("Invalid GPA");
        	return false;
        }
        else if(root == null){//special case, empty tree
            root = new node(id, lastName, age, gpa, null, null);
            return true;
        }
        else{
            node temp = root;
            node prev = null;
            while(temp != null){
                if(id == temp.id){ //student already exists
                    System.out.println("Student already exists");
                    return false;
                }
                else if(id < temp.id){ //go left
                    prev = temp;
                    temp = temp.left;
                }
                else{ //go right
                    prev = temp;
                    temp = temp.right;
                }
            }
            if(id < prev.id){
                prev.left = new node(id, lastName, age, gpa, null, null);
                return true;
            }
            else{
                prev.right = new node(id, lastName, age, gpa, null, null);
                return true;
            }
        }
    }
    
    public boolean delete(int id){
        node prev = getPrev(id);
        if(prev == null){//delete root
            delete(root, null);
            return true;
        }
        else if(prev.left == null && prev.right == null){// id didn't exist
            return false;
        }
        else if(prev.left != null && prev.left.id == id){// delete left node
            delete(prev.left, prev);
            return true;
        }
        else if(prev.right != null && prev.right.id == id){ //delete right node
            delete(prev.right, prev);
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean search(int id){
        node prev = getPrev(id);
        if(prev == null){//print root
            print(root);
            return true;
        }
        else if(prev.left == null && prev.right == null){// id didn't exist
            System.out.println("Student not found");
            return false;
        }
        else if(prev.left != null && prev.left.id == id){// print left node
            print(prev.left);
            return true;
        }
        else if(prev.right != null && prev.right.id == id){ //print right node
            print(prev.right);
            return true;
        }
        else{
            System.out.println("Student not found");
            return false;
        }
    }
    
    public boolean modify(int id, String lastName, int age, double gpa){
        node prev = getPrev(id);
        if(prev == null){//modify root
            modify(root, lastName, age, gpa);
            return true;
        }
        else if(prev.left == null && prev.right == null){// id didn't exist
            System.out.println("Student not found");
            return false;
        }
        else if(prev.left != null && prev.left.id == id){// print left node
            modify(prev.left, lastName, age, gpa);
            return true;
        }
        else if(prev.right != null && prev.right.id == id){ //print right node
            modify(prev.right, lastName, age, gpa);
            return true;
        }
        else{
            System.out.println("Student not found");
            return false;
        }
    }
    
    public void display(){
        display(root);
    }
    
    public void upload(String path){  
         System.out.println("Upload");
         try{  //grab inputs from file
                FileReader fileReader = new FileReader(new File(path));
                BufferedReader br = new BufferedReader(fileReader);
                
                System.out.println("Setup success");
                
                root = null; // delete current tree
                String line;
                while((line=br.readLine())!=null){
                    //do sstuff
                    System.out.println("Lineread");
                    int id = Integer.valueOf(line.substring(4,9).trim());
                    System.out.println("ID: " + id);
                    int age = Integer.valueOf(line.substring(17,19).trim());
                    System.out.println("AGE: " + age);
                    double gpa = Double.valueOf(line.substring(26,32));
                    System.out.println("GPA: " + gpa);
                    String lastName = line.substring(44);
                }
            }
            catch (Exception e) {
            System.out.println(e.getClass());
            } 
    }
    public void download(String path){
        try{
            // Create file 
            FileWriter fstream = new FileWriter(path);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(downloadFormat(root));
            //Close the output stream
            out.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        
    }
    
    private String downloadFormat(node temp){
        if(temp == null){
            return "";
        }
        else{
            return format(temp) + downloadFormat(temp.left) + downloadFormat(temp.right);
        }
    }
    
    private void display(node temp){
        if(temp == null){
            return;
        }
        else{
            display(temp.left);
            print(temp);
            display(temp.right);
        }
    }
    
    private void modify(node temp, String lastName, int age, double gpa){
        temp.lastName = lastName;
        temp.age = age;
        temp.gpa = gpa;
    }
    
    private String format(node temp){
        return String.format("ID: %5d  AGE: %3d  GPA: %1.3f  LASTNAME: %S %n",temp.id, 
                                                                              temp.age, 
                                                                              temp.gpa, 
                                                                              temp.lastName);
        
    }
    
    private void print(node temp){
        System.out.print(format(temp));
    }
    
    private boolean delete(node temp, node prev){
        if(temp.left == null){ //no left subtree
            System.out.println("No left subtree");
            if (temp == root){ //deleting root
                root = temp.right;
            }
            else if (temp == prev.left){
                prev.left = temp.right;
            }
            else{
                prev.left = temp.right;
            }
        }
        else if(temp.right == null){//no right subtree
            System.out.println("No right subtree");
            if (temp == root){ //deleting root
                root = temp.left;
            }
            else if (temp == prev.right){
                prev.right = temp.left;
            }
            else{
                prev.left = temp.left;
            }
        }
        else{//left and right subtrees present
            System.out.println("both subtree");
            node p = temp.left;
            node q = temp;
            while(p.right != null){ //get max of left subtree
                q = p;
                p = p.right;
            }
            temp.id = p.id;
            temp.age = p.age;
            temp.gpa = p.gpa;
            temp.lastName = p.lastName;
            if(q == p.left){
                q.left = p.left;
            }
            else{
                q.right = p.left;
            }
        }
        return true;
    }
    
    private node getPrev(int id){
        node temp = root;
        node prev = null;
        while(temp != null){
            if(id == temp.id){ // found it
                break;
            }
            else if(id < temp.id){ //go left
                prev = temp;
                temp = temp.left;
            }
            else{ //go right
                prev = temp;
                temp = temp.right;
            }
        }
        return prev;
    }
    
}
