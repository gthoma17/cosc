import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class hashtable
{
    private class entry{
        private String data;
        private entry left;
        private entry right;
        
        private entry(String data, entry left, entry right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
        
    }
    
    public int count;
    public int probes;
    public int words;
    public entry root;
    
    public hashtable(){
        this.root = null;
        this.count = 0;
        this.probes = 0;
        this.words = 0;
    }
    
    public boolean insert(String data){
        words++;
        if(count >= 10000){ // special case, over 10000 words
            return false;
        }
        else if(root == null){// special case, root null
            root = new entry(data, null, null);
            probes++;
            count++;
            return true;
        }
        else{//general case
            entry temp = root;
            entry prev = null;
            while(temp != null){
                probes++;
                if(data == temp.data){
                    return false;
                }
                else if((data.compareTo(temp.data))<0){
                    prev = temp;
                    temp = temp.left;
                }
                else{
                    prev = temp;
                    temp = temp.right;
                }
            }
            probes++;
            if((data.compareTo(prev.data))<0){
                prev.left = new entry(data, null, null);
                count++;
            }
            else{
                prev.right = new entry(data, null, null);
                count++;
            }
            return true;
        }
    }
    
    public int upload(String path){  
         try{  //grab inputs from file
                FileReader fileReader = new FileReader(new File(path));
                BufferedReader br = new BufferedReader(fileReader);
                String line;
                int i;
                while((line=br.readLine())!=null && count<10000){
                    //do sstuff
                    String[] thisLine = line.split("\\s+");
                    for(i=0;i<thisLine.length;i++){
                        insert(thisLine[i]);
                    }
                }
            }
            catch (Exception e) {
            System.out.println(e.getClass());
            } 
            
         return probes;
    }
    
    
    public void reset(){
        this.root = null;
        this.count = 0;
        this.probes = 0;
        this.words = 0;
    }
}
