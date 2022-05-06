import java.util.Arrays;

public class l002_StringSet {

    public static void display(int[] dp) {
        for (int ele : dp)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] d : dp)
            display(d);
    }

    public static int longestPlaindromicSubsequence_memo(String str, int i, int j, int[][] dp) {
        if (i >= j) {
            return dp[i][j] = i == j ? 1 : 0;
        }

        if (dp[i][j] != 0)
            return dp[i][j];

        int a = longestPlaindromicSubsequence_memo(str, i + 1, j - 1, dp);
        int b = longestPlaindromicSubsequence_memo(str, i + 1, j, dp);
        int c = longestPlaindromicSubsequence_memo(str, i, j - 1, dp);

        if (str.charAt(i) == str.charAt(j))
            return dp[i][j] = a + 2;
        else
            return dp[i][j] = Math.max(b, c);
    }

    public static int longestPlaindromicSubsequence_tabu(String str, int I, int J, int[][] dp) {
        int n = str.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (i >= j) {
                    dp[i][j] = i == j ? 1 : 0;
                    continue;
                }
                if (str.charAt(i) == str.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[I][J];
    }

    public static void longestPlaindromicSubsequence() {
        String str = "geeksforgeeks";
        int n = str.length();
        int[][] dp = new int[n][n];
        int ans = longestPlaindromicSubsequence_tabu(str, 0, n - 1, dp);

        display2D(dp);
        System.out.println(ans);
    }

    // =======================================================================

    public static int longestCommonSubsequence_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int a = longestCommonSubsequence_memo(s1, s2, n - 1, m - 1, dp);
        int b = longestCommonSubsequence_memo(s1, s2, n - 1, m, dp);
        int c = longestCommonSubsequence_memo(s1, s2, n, m - 1, dp);

        if (s1.charAt(n - 1) == s2.charAt(m - 1))
            return dp[n][m] = a + 1;
        else
            return dp[n][m] = Math.max(b, c);
    }

    public static int longestCommonSubsequence_tabu(String s1, String s2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }
                if (s1.charAt(n - 1) == s2.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                else
                    dp[n][m] = Math.max(dp[n][m - 1], dp[n - 1][m]);
            }
        }

        return dp[N][M];
    }

    public static int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int ans = longestCommonSubsequence_memo(s1, s2, n, m, dp);
        display2D(dp);
        return ans;
    }

    // =======================================

    public static int minDistance_memo(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = n != 0 ? n : m;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int a = minDistance_memo(s1, s2, n - 1, m - 1, dp);
        int b = minDistance_memo(s1, s2, n - 1, m, dp);
        int c = minDistance_memo(s1, s2, n, m - 1, dp);

        if (s1.charAt(n - 1) == s2.charAt(m - 1))
            return dp[n][m] = a;
        else
            return dp[n][m] = Math.min(Math.min(a, b), c) + 1;
    }

    public static int minDistance_tabu(String s1, String s2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = n != 0 ? n : m;
                    continue;
                }
                if (s1.charAt(n - 1) == s2.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1];
                else
                    dp[n][m] = Math.min(Math.min(dp[n][m - 1], dp[n - 1][m]), dp[n - 1][m - 1]) + 1;
            }
        }

        return dp[N][M];
    }

    public int minDistance(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int ans = minDistance_memo(s1, s2, n, m, dp);
        return ans;
    }

    public int minDistance_followup(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int[] cost = { 2, 5, 3 }; // insert, delete, replace

        int ans = 0;
        return ans;
    }

    // https://www.geeksforgeeks.org/minimum-number-deletions-insertions-transform-one-string-another/

    // 115
    public int numDistinct_memo(String s, String t, int n, int m, int[][] dp) {
        if (m == 0)
            return dp[n][m] = 1;
        else if (n == 0)
            return dp[n][m] = 0;

        if (dp[n][m] != -1)
            return dp[n][m];

        int a = numDistinct_memo(s, t, n - 1, m - 1, dp);
        int b = numDistinct_memo(s, t, n - 1, m, dp);

        if (s.charAt(n - 1) == t.charAt(m - 1))
            return dp[n][m] = a + b;
        else
            return dp[n][m] = b;
    }

    public int numDistinct_tabu(String s, String t, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (m == 0 || n == 0) {
                    dp[n][m] = m == 0 ? 1 : 0;
                    continue;
                }

                if (s.charAt(n - 1) == t.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1] + dp[n - 1][m];
                else
                    dp[n][m] = dp[n - 1][m];
            }
        }
        return dp[N][M];
    }

    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return numDistinct_memo(s, t, n, m, dp);
    }

    // 583
    public int minDistance(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int ans = longestCommonSubsequence_memo(s1, s2, n, m, dp);

        return n + m - 2 * ans;
    }

    // 1035
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int N = nums1.length, M = nums2.length;
        int[][] dp = new int[N + 1][M + 1];
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (nums1[n - 1] == nums2[m - 1])
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }

        return dp[N][M];
    }

    // 005
    public int longestPlaindromicSubstring(String s) {
        int n = s.length(), len = 0, stIdx = 0, count = 0;
        int[][] dp = new int[n][n];
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = 1;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    dp[i][j] = 2;
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] > 0 ? dp[i + 1][j - 1] + 2 : 0;

                if (dp[i][j] > len) {
                    len = dp[i][j];
                    stIdx = i;
                }

                count += dp[i][j] > 0 ? 1 : 0;
            }
        }

        // s.substring(stIdx, stIdx + len);
        return len;
    }

    // https://www.geeksforgeeks.org/longest-common-substring-dp-29/
    public int longestCommonSubstring(String s) {
        int n = s.length(), len = 0, stIdx = 0, count = 0;
        int[][] dp = new int[n][n];
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = 1;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    dp[i][j] = 2;
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] > 0 ? dp[i + 1][j - 1] + 2 : 0;

                if (dp[i][j] > len) {
                    len = dp[i][j];
                    stIdx = i;
                }

                count += dp[i][j] > 0 ? 1 : 0;
            }
        }

        // s.substring(stIdx, stIdx + len);
        return len;
    }

    // https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1
    public int fun(String s) {
        int aCount = 0, bCount = 0, cCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                aCount = aCount + (1 + aCount);
            } else if (ch == 'b') {
                bCount = bCount + (aCount + bCount);
            } else {
                cCount = cCount + (bCount + cCount);
            }
        }

        return cCount;
    }

    public static void main(String[] args) {
        longestPlaindromicSubsequence();
    }

}