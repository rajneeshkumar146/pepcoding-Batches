import java.util.ArrayList;
import java.util.Arrays;

public class l003_LIS {
    // {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14};
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

    // LIS_Set

    public static int[] LIS_LR(int[] arr) {
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

        return dp;
    }

    // LDS
    public static int[] LIS_RL(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        return dp;
    }

    // https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
    public static int LBS_uphill(int[] arr) {
        int[] LIS = LIS_LR(arr);
        int[] LDS = LIS_RL(arr);

        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            len = Math.max(len, LIS[i] + LDS[i] - 1);
        }

        return len;
    }

    public static int[] LDS_LR(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        return dp;
    }

    public static int[] LDS_RL(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        return dp;
    }

    public static int LBS_downhill(int[] arr) {
        int[] LDS = LDS_LR(arr);
        int[] LIS = LDS_RL(arr);

        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            len = Math.max(len, LDS[i] + LIS[i] - 1);
        }

        return len;
    }

    // https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence/0

    // 673
    public int findNumberOfLIS(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int[] count = new int[n];

        int maxLen = 0;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxCount = count[i];
            } else if (maxLen == dp[i]) {
                maxCount += count[i];
            }
        }

        return maxCount;
    }

    public void allLIS(ArrayList<ArrayList<Integer>> mapping, int[] arr, int idx, int len, String ans) {
        if (len == 1) {
            System.out.println(ans + arr[idx]);
            return;
        }

        for (Integer i : mapping.get(len - 1)) {
            if (i < idx && arr[i] < arr[idx]) {
                allLIS(mapping, arr, i, len - 1, ans + arr[idx] + ", ");
            }
        }
    }

    public void findAllLIS(int[] arr) {
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

        ArrayList<ArrayList<Integer>> mapping = new ArrayList<>();
        for (int i = 0; i <= len; i++)
            mapping.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            mapping.get(dp[i]).add(i);
        }

        for (Integer i : mapping.get(len)) {
            allLIS(mapping, arr, i, len, "");
        }
    }

    // https://www.geeksforgeeks.org/dynamic-programming-building-bridges/

    // {{sp1,ep1},{sp2,ep2}.....}
    public static int buildingBridges(int[][] arr) {
        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0]; // this - other, default behaviour.
            // return b[0] - a[0]; // other - this, reverse default behaviour.
        });

        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j][0] < arr[i][0] && arr[j][1] < arr[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        return len;
    }

    // Leetcode 354

    public static void main(String[] args) {

    }
}