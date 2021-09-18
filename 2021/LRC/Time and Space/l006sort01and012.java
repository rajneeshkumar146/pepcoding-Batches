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

    public static void sort012(int[] arr) {
        int pt1 = -1, n = arr.length, pt2 = n - 1, itr = 0;

        while (itr <= pt2) {
            if (arr[itr] == 0)
                swap(arr, ++pt1, itr++);
            else if (arr[itr] == 1)
                itr++;
            else
                swap(arr, itr, pt2--);
        }
    }

    public static void partition(int[] arr, int pivot) {
        int n = arr.length, pt = -1, itr = 0;
        while (itr < n) {
            if (arr[itr] <= pivot)
                swap(arr, ++pt, itr);

            itr++;
        }
    }

    public static int partition_02(int[] arr, int pIdx, int si, int ei) {
        int pt = si - 1, itr = si;
        int pivot = arr[pIdx];
        swap(arr, pIdx, ei);

        while (itr <= ei) {
            if (arr[itr] <= pivot)
                swap(arr, ++pt, itr);
            itr++;
        }

        return pt;
    }

    public static void quickSort(int[] arr, int si, int ei) {
        if (si >= ei)
            return;

        int pIdx = (si + ei) / 2;
        pIdx = partition_02(arr, pIdx, si, ei);

        quickSort(arr, si, pIdx - 1);
        quickSort(arr, pIdx + 1, ei);
    }

    public static void main(String[] args) {
        int[] arr = { 3, 7, -12, 15, 19, -13, 0, 8, 63, 97, -16, 69, 35, 21, 22 };
        // partition_02(arr, 1);
        // for (int ele : arr)
        // System.out.print(ele + " ");

    }
}