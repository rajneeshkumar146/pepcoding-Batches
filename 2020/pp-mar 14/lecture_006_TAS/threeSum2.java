import java.util.*;

public class threeSum2 {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

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

    public static void quickSort(int[] arr, int si, int ei) {
        if (si > ei)
            return;

        int pivot = ei;
        int pIdx = segregate(arr, pivot, si, ei);
        quickSort(arr, si, pIdx - 1);
        quickSort(arr, pIdx + 1, ei);
    }

    // int[] arr = {2,3,4,5,6};
    public static void twoSum(int[] arr, int tar, int si, int ei, int a, ArrayList<int[]> ans) {
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            if (sum == tar)
                ans.add(new int[] { a, arr[si++], arr[ei--] });
            else if (sum > tar)
                ei--;
            else
                si++;
        }
    }

    public static void threeSum(int[] arr, int tar, int si, int ei, ArrayList<int[]> ans) {
        for (int i = si; i <= ei; i++) {
            twoSum(arr, tar - arr[i], i + 1, ei, arr[i], ans);
        }
    }

    public static void threeSum(int[] arr, int tar) {
        int n = arr.length;
        quickSort(arr, 0, n - 1);
        ArrayList<int[]> ans = new ArrayList<>();
        threeSum(arr, tar, 0, n - 1, ans);
        for (int[] a : ans) {
            System.out.println(a[0] + ", " + a[1] + ", " + a[2]);
        }
    }

    public static void main(String[] args) {
        int[] arr = { -2, -3, 7, 5, 8, 15, 3, 2, 9, 10, 19 };
        threeSum(arr, 25);
    }
}