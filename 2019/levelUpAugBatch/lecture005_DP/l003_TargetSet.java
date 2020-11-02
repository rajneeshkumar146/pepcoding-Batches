import java.util.Arrays;
public class l003_TargetSet{
    public static void print(int[] arr){
        for(int ele: arr)
          System.out.print(ele + " "); 
        
        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] a: arr)
          print(a);

        System.out.println();
    }

    public static int coinChangePermutation(int[] arr,int tar,int[] dp){
        if(tar == 0){
            return dp[tar] = 1;
        }
        
        if(dp[tar]!=0) return dp[tar];

        for(int ele : arr){
            if(tar - ele >= 0){
                dp[tar] += coinChangePermutation(arr,tar-ele,dp);
            }
        }
        return dp[tar];
    }
 
    public static int coinChangePermutationDP(int[] arr,int Tar,int[] dp){
        dp[0] = 1;
        for(int tar = 1; tar <= Tar; tar++){
            for(int ele : arr){
                if(tar - ele >= 0){
                    dp[tar] += dp[tar-ele];
                }
            }            
        }

        return dp[Tar];
    }

    public static int coinChangeCobinationDP(int[] arr,int Tar,int[] dp){
        dp[0] = 1;
        for(int ele : arr){
            for(int tar = ele; tar <= Tar; tar++){
                    dp[tar] += dp[tar-ele];
            }            
        }

        return dp[Tar];
    }

    //Leetcode 377  -> coinChangePermutationDP

    //Leetcode 322

    public int minCoinsRequired(int[] arr, int tar,int[] dp) {
        if(tar == 0){
            return 0;
        }

        if(dp[tar] != -1) return dp[tar];

        int minCoin = (int)1e9;
        for(int ele : arr){
            if(tar - ele >= 0 ){
                int val = minCoinsRequired(arr,tar - ele,dp);
                if(val != (int)1e9 && val + 1 < minCoin)
                   minCoin = val + 1;
            }
        }

        return dp[tar] = minCoin;
    }

    public int minCoinsRequiredDP(int[] arr, int Tar,int[] dp){
        dp[0] = 0;
        for(int tar = 1; tar<=Tar;tar++){
            int minCoin = (int)1e9;
            for(int ele : arr){
                if(tar - ele >= 0 ){
                    int val = dp[tar - ele];
                    if(val != (int)1e9 && val + 1 < minCoin)
                       minCoin = val + 1;
                }
            }
            dp[tar] = minCoin;
        }
        return dp[Tar];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp=new int[amount+1];
        Arrays.fill(dp,-1);

        int ans = minCoinsRequiredDP(coins,amount,dp);
        return ans != (int)1e9? ans: -1;
    }

    //https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

    public static int targetSum(int[] arr,int idx,int tar,int[][] dp){
        if(tar == 0 || idx == arr.length){
            return dp[idx][tar] = tar == 0 ? 1 : 0;
        }

        if(dp[idx][tar] != -1) return dp[idx][tar];

        int count = 0;
        if(tar - arr[idx] >= 0)
          count += targetSum(arr,idx+1,tar - arr[idx], dp);
        count += targetSum(arr,idx+1,tar, dp);

        return dp[idx][tar] = count;
    }

    public static int targetSumDP(int[] arr,int Idx,int Tar,int[][] dp){
        for(int idx = arr.length;idx >= 0;idx--){
            for(int tar = 0;tar<=Tar;tar++){
                if(tar == 0 || idx == arr.length){
                    dp[idx][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }
                
                int count = 0;
                if(tar - arr[idx] >= 0)
                   count += dp[idx + 1][tar-arr[idx]];
                count += dp[idx + 1][tar];
               
                dp[idx][tar] = count;
            }
        }

        return dp[Idx][Tar];
    }


    public static int targetSum2(int[] arr,int n,int tar,int[][] dp){
        if(tar == 0 || n == 0){
            return dp[n][tar] = (tar == 0) ? 1 : 0;
        }

        if(dp[n][tar] != -1) return dp[n][tar];

        int count = 0;
        if(tar - arr[n - 1] >= 0)
          count += targetSum2(arr,n - 1,tar - arr[n - 1], dp);
        count += targetSum2(arr,n - 1,tar, dp);

        return dp[n][tar] = count;
    }

    public static int targetSumDP2(int[] arr,int N,int Tar,int[][] dp){
        for(int n = 0;n<=N;n++){
            for(int tar = 0;tar<=Tar;tar++){
                if(tar == 0 || n == 0){
                    dp[n][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }
                
                int count = 0;
                if(tar - arr[n - 1] >= 0)
                   count += dp[n-1][tar-arr[n-1]];
                count += dp[n-1][tar];
               
                dp[n][tar] = count;
            }
        }

        return dp[N][Tar];
    }

    public static boolean targetSumPath(int[] arr,int n,int tar,int[][] dp,String psf){
        if(tar == 0 || n == 0){
           if(tar == 0){
            System.out.println(psf);
            return true;
           }
           return false;
        }

        boolean res = false;
        if(tar - arr[n - 1] >= 0 && dp[n-1][tar - arr[n-1]] > 0)
          res = res || targetSumPath(arr,n - 1,tar - arr[n - 1], dp, psf + arr[n-1] + ",");
        if(dp[n-1][tar] > 0) res = res || targetSumPath(arr,n - 1,tar, dp,psf);

        return res;
    }

    public static void targetSum(){
        int[] arr = {2,3,5,7};
        int tar = 10;

        int[][] dp = new int[arr.length+1][tar+1];
        // for(int[] d: dp) Arrays.fill(d,-1);
        // int ans = targetSumDP(arr,0,tar,dp);

        int ans = targetSumDP2(arr,arr.length,tar,dp);
        System.out.println(targetSumPath(arr,arr.length,tar,dp,""));

        print2D(dp);
    }

    //416
    public boolean canPartition(int[] arr) {
        int N = arr.length;
        if(N==0) return false;

        int sum = 0;
        for(int ele : arr) sum+=ele;
        if(sum % 2 != 0) return false;

        int Tar = sum / 2;
        boolean[][] dp = new boolean[N + 1][Tar + 1];

        for(int n = 0;n<=N;n++){
            for(int tar = 0;tar<=Tar;tar++){
                if(tar == 0 || n == 0){
                    dp[n][tar] = (tar == 0) ? true : false;
                    continue;
                }
                
                int count = 0;
                if(tar - arr[n - 1] >= 0)
                   dp[n][tar] = dp[n][tar] || dp[n-1][tar-arr[n-1]];
                dp[n][tar] = dp[n][tar] ||  dp[n-1][tar];               
            }
        }

        return dp[N][Tar];
    }

    //https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
    public static int knapsack01(int[] weight,int[] value,int n,int tar,int[][] dp){
        if(n == 0 || tar == 0){
            return dp[n][tar] = 0;
        }

        if(dp[n][tar] != -1) return dp[n][tar];

        int maxValue = 0;
        if(tar - weight[n - 1] >= 0)
           maxValue = knapsack01(weight,val,n - 1,tar - weight[n - 1],dp) + value[n - 1];
        maxValue = Math.max(maxValue, knapsack01(weight,val,n - 1,tar,dp))
        
        return dp[n][tar] = maxValue;
    }

    //https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/
    public static int nboundedCost(int[] weight,int[] val,int Tar){
        int[] dp = new int[tar+1];
        for(int i = 0;i<weight.length;i++){   
           for(int tar = weight[i];tar <= Tar;tar++){
               dp[tar] = Math.max(dp[tar],dp[tar - weight[i]] + val[i]);
           }
        }

        return dp[Tar];
    }

    //for you -> https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
    
    // 494
    public int findTargetSumWays(int[] nums, int tar,int n,int sum,int[][] dp) {
        if(n == 0){
            return dp[n][sum] = (tar == sum)? 1: 0;
        }

        if(dp[n][sum] != -1) return dp[n][sum];

        int count = 0;
        count += findTargetSumWays(nums,tar,n-1,sum + nums[n-1],dp); // positive number
        count += findTargetSumWays(nums,tar,n-1,sum + (-nums[n-1]),dp); // negative number

        return dp[n][sum] = count;
    }
    
    
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
      
        if(n == 0) return 0; 
        
        int sum = 0;
        for(int ele : nums) sum+=ele;
        if(S > sum || S < -sum) return 0;
        int[][] dp = new int[n+1][2 * sum + 1];
        for(int[] d: dp) Arrays.fill(d,-1);
        int ans = findTargetSumWays(nums,(S + sum),n,(0 + sum),dp);

        return ans;
    }


    public static void solve(){
        targetSum();
    }


    public static void main(String[] args){
        solve();
    }
}