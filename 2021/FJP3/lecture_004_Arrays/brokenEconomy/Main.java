import java.util.*;

public class Main{
    
    public  static void brokenEconomy(int [] arr, int x){
        int li = 0;
        int ri = arr.length-1;
        
        int ceil = 0;
        int floor = 0;

        while(li<=ri){
            int mid = (li+ri)/2;
            
            if(arr[mid] == x){
                
                ceil = arr[mid];
                floor = arr[mid];
                break;
            }
            else if(arr[mid] > x){
               
                
                ceil = arr[mid];
                ri = mid-1;
            }
            else {
               
                
                floor = arr[mid];
                li = mid + 1;
            }
            
        }

        System.out.println(ceil);
        System.out.println(floor);
        
    }
        

    public static void main(String [] args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int [] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = scn.nextInt();
        }
        int x = scn.nextInt();
        
        brokenEconomy(arr, x);
       
       

    }
}