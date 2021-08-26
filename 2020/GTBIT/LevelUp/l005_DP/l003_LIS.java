import java.util.Arrays;

public class l003_LIS {

    // LIS -> Left To right
    public int[] LIS_LR(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return dp;
    }

    // LIS -> Right to Left  === LDS
    public int[] LIS_RL(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        int maxLen = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return dp;
    }

    public int LongestBitonicSequence(int[] nums) {
        int[] LIS = LIS_LR(nums);
        int[] LDS = LIS_RL(nums);

        int n = nums.length, maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, LIS[i] + LDS[i] - 1);
        }

        return maxLen;
    }

    // ===========================================================================

    // LDS -> left to Right  
    public int[] LDS_LR(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return dp;
    }

    // LDS -> right to left === LIS
    public int[] LDS_RL(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        int maxLen = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return dp;
    }

    public int LongestBitonicSequence_02(int[] nums) {
        int[] LIS = LDS_LR(nums);
        int[] LDS = LDS_RL(nums);

        int n = nums.length, maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, LIS[i] + LDS[i] - 1);
        }

        return maxLen;
    }

    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
    public int maxSumIS(int arr[], int n) {
        return 1;
    }

    // https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence1857/1
    public static int maxSumBS(int arr[], int n) {
        return 1;
    }

    public static int MaximumNonOverlapingBridges(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            return a[0] - b[0];
        });

        int n = points.length, maxLen = 0;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            {
                dp[i] = 1;
                for (int j = i - 1; j >= 0; j--) {
                    if (points[i][1] > points[j][1]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }

                maxLen = Math.max(maxLen, dp[i]);
            }
        }

        return maxLen;

    }

    // minimum Number of deletion to be performed to make array sorted (array
    // contain -1e7 <= ele <= 1e7)

    public static int minDeletion(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] >= arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return n - maxLen;
    }

    public static void main(String[] args) {

    }
}