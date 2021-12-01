import java.util.ArrayList;

public class l003 {
    public static ArrayList<String> getSubseq(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(0);
        str = str.substring(1);
        ArrayList<String> ans = getSubseq(str);
        ArrayList<String> myRes = new ArrayList<>(ans);

        for (String s : ans) {
            myRes.add(ch + s);
        }

        return myRes;
    }

    public static String[] words = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static ArrayList<String> getKPC(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(0);
        int idx = ch - '0';
        String word = words[idx];

        ArrayList<String> recAns = getKPC(str.substring(1));
        ArrayList<String> myAns = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            for (String s : recAns) {
                myAns.add(c + s);
            }
        }

        return myAns;
    }

    public static ArrayList<String> mazePath(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myRes = new ArrayList<>();
        if (sc + 1 <= dc) {
            ArrayList<String> horizontal = mazePath(sr, sc + 1, dr, dc);
            for (String s : horizontal)
                myRes.add('h' + s);
        }

        if (sr + 1 <= dr) {
            ArrayList<String> vertical = mazePath(sr + 1, sc, dr, dc);
            for (String s : vertical)
                myRes.add('v' + s);
        }

        return myRes;
    }

    public static ArrayList<String> getStairPath(int n) {
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> ans = new ArrayList<>();
        for (int jump = 1; jump <= 3 && n - jump >= 0; jump++) {
            ArrayList<String> recAns = getStairPath(n - jump);
            for (String s : recAns) {
                ans.add(jump + s);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(getSubseq("abcd"));
    }
}