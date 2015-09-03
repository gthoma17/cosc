public class HeapSort
{
   public static void sort(int[] a){
       int i, current, left, right;
       
       //insert elements into a min heap
       for(i=1; i<a.length; i++){
           current = i; //first element moves up the heap
           while(current > 0 && a[current] < a[(current-1)/2]){
               swap(a, (current-1)/2, current);
               current = (current-1)/2;
           }
       }
       
       //delete min elements, place at end of array, maintain the heap
       for(i = a.length-1; i>=0; i--){
           swap(a, 0, i);
           current = 0;
           while(true){
               left = 2*current+1;
               right = 2*current+2;
               
               if(left<=i-1 && right<=i-1){//two children
                   if(a[current] <= a[left] && a[current] <= a[right]){
                       break;
                   }
                   else{
                       if(a[left] < a[right]){
                           swap(a, current, left);
                           current = left;
                       }
                       else{
                           swap(a, current, right);
                           current = right;
                       }
                   }
               }
               else if(left <= i-1 && right > i-1){//left child only
                   if(a[current]<=a[left]){
                       break;
                   }
                   else{
                       swap(a, current, left);
                       current = left;
                   }
               }
               else{//no children
                   break;
               }
           }
       }
   }
   
   public static void swap(int[] a, int i, int j){
      int temp = a[i];
      a[i] = a[j];
      a[j] = temp;
   }
}