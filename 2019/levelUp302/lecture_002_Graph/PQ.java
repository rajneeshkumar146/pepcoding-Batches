import java.util.*;

public class PQ {
    public static void main(String[] args) {
        PriorityQueue<pair> pq = new PriorityQueue<>();
        pq.add(new pair(10, 10));
        pq.add(new pair(2, 16));
        pq.add(new pair(5, 560));
        pq.add(new pair(20, 2340));
        pq.add(new pair(-8, 340));

        while (pq.size() != 0) {
            System.out.println(pq.peek().age + " " + pq.peek().wt);
            pq.remove();
        }

    }

    public static class pair implements Comparable<pair> {
        int age;
        int wt;

        pair(int age, int wt) {
            this.age = age;
            this.wt = wt;
        }

        @Override
        public int compareTo(pair o) {
            return this.age-o.age;  // min PQ.
            // return o.age-this.age;  // max PQ.
        }
    }
}