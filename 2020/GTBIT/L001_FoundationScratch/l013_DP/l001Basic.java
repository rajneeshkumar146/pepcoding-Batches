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

    public static void main(String[] args) {
        fibo();
    }
}