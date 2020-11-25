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


class RandomizedSet {
    HashMap<Integer,Integer> map = new HashMap<>();
    ArrayList<Integer> list = new ArrayList<>();
    Random rand = new Random();
       
     public RandomizedSet() {
         
     }
     
     /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
     public boolean insert(int val) {
         if(map.containsKey(val)) return false;
         
         map.put(val,list.size());
         list.add(val);
         
         return true;
     }
     
     /** Removes a value from the set. Returns true if the set contained the specified element. */
     public boolean remove(int val) {
         if(!map.containsKey(val)) return false;
         
         int cidx = map.get(val);
         int lidx = list.size() - 1;
         
         map.put(list.get(lidx),cidx);
         map.remove(val);
         
         list.set(cidx,list.get(lidx));
         list.remove(lidx);
         
         return true;
         
     }
     
     /** Get a random element from the set. */
     public int getRandom() {
         int idx = rand.nextInt(list.size());
         return list.get(idx);
     }
 }

 //295
 class MedianFinder {
    
    PriorityQueue<Integer> leftMaxHeap;
    PriorityQueue<Integer> rightMinHeap;
    
    public MedianFinder() {
       leftMaxHeap = new PriorityQueue<>((a,b)->{
        return b - a;
    }); 
        rightMinHeap = new PriorityQueue<>();
    
    }
    
    public void addNum(int num) {
      if(leftMaxHeap.size() == 0 || num <= leftMaxHeap.peek())leftMaxHeap.add(num);
      else rightMinHeap.add(num);
        
      if(leftMaxHeap.size() - rightMinHeap.size() == 2){
          rightMinHeap.add(leftMaxHeap.peek());
          leftMaxHeap.remove();
      }else if(leftMaxHeap.size() < rightMinHeap.size()){
          leftMaxHeap.add(rightMinHeap.peek());
          rightMinHeap.remove();
      }
    }
    
    public double findMedian() {
       if(leftMaxHeap.size() == rightMinHeap.size()) return (leftMaxHeap.peek() + rightMinHeap.peek())/2.0;
        else return leftMaxHeap.peek();
    }
}


//378
public int kthSmallest(int[][] matrix, int k) {
    if(matrix.length == 0 || matrix[0].length == 0) return 0;
    
    int n = matrix.length;
    int m = matrix[0].length;
    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
        return matrix[a/m][a%m] - matrix[b/m][b%m];
    });
    
    for(int i=0;i<n;i++) pq.add(i * m + 0);
    
    while(--k > 0){
        int idx = pq.remove();
        int r = idx / m;
        int c = idx % m;
        
        c++;
        if(c < m) pq.add( r * m + c);
    }
    
        int idx = pq.remove();
        int r = idx / m;
        int c = idx % m;

     return matrix[r][c];
}
 