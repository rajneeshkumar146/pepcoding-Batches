import java.util.LinkedList;
import java.util.Arrays;

public class l001Basic {
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

    public static int fibo_memo(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = n;
        }

        if (dp[n] != 0)
            return dp[n];

        int ans = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
        return dp[n] = ans;
    }

    public static int fibo_DP(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }

            int ans = dp[n - 1] + dp[n - 2];// fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
            dp[n] = ans;
        }

        return dp[N];
    }

    public static int fibo_Opti(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int sum = a + b;
            a = b;
            b = sum;
        }

        return a;
    }

    public static void fibo() {
        int n = 6;
        int[] dp = new int[n + 1];
        fibo_DP(n, dp);
        System.out.println(fibo_Opti(n));

        print(dp);
    }

    public int climbStairs(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        int steps = climbStairs(n - 1, dp) + climbStairs(n - 2, dp);
        return dp[n] = steps;
    }

    public int climbStairs_DP(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            int steps = dp[n - 1] + dp[n - 2];// climbStairs(n-1,dp) + climbStairs(n-2,dp);
            dp[n] = steps;
        }

        return dp[N];
    }

    public int climbStairs_Opti(int n) {
        int a = 1, b = 1;
        for (int i = 0; i < n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }

        return a;

    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        // int ans = climbStairs(n,dp);
        // int ans = climbStairs_DP(n,dp);
        int ans = climbStairs_Opti(n);

        return ans;
    }

    public int minCostClimbingStairs(int[] cost, int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = cost[n];
        }
        if (dp[n] != 0)
            return dp[n];

        int minCostOfOneStep = minCostClimbingStairs(cost, n - 1, dp);
        int minCostOfTwoStep = minCostClimbingStairs(cost, n - 2, dp);

        int ans = Math.min(minCostOfOneStep, minCostOfTwoStep) + (n != cost.length ? cost[n] : 0);

        return dp[n] = ans;
    }

    public int minCostClimbingStairs_DP(int[] cost, int N, int[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = cost[n];
                continue;
            }

            int minCostOfOneStep = dp[n - 1];// minCostClimbingStairs(cost, n - 1, dp);
            int minCostOfTwoStep = dp[n - 2];// minCostClimbingStairs(cost, n - 2, dp);

            int ans = Math.min(minCostOfOneStep, minCostOfTwoStep) + (n != cost.length ? cost[n] : 0);

            dp[n] = ans;
        }

        return dp[N];
    }

    public int minCostClimbingStairs_Opti(int[] cost, int N) {

        int a = cost[0], b = cost[1];
        for (int i = 2; i <= N; i++) {
            int minVal = Math.min(a, b) + (i != cost.length ? cost[i] : 0);
            a = b;
            b = minVal;
        }
        return b;
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        int ans = minCostClimbingStairs(cost, n, dp);
        return ans;

    }

    public static int boardPath_memo(int n, int[] dp) {
        if (n == 0) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        int count = 0;
        for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
            count += boardPath_memo(n - dice, dp);
        }

        return dp[n] = count;
    }

    public static int boardPath_dp(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n == 0) {
                dp[n] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
                count += dp[n - dice];// boardPath_memo(n - dice, dp);
            }

            dp[n] = count;
        }
        return dp[N];
    }

    public static int boardPath_Opti(int n) {
        LinkedList<Integer> ll = new LinkedList<>();

        ll.addLast(1);
        ll.addLast(1);
        for (int i = 2; i <= n; i++) {
            if (ll.size() <= 6)
                ll.addLast(ll.getLast() * 2);
            else
                ll.addLast(ll.getLast() * 2 - ll.removeFirst());
        }

        return ll.getLast();
    }

    public static void boardPath() {
        int n = 20;
        int[] dp = new int[n + 1];
        // System.out.println(boardPath_memo(n, dp));
        System.out.println(boardPath_dp(n, dp));
        System.out.println(boardPath_Opti(n));

        print(dp);
    }

    public static int mazePath_HDV(int sr, int sc, int er, int ec, int[][] dir, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                count += mazePath_HDV(r, c, er, ec, dir, dp);
            }
        }

        return dp[sr][sc] = count;
    }

    public static int mazePath_HDV_DP(int SR, int SC, int er, int ec, int[][] dir, int[][] dp) {

        for (int sr = er; sr >= 0; sr--) {
            for (int sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                        count += dp[r][c];// mazePath_HDV(r, c, er, ec, dir, dp);
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public static int mazePathJump_HDV(int sr, int sc, int er, int ec, int[][] dir, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];
                if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                    count += mazePathJump_HDV(r, c, er, ec, dir, dp);
                } else
                    break;
            }
        }

        return dp[sr][sc] = count;
    }

    public static int mazePathJump_HDV_DP(int SR, int SC, int er, int ec, int[][] dir, int[][] dp) {

        for (int sr = er; sr >= 0; sr--) {
            for (int sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                        int r = sr + rad * dir[d][0];
                        int c = sc + rad * dir[d][1];
                        if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                            count += dp[r][c];// mazePathJump_HDV(r, c, er, ec, dir, dp);
                        } else
                            break;
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public static void mazePath() {
        int n = 4, m = 5;
        int[][] dp = new int[n][m];
        int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 } };

        // System.out.println(mazePath_HDV(0, 0, n - 1, m - 1, dir, dp));
        // System.out.println(mazePath_HDV_DP(0, 0, n - 1, m - 1, dir, dp));
        System.out.println(mazePathJump_HDV(0, 0, n - 1, m - 1, dir, dp));
        print2D(dp);

    }

    // Friends Pairing
    public static int friendsPairing_memo(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        int single = friendsPairing_memo(n - 1, dp);
        int pairup = friendsPairing_memo(n - 2, dp) * (n - 1);

        return dp[n] = single + pairup;
    }

    public static int friendsPairing_dp(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            int single = dp[n - 1];// friendsPairing_memo(n - 1, dp);
            int pairup = dp[n - 2] * (n - 1);// friendsPairing_memo(n - 2, dp) * (n - 1);

            dp[n] = single + pairup;
        }
        return dp[N];
    }

    public static int frindsPairing_Opti(int n) {
        int a = 1, b = 1;
        for (int i = 2; i <= n; i++) {
            int sum = b + a * (i - 1);
            a = b;
            b = sum;
        }

        return b;
    }

    public static void frindsPairing() {
        int n = 11;
        int[] dp = new int[n + 1];

        System.out.println(friendsPairing_dp(n, dp));
        print(dp);

        System.out.println(frindsPairing_Opti(n));
    }

    public static int goldMine_memo(int[][] arr, int r, int c, int[][] dp, int[][] dir) {
        if (c == arr[0].length - 1) {
            return dp[r][c] = arr[r][c];
        }

        if (dp[r][c] != -1)
            return dp[r][c];

        int maxGold = 0;
        for (int d = 0; d < dir.length; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if (x >= 0 && y >= 0 && x < arr.length && y < arr[0].length)
                maxGold = Math.max(maxGold, goldMine_memo(arr, x, y, dp, dir) + arr[r][c]);
        }

        return dp[r][c] = maxGold;
    }

    public static int goldMine_memo(int[][] arr) {

        int n = arr.length, m = arr[0].length;
        int[][] dir = { { 0, 1 }, { 1, 1 }, { -1, 1 } };

        int[][] dp = new int[n][m];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int maxGold = 0;
        for (int i = 0; i < n; i++) {
            maxGold = Math.max(maxGold, goldMine_memo(arr, i, 0, dp, dir));
        }

        print2D(dp);
        return maxGold;
    }

    public static int goldMine_dp(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int[][] dir = { { 0, 1 }, { 1, 1 }, { -1, 1 } };

        int[][] dp = new int[n][m];
        for (int c = arr[0].length - 1; c >= 0; c--) {
            for (int r = arr.length - 1; r >= 0; r--) {
                if (c == arr[0].length - 1) {
                    dp[r][c] = arr[r][c];
                    continue;
                }

                int maxGold = 0;
                for (int d = 0; d < dir.length; d++) {
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if (x >= 0 && y >= 0 && x < arr.length && y < arr[0].length)
                        maxGold = Math.max(maxGold, dp[x][y] + arr[r][c]);
                }

                dp[r][c] = maxGold;
            }
        }

        int maxGold = 0;
        for (int i = 0; i < n; i++) {
            maxGold = Math.max(maxGold, dp[i][0]);
        }

        print2D(dp);
        return maxGold;
    }

    public static void goldMine() {
        int[][] arr = { { 10, 33, 13, 15 }, { 22, 21, 04, 1 }, { 5, 0, 2, 3 }, { 0, 6, 14, 2 } };
        System.out.println(goldMine_dp(arr));
    }

    // min Cost path.======================================
    public static int minCostPath(int[][] arr, int r, int c, int[][] dp, int[][] dir) {
        int n = arr.length, m = arr[0].length;
        if (r == n - 1 && c == m - 1) {
            return dp[r][c] = arr[r][c];
        }

        if (dp[r][c] != (int) 1e9)
            return dp[r][c];
        int minCost = (int) 1e9;
        for (int d = 0; d < dir.length; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if (x >= 0 && y >= 0 && x < n && y < m)
                minCost = Math.min(minCost, minCostPath(arr, x, y, dp, dir) + arr[r][c]);
        }

        return dp[r][c] = minCost;
    }

    public static void minCost() {
        int[][] arr;

        int n = arr.length, m = arr[0].length;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };

        int[][] dp = new int[n][m];

        for (int[] d : dp)
            Arrays.fill(d, (int) 1e9);

        System.out.println(minCostPath(arr, 0, 0, dp, dir));
        print2D(dp);
    }

    public static void main(String[] args) {
        goldMine();
    }
}