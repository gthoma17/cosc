public class hanoiSolver
{
    int cnt;
    public hanoiSolver(int disks){
        cnt = 0;
        hanoi("A","C","B",disks,1);
        System.out.println("Move disk 1 black from B to C");
        System.out.println("Total moves: " + (cnt+1));
    }
    
    public void hanoi(String source, String dest, String inter, int disks, int cnt){
        if(disks==1){
            if(cnt % 2 == 0){
                move(source, inter, disks, "white");
                move(source, inter, disks, "black");
            }
            
            else{
                move(source, inter, disks, "black");
                move(source, inter, disks, "white");
            }
        }
        else{
            hanoi(source, inter, dest, disks-1,cnt+1); // move n-1 pile to intermediate pole
            move(source, inter, disks, "white"); // move nth white disk to intermediate pole
            hanoi(dest, inter, source, disks-1,cnt); // return n-1 pile to source
            hanoi(source, dest, inter, disks-1,cnt+1); // move n-1 pile to dest pole
            move(source, dest, disks, "black");  // move nth black disk to destination pole
            hanoi(source, dest, inter, disks-1,cnt+1); // move n-1 pile to intermediate pole
        }
    }
    public void move(String source, String dest, int disk, String color){
        System.out.println("Move disk "+disk+" "+color+" from "+source+" to "+dest);
        cnt++;
    }
}
