public class bst
{
    private class node{
        int data;
        node left;
        node right;
        
        private node(int data, node left, node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    
    node root;
    
    public bst(int[] arr){
        for(int i=0; i<20; i++){
            insert(arr[i]);
        }
    }

    public int nodes(){
        return nodes(root);
    }
    
    public void leaves(){
        leaves(root);
    }
    
    public int height(){
        return height(root);
    }
    
    public int odd(){
        return odd(root);
    }
    
    public boolean zero(){
        return zero(root);
    }
    
    public void descend(){
        descend(root);
    }
    
    public void ascend(){
        ascend(root);
    }
    
    public int minimum(){
        return minimum(root);
    }
    
    public void greater(int val){
        greater(root, val);
    }
    
    public int level(int val){
        return level(root, val, 1);
    }
    
    public void path(int val){
        System.out.println(path(root, val, ""));
    }
    
    private boolean insert(int data){
        if(root==null){//special case, insert at root
            root = new node(data, null, null);
        }
        else{
            node temp = root;
            node prev = null;
            
            while(temp != null){
                if(data == temp.data){
                    return false;
                }
                else if(data > temp.data){
                    prev = temp;
                    temp = temp.right;
                }
                else{
                    prev = temp;
                    temp = temp.left;
                }
            }
            if(data > prev.data){
                prev.right = new node(data, null, null);
            }
            else{
                prev.left = new node(data, null, null);
            }
        }
        return true;
    }
    
    private int nodes(node temp){
        if(temp == null){
            return 0;
        }
        else{
            return 1 + nodes(temp.left) + nodes(temp.right);
        }
    }
    
    private void leaves(node temp){
        if(temp == null){
            return;
        }
        else if(temp.left == null && temp.right == null){
            System.out.println(temp.data);
        }
        else{
            leaves(temp.left);
            leaves(temp.right);
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
    
    private int odd(node temp){
        if(temp == null){
            return 0;
        }
        else if(temp.data % 2 == 1){
            return temp.data + odd(temp.left) + odd(temp.right);
        }
        else{
            return odd(temp.left) + odd(temp.right);
        }
    }
    
    private boolean zero(node temp){
        if(temp == null){
            return false;
        }
        else if(temp.data == 0){
            return true;
        }
        else{
            return (zero(temp.left) || zero(temp.right));
        }
    }
    
    private void descend(node temp){
        if(temp == null){
            return;
        }
        else{
            descend(temp.right);
            System.out.println(temp.data);
            descend(temp.left);
        }
    }
    
    private void ascend(node temp){
        if(temp == null){
            return;
        }
        else{
            descend(temp.left);
            System.out.println(temp.data);
            descend(temp.right);
        }
    }
    
    private int minimum(node temp){
        if(temp == null){
            return Integer.MAX_VALUE;
        }
        else{
            int toReturn;
            int lmin = minimum(temp.left);
            int rmin = minimum(temp.right);
            if(lmin < rmin && lmin < temp.data){
                toReturn = lmin;
            }
            else if(rmin < lmin && rmin < temp.data){
                toReturn = rmin;
            }
            else{
                toReturn = temp.data;
            }
            return toReturn;
        }
    }
    
    private void greater(node temp, int val){
        if(temp == null){
            return;
        }
        else if(temp.data > val){
            System.out.println(temp.data);
        }
        greater(temp.left, val);
        greater(temp.right, val);
    }
    
    private int level(node temp, int val, int lvl){
        if(temp == null){ //didn't find it
            return -1;
        }
        else if(temp.data == val){ //found it
            return lvl;
        }
        else{ //keep searching
            if(val > temp.data){ //go right
                return level(temp.right, val, lvl+1);
            }
            else{ //go left
                return level(temp.left, val, lvl+1);
            }
        }
    }
    
    private String path(node temp, int val, String path){
        if(temp == null){ //no path existed
            return "Path did not exist";
        }
        else if(temp.data == val){// found it
            return path + temp.data;
        }
        else{// keep searching
            if(val > temp.data){ //go right
                return path(temp.right, val, path+temp.data+"-->");
            }
            else{ //go left
                return path(temp.left, val, path+temp.data+"-->");
            }
        }
    }
}
