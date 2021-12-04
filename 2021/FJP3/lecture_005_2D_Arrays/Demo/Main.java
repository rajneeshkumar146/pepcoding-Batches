import java.io.*;
import java.util.*;

public class Main{

public static void main(String[] args) throws Exception {
    // // 1D create
    // int [] arr = new int[n];
    
    // 2D create
    // int [][] arr = new int[n][m];
    
    // //travel each cell
    // for(int i = 0; i<n; i++){
    //     for(int j = 0; j<m; j++){
    //         System.out.println(arr[i][j]);
    //     }
    // }
    
    // for(int i = 0; i<n; i++){
    //     for(int j = 0; j<m; j++){
    //         arr[i][j] = scn.nextInt();
    //     }
    // }
    
    
    
    //=======================================
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int m = scn.nextInt();
    
    int [][] arr = new int [n][m];
    for(int i = 0; i<n; i++){
        for(int j = 0; j<m; j++){
            arr[i][j] = scn.nextInt();
        }
    }
    
    for(int i = 0; i<n; i++){
        for(int j = 0; j<m; j++){
            System.out.print(arr[i][j]+ " ");
        }
        System.out.println();
    }
      
    
 }

}