import java.util.*;

public class l003_RecursionTree {

    public static int infiPermutation(int[] coins, int tar, String asf) {
        if (tar == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += infiPermutation(coins, tar - coins[i], asf + coins[i] + " ");
            }
        }

        return count;
    }

    public static int infiCombination(int[] coins, int tar, int idx, String asf) {
        if (tar == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += infiCombination(coins, tar - coins[i], i, asf + coins[i] + " ");
            }
        }

        return count;
    }

    public static int singleCombination(int[] coins, int tar, int idx, String asf) {
        if (tar == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += singleCombination(coins, tar - coins[i], i + 1, asf + coins[i] + " ");
            }
        }

        return count;
    }

    public static int singlePermutation(int[] coins, int tar, String asf) {
        if (tar == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] > 0 && tar - coins[i] >= 0) {
                int val = coins[i];
                coins[i] = -coins[i];

                count += singlePermutation(coins, tar - val, asf + val + " ");

                coins[i] = -coins[i];
            }
        }

        return count;
    }

    public static int singlePermutation(int[] coins, int tar, boolean[] vis, String asf) {
        if (tar == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (!vis[i] && tar - coins[i] >= 0) {
                vis[i] = true;
                count += singlePermutation(coins, tar - coins[i], vis, asf + coins[i] + " ");
                vis[i] = false;
            }
        }

        return count;
    }

    public static int singleCombination_subseq(int[] coins, int tar, int idx, String asf) {
        if (tar == 0 || idx >= coins.length) {
            if (tar == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - coins[idx] >= 0)
            count += singleCombination_subseq(coins, tar - coins[idx], idx + 1, asf + coins[idx] + " ");
        count += singleCombination_subseq(coins, tar, idx + 1, asf);

        return count;
    }

    public static int infiCombination_subseq(int[] coins, int tar, int idx, String asf) {
        if (tar == 0 || idx >= coins.length) {
            if (tar == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - coins[idx] >= 0)
            count += infiCombination_subseq(coins, tar - coins[idx], idx, asf + coins[idx] + " ");
        count += infiCombination_subseq(coins, tar, idx + 1, asf);

        return count;
    }

    public static int infiPermutation_subseq(int[] coins, int tar, int idx, String asf) {
        if (tar == 0 || idx >= coins.length) {
            if (tar == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - coins[idx] >= 0)
            count += infiPermutation_subseq(coins, tar - coins[idx], 0, asf + coins[idx] + " ");
        count += infiPermutation_subseq(coins, tar, idx + 1, asf);

        return count;
    }

    public static int singlePermutation_subseq(int[] coins, int tar, boolean[] vis, String asf) {
        return 0;
    }

    public static void coinchange() {
        // int[] coins = { 2, 3, 5, 7 };
        // int tar = 10;

        int[] coins = { 1, 1, 1, 1, 1, 1, 1 };
        int tar = 4;

        String asf = "";
        // System.out.println(infiPermutation(coins, tar, asf));
        // System.out.println(infiCombination(coins, tar, 0, asf));
        System.out.println(singleCombination(coins, tar, 0, asf));
        // System.out.println(singlePermutation(coins, tar, asf));

        // System.out.println(singleCombination_subseq(coins, tar, 0, asf));
        // System.out.println(infiCombination_subseq(coins, tar, 0, asf));
        // System.out.println(infiPermutation_subseq(coins, tar, 0, asf));
    }

    // questions.

    // 39
    public void combinationSum(int[] arr, int tar, int idx, List<List<Integer>> ans, List<Integer> smallAns) {

        if (tar == 0) {
            List<Integer> base = new ArrayList<>(smallAns); // deep copy
            ans.add(base); // shallow copy
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {
                smallAns.add(arr[i]);
                combinationSum(arr, tar - arr[i], i, ans, smallAns);
                smallAns.remove(smallAns.size() - 1);
            }
        }

    }

    public List<List<Integer>> combinationSum(int[] arr, int tar) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();

        combinationSum(arr, tar, 0, ans, smallAns);
        return ans;
    }

    public static int subsets(String str, int idx, StringBuilder asf, ArrayList<String> ans) {
        int count = 1;
        ans.add(asf.toString());
        for (int i = idx; i < str.length(); i++) {
            asf.append(str.charAt(i));
            count += subsets(str, i + 1, asf, ans);
            asf.deleteCharAt(asf.length() - 1);
        }
        return count;
    }

    public static void subsets(String str) {
        ArrayList<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder("");
        subsets(str, 0, sb, ans);
        System.out.println(ans);
    }

    // 40
    // 216
    // 322
    // 77
    // 377
    // 51
    // 52

    // Queen_Set.=========================================================================================

    // tnb : total no boxes, bno : box no, tnq : total No queen, qpsf : queen placed
    // so far
    public static int queenCombination1D(int tnb, int bno, int tnq, int qpsf, String asf) {
        if (qpsf > tnq) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int b = bno; b <= tnb; b++) {
            count += queenCombination1D(tnb, b + 1, tnq, qpsf + 1, asf + "b" + b + "q" + qpsf + " ");
        }
        return count;
    }

    public static int queenCombination1D_sub(int tnb, int bno, int tnq, int qpsf, String asf) {
        if (qpsf > tnq || bno > tnb) {
            if (qpsf > tnq) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        count += queenCombination1D_sub(tnb, bno + 1, tnq, qpsf + 1, asf + "b" + bno + "q" + qpsf + " ");
        count += queenCombination1D_sub(tnb, bno + 1, tnq, qpsf, asf);

        return count;
    }

    public static int queenPermutation1D(int tnb, int tnq, int qpsf, boolean[] vis, String asf) {
        if (qpsf > tnq) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int b = 1; b <= tnb; b++) {
            if (!vis[b]) {
                vis[b] = true;
                count += queenPermutation1D(tnb, tnq, qpsf + 1, vis, asf + "b" + b + "q" + qpsf + " ");
                vis[b] = false;
            }
        }
        return count;
    }

    public static int queenPermutation1D_sub(int tnb, int bno, int tnq, int qpsf, boolean[] vis, String asf) {
        if (qpsf > tnq || bno > tnb) {
            if (qpsf > tnq) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;

        if (!vis[bno]) {
            vis[bno] = true;
            count += queenPermutation1D_sub(tnb, 1, tnq, qpsf + 1, vis, asf + "b" + bno + "q" + qpsf + " ");
            vis[bno] = false;
        }
        count += queenPermutation1D_sub(tnb, bno + 1, tnq, qpsf, vis, asf);
        return count;
    }

    public static void queen() {
        int tnb = 16, tnq = 4;
        boolean[] vis = new boolean[tnb + 1];
        System.out.println(queenCombination1D_sub(tnb, 1, tnq, 1, ""));
        // System.out.println(queenPermutation1D(tnb, tnq, 1,vis, ""));

        // System.out.println(queenPermutation1D_sub(tnb, 1, tnq, 1, vis, ""));
    }

    // 2QueenSet.=============================================================================

    // tnb : total no boxes, bno : box no, tnq : total No queen, qpsf : queen placed
    // so far
    public static int queenCombination2D(boolean[][] box, int bno, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        int n = box.length, m = box[0].length;
        for (int b = bno; b < n * m; b++) {
            int r = b / m;
            int c = b % m;
            count += queenCombination2D(box, b + 1, tnq - 1, asf + "(" + r + "," + c + ") ");
        }
        return count;
    }

    public static int queenCombination2D_sub(int tnb, int bno, int tnq, int qpsf, String asf) {
        if (qpsf > tnq || bno > tnb) {
            if (qpsf > tnq) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;

        return count;
    }

    public static int queenPermutation2D(boolean[][] box, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        int n = box.length, m = box[0].length;
        for (int b = 0; b < n * m; b++) {
            int r = b / m;
            int c = b % m;
            if (!box[r][c]) {
                box[r][c] = true;
                count += queenPermutation2D(box, tnq - 1, asf + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }
        }
        return count;
    }

    public static int queenPermutation2D_sub(int tnb, int bno, int tnq, int qpsf, boolean[] vis, String asf) {
        if (qpsf > tnq || bno > tnb) {
            if (qpsf > tnq) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;

        return count;
    }

    public static void queen2D() {
        int tnq = 4;
        boolean[][] box = new boolean[4][4];
        System.out.println(queenCombination2D(box, 0, tnq, ""));
        // System.out.println(queenPermutation2D(box, tnq, ""));

        // System.out.println(queenPermutation1D_sub(tnb, 1, tnq, 1, vis, ""));
    }

    public static void main(String[] args) {
        // queen2D();
        subsets("123");
    }
}