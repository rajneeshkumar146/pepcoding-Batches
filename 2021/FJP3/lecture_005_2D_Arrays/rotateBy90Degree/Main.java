import java.io.*;
import java.util.*;

public class Main {
    
    public static void transpose(int [][] arr){
        int n = arr.length;
        for(int i = 0; i<n ; i++){
            for(int j = i; j<n; j++){
                int tmp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = tmp;
            }
        }
    }
    
    public static void reverse1D(int [] arr){
        int li = 0;
        int ri = arr.length-1;
        while(li<=ri){
            int t = arr[li];
            arr[li] = arr[ri];
            arr[ri] = t;
            li++;
            ri--;
        }
        
    }
    
    public static void reverse(int [][] arr){
        int n = arr.length;
        // for(int i = 0; i<n ; i++){
        //     int li = 0;
        //     int ri = n-1;
            
        //     while(li<=ri){
        //         int tmp = arr[i][li];
        //         arr[i][li] = arr[i][ri];
        //         arr[i][ri] = tmp;
                
        //         li++;
        //         ri--;
        //     }
            
        // }
        
        for(int [] d : arr)
            reverse1D(d);

        
    }
    
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int [][] arr = new int[n][n];
        
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                arr[i][j] = scn.nextInt();
            }
        }
        
        // 2 steps
        // 1. Transpose
        // 2. Reverse each row
        
        transpose(arr);
        reverse(arr);
        display(arr);
    }

    public static void display(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}