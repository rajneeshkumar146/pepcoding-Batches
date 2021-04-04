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

    // 115
    public int numDistinct_memo(String s, String t, int n, int m, int[][] dp) {
        if (m == 0) {
            return dp[n][m] = 1;
        }

        if (n < m) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (s.charAt(n - 1) == t.charAt(m - 1)) {
            dp[n][m] = numDistinct_memo(s, t, n - 1, m - 1, dp) + numDistinct_memo(s, t, n - 1, m, dp);
        } else {
            dp[n][m] = numDistinct_memo(s, t, n - 1, m, dp);
        }

        return dp[n][m];
    }

    public int numDistinct_DP(String s, String t, int N, int M, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (m == 0) {
                    dp[n][m] = 1;
                    continue;
                }

                // if (n < m) {
                // return dp[n][m] = 0;
                // }

                if (s.charAt(n - 1) == t.charAt(m - 1)) {
                    dp[n][m] = dp[n - 1][m - 1] + dp[n - 1][m];
                } else {
                    dp[n][m] = dp[n - 1][m];
                }

                return dp[n][m];
            }
        }

        return dp[N][M];
    }

    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n + 1][m + 1];
        // for (int[] d : dp)
        // Arrays.fill(d, -1);

        int ans = numDistinct_memo(s, t, n, m, dp);
        for (int[] d : dp) {
            for (int e : d) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
        return ans;
    }

    // 1143
    int longestCommonSubsequence(String text1, String text2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (text1.charAt(n - 1) == text2.charAt(m - 1))
            dp[n][m] = 1 + longestCommonSubsequence(text1, text2, n - 1, m - 1, dp);
        else
            dp[n][m] = Math.max(longestCommonSubsequence(text1, text2, n, m - 1, dp),
                    longestCommonSubsequence(text1, text2, n - 1, m, dp));

        return dp[n][m];
    }

    int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return longestCommonSubsequence(text1, text2, n, m, dp);
    }

    // 1035
    public int maxUncrossedLines(int[] A, int[] B) {

        int N = A.length;
        int M = B.length;
        int[][] dp = new int[N + 1][M + 1];

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {

                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (A[n - 1] == B[m - 1])
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }

        return dp[N][M];
    }

    // 1458
    public int maxDotProduct_memo(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = -(int) 1e7;
        }

        if (dp[n][m] != -(int) 1e8)
            return dp[n][m];

        int val = nums1[n - 1] * nums2[m - 1];
        int acceptBothNumber = maxDotProduct_memo(nums1, nums2, n - 1, m - 1, dp) + val;
        int a = maxDotProduct_memo(nums1, nums2, n - 1, m, dp);
        int b = maxDotProduct_memo(nums1, nums2, n, m - 1, dp);

        return dp[n][m] = Math.max(Math.max(acceptBothNumber, val), Math.max(a, b));
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -(int) 1e8);
        return maxDotProduct_memo(nums1, nums2, n, m, dp);
    }

    // 72
    public int minDistance(String word1, String word2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n != 0) ? n : m;
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

    // follow up
    // cost {inserCost,deleteCost,replaceCost}
    public int minDistanceWithCost(String word1, String word2, int[] cost, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n != 0) ? n * cost[1] : m * cost[0];
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int insert = minDistanceWithCost(word1, word2, cost, n, m - 1, dp);
        int delete = minDistanceWithCost(word1, word2, cost, n - 1, m, dp);
        int replace = minDistanceWithCost(word1, word2, cost, n - 1, m - 1, dp);

        if (word1.charAt(n - 1) == word2.charAt(m - 1))
            return dp[n][m] = replace + 0;
        else
            return dp[n][m] = Math.min(Math.min(insert + cost[0], delete + cost[1]), replace + cost[2]);
    }

    public int minDistance_dp(String word1, String word2, int N, int M, int[][] dp) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = (n != 0) ? n : m;
                    continue;
                }

                int insert = dp[n][m - 1];// minDistance(word1, word2, n, m - 1, dp);
                int delete = dp[n - 1][m];// minDistance(word1, word2, n - 1, m, dp);
                int replace = dp[n - 1][m - 1];// minDistance(word1, word2, n - 1, m - 1, dp);

                if (word1.charAt(n - 1) == word2.charAt(m - 1))
                    dp[n][m] = replace;
                else
                    dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
            }
        }

        return dp[N][M];
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return minDistance(word1, word2, n, m, dp);
    }

    // 44
    // -1 -> default, 0 -> false, 1 -> true
    public int isMatch(String s, String p, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 1;
            else if (m == 1 && p.charAt(m - 1) == '*')
                return dp[n][m] = 1;
            else
                return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = s.charAt(n - 1);
        char ch2 = p.charAt(m - 1);

        if (ch1 == ch2 || ch2 == '?')
            return dp[n][m] = isMatch(s, p, n - 1, m - 1, dp);
        else if (ch2 == '*') {
            boolean res = false;
            res = res || (isMatch(s, p, n - 1, m, dp) == 1); // map to character
            res = res || (isMatch(s, p, n, m - 1, dp) == 1); // map to empty String

            return dp[n][m] = res ? 1 : 0;
        } else
            return dp[n][m] = 0;
    }

    public String removeStars(String s) {
        if (s.length() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));

        int i = 1;
        while (i < s.length()) {
            while (i < s.length() && sb.charAt(sb.length() - 1) == '*' && s.charAt(i) == '*')
                i++;
            if (i < s.length())
                sb.append(s.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public boolean isMatch(String s, String p) {
        p = removeStars(p);
        int n = s.length();
        int m = p.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return isMatch(s, p, n, m, dp) == 1;
    }

    public String longestPalindrome(String str) {

        int n = str.length();
        int[][] dp = new int[n][n];
        int len = 0;
        int si = 0;
        int count = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = 1;
                else if (gap == 1)
                    dp[i][j] = str.charAt(i) == str.charAt(j) ? 2 : 0;
                else
                    dp[i][j] = str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1] != 0 ? dp[i + 1][j - 1] + 2 : 0;

                if (dp[i][j] > len) {
                    len = dp[i][j];
                    si = i;
                }

                if (dp[i][j] != 0)
                    count++;
            }
        }

        return str.substring(si, si + len);
    }

}