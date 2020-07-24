import java.util.LinkedList;
import java.util.Arrays;

public class l001 {

    // Future Planning.=============================================
    static int mod = (int) 1e9 + 7;

    public static void display(int[] dp) {
        for (int ele : dp)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] ar : dp) {
            display(ar);
        }
        System.out.println();
    }

    public static int fibo_Rec(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;

        if (dp[n] != 0)
            return dp[n];

        int ans = fibo_Rec(n - 1, dp) + fibo_Rec(n - 2, dp);

        return dp[n] = ans;
    }

    public static int fibo_DP(int n, int[] dp) {
        int N = n;
        for (n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }
            int ans = dp[n - 1] + dp[n - 2];// fibo_Rec(n - 1, dp) + fibo_Rec(n - 2, dp);

            dp[n] = ans;
        }

        return dp[N];
    }

    public static int fibo_Opti(int n) {
        int a = 0;
        int b = 1;
        for (int i = 0; i < n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    public static int mazePath_Rec_01(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        if (sr + 1 <= er)
            count += mazePath_Rec_01(sr + 1, sc, er, ec, dp);
        if (sc + 1 <= ec)
            count += mazePath_Rec_01(sr, sc + 1, er, ec, dp);
        if (sr + 1 <= er && sc + 1 <= ec)
            count += mazePath_Rec_01(sr + 1, sc + 1, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazePath_Rec_02(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int jump = 1; sr + jump <= er; jump++)
            count += mazePath_Rec_02(sr + jump, sc, er, ec, dp);
        for (int jump = 1; sc + jump <= ec; jump++)
            count += mazePath_Rec_02(sr, sc + jump, er, ec, dp);
        for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
            count += mazePath_Rec_02(sr + jump, sc + jump, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazePath_DP_01(int sr, int sc, int er, int ec, int[][] dp) {
        for (sr = er; sr >= 0; sr--) {
            for (sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                if (sr + 1 <= er)
                    count += dp[sr + 1][sc];// mazePath_Rec_01(sr + 1, sc, er, ec, dp);
                if (sc + 1 <= ec)
                    count += dp[sr][sc + 1];// mazePath_Rec_01(sr, sc + 1, er, ec, dp);
                if (sr + 1 <= er && sc + 1 <= ec)
                    count += dp[sr + 1][sc + 1];// mazePath_Rec_01(sr + 1, sc + 1, er, ec, dp);

                dp[sr][sc] = count;
            }
        }

        return dp[0][0];
    }

    public static int mazePath_DP_02(int sr, int sc, int er, int ec, int[][] dp) {
        for (sr = er; sr >= 0; sr--) {
            for (sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int jump = 1; sr + jump <= er; jump++)
                    count += dp[sr + jump][sc];
                for (int jump = 1; sc + jump <= ec; jump++)
                    count += dp[sr][sc + jump];
                for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
                    count += dp[sr + jump][sc + jump];

                dp[sr][sc] = count;
            }
        }

        return dp[0][0];
    }

    // to_Be_done------------------------------------------->62_and_63_of_leetcode.

    public static int boardPath_Rec_01(int si, int ei, int[] dp) {
        if (si == ei) {
            return dp[si] = 1;
        }

        if (dp[si] != 0)
            return dp[si];

        int count = 0;
        for (int dice = 1; dice <= 6 && si + dice <= ei; dice++) {
            count += boardPath_Rec_01(si + dice, ei, dp);
        }

        return dp[si] = count;
    }

    public static int boardPath_DP_01(int si, int ei, int[] dp) {

        for (si = ei; si >= 0; si--) {
            if (si == ei) {
                dp[si] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 1; dice <= 6 && si + dice <= ei; dice++) {
                count += dp[si + dice];// boardPath_Rec_01(si + dice, ei, dp);
            }

            dp[si] = count;
        }

        return dp[0];
    }

    public static int boardPath_opti(int ei) {
        LinkedList<Integer> ll = new LinkedList<>();

        for (int si = 0; si <= ei; si++) {
            if (si < 2) {
                ll.addFirst(1);
                continue;
            }

            if (ll.size() <= 6)
                ll.addFirst(2 * ll.getFirst());
            else {
                ll.addFirst(2 * ll.getFirst() - ll.getLast());
                ll.removeLast();
            }
        }

        return ll.getFirst();
    }

    public static int boardPath_DP_02(int si, int ei, int[] moves, int[] dp) {
        for (si = ei; si >= 0; si--) {
            if (si == ei) {
                dp[si] = 1;
                continue;
            }

            int count = 0;
            for (int i = 0; i < moves.length; i++) {
                if (si + moves[i] <= ei)
                    count += dp[si + moves[i]];// boardPath_Rec_01(si + moves[i], ei, dp);
            }

            dp[si] = count;
        }

        return dp[0];
    }

    // https://practice.geeksforgeeks.org/problems/gold-mine-problem/0

    public static int goldmineProblem(int[][] grid, int sr, int sc, int[][] dp) {
        if (sc == grid[0].length - 1)
            return grid[sr][sc];
        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int[][] dirA = { { 0, 1 }, { -1, 1 }, { 1, 1 } };
        int maxCoin = 0; // max coin collected by nbrs.
        for (int d = 0; d < 3; d++) {
            int r = sr + dirA[d][0];
            int c = sc + dirA[d][1];
            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length) {
                maxCoin = Math.max(maxCoin, goldmineProblem(grid, r, c, dp));
            }
        }

        return dp[sr][sc] = maxCoin + grid[sr][sc];
    }

    public static int goldmineProblem_DP(int[][] grid, int sr, int sc, int[][] dp) {

        int[][] dirA = { { 0, 1 }, { -1, 1 }, { 1, 1 } };
        for (sc = grid[0].length - 1; sc >= 0; sc--) {
            for (sr = grid.length - 1; sr >= 0; sr--) {
                if (sc == grid[0].length - 1) {
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }

                int maxCoin = 0; // max coin collected by nbrs.
                for (int d = 0; d < 3; d++) {
                    int r = sr + dirA[d][0];
                    int c = sc + dirA[d][1];
                    if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length) {
                        maxCoin = Math.max(maxCoin, dp[r][c]);
                    }
                }

                dp[sr][sc] = maxCoin + grid[sr][sc];
            }
        }
        int MaxCoins = 0;
        for (int i = 0; i < grid.length; i++) {
            MaxCoins = Math.max(MaxCoins, dp[i][0]);
        }
        return MaxCoins;
    }

    public static int goldmineProblem(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];

        int MaxCoins = 0;
        for (int i = 0; i < grid.length; i++) {
            MaxCoins = Math.max(MaxCoins, goldmineProblem(grid, i, 0, dp));
        }

        return MaxCoins;
    }

    // Leetcode 64

    public static int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];

        for (int sr = grid.length - 1; sr >= 0; sr--) {
            for (int sc = grid[0].length - 1; sc >= 0; sc--) {
                if (sr == grid.length - 1 && sc == grid[0].length - 1) {
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }

                int minCoins = (int) 1e8;
                if (sc + 1 < grid[0].length)
                    minCoins = Math.min(minCoins, dp[sr][sc + 1]);
                if (sr + 1 < grid.length)
                    minCoins = Math.min(minCoins, dp[sr + 1][sc]);

                dp[sr][sc] = minCoins + grid[sr][sc];
            }
        }

        return dp[0][0];
    }

    // Leetcode 70.

    public static int climbStairs(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = 1;
        if (dp[n] != 0)
            return dp[n];

        return dp[n] = climbStairs(n - 1, dp) + climbStairs(n - 2, dp);
    }

    public static int climbStairs(int n) {

        int[] dp = new int[n + 1];
        return climbStairs(n, dp);
    }

    public static int minCostClimbingStairs(int[] cost, int n, int[] dp) {
        if (n <= 1)
            return dp[n] = cost[n];
        if (dp[n] != 0)
            return dp[n];

        int minCost = Math.min(minCostClimbingStairs(cost, n - 1, dp), minCostClimbingStairs(cost, n - 2, dp));

        return dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
    }

    public static int minCostClimbingStairs_Opti(int[] cost) {
        int a = cost[0];
        int b = cost[1];

        for (int i = 2; i < cost.length; i++) {
            int ans = Math.min(a, b) + cost[i];
            a = b;
            b = ans;
        }
        return Math.min(a, b);
    }

    public static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];

        return minCostClimbingStairs(cost, cost.length, dp);
    }

    // https://practice.geeksforgeeks.org/problems/friends-pairing-problem/0

    public static long friendsPairingProblem(int n, long[] dp) {
        if (n <= 1)
            return dp[n] = 1;
        if (dp[n] != 0)
            return dp[n];

        long single = friendsPairingProblem(n - 1, dp) % mod;
        long pairUp = friendsPairingProblem(n - 2, dp) % mod * (n - 1) % mod;

        return dp[n] = (single + pairUp) % mod;
    }

    public static long friendsPairingProblem_DP(int n, long[] dp) {
        int N = n;
        for (n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            long single = dp[n - 1] % mod;// friendsPairingProblem(n - 1, dp);
            long pairUp = dp[n - 2] % mod * (n - 1) % mod;// friendsPairingProblem(n - 2, dp) * (n - 1);

            dp[n] = single + pairUp;
        }

        return dp[N] % mod;
    }

    public static int friendsPairingProblem_Opti(int n) {
        int single = 1;
        int pairUp = 1;

        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = single + pairUp * (i - 1);

            pairUp = single;
            single = ans;
        }

        return ans;
    }

    public static void friendsPairingProblem() {
        // int n = scn.nextInt();
        int n = 5;
        long[] dp = new long[n + 1];
        long ans = friendsPairingProblem(n, dp);

        System.out.println(ans);
    }

    // https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/

    public static int count_of_ways(int n, int k, int[][] dp) {
        if (k == n || k == 1)
            return dp[k][n] = 1;

        if (dp[k][n] != -1)
            return dp[k][n];

        int ownGroup = count_of_ways(n - 1, k - 1, dp);
        int partOfOtherGroup = count_of_ways(n - 1, k, dp) * k;

        return dp[k][n] = ownGroup + partOfOtherGroup;
    }

    public static int count_of_ways_DP(int n, int k, int[][] dp) {
        int K = k;
        int N = n;
        for (k = 1; k <= K; k++) {
            for (n = k; n <= N; n++) {
                if (k == n || k == 1) {
                    dp[k][n] = 1;
                    continue;
                }

                int ownGroup = dp[k - 1][n - 1]; // count_of_ways(n - 1, k - 1, dp);
                int partOfOtherGroup = dp[k][n - 1] * k;// count_of_ways(n - 1, k, dp) * k;

                dp[k][n] = ownGroup + partOfOtherGroup;
            }
        }

        return dp[K][N];
    }

    public static void count_of_ways(int n, int k) {
        int[][] dp = new int[k + 1][n + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        // System.out.println(count_of_ways(n, k, dp));
        System.out.println(count_of_ways_DP(n, k, dp));

        display2D(dp);
    }
    // String_set=========================================================================================

    // Leetcode 005,647.
    public static int[][] longestPalindromeSubstring(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];

        int maxLen = 0;
        int count = 0;
        int si = 0, ei = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; j++, i++) {
                if (gap == 0)
                    dp[i][j] = 1;
                else if (gap == 1 && str.charAt(i) == str.charAt(j))
                    dp[i][j] = 2;
                else if (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1] > 0) // if dp[i][j] > 0 means it is a
                                                                                 // palindrome.
                    dp[i][j] = dp[i + 1][j - 1] + 2;

                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    si = i;
                    ei = j;
                }
                count += (dp[i][j] > 0) ? 1 : 0;
            }
        }

        System.out.println("maxLen: " + maxLen + " and Count of total Palindromes: " + count);
        System.out.println(str.substring(si, ei + 1));

        return dp;
    }

    // Leetcode 516
    public static int longestPlaindromeSubsequence(String str, int i, int j, int[][] dp) {
        if (i > j)
            return dp[i][j] = 0;
        if (i == j)
            return dp[i][j] = 1;
        if (dp[i][j] != 0)
            return dp[i][j];
        int maxLen = 0;
        if (str.charAt(i) == str.charAt(j))
            maxLen = longestPlaindromeSubsequence(str, i + 1, j - 1, dp) + 2;
        else
            maxLen = Math.max(longestPlaindromeSubsequence(str, i + 1, j, dp),
                    longestPlaindromeSubsequence(str, i, j - 1, dp));

        return dp[i][j] = maxLen;
    }

    public static class pair {
        String str = "";
        int len = 0;

        pair(String str, int len) {
            this.str = str;
            this.len = len;
        }
    }

    public static pair longestPlaindromeSubsequence_02(String str, int i, int j, pair[][] dp) {
        if (i > j)
            return dp[i][j] = new pair("", 0);
        if (i == j)
            return dp[i][j] = new pair(str.charAt(i) + "", 1);

        if (dp[i][j] != null)
            return dp[i][j];

        pair maxPair = new pair("", 0);
        if (str.charAt(i) == str.charAt(j)) {
            pair recAns = longestPlaindromeSubsequence_02(str, i + 1, j - 1, dp);
            maxPair.str = str.charAt(i) + recAns.str + str.charAt(j);
            maxPair.len = recAns.len + 2;
        } else {
            pair recAns1 = longestPlaindromeSubsequence_02(str, i + 1, j, dp);
            pair recAns2 = longestPlaindromeSubsequence_02(str, i, j - 1, dp);

            if (recAns1.len > recAns2.len) {
                maxPair.len = recAns1.len;
                maxPair.str = recAns1.str;
            } else {
                maxPair.len = recAns2.len;
                maxPair.str = recAns2.str;
            }
        }

        return dp[i][j] = maxPair;

    }

    public static int longestPlaindromeSubsequence_DP(String str, int i, int j, int[][] dp) {
        int n = str.length();

        String[][] sdp = new String[n][n];
        for (String[] d : sdp)
            Arrays.fill(d, "");

        for (int gap = 0; gap < n; gap++) {
            for (i = 0, j = gap; j < n; j++, i++) {
                if (i == j) {
                    dp[i][j] = 1;
                    continue;
                }

                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    sdp[i][j] = str.charAt(i) + sdp[i + 1][j - 1] + str.charAt(j);

                } else if (dp[i + 1][j] > dp[i][j - 1]) {
                    dp[i][j] = dp[i + 1][j];
                    sdp[i][j] = sdp[i + 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1];
                    sdp[i][j] = sdp[i][j - 1];
                }

            }
        }

        System.out.println(sdp[0][n - 1] + " @ " + dp[0][n - 1]);
        return dp[0][n - 1];
    }

    // Leetcode 115
    // https://practice.geeksforgeeks.org/problems/find-number-of-times-a-string-occurs-as-a-subsequence/0

    public static int numDistinct_(String s, String t, int n, int m, int[][] dp) {
        if (n < m)
            return dp[n][m] = 0;
        if (m == 0)
            return dp[n][m] = 1;

        if (dp[n][m] != -1)
            return dp[n][m];

        if (s.charAt(n - 1) == t.charAt(m - 1)) {
            return dp[n][m] = numDistinct_(s, t, n - 1, m - 1, dp) + numDistinct_(s, t, n - 1, m, dp);
        } else
            return dp[n][m] = numDistinct_(s, t, n - 1, m, dp);

    }

    public static int numDistinct_DP_(String s, String t, int n, int m, int[][] dp) {
        int N = n, M = m;
        for (n = 0; n <= N; n++) {
            for (m = 0; m <= M; m++) {
                if (n < m) {
                    dp[n][m] = 0;
                    continue;
                }
                if (m == 0) {
                    dp[n][m] = 1;
                    continue;
                }

                if (s.charAt(n - 1) == t.charAt(m - 1)) {
                    dp[n][m] = dp[n - 1][m - 1] + dp[n - 1][m];
                } else
                    dp[n][m] = dp[n - 1][m];
            }
        }
        return dp[N][M];
    }

    public static int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        // int ans = numDistinct_(s, t, n, m, dp);
        int ans = numDistinct_DP_(s, t, n, m, dp);

        for (int[] d : dp) {
            for (int ele : d) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        return ans;
    }

    // ===================================================================================================

    public static void String_set() {
        String str = "effbccbade";
        int n = str.length();
        int[][] dp = new int[n][n];

        // longestPalindromeSubstring("effbccbad");
        // System.out.println(longestPlaindromeSubsequence(str, 0, n - 1, dp));
        System.out.println(longestPlaindromeSubsequence_DP(str, 0, n - 1, dp));

        // pair[][] dp = new pair[n][n];
        // pair ans = longestPlaindromeSubsequence_02(str, 0, n - 1, dp);
        // System.out.println(ans.str + "@" + ans.len);

        // display2D(dp);
    }

    public static void pathSet() {
        // int n = 4, m = 4;
        // int[][] dp = new int[n][m];

        // System.out.println(mazePath_Rec_01(0, 0, n - 1, m - 1, dp));
        // System.out.println(mazePath_DP_01(0, 0, n - 1, m - 1, dp));

        // System.out.println(mazePath_Rec_02(0, 0, n - 1, m - 1, dp));
        // System.out.println(mazePath_DP_02(0, 0, n - 1, m - 1, dp));

        // int n = 10;
        // int[] dp = new int[n + 1];
        // int[] moves = { 2, 4, 1, 5 };
        // System.out.println(boardPath_Rec_01(0, n, dp));
        // System.out.println(boardPath_DP_01(0, n, dp));
        // System.out.println(boardPath_opti(n));

        // System.out.println(boardPath_DP_02(0, n, moves, dp));

        count_of_ways(5, 3);

        // display(dp);
        // display2D(dp);
    }

    public static void set1() {
        int n = 17;
        int[] dp = new int[n + 1];

        System.out.println(fibo_Rec(n, dp));
        // System.out.println(fibo_DP(n, dp));
        System.out.println(fibo_Opti(n));

        display(dp);
    }

    public static void solve() {
        // set1();
        // pathSet();
        String_set();
    }

    public static void main(String... args) {
        solve();

    }

}