import java.util.*;

public class l005_cutSet {

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

    public static int mcm_memo(int[] arr, int si, int ei, int[][] dp) {
        if (si + 1 == ei)
            return dp[si][ei] = 0;

        if (dp[si][ei] != -1)
            return dp[si][ei];
        int minRes = (int) 1e8;
        for (int cut = si + 1; cut < ei; cut++) {
            int leftRes = mcm_memo(arr, si, cut, dp);
            int rightRes = mcm_memo(arr, cut, ei, dp);

            int res = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
            if (res < minRes)
                minRes = res;
        }

        return dp[si][ei] = minRes;
    }

    public static int mcm_tabu(int[] arr, int SI, int EI, int[][] dp) {
        int n = arr.length;
        String[][] sdp = new String[n][n];
        for (String[] d : sdp)
            Arrays.fill(d, "");

        for (int gap = 1; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (si + 1 == ei) {
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char) (si + 'A') + "";
                    continue;
                }

                int minRes = (int) 1e8;
                String minStr = "";
                for (int cut = si + 1; cut < ei; cut++) {
                    int leftRes = dp[si][cut];// mcm_memo(arr, si, cut, dp);
                    int rightRes = dp[cut][ei];// m cm_memo(arr, cut, ei, dp);

                    int res = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
                    if (res < minRes) {
                        minRes = res;
                        minStr = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                    }
                }

                dp[si][ei] = minRes;
                sdp[si][ei] = minStr;
            }
        }

        System.out.println(sdp[SI][EI]);
        return dp[SI][EI];
    }

    public static void mcm() {
        int[] arr = { 4, 2, 3, 3, 4, 4, 5 };
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        System.out.println(mcm_tabu(arr, 0, n - 1, dp));
        display2D(dp);
    }

    public static void main(String[] args) {
        mcm();
    }

}