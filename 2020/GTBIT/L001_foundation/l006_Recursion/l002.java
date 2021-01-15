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

    public static void subsequence() {
        String str = "abc";
        // printSS(str, 0, "");
        // printSS_02(str,0,new StringBuilder());
        // System.out.println(subseq_03(str, 0));
        printASCIISS(str,0,"")''
    }

    public static void main(String[] args) {
        subsequence();
    }

}