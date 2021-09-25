import java.util.*;

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
    public int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0)
            return dp[si][ei];

        int lele = si == 0 ? 1 : nums[si - 1];
        int rele = ei == nums.length - 1 ? 1 : nums[ei + 1];

        int maxCoins = 0;
        for (int cut = si; cut <= ei; cut++) {
            int lcost = cut == si ? 0 : maxCoins(nums, si, cut - 1, dp);
            int rcost = cut == ei ? 0 : maxCoins(nums, cut + 1, ei, dp);

            maxCoins = Math.max(maxCoins, lcost + lele * nums[cut] * rele + rcost);
        }

        return dp[si][ei] = maxCoins;
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        return maxCoins(nums, 0, n - 1, dp);

    }

    public static class pairBoolen {
        long totalTrue = 0;
        long totalFalse = 0;

        pairBoolen(long totalTrue, long totalFalse) {
            this.totalFalse = totalFalse;
            this.totalTrue = totalTrue;
        }

        pairBoolen() {

        }
    }

    public static void evaluateTrue(pairBoolen left, pairBoolen right, pairBoolen res, char operator) {
        long mod = 1003;
        long totalTF = ((left.totalTrue + left.totalFalse) * (right.totalTrue + right.totalFalse)) % mod;
        long totalTrue = 0, totalFalse = 0;
        if (operator == '|') {
            totalFalse = (left.totalFalse * right.totalFalse) % mod;
            totalTrue = (totalTF - totalFalse + mod) % mod;
        } else if (operator == '^') {
            totalTrue = (left.totalFalse * right.totalTrue) % mod + (left.totalTrue * right.totalFalse) % mod;
            totalFalse = (totalTF - totalTrue + mod) % mod;

        } else if (operator == '&') {
            totalTrue = (left.totalTrue * right.totalTrue) % mod;
            totalFalse = (totalTF - totalTrue + mod) % mod;
        }

        res.totalFalse = (res.totalFalse + totalFalse) % mod;
        res.totalTrue = (res.totalTrue + totalTrue) % mod;
    }

    public static pairBoolen countWays(String S, int si, int ei, pairBoolen[][] dp) {
        if (si == ei) {
            char ch = S.charAt(si);
            int t = ch == 'T' ? 1 : 0;
            int f = ch == 'F' ? 1 : 0;
            return dp[si][ei] = new pairBoolen(t, f);
        }
        if (dp[si][ei] != null) {
            return dp[si][ei];
        }

        pairBoolen res = new pairBoolen();
        for (int cut = si + 1; cut < ei; cut += 2) {
            pairBoolen lres = countWays(S, si, cut - 1, dp);
            pairBoolen rres = countWays(S, cut + 1, ei, dp);

            evaluateTrue(lres, rres, res, S.charAt(cut));
        }

        return dp[si][ei] = res;
    }

    static int countWays(int N, String S) {
        pairBoolen[][] dp = new pairBoolen[N][N];
        return (int) countWays(S, 0, N - 1, dp).totalTrue;
    }

    public static int obst(int[] val, int[] freq, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0)
            return dp[si][ei];
        int minCost = (int) 1e8;

        int sum = 0;

        for (int i = si; i <= ei; i++)
            sum += freq[i];

        for (int cut = si; cut <= ei; cut++) {
            int lres = cut == si ? 0 : obst(val, freq, si, cut - 1, dp);
            int rres = cut == ei ? 0 : obst(val, freq, cut + 1, ei, dp);
            // sum += freq[cut];
            minCost = Math.min(minCost, lres + sum + rres); // sum : sumOfRange(freq, si,ei);
        }

        return dp[si][ei] = minCost; // minCost + sum
    }

    // 1039
    public int minScoreTriangulation(int[] values, int si, int ei, int[][] dp) {
        if (ei - si < 2)
            return dp[si][ei] = 0;

        if (dp[si][ei] != 0)
            return dp[si][ei];
        int minRes = (int) 1e9;
        for (int cut = si + 1; cut < ei; cut++) {
            int leftRes = minScoreTriangulation(values, si, cut, dp);
            int rightRes = minScoreTriangulation(values, cut, ei, dp);

            minRes = Math.min(minRes, leftRes + values[si] * values[cut] * values[ei] + rightRes);
        }

        return dp[si][ei] = minRes;
    }

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        return minScoreTriangulation(values, 0, n - 1, dp);
    }

    // 95
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void generateAlllBST(List<TreeNode> leftList, List<TreeNode> rightList, List<TreeNode> ans, int num) {
        for (TreeNode ln : leftList) {
            for (TreeNode rn : rightList) {
                TreeNode root = new TreeNode(num);
                root.left = ln;
                root.right = rn;
                ans.add(root);
            }
        }
    }

    public List<TreeNode> generateTrees(int si, int ei, List<TreeNode>[][] dp) {
        List<TreeNode> myAns = new ArrayList<>();
        if (si >= ei) {
            TreeNode root = (si == ei ? new TreeNode(si) : null);
            myAns.add(root);
            return myAns;
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        for (int cut = si; cut <= ei; cut++) {
            List<TreeNode> leftList = generateTrees(si, cut - 1, dp);
            List<TreeNode> rightList = generateTrees(cut + 1, ei, dp);

            generateAlllBST(leftList, rightList, myAns, cut);
        }

        return dp[si][ei] = myAns;
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[][] dp = new ArrayList[n][n];
        return generateTrees(1, n, dp);
    }

    // 576
    int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    int mod = (int) 1e9 + 7;

    public int findPaths(int n, int m, int K, int sr, int sc, int[][][] dp) {
        if (sr < 0 || sc < 0 || sr == n || sc == m) {
            return 1;
        }

        if (K == 0)
            return 0;

        if (dp[sr][sc][K] != -1)
            return dp[sr][sc][K];
        int count = 0;

        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            count = (count + findPaths(n, m, K - 1, r, c, dp)) % mod;

        }

        return dp[sr][sc][K] = count;
    }

    public int findPaths(int n, int m, int k, int r, int c) {
        int[][][] dpp = new int[n + 1][m + 1][k + 1];
        for (int[][] dp : dpp)
            for (int[] d : dp)
                Arrays.fill(d, -1);

        return findPaths(n, m, k, r, c, dpp);
    }

    public static void main(String... args) {
        // mcm();
        minMax();
    }

}