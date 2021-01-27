public class l001_Sorting {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void moveToLast(int[] arr, int si, int ei) {
        for (int i = si + 1; i <= ei; i++) {
            if (arr[i - 1] > arr[i])
                swap(arr, i - 1, i);
        }
    }

    public static void bubble(int arr[], int n) {
        int ei = n - 1;
        for (int i = 0; i < n; i++) {
            moveToLast(arr, 0, ei - i);
        }

    }

    public static void moveToStart(int[] arr, int si, int ei) {
        for (int i = si + 1; i <= ei; i++) {
            if (arr[si] > arr[i])
                swap(arr, si, i);
        }
    }

    public static void selectionSort(int arr[]) {
        int n = arr.length;
        int ei = n - 1;
        for (int i = 0; i < n - 1; i++) {
            moveToStart(arr, i, ei);
        }
    }

}