import java.util.ArrayList;
import java.util.List;

public class l003CoinPermutation {
    List<Integer> smallAns = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    public void combinationSum(int[] arr, int idx, int target) {
        if (idx == arr.length || target == 0) {
            if (target == 0) {
                List<Integer> base = new ArrayList<>(smallAns);
                res.add(base);
            }
            return;
        }

        if (target - arr[idx] >= 0) {
            smallAns.add(arr[idx]);
            combinationSum(arr, idx, target - arr[idx]);
            smallAns.remove(smallAns.size() - 1);
        }
        combinationSum(arr, idx + 1, target);
    }

    public List<List<Integer>> combinationSum(int[] arr, int target) {
        combinationSum(arr, 0, target);
        return res;

    }

    // 46
    public void permute(int[] nums, int count, boolean[] vis) {
        if (count == nums.length) {

            res.add(new ArrayList<>(smallAns));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!vis[i]) {
                vis[i] = true;
                smallAns.add(nums[i]);
                permute(nums, count + 1, vis);
                smallAns.remove(smallAns.size() - 1);
                vis[i] = false;
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        boolean[] vis = new boolean[nums.length];
        permute(nums, 0, vis);
        return res;
    }

    // Queen

    // tnq = total no of queens, qpsf = queen placed so far
    // tnb = totak no of boxes, bno : box no
    public static int queenCombination1D(int tnb, int bno, int tnq, int qpsf, String ans) {
        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int b = bno; b < tnb; b++) {
            count += queenCombination1D(tnb, b + 1, tnq, qpsf + 1, ans + "b" + b + "q" + qpsf + " ");
        }

        return count;
    }

    public static int queenPermutation1D(boolean[] tnb, int tnq, int qpsf, String ans) {
        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int b = 0; b < tnb.length; b++) {
            if (!tnb[b]) {
                tnb[b] = true;
                count += queenPermutation1D(tnb, tnq, qpsf + 1, ans + "b" + b + "q" + qpsf + " ");
                tnb[b] = false;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // System.out.println(queenCombination1D(5, 0, 3, 0, ""));
        boolean[] tnb = new boolean[5];
        System.out.println(queenPermutation1D(tnb, 3, 0, ""));

    }

}