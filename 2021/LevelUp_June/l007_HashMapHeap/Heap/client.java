public class client {
    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13, 14 };
        heap hp = new heap(arr, false);  // true -> max, false -> min

        while (hp.size() != 0) {
            System.out.print(hp.remove() + " ");
        }

    }
}