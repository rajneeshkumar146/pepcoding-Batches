public class l003String {

    public static boolean isPalindrome(String str, int i, int j) {
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }

        return true;
    }

    public static void printAllSubString(String str) {
        int n = str.length();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                System.out.println(str.substring(i, j + 1));
            }

        }
    }

    public static void allPalindromicSubstring(String str) {
        int n = str.length();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(str, i, j))
                    System.out.println(str.substring(i, j + 1));
            }
        }
    }

    public static String compression(String str) {
        if (str.length() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        int n = str.length();
        int i = 1;
        while (i < n) {
            while (i < n && sb.charAt(sb.length() - 1) == str.charAt(i)) {
                i++;
            }

            if (i < n)
                sb.append(str.charAt(i++));
        }

        return sb.toString();
    }

    public static String compression2(String str) {
        if (str.length() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        int n = str.length();
        int i = 1;
        while (i < n) {
            int count = 1;
            while (i < n && sb.charAt(sb.length() - 1) == str.charAt(i)) {
                i++;
                count++;
            }

            if (count > 1)
                sb.append(count);

            if (i < n)
                sb.append(str.charAt(i++));
        }

        return sb.toString();
    }

    public static int compression_switchCount(String str) {
        if (str.length() == 0)
            return 0;

        char prevCh = str.charAt(0);
        int n = str.length();
        int i = 1, switchCount = 0;

        while (i < n) {
            while (i < n && prevCh == str.charAt(i))
                i++;

            if (i < n)
                prevCh = str.charAt(i++);
            switchCount++;
        }

        return switchCount;
    }

    public static String toggleCases(String str) {
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

    public static void removeHi(String str) {
        if (str.length() == 0)
            return;
        StringBuilder ans = new StringBuilder();

        int i = 0, n = str.length(), count = 0;
        while (i < n) {
            if (i < n - 1 && str.charAt(i) == 'h' && str.charAt(i + 1) == 'i') {
                i += 2;
                count++;
                continue;
            }

            ans.append(str.charAt(i++));
        }

        System.out.println(count);
        System.out.println(ans.toString());
    }

    public static void removeHiWithoutHit(String str) {
        if (str.length() == 0)
            return;
        StringBuilder ans = new StringBuilder();

        int i = 0, n = str.length(), count = 0;
        while (i < n) {
            if (i < n - 1 && str.substring(i, i + 2).equals("hi")) {
                if (i < n - 2 && str.substring(i, i + 3).equals("hit")) {
                    ans.append("hit");
                    i += 3;
                } else {
                    i += 2;
                    count++;
                }
                continue;
            }

            ans.append(str.charAt(i++));
        }

        System.out.println(count);
        System.out.println(ans.toString());
    }

    public static void main(String[] args) {
        // allPalindromicSubstring("abcdaaddfrdrd");
        // System.out.println(toggleCases("pePcoDIng"));
        removeHiWithoutHit("hithihihit");
    }
}