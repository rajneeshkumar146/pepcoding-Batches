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

    public static void main(String[] args) {
        System.out.println(getSubseq("abcd"));
    }
}