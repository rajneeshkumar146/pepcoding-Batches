import java.util.*;

class Program {
    public static int[] heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            downHeapify(i, n - 1, arr);
        }

        int ei = n - 1;
        while (ei > 0) {
            swap(0, ei--, arr);
            downHeapify(0, ei, arr);
        }

        return arr;
    }

    private static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void downHeapify(int pi, int ei, int[] arr) {
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;
        int maxIdx = pi;

        if (lci <= ei && arr[lci] > arr[maxIdx])
            maxIdx = lci;
        if (rci <= ei && arr[rci] > arr[maxIdx])
            maxIdx = rci;

        if (pi != maxIdx) {
            swap(pi, maxIdx, arr);
            downHeapify(maxIdx, ei, arr);
        }
    }
}
