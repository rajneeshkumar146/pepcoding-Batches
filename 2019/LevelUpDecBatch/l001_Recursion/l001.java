import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class l001 {
    public static Scanner scn = new Scanner(System.in);

    public static void printDecreasing(int n) {
        if (n == 0)
            return;

        System.out.println(n);
        printDecreasing(n - 1);
    }

    public static void printIncreasing(int n) {
        if (n == 0)
            return;

        printIncreasing(n - 1);
        System.out.println(n);

    }

    public static void printEvenOdd(int n) {
        if (n == 0)
            return;

        if (n % 2 == 0)
            System.out.println(n);
        printEvenOdd(n - 1);
        if (n % 2 != 0)
            System.out.println(n);
    }

    public static int factorial(int n) {
        return n == 0 ? 1 : factorial(n - 1) * n;
    }

    public static int power(int a, int b) {
        return b == 0 ? 1 : power(a, b - 1) * a;
    }

    public static int power1(int a, int b) {
        if (b == 0)
            return 1;
        int smallAns = power(a, b - 1);
        return smallAns * a;
    }

    public static int powerBtr(int a, int b) {
        if (b == 0)
            return 1;
        int smallAns = powerBtr(a, b / 2);
        smallAns *= smallAns;
        return b % 2 != 0 ? smallAns * a : smallAns;
    }

    public static void display(int[] arr, int idx) {
        if (idx == arr.length)
            return;

        System.out.println(arr[idx] + " ");
        display(arr, idx + 1);
    }

    public static int maximumEle(int[] arr, int idx) {
        if (idx == arr.length)
            return -(int) 1e9;
        return Math.max(arr[idx], maximumEle(arr, idx + 1));
    }

    public static int minimumEle(int[] arr, int idx) {
        if (idx == arr.length)
            return (int) 1e9;
        return Math.min(arr[idx], minimumEle(arr, idx + 1));
    }

    public static int findData(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return -1;
        if (arr[idx] == data)
            return idx;
        return findData(arr, idx + 1, data);
    }

    public static int findData2(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return -1;
        int smallAns = findData2(arr, idx + 1, data);
        if (smallAns != -1)
            return smallAns;

        return arr[idx] == data ? idx : -1;
    }

    public static int[] allIndex(int[] arr, int idx, int count, int data) {
        if (idx == arr.length)
            return new int[count];
        if (arr[idx] == data)
            count++;
        int[] smallAns = allIndex(arr, idx + 1, count, data);
        if (arr[idx] == data)
            smallAns[count - 1] = idx;

        return smallAns;
    }

    public static ArrayList<String> subsequence(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(idx);

        ArrayList<String> smallAns = subsequence(str, idx + 1);
        ArrayList<String> myAns = new ArrayList<>();

        for (String s : smallAns) {
            myAns.add(s);
            myAns.add(ch + s);
        }

        return myAns;
    }

    static String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz", "+-*", "/%^" };

    public static ArrayList<String> getKPC(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        int indexOfCode = str.charAt(idx) - '0';

        ArrayList<String> smallAns = getKPC(str, idx + 1);
        ArrayList<String> myAns = new ArrayList<>();

        String code = codes[indexOfCode];
        for (int i = 0; i < code.length(); i++) {
            for (String s : smallAns) {
                myAns.add(code.charAt(i) + s);
            }
        }

        return myAns;
    }

    public static int getKPC(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        String code = codes[str.charAt(idx) - '0'];
        for (int i = 0; i < code.length(); i++) {
            count += getKPC(str, idx + 1, ans + code.charAt(i));
        }

        if (idx < str.length() - 1) {
            int num = (str.charAt(idx) - '0') * 10 + (str.charAt(idx + 1) - '0');
            if (num == 10 || num == 11) {
                code = codes[num];
                for (int i = 0; i < code.length(); i++) {
                    count += getKPC(str, idx + 2, ans + code.charAt(i));
                }
            }
        }
        return count;
    }

    // https://www.pepcoding.com/resources/online-java-foundation/recursion-on-the-way-up/print-encodings-official/ojquestion

    public static int decodeWays(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return 1;
        }

        if (str.charAt(idx) == '0')
            return 0;

        int count = 0;
        count += decodeWays(str, idx + 1, ans + (char) (str.charAt(idx) - '1' + 'a'));
        if (idx < str.length() - 1) {
            int num = (str.charAt(idx) - '0') * 10 + (str.charAt(idx + 1) - '0');
            if (num <= 26) {
                count += decodeWays(str, idx + 1, ans + (char) (num - 1 + 'a'));
            }
        }

        return count;
    }

    // 91
    public int decodeWays(String str, int idx) {
        if (idx == str.length()) {
            return 1;
        }

        if (str.charAt(idx) == '0')
            return 0;

        int count = 0;
        count += decodeWays(str, idx + 1);

        if (idx < str.length() - 1) {
            int num = (str.charAt(idx) - '0') * 10 + (str.charAt(idx + 1) - '0');
            if (num <= 26) {
                count += decodeWays(str, idx + 2);
            }
        }

        return count;
    }

    public int numDecodings(String s) {
        if (s.length() == 0)
            return 0;
        return decodeWays(s, 0);

    }

    // https://www.pepcoding.com/resources/online-java-foundation/recursion-on-the-way-up/print-permutations-official/ojquestion#

    public static int printPermutations(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1); // ros : rest of the string
            count += printPermutations(ros, ans + ch);
        }

        return count;
    }

    public static int printPermutationUnique(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        boolean[] vis = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!vis[ch - 'a']) {
                vis[ch - 'a'] = true;
                String ros = str.substring(0, i) + str.substring(i + 1); // ros : rest of the string
                count += printPermutationUnique(ros, ans + ch);
            }
        }

        return count;
    }

    public static void printPermutationUnique() {
        String str = "aba";
        printPermutationUnique(str, "");
    }

    public static void main(String[] args) {
        printDecreasing(10);
    }
}