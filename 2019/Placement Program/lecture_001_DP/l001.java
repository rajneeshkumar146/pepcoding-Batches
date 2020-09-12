import java.util.LinkedList;
import java.util.Arrays;
public class l001{

    public static void print(int[] arr){
        for(int ele: arr) System.out.print(ele + " ");
        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] ar: arr) print(ar);
        System.out.println();
    }

    //fibo.=====================================================================

    public static int fibo(int n,int[] dp){
        if(n<=1){
            return dp[n] = n;   //store
        }
        if(dp[n]!=0) return dp[n]; // use of previous answer

        int ans = fibo(n-1,dp) + fibo(n-2,dp);
        return dp[n] = ans;   // store 
    }

    public static int fibo_DP(int N,int[] dp){
        for(int n = 1;n<=N;n++){
            if(n<=1){
                dp[n] = n; 
                continue;
            }

            int ans = dp[n-1] + dp[n-2]; //fibo(n-1,dp) + fibo(n-2,dp);
            dp[n] = ans;                
        }

        return dp[N];
    }

    public static int fibo_Opti(int N){
        int a = 0;
        int b = 1;

        for(int i=2;i<=N;i++){
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void fibo(){
        int n = 10;
        int[] dp=new int[n+1];
        System.out.println(fibo(n,dp));
        System.out.println(fibo_DP(n,dp));
        System.out.println(fibo_Opti(n));

        print(dp);
    }

    // Leetcode 70 .=============================================================
    public int climbStairs(int n,int[] dp) {
        if(n<=1){
            return dp[n] = 1;
        }

        if(dp[n]!=0) return dp[n];
        
        int ans = climbStairs(n-1,dp) + climbStairs(n-2,dp);
        
        return dp[n] = ans;
    }

    public int climbStairs_DP(int N,int[] dp) {
        for(int n = 0;n<=N;n++){
            if(n<=1){
                dp[n] = 1;
                continue;
            }
    
            int ans = dp[n-1] + dp[n-2];//climbStairs(n-1,dp) + climbStairs(n-2,dp);
            
            dp[n] = ans;
        }
        return dp[N];
    }

    public  int climbStairs(int n) {
        int[] dp = new int[n + 1];
        // return climbStairs(n, dp);
        return climbStairs_DP(n,dp);
    }

    //Leetcode 746.==============================================================
    public int minCostClimbingStairs(int[] cost,int n,int[] dp){
        if(n<=1){
            return dp[n] = cost[n];
        }

        if(dp[n]!=0) return dp[n];

        int ans = Math.min(minCostClimbingStairs(cost,n-1,dp),minCostClimbingStairs(cost,n-2,dp));

        return dp[n] = ans + (n!=cost.length?cost[n]:0);
    }

    public int minCostClimbingStairs_DP(int[] cost,int N, int[] dp){
        for(int n = 0 ;n<=N;n++){
            if(n<=1){
                dp[n] = cost[n];
                continue;
            }
    
            int ans = Math.min(dp[n-1],dp[n-2]);
    
            dp[n] = ans + (n != cost.length ? cost[n] : 0);
        }
        return dp[N];
    }

    public  int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[cost.length + 1];
        int ans = minCostClimbingStairs(cost,n,dp);
        // int ans = minCostClimbingStairs_DP(cost,n,dp);
        return ans;
    }

    //Geeks : https://www.geeksforgeeks.org/friends-pairing-problem/ ============

    public static int friendsPairing(int n,int[] dp){
        if(n<=1){
            return dp[n] = 1;
        }

        if(dp[n]!=0) return dp[n];

        int single = friendsPairing(n-1,dp);
        int pairUp = friendsPairing(n-2,dp) * ( n - 1 );

        return dp[n] = single + pairUp;
    }

    public static int friendsPairing_DP(int N,int[] dp){
        for(int n = 0; n<=N;n++){
            if(n<=1){
               dp[n] = 1;
               continue;
            }
    
            int single = dp[n-1];//friendsPairing(n-1,dp);
            int pairUp = dp[n-2] * (n-1);//friendsPairing(n-2,dp) * ( n - 1 );
    
            dp[n] = single + pairUp;
        }
        return dp[N];
    }

    public static int friendsPairing_Opti(int N){
        int a = 1;
        int b = 1;
        for(int n = 2; n<=N;n++){
            int sum = b + a * (n-1); 
            a = b;
            b = sum; 
        }
        return b;
    }

    //Path Set.==============================================================
    public static int mazePath_HVD(int sr,int sc,int er,int ec,int[][] dp,int[][] dir){
        if(sr==er && sc==ec){
            return dp[sr][sc]=1;
        }
        if(dp[sr][sc]!=0) return dp[sr][sc];

        int count = 0;
        for(int d=0;d<dir.length;d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r <= er && c <= ec)
              count += mazePath_HVD(r,c,er,ec,dp,dir); 
        }

        return dp[sr][sc] = count;
    }


    public static int mazePath_HVD_Multi(int sr,int sc,int er,int ec,int[][] dp,int[][] dir){
        if(sr==er && sc==ec){
            return dp[sr][sc]=1;
        }
        if(dp[sr][sc]!=0) return dp[sr][sc];

        int count = 0;
        for(int d=0;d<dir.length;d++){
            for(int rad = 1;rad <= dp.length;rad++){
               int r = sr + rad*dir[d][0];
               int c = sc + rad*dir[d][1];

               if(r <= er && c <= ec)
                count += mazePath_HVD_Multi(r,c,er,ec,dp,dir);
               else break;
            } 
        }

        return dp[sr][sc] = count;
    }

    public static int mazePath_HVD_DP(int sr,int sc,int er,int ec,int[][] dp,int[][] dir){
        for(sr = er;sr>=0;sr--){
            for(sc = ec;sc>=0;sc--){
                if(sr==er && sc==ec){
                    dp[sr][sc]=1;
                    continue;
                }

                
                int count = 0;
                for(int d=0;d<dir.length;d++){
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
        
                    if(r <= er && c <= ec)
                      count += dp[r][c]; 
                }
        
                dp[sr][sc] = count;
            }
        }

        return dp[0][0];
    }


    public static int mazePath_HVD_Multi_DP(int sr,int sc,int er,int ec,int[][] dp,int[][] dir){
        for(sr = er;sr>=0;sr--){
            for(sc = ec;sc>=0;sc--){
                
                if(sr==er && sc==ec){
                    dp[sr][sc]=1;
                    continue;
                }

                int count = 0;
                for(int d=0;d<dir.length;d++){
                    for(int rad = 1;rad <= dp.length;rad++){
                       int r = sr + rad*dir[d][0];
                       int c = sc + rad*dir[d][1];
        
                       if(r <= er && c <= ec)
                        count += dp[r][c];
                       else break;
                    } 
                }
        
                dp[sr][sc] = count;
            }
        }

        return dp[0][0];
    }

    public static int boardPath(int si,int ei,int[] dp){
        if(si==ei){
            return dp[si] = 1;
        }
        if(dp[si]!=0) return dp[si];

        int count=0;
        for(int dice = 1;dice <= 6 && si+dice<=ei ; dice++){
            count+=boardPath(si+dice,ei,dp);
        }

        return dp[si] = count;
    }

    public static int boardPath_DP(int si,int ei,int[] dp){ 
        for(si = ei; si>=0;si--){
            if(si==ei){
                dp[si] = 1;
                continue;
            }

            int count=0;
            for(int dice = 1;dice <= 6 && si+dice<=ei ; dice++){
                count += dp[si+dice];
            }
    
            dp[si] = count;
        }

        return dp[0];
    }

    public static int boardPath_Opti(int si,int ei){ 
        LinkedList<Integer> ll = new LinkedList<>();
        for(si = ei; si>=0;si--){
            if(si >= ei-1){
                ll.addFirst(1);
                continue;
            }

            if(ll.size()<=6) 
              ll.addFirst(2*ll.getFirst());
            else
               ll.addFirst((2 * ll.getFirst()) - ll.removeLast());
        }

        return ll.getFirst();
    }

    public static int boardPath_Opti2(int ei){ 
        int[] ans = {1,1,2,4,8,16,32};
        int idx = 6;
        if(ei<=6) return ans[ei];

        for(int i=7;i<=ei;i++){
            int val = 2*ans[idx];
            idx = (idx+1) % 7;
            val -= ans[idx];

            ans[idx] = val;     
        }

        return ans[idx];
    }

    //Leetcode 64
    public int minPathSum(int[][] grid,int sr,int sc,int[][] dp) {
        if(sr == grid.length - 1 && sc == grid[0].length - 1){
            return dp[sr][sc] = grid[sr][sc];
        }

        if(dp[sr][sc]!=0) return dp[sr][sc];
        
        int minAns = (int) 1e9;
        if(sr + 1 < grid.length) minAns = Math.min(minAns, minPathSum(grid,sr+1,sc,dp));
        if(sc + 1 < grid[0].length) minAns = Math.min(minAns, minPathSum(grid,sr,sc+1,dp));

        return dp[sr][sc]=minAns;
    }

    
    public int minPathSum_DP(int[][] grid,int sr,int sc,int[][] dp) {
        for(sr = grid.length-1;sr>=0;sr--){
            for(sc = grid[0].length-1;sc>=0;sc--){
                if(sr == grid.length - 1 && sc == grid[0].length - 1){
                    dp[sr][sc] = grid[sr][sc];
                }
                
                int minAns = (int) 1e9;
                if(sr + 1 < grid.length) minAns = Math.min(minAns, dp[sr+1][sc]);
                if(sc + 1 < grid[0].length) minAns = Math.min(minAns,dp[sr][sc+1]);
        
                dp[sr][sc]=minAns;
            }
        }

        return dp[0][0];
    }

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        int ans= minPathSum(grid,0,0,dp);         
        return ans;
    }

    public static int goldMine(int[][] grid,int sr,int sc,int[][] dp,int[][] dir){
        if(sc==grid[0].length-1){
            return dp[sr][sc] = grid[sr][sc];
        }

        if(dp[sr][sc]!=0) return dp[sr][sc];

        int maxAns = 0;
        for(int d=0;d<dir.length;d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            
            if(r>=0 && c>=0 && r< grid.length && c<grid[0].length)
              maxAns = Math.max(maxAns,goldMine(grid,r,c,dp,dir));
        }

        return dp[sr][sc] = maxAns + grid[sr][sc];
    }

    public static void goldMineP_DP(int[][] grid,int sr,int sc,int[][] dp,int[][] dir){
        for(sr = grid.length-1;sr>=0;sr--){
            for(sc = grid[0].length-1;sc>=0;sc--){
                if(sr == grid.length-1 && sc == grid[0].length-1){
                     dp[sr][sc] = grid[sr][sc];
                     continue;
                }
        
                int maxAns = 0;
                for(int d=0;d<dir.length;d++){
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    
                    if(r>=0 && c>=0 && r< grid.length && c<grid[0].length)
                      maxAns = Math.max(maxAns,dp[r][c]);
                }
        
                dp[sr][sc] = maxAns + grid[sr][sc];
            }
        }

        int maxRes =0;
        for(int i=0;i<grid.length;i++)
          maxRes = Math.max(maxRes, dp[i][0]);

        System.out.println(maxRes);
        
    }



    public static void goldMine(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m]; 
        
        int maxAns = 0;
        int[][] dir = {{-1,1},{0,1},{1,1}};

        for(int i=0;i<n;i++)
          maxAns = Math.max(maxAns, goldMine(grid,i,0,dp,dir));
        
        System.out.println(maxAns);
    }

    // https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/

    public static int countWays(int n,int k,int[][] dp){
        // if(n < k) return 0; 
        if(n==k || k==1){
            return dp[n][k] = 1;
        }

        if(dp[n][k] != -1) return dp[n][k];

        int ownSet = countWays(n-1,k-1,dp);
        int partOfSet = countWays(n-1,k,dp) * k;

        return dp[n][k] = ownSet + partOfSet;
    }

    
    public static int countWays_DP(int N,int K,int[][] dp){ 
        for(int n = 1;n<=N;n++){
            for(int k=1;k<=K;k++){
                if(n<k){
                    dp[n][k] =0;
                    break;
                }

                if(n==k || k==1){
                    dp[n][k] = 1;
                    continue;
                }
        
                int ownSet = dp[n-1][k-1];//countWays(n-1,k-1,dp);
                int partOfSet = dp[n-1][k]* k;
        
                dp[n][k] = ownSet + partOfSet;
            }
        }

        return dp[N][K];
    }

    //https://practice.geeksforgeeks.org/problems/mobile-numeric-keypad/0

    public static void mazePath(){
        // int n = 3;
        // int m = 3;

        // int[][] dp = new int[n][m];
        // int[][] dir = {{1,0},{0,1},{1,1}};

        // System.out.println(mazePath_HVD(0,0,n-1,m-1,dp,dir));
        // System.out.println(mazePath_HVD_Multi(0,0,n-1,m-1,dp,dir));

        // System.out.println(mazePath_HVD_DP(0,0,n-1,m-1,dp,dir));
        // System.out.println(mazePath_HVD_Multi_DP(0,0,n-1,m-1,dp,dir));

        // int n = 1;
        // int[] dp = new int[n+1];
        // System.out.println(boardPath(0,n,dp));
        // System.out.println(boardPath_DP(0,n,dp));
        // System.out.println(boardPath_Opti2(n));

        int n = 5;
        int k = 3;
        int[][] dp = new int[n+1][k+1];
        for(int[] a : dp) Arrays.fill(a,0);
        System.out.println(countWays_DP(n,k,dp));

        // print(dp);
        print2D(dp);
    }

    //StringSet.=====================================================================

    //Longest Plaindromic Subsequence
    public static int LPSS(String str,int i,int j,int[][] dp){
        if(i >= j){
            return dp[i][j] = (i==j?1:0);
        }

        if(dp[i][j]!=0) return dp[i][j];

        if(str.charAt(i) == str.charAt(j)) return dp[i][j] = LPSS(str,i+1,j-1,dp) + 2;
        else return dp[i][j] = Math.max(LPSS(str,i+1,j,dp),LPSS(str,i,j-1,dp));
    }

    public static int LPSS_DP(String str,int i,int j,int[][] dp){
        for(int gap =0; gap<str.length();gap++){
            for(i=0,j=gap;j<str.length();i++,j++){
                if(i >= j){
                    dp[i][j] = (i==j?1:0);
                    continue;
                }
        
                if(str.charAt(i) == str.charAt(j)) return dp[i][j] = dp[i+1][j-1] + 2;
                else return dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }

        return dp[0][str.length()-1];  
    }

    
    public static String LPSS_SDP(String str,int i,int j,int[][] dp){
        String[][] sdp = new String[str.length()][str.length()];
        for(String[] s: sdp) Arrays.fill(s,"");

        for(int gap =0; gap<str.length();gap++){
            for(i=0,j=gap;j<str.length();i++,j++){
                if(i >= j){
                    dp[i][j] = (i==j?1:0);
                    if(dp[i][j]==1) sdp[i][j] = str.charAt(i);
                    continue;
                }
        
                if(str.charAt(i) == str.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                    sdp[i][j] = str.charAt(i) + sdp[i+1][j-1] + str.charAt(j);
                }
                else{
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                    if(dp[i][j] == dp[i+1][j]) sdp[i][j] = sdp[i+1][j];
                    else sdp[i][j] = sdp[i][j-1];
                }
            }
        }

        return sdp[0][str.length()-1];  
    }
    
    //Leetcode 516
    public static int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        int ans = LPSS(s,0,s.length()-1,dp);
        
        
        return ans;
    }

    
    //Longest Plaindromic Substring
    // Leetcode 5, 647
    public static int[] LPSS_DP(String str){
        boolean[][] dp = new boolean[s.length()][s.length()];
        
        int count = 0;
        int si = 0;
        int ei =0;
        int length = 0;

        for(int gap =0; gap<str.length();gap++){
            for(i=0,j=gap;j<str.length();i++,j++){

                if(gap==0){
                    dp[i][j] = true;
                }else if(gap == 1 && str.charAt(i) == str.charAt(j)) dp[i][j] = true;
                else  dp[i][j] = str.charAt(i) == str.charAt(j) && dp[i+1][j-1];
            
                if(dp[i][j]){
                    count++;
                    if(gap + 1 > len){
                        len = gap + 1;
                        si = i;
                        ei = j;
                    }
                }
            }
        }

        return dp;  
    }

    // https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1
    public static int countPS(String str,int i,int j,int[][] dp){
        if(i >= j){
            return dp[i][j] = (i==j?1:0);
        }

        int a = countPS(str,i+1,j-1,dp);
        int b = countPS(str,i+1,j,dp);
        int c = countPS(str,i,j-1,dp);

        if(str.charAt(i)==str.charAt(j)){
            return dp[i][j] = b + c + 1
        }else
           return dp[i][j] = b + c - a;
    }

    public static int countPS(String str,int i,int j,int[][] dp){
        
        for(int gap =0; gap<str.length();gap++){
            for(i=0,j=gap;j<str.length();i++,j++){
                
                if(i >= j){
                    dp[i][j] = (i==j?1:0);
                    continue;
                }
        
                int a = dp[i+1][j-1];//countPS(str,i+1,j-1,dp);
                int b = dp[i+1][j];//countPS(str,i+1,j,dp);
                int c = dp[i][j-1];//countPS(str,i,j-1,dp);
        
                if(str.charAt(i)==str.charAt(j)){
                    dp[i][j] = b + c + 1
                }else
                   dp[i][j] = b + c - a;     
            }
        }

        return dp[0][str.length()-1];
    }
    
    public static int numDistinct(String s, int n,String t,int m,int[][] dp){
        if(n < m) return dp[n][m] = 0;
        if(m == 0) return dp[n][m] = 1;

        if(dp[n][m] != -1) return dp[n][m];

        int count=0;
        
        if(s.charAt(n-1) == t.charAt(m-1)) count+= numDistinct(s,n-1,t,m-1,dp);
        count+=numDistinct(s,n-1,t,m,dp);

        return dp[n][m] = count;
    }



    public static int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int ans = numDistinct(s, n,t, m, dp);
        
        // for (int[] d : dp) {
        //     for (int ele : d) {
        //         System.out.print(ele + " ");
        //     }
        //     System.out.println();
        // }
        return ans;
    }





    
    public static void twoPointerSet(){
        // fibo();
        mazePath();
    }
    
    
    
    public static void solve(){
        twoPointerSet();
    }
    
    public static void main(String... args){
        solve();
    }
}