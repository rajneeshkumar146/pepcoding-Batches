import java.util.PriorityQueue;
import java.util.Collections;

public class questionHeap{

    //Leetcode 215
    public int findKthLargest(int[] nums, int k) {
        // PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverse()); // by default min -> max

        // PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->{  // this, other
        //     return b - a;     // this - other -> default, other - this -> reverse of default
        // }); // by default min -> max


        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int ele: nums){
            pq.add(ele);
            if(pq.size()>k) pq.remove();
        }

        return pq.remove();
    }

    public int findKthSmallest(int[] nums, int k) {
        // PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverse()); // by default min -> max

        // PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->{  // this, other
        //     return b - a;     // this - other -> default, other - this -> reverse of default
        // }); // by default min -> max


        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->{
            return b - a;
        });

        for(int ele: nums){
            pq.add(ele);
            if(pq.size()>k) pq.remove();
        }

        return pq.remove();
    }

    class KthLargest {
        
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        int K = 0;
        public KthLargest(int k, int[] nums) {
            this.K = k;
            for(int ele: nums){
                add(ele);
            }
        }
        
        public int add(int val) {
            this.pq.add(val);
            if(this.pq.size() > K) 
               this.pq.remove();
            
            return this.pq.peek();
        }
    }

//Leetcode 378  
public int kthSmallest(int[][] matrix, int k) {

    int n = matrix.length;
    int m = matrix[0].length;

    // val, i, j
    PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->{
        return a[0] - b[0]; // default pq (min).
    }); 

    for(int i=0;i<n;i++) pq.add(new int[]{matrix[i][0],i,0});

    while(k-- > 1){
        int[] rEle = pq.remove();
        int r = rEle[1];
        int c = rEle[2];

        if(c + 1 < m) pq.add(new int[]{matrix[r][c + 1],r,c + 1});
    }

    return pq.remove()[0];
}

//Leetcode 973
public class kClosestPair implements Comparable<kClosestPair>{
    int i = 0;
    int j = 0;

    kClosestPair(int i,int j){
        this.i=i;
        this.j=j;
    }

    @Override
    public int compareTo(kClosestPair o){
        int r1 = this.i * this.i + this.j * this.j;
        int r2 = o.i * o.i + o.j * o.j;
        
        return r2 - r1;   // other - this
    }
}

public int[][] kClosest(int[][] points, int K) {
    PriorityQueue<kClosestPair> pq=new PriorityQueue<>();

    for(int[] p: points){
        pq.add(new kClosestPair(p[0],p[1]));
        if(pq.size() > k) pq.remove();
    }

    int[][] ans=new int[K][2];
    
    int i = 0;
    while(pq.size()!=0){
        kClosestPair p = pq.remove();
        ans[i][0] = p.i;
        ans[i][1] = p.j; 
        i++;
    }

    return ans;
}

}