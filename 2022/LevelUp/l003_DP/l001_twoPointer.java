public class l001_twoPointer {
    /*
     * 1. Faith
     * 2. Tree Diagram
     * 3. Recursion
     * 4. Recursion -> Memoization
     * 5. Obervation
     * 6. Memoization -> Tabulation after observation
     * 7. Optimization
     */

    public static void display(int[] dp) {
        for (int ele : dp)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] d : dp)
            display(d);
    }

    public static int fibo_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;
        if (dp[n] != 0)
            return dp[n];

        return dp[n] = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
    }

    public static int fibo_tabu(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }

            dp[n] = dp[n - 1] + dp[n - 2];// fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
        }
        return dp[N];
    }

    public static int fibo_opti(int N) {
        int a = 0, b = 1;
        for (int n = 0; n < N; n++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }

    public static void fibo() {
        int n = 12;
        int[] dp = new int[n + 1];
        // System.out.println(fibo_memo(n, dp));
        System.out.println(fibo_tabu(n, dp));
        System.out.println(fibo_opti(n));
        display(dp);
    }

    // 1137
    public int tribonacci_memo(int n, int[] dp) {
        if (n <= 2) {
            return dp[n] = (n == 0 ? 0 : 1);
        }

        if (dp[n] != 0)
            return dp[n];
        return dp[n] = tribonacci_memo(n - 1, dp) + tribonacci_memo(n - 2, dp) + tribonacci_memo(n - 3, dp);
    }

    public int tribonacci_tabu(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 2) {
                dp[n] = (n == 0 ? 0 : 1);
                continue;
            }

            dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3];
        }

        return dp[N];
    }

    public int tribonacci_opti(int N) {
        int a = 0, b = 1, c = 1;
        for (int n = 0; n < N; n++) {
            int sum = a + b + c;
            a = b;
            b = c;
            c = sum;
        }

        return a;
    }

    public int tribonacci(int n) {
        int[] dp = new int[n + 1];
        int ans = tribonacci_memo(n, dp);
        display(dp);
        return ans;
    }

    // 70
    public int climbStairs_memo(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = 1;
        if (dp[n] != 0)
            return dp[n];

        return dp[n] = climbStairs_memo(n - 1, dp) + climbStairs_memo(n - 2, dp);
    }

    public int climbStairs_tabu(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            dp[n] = dp[n - 1] + dp[n - 2];
        }
        return dp[N];
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        return climbStairs_memo(n, dp);
    }

    // 746
    public int minCostClimbingStairs_memo(int[] cost, int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = cost[n];
        }

        if (dp[n] != 0)
            return dp[n];

        int fcall = minCostClimbingStairs_memo(cost, n - 1, dp);
        int scall = minCostClimbingStairs_memo(cost, n - 2, dp);
        int ans = Math.min(fcall, scall) + (n == cost.length ? 0 : cost[n]);

        return dp[n] = ans;
    }

    public int minCostClimbingStairs_tabu(int[] cost, int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = cost[n];
                continue;
            }

            dp[n] = Math.min(dp[n - 1], dp[n - 2]) + (n == cost.length ? 0 : cost[n]);
        }

        return dp[N];
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        return minCostClimbingStairs_memo(cost, n, dp);
    }

    // HW :
    // https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1

    public static long friendsPairing_memo(int n, long[] dp) {
        if (n <= 1) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        long single = friendsPairing_memo(n - 1, dp);
        long pairUp = friendsPairing_memo(n - 2, dp) * (n - 1);

        return dp[n] = single + pairUp;
    }

    public long friendsPairing_tabu(int N, long[] dp) {
        long mod = (long) 1e9 + 7;
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }
            dp[n] = (dp[n - 1] % mod + (dp[n - 2] % mod * (n - 1)) % mod) % mod;
        }

        return dp[N];
    }

    public long countFriendsPairings(int n) {
        long[] dp = new long[n + 1];
        long ans = friendsPairing_tabu(n, dp);
        return ans;
    }

    // maze Path

    public static int mazePath_memo(int er, int ec, int[][] dp, int[][] dir) {
        if (er == 0 && ec == 0) {
            return dp[er][ec] = 1;
        }

        if (dp[er][ec] != 0)
            return dp[er][ec];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];
            if (r >= 0 && c >= 0 && r < dp.length && c < dp.length) {
                count += mazePath_memo(r, c, dp, dir);
            }
        }

        return dp[er][ec] = count;
    }

    public static int mazePath_tabu(int ER, int EC, int[][] dp, int[][] dir) {
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    if (r >= 0 && c >= 0 && r < dp.length && c < dp.length) {
                        count += dp[r][c];
                    }
                }
                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    public static int mazePathJump_memo(int er, int ec, int[][] dp, int[][] dir) {
        if (er == 0 && ec == 0) {
            return dp[er][ec] = 1;
        }

        if (dp[er][ec] != 0)
            return dp[er][ec];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = er + dir[d][0];
            int c = ec + dir[d][1];
            while (r >= 0 && c >= 0 && r < dp.length && c < dp.length) {
                count += mazePathJump_memo(r, c, dp, dir);
                r += dir[d][0];
                c += dir[d][1];
            }
        }

        return dp[er][ec] = count;
    }

    public static int mazePathJump_tabu(int ER, int EC, int[][] dp, int[][] dir) {
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    while (r >= 0 && c >= 0 && r < dp.length && c < dp.length) {
                        count += mazePathJump_memo(r, c, dp, dir);
                        r += dir[d][0];
                        c += dir[d][1];
                    }
                }

                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    public static void mazePath() {
        int n = 3, m = 3;
        int er = n - 1, ec = m - 1;
        int[][] dp = new int[n][m];
        int[][] dir = { { -1, 0 }, { 0, -1 }, { -1, -1 } };

        int ans = mazePathJump_memo(er, ec, dp, dir);
        display2D(dp);

        System.out.println(ans);
    }
    
    public static void main(String[] args) {
        mazePath();
    }
}