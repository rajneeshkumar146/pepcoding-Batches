import java.util.ArrayList;

public class l001Recursion {
    public static void pppppp(int a) {
        System.out.println("I am Base case: " + a);
        return;
    }

    public static void ppppp(int a) {
        System.out.println("hi: " + a);
        pppppp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void pppp(int a) {
        System.out.println("hi: " + a);
        ppppp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void ppp(int a) {
        System.out.println("hi: " + a);
        pppp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void pp(int a) {
        System.out.println("hi: " + a);
        ppp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void p(int a) {
        System.out.println("hi: " + a);
        pp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void recursionPattern(int a, int b) {
        if (a == b) {
            System.out.println("I am Base case: " + a);
            return;
        }

        System.out.println("Hi" + a);
        recursionPattern(a + 1, b);
        System.out.println("Bye" + a);
    }

    public static void printIncreasing(int a, int b) {
        if (a > b)
            return;
        System.out.println(a);
        printIncreasing(a + 1, b);
    }

    public static void printDecreasing(int a, int b) {
        if (a > b)
            return;
        printDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printIncreasingDecreasing(int a, int b) {
        if (a > b)
            return;
        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void oddEven(int a, int b) {
        if (a > b)
            return;
        if (a % 2 != 0)
            System.out.println(a);
        oddEven(a + 1, b);
        if (a % 2 == 0)
            System.out.println(a);
    }

    public static int factorial(int n) {
        if (n == 0)
            return 1;

        return factorial(n - 1) * n;
    }

    public static int power(int a, int b) {
        if (b == 0)
            return 1;

        return power(a, b - 1) * a;
    }

    // O(logn)
    public static int powerBtr(int a, int b) {
        if (b == 0)
            return 1;

        int smallAns = powerBtr(a, b / 2);
        smallAns *= smallAns;

        return b % 2 == 0 ? smallAns : smallAns * a;
    }

    public static void printArray(int[] arr, int idx) {
        if (idx >= arr.length)
            return;

        System.out.println(arr[idx]);
        printArray(arr, idx + 1);
    }

    public static void printArrayReverse(int[] arr, int idx) {
        if (idx >= arr.length)
            return;

        printArrayReverse(arr, idx + 1);
        System.out.println(arr[idx]);
    }

    public static int maximum(int[] arr, int idx) {
        if (idx >= arr.length)
            return -(int) 1e9;

        int maxValue = maximum(arr, idx + 1);
        return Math.max(maxValue, arr[idx]);
    }

    public static int minimum(int[] arr, int idx) {
        if (idx >= arr.length)
            return (int) 1e9;

        int minValue = minimum(arr, idx + 1);
        return Math.min(minValue, arr[idx]);
    }

    public static boolean find(int[] arr, int data, int idx) {
        if (idx >= arr.length)
            return false;

        return arr[idx] == data || find(arr, data, idx + 1);
    }

    public static int firstIndex(int[] arr, int data, int idx) {
        if (idx >= arr.length)
            return -1;

        return arr[idx] == data ? idx : firstIndex(arr, data, idx + 1);
    }

    public static int lastIndex(int[] arr, int data, int idx) {
        if (idx >= arr.length)
            return -1;

        int ans = lastIndex(arr, data, idx + 1);
        if (ans != -1)
            return ans;

        return arr[idx] == data ? idx : -1;
    }

    public static int[] allIndex(int[] arr, int data, int idx) {

    }

    public static ArrayList<String> subsequnce(String str,int idx) {

    }

    public static int subsequnce(String str,int idx,String asf,ArrayList<String> ans) {

    }



    public static void main(String[] args) {
        recursionPattern(1, 6);

    }

}