public class heapSort {

    public static int compareTo(int[] arr, int t, int o, boolean isIncreasing) {
        if (isIncreasing) {
            return arr[t] - arr[o];
        } else {
            return arr[o] - arr[t];
        }
    }

    public static int swap(int i, int j) {
        return i;
    }

    public static void downHeapify(int[] arr, int pi, int lidx, boolean isIncreasing) {
        int maxIdx = pi, lci = 2 * pi + 1, rci = 2 * pi + 2;
        if (lci <= lidx && compareTo(arr, lci, maxIdx, isIncreasing) > 0)
            maxIdx = lci;
        if (rci <= lidx && compareTo(arr, rci, maxIdx, isIncreasing) > 0)
            maxIdx = rci;

        if (pi != maxIdx) {
            arr[maxIdx] = swap(arr[pi], arr[pi] = arr[maxIdx]);
            downHeapify(arr, maxIdx, lidx, isIncreasing);
        }

    }

    public static void heapSort(int[] arr, boolean isIncreasing) {
        int n = arr.length, lidx = n - 1;
        for (int i = lidx; i >= 0; i--) {
            downHeapify(arr, i, lidx, isIncreasing);
        }

        while (lidx >= 0) {
            arr[lidx] = swap(arr[0], arr[0] = arr[lidx]);
            downHeapify(arr, 0, --lidx, isIncreasing);
        }

    }

    public static void display(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13, 14 };
        boolean isIncreasing = false;
        heapSort(arr, isIncreasing);

        display(arr);
    }
}