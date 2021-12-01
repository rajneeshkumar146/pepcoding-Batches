public class l002_selectionSort {

    public static void selectionSort_01(int[] arr) {

        int n = arr.length;
        for (int p = 0; p < n; p++) {
            for (int c = p + 1; c < n; c++) {
                if (arr[p] > arr[c]) {
                    int temp = arr[p];
                    arr[p] = arr[c];
                    arr[c] = temp;
                }

            }
        }
    }

    // used for swapping ith and jth elements of array
    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping " + arr[i] + " and " + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // return true if ith element is smaller than jth element
    public static boolean isSmaller(int[] arr, int i, int j) {
        System.out.println("Comparing " + arr[i] + " and " + arr[j]);
        if (arr[i] < arr[j]) {
            return true;
        } else {
            return false;
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int p = 0; p < n; p++) {
            int minIdx = p;
            for (int c = p + 1; c < n; c++) {
                if (isSmaller(arr, c, minIdx))
                    minIdx = c;
            }

            swap(arr, p, minIdx);
        }
    }

    public static void print(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + " ");

        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 10, 12, -5, -13, 2, 15, 19, 100, 1000, 10000 };
        // int[] arr = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6, -7,
        // -8, -9, -10 };
        selectionSort_01(arr);
        print(arr);
    }
}