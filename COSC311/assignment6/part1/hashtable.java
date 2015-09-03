import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class hashtable
{
    private class entry{
        private String data;
        private int hash;
        private entry next;
        
        private entry(String data, int hash){
            this.data = data;
            this.hash = hash;
        }
        
    }
    
    private entry[] table;
    public int count;
    public int probes;
    public int words;
    
    public hashtable(){
        this.table = new entry[10000];
        this.count = 0;
        this.probes = 0;
        this.words = 0;
    }
    
    public boolean insert(String data){
        words++;
        if(count >= 10000){
            return false;
        }
        int hash = Math.abs(hashcode(data)) % 10000;
        probes++;
        if (table[hash] != null){
            entry temp = table[hash];
            entry prev = null;
            while (temp != null){
                if (temp.data == data){
                    return false;
                }
                prev = temp;
                temp = temp.next;
                probes++;
            }
            prev.next = new entry(data, hash);
            count++;
            return true;
        }
        else{
            table[hash] = new entry(data, hash);
            count++;
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
    
    public void download(String path){
        try{
            // Create file 
            FileWriter fstream = new FileWriter(path);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(downloadFormat());
            //Close the output stream
            out.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        
    }
    
    public void reset(){
        this.table = new entry[10000];
        this.count = 0;
        this.probes = 0;
        this.words = 0;
    }
    
    private String downloadFormat(){
        String out = "";
        entry temp;
        
        for(int i=0;i<table.length;i++){
            temp = table[i];
            while(temp != null){
                out = out + String.format("%s %n", temp.data);
                temp = temp.next;
            }
        }
        
        return out;
    }
    
    private int hashcode(String data){
        return data.hashCode();
    }
    
//     private int hashcode(String data){
//         //add everything togetehr, then multiply it together
//         
//         int hash = 0;
//         for(int i=0;i<data.length();i++){
//             hash = hash + data.charAt(i);
//         }
//         
//         for(int i=0;i<data.length();i++){
//             hash = hash * data.charAt(i);
//         }
//         
//         return hash;
//     }

//     private int hashcode(String data){
//         //Most characters I run into will be A - z
//         // A is 65 in ascii, z is 122
//         // 122^2 = 14884
//         // 65^2 = 4225
//         // so, if I want my return values to be between 0 and 10000 I can square the
//         // ascii value, subtract 4225 (I want A to be 0) then find the "average" of the word
// 
//         
//         int hash = 0;
//         for(int i=0;i<data.length();i++){
//             hash = hash + ((int)Math.pow(data.charAt(i), 2) - 4225);
//         }
//         
//         hash = (hash / (data.length()+1)); //avoid divide by zero
//         return hash;
//    }
   
//        private int hashcode(String data){
//         //
// 
//         
//         int hash = 0;
//         char c;
//         for(int i=0;i<(data.length()-1);i++){
//             c = data.charAt(i);
//             if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u'){
//                 hash = hash * data.charAt(i) + data.charAt(i+1);
//             }
//             else{
//                 hash = hash * ((int)Math.pow(data.charAt(i), 2));
//             }
//         }
//         
//         hash = (hash / (data.length()+1)); //avoid divide by zero
//         return hash;
//    }

//        private int hashcode(String data){
//         //this algorithm is based on the idea that most of a word's uniquness is in the middle
//         // free from pre/suf-fixes
// 
//         
//         int hash = 0;
//         char c;
//         for(int i=0;i<(data.length()-1);i++){
//             if(i>1 && i<(data.length()-2)){
//                 hash = hash * data.charAt(i);
//             }
//             //hash = hash + ((int)Math.pow(data.charAt(i), i+1));
//             hash = hash + ((int)Math.pow(data.charAt(i), 2) - 4225);
//             
//         }
//         return hash;
//    }


}
