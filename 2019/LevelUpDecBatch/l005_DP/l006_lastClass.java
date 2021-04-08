import java.util.Arrays;
public class l006_lastClass {

    // https://www.geeksforgeeks.org/applications-of-catalan-numbers/

    // leetcode : 1216
    // length of string - longest palindromic subsequnece

    // Leetcode 95

    // leetcode 940
    public int distinctSubseqII(String S) {
        S = '$' + S;
        int n = S.length();
        int[] dp = new int[n];
        int[] lastOcc = new int[26];
        Arrays.fill(lastOcc, -1);

        int mod = (int) 1e9 + 7;

        dp[0] = 1; // for empty String.
        for (int i = 1; i < n; i++) {
            char ch = S.charAt(i);
            int idx = ch - 'a';
            dp[i] = (2 * dp[i - 1]) % mod;
            if (lastOcc[idx] != -1) {
                dp[i] = (dp[i] - dp[lastOcc[idx] - 1] + mod) % mod;
            }

            lastOcc[idx] = i;
        }

        return dp[n - 1] - 1;
    }

    // leetcode 1278
    public int[][] minChanges(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        for (int gap = 1; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 1)
                    dp[i][j] = str.charAt(i) == str.charAt(j) ? 0 : 1;
                else
                    dp[i][j] = str.charAt(i) != str.charAt(j) ? dp[i + 1][j - 1] + 1 : dp[i + 1][j - 1];
            }
        }
        return dp;
    }

    public int palindromePartition_(String str, int k, int si, int[][] dp, int[][] minChange) {
        if (str.length() - si <= k) {
            return dp[si][k] = (str.length() - si == k) ? 0 : (int) 1e9;
        }

        if (k == 1)
            return dp[si][k] = minChange[si][str.length() - 1];
        if (dp[si][k] != -1)
            return dp[si][k];

        int minAns = (int) 1e9;
        for (int i = si; i < str.length() - 1; i++) {
            int minChangesInMySet = minChange[si][i];
            int minChangesInRecSet = palindromePartition_(str, k - 1, i + 1, dp, minChange);
            if (minChangesInRecSet != (int) 1e9)
                minAns = Math.min(minAns, minChangesInRecSet + minChangesInMySet);
        }

        return dp[si][k] = minAns;
    }

    public int palindromePartition(String str, int k) {
        int[][] minChangeDP = minChanges(str);
        int[][] dp = new int[str.length()][k + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return palindromePartition_(str, k, 0, dp, minChangeDP);
    }

    // leetcode 10

}