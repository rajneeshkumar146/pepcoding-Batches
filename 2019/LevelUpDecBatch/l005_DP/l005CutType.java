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
                res.minExpression = "(" + lans.minExpression + " " + str.charAt(cut) + " " + rans.minExpression + ")";
            }

            if (maxValue > res.maxValue) {
                res.maxValue = maxValue;
                res.maxExpression = "(" + lans.maxExpression + " " + str.charAt(cut) + " " + rans.maxExpression + ")";
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

    // 132

    public int minCut_memo(String str, int si, boolean[][] isPlaindrome, int[] dp) {
        if (isPlaindrome[si][str.length() - 1])
            return dp[si] = 0;
        if (dp[si] != -1)
            return dp[si];

        int minAns = (int) 1e9;
        for (int cut = si; cut < str.length(); cut++) {
            if (isPlaindrome[si][cut]) {
                minAns = Math.min(minAns, minCut_memo(str, cut + 1, isPlaindrome, dp) + 1);
            }
        }

        return dp[si] = minAns;
    }

    public int minCut_dp(String str, int SI, boolean[][] isPlaindrome, int[] dp) {
        for (int si = str.length() - 1; si >= 0; si--) {
            if (isPlaindrome[si][str.length() - 1]) {
                dp[si] = 0;
                continue;
            }

            int minAns = (int) 1e9;
            for (int cut = si; cut < str.length(); cut++) {
                if (isPlaindrome[si][cut]) {
                    minAns = Math.min(minAns, dp[cut + 1] + 1);
                }
            }

            dp[si] = minAns;
        }

        return dp[SI];
    }

    public int minCut(String str) {
        int n = str.length();
        boolean[][] isPlaindrome = new boolean[n][n];
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    isPlaindrome[i][j] = true;
                else if (gap == 1)
                    isPlaindrome[i][j] = str.charAt(i) == str.charAt(j);
                else
                    isPlaindrome[i][j] = str.charAt(i) == str.charAt(j) && isPlaindrome[i + 1][j - 1];
            }
        }

        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return minCut_memo(str, 0, isPlaindrome, dp);
    }

    // 312
    public int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if (dp[si][ei] != -1)
            return dp[si][ei];

        int lval = si - 1 == -1 ? 1 : nums[si - 1];
        int rval = ei + 1 == nums.length ? 1 : nums[ei + 1];

        int maxAns = 0;
        for (int cut = si; cut <= ei; cut++) {
            int lans = (cut == si) ? 0 : maxCoins(nums, si, cut - 1, dp);
            int rans = (cut == ei) ? 0 : maxCoins(nums, cut + 1, ei, dp);

            maxAns = Math.max(maxAns, lans + lval * nums[cut] * rval + rans);
        }

        return dp[si][ei] = maxAns;

    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return maxCoins(nums, 0, n - 1, dp);
    }

    // 1039
    public int minScoreTriangulation(int[] arr, int si, int ei, int[][] dp) {
        if (ei - si <= 1) {
            return dp[si][ei] = 0;
        }

        if (dp[si][ei] != -1)
            return dp[si][ei];

        int minAns = (int) 1e9;
        for (int cut = si + 1; cut < ei; cut++) {
            int lans = minScoreTriangulation(arr, si, cut, dp);
            int rans = minScoreTriangulation(arr, cut, ei, dp);

            minAns = Math.min(minAns, lans + arr[si] * arr[cut] * arr[ei] + rans);
        }

        return dp[si][ei] = minAns;

    }

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return minScoreTriangulation(values, 0, n - 1, dp);
    }

    public static class pairBoolean {
        int trueWays = 0;
        int falseWays = 0;

        pairBoolean(int trueWays, int falseWays) {
            this.trueWays = trueWays;
            this.falseWays = falseWays;
        }
    }

    public static pairBoolean Evaluate(pairBoolean left, pairBoolean right, char operator) {
        int mod = 1003;
        int TotalWays = ((left.trueWays + left.falseWays) % mod * (right.trueWays + right.falseWays) % mod) % mod;

        pairBoolean ans = new pairBoolean(0, 0);
        if (operator == '&') {
            ans.trueWays = (left.trueWays * right.trueWays) % mod;
            ans.falseWays = (TotalWays - ans.trueWays + mod) % mod;
        } else if (operator == '|') {
            ans.falseWays = (left.falseWays * right.falseWays) % mod;
            ans.trueWays = (TotalWays - ans.falseWays + mod) % mod;
        } else {
            ans.trueWays = (left.falseWays * right.trueWays) % mod + (left.trueWays * right.falseWays) % mod;
            ans.falseWays = (TotalWays - ans.trueWays + mod) % mod;
        }

        return ans;
    }

    public static pairBoolean booleanPare(String str, int si, int ei, pairBoolean[][] dp) {
        if (si == ei) {
            char ch = str.charAt(si);
            return new pairBoolean(ch == 'T' ? 1 : 0, ch == 'F' ? 1 : 0);
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        pairBoolean myAns = new pairBoolean(0, 0);
        for (int cut = si + 1; cut < ei; cut += 2) {
            char operator = str.charAt(cut);
            pairBoolean lans = booleanPare(str, si, cut - 1, dp);
            pairBoolean rans = booleanPare(str, cut + 1, ei, dp);

            pairBoolean recAns = Evaluate(lans, rans, operator);
            myAns.trueWays = (myAns.trueWays + recAns.trueWays) % 1003;
            myAns.falseWays = (myAns.falseWays + recAns.falseWays) % 1003;
        }

        return dp[si][ei] = myAns;
    }

    static int countWays(int N, String S) {
        pairBoolean[][] dp = new pairBoolean[N][N];
        pairBoolean ans = booleanPare(S, 0, N - 1, dp);

        return ans.trueWays;
    }

    public static void main(String[] args) {
        minMaxEvalutaion();
    }

}