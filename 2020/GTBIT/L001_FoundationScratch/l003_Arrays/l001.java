import java.util.Scanner;

public class l001 {
    public static Scanner scn = new Scanner(System.in);

    public static void display2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void test1() {
        int n = scn.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = scn.nextInt();
        display2(arr);
    }

    public static int maximum(int[] arr) {
        int maxEle = -(int) 1e9; // -10^9
        for (int ele : arr) {
            if (ele > maxEle)
                maxEle = ele;
        }
        return maxEle;
    }

    public static int maximum2(int[] arr) {
        if (arr.length == 0)
            return -(int) 1e9;
        int maxEle = arr[0]; // -10^9
        for (int ele : arr) {
            if (ele > maxEle)
                maxEle = ele;
        }
        return maxEle;
    }

    public static int minimum(int[] arr) {
        int minEle = (int) 1e9; // 10^9
        for (int ele : arr) {
            if (ele < minEle)
                minEle = ele;
        }
        return minEle;
    }

    public static boolean findData(int[] arr, int data) {
        for (int ele : arr) {
            if (ele == data)
                return true;
        }

        return false;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // si = starting index, ei = ending index
    public static void reverse(int[] arr, int si, int ei) {
        while (si < ei) {
            swap(arr, si++, ei--);
        }
    }

    public static void inverse(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[arr[i]] = i;
        }
    }

    public static void rotate(int[] arr, int k) {
        int n = arr.length;
        k = (k % n + n) % n;
        
        reverse(arr,0,n - 1);
        reverse(arr,0,k - 1);
        reverse(arr,k, n - 1);
    }

    public static void display(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void input(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
    }



    public static void main(String[] args) {
        test1();
    }
}