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

    public static void mazePath(){
        int n = 3;
        int m = 3;

        int[][] dp = new int[n][m];
        int[][] dir = {{1,0},{0,1},{1,1}};

        // System.out.println(mazePath_HVD(0,0,n-1,m-1,dp,dir));
        // System.out.println(mazePath_HVD_Multi(0,0,n-1,m-1,dp,dir));

        // System.out.println(mazePath_HVD_DP(0,0,n-1,m-1,dp,dir));
        System.out.println(mazePath_HVD_Multi_DP(0,0,n-1,m-1,dp,dir));


        print2D(dp);
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