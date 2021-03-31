import java.util.*;

public class l004_Arrays {
    public static Scanner scn = new Scanner(System.in);

    public static void test1(int n) {
        // type[] nameOfArray = new type[size]; with default value 0.
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void display1(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " "); // get
        }
    }

    public static void display2(int[] arr) {

        // forEach loop used for :
        // 1. only for get, if you try to set value ypu get a beutifull error.
        // 2. automatically increment by 1
        // 3. always in forward direction
        // 4. range of loop : [0 , n - 1]

        for (int ele : arr) {
            System.out.print(ele + " ");
        }
    }

    public static void input1(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt(); // set
        }
    }

    public static int[] input2(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt(); // set
        }

        return arr;
    }

    public static int maximum(int[] arr) {
        int maxEle = -(int) 1e9; // Integer.MIN_VALUE;
        for (int ele : arr) {
            maxEle = Math.max(maxEle, ele);
        }

        return maxEle;
    }

    public static int maximum2(int[] arr) {
        if (arr.length == 0)
            return Integer.MIN_VALUE;

        int maxEle = arr[0]; // Integer.MIN_VALUE;
        for (int ele : arr) {
            maxEle = Math.max(maxEle, ele);
        }

        return maxEle;
    }

    public static int minimum(int[] arr) {
        int minEle = (int) 1e9;
        for (int ele : arr) {
            minEle = Math.min(minEle, ele);
        }

        return minEle;
    }

    // if you found return index, otherwise return -1.
    public static int find(int[] arr, int data) {
        int foundAtIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == data) {
                foundAtIndex = i;
                break;
            }
        }

        return foundAtIndex;
    }

    public static int find2(int[] arr, int data) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == data)
                return i;
        }

        return -1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 10,20,30,40,50,60 -> 60,50,40,30,20,10
    public static void reverseOfArray(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    public static void inverseOfArray(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int idx = arr[i];
            ans[idx] = i;
        }
    }

    public static int spanOfArray(int[] arr) {
        int maxEle = -(int) 1e9;
        int minEle = (int) 1e9;

        for (int ele : arr) {
            maxEle = Math.max(maxEle, ele);
            minEle = Math.min(minEle, ele);
        }

        return maxEle - minEle;
    }

    public static int[] rotateOfArray(int[] arr, int r) {
        int n = arr.length;
        r %= n;
        if (r < 0)
            r += n;

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int idx = (i + r) % n;
            ans[idx] = arr[i];
        }

        return ans;
    }

    public static void reverseOfArray(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    public static void rotateOfArray2(int[] arr, int r) {
        int n = arr.length;
        r %= n;
        if (r < 0)
            r += n;

        reverseOfArray(arr, 0, n - 1);
        reverseOfArray(arr, 0, r - 1);
        reverseOfArray(arr, r, n - 1);
    }

    public static void sumOfTwoArray(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;
        int p = Math.max(n, m) + 1;
        int[] ans = new int[p];

        int i = n - 1, j = m - 1, k = p - 1, carry = 0;
        while (k >= 0) {
            int sum = carry + (i >= 0 ? arr1[i] : 0) + (j >= 0 ? arr2[j] : 0);
            ans[k] = sum % 10;
            carry = sum / 10;

            i--;
            j--;
            k--;
        }

        for (int idx = 0; idx < p; idx++) {
            if (idx == 0 && ans[idx] == 0)
                continue;
            System.out.print(ans[idx]);
        }

    }

    // arr2 as number > arr1 as number
    public static void subtractOfTwoArray(int[] arr1, int[] arr2) {

        int n = arr1.length;
        int m = arr2.length;
        int[] ans = new int[m];

        int i = n - 1, j = m - 1, k = m - 1;
        int borrow = 0;
        while (k >= 0) {
            int diff = borrow + arr2[j] - (i >= 0 ? arr1[i] : 0);
            if (diff < 0) {
                diff += 10;
                borrow = -1;
            } else {
                borrow = 0;
            }

            ans[k] = diff;

            i--;
            j--;
            k--;
        }

        boolean nonZeroFound = false;
        for (int ele : ans) {
            if (ele != 0) {
                nonZeroFound = true;
            }

            if (nonZeroFound)
                System.out.println(ele);
        }

    }

    public static void printInRange(int[] arr, int si, int ei) {
        while (si <= ei) {
            System.out.print(arr[si] + " ");
            si++;
        }
    }

    public static void printAllSubArrays(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                printInRange(arr, i, j);
            }
        }
    }

    public static int binarySearch(int[] arr, int data) {
        int si = 0, ei = arr.length - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data)
                return mid;
            else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        input1(arr);
        int r = scn.nextInt();
        // int[] ans = rotateOfArray(arr, r);
        // display1(ans);
        rotateOfArray2(arr, r);
    }
}