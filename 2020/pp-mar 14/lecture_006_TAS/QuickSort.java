import java.util.Random;

public class QuickSort {

    public static Random rand = new Random();

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partitionOverPivot(int[] arr, int si, int ei, int pIdx) {
        swap(arr, pIdx, ei);

        int p = si - 1, itr = si;
        while (itr <= ei) {
            if (arr[itr] <= arr[ei])
                swap(arr, itr, ++p);
            itr++;
        }

        return p;
    }

    public static void quickSort(int[] arr, int si, int ei) {
        if (si >= ei)
            return;

        int pIdx = ei; // rand.nextInt(ei - si + 1) + si
        int p = partitionOverPivot(arr, si, ei, pIdx);

        // int prev = si, idx = si;
        // while (idx <= ei && arr[prev] <= arr[idx]) {
        // prev = idx;
        // idx++;
        // }

        // if (idx == ei + 1)
        // return;

        quickSort(arr, si, p - 1);
        quickSort(arr, p + 1, ei);
    }

    public static void main(String[] args) {
        int[] arr = { -12, 2, 7, 4, 34, 23, 0, 1, -1, -50, 16, 23, 7, 4, 2, 3 };
        quickSort(arr, 0, arr.length - 1);

        for (int ele : arr)
            System.out.print(ele + " ");
    }
}