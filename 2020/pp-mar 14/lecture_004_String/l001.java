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

    public static void main(String[] args) {
        // String str = "aaaaaabbbbbbbbccccccdddefghiiiiiiijjjjjk";
        // removeDuplicates(str);
        // removeDuplicatesInsertCount(str);

        // System.out.println(countOfHi("hihijhiihihih"));
        System.out.println(countHiWithoutHit("ihihit"));
    }

}