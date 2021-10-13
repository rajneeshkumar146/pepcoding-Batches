public class heapSort {

    public static boolean compareTo(int[] arr, int x, int y, boolean isIncreasing) {
        if (isIncreasing)
            return arr[x] > arr[y];
        return arr[y] > arr[x];
    }

    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void downHeapify(int[] arr, int pi, int li, boolean isIncreasing) {
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;
        int maxIdx = pi;

        if (lci <= li && compareTo(arr, lci, maxIdx, isIncreasing))
            maxIdx = lci;
        if (rci <= li && compareTo(arr, rci, maxIdx, isIncreasing))
            maxIdx = rci;

        if (pi != maxIdx) {
            swap(arr, maxIdx, pi);
            downHeapify(arr, maxIdx, li, isIncreasing);
        }

    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13, 14 };

        boolean isIncreasing = false;
        int li = arr.length - 1;
        
        // we will create a max heap
        for (int i = li; i >= 0; i--)
            downHeapify(arr, i, li, isIncreasing);

        // sort array
        while(li > 0){
            swap(arr, 0, li--);
            downHeapify(arr, 0, li, isIncreasing);
        }

        for(int ele : arr) System.out.print(ele + " ");
    }
}