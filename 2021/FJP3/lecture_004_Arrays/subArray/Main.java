import java.io.*;
import java.util.*;

public class Main{

public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();      //3
    int [] arr = new int[n];
    
    for(int i = 0; i<n; i++){
        arr[i] = scn.nextInt();     //10 20 30
    }
    
    //input part done
    
    // st --
    //     end--
    //         st --end print
    
    
    for(int start = 0; start<n; start++){
        for(int end = start; end < n; end++){
            for(int k = start; k<=end; k++){
                System.out.print(arr[k] + "\t");
            }
            System.out.println();
        }
    }
    
    
    
 }

}