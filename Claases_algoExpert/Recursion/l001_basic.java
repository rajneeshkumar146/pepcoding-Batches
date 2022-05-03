import java.util.ArrayList;
import java.util.List;

public class l001_basic {

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

    public static void printEvenOdd(int a, int b) {
        if (a > b)
            return;

        if (a % 2 == 0)
            System.out.println(a);
        printEvenOdd(a + 1, b);
        if (a % 2 != 0)
            System.out.println(a);

    }

    public static int fact(int n) {
        return n == 0 ? 1 : n * fact(n - 1);
    }

    public static int pow(int a, int b) {
        return b == 0 ? 1 : a * pow(a, b - 1);
    }

    public static int getNthFib(int n) {
        if (n <= 1)
            return n;
        return getNthFib(n - 1) + getNthFib(n - 2);
    }

    public static int check(int n) {
        if (n <= 1) {
            System.out.println("base: " + n);
            return n + 3;
        }

        int count = 0;

        System.out.println("pre: " + n);
        count += check(n - 1);

        System.out.println("in: " + n);

        count += check(n - 2);
        System.out.println("post: " + n);

        return count + 3;
    }

    public static int check2(int n) {
        if (n <= 2) {
            System.out.println("base: " + n);
            return n + 3;
        }

        int count = 0;

        System.out.println("pre: " + n);
        count += check2(n - 1);

        System.out.println("in1: " + n);

        count += check2(n - 2);
        System.out.println("in2: " + n);

        count += check2(n - 3);
        System.out.println("post: " + n);

        return count + 3;
    }

    public static int powBtr(int a, int b) {
        if (b == 0) {
            return 1;
        }

        int smallAns = pow(a, b / 2);
        smallAns *= smallAns;

        return b % 2 == 0 ? smallAns : smallAns * a;
    }

    public static void display(int[] arr, int idx) {
        if (idx == arr.length)
            return;
        System.out.println(arr[idx]);
        display(arr, idx + 1);
    }

    public static int subseq(String str, int idx, String asf) {
        if (idx == str.length()) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        count += subseq(str, idx + 1, asf);
        count += subseq(str, idx + 1, asf + str.charAt(idx));

        return count;
    }

    public static void powerset(List<Integer> array, int idx, List<List<Integer>> res, List<Integer> smallAns) {
        if (idx == array.size()) {
            List<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return;
        }

        powerset(array, idx + 1, res, smallAns); // include nahi krne ki call

        smallAns.add(array.get(idx));
        powerset(array, idx + 1, res, smallAns); // include krne ki call
        smallAns.remove(smallAns.size() - 1);
    }

    public static List<List<Integer>> powerset(List<Integer> array) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        powerset(array, 0, res, smallAns);
        return res;
    }

    public static void permutation(String str, boolean[] usedChar, int count, String asf) {
        if (count == str.length()) {
            System.out.println(asf);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!usedChar[i]) {
                usedChar[i] = true;
                permutation(str, usedChar, count + 1, asf + str.charAt(i));
                usedChar[i] = false;
            }
        }
    }

    // algo expert question

    public static void getPermutations(List<Integer> arr, boolean[] usedIndex, int count, List<List<Integer>> res,
            List<Integer> smallAns) {
        if (count == arr.size()) {
            List<Integer> base = new ArrayList<>(smallAns);
            res.add(base);
            return;
        }

        for (int i = 0; i < arr.size(); i++) {
            if (!usedIndex[i]) {
                usedIndex[i] = true;
                smallAns.add(arr.get(i));
                getPermutations(arr, usedIndex, count + 1, res, smallAns);
                usedIndex[i] = false;
                smallAns.remove(smallAns.size() - 1);
            }

        }

    }

    public static List<List<Integer>> getPermutations(List<Integer> arr) {
        if (arr.size() == 0)
            return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        boolean[] usedIndex = new boolean[arr.size()];
        getPermutations(arr, usedIndex, 0, res, smallAns);
        return res;
    }

    public int staircaseTraversal(int height, int maxSteps) {
        if (height == 0)
            return 1;

        int count = 0;
        for (int jump = 1; jump <= maxSteps && height - jump >= 0; jump++) {
            count += staircaseTraversal(height - jump, maxSteps);
        }

        return count;
    }

    public static void main(String... args) {
        // int[] arr = { 1, 2, 345, 67, 233, 5 };
        // display(arr, 0);

        // System.out.println(subseq("123", 0, ""));
        boolean[] usedChar = new boolean[3];
        permutation("abc", usedChar, 0, "");

    }

}