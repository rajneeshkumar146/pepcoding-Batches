
//256
public int minCost(int[][] costs) {
        
    if(costs.length == 0 || costs[0].length == 0) return 0;
    
    int n = costs.length;
    for(int i=1;i<n;i++){
        costs[i][0] += Math.min(costs[i-1][1],costs[i-1][2]);
        costs[i][1] += Math.min(costs[i-1][0],costs[i-1][2]);
        costs[i][2] += Math.min(costs[i-1][1],costs[i-1][0]);
    }
    
    return Math.min(Math.min(costs[n-1][0],costs[n-1][1]),costs[n-1][2]);
}

// 276

public int numWays(int n, int k) {
    if(n==0 || k == 0) return  0;
    
    int[] dp = new int[n+1];
    
    for(int i = 1;i<=n;i++){
        if(i == 1) dp[i] = k;
        else if(i == 2) dp[i] = k*k;
        else{
            dp[i] = (dp[i-1] + dp[i-2]) * (k-1);
        }
    }
    
    return dp[n];
}

// 121
public int maxProfit(int[] arr) {
    if(arr.length==0) return 0;

    int T0 = 0;
    int T1 = -(int)1e9;

    for(int val : arr){
        T0 = Math.max(T0,T1 + val);
        T1 = Math.max(T1,0 - val);
    }

    return T0;
}

//122
public int maxProfit(int[] arr) {
    if(arr.length==0) return 0;

    int T0 = 0;
    int T1 = -(int)1e9;

    for(int val : arr){
        T0 = Math.max(T0,T1 + val);
        T1 = Math.max(T1,T0 - val);
    }

    return T0;        
}

//309
public int maxProfit(int[] arr) {
    if(arr.length==0) return 0;

    int T0 = 0;
    int T1 = -(int)1e9;
    int T2 = 0;

    for(int val : arr){
        int temp = T0;
        T0 = Math.max(T0,T1 + val);
        T1 = Math.max(T1,T2 - val);
        T2 = temp;
    }

    return T0;        
}

//123
public int maxProfit(int[] arr) {
    if(arr.length==0) return 0;

    int T10 = 0;   // ek transaction pe holding zero 
    int T11 = -(int)1e9; // ek transaction pe holding ek ki hai.
   
    int T20 = 0;// do transaction pe holding zero 
    int T21 = -(int)1e9; // do transaction pe holding ek ki hai.
    
    for(int val : arr){
        T20 = Math.max(T20,T21 + val);
        T21 = Math.max(T21,T10 - val);

        T10 = Math.max(T10,T11 + val);
        T11 = Math.max(T11, 0 - val);
    }

    return T20;        
}

//188
public int maxProfit(int k, int[] prices) {
    if(prices.length==0) return 0;
    
    if(k > (prices.length >>> 1 )){
        
    int T0 = 0;
    int T1 = -(int)1e9;

    for(int val : prices){
        T0 = Math.max(T0,T1 + val);
        T1 = Math.max(T1,T0 - val);
    }

    return T0; 
    }
    
    int[] Ti0 = new int[k + 1];
    int[] Ti1 = new int[k + 1];
    Arrays.fill(Ti1,-(int)1e9);

    for(int val : prices){
        for(int K = k;K > 0; K--){
            Ti0[K] = Math.max(Ti0[K],Ti1[K] + val);
            Ti1[K] = Math.max(Ti1[K],Ti0[K - 1] - val);
        }
    }

    return Ti0[k];
}

//714
public int maxProfit(int[] prices, int fee) {
        
    int T0 = 0;
    int T1 = -(int)1e9;

    for(int val : prices){
        int T0_prev = T0; 
        T0 = Math.max(T0,T1 + val);
        T1 = Math.max(T1,T0_prev - val - fee);
    }

    return T0; 
        
}

//https://practice.geeksforgeeks.org/problems/consecutive-1s-not-allowed1912/1
long countStrings(int n) {
    if(n == 0) return n;
    
    long mod = (int)1e9 + 7;
    long zeros = 1;
    long onces = 1;
    for(int i = 1;i < n;i++){
        long prevZero = zeros;
        zeros = (zeros%mod + onces%mod)%mod;
        onces = prevZero%mod;
    }
    
    return (zeros%mod + onces%mod)%mod;
}

//https://www.geeksforgeeks.org/count-possible-ways-to-construct-buildings/


//887
public int superEggDrop(int K, int N,int[][] dp) {
    if(N <= 2) return dp[N][K] = N;
    if(K == 1) return dp[N][K] =N;
    
    if(dp[N][K]!=0) return dp[N][K];
    
    int ans = (int)1e8, lo = 1, hi = N;
    while(lo <= hi){
        
        int mid = (lo + hi) >> 1;
        int EggBreak = superEggDrop(K-1,mid-1,dp);
        int EggNotBreak = superEggDrop(K,N- mid,dp);
        
        if(EggBreak < EggNotBreak) lo = mid + 1;
        else hi = mid - 1;
        
        ans = Math.min(ans, 1 + Math.max(EggBreak,EggNotBreak));
    }
    
    return dp[N][K] = ans;
}

public int superEggDrop(int K, int N) {
    int[][] dp = new int[N+1][K+1];
    
    return superEggDrop(K,N,dp);
}


