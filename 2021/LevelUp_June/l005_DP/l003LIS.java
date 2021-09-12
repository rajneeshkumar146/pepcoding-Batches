public class l003LIS {

    public static int LIS_memo(int[] arr, int ei, int[] dp) {
        if (dp[ei] != 0)
            return dp[ei];
        int maxLen = 1;
        for (int i = ei - 1; i >= 0; i--) {
            if (arr[i] < arr[ei]) {
                int recLen = LIS_memo(arr, i, dp);
                maxLen = Math.max(maxLen, recLen + 1);
            }
        }

        return dp[ei] = maxLen;
    }

    public static int LIS_Rec() {
        int[] arr = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14 };
        int[] dp = new int[arr.length];
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            maxLen = Math.max(maxLen, LIS_memo(arr, i, dp));
        }

        return maxLen;
    }

    public static int LIS_LR(int[] arr, int[] dp) {
        int n = arr.length, maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(dp[i], maxLen);
        }

        return maxLen;
    }

    public static int LIS_RL(int[] arr, int[] dp) {
        int n = arr.length, maxLen = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(dp[i], maxLen);
        }

        return maxLen;
    }

    public static int LDS_LR(int[] arr, int[] dp) {
        int n = arr.length, maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] < arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(dp[i], maxLen);
        }

        return maxLen;
    }

    public static int LDS_RL(int[] arr, int[] dp) {
        int n = arr.length, maxLen = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] < arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(dp[i], maxLen);
        }

        return maxLen;
    }

    public static int bitonicSequence(int[] arr) {
        int n = arr.length, maxLen = 0;
        int[] LIS = new int[n];
        int[] LDS = new int[n];

        LIS_LR(arr, LIS);
        LIS_RL(arr, LDS);

        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, LIS[i] + LDS[i] - 1);
        }

        return maxLen;
    }

    // https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence1857/1
    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
    // Mimimum deletetion required to make array sorted.

    public static int minDeletion(int[] arr) {
        int n = arr.length, maxLen = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] >= arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(dp[i], maxLen);
        }

        return arr.length - maxLen;
    }

    // 673
    public int findNumberOfLIS(int[] arr) {

        int n = arr.length, maxLen = 0, maxCount = 0;
        int[] dp = new int[n];
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxCount = count[i];
            } else if (dp[i] == maxLen) {
                maxCount += count[i];
            }
        }

        return maxCount;
    }

    // https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
    // {{sp,ep}...}
    public static int maxmimumBridge(int[][] arr) {
        Arrays.sort(arr, (a, b) -> {
            return a[1] - b[1];
        });

        int n = arr.length, maxLen = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i][0] > arr[j][0] && arr[i][1] > arr[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(dp[i], maxLen);
        }

        return maxLen;
    }

    //354
    

}