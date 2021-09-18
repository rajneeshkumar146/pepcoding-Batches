public class l006sort01and012 {
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void sort01(int[] arr) {
        int pt = -1, itr = 0, n = arr.length;

        while (itr < n) {
            if (arr[itr] == 0)
                swap(arr, ++pt, itr);

            itr++;
        }

    }
}