import java.util.Scanner;

public class l001 {

    // nor : number of rows
    public static void printRectangle(int cst, int nst, int nor, int n, int m) {
        if (nor == n + 1)
            return;

        if (cst == nst + 1) {
            System.out.println();
            printRectangle(1, nst, nor + 1, n, m);
            return;
        }

        System.out.print("*");
        printRectangle(cst + 1, nst, nor, n, m);
    }

    public static void printTriangle(int cst, int nst, int nor, int n, int m) {
        if (nor == n + 1)
            return;

        if (cst == nst + 1) {
            System.out.println();
            printTriangle(1, nst + 1, nor + 1, n, m);
            return;
        }

        System.out.print("*");
        printTriangle(cst + 1, nst, nor, n, m);
    }

    public static int sumOfDigit(String str, int idx) {
        if (idx == str.length())
            return 0;

        int recAns = sumOfDigit(str, idx + 1);
        return recAns + (str.charAt(idx) - '0');
    }

    public static long stringToNumber(String str, int idx, long pow) {
        if (idx == -1)
            return 0;
        long recAns = stringToNumber(str, idx - 1, pow * 10);
        return recAns + (str.charAt(idx) - '0') * pow;
    }

    public static boolean checkReverse(String s1, int idx1, String str2, int idx2) {
        if (idx2 == -1)
            return true;
        if (s1.charAt(idx1) != str2.charAt(idx2))
            return false;
        return checkReverse(s1, idx1 + 1, str2, idx2 - 1);
    }

    public static String seprateDuplicates(String str) {
        if (str.length() == 1)
            return str;

        char ch = str.charAt(0);
        String ros = str.substring(1);

        String result = seprateDuplicates(ros);
        if (ch != result.charAt(0))
            return ch + result;
        else
            return ch + "*" + result;
    }

    public static void seprateDuplicates_wayUp(String str, int idx, String ans) {
        if (idx == str.length() - 1) {
            System.out.println(ans + str.charAt(str.length() - 1));
            return;
        }

        char ch = str.charAt(idx);
        if (ch == str.charAt(idx + 1))
            seprateDuplicates_wayUp(str, idx + 1, ans + ch + "*");
        else
            seprateDuplicates_wayUp(str, idx + 1, ans + ch);
    }

    public static void removeAdjacentDuplicates(String str, int idx, String ans) {
        if (idx == str.length() - 1) {
            System.out.println(ans + str.charAt(idx));
            return;
        }

        char ch = str.charAt(idx);
        if (ch != str.charAt(idx + 1))
            removeAdjacentDuplicates(str, idx + 1, ans + ch);
        else
            removeAdjacentDuplicates(str, idx + 1, ans);
    }

    public static String removeAdjacentDuplicates(String str) {
        if (str.length() == 1)
            return str;

        char ch = str.charAt(0);
        String ros = str.substring(1);

        String res = removeAdjacentDuplicates(ros);

        if (ch != res.charAt(0))
            return ch + res;
        return res;
    }

    // https://www.hackerrank.com/contests/pepdec62017/challenges/pep-java-7recursion-22movecharacters/problem
    public static void moveCharacters(String str, int idx, String ans1, String ans2, char moveCh) {
        if (idx == str.length()) {
            System.out.println(ans1 + ans2);
            return;
        }
        char ch = str.charAt(idx);
        if (ch != moveCh) {
            moveCharacters(str, idx + 1, ans1 + ch, ans2, moveCh);
        } else
            moveCharacters(str, idx + 1, ans1, ans2 + ch, moveCh);

    }

    public static String moveCharacters(String str, int idx, char moveCh) {
        if (idx == str.length()) {
            return "";
        }
        char ch = str.charAt(idx);
        String res = moveCharacters(str, idx + 1, moveCh);
        if (ch != moveCh)
            return ch + res;
        else
            return res + ch;
    }

    // https://www.hackerrank.com/contests/pepdec62017/challenges/pep-java-7recursion-23countorremovehi/problem
    public static int countAllHi(String str, int idx) {
        if (idx >= str.length() - 1)
            return 0;

        String s = str.substring(idx, idx + 2);
        if (s.equals("hi"))
            return countAllHi(str, idx + 2) + 1;
        else
            return countAllHi(str, idx + 1);
    }

    public static void countAllHi(String str, int idx, String ans) {
        if (idx >= str.length() - 1) {
            if (idx == str.length() - 1)
                ans += str.charAt(idx);
            System.out.println(ans);
            return;
        }

        String s = str.substring(idx, idx + 2);
        if (s.equals("hi"))
            countAllHi(str, idx + 2, ans);
        else
            countAllHi(str, idx + 1, ans + str.charAt(idx));
    }

    public static String countAllHi_re(String str, int idx) {
        if (idx >= str.length() - 1) {
            if (idx == str.length() - 1)
                return (str.charAt(idx) + "");
            return "";
        }

        String s = str.substring(idx, idx + 2);
        if (s.equals("hi"))
            return countAllHi_re(str, idx + 2);
        else
            return str.charAt(idx) + countAllHi_re(str, idx + 1);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        int idx = scn.nextInt();

        String ans = removeAdjacentDuplicates(str);
        System.out.println(ans.charAt(idx));
        removeAdjacentDuplicates(str, 0, "");

    }

}