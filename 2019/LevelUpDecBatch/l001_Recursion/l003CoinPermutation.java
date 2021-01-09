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
}