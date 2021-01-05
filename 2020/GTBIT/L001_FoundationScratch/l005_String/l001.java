import java.util.ArrayList;

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

    public static String diffrenceOfTwoNumber(String str) {
        if (str.length() <= 1)
            return str;
        StringBuilder sb = new StringBuilder();

        sb.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            char ch0 = str.charAt(i - 1);
            char ch1 = str.charAt(i);
            sb.append(ch1 - ch0);
            sb.append(ch1);
        }

        return sb.toString();
    }

    public static ArrayList<String> subseq(String str) {
        ArrayList<String> ans = new ArrayList<>();
        ans.add("");

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int size = ans.size();
            for (int j = 0; j < size; j++) {
                ans.add(ans.get(j) + ch);
            }
        }

        return ans;
    }

    public static void solution(String str) {
        ArrayList<String> ans = new ArrayList<>();
        ans.add("");
         for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            ArrayList<String> myAns = new ArrayList<>();
            
            for(String s : ans){
                for(int j=0;j<=s.length();j++){
                    myAns.add(s.substring(0,j) + ch + s.substring(j));
                }
            }
            
            ans = myAns;
         }
         
         System.out.println(ans);
    }

    public static void main(String[] args) {
        System.out.println();

    }
}