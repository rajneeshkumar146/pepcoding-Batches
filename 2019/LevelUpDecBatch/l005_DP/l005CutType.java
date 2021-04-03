public class l005CutType {
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

    public static int mcm_memo(int[] arr, int si, int ei, int[][] dp) {
        if (si + 1 == ei) {
            return dp[si][ei] = 0;
        }

        if (dp[si][ei] != 0)
            return dp[si][ei];

        int minAns = (int) 1e9;

        for (int cut = si + 1; cut < ei; cut++) {
            int lans = mcm_memo(arr, si, cut, dp);
            int rans = mcm_memo(arr, cut, ei, dp);

            minAns = Math.min(minAns, lans + arr[si] * arr[cut] * arr[ei] + rans);
        }

        return dp[si][ei] = minAns;
    }

    // cost of one multiplication is 1$
    public static int mcm_dp(int[] arr, int SI, int EI, int[][] dp) {

        int n = arr.length;
        for (int gap = 1; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (si + 1 == ei) {
                    dp[si][ei] = 0;
                    continue;
                }

                int minAns = (int) 1e9;
                for (int cut = si + 1; cut < ei; cut++) {
                    int lans = dp[si][cut];// mcm_memo(arr, si, cut, dp);
                    int rans = dp[cut][ei];// mcm_memo(arr, cut, ei, dp);

                    minAns = Math.min(minAns, lans + arr[si] * arr[cut] * arr[ei] + rans);
                }

                dp[si][ei] = minAns;
            }
        }

        return dp[SI][EI];
    }

    // cost of one multiplication is 3$ and cost of 1 addition is 5$.
    public static int mcm_dp2(int[] arr, int SI, int EI, int[][] dp) {

        int n = arr.length;
        for (int gap = 1; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (si + 1 == ei) {
                    dp[si][ei] = 0;
                    continue;
                }

                int minAns = (int) 1e9;
                for (int cut = si + 1; cut < ei; cut++) {
                    int lans = dp[si][cut];// mcm_memo(arr, si, cut, dp);
                    int rans = dp[cut][ei];// mcm_memo(arr, cut, ei, dp);

                    minAns = Math.min(minAns, lans + arr[si] * (3 * arr[cut] + 5 * (arr[cut] - 1)) * arr[ei] + rans);
                }

                dp[si][ei] = minAns;
            }
        }

        return dp[SI][EI];
    }

    public static class pair {
        int minValue = (int) 1e9;
        int maxValue = -(int) 1e9;

        String minExpression = "";
        String maxExpression = "";

        pair() {

        }

        pair(int minValue, int maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        pair(int minValue, int maxValue, String minExpression, String maxExpression) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.minExpression = minExpression;
            this.maxExpression = maxExpression;
        }
    }

    public static int evaluate(int a, int b, char ch) {
        if (ch == '+')
            return a + b;
        else
            return a * b;
    }

    public static pair minMaxEvalutaion(String str, int si, int ei, pair[][] dp) {
        if (si == ei) {
            int val = str.charAt(si) - '0';
            return dp[si][ei] = new pair(val, val, val + "", val + "");
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        pair res = new pair();
        for (int cut = si + 1; cut < ei; cut += 2) {
            pair lans = minMaxEvalutaion(str, si, cut - 1, dp); // (si,i-1)
            pair rans = minMaxEvalutaion(str, cut + 1, ei, dp); // (i+1,ei);, first number = (si, i-1), second number =
                                                                // (i +1, ii - 1), i = ii

            int minValue = evaluate(lans.minValue, rans.minValue, str.charAt(cut));
            int maxValue = evaluate(lans.maxValue, rans.maxValue, str.charAt(cut));

            // res.minValue = Math.min(res.minValue, minValue);
            // res.maxValue = Math.max(res.maxValue, maxValue);

            if (minValue < res.minValue) {
                res.minValue = minValue;
                res.minExpression = "(" + lans.minExpression + " " + str.charAt(cut) + " " + rans.minExpression
                        + ")";
            }

            if (maxValue > res.maxValue) {
                res.maxValue = maxValue;
                res.maxExpression = "(" + lans.maxExpression + " " + str.charAt(cut) + " " + rans.maxExpression
                        + ")";
            }

        }

        return dp[si][ei] = res;
    }

    public static void minMaxEvalutaion() {
        String str = "1+2*3+4*5";
        int n = str.length();
        pair[][] dp = new pair[n][n];

        pair res = minMaxEvalutaion(str, 0, n - 1, dp);
        System.out.println("Min Value: " + res.minValue + "\nMin Expression: " + res.minExpression);
        System.out.println("Max Value: " + res.maxValue + "\nMax Expression: " + res.maxExpression);

    }

    public static void mcm() {
        int[] arr = { 40, 20, 30, 10, 30, 50, 60 };
        int n = arr.length;
        int[][] dp = new int[n][n];

        System.out.println(mcm_dp(arr, 0, n - 1, dp));
        print2D(dp);
    }

    public static void main(String[] args) {
        minMaxEvalutaion();
    }

}