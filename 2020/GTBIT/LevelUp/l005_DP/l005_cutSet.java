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

    public static class minMaxPair {
        int minVal = (int) 1e9;
        int maxVal = -(int) 1e9;

        minMaxPair(int minVal, int maxVal) {
            this.minVal = minVal;
            this.maxVal = maxVal;
        }

        minMaxPair() {

        }
    }

    public static minMaxPair evaluation(minMaxPair lp, minMaxPair rp, char operator) {
        if (operator == '+')
            return new minMaxPair(lp.minVal + rp.minVal, lp.maxVal + rp.maxVal);
        else
            return new minMaxPair(lp.minVal * rp.minVal, lp.maxVal * rp.maxVal);
    }

    public static minMaxPair minMaxEvaluation(String str, int si, int ei, minMaxPair[][] dp) {
        if (si == ei) {
            int val = str.charAt(si) - '0';
            return new minMaxPair(val, val);
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        minMaxPair myRes = new minMaxPair();
        for (int cut = si + 1; cut < ei; cut += 2) {
            minMaxPair leftPair = minMaxEvaluation(str, si, cut - 1, dp);
            minMaxPair rightPair = minMaxEvaluation(str, cut + 1, ei, dp);

            minMaxPair eval = evaluation(leftPair, rightPair, str.charAt(cut));
            myRes.minVal = Math.min(myRes.minVal, eval.minVal);
            myRes.maxVal = Math.max(myRes.maxVal, eval.maxVal);
        }

        return dp[si][ei] = myRes;
    }

    public static void minMaxEvaluation() {
        String str = "1+2*3+4*5";
        int n = str.length();

        minMaxPair[][] dp = new minMaxPair[n][n];
        minMaxPair res = minMaxEvaluation(str, 0, n - 1, dp);

        System.out.println("Min value: " + res.minVal);
        System.out.println("Max value: " + res.maxVal);
    }

    // 312
    public int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0)
            return dp[si][ei];

        int lval = si == 0 ? 1 : nums[si - 1];
        int rval = ei == nums.length - 1 ? 1 : nums[ei + 1];

        int maxCost = 0;
        for (int cut = si; cut <= ei; cut++) {
            int leftCost = cut == si ? 0 : maxCoins(nums, si, cut - 1, dp);
            int rightCost = cut == ei ? 0 : maxCoins(nums, cut + 1, ei, dp);

            int myCost = leftCost + lval * nums[cut] * rval + rightCost;
            maxCost = Math.max(maxCost, myCost);
        }

        return dp[si][ei] = maxCost;

    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        return maxCoins(nums, 0, n - 1, dp);
    }

    // https://practice.geeksforgeeks.org/problems/boolean-parenthesization5610/1

    public static class bpair {
        int tCount = 0;
        int fCount = 0;

        bpair(int tCount, int fCount) {
            this.fCount = fCount;
            this.tCount = tCount;
        }
    }

    public static void Evaluation(bpair left, bpair right, char oper, bpair ans) {
        int mod = 1003;
        int totalTF = (left.tCount + left.fCount) * (right.tCount + right.fCount);
        if (oper == '|') {
            int fCount = (left.fCount * right.fCount);
            ans.fCount += fCount;
            ans.tCount += totalTF - fCount;
        } else if (oper == '&') {
            int tCount = (left.tCount * right.tCount);
            ans.tCount += tCount;
            ans.fCount += totalTF - tCount;
        } else {
            int tCount = (left.tCount * right.fCount) + (left.fCount * right.tCount);
            ans.tCount += tCount;
            ans.fCount = totalTF - tCount;
        }
    }

    public static bpair countWays(String str, int si, int ei, bpair[][] dp) {
        if (si == ei) {
            int t = str.charAt(si) == 'T' ? 1 : 0;
            int f = str.charAt(si) == 'F' ? 1 : 0;

            bpair base = new bpair(t, f);
            return dp[si][ei] = base;
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        bpair ans = new bpair(0, 0);
        for (int cut = si + 1; cut < ei; cut += 2) {
            bpair lp = countWays(str, si, cut - 1, dp);
            bpair rp = countWays(str, cut + 1, ei, dp);

            char operator = str.charAt(cut);
            Evaluation(lp, rp, operator, ans);
        }

        return dp[si][ei] = ans;
    }

    static int countWays(int N, String S) {
        bpair[][] dp = new bpair[N][N];
        return countWays(S, 0, N - 1, dp).tCount;
    }

    public static void main(String[] args) {
        minMaxEvaluation();
    }

}