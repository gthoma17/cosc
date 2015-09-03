public class MergeSort
{
   public static void sort(int[] a){
       mergeSort(a, 0, a.length-1);
   }
   
   private static void mergeSort(int[] a, int low, int high){
       if(low<high){ //make sure array lenght is greater than 2
           int mid = (low + high)/2;
           mergeSort(a, low, mid); //sort left half
           mergeSort(a, mid+1, high); //sort right half
           merge(a, low, mid, high); //merge the halves
       }
   }
   
   private static void merge(int[] a, int low, int mid, int high){
       int[] tmp = new int[high-low+1];
       int p = low;
       int q = mid + 1;
       int r = 0;
       
       while(p<=mid && q<=high){ // while both halves aren't done
           if(a[p] > a[q]){         //copy the larger element into tmp array
               tmp[r++] = a[p++];
           }
           else{
               tmp[r++] = a[q++];
           }
       }
       
       if(p > mid){ //left half finished, copy the rest of the right half over
           while(q<=high){
               tmp[r++] = a[q++];
           }
       }
       else{
           while(p<=mid){
               tmp[r++] = a[p++];
           }
        }
        
       for(p=0; p<high-low+1; p++){//copy tmp array into original array
           a[low+p] = tmp[p];
       }
   }
}