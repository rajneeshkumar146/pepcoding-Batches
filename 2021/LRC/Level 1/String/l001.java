public class l001 {
    public static void experiment01() {
        String str = "";
        int n = (int) 1e5;
        for (int i = 0; i < n; i++) {
            str += i;
        }

        System.out.println(str);
    }

    public static void experiment02() {
        StringBuilder str = new StringBuilder();
        int n = (int) 1e5;
        for (int i = 0; i < n; i++) {
            str.append(i);
        }

        System.out.println(str.toString());
    }

    public static void printAllSubstring(String str) {
        int n = str.length();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                System.out.println(str.substring(i, j + 1));
            }
        }
    }

    public static boolean isPlaindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;

            i++;
            j--;
        }

        return true;
    }

    public static void printAllPalindromicSubstring(String str) {
        int n = str.length();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String s = str.substring(i, j + 1);
                if (isPlaindrome(s))
                    System.out.println(s);
            }
        }
    }

    public static String compression1(String str) {
        if (str.length() <= 1)
            return str;

        StringBuilder sb = new StringBuilder();
        int i = 1, n = str.length();
        sb.append(str.charAt(0));
        while (i < n) {
            while (i < n && str.charAt(i) == sb.charAt(sb.length() - 1))
                i++;

            if (i < n)
                sb.append(str.charAt(i));

            i++;
        }
        return sb.toString();
    }

    public static String compression2(String str) {
        if (str.length() <= 1)
            return str;

        StringBuilder sb = new StringBuilder();
        int i = 1, n = str.length();
        sb.append(str.charAt(0));
        while (i < n) {
            int count = 1;
            while (i < n && str.charAt(i) == sb.charAt(sb.length() - 1)) {
                i++;
                count++;
            }

            if (count > 1)
                sb.append(count);
            if (i < n)
                sb.append(str.charAt(i));

            i++;
        }
        return sb.toString();
    }

    public static String toggleString(String str) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z')
                ans.append((char) (ch - 'a' + 'A'));
            else if (ch >= 'A' && ch <= 'Z')
                ans.append((char) (ch - 'A' + 'a'));
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        // experiment01();
        // experiment02();
        System.out.println(toggleString("abcDEFgh"));
    }
}