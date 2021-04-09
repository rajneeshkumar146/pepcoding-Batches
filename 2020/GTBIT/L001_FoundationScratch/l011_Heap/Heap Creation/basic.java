public class basic {
    public static int height(int[] arr, int pi) {
        if (pi >= arr.length)
            return -1;
        int lh = height(arr, 2 * pi + 1);
        int rh = height(arr, 2 * pi + 2);

        return Math.max(lh, rh) + 1;
    }

    public static int size(int[] arr, int pi) {
        if (pi >= arr.length)
            return 0;
        int ls = size(arr, 2 * pi + 1);
        int rs = size(arr, 2 * pi + 2);

        return ls + rs + 1;
    }



    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13, 14 };
        System.out.println(height(arr, 0));
        System.out.println(size(arr,0));
        System.out.println(arr.length);
    }
}