public class questions {
    // 46
    public void permute(int[] nums, int count, List<List<Integer>> res, List<Integer> ans) {
        if (count == nums.length) {
            List<Integer> base = new ArrayList<>();
            for (int ele : ans)
                base.add(ele);
            res.add(base);
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] >= -10 && nums[i] <= 10) {
                int val = nums[i];

                nums[i] = -11;
                ans.add(val);

                permute(nums, count + 1, res, ans);

                ans.remove(ans.size() - 1);
                nums[i] = val;
            }
        }

    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();

        permute(nums, 0, res, ans);

        return res;
    }

    //47
}