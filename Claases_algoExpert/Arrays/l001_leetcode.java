public class l001_leetcode {

    // 001
    public int[] twoSum_Sol1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int val = target - nums[i];
            if (map.containsKey(val) && i != map.get(val)) {
                return new int[] { i, map.get(val) };
            }
        }

        return new int[0];
    }

    public int[] twoSum_Sol2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = target - nums[i];
            if (map.containsKey(val)) {
                return new int[] { map.get(val), i };
            } else {
                map.put(nums[i], i);
            }
        }

        return new int[0];
    }

    // 167
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length, si = 0, ei = n - 1;
        while (si < ei) {
            int sum = numbers[si] + numbers[ei];
            if (sum < target)
                si++;
            else if (sum > target)
                ei--;
            else
                return new int[] { si + 1, ei + 1 };
        }

        return new int[0];
    }

    // 15
    // O(N)
    public List<List<Integer>> twoSumUnique(int[] nums, int tar, int si, int ei) {
        List<List<Integer>> ans = new ArrayList<>();
        while (si < ei) {
            int sum = nums[si] + nums[ei];
            if (sum < tar)
                si++;
            else if (sum > tar)
                ei--;
            else {
                List<Integer> smallAns = new ArrayList<>();
                smallAns.add(nums[si]);
                smallAns.add(nums[ei]);
                ans.add(smallAns);

                si++;
                ei--;
                while (si < ei && nums[si - 1] == nums[si])
                    si++;
                while (si < ei && nums[ei + 1] == nums[ei])
                    ei--;
            }
        }

        return ans;
    }

    public void addEleInList(List<List<Integer>> res, List<List<Integer>> ans, int data) {
        if (ans.size() == 0 || ans.get(0).size() == 0)
            return;

        for (List<Integer> sa : ans) {
            List<Integer> smallList = new ArrayList<>();
            smallList.add(data);
            smallList.addAll(sa);
            res.add(smallList);
        }

    }

    // O(NLogN + N2);
    public List<List<Integer>> threeSum(int[] nums, int tar, int si) {
        // Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length, i = si;
        while (i < n) {
            List<List<Integer>> ans = twoSumUnique(nums, tar - nums[i], i + 1, n - 1);
            addEleInList(res, ans, nums[i]);
            i++;
            while (i < n && nums[i - 1] == nums[i])
                i++;
        }

        return res;
    }

    // 18
    public List<List<Integer>> fourSum(int[] nums, int tar) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length, i = 0;
        while (i < n) {
            List<List<Integer>> ans = threeSum(nums, tar - nums[i], i + 1);
            addEleInList(res, ans, nums[i]);
            i++;
            while (i < n && nums[i - 1] == nums[i])
                i++;
        }

        return res;
    }

    // 125
    public boolean isValidChar(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9');
    }

    public boolean isPalindrome(String s) {
        if (s.length() <= 1)
            return true;
        int n = s.length(), si = 0, ei = n - 1;
        while (si < ei) {
            char c1 = s.charAt(si++);
            char c2 = s.charAt(ei--);

            while (si < ei && !isValidChar(c1))
                c1 = s.charAt(si++);
            while (si < ei && !isValidChar(c2))
                c2 = s.charAt(ei--);

            if (isValidChar(c1) && isValidChar(c2) && Character.toLowerCase(c1) != Character.toLowerCase(c2))
                return false;
        }

        return true;
    }

    // 283
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        int pt = 0;
        for (int ele : nums) {
            if (ele != 0)
                nums[pt++] = ele;
        }

        while (pt < nums.length) {
            nums[pt++] = 0;
        }

    }
}