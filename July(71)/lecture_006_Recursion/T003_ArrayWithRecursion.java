import java.util.Scanner;
import java.util.Arrays;

public class T003_ArrayWithRecursion {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        int[] arr = { -7, 10, 5, 2, -4, 3, 10, 10, 10, 20, 440, -2340, 10, 10, 230, 320 };

        System.out.println(maximum(arr, 0));
        System.out.println(minimum(arr, 0));
        System.out.println(findIndex(arr, 10, 0));
        System.out.println(lastIndex(arr, 10, 0));

        int[] ansOfAllIndex = allIndex(arr, 10, 0, 0);
        for (int i : ansOfAllIndex) {
            System.out.print(i + " ");
        }
    }

    public static void display(int[] arr, int vidx) {
        if (vidx == arr.length)
            return;

        System.out.print(arr[vidx] + " ");
        display(arr, vidx + 1);
    }

    public static int maximum(int[] arr, int vidx) {
        if (vidx == arr.length) {
            return Integer.MIN_VALUE; // (int)-1e6;, (int)1e6
        }

        int faithMax = maximum(arr, vidx + 1);
        int actualMax = Math.max(faithMax, arr[vidx]);
        return actualMax;

    }

    public static int minimum(int[] arr, int vidx) {
        if (vidx == arr.length) {
            return Integer.MAX_VALUE; // (int)-1e6;, (int)1e6
        }

        // if(vidx==arr.length-1) return arr[vidx];

        int faithMin = minimum(arr, vidx + 1);
        int actualMin = Math.min(faithMin, arr[vidx]);
        return actualMin;
    }

    public static int findIndex(int[] arr, int data, int vidx) {
        if (vidx == arr.length)
            return -1;

        if (arr[vidx] == data)
            return vidx;

        int ans = findIndex(arr, data, vidx + 1);
        // if (ans != -1) {
        // return ans;
        // }

        // if (arr[vidx] == data) {
        // return vidx;
        // } else
        // return -1;

        return ans;

    }

    public static int lastIndex(int[] arr, int data, int vidx) {
        if (vidx == arr.length)
            return -1;

        int ans = lastIndex(arr, data, vidx + 1);
        if (ans != -1)
            return ans;

        if (arr[vidx] == data) {
            return vidx;
        } else
            return -1;

    }

    static int count = 0;

    public static int[] allIndex(int[] arr, int data, int vidx, int count) {
        if (vidx == arr.length) {
            int[] base = new int[count];
            return base;
        }

        if (arr[vidx] == data) {
            count++;
        }

        int[] SmallAns = allIndex(arr, data, vidx + 1, count);

        if (arr[vidx] == data) {
            SmallAns[count - 1] = vidx;
        }

        return SmallAns;

    }

    public static void inverse(int[] arr, init vidx) {
        if (vidx == arr.length)
            return;

        int data = arr[vidx];
        inverse(arr, vidx + 1);
        arr[data] = vidx;

    }

    // without Recursion.
    public static void addition(int[] arr1, int[] arr2) {

        int len = Math.max(arr1.length, arr2.length) + 1;
        int[] ans = new int[len];

        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int k = len - 1;

        int carry = 0;
        while (i >= 0 && j >= 0) {
            int val1 = arr1[i];
            int val2 = arr2[j];
            int stateAns = val1 + val2 + carry;

            ans[k] = stateAns % 10;
            carry = stateAns / 10;
            i--;
            j--;
            k--;
        }

        while (i >= 0) {
            int stateAns = arr1[i] + carry;
            ans[k] = stateAns % 10;
            carry = stateAns / 10;
            i--;
            k--;
        }

        while (j >= 0) {
            int stateAns = arr2[j] + carry;
            ans[k] = stateAns % 10;
            carry = stateAns / 10;
            j--;
            k--;
        }

        ans[k] = carry;

        if (ans[0] == 0) {
            for (int itr = 1; itr < len; itr++) {
                System.out.print(ans[itr] + " ");
            }
        } else {
            for (int itr = 0; itr < len; itr++) {
                System.out.print(ans[itr] + " ");
            }
        }

    }

    public static void addition2(int[] arr1, int[] arr2) {

        int len = Math.max(arr1.length, arr2.length) + 1;
        int[] ans = new int[len];

        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int k = len - 1;

        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int val1 = 0;
            int val2 = 0;

            if (i >= 0) {
                val1 = arr1[i];
                i--;
            }

            if (j >= 0) {
                val2 = arr2[j];
                j--;
            }

            int stateAns = val1 + val2 + carry;

            ans[k] = stateAns % 10;
            carry = stateAns / 10;

            k--;
        }
        
        if (ans[0] == 0) {
            for (int itr = 1; itr < len; itr++) {
                System.out.print(ans[itr] + " ");
            }
        } else {
            for (int itr = 0; itr < len; itr++) {
                System.out.print(ans[itr] + " ");
            }
        }

    }

}