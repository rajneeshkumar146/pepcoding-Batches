import java.util.Arrays;
public class l006_CutType{

    public static int MCM(int[] arr,int si,int ei,int[][] dp){
        if(si + 1 == ei){
            return dp[si][ei] = 0;
        }

        if(dp[si][ei] != -1) return dp[si][ei];

        int minCost = (int)1e9;
        for(int cut = si + 1;cut < ei;cut++){
            int leftTree = MCM(arr,si,cut,dp);
            int rightTree = MCM(arr,cut,ei,dp);

            int costOfMultiplication = leftTree + (arr[si] * arr[cut] * arr[ei]) + rightTree;
            minCost = Math.min(minCost,costOfMultiplication);
        }

        return dp[si][ei] = minCost;
    }

    public static int MCM_DP(int[] arr,int SI,int EI,int[][] dp){
        int n = arr.length;
        for(int gap = 1;gap<n;gap++){
            for(int si = 0,ei = gap; ei < n;si++,ei++ ){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    continue;
                }
                
                int minCost = (int)1e9;
                for(int cut = si + 1;cut < ei;cut++){
                    int leftTree = dp[si][cut];//MCM(arr,si,cut,dp);
                    int rightTree = dp[cut][ei];//MCM(arr,cut,ei,dp);
        
                    int costOfMultiplication = leftTree + (arr[si] * arr[cut] * arr[ei]) + rightTree;
                    minCost = Math.min(minCost,costOfMultiplication);
                }
        
                dp[si][ei] = minCost;
            }
        }

