import java.util.Arrays;

public class l002StringSet {

    public static void print(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void print2D(int[][] arr) {
        for (int[] a : arr) {
            print(a);
        }

        System.out.println();
    }

    // LPSS : longestPalindromicSubsequence
    public static int LPSS(String str, int i, int j, int[][] dp) {
        if (i >= j) {
            return dp[i][j] = (i == j) ? 1 : 0;
        }

        if (dp[i][j] != 0)
            return dp[i][j];

        if (str.charAt(i) == str.charAt(j))
            return dp[i][j] = LPSS(str, i + 1, j - 1, dp) + 2;
        else
            return dp[i][j] = Math.max(LPSS(str, i + 1, j, dp), LPSS(str, i, j - 1, dp));
    }

    public static int LPSS_DP(String str, int I, int J, int[][] dp) {
        int n = str.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (i >= j) {
                    dp[i][j] = (i == j) ? 1 : 0;
                    continue;
                }

                if (str.charAt(i) == str.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;// LPSS(str, i + 1, j - 1, dp) + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[I][J];
    }

    public static String LPSS_StringDP(String str) {
        int n = str.length();
        String[][] dp = new String[n][n];
        for (String[] d : dp)
            Arrays.fill(d, "");

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (i >= j) {
                    dp[i][j] = (i == j) ? str.charAt(i) + "" : "";
                    continue;
                }

                if (str.charAt(i) == str.charAt(j))
                    dp[i][j] = str.charAt(i) + dp[i + 1][j - 1] + str.charAt(j);
                else
                    dp[i][j] = dp[i + 1][j].length() > dp[i][j - 1].length() ? dp[i + 1][j] : dp[i][j - 1];
            }
        }

        return dp[0][n - 1];
    }

    public static void longestPalindromicSubsequence() {
        String str = "peeeddfeeeep";
        int n = str.length();
        int[][] dp = new int[n][n];

        // for (int[] d : dp)
        // Arrays.fill(d, -1);

        // System.out.println(LPSS(str, 0, n - 1, dp));
        System.out.println(LPSS_StringDP(str));
        // print2D(dp);

    }

    // LPS : Longest Palindromic Substring
    public static void LPS(String str, boolean[][] dp) {
        int n = str.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = true;
                else if (gap == 1)
                    dp[i][j] = str.charAt(i) == str.charAt(j);
                else
                    dp[i][j] = str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1];
            }
        }
    }

    public static int LPS(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];

        int maxLen = 0;
        int count = 0;

        int sp = 0, ep = 0;

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = 1;
                else if (gap == 1)
                    dp[i][j] = str.charAt(i) == str.charAt(j) ? 2 : 0;
                else if (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1] > 0)
                    dp[i][j] = dp[i + 1][j - 1] + 2;

                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    sp = i;
                    ep = j;
                }

                if (dp[i][j] != 0)
                    count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        longestPalindromicSubsequence();
    }
}