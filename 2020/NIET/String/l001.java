import java.util.ArrayList;

public class l001 {

    public static boolean isPalindrome(String str, int si, int ei) {
        while (si < ei) {
            if (str.charAt(si++) != str.charAt(ei--))
                return false;
        }
        return true;
    }

    public static void generateAllSubString(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                System.out.println(str.substring(i, j + 1));
            }
        }
    }

    public static void printAllPalindromicSubstring(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                if (isPalindrome(str, i, j)) {
                    String s = str.substring(i, j + 1);
                    System.out.println(s);
                }
            }
        }
    }

    public static String toggleCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z')
                sb.append((char) (ch - 'a' + 'A'));
            else
                sb.append((char) (ch - 'A' + 'a'));
        }

        return sb.toString();
    }

    public static String difference(String str) {
        if (str.length() <= 1)
            return str;

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            int diff = str.charAt(i) - str.charAt(i - 1);
            sb.append(diff);
            sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    public static String compression1(String str) {
        if (str.length() <= 1)
            return str;

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        int i = 1;
        while (i < str.length()) {
            while (i < str.length() && str.charAt(i - 1) == str.charAt(i))
                i++;

            if (i < str.length())
                sb.append(str.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public static String compression2(String str) {
        if (str.length() <= 1)
            return str;

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        int i = 1;
        while (i < str.length()) {
            int count = 1;
            while (i < str.length() && str.charAt(i - 1) == str.charAt(i)) {
                i++;
                count++;
            }

            if (count != 1)
                sb.append(count);
            if (i < str.length())
                sb.append(str.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    public static void removePrimes(ArrayList<Integer> al) {
        for (int i = al.size() - 1; i >= 0; i--) {
            if (isPrime(al.get(i))) {
                al.remove(i);
            }
        }
    }

    public static void test1(String str) {
        // System.out.println(str.charAt(2));
        String ans = str.substring(1);
        ans = str.substring(2, 3);

        System.out.println(ans.length());
    }

    public static void test2() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            str.append(i);
        }
        System.out.println(str);
    }

    public static void main(String[] args) {
        // String str = "abcdef";
        // generateAllSubString(str);
        // test2();
        // System.out.println((char) ('P' - 'A' + 'a'));
        // System.out.println((char) ('x' - 'a' + 'A'));

        String str = "wwwwaaadexxxxxx";
        System.out.println(compression2(str));
    }
}