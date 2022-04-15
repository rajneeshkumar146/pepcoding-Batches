import java.util.*;

public class l001_leetcode {
    // 215
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int ele : nums) {
            pq.add(ele);
            if (pq.size() > k)
                pq.remove();
        }

        return pq.peek();
    }

    // 1985
    public String kthLargestNumber(String[] nums, int k) {
        // this - other give default behaviour(min PQ)
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> { // first argument is this and second is other
            if (nums[a].length() != nums[b].length())
                return nums[a].length() - nums[b].length();

            int n = nums[a].length();
            for (int i = 0; i < n; i++) {
                if(nums[a].charAt(i) != nums[b].charAt(i)) return nums[a].charAt(i) - nums[b].charAt(i);
            }
            return 0;
        });

        for (int i = 0; i < nums.length; i++) {
            pq.add(i);
            if (pq.size() > k)
                pq.remove();
        }

        return nums[pq.peek()];
    }
}