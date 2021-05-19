public class twoSum {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int segregate(int[] arr, int si, int ei, int pivot) {
        swap(arr, pivot, ei);
        int p = si - 1, itr = si;
        while (itr <= ei) {
            if (arr[itr] <= arr[ei])
                swap(arr, itr, ++p);
            itr++;
        }
        return p;
    }

    public static void quickSort(int[] arr, int si, int ei) {
        if (si > ei)
            return;

        int pivot = ei;
        int pIdx = segregate(arr, si, ei, pivot);

        quickSort(arr, si, pIdx - 1);
        quickSort(arr, pIdx + 1, ei);
    }

    public static void targetSumPair(int[] arr, int target) {
        int n = arr.length;
        quickSort(arr, 0, n - 1);  // Arrays.sort(arr);

        int si = 0, ei = n - 1;
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            if (sum == target)
                System.out.println(arr[si++] + ", " + arr[ei--]);
            else if (sum < target)
                si++;
            else
                ei--;
        }
    }
}