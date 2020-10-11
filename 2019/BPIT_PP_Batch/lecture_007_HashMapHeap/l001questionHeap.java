public class l001questionHeap{
    
    //Leetcode 215
    int findKthLargest(int[] nums, int k){

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int ele: nums){
            pq.add(ele);
            if(pq.size() > k) pq.poll();
        }

        return pq.peek();
    }

    // Leetcode 703
    class KthLargest {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int size = 0;
        public KthLargest(int k, int[] nums) {
            this.size = k;
            for(int ele:nums){
                pq.add(ele);
                if(pq.size() > this.size) pq.poll();
            }
        }
        
        public int add(int val) {
            pq.add(val);
            if(pq.size() > this.size) pq.poll();
            return pq.peek();
        }
    }
}


//378
public int kthSmallest(int[][] matrix, int k) {

    int n = matrix.length;
    int m = matrix[0].length;

    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
        return matrix[a/m][a%m] - matrix[b/m][b%m];
    });

    for(int i=0;i<n;i++) pq.add(i * m + 0);

    while(--k > 0){
        int idx = pq.poll();
        int r = idx / m;
        int c = idx % m;

        c++;
        if(c < m) pq.add(r * m + c);
    }

    int val = pq.peek();
    return Matrix[val/m][val%m];
}

// 973
public int[][] kClosest(int[][] points, int K) {
    int n = points.length;
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
        return  (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]);
    }); 

    for(int[] d : points){
        pq.add(d);
        if(pq.size() > K) pq.poll();
    }

    int[][] ans = new int[pq.size()][2];
    int idx =0;

    while(pq.size()!=0){
        ans[idx++] = pq.poll();
    }
    
    return ans;
}

// https://www.geeksforgeeks.org/nearly-sorted-algorithm/

public static void nearlySorted(int[] arr,int k){
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    k++;

    int n = arr.length;
    int idx = 0; 
    for(int i = 0; i < n;i++){
        pq.add(arr[i]);
        if(pq.size() > k){
            arr[idx++] = pq.poll();
        }
    }

    while(pq.size()!=0) arr[idx++] = pq.poll();
}

//356
public boolean isReflected(int[][] points) {
    HashSet<String> map = new HashSet<>();
    int min = (int)1e9;
    int max = -(int)1e9;
    
    for(int[] p : points){
        max = Math.max(max,p[0]);
        min = Math.min(min,p[0]);
        
        String str = p[0] + "@" + p[1];
        map.add(str);
    }
    
    int sum = max + min;
    for(int[] p : points){
        String str = (sum - p[0]) + "@" + p[1];
        if(!map.contains(str)) return false;
    }
    
    return true;   
}
