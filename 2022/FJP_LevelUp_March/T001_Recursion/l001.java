import java.util.ArrayList;

public class l001 {
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

    public static int fact(int n) {
        return n == 0 ? 1 : fact(n - 1) * n;
    }

    public static int power(int a, int b) {
        return b == 0 ? 1 : power(a, b - 1) * a;
    }

    public static int power_btr(int a, int b) {
        if (b == 0)
            return 1;
        int smallAns = power(a, b / 2);
        smallAns *= smallAns;
        return smallAns * (b % 2 == 0 ? 1 : a);
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

    public static int FirstIndex(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return -1;
        if (arr[idx] == data)
            return idx;

        return FirstIndex(arr, idx + 1, data);
    }

    public static int LastIndex(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return -1;

        int ans = LastIndex(arr, idx + 1, data);
        if (ans != -1)
            return ans;

        return arr[idx] == data ? idx : -1;
    }

    // n = 5
    public static int check(int n) {
        if (n <= 1) {
            System.out.println("base" + n);
            return n + 3;
        }
        int count = 0;

        System.out.println("pre" + n);
        count += check(n - 1);
        System.out.println("in" + n);
        count += check(n - 2);
        System.out.println("post" + n);

        return count + 3;
    }

    public static int subseq(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        count += subseq(str, idx + 1, ans);
        count += subseq(str, idx + 1, ans + str.charAt(idx));

        return count;
    }

    public static int subseq2(String str, int n, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        count += subseq2(str, n - 1, ans);
        count += subseq2(str, n - 1, ans + str.charAt(n - 1));

        return count;
    }

    // jump : 1,2,3
    public static int stairPath(int n, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int jump = 1; jump <= 3 && n - jump >= 0; jump++)
            count += stairPath(n - jump, ans + jump);

        return count;
    }

    // jumps : [1,4,6,7]
    public static int variablePath(int[] jumps, int n, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < jumps.length && n - jumps[i] >= 0; i++)
            count += variablePath(jumps, n - jumps[i], ans + jumps[i]);

        return count;
    }

    public static void main(String... args) {
        System.out.println(stairPath(5, ""));
    }
}