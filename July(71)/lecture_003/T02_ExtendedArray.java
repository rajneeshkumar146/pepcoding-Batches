import java.util.Scanner;
import java.util.ArrayList;

public class T02_ExtendedArray {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        int n = scn.nextInt();
        int[] arr = new int[n];
        inputArray(arr);

        // ArrayList<Integer> ans = allIndex(arr, 9);
        // System.out.println(ans);
        // for (Integer i : ans) {
        // System.out.print(i + " ");
        // }

        // int rot = scn.nextInt();
        // rotate(arr, rot);
        // for (int i : arr)
        // System.out.print(i + " ");

        System.out.println(binarySearch(arr, 9));
        System.out.println(lowerBoundBinarySearch(arr, 9));
        System.out.println(upperBoundBinarySearch(arr, 9));

        // display(arr);
    }

    public static void inputArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }

    public static void display(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }

        System.out.println();

    }

    public static ArrayList<Integer> allIndex(int[] arr, int data) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == data)
                ans.add(i);
        }

        return ans;
    }

    public static void rotate(int[] arr, int rot) {

        rot %= arr.length;
        // rot = rot < 0 ? rot + arr.length : rot;
        if (rot < 0) {
            rot = rot + arr.length;
        }

        reverse(arr, 0, rot - 1);
        reverse(arr, rot, arr.length - 1);
        reverse(arr, 0, arr.length - 1);

    }

    public static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

    }

    public static int binarySearch(int[] arr, int data) {
        int le = 0;
        int ri = arr.length - 1;

        while (le <= ri) {
            int mid = (le + ri) / 2;
            if (arr[mid] == data)
                return mid;
            else if (arr[mid] < data)
                le = mid + 1;
            else
                ri = mid - 1;
        }
        return -1;

    }

    public static int lowerBoundBinarySearch(int[] arr, int data) {
        int le = 0;
        int ri = arr.length - 1;

        while (le <= ri) {
            int mid = (le + ri) / 2;
            if (arr[mid] == data) {

                if (mid - 1 >= 0 && arr[mid - 1] == data)
                    ri = mid - 1;
                else
                    return mid;

            } else if (arr[mid] < data)
                le = mid + 1;
            else
                ri = mid - 1;
        }
        return -1;

    }

    public static int upperBoundBinarySearch(int[] arr, int data) {
        int le = 0;
        int ri = arr.length - 1;

        while (le <= ri) {
            int mid = (le + ri) / 2;
            if (arr[mid] == data) {

                if (mid + 1 < arr.length && arr[mid + 1] == data)
                    le = mid + 1;
                else
                    return mid;

            } else if (arr[mid] < data)
                le = mid + 1;
            else
                ri = mid - 1;
        }
        return -1;

    }

    public static int closestEleInSortedArray(int[] arr, int data) {
        int le = 0;
        int ri = arr.length - 1;

        while (le <= ri) {
            int mid = (le + ri) / 2;
            if (arr[mid] == data)
                return mid;
            else if (arr[mid] < data)
                le = mid + 1;
            else
                ri = mid - 1;
        }

        if (le >= arr.length)
            return ri;
        else if (ri < 0)
            return le;
        else {
            return ((arr[le] - data) < (data - arr[ri])) ? le : ri;
        }

    }

}
