public class l002 {

    public static void printArray(int[] arr, int idx) {
        if (idx == arr.length)
            return;

        System.out.println(arr[idx]);
        printArray(arr, idx + 1);
    }

    public static void printReverseArray(int[] arr, int idx) {
        if (idx == arr.length)
            return;

        printReverseArray(arr, idx + 1);
        System.out.println(arr[idx]);
    }

    public static int maximum(int[] arr, int idx) {

    }

    public static int minimum(int[] arr, int idx) {

    }

    public static boolean findData(int[] arr, int idx, int data) {

    }

    public static int firstIdx(int[] arr, int idx, int data) {

    }

    public static int lastIdx(int[] arr, int idx, int data) {

    }

    public static void main(String[] args) {
        int[] arr = { 12, 3, 2,4, 34, 3, 4, 2, 2, 76 };
        printReverseArray(arr, 0);
        System.out.println(maximum(arr, 0));
        System.out.println(minimum(arr, 0));
        System.out.println(findData(arr, 0, 2));
        System.out.println(firstIdx(arr, 0, 2));
        System.out.println(lastIdx(arr, 0, 2));
    }
}