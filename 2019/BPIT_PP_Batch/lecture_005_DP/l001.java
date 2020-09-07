import java.util.LinkedList;
import java.util.ArrayList;
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
                ll.addFirst(ll.getFirst()*2 - lval)
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
        int ans = minCostClimbingStairs_DP(cost,dp.length,dp);
        return ans;
    }


    public static int boardPath_Moves(int si,int ei,int[] moves){
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
           
        int n = 10;
        int[] dp = new int[n+1];
        // System.out.println(boardPath(0,n,dp));
        System.out.println(boardPath_DP(0,n,dp));
        
        print(dp);
        // print2D(dp);
    }


    public static void solve(){
        Basic();
    }

    public static void main(String[] args){
        solve();
    }
}