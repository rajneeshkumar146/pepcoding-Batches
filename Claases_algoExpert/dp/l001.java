public class l001 {

    /*
     * 1. faith
     * 2. Tree Diagaram
     * 3. Recursion
     * 4. Recursion -> Memoization
     * 5. Observation for loop order
     * 6. Memoization -> tabulation
     */

    public static int fibo_memo(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = n;
        }

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
            dp[n] = dp[n - 1] + dp[n - 2];// fibo(n - 1) + fibo(n - 2);
        }

        return dp[N];
    }

    public static void fibo() {
        int n = 10;
        int[] dp = new int[n + 1];
        System.out.println(fibo_memo(n, dp));
        System.out.println(fibo_tabu(n, dp));
    }

    public int climbStairs_memo(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = 1;
        }

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

    // coinChange=====================================================

    public static int coinChangePermutation(int[] arr, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 1;
        }

        if (dp[tar] != 0)
            return dp[tar];

        int count = 0;
        for (int coin : arr) {
            if (tar - coin >= 0)
                count += coinChangePermutation(arr, tar - coin, dp);
        }

        return dp[tar] = count;
    }

    public static void coinChange() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        int[] dp = new int[tar + 1];
        System.out.println(coinChangePermutation(arr, tar, dp));
    }

    // 322
    public int minCoins(int[] arr, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 0;
        }

        if (dp[tar] != (int) 1e9)
            return dp[tar];

        int minCoin = (int) 1e8;
        for (int coin : arr) {
            if (tar - coin >= 0)
                minCoin = Math.min(minCoins(arr, tar - coin, dp) + 1, minCoin);
        }

        return dp[tar] = minCoin;
    }

    public int coinChange(int[] arr, int tar) {
        int[] dp = new int[tar + 1];
        Arrays.fill(dp, (int) 1e9);
        int ans = minCoins(arr, tar, dp);
        return ans != (int) 1e8 ? ans : -1;
    }

    public static void main(String... args) {
        fibo();
    }
}