        return dp[SI][EI];
    }

    public static int MCM_DP_String(int[] arr,int SI,int EI,int[][] dp){
        int n = arr.length;
        String[][] sdp = new String[n][n];
        for(String[] s : sdp) Arrays.fill(s,"");

        for(int gap = 1;gap<n;gap++){
            for(int si = 0,ei = gap; ei < n;si++,ei++ ){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char)(si + 'A') +"";
                    continue;
                }
                
                int minCost = (int)1e9;
                for(int cut = si + 1;cut < ei;cut++){
                    int leftTree = dp[si][cut];//MCM(arr,si,cut,dp);
                    int rightTree = dp[cut][ei];//MCM(arr,cut,ei,dp);
        
                    int costOfMultiplication = leftTree + (arr[si] * arr[cut] * arr[ei]) + rightTree;
                    if(costOfMultiplication < minCost){
                        minCost = costOfMultiplication;
                        sdp[si][ei] = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                    }
                }
        
                dp[si][ei] = minCost;
            }
        }
        
        System.out.println(sdp[SI][EI]);
        return dp[SI][EI];
    }



    public static void MCM(){

        int[] arr = {10, 20, 30, 40, 30};
        int n = arr.length;
        int[][] dp = new int[n][n];
        int  ans = MCM_DP_String(arr,0,n-1,dp);

        System.out.println(ans);
    }

    //https://www.geeksforgeeks.org/minimum-maximum-values-expression/
    public static class pair{
        int minVal = 0;
        int maxVal = 0;

        pair(int minVal,int maxVal){
            this.minVal = minVal;
            this.maxVal = maxVal;
        }
    }

    public static int Evaluate(char ch, int a,int b){
        if(ch == '*') return a*b;
        else return a+b;
    }


    public static pair minMaxEvaluation(int[] num,char[] operator,int si,int ei,pair[][] dp){
        if(si == ei){
            return new pair(num[si],num[si]);
        }
        if(dp[si][ei] != null) return dp[si][ei];

        pair myAns = new pair((int)1e9,-(int)1e9);
        for(int cut = si; cut < ei; cut++){
            pair left = minMaxEvaluation(num,operator,si,cut,dp);
            pair right = minMaxEvaluation(num,operator,cut+1,ei,dp);

            char ch = operator[cut];

            myAns.minVal = Math.min(myAns.minVal, Evaluate(ch,left.minVal,right.minVal)); // m,m / M,M / m,M / M,m  -> m : for min, M : for Max 
            myAns.maxVal = Math.max(myAns.maxVal, Evaluate(ch,left.maxVal,right.maxVal));
        }

        return dp[si][ei] = myAns;
    }

    public  static void minMaxEvaluation(){
        int[] num = {1,2,3,4,5};
        char[] operator ={'+','*','+','*'};
        int n = num.length;
        pair[][] dp = new pair[n][n];


        pair ans = minMaxEvaluation(num,operator,0,n-1,dp);
        System.out.println("minVal : "  + ans.minVal + ", maxVal: " + ans.maxVal);
    }

    public int maxCoins(int[] nums,int si,int ei ,int[][] dp) {
        if(dp[si][ei] != -1) return dp[si][ei];

        int lVal = (si == 0) ? 1 : nums[si-1];
        int rVal = (ei == nums.length - 1) ? 1 : nums[ei + 1];


        
        int myAns = 0;
        for(int cut = si; cut <= ei; cut++){
            int leftAns = (cut == si) ? 0 : maxCoins(nums,si,cut-1,dp);
            int rightAns = (cut == ei) ? 0 : maxCoins(nums,cut+1,ei,dp);

            myAns = Math.max(myAns, leftAns + lVal * nums[cut] * rVal + rightAns);
        }

        return dp[si][ei] = myAns;
    }


    public int maxCoins(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        
        int[][] dp = new int[n][n];
        for(int[] d: dp)Arrays.fill(d,-1);
        
        return maxCoins(nums,0,n-1,dp);   
    }

    //https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/

    // only for memoization
    public static int OBST(int[] keys,int[] freq,int si,int ei,int level,int[][] dp){
        if(dp[si][ei] != -1) return dp[si][ei];

        int minCost = (int) 1e9;
        for(int cut = si; cut<=ei;cut++){
            int leftTree = (cut == si) ? 0 : OBST(keys,freq, si, cut-1 ,level + 1,dp);
            int rightTree = (cut == ei) ? 0 : OBST(keys,freq, cut + 1, ei ,level + 1,dp);

            minCost = Math.min(minCost, leftTree + freq[cut] * level  + rightTree);
        }

        return dp[si][ei] = minCost;
    }

    public static int OBST_01(int[] keys,int[] freq,int si,int ei,int[] psum,int[][] dp){
        if(dp[si][ei] != -1) return dp[si][ei];

        int minCost = (int) 1e9;
        for(int cut = si; cut<=ei;cut++){
            int leftTree = (cut == si) ? 0 : OBST(keys,freq, si, cut-1 ,psum,dp);
            int rightTree = (cut == ei) ? 0 : OBST(keys,freq, cut + 1, ei ,psum,dp);

            minCost = Math.min(minCost, leftTree + (psum[ei] - (si == 0 ? 0 :psum[si-1]))  + rightTree);
        }
        
        return dp[si][ei] = minCost;
    }

    public static int OBST_02(int[] keys,int[] freq,int si,int ei,int[][] dp){
        if(dp[si][ei] != -1) return dp[si][ei];

        int minCost = (int) 1e9;
        int sum = 0;
        for(int cut = si; cut<=ei;cut++){
            int leftTree = (cut == si) ? 0 : OBST(keys,freq, si, cut-1 ,dp);
            int rightTree = (cut == ei) ? 0 : OBST(keys,freq, cut + 1, ei ,dp);
            sum += arr[cut];
            minCost = Math.min(minCost, leftTree + rightTree);
        }
        
        return dp[si][ei] = minCost + sum;
    }

    public static int OBST_02_DP(int[] keys,int[] freq,int SI,int EI,int[][] dp){
        int n = keys.length;
        for(int gap = 0; gap < n ;gap++){
            for(int si=0,ei = gap;ei < n;si++,ei++){
                int minCost = (int) 1e9;
                int sum = 0;
                for(int cut = si; cut<=ei;cut++){
                    int leftTree = (cut == si) ? 0 : dp[si][cut-1]
                    int rightTree = (cut == ei) ? 0 : dp[cut+1][ei];
                    sum += arr[cut];
                    minCost = Math.min(minCost, leftTree + rightTree);
                }
                
                dp[si][ei] = minCost + sum;
            }
        }
        return dp[SI][EI];
    }

    public static void OBST(){
        int[] keys;
        int[] freq;
        int n = keys.length;
        int[] psum = new int[n];
        int prev = 0;
        for(int i=0;i<n;i++){
            psum[i] = prev + freq[i];
            prev = psum[i];
        }
    }

    //1039
    public int minScoreTriangulation(int[] A,int si,int ei,int[][] dp) {
        if(si + 1 == ei){
            return dp[si][ei] = 0;
        }
        if(dp[si][ei] != 0) return dp[si][ei];
        
        int minAns = (int)1e9;
        for(int cut = si + 1; cut < ei;cut++){
            int leftPoly = minScoreTriangulation(A,si,cut,dp);
            int rightPoly = minScoreTriangulation(A,cut,ei,dp);
            
            minAns = Math.min(minAns, leftPoly + A[si] * A[cut] * A[ei] + rightPoly);
        }

        return dp[si][ei] = minAns;
    }
    
    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        return minTriangulation(A,0,n-1,dp); 
    }
    

    public static void main(String[] args){
        // MCM();
        minMaxEvaluation();
    }
}