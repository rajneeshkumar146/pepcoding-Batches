import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.Scanner; 

public class client{
    public static Scanner scn = new Scanner(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        // linkedlist ll = new linkedlist();
        // for(int i = 1; i <= 10;i++){
        //     ll.addLast(i*10);
        // }

        // for(int i = 10; i >=1;i--){
        //     ll.addFirst(i*10);
        // }

        // System.out.println(ll);
        
        System.out.println(scn.nextInt());
        System.out.println(scn.nextDouble());
    }

}