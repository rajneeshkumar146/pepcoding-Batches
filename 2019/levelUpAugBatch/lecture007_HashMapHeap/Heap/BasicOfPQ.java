import java.util.PriorityQueue;
import java.util.Collections;

public class BasicOfPQ{

    public static void set1(int[] arr){
        // PriorityQueue<Integer> pq = new PriorityQueue<>();  // minPQ
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return b - a;   // other - this, reverse of default.
        });  // maxPQ

        // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());  // maxPQ
        
        for (int ele : arr)
           pq.add(ele);
        while (pq.size() != 0){
           System.out.print(pq.remove() + " ");
        }
    }

    public static class pair implements Comparable<pair>{
        int val1 = 0;
        int val2 = 0;

        pair(int val1,int val2){
            this.val1 = val1;
            this.val2 = val2;
        }

        @Override
        public int compareTo(pair o){
            // return o.val1 - this.val1; // other - this.
            return this.val1 - o.val1; // this - other.
        }
    }

    public static void set2(int[][] arr){
        // PriorityQueue<pair> pq = new PriorityQueue<>((a,b)->{
        //     return a.val2 - b.val2;   // other - this, reverse of default.
        // });  // maxPQ

        PriorityQueue<pair> pq = new PriorityQueue<>();
        for (int[] ele : arr)
           pq.add(new pair(ele[0],ele[1]));

        while (pq.size() != 0){
           pair p = pq.peek();
           System.out.println(p.val1 + "," + p.val2);
           pq.remove();
        }
    }




    public static void main(String[] args){
        // int[] arr = {10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13};
        int[][] arr = {{10, 20}, {30, -2}, {-3, -4}, {5, 6}, {7, 8}, {9, 22}, {11, 13}};
        // set1(arr);
        set2(arr);

    }
}