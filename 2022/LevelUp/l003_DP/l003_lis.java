import java.util.Arrays;

public class l003_lis {

    private static int LIS_rec(int[] arr, int idx, int[] dp) {
        if (dp[idx] != 0)
            return dp[idx];

        int maxLen = 1;
        for (int i = idx - 1; i >= 0; i--) {
            if (arr[i] < arr[idx]) {
                int recAns = LIS_rec(arr, i, dp);
                maxLen = Math.max(recAns + 1, maxLen);
            }
        }

        return dp[idx] = maxLen;
    }

    public static void LIS_Rec() {
        int[] arr = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14 };
        int n = arr.length, maxLen = 0;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, LIS_rec(arr, i, dp));
        }
    }

    // left to right
    public static int LIS_DP_LR(int[] arr, int[] dp) {
        int maxLen = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static int LIS_DP_RL(int[] arr, int[] dp) {
        int maxLen = 0, n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // left to right
    public static int reverse_LDS_DP_LR(int[] arr, int[] dp) {
        int maxLen = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // Left to right
    public static int reverse_LDS_DP_RL(int[] arr, int[] dp) {
        int maxLen = 0, n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static int LIS_SumDP(int[] arr, int[] dp) {
        int maxSum = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }

            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    public static int LDS_SumDP(int[] arr, int[] dp) {
        int maxSum = 0, n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = arr[i];
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }

            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    // https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1/
    public int LongestBitonicSequence(int[] nums) {
        int n = nums.length;

        int[] LIS = new int[n];
        int[] LDS = new int[n];

        LIS_DP_LR(nums, LIS);
        LIS_DP_RL(nums, LDS);

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, LIS[i] + LDS[i] - 1);
        }
        return maxLen;
    }

    // https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence1857/1/
    public static int maxSumBS(int arr[], int n) {

        int[] LIS = new int[n];
        int[] LDS = new int[n];

        LIS_SumDP(arr, LIS);
        LDS_SumDP(arr, LDS);

        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, LIS[i] + LDS[i] - arr[i]);
        }
        return maxSum;

    }

    // reverse_longestBitonicSequnece
    public int reverse_LongestBitonicSequence(int[] nums) {
        int n = nums.length;

        int[] LIS = new int[n];
        int[] LDS = new int[n];

        reverse_LDS_DP_LR(nums, LIS);
        reverse_LDS_DP_RL(nums, LDS);

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, LIS[i] + LDS[i] - 1);
        }
        return maxLen;
    }

    // 354: https://leetcode.com/problems/russian-doll-envelopes/
    // O(n2)
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            return a[0] - b[0];
        });

        int n = envelopes.length, maxLen = 0;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (envelopes[j][1] < envelopes[i][1] && envelopes[j][0] < envelopes[i][0])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    // O(NlogN)
    public int maxEnvelopes_optimized(int[][] envelopes) {

    }

}
