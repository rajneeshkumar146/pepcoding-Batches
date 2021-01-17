import java.util.ArrayList;

public class l002 {

    public static void printSS(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return;
        }

        printSS(str, idx + 1, ans + str.charAt(idx));
        printSS(str, idx + 1, ans);
    }

    public static void printSS_02(String str, int idx, StringBuilder ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return;
        }

        ans.append(str.charAt(idx));
        printSS_02(str, idx + 1, ans);
        ans.deleteCharAt(ans.length() - 1);

        printSS_02(str, idx + 1, ans);
    }

    // return Type
    public static ArrayList<String> subseq_03(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> recAns = subseq_03(str, idx + 1);

        ArrayList<String> myAns = new ArrayList<>(recAns); // nahi anne ki choise dekhli.

        char ch = str.charAt(idx);
        for (String s : recAns) {
            myAns.add(ch + s);
        }

        return myAns;
    }

    public static void printASCIISS(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return;
        }

        printASCIISS(str, idx + 1, ans + str.charAt(idx));
        printASCIISS(str, idx + 1, ans + (int) str.charAt(idx));
        printASCIISS(str, idx + 1, ans);
    }

    public static ArrayList<String> printASCIISS_Ret(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> recAns = printASCIISS_Ret(str, idx + 1);

        ArrayList<String> myAns = new ArrayList<>(); // nahi anne ki choise dekhli.

        char ch = str.charAt(idx);
        for (String s : recAns) {
            myAns.add(ch + s);
            myAns.add((int) ch + s);
            myAns.add(s);
        }

        return myAns;
    }

    public static void subsequence() {
        String str = "abc";
        // printSS(str, 0, "");
        // printSS_02(str,0,new StringBuilder());
        // System.out.println(subseq_03(str, 0));
        printASCIISS(str, 0, "");
    }

    static String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static ArrayList<String> getKPC(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> recAns = getKPC(str, idx + 1);
        ArrayList<String> myAns = new ArrayList<>();

        char ch = str.charAt(idx);
        String code = codes[ch - '0'];
        int count = 0;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);

            for (String s : recAns) {
                myAns.add(c + s);
            }
        }

        return myAns;
    }

    public static int printKPC(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return 1;
        }

        char ch = str.charAt(idx);
        String code = codes[ch - '0'];
        int count = 0;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            count += printKPC(str, idx + 1, ans + c);
        }

        return count;
    }

    public static void getMazePaths_multiMoves(int sr, int sc, int dr, int dc, String ans) {
        if (sr == dr && sc == dc) {
            System.out.println(ans);
            return;
        }

        for (int jump = 1; sc + jump <= dc; jump++)
            getMazePaths_multiMoves(sr, sc + jump, dr, dc, ans + 'h' + jump);
        for (int jump = 1; sr + jump <= dr; jump++)
            getMazePaths_multiMoves(sr + jump, sc, dr, dc, ans + 'v' + jump);
        for (int jump = 1; sr + jump <= dr && sc + jump <= dc; jump++)
            getMazePaths_multiMoves(sr + jump, sc + jump, dr, dc, ans + 'd' + jump);

    }

    public static ArrayList<String> getMazePaths_multiMoves(int sr, int sc, int dr, int dc) {

        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        for (int jump = 1; sc + jump <= dc; jump++) {
            ArrayList<String> Horizontal = getMazePaths_multiMoves(sr, sc + jump, dr, dc);
            for (String s : Horizontal) {
                myAns.add("h" + jump + s);
            }
        }

        for (int jump = 1; sr + jump <= dr; jump++) {
            ArrayList<String> Vertical = getMazePaths_multiMoves(sr + jump, sc, dr, dc);
            for (String s : Vertical) {
                myAns.add("v" + jump + s);
            }
        }

        for (int jump = 1; sr + jump <= dr && sc + jump <= dc; jump++) {
            ArrayList<String> Digonal = getMazePaths_multiMoves(sr + jump, sc + jump, dr, dc);
            for (String s : Digonal) {
                myAns.add("d" + jump + s);
            }
        }

        return myAns;
    }

    public static void main(String[] args) {
        subsequence();
    }

}