import java.util.Arrays;

public class l002_stringSet {

    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] d : dp) {
            display(d);
        }
        System.out.println();
    }

    // 516. Longest Palindromic Subsequence
    public static int lpss(String s, int i, int j, int[][] dp) {
        if (i >= j)
            return dp[i][j] = (i == j ? 1 : 0);

        if (dp[i][j] != 0)
            return dp[i][j];

        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] = lpss(s, i + 1, j - 1, dp) + 2;
        else
            return dp[i][j] = Math.max(lpss(s, i + 1, j, dp), lpss(s, i, j - 1, dp));
    }

    public static int lpss_DP(String s, int I, int J, int[][] dp) {
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (i >= j) {
                    dp[i][j] = (i == j ? 1 : 0);
                    continue;
                }

                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;// lpss(s, i + 1, j - 1, dp) + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[I][J];
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int ans = lpss(s, 0, n - 1, dp);

        return ans;
    }

    public static int lcss(String str1, String str2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (str1.charAt(n - 1) == str2.charAt(m - 1))
            dp[n][m] = lcss(str1, str2, n - 1, m - 1, dp) + 1;
        else
            dp[n][m] = Math.max(lcss(str1, str2, n - 1, m, dp), lcss(str1, str2, n, m - 1, dp));

        return dp[n][m];
    }

    public static int lcss_DP(String str1, String str2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (str1.charAt(n - 1) == str2.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1] + 1;// lcss(str1, str2, n - 1, m - 1, dp) + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }

        return dp[N][M];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int ans = lcss(text1, text2, n, m, dp);

        return ans;
    }

    // 115

    public int numDistinct(String s, String t, int n, int m, int[][] dp) {
        if (m == 0) {
            return dp[n][m] = 1;
        }

        if (n < m) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int a = numDistinct(s, t, n - 1, m - 1, dp);
        int b = numDistinct(s, t, n - 1, m, dp);

        if (s.charAt(n - 1) == t.charAt(m - 1))
            return dp[n][m] = a + b;
        else
            return dp[n][m] = b;
    }

    public int numDistinct_DP(String s, String t, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (m == 0) {
                    dp[n][m] = 1;
                    continue;
                }

                if (n < m) {
                    dp[n][m] = 0;
                    continue;
                }

                int a = dp[n - 1][m - 1];// numDistinct(s, t, n - 1, m - 1, dp);
                int b = dp[n - 1][m];// numDistinct(s, t, n - 1, m, dp);

                if (s.charAt(n - 1) == t.charAt(m - 1))
                    dp[n][m] = a + b;
                else
                    dp[n][m] = b;
            }
        }

        return dp[N][M];
    }

    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = numDistinct(s, t, n, m, dp);

        return ans;
    }

    // 72
    public int minDistance(String word1, String word2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0 ? m : n);
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int insert = minDistance(word1, word2, n, m - 1, dp);
        int delete = minDistance(word1, word2, n - 1, m, dp);
        int replace = minDistance(word1, word2, n - 1, m - 1, dp);

        if (word1.charAt(n - 1) == word2.charAt(m - 1))
            return dp[n][m] = replace;
        else
            return dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
    }

    // cost : {insert = a, replace = b, delete = c}
    public int minDistance_02(String word1, String word2, int n, int m, int[] cost, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0 ? m * cost[0] : n * cost[2]);
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int insert = minDistance_02(word1, word2, n, m - 1, cost, dp);
        int delete = minDistance_02(word1, word2, n - 1, m, cost, dp);
        int replace = minDistance_02(word1, word2, n - 1, m - 1, cost, dp);

        if (word1.charAt(n - 1) == word2.charAt(m - 1))
            return dp[n][m] = replace;
        else
            return dp[n][m] = Math.min(Math.min(insert + cost[0], delete + cost[2]), replace + cost[1]);
    }

    public int minDistance(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = minDistance(s, t, n, m, dp);

        return ans;
    }

    // 44
    public String removeStars(String str) {
        if (str.length() == 0)
            return str;

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        int i = 1;
        while (i < str.length()) {
            while (i < str.length() && sb.charAt(sb.length() - 1) == '*' && str.charAt(i) == '*')
                i++;

            if (i < str.length())
                sb.append(str.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public int isMatch(String s, String p, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 1; // true
            else if (m == 1 && p.charAt(m - 1) == '*')
                return dp[n][m] = 1;
            else
                return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = s.charAt(n - 1);
        char ch2 = p.charAt(m - 1);

        if (ch1 == ch2 || ch2 == '?') {
            return dp[n][m] = isMatch(s, p, n - 1, m - 1, dp);
        } else if (ch2 == '*') {
            boolean res = false;
            res = res || isMatch(s, p, n - 1, m, dp) == 1; // sequnence of character
            res = res || isMatch(s, p, n, m - 1, dp) == 1; // empty string

            return dp[n][m] = res ? 1 : 0;

        } else
            return dp[n][m] = 0;
    }

    public boolean isMatch(String s, String p) {
        p = removeStars(p);
        int n = s.length(), m = p.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = isMatch(s, p, n, m, dp);

        return ans == 1;
    }
}