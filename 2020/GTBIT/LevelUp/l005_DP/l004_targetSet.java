import java.util.*;

public class l004_targetSet {

    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] ar : dp) {
            display(ar);
        }
        System.out.println();
    }

    public static int permutation_memo(int[] arr, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 1;
        }

        if (dp[tar] != -1)
            return dp[tar];
        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0) {
                count += permutation_memo(arr, tar - ele, dp);
            }
        }

        return dp[tar] = count;
    }

    public static int permutation_tabu(int[] arr, int Tar, int[] dp) {
        for (int tar = 0; tar <= Tar; tar++) {
            if (tar == 0) {
                dp[tar] = 1;
                continue;
            }
            int count = 0;
            for (int ele : arr) {
                if (tar - ele >= 0) {
                    count += dp[tar - ele];
                }
            }
            dp[tar] = count;
        }

        return dp[Tar];
    }

    public static int combination_memo(int[] arr, int tar, int n, int[][] dp) {
        if (tar == 0) {
            return dp[n][tar] = 1;
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (tar - arr[i] >= 0) {
                count += combination_memo(arr, tar - arr[i], i + 1, dp);
            }
        }

        return dp[n][tar] = count;
    }

    public static int combination_tabu(int[] arr, int Tar) {
        int[] dp = new int[Tar + 1];
        dp[0] = 1;
        for (int ele : arr) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        display(dp);
        return dp[Tar];
    }

    public static void coinSet() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10, n = arr.length;

        // int[] dp = new int[tar + 1];
        // Arrays.fill(dp, -1);
        // System.out.println(permutation_tabu(arr, tar, dp));
        // display(dp);

        int[][] dp = new int[n + 1][tar + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        System.out.println(combination_tabu(arr, tar));
        // display2D(dp);
    }

    // 322
    public int coinChange(int[] arr, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 0;
        }

        if (dp[tar] != (int) 1e9)
            return dp[tar];

        int minCount = (int) 1e8;
        for (int ele : arr) {
            if (tar - ele >= 0) {
                minCount = Math.min(minCount, coinChange(arr, tar - ele, dp) + 1);
            }
        }

        return dp[tar] = minCount >= (int) 1e8 ? (int) 1e8 : minCount;

    }

    public int coinChange_DP(int[] arr, int Tar, int[] dp) {
        for (int tar = 0; tar <= Tar; tar++) {
            if (tar == 0) {
                dp[tar] = 0;
                continue;
            }

            int minCount = (int) 1e8;
            for (int ele : arr) {
                if (tar - ele >= 0) {
                    minCount = Math.min(minCount, dp[tar - ele] + 1);
                }
            }

            dp[tar] = minCount >= (int) 1e8 ? (int) 1e8 : minCount;
        }

        return dp[Tar];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, (int) 1e9);
        int ans = coinChange(coins, amount, dp);
        return ans >= (int) 1e8 ? -1 : ans;
    }

    // https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
    // leetcode 377

    public static int targetSum(int[] arr, int tar, int n, int[][] dp) {
        if (tar == 0 || n == 0) {
            return dp[n][tar] = (tar == 0 ? 1 : 0);
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];

        int count = 0;
        if (tar - arr[n - 1] >= 0)
            count += targetSum(arr, tar - arr[n - 1], n - 1, dp);
        count += targetSum(arr, tar, n - 1, dp);

        return dp[n][tar] = count;
    }

    public static int targetSum_dp(int[] arr, int Tar, int N, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (tar == 0 || n == 0) {
                    dp[n][tar] = (tar == 0 ? 1 : 0);
                    continue;
                }

                int count = dp[n - 1][tar];
                if (tar - arr[n - 1] >= 0)
                    count += dp[n - 1][tar - arr[n - 1]];

                dp[n][tar] = count;
            }
        }

        return dp[N][Tar];
    }

    public static void targetSum() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10, n = arr.length;
        int[][] dp = new int[n + 1][tar + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        System.out.println(targetSum_dp(arr, tar, arr.length, dp));

        display2D(dp);
    }

    // 0-1Knapsack
    public static int knapSack(int W, int wt[], int val[], int n, int[][] dp) {
        if (n == 0 || W == 0) {
            return dp[n][W] = 0;
        }

        if (dp[n][W] != -1)
            return dp[n][W];

        int maxValue = knapSack(W, wt, val, n - 1, dp);
        if (W - wt[n - 1] >= 0)
            maxValue = Math.max(maxValue, knapSack(W - wt[n - 1], wt, val, n - 1, dp) + val[n - 1]);

        return dp[n][W] = maxValue;
    }

    public static int knapSack(int W, int wt[], int val[], int n) {
        int[][] dp = new int[n + 1][W + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return knapSack(W, wt, val, n, dp);
    }

    static int knapSack(int N, int W, int val[], int wt[]) {
        int[] dp = new int[W + 1];
        for (int w = 1; w <= W; w++) {
            for (int i = 0; i < N; i++) {
                if (w - wt[i] >= 0)
                    dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
            }
        }

        return dp[W];
    }

    // 416
    // -1 : not defined, 1 : true, 0 : false.
    public int canPartition(int[] nums, int n, int tar, int[][] dp) {
        if (n == 0 || tar == 0) {
            return dp[n][tar] = (tar == 0 ? 1 : 0);
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];

        boolean res = false;
        if (tar - nums[n - 1] >= 0)
            res = res || canPartition(nums, n - 1, tar - nums[n - 1], dp) == 1;
        res = res || canPartition(nums, n - 1, tar, dp) == 1;

        return dp[n][tar] = (res ? 1 : 0);
    }

    public boolean canPartition(int[] nums) {
        int sum = 0, n = nums.length;
        for (int ele : nums)
            sum += ele;

        if (n == 0 || sum % 2 != 0)
            return false;
        int[][] dp = new int[n + 1][sum / 2 + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return canPartition(nums, n, sum / 2, dp) == 1;
    }

    // 494
    public int findTargetSumWays(int[] nums, int n, int target, int sum, int[][] dp) {
        if (n == 0) {
            return dp[n][sum] = (target == sum) ? 1 : 0;
        }

        if (dp[n][sum] != -1)
            return dp[n][sum];

        int count = 0;
        count += findTargetSumWays(nums, n - 1, target, sum - nums[n - 1], dp);
        count += findTargetSumWays(nums, n - 1, target, sum + nums[n - 1], dp);

        return dp[n][sum] = count;
    }

    public int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 0)
            return 0;
        int sum = 0;
        for (int ele : nums)
            sum += ele;

        int n = nums.length;
        if (sum < target || target < -sum)
            return 0;
        int[][] dp = new int[n + 1][2 * sum + 2];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = findTargetSumWays(nums, n, target + sum, sum, dp);
        return ans;
    }

    public static void main(String[] args) {
        targetSum();
    }

}