import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

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

    public static int lcsubstring_DP(String str1, String str2, int N, int M, int[][] dp) {
        int maxLen = 0, ei = 0;
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                    if (dp[n][m] > maxLen) {
                        maxLen = dp[n][m];
                        ei = n - 1;
                    }
                }
            }
        }

        return maxLen;
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

    // 583
    public int minDistance(String word1, String word2) {
        return word1.length() + word2.length() - 2 * longestCommonSubsequence(word1, word2);
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

    // 1035
    public int maxUncrossedLines(int[] arr1, int[] arr2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (arr1[n - 1] == arr2[m - 1])
                    dp[n][m] = dp[n - 1][m - 1] + 1;// lcss(str1, str2, n - 1, m - 1, dp) + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }

        return dp[N][M];
    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        int ans = maxUncrossedLines(nums1, nums2, n, m, dp);
        return ans;
    }

    // 1458
    public int maximum(int... arr) {
        int max = arr[0];
        for (int ele : arr)
            max = Math.max(ele, max);

        return max;
    }

    public int maxDotProduct(int[] nums1, int[] nums2, int n, int m, int[][] dp) {

        if (n == 0 || m == 0) {
            return dp[n][m] = -(int) 1e8;
        }

        if (dp[n][m] != -(int) 1e9)
            return dp[n][m];

        int val = nums1[n - 1] * nums2[m - 1];
        int acceptBothNumbers = maxDotProduct(nums1, nums2, n - 1, m - 1, dp) + val;
        int a = maxDotProduct(nums1, nums2, n - 1, m, dp);
        int b = maxDotProduct(nums1, nums2, n, m - 1, dp);

        return dp[n][m] = maximum(val, acceptBothNumbers, a, b);
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -(int) 1e9);
        int ans = maxDotProduct(nums1, nums2, n, m, dp);
        return ans;
    }

    // 005
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int count = 0, MaxLen = 0, si = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = true;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    dp[i][j] = true;
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];

                if (dp[i][j]) {
                    count++;
                    if (j - i + 1 > MaxLen) {
                        MaxLen = j - i + 1;
                        si = i;
                    }
                }
            }
        }

        return s.substring(si, si + MaxLen);
    }

    // 132
    public int minCut(String s, int si, int ei, int[] dp, boolean[][] pdp) {
        if (pdp[si][ei])
            return 0;
        if (dp[si] != -1)
            return dp[si];

        int minAns = (int) 1e8;
        for (int cut = si; cut <= ei; cut++) {
            if (pdp[si][cut]) {
                minAns = Math.min(minAns, minCut(s, cut + 1, ei, dp, pdp) + 1);
            }
        }

        return dp[si] = minAns;
    }

    // faafaaaaabaageeg
    public int minCut(String s) {
        int n = s.length();
        boolean[][] pdp = new boolean[n][n];
        int count = 0, MaxLen = 0, si = 0;
        for (int gap = 0; gap < n; gap++)
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    pdp[i][j] = true;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    pdp[i][j] = true;
                else
                    pdp[i][j] = s.charAt(i) == s.charAt(j) && pdp[i + 1][j - 1];
            }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return minCut(s, 0, n - 1, dp, pdp);
    }

    // https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1
    public int fun(String s) {
        int n = s.length();
        long emptyCount = 1, aCount = 0, bCount = 0, cCount = 0, mod = (long) 1e9 + 7;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == 'a')
                aCount = aCount + (emptyCount + aCount) % mod;
            else if (ch == 'b')
                bCount = bCount + (aCount + bCount) % mod;
            else if (ch == 'c')
                cCount = cCount + (bCount + cCount) % mod;
        }

        return (int) (cCount % mod);
    }

    // followUp Question : ai-bj-ck-dl-em-fn
    // HM : 1278. Palindrome Partitioning III

    // 139
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        int len = 0, n = s.length();
        for (String ss : wordDict) {
            set.add(ss);
            len = Math.max(ss.length(), len);
        }

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= n; i++) {
            if (!dp[i])
                continue;

            for (int l = 1; l <= len && i + l <= n; l++) {
                String substr = s.substring(i, i + l);
                if (set.contains(substr)) {
                    dp[i + l] = true;
                }
            }
        }
        return dp[n];
    }

    public static String lpss_backEng(String str, int si, int ei, int[][] dp) {
        if (si >= ei) {
            return si == ei ? str.charAt(si) + "" : "";
        }

        if (str.charAt(si) == str.charAt(ei)) {
            return str.charAt(si) + lpss_backEng(str, si + 1, ei - 1, dp) + str.charAt(ei);
        } else if (dp[si + 1][ei] > dp[si][ei - 1]) {
            return lpss_backEng(str, si + 1, ei, dp);
        } else {
            return lpss_backEng(str, si, ei - 1, dp);
        }
    }

    public void wordBreak_backEngg(String s, int idx, boolean[] dp, int maxLen, List<String> wordDict,
            HashSet<String> set, String ssf, List<String> ans) {
        if (idx >= s.length()) {
            ans.add(ssf.substring(0, ssf.length() - 1));
            return;
        }

        for (int l = 1; l <= maxLen && idx + l <= s.length(); l++) {
            if (dp[idx + l]) {
                String substr = s.substring(idx, idx + l);
                if (set.contains(substr)) {
                    wordBreak_backEngg(s, idx + l, dp, maxLen, wordDict, set, ssf + substr + " ", ans);
                }
            }
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        int len = 0, n = s.length();
        for (String ss : wordDict) {
            set.add(ss);
            len = Math.max(ss.length(), len);
        }

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= n; i++) {
            if (!dp[i])
                continue;

            for (int l = 1; l <= len && i + l <= n; l++) {
                String substr = s.substring(i, i + l);
                if (set.contains(substr)) {
                    dp[i + l] = true;
                }
            }
        }

        List<String> ans = new ArrayList<>();
        if (dp[n])
            wordBreak_backEngg(s, 0, dp, len, wordDict, set, "", ans);

        return ans;
    }

    // 10
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
        if (n == 0 && m == 0)
            return dp[n][m] = 1;
        if (m == 0)
            return dp[n][m] = 0;

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = n > 0 ? s.charAt(n - 1) : '$';
        char ch2 = p.charAt(m - 1);

        if (ch1 != '$' && (ch1 == ch2 || ch2 == '.'))
            return dp[n][m] = isMatch(s, p, n - 1, m - 1, dp);
        else if (ch2 == '*') {
            boolean res = false;

            if (n > 0 && m > 1 && (p.charAt(m - 2) == '.' || p.charAt(m - 2) == ch1))
                res = res || isMatch(s, p, n - 1, m, dp) == 1;
            res = res || isMatch(s, p, n, m - 2, dp) == 1;

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

        return isMatch(s, p, n, m, dp) == 1;
    }

    // 1278
    public int[][] minChanges(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        for (int gap = 1; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 1)
                    dp[i][j] = str.charAt(i) == str.charAt(j) ? 0 : 1;
                else
                    dp[i][j] = str.charAt(i) == str.charAt(j) ? dp[i + 1][j - 1] : dp[i + 1][j - 1] + 1;
            }
        }

        return dp;
    }

    public int palindromePartition(String s, int k, int si, int[][] dp, int[][] minChanges) {
        if (s.length() - si <= k) {
            return dp[si][k] = s.length() - si == k ? 0 : (int) 1e9;
        }

        if (k == 1)
            dp[si][k] = minChanges[si][s.length() - 1];

        if (dp[si][k] != -1) {
            return dp[si][k];
        }

        int minAns = (int) 1e9;
        for (int i = si; i < s.length(); i++) {
            int minChangesInMyString = minChanges[si][i];
            int minChangesInRecString = palindromePartition(s, k - 1, i + 1, dp, minChanges);

            if (minChangesInRecString != (int) 1e9)
                minAns = Math.min(minAns, minChangesInRecString + minChangesInMyString);
        }

        return dp[si][k] = minAns;
    }

    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] minChanges = minChanges(s);
        int[][] dp = new int[n + 1][k + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return palindromePartition(s, k, 0, dp, minChanges);
    }

    // 213
    public int rob_II(int[] arr, int si, int ei, int[] dp) {
        if (si > ei)
            return 0;

        if (dp[si] != -1)
            return dp[si];

        int robCurrHouse = arr[si] + rob_II(arr, si + 2, ei, dp);
        int notRobCurrHouse = rob_II(arr, si + 1, ei, dp);

        return dp[si] = Math.max(robCurrHouse, notRobCurrHouse);
    }

    public int rob_II(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1) {
            return n == 1 ? nums[0] : 0;
        }
        int[] dp1 = new int[n];
        Arrays.fill(dp1, -1);

        int[] dp2 = new int[n];
        Arrays.fill(dp2, -1);

        return Math.max(rob_II(nums, 0, n - 2, dp1), rob_II(nums, 1, n - 1, dp2));

    }

    // 198
    public int rob_I(int[] arr, int n, int[] dp) {
        if (n <= 0)
            return 0;

        if (dp[n] != -1)
            return dp[n];

        int robCurrHouse = arr[n - 1] + rob_I(arr, n - 2, dp);
        int notRobCurrHouse = rob_I(arr, n - 1, dp);

        return dp[n] = Math.max(robCurrHouse, notRobCurrHouse);
    }

    public int rob_I(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1) {
            return n == 1 ? nums[0] : 0;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return rob_I(nums, n, dp);
    }

    // 1388
    public int maxSizeSlices(int[] slices, int si, int ei, int NoOfSlices, int[][] dp) {
        if (si > ei || NoOfSlices == 0) {
            return 0;
        }
        if (dp[ei][NoOfSlices] != -1)
            return dp[ei][NoOfSlices];
        int takeThisSlice = maxSizeSlices(slices, si, ei - 2, NoOfSlices - 1, dp) + slices[ei];
        int leaveThisSlice = maxSizeSlices(slices, si, ei - 1, NoOfSlices, dp);

        return dp[ei][NoOfSlices] = Math.max(takeThisSlice, leaveThisSlice);
    }

    public int maxSizeSlices(int[] slices) {
        int n = slices.length;
        int[][] dp1 = new int[n + 1][n / 3 + 1];
        int[][] dp2 = new int[n + 1][n / 3 + 1];

        for (int[] d : dp1)
            Arrays.fill(d, -1);
        for (int[] d : dp2)
            Arrays.fill(d, -1);

        return Math.max(maxSizeSlices(slices, 0, n - 2, n / 3, dp1), maxSizeSlices(slices, 1, n - 1, n / 3, dp2));

    }
}