import java.util.PriorityQueue;
import java.util.Collections;
public class Question{
    public static void main(String[] args){
      int[] arr={12, 3, 5, 7, 19};
      int k=3;
      System.out.println(KthLargest(arr,k));
      System.out.println(KthSmallest(arr,k));
      
      pair ans=KthLargest_Index(arr,k);
      System.out.println(ans.val + " idx: -> " + ans.idx);
      
    }

    public static int KthLargest(int[] arr,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>();  //min PQ.
        for(int ele: arr){
            pq.add(ele);
            if(pq.size()>k){
                pq.remove();
            }
        }

        return pq.peek();  // Kth largest.
    }

    public static class pair implements Comparable<pair>{
        int val=0;
        int idx=0;

        pair(int val,int idx){
            this.val=val;
            this.idx=idx;
        }

        @Override
        public int compareTo(pair o){
            return this.val-o.val;  // min pQ.
            // return o.val-this.val;  // max pQ.
        }
    }

    public static pair KthLargest_Index(int[] arr,int k){
        PriorityQueue<pair> pq=new PriorityQueue<>();  //min PQ.
      
        for(int i=0;i<arr.length;i++){
            pq.add(new pair(arr[i],i));
            if(pq.size()>k){
                pq.remove();
            }
        }

        return pq.peek();  // Kth largest.
    }

    public static int KthSmallest(int[] arr,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());  //max PQ.
        for(int ele: arr){
            pq.add(ele);
            if(pq.size()>k){
                pq.remove();
            }
        }

        return pq.peek();  //Kth smallest
    }
}