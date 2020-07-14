import java.util.Collections;
import java.util.PriorityQueue;

public class l001Heap {

    public static void test1() {
        int[] arr = { 2, 3, 11, 5, -1, 1, 7, 23, 6, 2, 237 };

        // PriorityQueue<Integer> pq = new PriorityQueue<>(); //min PQ
        // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // //MaxPQ

        boolean isMax = true;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if (!isMax)
                return a - b; // this-other for default. for MIN_PQ
            else
                return b - a; // other - this for default. for MAX_PQ
        });

        for (int ele : arr) // nlogn
            pq.add(ele);

        while (pq.size() != 0) {
            System.out.print(pq.remove() + " ");
        }
    }

    public static void solve() {
        test1();
    }

    public static void main(String[] args) {
        solve();
    }

}