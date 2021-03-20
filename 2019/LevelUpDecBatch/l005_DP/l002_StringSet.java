import java.util.Arrays;

public class l002_StringSet {
    // 516
    public static int longestPalindromeSubseq(String s, int i, int j, int[][] dp) {
        if (i >= j) {
            return dp[i][j] = (i == j) ? 1 : 0;
        }

        if (dp[i][j] != -1)
            return dp[i][j];

        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] = longestPalindromeSubseq(s, i + 1, j - 1, dp) + 2;

        return dp[i][j] = Math.max(longestPalindromeSubseq(s, i + 1, j, dp), longestPalindromeSubseq(s, i, j - 1, dp));
    }

    public static int longestPalindromeSubseq_DP(String s, int I, int J, int[][] dp) {
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {

                if (i == j) {
                    dp[i][j] = 1;
                    continue;
                }

                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[I][J];
    }

    public static String longestPalindromeSubseq_String(String s) {
        int n = s.length();
        String[][] dp = new String[n][n];
        for (String[] d : dp)
            Arrays.fill(d, "");

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (i == j) {
                    dp[i][j] = s.charAt(i) + "";
                    continue;
                }
                
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = s.charAt(i) + dp[i + 1][j - 1] + s.charAt(j);
                else {
                    dp[i][j] = (dp[i + 1][j].length() > dp[i][j - 1].length()) ? dp[i + 1][j] : dp[i][j - 1];
                }
            }
        }

        return dp[0][n - 1];
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // for(int[] d : dp) Arrays.fill(d,-1);
        return longestPalindromeSubseq_DP(s, 0, n - 1, dp);
    }
}