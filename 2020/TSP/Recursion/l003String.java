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

    public static void main(String[] args) {
        allPalindromicSubstring("abcdaaddfrdrd");

    }
}