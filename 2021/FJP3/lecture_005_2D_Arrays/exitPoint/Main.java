import java.io.*;
import java.util.*;

public class Main {
    
    public static Scanner scn = new Scanner(System.in);
    public static void input(int [][] arr){
        for(int i = 0; i<arr.length; i++){
            for(int j = 0; j<arr[0].length; j++){
                arr[i][j] = scn.nextInt();
            }
        }
    }
    
    public static void exitPoint(int [][] arr){
        //east 0. south 1, west = 2, north = 3
        
        int dir = 0;
        
        //initially top left 0,0
        int i = 0;
        int j = 0;
        int n = arr.length;
        int m = arr[0].length;
        
        while(true){
            dir += arr[i][j];
            dir %= 4;
            
            if(dir == 0){
                j++;
            }
            else if(dir == 1){
                i++;
            }
            else if(dir == 2){
                j--;
            }
            else{
                //dir == 3
                i--;
            }
            
            if( i < 0 || j < 0 || i >= n || j >= m){
                break;
            }
            
        }
        
        //from which cell I have gone out of grid
        
        if(i < 0){
            i++;
        }
        else if(i>=n){
            i--;
        }
        else if(j<0){
            j++;
        }
        else if(j >= m){
            j--;
        }
        
        System.out.println(i);
        System.out.println(j);
        
    }
    public static void main(String[] args) throws Exception {
        int n = scn.nextInt();
        int m = scn.nextInt();
        
        int [][] arr = new int[n][m];
        
        input(arr);
        
        exitPoint(arr);
    }

}