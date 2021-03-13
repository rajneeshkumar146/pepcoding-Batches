import java.util.Arrays;

public class l001 {

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

    public static void main(String[] args) {
        // fibo();
        mazePath();
    }

}