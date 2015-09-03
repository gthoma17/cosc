import java.util.Scanner;
public class Main
{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("How many people are playing? (n) : ");
        int n = keyboard.nextInt();
        System.out.println("On which player should we first start counting? (s) : ");
        int s = keyboard.nextInt();
        System.out.println("How many players should we count per turn? (k) : ");
        int k = keyboard.nextInt();
        SuckerPicker sp = new SuckerPicker(n,s,k);
    }
}
