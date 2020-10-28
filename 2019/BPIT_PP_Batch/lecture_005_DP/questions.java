
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

