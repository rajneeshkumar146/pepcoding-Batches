import java.util.ArrayList;
import java.util.StringBuilder;

public class T001_basic {

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // experimentOne();
        
    }

    public static void experimentOne() {
        String str = "";
        for (int i = 0; i < 1000000; i++) {
            str += i;
        }

        System.out.print(str);
    }

    public static void stringFunctions() {
        String str = "abcdefghijklmnopqrstuvwxyz";

        System.out.println(str.length());

        String str1 = str.substring(1);
        String str2 = str.substring(1, 12);

        System.out.println(str1);
        System.out.println(str2);

        char ch = str.charAt(2);
        System.out.println(ch);
    }

    public static void stringBuilderFunctions() {
        StringBuilder str = new StringBuilder("abcdefghijklmnopqrstuvwxyz");

        System.out.println(str.length());

        String str1 = str.substring(1);
        String str2 = str.substring(1, 12);

        System.out.println(str1);
        System.out.println(str2);

        char ch = str.charAt(2);
        System.out.println(ch);

        str.setCharAt(2, 'X');
        str.deleteCharAt(3);
        String ans = str.toString();
    }

    public static void removeDuplicates(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < str.length(); i++) {
            char pch = str.charAt(i - 1);
            char ch = str.charAt(i);
            if (pch != ch) {
                sb.append(pch);
            }
        }

        char lch = str.charAt(str.length() - 1);
        sb.append(lch);

        String ans = sb.toString();
        System.out.println(ans);

    }

    public static void removeAndCountDuplicates(String str) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            char pch = str.charAt(i - 1);
            char ch = str.charAt(i);
            if (pch != ch) {
                sb.append(pch);
                if (count > 1) {
                    sb.append(count);
                }
                count = 1;
            } else {
                count++;
            }
        }

        char lch = str.charAt(str.length() - 1);
        sb.append(lch);
        if (count > 1)
            sb.append(count);

        String ans = sb.toString();
        System.out.println(ans);

    }

    public static void allSubstring(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                System.out.println(str.substring(i, j));
            }
        }
    }

    public static ArrayList<String> MazePathSimple(int sr, int sc, int er, int ec) {

        if (sr == er && sc == ec) {
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;

        }

        ArrayList<String> myAns = new ArrayList<>();
        if (sc + 1 <= ec) {
            ArrayList<String> HorizontalAns = MazePathSimple(sr, sc + 1, er, ec);
            for (String smallAns : HorizontalAns) {
                String realAns = "H" + smallAns;
                myAns.add(realAns);
            }
        }

        if (sr + 1 <= er) {
        ArrayList<String> verticalAns = MazePathSimple(sr + 1, sc, er, ec);
        for (String smallAns : verticalAns) {
            String realAns = "V" + smallAns;
            myAns.add(realAns);
        }
    }

        return myAns;

    }

}