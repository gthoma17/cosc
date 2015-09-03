public class SelectionSort
{
   public static void sort(int[] a){
       int i, j, max, temp;
       
       for(i=0; i<a.length; i++){
           max = i; //initialize position of max
           for(j=i+1; j<a.length; j++){
               if(a[j]>a[max]){//look for new max
                   max = j;
               }
           }
           temp = a[i];
           a[i] = a[max];
           a[max] = temp;
       }
   }
}