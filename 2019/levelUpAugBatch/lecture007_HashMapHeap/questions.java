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

// 703
class KthLargest {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int K = 0;
    
    public KthLargest(int k, int[] nums) {
         this.K = k;
        for(int ele : nums){
            pq.add(ele);
            if(pq.size() > k) pq.remove();
        }
    }
    
    public int add(int val) {
        pq.add(val);
        if(pq.size() > this.K) pq.remove();
        return pq.peek();
    }
}