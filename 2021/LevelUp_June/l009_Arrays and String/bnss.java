class bnss {
    // leet 121=================================================================

    public int maxProfit(int[] prices) {
        int dpi0=0;
        int dpi1=-(int)1e9;
        
        for(int i=0; i<prices.length; i++){
            // int dpi_10=dpi0;
            // int dpi_11=dpi1;
            
            dpi0=Math.max(dpi0,dpi_11+prices[i]);
            dpi1=Math.max(dpi1,0-prices[i]);
        }
        
        return dpi0;
    }


    // leet 122 =======================================================

    public int maxProfit(int[] prices) {
        int dpi0=0;
        int dpi1=-(int)1e9;
        
        for(int i=0; i<prices.length; i++){
            int dpi_10=dpi0; // dp[i-1][0];
            // int dpi_11=dpi1;
            
            dpi0=Math.max(dpi0,dpi1+prices[i]);
            dpi1=Math.max(dpi1,dpi_10-prices[i]);
        }
        
        return dpi0;
    }

    // leet 714 ====================================

    public int maxProfit(int[] prices, int fee) {
        int dpi0=0;
        int dpi1=-(int)1e9;
        
        for(int i=0; i<prices.length; i++){
            int dpi_10=dpi0; // dp[i-1][0];
            // int dpi_11=dpi1;
            
            dpi0=Math.max(dpi0,dpi1+prices[i]);
            dpi1=Math.max(dpi1,dpi_10-(prices[i]+fee));
        }
        
        return dpi0;
    }


    // leet 309 ======================================


    public int maxProfit(int[] prices) {
        // int[][] dp=new int[prices.length][2];
        
        
        int dpi0=0;
        int dpi_20=0; // dp[i-2][0];
        int dpi1=-(int)1e9;
        
        for(int i=0; i<prices.length; i++){
            int dpi_10=dpi0; // dp[i-1][0];
            // int dpi_11=dpi1;
            
            dpi0=Math.max(dpi0,dpi1+prices[i]);
            dpi1=Math.max(dpi1,dpi_20-prices[i]);
            
            dpi_20=dpi_10;
        }
        
        return dpi0;
    }


    // leet 123 ==================================================== 

    public int maxProfit(int[] prices) {
        int n=prices.length;
        
        int[][][] dp=new int[n][3][2];
        
        for(int i=0; i<n; i++){
            for(int k=0; k<3; k++){
                for(int x=0; x<2; x++){
                    if(k==0){
                        if(x==0){
                            dp[i][k][x]=0;
                        } else {
                            dp[i][k][x]=-(int)(1e9);
                        }
                    } else{
                        if(i==0){
                            if(x==0){
                                dp[i][k][x]=Math.max(0,-(int)1e9+prices[i]);
                            } else {
                                dp[i][k][x]=Math.max(-(int)(1e9),0-prices[i]);
                            }
                        } else {
                            if(x==0){
                                dp[i][k][x]=Math.max(dp[i-1][k][0],dp[i-1][k][1]+prices[i]);
                            } else {
                                dp[i][k][x]=Math.max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i]);
                            }
                        }
                    } 
                }
            }
        }
        
        return dp[n-1][2][0];
    }

    // leet 123 optimized  ====================================================== 

    public int maxProfit(int[] prices) {
        int dpi20=0;
        int dpi21=-(int)(1e9);
        int dpi10=0;
        int dpi11=-(int)(1e9);
        
        for(int i=0; i<prices.length; i++){
            int p=prices[i];
            dpi20=Math.max(dpi20,dpi21+p);
            dpi21=Math.max(dpi21,dpi10-p);
            dpi10=Math.max(dpi10,dpi11+p);
            dpi11=Math.max(dpi11,0-p);
        }
        
        return Math.max(dpi20,dpi10);
    }


    // leet 188 ===================================================== 

     public int maxProfit(int K, int[] prices) {
        int n=prices.length;
        if(n==0) return 0;
        
        int[][][] dp=new int[n][K+1][2];
        
        for(int i=0; i<n; i++){
            for(int k=0; k<=K; k++){
                for(int x=0; x<2; x++){
                    if(k==0){
                        if(x==0){
                            dp[i][k][x]=0;
                        } else {
                            dp[i][k][x]=-(int)(1e9);
                        }
                    } else{
                        if(i==0){
                            if(x==0){
                                dp[i][k][x]=Math.max(0,-(int)1e9+prices[i]);
                            } else {
                                dp[i][k][x]=Math.max(-(int)(1e9),0-prices[i]);
                            }
                        } else {
                            if(x==0){
                                dp[i][k][x]=Math.max(dp[i-1][k][0],dp[i-1][k][1]+prices[i]);
                            } else {
                                dp[i][k][x]=Math.max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i]);
                            }
                        }
                    } 
                }
            }
        }
        
        return dp[n-1][K][0];
    }
}