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
    // 10,20,30,40,50,60 -> 60,50,40,30,20,10
    public static void reverseOfArray(int[] arr){

    }

    // 
    public static void inverseOfArray(int[] arr){

    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = input2(n);
    }
}