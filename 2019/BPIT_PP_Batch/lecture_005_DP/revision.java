public class revision{
    
    public static void print(int[] arr){
        for(int ele: arr)
        System.out.print(ele+" ");

        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] ar: arr) print(ar);
        System.out.println();
    }


    public static int mazePath_Multi(int sr,int sc,int er,int ec,int[][] dp,int[][] dir){
        if(sr==er && sc == ec){
            return dp[sr][sc] = 1;
        }

        if(dp[sr][sc]!=0) return dp[sr][sc];
        
        int count = 0;
        for(int d = 0; d<dir.length;d++){   
           for(int jump = 1;jump <= Math.max(er,ec) ; jump++){
               int r = sr + jump*dir[d][0];
               int c = sc + jump*dir[d][1];

               if(r>=0 && c>=0 && r <= er && c<=ec)
                  count+=mazePath_Multi(r,c,er,ec,dp,dir);
               else break;
           }
        }

        return dp[sr][sc] = count;
    }

    public static int mazePath_MultiDP(int sr,int sc,int er,int ec,int[][] dp,int[][] dir){
               
        for(sr = er ; sr>=0 ; sr--){
            for(sc = ec; sc>=0 ; sc--){
                if(sr==er && sc == ec){
                   dp[sr][sc] = 1;
                   continue;
                }
        
                
                int count = 0;
                for(int d = 0; d<dir.length;d++){   
                   for(int jump = 1;jump <= Math.max(er,ec) ; jump++){
                       int r = sr + jump*dir[d][0];
                       int c = sc + jump*dir[d][1];
        
                       if(r>=0 && c>=0 && r <= er && c<=ec)
                          count+=dp[r][c];
                       else break;
                   }
                }
        
                dp[sr][sc] = count;
            }
        }

        return dp[0][0];
    }

    public static int boardPath(int sp,int ep,int[] dp){
        if(sp==ep){
            return dp[sp] = 1;
        }

        int count = 0;
        for(int dice = 1;dice <= 6 && sp+dice <=ep;dice++){
            count+= boardPath(sp+dice,ep,dp);
        }

        return dp[sp] = count;
    }

    public static int boardPath_DP(int sp,int ep,int[] dp){

        for(sp=ep; sp >= 0 ; sp--){
            if(sp==ep){
                dp[sp] = 1;
                continue;
            }
    
            int count = 0;
            for(int dice = 1;dice <= 6 && sp+dice <= ep;dice++){
                count+= dp[sp + dice];//boardPath(sp+dice,ep,dp);
            }
    
            dp[sp] = count;            
        }

        return dp[0];
    }
    
    public int minCostClimbingStairs(int[] cost,int n,int[] dp){
          if(n<=1){
            return dp[n] = cost[n]; 
          }

          if(dp[n]!=0) return dp[n];

          int a = minCostClimbingStairs(cost, n-1,dp);
          int b = minCostClimbingStairs(cost, n-2,dp);

          return Math.min(a,b) + (n != cost.length ? cost[n] : 0);
    }

    public int minCostClimbingStairs(int[] cost,int n,int[] dp){
        
        int N = n;
        for(n = 0; n <= N ; n++){
            if(n<=1){
                dp[n] = cost[n];
                continue;
              }
    
              int a = dp[n-1]; //minCostClimbingStairs(cost, n-1,dp);
              int b = dp[n-2]; //minCostClimbingStairs(cost, n-2,dp);
    
              Math.min(a,b) + (n != cost.length ? cost[n] : 0);
        
        }

        return dp[N];
    }

    //geeksforgeeks.org/friends-pairing-problem/
    public static int frindsPairing(int n,int[] dp){
        if(n<=1) {
            return dp[n] = 1;
        }

        if(dp[n]!=0) return dp[n];

        int single = frindsPairing(n-1,dp);
        int pairUp = frindsPairing(n-2,dp) * (n-1);
        
        return dp[n] = single + pairUp;
    }

    public static int frindsPairing_DP(int n,int[] dp){
        int N = n;
        for(n=0;n<=N;n++){
            if(n<=1) {
                dp[n] = 1;
                continue;
            }
    
            int single = dp[n-1];
            int pairUp = dp[n-2] * (n-1);
            
            dp[n] = single + pairUp;
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

    public static void Basic(){   
        // int n=5;
        // int[][] dp =new int[n][n];
        // int[][] dir = {{1,0},{0,1},{1,1}};
        // System.out.println(mazePath_Multi(0,0,n-1,n-1,dp,dir));
        // System.out.println(mazePath_MultiDP(0,0,n-1,n-1,dp,dir));
        
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