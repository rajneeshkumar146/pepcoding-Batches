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
            count += singleCombination_subseq(coins, tar - coins[idx], idx + 1 , asf + coins[idx] + " ");
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
            count += infiCombination_subseq(coins, tar - coins[idx], idx , asf + coins[idx] + " ");
        count += infiCombination_subseq(coins, tar, idx + 1, asf);

        return count;
    }

    public static int infiPermutation_subseq(int[] coins, int tar,int idx, String asf) {
        if (tar == 0 || idx >= coins.length) {
            if (tar == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - coins[idx] >= 0)
            count += infiPermutation_subseq(coins, tar - coins[idx], 0 , asf + coins[idx] + " ");
        count += infiPermutation_subseq(coins, tar, idx + 1, asf);

        return count;
    }

    public static int singlePermutation_subseq(int[] coins, int tar, boolean[] vis, String asf) {

    }

    public static void coinchange() {
        int[] coins = { 2, 3, 5, 7 };
        int tar = 10;
        String asf = "";
        // System.out.println(infiPermutation(coins, tar, asf));
        // System.out.println(infiCombination(coins, tar, 0, asf));
        // System.out.println(singleCombination(coins, tar, 0, asf));
        // System.out.println(singlePermutation(coins, tar, asf));

        // System.out.println(singleCombination_subseq(coins, tar, 0, asf));
        // System.out.println(infiCombination_subseq(coins, tar, 0, asf));
        System.out.println(infiPermutation_subseq(coins, tar, 0, asf));
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

    public static void main(String[] args) {
        coinchange();
    }
}