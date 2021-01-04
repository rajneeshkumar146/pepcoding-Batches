public class l001 {

    public static boolean isPalindrome(String str) {
        int si = 0, ei = str.length() - 1;
        while (si < ei) {
            if (str.charAt(si++) != str.charAt(ei--))
                return false;
        }

        return true;
    }

    public static void getAllSubstrings(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int len = 1; i + len <= str.length(); len++) {
                System.out.println(str.substring(i, i + len));
            }
        }
    }

    public static void getAllPalindromicSubstrings(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int len = 1; i + len <= str.length(); len++) {
                String s = str.substring(i, i + len);
                if (isPalindrome(s))
                    System.out.println(s);
            }
        }
    }

    public static String compression1(String str) {
        if (str.length() <= 1)
            return str;
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        for (int i = 1; i <= str.length(); i++) {
            int count = 1;
            while (i < str.length() && str.charAt(i - 1) == str.charAt(i)) {
                count++;
                i++;
            }

            // if(count != 1) sb.append(count);
            if (i < str.length())
                sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    public static String compression2(String str) {
        if (str.length() <= 1)
            return str;
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        for (int i = 1; i <= str.length(); i++) {
            int count = 1;
            while (i < str.length() && str.charAt(i - 1) == str.charAt(i)) {
                count++;
                i++;
            }

            if (count != 1)
                sb.append(count);
            if (i < str.length())
                sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    public static String toggle(String str) {
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

    public static void main(String[] args) {

    }
}