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

    public static void main(String[] args){
        MCM();
    }





}