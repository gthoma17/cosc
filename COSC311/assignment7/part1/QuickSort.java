public class QuickSort
{
   public static void sort(int[] a){
       quickSort(a, 0, a.length-1); //recurse, recurse
   }
   
   private static void quickSort(int[] a, int low, int high){
       if(low < high){// if more than 2 items
           int mid = partition(a, low, high); //partition it
           quickSort(a, low, mid-1);
           quickSort(a, mid+1, high);
       }
   }
   
   private static int partition(int[] a, int low, int high){
       int temp = low;
       
       for(int i = low+1; i<=high; i++){
           if(a[i] > a[low]){ //if greater than pivot, insert left
               temp = temp + 1;
               swap(a, i, temp);
           }
           //otherwise, insert right (no work required)
       }
       
       swap(a, low, temp); //insert pivot into middle
       return temp; //return middle location
   }
   
   private static void swap(int[] a, int i, int j){
       int temp = a[i];
       a[i] = a[j];
       a[j] = temp;
   }
}