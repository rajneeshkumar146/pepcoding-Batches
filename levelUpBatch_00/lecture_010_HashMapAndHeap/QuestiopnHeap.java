import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;
public class QuestiopnHeap{
    //leetcode 215.===========================================

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq=new PriorityQueue<>();  // by default min PQ
        for(int ele: nums){
              pq.add(ele);
            if(pq.size()>k) 
              pq.poll();
        }

        return pq.top();
    }

    public int[] topKFrequent(int[] nums, int k) {
    HashMap<Integer,Integer> map=new HashMap<>();
    for (int ele : nums)
        map.put(ele, map.getOrDefault(ele,0) + 1);

    // first -> freq, second -> number and by default it is min PQ.
    PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->{
        return a[0]-b[0];
    });

    for ( Integer key: map.keySet())
    {
        pq.add(new int[2]{map.get(key),key});
        if(pq.size()>k) pq.remove();
    }

    int[] ans=new int[pq.size()];
    int i=0;
    while(pq.size()!=0){
        int[] ar=pq.remove();
        ans[i++]=ar[1];
    }

    return ans;
}

}