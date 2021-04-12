
public class l001 {
    public static boolean isPlaindrome(String str, int si, int ei) {
        while (si < ei) {
            if (str.charAt(si) != str.charAt(ei))
                return false;
            si++;
            ei--;
        }

        return true;
    }

    public static void printAllPalindromicSubString(String str) {
        int n = str.length();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPlaindrome(str, i, j)) {
                    String s = str.substring(i, j + 1);
                    System.out.println(s);
                }
            }
        }
    }

    public static void removeDuplicates(String str) {
        if (str.length() == 0)
            return;

        int n = str.length();
        String ans = str.charAt(0) + "";

        int i = 1;
        int changes = 0;
        while (i < n) {
            while (i < n && ans.charAt(ans.length() - 1) == str.charAt(i))
                i++;

            changes++;
            if (i < n)
                ans += str.charAt(i);
            i++;
        }

        System.out.println(ans);
    }

    public static void removeDuplicatesInsertCount(String str) {
        if (str.length() == 0)
            return;

        int n = str.length();
        String ans = str.charAt(0) + "";

        int i = 1;
        int changes = 0, count = 0;

        while (i < n) {
            count = 1;
            while (i < n && ans.charAt(ans.length() - 1) == str.charAt(i)) {
                i++;
                count++;
            }

            changes++;
            if (count > 1)
                ans += count;
            if (i < n)
                ans += str.charAt(i);
            i++;
        }

        System.out.println(ans);
        System.out.println(changes);
    }

    public static int countOfHi(String str) {
        int n = str.length();
        int i = 0, count = 0;
        while (i < n - 1) {
            if (str.charAt(i) == 'h' && str.charAt(i + 1) == 'i') {
                count++;
                i += 2;
            } else
                i++;
        }
        return count;
    }

    public static int countHiWithoutHit(String str) {
        int n = str.length();
        int i = 0, count = 0;
        while (i < n - 1) {
            if (str.charAt(i) == 'h' && str.charAt(i + 1) == 'i') {
                if (i + 2 < n && str.charAt(i + 2) == 't') {
                    i += 3;
                } else {
                    count++;
                    i += 2;
                }
            } else
                i++;
        }

        return count;
    }

    // String immutable test
    public static void stringImmutableTest() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i <= (int) 1e6; i++) {
            str.append(i);
        }

        // System.out.println(str);
    }

    // String Builder Basic Functions.
    public static void stringBuilderBasicFunction() {
        StringBuilder sb = new StringBuilder();
        sb.append("a"); //
        sb.append("bcd"); // O(m), where m is length of string which i want to append.
        System.out.println(sb.toString()); // O(n)
        sb.setCharAt(2, 'e'); // O(1)
        System.out.println(sb.toString()); // O(n)
        sb.deleteCharAt(2); // O(n)
        System.out.println(sb.toString());
    }

    // Toggle Case
    public static String toggleCase(String str) {
        StringBuilder sb = new StringBuilder();
        int n = str.length();
        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z')
                sb.append((char) (ch - 'a' + 'A'));
            else
                sb.append((char) (ch - 'A' + 'a'));
        }
        return sb.toString();
    }

    public static String asciiDifference(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        int n = str.length();
        for (int i = 1; i < n; i++) {
            sb.append(str.charAt(i) - str.charAt(i - 1));
            sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // String str = "aaaaaabbbbbbbbccccccdddefghiiiiiiijjjjjk";
        // removeDuplicates(str);
        // removeDuplicatesInsertCount(str);

        // System.out.println(countOfHi("hihijhiihihih"));
        // System.out.println(countHiWithoutHit("ihihit"));

        // long start = System.currentTimeMillis();
        // stringImmutableTest();
        // long end = System.currentTimeMillis();
        // System.out.println(end - start);

        // stringBuilderBasicFunction();
    }

}