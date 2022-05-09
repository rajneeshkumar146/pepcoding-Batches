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

    public static int LIS_DP(int[] arr, int[] dp) {

    }

    public static int LDS_DP(int[] arr, int[] dp) {

    }

    // https://www.geeksforgeeks.org/maximum-sum-increasing-subsequence-dp-14/

    // https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1/
    public int LongestBitonicSequence(int[] nums) {
        
    }

}
