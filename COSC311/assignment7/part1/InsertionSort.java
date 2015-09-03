public class InsertionSort
{
   public static void sort(int[] a){
       int i, j, temp;
       
       for(i=0;i<a.length;i++){//outer loop
           temp = a[i]; //save the element we're inserting
           for(j=i-1;j>=0 && temp > a[j]; j--){//inner loop
               a[j+1] = a[j]; //shuft until we don't have to anymore
           }
           a[j+1] = temp; //insert
       }
   }
}
