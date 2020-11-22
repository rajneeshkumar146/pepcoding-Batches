import java.util.PriorityQueue;
public class questions{

    //Leetcode 215
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int ele : nums){
            pq.add(ele);
            if(pq.size() > k) pq.remove();
        }

        return pq.peek();
    }
}