public class quickSelect {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
]
    public static int segregate(int[] arr, int pivot, int si, int ei) {
        swap(arr, pivot, ei);

        int p = si - 1, itr = si;

        while (itr <= ei) {
            if (arr[itr] <= arr[ei])
                swap(arr, ++p, itr);
            itr++;
        }

        return p;
    }

    public static void quickselect(int[] arr, int si, int ei, int idx) {
        if (si > ei)
            return;

        int pivot = ei;
        int pIdx = segregate(arr, pivot, si, ei);
        if (pIdx == idx)
            return;
        else if (idx > pIdx)
            quickselect(arr, pIdx + 1, ei, idx);
        else
            quickselect(arr, si, pIdx - 1, idx);
    }

    public static int quickselect(int[] arr, int k) {
        int n = arr.length, idx = n - k;
        quickselect(arr, 0, n - 1, idx);

        return arr[idx];
    }

    public static void main(String[] args) {
        int[] arr = { -12, 2, 7, 4, 34, 23, 0, 1, -1, -50, 16, 23, 7, 4, 2, 3 };
        int k = 5;
        System.out.println(quickselect(arr, k));
    }

}