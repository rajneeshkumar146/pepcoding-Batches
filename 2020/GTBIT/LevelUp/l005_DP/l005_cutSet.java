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

    // OBST

    public static int sumOfFreq(int i, int j, int[] freq) {
        int sum = 0;
        while (i <= j) {
            sum += freq[i];
            i++;
        }
        return sum;
    }

    public static int obst_memo(int[] nodes, int[] freq, int si, int ei, int[][] dp) {
        if (dp[si][ei] != -1)
            return dp[si][ei];

        int minAns = (int) 1e9;
        int sum = 0;
        for (int cut = si; cut <= ei; cut++) {
            int leftRes = si == cut ? 0 : obst_memo(nodes, freq, si, cut - 1, dp);
            int rightRes = ei == cut ? 0 : obst_memo(nodes, freq, cut + 1, ei, dp);

            sum += freq[cut];
            int myAns = leftRes + rightRes;
            minAns = Math.min(minAns, myAns);
        }

        return dp[si][ei] = minAns + sum;
    }

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

    public void generateAllBST(int num, List<TreeNode> left, List<TreeNode> right, List<TreeNode> ans) {
        if (left.size() != 0 && right.size() != 0) {
            for (int i = 0; i < left.size(); i++) {
                for (int j = 0; j < right.size(); j++) {
                    TreeNode root = new TreeNode(num);
                    root.left = left.get(i);
                    root.right = right.get(j);
                    ans.add(root);
                }
            }
        } else if (left.size() != 0) {
            for (int i = 0; i < left.size(); i++) {
                TreeNode root = new TreeNode(num);
                root.left = left.get(i);
                ans.add(root);
            }
        } else if (right.size() != 0) {
            for (int j = 0; j < right.size(); j++) {
                TreeNode root = new TreeNode(num);
                root.right = right.get(j);
                ans.add(root);
            }
        } else {
            ans.add(new TreeNode(num));
        }
    }

    public List<TreeNode> generateTrees(int si, int ei) {
        List<TreeNode> ans = new ListNode<>();
        for (int cut = si; cut <= ei; cut++) {
            List<TreeNode> leftList = generateTrees(si, cut - 1);
            List<TreeNode> rightList = generateTrees(cut + 1, ei);

            generateAllBST(cut, leftList, rightList, ans);
        }

        return ans;
    }

    public int minScoreTriangulation(int[] values, int si, int ei, int[][] dp) {
        if (ei - si <= 1) {
            return dp[si][ei] = 0;
        }

        if (dp[si][ei] != -1)
            return dp[si][ei];

        int minRes = (int) 1e9;
        for (int cut = si + 1; cut < ei; cut++) {
            int leftRes = minScoreTriangulation(values, si, cut, dp);
            int rightRes = minScoreTriangulation(values, cut, ei, dp);

            int myAns = leftRes + values[si] * values[cut] * values[ei] + rightRes;
            minRes = Math.min(myAns, minRes);
        }

        return dp[si][ei] = minRes;
    }

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return minScoreTriangulation(values, 0, n - 1, dp);
    }

    // 139
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();

        int len = 0;
        HashSet<String> words = new HashSet<>();
        for (String str : wordDict) {
            len = Math.max(str.length(), len);
            words.add(str);
        }

        boolean[] dp = new boolean[n + 1];

        dp[0] = true;
        for (int i = 0; i < n; i++) {
            if (!dp[i])
                continue;

            for (int l = 1; len <= len && i + l <= n; l++) {
                if (words.contains(s.substring(i, i + l)))
                    dp[i + l] = true;
            }
        }

        return dp[n];
    }

    public void dfs(String str, int idx, int len, String asf, List<String> ans, HashSet<String> words, boolean[] dp) {
        if (idx == str.length()) {
            ans.add(asf.substring(0, asf.length() - 1));
            return;
        }
        for (int l = 1; idx + l <= str.length() && l <= len; l++) {
            if (dp[idx + l] && words.contains(str.substring(idx, idx + l)))
                dfs(str, idx + l, len, asf + str.substring(idx, idx + l) + " ", ans, words, dp);
        }
    }

    public List<String> wordBreak_II(String s, List<String> wordDict) {
        int n = s.length();

        int len = 0;
        HashSet<String> words = new HashSet<>();
        for (String str : wordDict) {
            len = Math.max(str.length(), len);
            words.add(str);
        }

        boolean[] dp = new boolean[n + 1];

        dp[0] = true;
        for (int i = 0; i < n; i++) {
            if (!dp[i])
                continue;

            for (int l = 1; len <= len && i + l <= n; l++) {
                if (words.contains(s.substring(i, i + l)))
                    dp[i + l] = true;
            }
        }

        List<String> ans = new ArrayList<>();
        if (!dp[n])
            return ans;
        dfs(s, 0, len, "", ans, words, dp);
        return ans;
    }

    public static void main(String[] args) {
        minMaxEvaluation();
    }

}