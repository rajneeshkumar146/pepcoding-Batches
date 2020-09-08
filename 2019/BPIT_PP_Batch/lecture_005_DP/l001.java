import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
public class l001{
    public static void print(int[] arr){
        for(int ele: arr)
        System.out.print(ele+" ");

        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] ar: arr) print(ar);
        System.out.println();
    }

    public static int fibo(int n,int[] dp){
        if(n<=1) return dp[n] = n;

        if(dp[n]!=0) return dp[n];
        
        int a = fibo(n-1,dp);
        int b = fibo(n-2,dp);
         
        return dp[n] = a+b;
    }

    public static int fibo_DP(int n,int[] dp){
        int N = n;
        for( n = 0;n <= N;n++){
            if(n<=1) 
            {
                dp[n] = n;
                continue;
            }

            int a = dp[n-1];//fibo(n-1,dp);
            int b = dp[n-2];//fibo(n-2,dp);
         
            dp[n] = a+b;
        }

        return dp[N];
    }

    public static int fibo_opti(int N){
        int a = 0;
        int b = 1;
        for(int n = 2;n<=N;n++){
            int sum = a+b;
            a=b;
            b=sum;
        }

        return b;
    }

    public static int mazePath_HVD(int sr,int sc,int er,int ec,int[][] dp){
        if(sr==er && sc==ec){
            return dp[sr][sc] = 1;
        }

        if(dp[sr][sc]!=0) return dp[sr][sc];
        int count=0;
        
        if(sc+1 <= ec) count+=mazePath_HVD(sr,sc+1,er,ec,dp);
        if(sr+1 <= er && sc+1 <= ec)count+=mazePath_HVD(sr+1,sc+1,er,ec,dp);
        if(sr+1 <= er)count+=mazePath_HVD(sr+1,sc,er,ec,dp);
        
        return dp[sr][sc] = count;
    }

    public static int mazePath_HVD_DP(int sr,int sc,int er,int ec,int[][] dp){
     
        for(sr=er;sr>=0;sr--){
            for(sc=ec;sc>=0;sc--){
                if(sr==er && sc==ec){
                    dp[sr][sc] = 1;
                    continue;
                }
        
                int count=0;
                
                if(sc+1 <= ec) count+=dp[sr][sc+1];//mazePath_HVD(sr,sc+1,er,ec,dp);
                if(sr+1 <= er && sc+1 <= ec)count+=dp[sr+1][sc+1];//mazePath_HVD(sr+1,sc+1,er,ec,dp);
                if(sr+1 <= er)count+=dp[sr+1][sc];//mazePath_HVD(sr+1,sc,er,ec,dp);
                
               dp[sr][sc] = count;
            }
        }
        return dp[0][0];
    }

    public static int mazePath_HVDJump_DP(int sr,int sc,int er,int ec,int[][] dp){
     
        for(sr=er;sr>=0;sr--){
            for(sc=ec;sc>=0;sc--){
                if(sr==er && sc==ec){
                    dp[sr][sc] = 1;
                    continue;
                }
        
                int count=0;
                
                for(int jump=1; sc+jump <= ec; jump++) count+=dp[sr][sc+jump];//mazePath_HVD(sr,sc+1,er,ec,dp);
                for(int jump=1; sr+jump <= er && sc+jump <= ec; jump++)count+=dp[sr+jump][sc+jump];//mazePath_HVD(sr+1,sc+1,er,ec,dp);
                for(int jump=1; sr+jump <= er; jump++)count+=dp[sr+jump][sc];//mazePath_HVD(sr+1,sc,er,ec,dp);
                
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
        for(int dice = 1; dice <= 6 && si + dice <= ei ; dice++){
            count+=boardPath(si+dice,ei,dp);
        }

        return dp[si] = count;
    }

    public static int boardPath_DP(int si,int ei,int[] dp){
        for(si=ei;si>=0;si--){
            if(si==ei){
                dp[si] = 1;
                continue;
            }
    
            int count=0;
            for(int dice = 1; dice <= 6 && si + dice <= ei ; dice++){
                count+=dp[si+dice]; //boardPath(si+dice,ei,dp);
            }

            dp[si] = count;
        }
        
        return dp[0];
    }

    public static int boardPath_Opti(int si,int ei,int[] dp){
        LinkedList<Integer> ll = new LinkedList<>();
        for(si=ei;si>=0;si--){
            if(si>=ei-1){
                ll.addFirst(1);
                continue;
            }

            if(ll.size()<=6) ll.addFirst(ll.getFirst()*2);
            else{
                int lval = ll.removeLast();
                ll.addFirst(ll.getFirst()*2 - lval);
            }
        }
        
        return ll.getFirst();
    }

    //----------------> Leetcode 70 similar to fibo.
    
    //leetcode 746

    public int minCostClimbingStairs(int[] cost, int n, int[] dp) {
        if(n<=1) return dp[n]=cost[n];
        if(dp[n]!=0) return dp[n];
        
        int ans = Math.min(minCostClimbingStairs(cost,n-1,dp),minCostClimbingStairs(cost,n-2,dp));

        return dp[n] = ans + (n != cost.length ? cost[n] : 0);
    }

    public int minCostClimbingStairs_DP(int[] cost, int n, int[] dp) {
        for(n=0;n<=cost.length;n++){
            if(n<=1){
                dp[n]=cost[n];
                continue;
            }

            int ans = Math.min(dp[n-1],dp[n-2]);
            dp[n] = ans + (n != cost.length ? cost[n] : 0);
        }
        
        return dp[cost.length];
    }

    public  int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        int ans = minCostClimbingStairs(cost,dp.length,dp);
        // ans = minCostClimbingStairs_DP(cost,dp.length,dp);
        return ans;
    }


    public static int boardPath_Moves(int si,int ei,int[] moves,int[] dp){
        Arrays.sort(moves);
        for(si=ei;si>=0;si--){
            if(si==ei){
                dp[si] = 1;
                continue;
            }
    
            int count=0;
            for(int i = 0; si + moves[i] <= ei ; i++){
                count+=dp[si + moves[i]]; //boardPath(si+dice,ei,dp);
            }

            dp[si] = count;
        }
        
        return dp[0];
    }

    //Leetcode 64    
    public static int minPathSum(int[][] grid,int sr,int sc,int[][] dp) {
        if(sr==grid.length-1 && sc==grid[0].length-1){
            return dp[sr][sc] = grid[sr][sc];
        }

        if(dp[sr][sc]!=0) return dp[sr][sc];

        int minCost = (int)1e8;
        if(sr + 1 < grid.length) minCost = Math.min(minCost,minPathSum(grid,sr+1,sc,dp));
        if(sc + 1 < grid[0].length) minCost = Math.min(minCost,minPathSum(grid,sr,sc+1,dp));
        
        return dp[sr][sc] = minCost + grid[sr][sc];
    }

    
    public static int minPathSum_DP(int[][] grid,int sr,int sc,int[][] dp) {
        
        for(sr=grid.length-1;sr>=0 ;sr--){
            for(sc=grid[0].length-1; sc>=0 ; sc--){
               if(sr==grid.length-1 && sc==grid[0].length-1){
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }
                int minCost = (int)1e8;
                if(sr + 1 < grid.length) minCost = Math.min(minCost,dp[sr+1][sc]);
                if(sc + 1 < grid[0].length) minCost = Math.min(minCost,dp[sr][sc+1]);
        
                dp[sr][sc] = minCost + grid[sr][sc];         
            }
        }

        return dp[0][0];
    }

    
    public static int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        // int ans= minPathSum(grid,0,0,dp);
        int ans= minPathSum_DP(grid,0,0,dp);

        return ans;
    }

    public static int goldMineProblem(int[][] coins,int sr,int sc,int[][] dp,int[][] dir){
        if(sc==coins[0].length-1){
            return dp[sr][sc] = coins[sr][sc];
        }

        if(dp[sr][sc]!=0) return dp[sr][sc];

        for(int d=0;d<3;d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r>=0 && c>=0 && r < coins.length && c < coins[0].length){
                dp[sr][sc] = Math.max(dp[sr][sc], goldMineProblem(coins,r,c,dp,dir) + coins[sr][sc]);
            }
        }

        return dp[sr][sc];
    }

    
    public static int goldMineProblem_DP(int[][] coins,int sr,int sc,int[][] dp,int[][] dir){
        for(sc = coins[0].length - 1;sc>=0;sc--){
            for(sr = coins.length - 1; sr>=0;sr--){
                if(sc==coins[0].length-1){
                    dp[sr][sc] = coins[sr][sc];
                    continue; 
                }
                
                for(int d=0;d<3;d++){
                     int r = sr + dir[d][0];
                     int c = sc + dir[d][1];
         
                     if(r>=0 && c>=0 && r < coins.length && c < coins[0].length){
                        dp[sr][sc] = Math.max(dp[sr][sc], dp[r][c]  + coins[sr][sc]);
                     }
                 }
            }
        }

        int max = 0;
        for(int i=0;i<coins.length;i++){
            max = Math.max(max, dp[i][0]);
        }

        return max;
    }

    public static int goldMineProblem(){
        int[][] coins={{10, 33, 13, 15},
                        {22, 21, 04, 1},
                        {5, 0, 2, 3},
                        {0, 6, 14, 2}};
        int[][] dp = new int[coins.length][coins[0].length];
        int[][] dir = {{-1,1},{0,1},{1,1}};

        int max = 0;
        // for(int i=0;i<coins.length;i++){
        //     max = Math.max(max, goldMineProblem(coins,i,0,dp,dir));
        // }

        max=goldMineProblem_DP(coins,0,0,dp,dir);

        print2D(dp);
        return max;
    }

    //geeksforgeeks.org/friends-pairing-problem/
    public static int frindsPairing(int n,int[] dp){
        if(n<=1){
            return dp[n] = 1;
        }

        if(dp[n]!=0) return dp[n];

        int single = frindsPairing(n-1,dp);
        int pairUp = frindsPairing(n - 2,dp) * (n-1);

        return dp[n] = single + pairUp;
    }

    public static int frindsPairing_DP(int N,int[] dp){
        
        for(int n=0;n <= N;n++){
            if(n<=1){
                dp[n] = 1;
                continue;
            }

            int single = dp[n-1];//frindsPairing(n-1,dp);
            int pairUp = dp[n-2]*(n-1);//frindsPairing(n - 2,dp) * (n-1);
            dp[n] = single + pairUp;

            // dp[n] = dp[n-1] + dp[n-2] * (n-1);
        }

        return dp[N];
    }

    public static int frindsPairing_Opti(int N,int[] dp){
        int a = 1;
        int b = 1;
        for(int i=2; i <= N;i++){
            int sum = a * (i-1) + b;
            a = b;
            b = sum;
        }

        return b;
    }

    public static void frindsPairing(){
        int n=84;
        int[] dp =new int[n+1];
        int ans = frindsPairing(n,dp);

        print(dp);
        System.out.println(ans);
    }
 
    public static int count_of_ways(int n,int k,int[][] dp){
        if(k == 1 || k == n){
            return dp[n][k] = 1;
        }

        if(dp[n][k]!=0) return dp[n][k];

        int ownSet = count_of_ways(n-1,k-1,dp);
        int partOfSet = count_of_ways(n-1,k,dp) * k;

        return dp[n][k] = ownSet + partOfSet;
    }

    public static void count_of_ways(){

    }





    public static void Basic(){
        // int n = 10;
        // int[] dp = new int[n+1];
        // System.out.println(fibo(n,dp));
        // System.out.println(fibo_DP(n,dp));
        // System.out.println(fibo_opti(n));
        
        // int n=5;
        // int[][] dp =new int[n][n];
        // System.out.println(mazePath_HVD(0,0,n-1,n-1,dp));
        // System.out.println(mazePath_HVD_DP(0,0,n-1,n-1,dp));

        // System.out.println(mazePath_HVDJump_DP(0,0,n-1,n-1,dp));
           
        // int n = 10;
        // int[] dp = new int[n+1];
        // System.out.println(boardPath(0,n,dp));
        // System.out.println(boardPath_DP(0,n,dp));
        
        // System.out.println(goldMineProblem());
        frindsPairing();

        // print(dp);
        // print2D(dp);
    }


    public static void solve(){
        Basic();
    }

    public static void main(String[] args){
        solve();
    }
}