import java.lang.Character.Subset;
import java.util.ArrayList;

public class l001Basic {

    public static void pppppp(int a, int b) {
        if (a > b)
            return;
    }

    public static void ppppp(int a, int b) {
        System.out.println(a);
        pppppp(a + 1, b);
    }

    public static void pppp(int a, int b) {
        System.out.println(a);
        ppppp(a + 1, b);
    }

    public static void ppp(int a, int b) {
        System.out.println(a);
        pppp(a + 1, b);
    }

    public static void pp(int a, int b) {
        System.out.println(a);
        ppp(a + 1, b);
    }

    public static void p(int a, int b) {
        System.out.println(a);
        pp(a + 1, b);
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
        if (a == b) {
            System.out.println(a);
            return;
        }
        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printOddEven(int a, int b) {
        if (a > b)
            return;

        if (a % 2 == 0)
            System.out.println(a);
        printOddEven(a + 1, b);
        if (a % 2 != 0)
            System.out.println(a);
    }

    public static void display(int[] arr, int idx) {
        if (idx == arr.length)
            return;

        System.out.println(arr[idx]);
        display(arr, idx + 1);
    }

    public static void displayReverse(int[] arr, int idx) {
        if (idx == arr.length)
            return;

        displayReverse(arr, idx + 1);
        System.out.println(arr[idx]);
    }

    public static int maximum(int[] arr, int idx) {
        if (idx == arr.length)
            return (int) -1e9;
        int maxSF = maximum(arr, idx + 1);
        return Math.max(maxSF, arr[idx]);

    }

    public static int minimum(int[] arr, int idx) {
        if (idx == arr.length)
            return (int) 1e9;
        int minSF = minimum(arr, idx + 1);
        return Math.min(minSF, arr[idx]);

    }

    public static boolean find(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return false;
        return arr[idx] == data || find(arr, idx + 1, data);
    }

    public static int firstIndex(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return -1;

        if (arr[idx] == data)
            return idx;
        return firstIndex(arr, idx + 1, data);

        // return arr[idx] == data ? idx : firstIndex(arr,idx +1 ,data);
    }

    public static int lastIndex(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return -1;
        int smallRes = lastIndex(arr, idx + 1, data);
        if (smallRes != -1)
            return smallRes;

        return arr[idx] == data ? idx : -1;
    }

    public static int[] allIndex(int[] arr, int idx, int data, int count) {
        if (idx == arr.length) {
            int[] base = new int[count];
            return base;
        }

        if (arr[idx] == data)
            count++;
        int[] ans = allIndex(arr, idx + 1, data, count);
        if (arr[idx] == data)
            ans[count - 1] = idx;

        return ans;
    }

    public static ArrayList<String> subSeq(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> recAns = subSeq(str, idx + 1);
        ArrayList<String> myAns = new ArrayList<>(recAns);
        for (String s : recAns)
            myAns.add(str.charAt(idx) + s);
        
        return myAns;
    }

    public static ArrayList<String> getKPC(String str) {

    }

    public static void main(String[] args) {

    }
}