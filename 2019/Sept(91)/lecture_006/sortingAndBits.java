public class sortingAndBits {
    public static void main(String[] args) {
        // int[] arr = { 8, 3, 11, 6, 16, 20, -10, 18, 5 };
        // int[] arr={8,9,10,16,26,30,45,42,43};
        // display(arr);
        // BS(arr);
        // SS(arr);
        // display(arr);

        // OnKthbit();
        offKthbit();
    }

    public static void display(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void BS(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            boolean flag = true;
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                    // swap(arr[j-1],arr[j]); //for c++
                    flag = false;
                }
            }

            display(arr);

            if (flag)
                break;
        }
    }

    public static void SS(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
            display(arr);
        }
    }

    public static void OnKthbit() {
        int num = 5;
        int k = 1;

        int mask = 1;
        mask <<= k;
        num |= mask;

        System.out.println(num);
    }

    public static void offKthbit() {
        int num = 7;
        int k = 1;

        int mask = ~(1 << k);
        num &= mask;

        System.out.println(num);
    }

}