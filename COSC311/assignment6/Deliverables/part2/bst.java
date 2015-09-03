import java.util.Random;
public class bst
{
    private class node{
        int data;
        node left;
        node right;
        node parent;
        
        private node(int data, node left, node right, node parent){
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
    
    node root;
    //needs: insert, constructor using rng, height, count nodes
    
    public bst(){
        this.root = null;
    }
    public bst(int n, long seed){
        this.root = null;
        Random rand = new Random(seed);
        for(int i=0;i<n;i++){
            this.insert((rand.nextInt(1000000000)+1));
        }
    }
    
    public int count(){
        return count(root);
    }
    
    public int height(){
        return height(root);
    }
    
    public boolean insert(int data){
        if(root == null){ //special case, empty tree
            root = new node(data, null, null, null);
            return true;
        }
        else{
            node temp = root;
            node prev = null;
            while(temp != null){
                if(temp.data == data){
                    return false;
                }
                else if(data > temp.data){ //go right
                    prev = temp;
                    temp = temp.right;
                }
                else{ //go left
                    prev = temp;
                    temp = temp.left;
                }
            }
            if(data > prev.data){ //create right
                prev.right = new node(data, null, null, prev);
                return true;
            }
            else{ //create left
                prev.left = new node(data, null, null, prev);
                return true;
            }
        }
    }
    
    public boolean search(int data){
        node temp = root;
        while(temp != null){
            if(temp.data == data){
                    return true;
            }
            else if(data > temp.data){ //go right
                    temp = temp.right;
            }
            else{ //go left
                temp = temp.left;
            }
        }
        return false;
    }
    
    private int count(node temp){
        if(temp == null){
            return 0;
        }
        else{
            return 1 + count(temp.left) + count(temp.right);
        }
    }
    
    private int height(node temp){
        if(temp == null){
            return 0;
        }
        int lheight = height(temp.left);
        int rheight = height(temp.right);
        if(lheight > rheight){
            return 1 + lheight;
        }
        else{
            return 1 + rheight;
        }
    }
}
