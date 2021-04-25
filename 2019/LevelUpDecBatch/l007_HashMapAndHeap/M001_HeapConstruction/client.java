public class client {
    public static void test() {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        heap pq = new heap(arr, true);

        while (pq.size() != 0) {
            System.out.println(pq.remove());
        }

    }

    public static void main(String[] args) {
        test();

    }
}