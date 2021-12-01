import java.io.*;
import java.util.*;

public class Main{

public static int [] difference(int [] A, int [] B){
    int borrow = 0;
    int [] ans = new int[B.length];
    int i = A.length-1;
    int j = B.length-1;
    int k = ans.length-1;
    
    while(j>=0){
        int digit = 0;
        digit += B[j] + borrow;
        if(i >= 0){
            digit -= A[i];
        }
        
        if(digit < 0){
            digit += 10;
            borrow = -1;
        }
        else{
            borrow = 0;
        }
        
        int val = digit % 10;
        
        ans[k] = val;
        i--;
        j--;
        k--;
        
    }
    return ans;
    
    
    
}

public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    
    int n1 = scn.nextInt();
    int [] A = new int[n1];
    for(int i = 0; i<n1; i++){
        A[i] = scn.nextInt();    
    }
    
        // n2 > n1
    int n2 = scn.nextInt();
    int [] B = new int[n2];
    for(int i = 0; i<n2; i++){
        B[i] = scn.nextInt();
    }
    
    
    int [] ans = difference(A,B);
    
    //edge cases
    
    // 1. first non zero index -- fnzi
    int fnzi = -1;
    for(int i = 0; i<ans.length; i++){
        if(ans[i] != 0){
            fnzi = i;
            break;
        }
    }
    //546-546 = 000
    if(fnzi == -1){
        System.out.println("0");
        return;
    }
    
    for(int i = fnzi; i<ans.length; i++){
        System.out.println(ans[i]);
    }
    
    
    
    
    
    
 }

}