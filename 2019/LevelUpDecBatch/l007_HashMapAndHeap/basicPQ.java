import java.util.PriorityQueue;

public class basicPQ {

    public static void test1_minPQ(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // Min PQ.
        for (int ele : arr)
            pq.add(ele);

        while (pq.size() != 0) {
            System.out.println(pq.poll());
        }
    }

    public static void test2_maxPQ(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            // return b - a; // other - this, reverse of default behaviour of a DS.
            return a - b; // this- other, default behaviour of a DS.
        });

        for (int ele : arr)
            pq.add(ele);

        while (pq.size() != 0) {
            System.out.println(pq.poll());
        }
    }

    public static void test3(int[][] arr) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[0] - a[0]; // other - this, reverse of default behaviour.
            // return a - b; // this- other, default behaviour.
        });

        for (int[] a : arr)
            pq.add(a);

        while (pq.size() != 0) {
            int[] a = pq.remove();
            int i = a[0];
            int j = a[1];
            System.out.println("(" + i + "," + j + ") ");
        }
    }

    public static class pair implements Comparable<pair> {
        int i = 0, j = 0;

        pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int compareTo(pair o) {
            return o.i - this.i;
        }
    }

    public static void test4(int[][] arr) {
        // PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
        //     return b.i - a.i; // other - this, reverse of default behaviour.
        //     // return a - b; // this- other, default behaviour.
        // });

        // Collections.sort(arr,(a,b)->{

        // })


        PriorityQueue<pair> pq = new PriorityQueue<>();

        for (int[] a : arr)
            pq.add(new pair(a[0], a[1]));

        while (pq.size() != 0) {
            pair p = pq.poll();
            int i = p.i;
            int j = p.j;
            System.out.println("(" + i + "," + j + ") ");
        }
    }

    public static void main(String[] args) {

        // int[] arr = { 2, 5, 1, -1, 0, -4, -6, 3, 6, 9, 9, 40 };
        // test1_minPQ(arr);

        int[][] arr = { { 2, 5 }, { 1, -1 }, { 0, -4 }, { -6, 3 }, { 6, 9 }, { 9, 40 } };
        test4(arr);

    }

}