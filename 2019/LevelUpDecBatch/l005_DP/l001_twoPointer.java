import java.util.Arrays;
import java.util.LinkedList;

public class l001_twoPointer {

    public static void print1D(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void print2D(int[][] arr) {
        for (int[] ar : arr) {
            print1D(ar);
        }
    }

    public static int fib_memo(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = n;
        }

        if (dp[n] != -1)
            return dp[n];

        int ans = fib_memo(n - 1, dp) + fib_memo(n - 2, dp);
        return dp[n] = ans;
    }

    public static int fib_DP(int N, int[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }

            int ans = dp[n - 1] + dp[n - 2];// fib_01(n - 1, dp) + fib_01(n - 2, dp);

            dp[n] = ans;
        }

        return dp[N];
    }

    public static int fib_twoPointer(int N) {
        int a = 0, b = 1;
        for (int n = 0; n < N; n++) {
            // System.out.print(a + " ");

            int sum = a + b;
            a = b;
            b = sum;
        }

        return a;
    }

    public static void fibo() {
        int n = 8;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        System.out.println(fib_memo(n, dp));
        System.out.println(fib_DP(n, dp));
        System.out.println(fib_twoPointer(n));

        print1D(dp);
    }

    public static int mazePath_memo(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        if (sc + 1 <= ec)
            count += mazePath_memo(sr, sc + 1, er, ec, dp);
        if (sr + 1 <= er)
            count += mazePath_memo(sr + 1, sc, er, ec, dp);
        if (sc + 1 <= ec && sr + 1 <= er)
            count += mazePath_memo(sr + 1, sc + 1, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazePath_DP(int SR, int SC, int er, int ec, int[][] dp) {
        for (int sr = er; sr >= 0; sr--) {
            for (int sc = ec; sc >= 0; sc--) {

                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                if (sc + 1 <= ec)
                    count += dp[sr][sc + 1];// mazePath_memo(sr, sc + 1, er, ec, dp);
                if (sr + 1 <= er)
                    count += dp[sr + 1][sc];// mazePath_memo(sr + 1, sc, er, ec, dp);
                if (sc + 1 <= ec && sr + 1 <= er)
                    count += dp[sr + 1][sc + 1]; // mazePath_memo(sr + 1, sc + 1, er, ec, dp);

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public static int mazePathInfi_memo(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int jump = 1; sc + jump <= ec; jump++)
            count += mazePathInfi_memo(sr, sc + jump, er, ec, dp);
        for (int jump = 1; sr + jump <= er; jump++)
            count += mazePathInfi_memo(sr + jump, sc, er, ec, dp);
        for (int jump = 1; sc + jump <= ec && sr + jump <= er; jump++)
            count += mazePathInfi_memo(sr + jump, sc + jump, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazePathInfi_DP(int SR, int SC, int er, int ec, int[][] dp) {

        for (int sr = er; sr >= SR; sr--) {
            for (int sc = ec; sc >= SC; sc--) {

                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int jump = 1; sc + jump <= ec; jump++)
                    count += dp[sr][sc + jump]; // mazePathInfi_memo(sr, sc + jump, er, ec, dp);
                for (int jump = 1; sr + jump <= er; jump++)
                    count += dp[sr + jump][sc]; // mazePathInfi_memo(sr + jump, sc, er, ec, dp);
                for (int jump = 1; sc + jump <= ec && sr + jump <= er; jump++)
                    count += dp[sr + jump][sc + jump]; // mazePathInfi_memo(sr + jump, sc + jump, er, ec, dp);

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public static void mazePath() {
        int n = 5;
        int m = 5;
        int[][] dp = new int[n][m];
        // System.out.println(mazePath_memo(0, 0, n - 1, m - 1, dp));
        // System.out.println(mazePathInfi_memo(0, 0, n - 1, m - 1, dp));
        System.out.println(mazePathInfi_DP(0, 0, n - 1, m - 1, dp));

        print2D(dp);
    }

    public static int boardPath_memo(int sp, int ep, int[] dp) {
        if (sp == ep) {
            return dp[sp] = 1;
        }

        if (dp[sp] != 0)
            return dp[sp];

        int count = 0;
        for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
            count += boardPath_memo(sp + dice, ep, dp);
        }

        return dp[sp] = count;
    }

    public static int boardPath_DP(int SP, int ep, int[] dp) {
        for (int sp = ep; sp >= SP; sp--) {
            if (sp == ep) {
                dp[sp] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
                count += dp[sp + dice];// boardPath_memo(sp + dice, ep, dp);
            }

            dp[sp] = count;
        }

        return dp[SP];
    }

    public static int boardPath_opti(int sp, int ep) {
        LinkedList<Integer> list = new LinkedList<>();

        for (sp = ep; sp >= 0; sp--) {
            if (sp >= ep - 1)
                list.addFirst(1);
            else {
                if (list.size() <= 6)
                    list.addFirst(list.getFirst() * 2);
                else
                    list.addFirst(list.getFirst() * 2 - list.removeLast());
            }
        }

        return list.getFirst();

    }

    public static void boardPath() {
        int n = 10;
        int[] dp = new int[n + 1];
        // System.out.println(boardPath_memo(0, n, dp));
        System.out.println(boardPath_DP(0, n, dp));
        System.out.println(boardPath_opti(0, n));

        print1D(dp);
    }

    public static int climbStairs_memo(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        return dp[n] = climbStairs_memo(n - 1, dp) + climbStairs_memo(n - 2, dp);
    }

    public static int climbStairs_dp(int N, int[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            dp[n] = dp[n - 1] + dp[n - 2]; // climbStairs_memo(n - 1, dp) + climbStairs_memo(n - 2, dp);
        }

        return dp[N];
    }

    public static int climbStairs(int n) {
        int[] dp = new int[n + 1];
        // int ans = climbStairs_memo(n, dp);
        int ans = climbStairs_dp(n, dp);
        print1D(dp);

        return ans;
    }

    public int minCostClimbingStairs(int n, int[] cost, int[] dp) {
        if (n <= 1)
            return dp[n] = cost[n];

        if (dp[n] != -1)
            return dp[n];

        int minCost = Math.min(minCostClimbingStairs(n - 1, cost, dp), minCostClimbingStairs(n - 2, cost, dp));

        return dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
    }

    public int minCostClimbingStairs_dp(int N, int[] cost, int[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = cost[n];
                continue;
            }

            int minCost = Math.min(dp[n - 1], dp[n - 2]);

            dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
        }

        return dp[N];
    }

    public int minCostClimbingStairs_Opti(int N, int[] cost) {
        int a = cost[0];
        int b = cost[1];
        for (int i = 2; i < N; i++) {
            int minCost = Math.min(a, b) + cost[i];
            a = b;
            b = minCost;
        }

        return Math.min(a, b);
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        // Arrays.fill(dp,-1);

        return minCostClimbingStairs_dp(n, cost, dp);
    }

    // https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1
    long mod = (int) 1e9 + 7;

    public long countFriendsPairings_memo(int n, long[] dp) {

        if (n <= 1) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        long single = countFriendsPairings_memo(n - 1, dp);
        long pairup = countFriendsPairings_memo(n - 2, dp) * (n - 1);

        return dp[n] = (single % mod + pairup % mod) % mod;
    }

    public long countFriendsPairings_dp(int N, long[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            long single = dp[n - 1]; // countFriendsPairings_memo(n-1,dp);
            long pairup = dp[n - 2] * (n - 1); // countFriendsPairings_memo(n-2,dp) * (n-1);

            dp[n] = (single % mod + pairup % mod) % mod;
        }

        return dp[N];
    }

    public static long printFriendsPairing(String friends, String ans) {
        if (friends.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        char ch = friends.charAt(0);
        long count = 0;
        count += printFriendsPairing(friends.substring(1), ans + ch + " ");
        for (int i = 1; i < friends.length(); i++) {
            String rstr = friends.substring(1, i) + friends.substring(i + 1);
            count += printFriendsPairing(rstr, ans + ch + friends.charAt(i) + " ");
        }

        return count;
    }

    public static void countFriendsPairings(int n) {
        // long[] dp = new long[n + 1];
        // return countFriendsPairings_memo(n, dp);
        System.out.println(printFriendsPairing("ABCDEF", ""));
    }

    public static int goldMine_memo(int r, int c, int[][] mat, int[][] dp, int[][] dir) {
        if (c == mat[0].length - 1) {
            return dp[r][c] = mat[r][c];
        }

        if (dp[r][c] != -1)
            return dp[r][c];

        int maxGold = 0;
        for (int d = 0; d < 3; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];
            if (x >= 0 && x < mat.length) {
                maxGold = Math.max(maxGold, goldMine_memo(x, y, mat, dp, dir));
            }
        }

        return dp[r][c] = maxGold + mat[r][c];
    }

    public static int goldMine_dp(int[][] mat, int[][] dp, int[][] dir) {
        int n = mat.length;
        int m = mat[0].length;

        for (int c = m - 1; c >= 0; c--) {
            for (int r = n - 1; r >= 0; r--) {

                if (c == mat[0].length - 1) {
                    dp[r][c] = mat[r][c];
                    continue;
                }

                int maxGold = 0;
                for (int d = 0; d < 3; d++) {
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    if (x >= 0 && x < n) {
                        maxGold = Math.max(maxGold, dp[x][y]);
                    }
                }

                dp[r][c] = maxGold + mat[r][c];
            }
        }

        int maxGold = 0;
        for (int i = 0; i < mat.length; i++) {
            maxGold = Math.max(maxGold, dp[i][0]);
        }

        return maxGold;
    }

    public static void goldMine() {
        int[][] mat = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2 } };

        int[][] dp = new int[mat.length][mat[0].length];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };

        int maxGold = 0;
        for (int i = 0; i < mat.length; i++) {
            maxGold = Math.max(maxGold, goldMine_memo(i, 0, mat, dp, dir));
        }

        System.out.println(goldMine_dp(mat, dp, dir));
        print2D(dp);
        // System.out.println(maxGold);
    }

    // 91
    int numDecodings(String s, int idx, int[] dp) {
        if (idx == s.length()) {
            return dp[idx] = 1;
        }

        if (dp[idx] != -1)
            return dp[idx];

        char ch1 = s.charAt(idx);
        if (ch1 == '0')
            return 0;

        int count = 0;
        count += numDecodings(s, idx + 1, dp);

        if (idx < s.length() - 1) {
            char ch2 = s.charAt(idx + 1);
            int num = (ch1 - '0') * 10 + (ch2 - '0');
            if (num <= 26)
                count += numDecodings(s, idx + 2, dp);
        }
        return dp[idx] = count;
    }

    int numDecodings_DP(String s, int IDX, int[] dp) {
        for (int idx = s.length(); idx >= 0; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            char ch1 = s.charAt(idx);
            if (ch1 == '0') {
                dp[idx] = 0;
                continue;
            }

            int count = 0;
            count += a;// numDecodings(s, idx + 1, dp);

            if (idx < s.length() - 1) {
                char ch2 = s.charAt(idx + 1);
                int num = (ch1 - '0') * 10 + (ch2 - '0');
                if (num <= 26)
                    count += b;// numDecodings(s, idx + 2, dp);
            }
            dp[idx] = count;
        }

        return dp[IDX];
    }

    int numDecodings_Opti(String s) {
        int a = 1, b = 0;
        for (int idx = s.length() - 1; idx >= 0; idx--) {

            int count = 0;
            char ch1 = s.charAt(idx);
            if (ch1 != '0') {

                count += a;

                if (idx < s.length() - 1) {
                    char ch2 = s.charAt(idx + 1);
                    int num = (ch1 - '0') * 10 + (ch2 - '0');
                    if (num <= 26)
                        count += b;
                }
            }
            b = a;
            a = count;
        }

        return a;
    }

    int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        int ans = numDecodings(s, 0, dp);

        for (int ele : dp)
            System.out.print(ele + " ");
        return ans;
    }

    // 639
    long numDecodings_memo(String s, int idx, long[] dp) {

        if (idx == s.length()) {
            return dp[idx] = 1;
        }

        if (dp[idx] != -1)
            return dp[idx];
        if (s.charAt(idx) == '0') {
            return 0;
        }

        long count = 0;
        char ch1 = s.charAt(idx);

        if (s.charAt(idx) == '*') {
            count = (count + 9 * numDecodings_memo(s, idx + 1, dp)) % mod;
            if (idx < s.length() - 1) {
                char ch2 = s.charAt(idx + 1);
                if (ch2 == '*')
                    count = (count + 15 * b) % mod;
                else if (ch2 >= '0' && ch2 <= '6')
                    count = (count + 2 * b) % mod;
                else if (ch2 > '6')
                    count = (count + b) % mod;

            }
        } else {
            count = (count + numDecodings_memo(s, idx + 1, dp)) % mod;
            if (idx < s.length() - 1) {
                if (s.charAt(idx + 1) != '*') {
                    char ch2 = s.charAt(idx + 1);
                    int num = (ch1 - '0') * 10 + (ch2 - '0');
                    if (num <= 26)
                        count = (count + b) % mod;
                } else {
                    if (s.charAt(idx) == '1')
                        count = (count + 9 * b) % mod;
                    else if (s.charAt(idx) == '2')
                        count = (count + 6 * b) % mod;
                }
            }
        }

        return dp[idx] = count;
    }

    long numDecodings_dp(String s, int IDX, long[] dp) {
        for (int idx = s.length(); idx >= 0; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            if (s.charAt(idx) == '0') {
                dp[idx] = 0;
                continue;
            }

            long count = 0;
            char ch1 = s.charAt(idx);

            if (s.charAt(idx) == '*') {
                count = (count + 9 * a) % mod;
                if (idx < s.length() - 1) {
                    char ch2 = s.charAt(idx + 1);
                    if (ch2 == '*')
                        count = (count + 15 * b) % mod;
                    else if (ch2 >= '0' && ch2 <= '6')
                        count = (count + 2 * b) % mod;
                    else if (ch2 > '6')
                        count = (count + b) % mod;

                }
            } else {
                count = (count + a) % mod;
                if (idx < s.length() - 1) {
                    if (s.charAt(idx + 1) != '*') {
                        char ch2 = s.charAt(idx + 1);
                        int num = (ch1 - '0') * 10 + (ch2 - '0');
                        if (num <= 26)
                            count = (count + b) % mod;
                    } else {
                        if (s.charAt(idx) == '1')
                            count = (count + 9 * b) % mod;
                        else if (s.charAt(idx) == '2')
                            count = (count + 6 * b) % mod;
                    }
                }
            }

            dp[idx] = count;
        }

        return (int) dp[IDX];
    }

    long numDecodings_opti(String s) {
        long a = 1, b = 0;
        for (int idx = s.length() - 1; idx >= 0; idx--) {

            long count = 0;
            char ch1 = s.charAt(idx);
            if (ch1 == '0') {
                count = 0;
            } else if (ch1 == '*') {
                count = (count + 9 * a) % mod;
                if (idx < s.length() - 1) {
                    char ch2 = s.charAt(idx + 1);
                    if (ch2 == '*')
                        count = (count + 15 * b) % mod;
                    else if (ch2 >= '0' && ch2 <= '6')
                        count = (count + 2 * b) % mod;
                    else if (ch2 > '6')
                        count = (count + b) % mod;

                }
            } else {
                count = (count + a) % mod;
                if (idx < s.length() - 1) {
                    if (s.charAt(idx + 1) != '*') {
                        char ch2 = s.charAt(idx + 1);
                        int num = (ch1 - '0') * 10 + (ch2 - '0');
                        if (num <= 26)
                            count = (count + b) % mod;
                    } else {
                        if (s.charAt(idx) == '1')
                            count = (count + 9 * b) % mod;
                        else if (s.charAt(idx) == '2')
                            count = (count + 6 * b) % mod;
                    }
                }
            }

            b = a;
            a = count;
        }

        return (int) a;
    }

    int numDecodings_II(String s) {
        long[] dp = new long[s.length() + 1];
        Arrays.fill(dp, -1);
        long ans = numDecodings_memo(s, 0, dp);
        return (int) ans;
    }

    // https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/
    public static int noOfWays(int n, int k, int[][] dp) {
        if (k == 1) {
            return dp[n][k] = 1;
        }
        if (n == k) {
            return dp[n][k] = 1;
        }

        if (dp[n][k] != 0)
            return dp[n][k];

        int uniqueGroup = noOfWays(n - 1, k - 1, dp);
        int partOfExisGroup = noOfWays(n - 1, k, dp) * k;

        return dp[n][k] = uniqueGroup + partOfExisGroup;
    }

    public static void main(String[] args) {
        // fibo();
        // mazePath();
        // boardPath();
        // climbStairs(10);
        // countFriendsPairings(10);
        goldMine();
    }
