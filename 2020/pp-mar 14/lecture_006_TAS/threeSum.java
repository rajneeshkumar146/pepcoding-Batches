import java.util.*;

public class threeSum {
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
    public static ArrayList<int[]> twoSum(int[] arr, int tar, int si, int ei) {
        ArrayList<int[]> ans = new ArrayList<>();
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            if (sum == tar)
                ans.add(new int[] { arr[si++], arr[ei--] });
            else if (sum > tar)
                ei--;
            else
                si++;
        }

        return ans;
    }

    public static ArrayList<int[]> threeSum(int[] arr, int tar, int si, int ei) {
        ArrayList<int[]> ans = new ArrayList<>();
        for (int i = si; i <= ei; i++) {
            ArrayList<int[]> smallAns = twoSum(arr, tar - arr[i], i + 1, ei);

            for (int[] a : smallAns) {
                ans.add(new int[] { arr[i], a[0], a[1] });
            }
        }

        return ans;
    }

    public static void threeSum(int[] arr, int tar) {
        int n = arr.length;
        quickSort(arr, 0, n - 1);
        ArrayList<int[]> ans = threeSum(arr, tar, 0, n - 1);
        for (int[] a : ans) {
            System.out.println(a[0] + ", " + a[1] + ", " + a[2]);
        }
    }

    public static void main(String[] args){
        int[] arr = { -2, -3, 7, 5, 8, 15, 3, 2, 9, 10, 19 };
        threeSum(arr, 25);
    }
}