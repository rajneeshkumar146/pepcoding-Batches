public class l003_CutType{

    public static void print(int[] arr){
        for(int ele: arr)
        System.out.print(ele+" ");

        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] ar: arr) print(ar);
        System.out.println();
    }

    public static int mcm_rec(int[] arr,int si,int ei,int[][] dp){
        if(si + 1 == ei){
            return dp[si][ei] = 0;
        }

        if(dp[si][ei]!=0) return dp[si][ei];

        int myAns = (int)1e8;
        for(int cut = si + 1;cut<ei;cut++){
            int leftTree = mcm_rec(arr,si,cut,dp);
            int rightTree = mcm_rec(arr,cut,ei,dp);

            int myCost = leftTree +  arr[si] * arr[cut] * arr[ei] + rightTree;
            
            myAns = Math.min(myAns, myCost);
        }

        return dp[si][ei] = myAns;
    }

    public static int mcm_DP(int[] arr,int SI,int EI,int[][] dp){
        int n = arr.length;
        for(int gap = 1; gap < n;gap++){
            for(int si = 0, ei = gap; ei < n; si++,ei++){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    continue;
                }
        
                int myAns = (int)1e8;
                for(int cut = si + 1;cut<ei;cut++){
                    int leftTree = dp[si][cut];
                    int rightTree = dp[cut][ei];
        
                    int myCost = leftTree +  arr[si] * arr[cut] * arr[ei] + rightTree;
                    
                    myAns = Math.min(myAns, myCost);
                }
        
                dp[si][ei] = myAns;
            }
        }

        return dp[SI][EI];
    }

    public static void mcm(){
        int[] arr = {1,2,3,4,3,7,5,10,45,234};
        int n = arr.length;

        int[][] dp = new int[n][n];
        System.out.println(mcm_rec(arr,0,n-1,dp));

        print2D(dp);
    }

    // for you -> https://www.geeksforgeeks.org/minimum-maximum-values-expression/

    public static void solve(){
        mcm();
    }

    public static void main(String[] args){
        solve();
    }
}