import java.util.*;

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
        int[] arr = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14, 7 };
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

    // 673
    // https://leetcode.com/problems/number-of-longest-increasing-subsequence/

    public int findNumberOfLIS(int[] nums) {

        int n = nums.length;
        int[] count = new int[n];
        int[] dp = new int[n];
        int maxLen = 0, maxCount = 0;

        for (int i = 0; i < n; i++) {
            count[i] = 1;
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 == dp[i])
                        count[i] += count[j];
                    else if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }
                }

            }

            if (maxLen == dp[i])
                maxCount += count[i];
            else if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxCount = count[i];
            }
        }

        return maxCount;
    }

    // Mimimum deletetion required to make array sorted.
    public static int minDeletion(int[] arr) {
        int n = arr.length, maxLen = 0;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return n - maxLen;
    }

    // https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
    public static int buildingBridges(int[][] arr) {
        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        int n = arr.length, maxLen = 0;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j][1] < arr[i][1] && arr[j][0] < arr[i][0]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    private static int binarySearch(List<Integer> list, int num) {
        int li = 0, ri = list.size();
        while (li < ri) {
            int mid = (li + ri) / 2;
            if (num <= list.get(mid))
                ri = mid;
            else
                li = mid + 1;
        }

        return li;
    }

    // O(NlogN)
    public static int LIS_BS(int[] arr) {
        int n = arr.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int idx = binarySearch(list, arr[i]);
            if (idx == list.size())
                list.add(arr[i]);
            else
                list.set(idx, arr[i]);
        }

        return list.size();
    }

}
