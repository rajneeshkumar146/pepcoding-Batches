import java.util.*;
import java.lang.*;
import java.io.*;
    
public class questions{
	public static void main (String[] args)
	 {
	      Scanner scn=new Scanner(System.in);
	      int t=scn.nextInt();
	      while(t-->0){
	          int[] arr=new int[scn.nextInt()];
	          for(int i=0;i<arr.length;i++) arr[i]=scn.nextInt();
	          System.out.println(LBS(arr));
	      }
	 }
	 
	 
    // Left to Right
    public static int LIS_LR(int[] arr,int[] dp){
        int max_=0;
        for(int i=0;i<arr.length;i++){
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
                if(arr[j] < arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            max_=Math.max(max_,dp[i]);
        }
        return max_;
    }

    // Right to Left
    public static int LIS_RL(int[] arr,int[] dp){
            int max_=0;
            for(int i = arr.length-1; i>=0 ; i--){
                dp[i] = 1;
                for(int j = i+1;j < arr.length;j++){
                    if(arr[j] < arr[i]){
                        dp[i]=Math.max(dp[i],dp[j]+1);
                    }
                }
                max_=Math.max(max_,dp[i]);
            }
            return max_;
    }

    // https://www.geeksforgeeks.org/longest-bitonic-subsequence-dp-15/
    public static int LBS(int[] arr){
        int n=arr.length;
        int[] LIS=new int[n];
        int[] LDS=new int[n];

        LIS_LR(arr,LIS);
        LIS_RL(arr,LDS);

        int maxLen=0;
        for(int i=0;i<n;i++){
            int len=LIS[i]+LDS[i]-1;
            maxLen=Math.max(maxLen,len);
        }

        return maxLen;
    }


    //Maximum Sum Bitonic subsequnece
    // Left to Right
    public static int LIS_LR(int[] arr,int[] dp){
        int max_=0;
        for(int i=0;i<arr.length;i++){
            dp[i] = arr[i];
            for(int j = i-1;j>=0;j--){
                if(arr[j] < arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+arr[i]);
                }
            }
            max_=Math.max(max_,dp[i]);
        }
        return max_;
    }

    // Right to Left
    public static int LIS_RL(int[] arr,int[] dp){
            int max_=0;
            for(int i = arr.length-1; i>=0 ; i--){
                dp[i] = arr[i];
                for(int j = i+1;j < arr.length;j++){
                    if(arr[j] < arr[i]){
                        dp[i]=Math.max(dp[i],dp[j]+arr[i]);
                    }
                }
                max_=Math.max(max_,dp[i]);
            }
            return max_;
    }

    public static int LBS(int[] arr){
        int n=arr.length;
        int[] LIS=new int[n];
        int[] LDS=new int[n];

        LIS_LR(arr,LIS);
        LIS_RL(arr,LDS);

        int maxLen=0;
        for(int i=0;i<n;i++){
            int len=LIS[i]+LDS[i]-arr[i];
            maxLen=Math.max(maxLen,len);
        }

        return maxLen;
    }



}