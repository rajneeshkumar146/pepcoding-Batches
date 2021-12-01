import java.util.Scanner;

public class l005 {
    public static Scanner scn = new Scanner(System.in);

    public static int sumOfDigit(String str, int idx) {
        if (idx == str.length())
            return 0;

        int smallAns = sumOfDigit(str, idx + 1);
        return smallAns + (str.charAt(idx) - '0');
    }

    public static int reverseDigit(int n, int recAns) {
        if (n == 0) {
            return recAns;
        }

        int digit = n % 10;
        n /= 10;
        return reverseDigit(n, recAns * 10 + digit);
    }

    public static String moveCharcter(String str, char ch, int idx) {
        if (idx == str.length()) {
            return "";
        }

        char c = str.charAt(idx);
        String smallAns = moveCharcter(str, ch, idx + 1);

        return c != ch ? c + smallAns : smallAns + c;
    }

    public static void moveCharcter_02(String str, char ch, int idx) {
        if (idx == str.length()) {
            return;
        }
        char c = str.charAt(idx);
        if (c != ch)
            System.out.print(c);

        moveCharcter_02(str, ch, idx + 1);

        if (c == ch)
            System.out.print(c);
    }

    public static void moveCharcter() {
        String str = scn.next();
        char ch = scn.next().charAt(0);
        int i = scn.nextInt();

        String ans = moveCharcter(str, ch, 0);
        System.out.println(ans);
        System.out.println(ans.charAt(i));

        moveCharcter_02(str, ch, 0);
    }

    public static String removeDuplicates(String str, int idx) {
        if (idx == str.length() - 1) {
            return str.charAt(idx) + "";
        }

        char ch = str.charAt(idx);
        String smallAns = removeDuplicates(str, idx + 1);
        return smallAns.charAt(0) == ch ? smallAns : ch + smallAns;
    }

    public static int removeHi(String str, int idx, String asf) {
        if (idx == str.length()) {
            System.out.println(asf);
            return 0;
        }
        if (idx < str.length() - 1 && str.charAt(idx) == 'h' && str.charAt(idx + 1) == 'i')
            return removeHi(str, idx + 2, asf) + 1;

        return removeHi(str, idx + 1, asf + str.charAt(idx));
    }

    public static int replaceHiPep(String str, int idx, String asf) {
        if (idx == str.length()) {
            System.out.println(asf);
            return 0;
        }
        if (idx < str.length() - 1 && str.charAt(idx) == 'h' && str.charAt(idx + 1) == 'i')
            return replaceHiPep(str, idx + 2, asf + "pep") + 1;

        return replaceHiPep(str, idx + 1, asf + str.charAt(idx));
    }

    public static int removeHiWithoutHit(String str, int idx, String asf) {
        if (idx == str.length()) {
            System.out.println(asf);
            return 0;
        }
        if (idx < str.length() - 1 && str.charAt(idx) == 'h' && str.charAt(idx + 1) == 'i') {
            if (idx < str.length() - 2 && str.charAt(idx + 2) == 't')
                return removeHiWithoutHit(str, idx + 3, asf + "hit");
            else {
                return removeHiWithoutHit(str, idx + 2, asf + "pep") + 1;
            }
        }

        return removeHiWithoutHit(str, idx + 1, asf + str.charAt(idx));
    }

    public static int decodeWays(String str, int idx, String asf) {
        if (idx == str.length()) {
            System.out.println(asf);
            return 1;
        }
        char ch1 = str.charAt(idx);
        if (ch1 == '0')
            return 0;

        int count = 0;
        count += decodeWays(str, idx + 1, asf + (char) ('a' + ch1 - '1'));

        if (idx < str.length() - 1) {
            int num = (ch1 - '0') * 10 + (str.charAt(idx + 1) - '0');
            if (num <= 26) {
                count += decodeWays(str, idx + 2, asf + (char) ('a' + num - 1));
            }
        }

        return count;
    }

    public static int decodeWays_02(String str, int idx) {
        if (idx == str.length()) {
            return 1;
        }
        char ch1 = str.charAt(idx);
        if (ch1 == '0')
            return 0;

        int count = 0;

        if (ch1 == '*') {
            count += 9 * decodeWays_02(str, idx + 1);
            if (idx < str.length() - 1) {
                char ch2 = str.charAt(idx + 1);
                if (ch2 >= '0' && ch2 <= '6')
                    count += 2 * decodeWays_02(str, idx + 2);
                else
                    count += 1 * decodeWays_02(str, idx + 2);
            }
        } else {
            count += decodeWays_02(str, idx + 1);
            if (idx < str.length() - 1) {
                char ch2 = str.charAt(idx + 1);
                if (ch2 != '*') {
                    int num = (ch1 - '0') * 10 + (ch2 - '0');
                    if (num <= 26)
                        count += decodeWays_02(str, idx + 2);
                } else if (ch1 == '1')
                    count += 9 * decodeWays_02(str, idx + 2);
                else if (ch1 == '2')
                    count += 6 * decodeWays_02(str, idx + 2);
            }
        }
        return count;
    }

    public static int permutation(String str, String asf) {
        if (str.length() == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1);
            count += permutation(ros, asf + ch);
        }

        return count;
    }

    public static String sort(String str) {
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            freq[ch - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            while (freq[i]-- > 0)
                sb.append((char) (i + 'a'));
        }

        return sb.toString();
    }

    public static int permutationUnique(String str, String asf) {
        if (str.length() == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        char prev = '$';
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch != prev) {
                String ros = str.substring(0, i) + str.substring(i + 1);
                count += permutationUnique(ros, asf + ch);
            }
            prev = ch;
        }

        return count;
    }

    public static void targetSum(int[] arr, int idx, int tar, String asf) {
        if (idx == arr.length || tar == 0) {
            if (tar == 0)
                System.out.println(asf + ".");
            return;
        }

        targetSum(arr, idx + 1, tar - arr[idx], asf + arr[idx] + ", ");
        targetSum(arr, idx + 1, tar, asf);
    }

    public static void main(String... args) {
        // System.out.println(sumOfDigit("12345678", 0));
        // System.out.println(reverseDigit(123456789, 0));
        // String ans = removeDuplicates("abbbaaaccccccddddddeeeeeeefffffgh", 0);
        // System.out.println(ans);

        System.out.println(permutationUnique(sort("abacbabac"), ""));

    }
}