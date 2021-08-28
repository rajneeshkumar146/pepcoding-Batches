import java.util.*;

public class l004_targetSet {

    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] ar : dp) {
            display(ar);
        }
        System.out.println();
    }

    public static int permutation_memo(int[] arr, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 1;
        }

        if (dp[tar] != -1)
            return dp[tar];
        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0) {
                count += permutation_memo(arr, tar - ele, dp);
            }
        }

        return dp[tar] = count;
    }

    public static int permutation_tabu(int[] arr, int Tar, int[] dp) {
        for (int tar = 0; tar <= Tar; tar++) {
            if (tar == 0) {
                dp[tar] = 1;
                continue;
            }
            int count = 0;
            for (int ele : arr) {
                if (tar - ele >= 0) {
                    count += dp[tar - ele];
                }
            }
            dp[tar] = count;
        }

        return dp[Tar];
    }

    public static int combination_memo(int[] arr, int tar, int n, int[][] dp) {
        if (tar == 0) {
            return dp[n][tar] = 1;
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (tar - arr[i] >= 0) {
                count += combination_memo(arr, tar - arr[i], i + 1, dp);
            }
        }

        return dp[n][tar] = count;
    }

    public static int combination_tabu(int[] arr, int Tar) {
        int[] dp = new int[arr.length];
        for (int ele : arr) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        return dp[Tar];
    }

    public static void coinSet() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10, n = arr.length;

        // int[] dp = new int[tar + 1];
        // Arrays.fill(dp, -1);
        // System.out.println(permutation_tabu(arr, tar, dp));
        // display(dp);

        int[][] dp = new int[n + 1][tar + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        System.out.println(combination_memo(arr, tar, n, dp));
        display2D(dp);
    }

    public static void main(String[] args) {
        coinSet();
    }

}