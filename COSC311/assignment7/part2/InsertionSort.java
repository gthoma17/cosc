import java.io.IOException;
import java.io.RandomAccessFile;

public class InsertionSort
{
   public static void sort(String path, int length) throws IOException{
       int i, j;
       double temp, value;
       RandomAccessFile file = new RandomAccessFile(path, "rw");
       
       for(i=0;i<length*8;i=i+8){//outer loop
           file.seek(i);
           temp = file.readDouble(); //save the element we're inserting
           for(j=i-8;j>=0; j=j-8){//inner loop
               file.seek(j);
               value = file.readDouble(); //save the element we're comparing
               if(temp > value){
                   break; //found the next smallest element, we can stop
               }
               else{
                   file.seek(j+8);
                   file.writeDouble(value);
               }
           }
           file.seek(j+8);
           file.writeDouble(temp); //insert
       }
   }
}
