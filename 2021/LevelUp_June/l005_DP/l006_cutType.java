public class l006_cutType {

    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for (int[] d : dp) {
            display(d);
        }
        System.out.println();
    }

    public static int mcm_memo(int[] arr, int si, int ei, int[][] dp) {
        if (ei - si == 1) {
            return dp[si][ei] = 0;
        }

        if (dp[si][ei] != 0)
            return dp[si][ei];

        int minRes = (int) 1e9;
        for (int cut = si + 1; cut < ei; cut++) {
            int leftRes = mcm_memo(arr, si, cut, dp);
            int rightRes = mcm_memo(arr, cut, ei, dp);

            minRes = Math.min(minRes, leftRes + arr[si] * arr[cut] * arr[ei] + rightRes);
        }

        return dp[si][ei] = minRes;
    }

    public static int mcm_Dp(int[] arr, int SI, int EI, int[][] dp) {
        int n = arr.length;
        String[][] sdp = new String[n][n];

        for (int gap = 1; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (ei - si == 1) {
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char) (si + 'A') + "";
                    continue;
                }

                int minRes = (int) 1e9;
                String minStr = "";
                for (int cut = si + 1; cut < ei; cut++) {
                    int leftRes = dp[si][cut];// mcm_memo(arr, si, cut, dp);
                    int rightRes = dp[cut][ei];// mcm_memo(arr, cut, ei, dp);

                    if (leftRes + arr[si] * arr[cut] * arr[ei] + rightRes < minRes) {
                        minRes = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
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
        int[] arr = { 10, 30, 5, 60 };
        int n = arr.length;
        int[][] dp = new int[n][n];

        System.out.println(mcm_Dp(arr, 0, n - 1, dp));

        display2D(dp);
    }

    // Min Max =======================================================

    public static class pairmm {
        int min = (int) 1e9;
        int max = 0;

        pairmm() {

        }

        pairmm(int val) {
            this.min = this.max = val;
        }
    }

    public static pairmm evaluateMinMax(pairmm leftRes, pairmm rightRes, char operator) {
        pairmm pair = new pairmm();
        if (operator == '+') {
            pair.min = leftRes.min + rightRes.min;
            pair.max = leftRes.max + rightRes.max;
        } else if (operator == '*') {
            pair.min = leftRes.min * rightRes.min;
            pair.max = leftRes.max * rightRes.max;
        }
        return pair;
    }

    public static pairmm minMax(String str, int si, int ei, pairmm[][] dp) {
        if (si == ei) {
            return dp[si][ei] = new pairmm((str.charAt(si) - '0'));
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        pairmm myRes = new pairmm();
        for (int cut = si + 1; cut < ei; cut += 2) {
            pairmm leftRes = minMax(str, si, cut - 1, dp);
            pairmm rightRes = minMax(str, cut + 1, ei, dp);
            pairmm pair = evaluateMinMax(leftRes, rightRes, str.charAt(cut));

            myRes.min = Math.min(myRes.min, pair.min);
            myRes.max = Math.max(myRes.max, pair.max);
        }

        return dp[si][ei] = myRes;
    }

    public static void minMax() {
        String str = "1+2*3+4*5";
        int n = str.length();
        pairmm[][] dp = new pairmm[n][n];

        pairmm res = minMax(str, 0, n - 1, dp);

        System.out.println("Min value: " + res.min);
        System.out.println("Max value: " + res.max);
    }

    // 312

    public static void main(String... args) {
        // mcm();
        minMax();
    }

}