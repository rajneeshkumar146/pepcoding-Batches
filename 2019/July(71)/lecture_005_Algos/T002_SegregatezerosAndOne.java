import java.util.Scanner;

public class T002_SegregatezerosAndOne {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // int[] arr = { 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1,
        // 0, 1, 0, 1 };
        // ZerosAndOnes(arr);

        int[] arr1 = { 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 2, 0, 1, 1, 1, 2, 2, 2, 1, 1, 1, 0, 0, 0, 2, 1, 1, 0, 1, 0,
                0, 1, 0, 1, 0, 1, 0, 1, 2, 2, 2, 1, 1, 0 };

        Zeros_OnesAndTwos(arr1);
        // display(arr);
        display(arr1);
    }

    public static void ZerosAndOnes(int[] arr) {

        int itr = 0;
        int pt = 0;
        while (itr < arr.length) {
            if (arr[itr] == 1)
                itr++;
            else {
                swap(arr, pt, itr);
                pt++;
                itr++;
            }
        }
    }

    public static void Zeros_OnesAndTwos(int[] arr) {

        int itr = 0;
        int pt1 = 0;
        int pt2 = arr.length - 1;
        while (itr <= pt2) {
            if (arr[itr] == 1)
                itr++;
            else if (arr[itr] == 0) {
                swap(arr, pt1, itr);
                pt1++;
                itr++;
            } else {
                swap(arr, pt2, itr);
                pt2--;
            }
        }
    }

    public static void display(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
