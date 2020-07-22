import java.util.*;
import java.lang.*;
import java.io.*;

class frinePairingProblem {
    static int mod = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        while (t-- > 0) {
            int n = scn.nextInt();
            friendsPairingProblem(n);
        }

    }

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

            single = pairUp;
            pairUp = ans;
        }

        return ans;
    }

    public static void friendsPairingProblem(int n) {
        // int n=scn.nextInt();
        long[] dp = new long[n + 1];
        long ans = friendsPairingProblem(n, dp);

        System.out.println(ans);
    }

}