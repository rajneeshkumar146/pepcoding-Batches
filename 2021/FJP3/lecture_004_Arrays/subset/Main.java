import java.io.*;
import java.util.*;

public class Main{
public static void subset(int [] arr){
    //total  -- 2^n
    
    
    //total number of subset print
        
        //convert to decimal and on basis of rem. I will print - or val;
    int n = arr.length;    
    int total = (int)Math.pow(2,n);
    
    for(int i = 0; i<total; i++){
        
        // i == 0 --> 0 0 0
        String str = "";
        int temp = i;
        for(int j = n-1; j>=0 ; j--){
            int r = temp % 2;
            temp = temp/2;
            
            if(r == 0){
                str = "-\t" + str;
            }
            else{
                str = arr[j] + "\t" + str;
            }
            
        }
        
        System.out.println(str);
    }
}
public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int [] arr = new int[n];
    for(int i = 0 ; i<n; i++){
        arr[i]=scn.nextInt();
    }
    subset(arr);
 }

}