import java.util.*;

// Recursion Way up
public class l004RWU {
    public static int subsequence(String ques, String ans) {
        if (ques.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        char ch = ques.charAt(0);
        String roq = ques.substring(1);
        int count = 0;

        count += subsequence(roq, ans);
        count += subsequence(roq, ans + ch);

        return count;
    }

    public static int subsequence(String ques, String ans, ArrayList<String> res) {
        if (ques.length() == 0) {
            res.add(ans);
            return 1;
        }

        char ch = ques.charAt(0);
        String roq = ques.substring(1);
        int count = 0;

        count += subsequence(roq, ans, res);
        count += subsequence(roq, ans + ch, res);

        return count;
    }

    public static String[] nokiaKeys = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static int nokiaKeyPad(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        char ch = str.charAt(0);
        int idx = ch - '0';
        String word = nokiaKeys[idx];
        int count = 0;

        for (int i = 0; i < word.length(); i++)
            count += nokiaKeyPad(str.substring(1), ans + word.charAt(i));

        return count;
    }

    public static int stairPath(int n, String ans) {
        if (n == 0) {
            // System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int jump = 1; jump <= 3 && n - jump >= 0; jump++) {
            count += stairPath(n - jump, ans + jump);
        }

        return count;
    }

    public static int boardPath(int n, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
            count += boardPath(n - dice, ans + dice);
        }

        return count;
    }

    public static int boardPath(int sp, int ep, String ans) {
        if (sp == ep) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
            count += boardPath(sp + dice, ep, ans + dice);
        }

        return count;
    }

    public static int boardPath(int[] arr, int n, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int jump : arr) {
            if (n - jump >= 0)
                count += boardPath(n - jump, n, ans + jump);
        }

        return count;
    }

    public static void main(String[] args) {
        // ArrayList<String> ans = new ArrayList<>();
        // System.out.println(subsequence("abc", "", ans));
        // System.out.println(ans);

        int[] arr = { 2, 5, 3, 1 };
        System.out.println(boardPath(arr, 10, ""));

    }

}