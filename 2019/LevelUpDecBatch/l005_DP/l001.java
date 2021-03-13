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

    public static void main(String[] args) {
        fibo();
    }

}