public class l003_LIS {

    // LIS left to right.
    public static int LIS_LR(int[] arr, int i, int[] dp) {
        if (dp[i] != 0)
            return dp[i];
        int longestLen = 1;
        for (int j = i - 1; j >= 0; j--) {
            if (arr[j] < arr[i]) {
                int len = LIS_LR(arr, j, dp);
                longestLen = Math.max(longestLen, len + 1);
            }
        }

        return dp[i] = longestLen;
    }

    public static int LIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            len = Math.max(len, LIS_LR(nums, i, dp));
        }

        return len;
    }

    // LR
    public int lengthOfLIS(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        return len;
    }

    // minimum Number of deletion to be performed to make array sorted (array
    // contain -1e7 <= ele <= 1e7)
    public static int minDeletion(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        return n - len;
    }

    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
    public int maximumSum_IS(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int maxIncrSum = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }

            maxIncrSum = Math.max(maxIncrSum, dp[i]);
        }

        return maxIncrSum;
    }

    // Maximum Sum Increasing Subsequence of Maximum Length.
    public static int maximumSumLIS(int[] arr) {

        return 0;
    }

    public static void main(String[] args) {

    }
}