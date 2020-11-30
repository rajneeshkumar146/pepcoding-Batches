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
    public static int OBST(int[] keys,int[] freq,int si,int ei,int level,int[][][] dp){
        if(dp[si][ei][level] != -1) return dp[si][ei][level];

        int minCost = (int) 1e9;
        for(int cut = si; cut<=ei;cut++){
            int leftTree = (cut == si) ? 0 : OBST(keys,freq, si, cut-1 ,level + 1,dp);
            int rightTree = (cut == ei) ? 0 : OBST(keys,freq, cut + 1, ei ,level + 1,dp);

            minCost = Math.min(minCost, leftTree + freq[cut] * level  + rightTree);
        }

        return dp[si][ei][level] = minCost;
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
                    int leftTree = (cut == si) ? 0 : dp[si][cut-1];
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
        int[] keys={10, 12, 20};
        int[] freq ={34, 8, 50};
        int n = keys.length;
        int[] psum = new int[n];
        int prev = 0;
        for(int i=0;i<n;i++){
            psum[i] = prev + freq[i];
            prev = psum[i];
        }

        int[][][] dp = new int[n][n][n+1];
        for(int[][] D:dp) for(int[] d:D) Arrays.fill(d,-1);
        System.out.println(OBST(keys,freq,0,n-1,1,dp));
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
        return minScoreTriangulation(A,0,n-1,dp); 
    }

    //https://practice.geeksforgeeks.org/problems/boolean-parenthesization/0#
    public static void main (String[] args) throws IOException 
    {
        Scanner scn = new Scanner(new BufferedReader(new InputStreamReader((System.in))));
        //  Scanner scn = new Scanner( System.in);
        int t = scn.nextInt();
        while(t-->0){
            int n = scn.nextInt();
            scn.nextLine();
            String str = scn.nextLine();
            
            int N = str.length();
            pair_[][] dp = new pair_[N][N];
            System.out.println(booleanParen(str,0,N - 1,dp).TCount % mod);
        }
    
    }
    
        public static class pair_{
       int TCount = 0;
       int FCount = 0;

       pair_(int T,int F){
           this.TCount = T;
           this.FCount = F;
       }
   }
   
   static int mod = 1003;
   public static void EvaluateBooleanAns(pair_ leftAns, pair_ rightAns,char oper,pair_ ans){
        int totalWays =  ( (leftAns.TCount % mod + leftAns.FCount % mod) % mod * (rightAns.TCount % mod + rightAns.FCount % mod) % mod ) % mod;
        if(oper == '|'){
           
           int fcount = (leftAns.FCount % mod * rightAns.FCount % mod) % mod;
           ans.TCount += (totalWays % mod - fcount  % mod + mod) % mod;
           ans.FCount += fcount;

       }else if(oper == '&'){
           
           int tcount = (leftAns.TCount % mod * rightAns.TCount % mod) % mod;
           ans.TCount += tcount;
           ans.FCount += (totalWays % mod - tcount  % mod + mod) % mod;
      
       }else{
           
           int tcount = ( (leftAns.TCount % mod * rightAns.FCount % mod) % mod + (leftAns.FCount % mod * rightAns.TCount % mod) % mod) % mod;
           ans.TCount += tcount;
           ans.FCount += (totalWays % mod - tcount  % mod + mod) % mod;
       
       }


   }

   public static pair_ booleanParen(String str,int si,int ei,pair_[][] dp){
       if(si == ei){
           int t = str.charAt(si) == 'T' ? 1 : 0;
           int f = str.charAt(si) == 'F' ? 1 : 0;
           pair_ base = new pair_(t,f);
           return dp[si][ei] = base;
       }

       if(dp[si][ei] != null) return dp[si][ei];

       pair_ ans = new pair_(0,0);
       for(int cut = si + 1;cut < ei; cut+=2){
           pair_ leftAns = booleanParen(str,si,cut-1,dp);
           pair_ rightAns = booleanParen(str,cut+1,ei,dp);

           char oper = str.charAt(cut);
           EvaluateBooleanAns(leftAns,rightAns,oper,ans);
       }

       return dp[si][ei] = ans;
   }


   //leetcode 132
   public int minCut(String str,int si,int[] dp,boolean[][] isPalindrome){
    if(si == str.length() || isPalindrome[si][str.length() - 1]){
        return dp[si] = 0;
    }
    
    if(dp[si] != -1) return dp[si];
    
    int ans  = (int)1e8;
    for(int cut = si;cut < str.length();cut++){
        if(isPalindrome[si][cut]){
            int MinCutCount =  minCut(str,cut+1,dp,isPalindrome);
            ans = Math.min(ans,MinCutCount + 1);
        }
    }
    
    return dp[si] = ans;
}


public int minCut(String str) {
    if(str.length() <= 1) return 0;
    int n = str.length();
    
    boolean[][] isPalindrome = new boolean[n][n];
    for(int gap = 0;gap<n;gap++){
        for(int i = 0,j = gap;j < n;i++,j++){
            if(gap == 0) isPalindrome[i][j] = true;
            else if(gap == 1) isPalindrome[i][j] = str.charAt(i) == str.charAt(j);
            else isPalindrome[i][j] = isPalindrome[i +1 ][j - 1] && (str.charAt(i) == str.charAt(j));
        }
    }
    
    int[] dp = new int[n + 1];
    Arrays.fill(dp,-1);
    return minCut(str,0,dp,isPalindrome);
}

public int palindromePartition(int si,int ei,int k,int[][] dp,int[][] pdp){
    if(k == 0) return dp[k][ei] = 0;
    if(k == 1) return dp[k][ei] = pdp[si][ei];
    if(ei - si + 1 <= k) return dp[k][ei] = (ei-si+1 < k) ? (int)1e8 : 0;
    
    if(dp[k][ei] != -1) return dp[k][ei];
    
    int min_ = (int)1e8; 
    for(int cut = si; cut < ei;cut++){
        int recAns = palindromePartition(si,cut,k-1,dp,pdp);
        
        min_ = Math.min(min_, pdp[cut + 1][ei] + recAns);
    }
    
    return dp[k][ei] = min_;
}


public int palindromePartition(String str, int k) {
    if(str.length() == 0 || k == 0 || str.length() <= k) return 0;
    int n = str.length();
    int[][] pdp = new int[n][n];
    
    for(int gap = 1;gap<n;gap++){
        for(int i=0,j=gap;j<n;i++,j++){
            pdp[i][j] = pdp[i+1][j-1];
            if(str.charAt(i) != str.charAt(j)) pdp[i][j]++;
        }
    }
    
    int[][] dp = new int[k + 1][n + 1];
    for(int[] d: dp) Arrays.fill(d,-1);
    
    return palindromePartition(0,n-1,k,dp,pdp);
    
}


    public static void main(String[] args){
        // MCM();
        // minMaxEvaluation();
        OBST();
    }
}