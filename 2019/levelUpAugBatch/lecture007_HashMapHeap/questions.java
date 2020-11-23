import java.util.PriorityQueue;
import java.util.HashSet;
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

//349
public int[] intersection(int[] nums1, int[] nums2) {
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int ele : nums1){
        map.put(ele,map.getOrDefault(ele,0) + 1);
    }
    
    ArrayList<Integer> ans = new ArrayList<>();

    for(int ele : nums2){
        if(map.containsKey(ele)){
            ans.add(ele);
            map.remove(ele);
        }
    }

    int[] arr = new int[ans.size()];
    int i = 0;
    
    for(int ele:ans) arr[i++] = ele;
    
    return arr;
}
//350
public int[] intersect(int[] nums1, int[] nums2) {
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int ele : nums1){
        map.put(ele,map.getOrDefault(ele,0) + 1);
    }
    
    ArrayList<Integer> ans = new ArrayList<>();

    for(int ele : nums2){
        if(map.containsKey(ele)){
            ans.add(ele);
            map.put(ele,map.get(ele) - 1);
            if(map.get(ele) == 0) map.remove(ele);
        }
    }

    int[] arr = new int[ans.size()];
    int i = 0;
    
    for(int ele:ans) arr[i++] = ele;
    
    return arr;
}

//128
public int longestConsecutive(int[] nums) {
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int ele : nums) map.put(ele,1);
    
    int len = 0;
    for(int ele : nums){
        if(!map.containsKey(ele)) continue;

        map.remove(ele);
        int prev = ele - 1;
        int next = ele + 1;

        while(map.containsKey(prev)){
            map.remove(prev); prev--;
        }

        while(map.containsKey(next)){
            map.remove(next); next++;
        }

        len = Math.max(len, next - prev - 1);
    }

    return len;
}

//347
public int[] topKFrequent(int[] nums, int k) {
        
    HashMap<Integer,Integer>map = new HashMap<>();
    for(int ele : nums) map.put(ele,map.getOrDefault(ele,0) + 1);
    
    // data,freq
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
        return a[1] - b[1];//other - this
    });
    
    for(int ele : map.keySet()){
        pq.add(new int[]{ele,map.get(ele)});
        if(pq.size() > k) pq.remove(); 
    }
    
    int[] ans = new int[k];
    int idx = 0;
    while(pq.size() != 0){
        int[] d = pq.remove();
        ans[idx++] = d[0];
    }
    
    return ans;   
}
// 347
public int[] topKFrequent(int[] nums, int k) {
        
    HashMap<Integer,Integer>map = new HashMap<>();
    for(int ele : nums) map.put(ele,map.getOrDefault(ele,0) + 1);
    
    // data,freq
    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
        return map.get(a) - map.get(b);//other - this
    });
    
    for(int ele : map.keySet()){
        pq.add(ele);
        if(pq.size() > k) pq.remove(); 
    }
    
    int[] ans = new int[k];
    int idx = 0;
    while(pq.size() != 0){
        ans[idx++] = pq.remove();
    }
    
    return ans;
    
}
