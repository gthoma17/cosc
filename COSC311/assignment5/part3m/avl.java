import java.util.Random;
public class avl
{
    private class node{
        int data;
        int height;
        node left;
        node right;
        node parent;
        
        private node(int data, int height, node left, node right, node parent){
            this.data = data;
            this.height = height;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
    
    node root;
    //needs: insert, constructor using rng, height, count nodes
    
    public avl(){
        this.root = null;
    }
    public avl(int n, long seed){
        this.root = null;
        Random rand = new Random(seed);
        for(int i=0;i<n;i++){
            System.out.println("Insert # " + i);
            this.insert((rand.nextInt(1000000000)+1));
        }
    }
    
    public int count(){
        return count(root);
    }
    
    public int height(){
        return root.height;
    }
    
    public boolean insert(int data){
        if(root == null){ //special case, empty tree
            root = new node(data, 1, null, null, null);
            return true;
        }
        else{
            node temp = root;
            node prev = null;
            System.out.println("About to search for: " + data);
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
            System.out.println("Got temp");
            if(data > prev.data){ //create right
                prev.right = new node(data, 1, null, null, prev);
                updateHeight(prev.right);
                return true;
            }
            else{ //create left
                prev.left = new node(data, 1, null, null, prev);
                updateHeight(prev.left);
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
    public void prePrint(){
        prePrint(root);
    }
    
    private void prePrint(node temp){
        if(temp == null){
            return;
        }
        else{
            System.out.println(temp.data);
            prePrint(temp.left);
            System.out.println("__________");
            prePrint(temp.right);
        }
    }
    
    private void updateHeight(node temp){
        if(temp == null){// at root, we're done
            return;
        }
        if(height(temp.left)==temp.height || height(temp.right)==temp.height){
            temp.height = temp.height+1;
        }
        if(imbalenced(temp)){
            rebalence(temp);
        }
        updateHeight(temp.parent);
    }
    
    private int height(node temp){
        if(temp == null){
            return 0;
        }
        else{
            return temp.height;
        }
    }
    
    private boolean imbalenced(node temp){
        int lheight = height(temp.left);
        int rheight = height(temp.right);
        if(rheight - lheight < -1 || rheight - lheight > 1){
            return true;
        }
        else{
            return false;
        }
    }
    
    private void rebalence(node temp){
        if(height(temp.left) > height(temp.right)){ //left heavy
            if(height(temp.left.left) > height(temp.left.right)){ //left heavy outside
                node parent = temp.parent;
                node nodeA = temp;
                node nodeB = temp.left;
                node subY = temp.left.right;
                
                nodeA.left = subY;
                nodeA.parent = nodeB;
                nodeB.right = nodeA;
                nodeB.parent = parent;
                if(subY != null){subY.parent = nodeA;}
                if(parent == null){ //special case, temp is root
                    root = nodeB;
                }
                else if(parent.left == temp){
                    parent.left = nodeB;
                }
                else{
                    parent.right = nodeB;
                }
                //update heights
                temp.height = Math.max(height(temp.left),height(temp.right)) + 1;
                temp = nodeB;
                temp.height = Math.max(height(temp.left),height(temp.right)) + 1;
            }
            else{//left heavy inside
                node parent = temp.parent;
                node nodeA = temp;
                node nodeB = temp.left;
                node nodeC = temp.left.right;
                node subY = temp.left.right.left;
                node subZ = temp.left.right.right;
                
                if(parent == null){ //special case, temp is root
                    root = nodeB;
                }
                else if(parent.left == temp){
                    parent.left = nodeC;
                }
                else{
                    parent.right = nodeC;
                }
                nodeA.left = subZ;
                nodeA.parent = nodeC;
                nodeB.right = subY;
                nodeB.parent = nodeC;
                nodeC.left = nodeB;
                nodeC.right = nodeA;
                nodeC.parent = parent;
                if(subY != null){subY.parent = nodeB;}
                if(subZ != null){subZ.parent = nodeA;}
                //update heights
                temp.height = Math.max(height(temp.left),height(temp.right)) + 1;
                temp = nodeB;
                temp.height = Math.max(height(temp.left),height(temp.right)) + 1;
                temp = nodeC;
                temp.height = Math.max(height(temp.left),height(temp.right)) + 1;
            }
        }
        else{ //right heavy
            if(height(temp.right.left) > height(temp.right.right)){//right heavy inside
                node parent = temp.parent;
                node nodeA = temp;
                node nodeB = temp.right;
                node nodeC = temp.right.left;
                node subY = temp.right.left.left;
                node subZ = temp.right.left.right;
                
                if(parent == null){ //special case, temp is root
                    root = nodeC;
                }
                else if(parent.left == temp){
                    parent.left = nodeC;
                }
                else{
                    parent.right = nodeC;
                }
                nodeA.right = subY;
                nodeA.parent = nodeC;
                nodeB.left = subZ;
                nodeB.parent = nodeC;
                nodeC.left = nodeA;
                nodeC.right = nodeB;
                nodeC.parent = parent;
                if(subY != null){subY.parent = nodeA;}
                if(subZ != null){subZ.parent = nodeB;}
                //update heights
                temp.height = Math.max(height(temp.left),height(temp.right)) + 1;
                temp = nodeB;
                temp.height = Math.max(height(temp.left),height(temp.right)) + 1;
                temp = nodeC;
                temp.height = Math.max(height(temp.left),height(temp.right)) + 1;
            }
            else{//right heavy outside
                node parent = temp.parent;
                node nodeA = temp;
                node nodeB = temp.right;
                node subY = temp.right.left;
                
                nodeA.right = subY;
                nodeA.parent = nodeB;
                nodeB.left = nodeA;
                nodeB.parent = parent;
                if(subY != null){subY.parent = nodeA;}
                
                if(parent == null){ //special case, temp is root
                    root = nodeB;
                }
                else if(parent.left == temp){
                    parent.left = nodeB;
                }
                else{
                    parent.right = nodeB;
                }
                //update heights
                temp.height = Math.max(height(temp.left),height(temp.right)) + 1;
                temp = nodeB;
                temp.height = Math.max(height(temp.left),height(temp.right)) + 1;
            }
        }
    }
    
    private int count(node temp){
        if(temp == null){
            return 0;
        }
        else if(temp.right == null && temp.left == null){
            return 1;
        }
        else{
            return 1 + count(temp.left) + count(temp.right);
        }
    }
}
