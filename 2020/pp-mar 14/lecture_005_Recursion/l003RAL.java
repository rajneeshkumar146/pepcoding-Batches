import java.util.*;

public class l003RAL {
    public static ArrayList<String> subseq(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(0);
        ArrayList<String> recAns = subseq(str.substring(1));

        ArrayList<String> myAns = new ArrayList<>();
        for (String s : recAns) {
            myAns.add(s);
            myAns.add(ch + s);
        }

        return myAns;
    }

    public static String[] nokiaKeys = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static ArrayList<String> getKPC(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(0);
        String code = nokiaKeys[ch - '0'];

        ArrayList<String> recAns = getKPC(str.substring(1));

        ArrayList<String> myAns = new ArrayList<>();
        for (int i = 0; i < code.length(); i++) {
            for (String s : recAns)
                myAns.add(code.charAt(i) + s);
        }

        return myAns;
    }

    // 11283, 11023, 0 , 113410111
    public static ArrayList<String> decodeWays(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        if (str.charAt(0) == '0')
            return new ArrayList<>();

        char ch1 = str.charAt(0);
        ArrayList<String> myAns = new ArrayList<>();
        ArrayList<String> recAnsFor1Len = decodeWays(str.substring(1));
        for (String s : recAnsFor1Len) {
            myAns.add((char) ('a' + ch1 - '1') + s);
        }

        if (str.length() > 1) {
            int num = (ch1 - '0') * 10 + (str.charAt(1) - '0');
            if (num <= 26) {
                ArrayList<String> recAnsFor2Len = decodeWays(str.substring(2));
                for (String s : recAnsFor2Len) {
                    myAns.add((char) ('a' + num - 1) + s);
                }
            }
        }

        return myAns;
    }

    public static ArrayList<String> decodeWays2(String str) {
        if (str.length() == 0) {
            ArrayList<String> ans = new ArrayList<>();
            ans.add("");
            return ans;
        }

        char ch1 = str.charAt(0);
        String word = nokiaKeys[ch1 - '0'];
        ArrayList<String> myAns = new ArrayList<>();

        ArrayList<String> recAns1 = decodeWays2(str.substring(1));
        for (int i = 0; i < word.length(); i++) {
            for (String s : recAns1) {
                myAns.add(word.charAt(i) + s);
            }
        }

        if (str.length() > 1) {
            char ch2 = str.charAt(1);
            int num = (ch1 - '0') * 10 + (ch2 - '0');
            if (num == 10 || num == 11) {
                ArrayList<String> recAns2 = decodeWays2(str.substring(2));
                word = nokiaKeys[num];
                for (int i = 0; i < word.length(); i++) {
                    for (String s : recAns2) {
                        myAns.add(word.charAt(i) + s);
                    }
                }
            }
        }

        return myAns;
    }

    public static ArrayList<String> getStairPath(int n) {
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        for (int i = 1; i <= 3 && n - i >= 0; i++) {
            ArrayList<String> recAns = getStairPath(n - i);
            for (String s : recAns) {
                myAns.add(i + s);
            }
        }

        return myAns;
    }

    public static ArrayList<String> boardPath(int n) {
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
            ArrayList<String> recAns = boardPath(n - dice);
            for (String s : recAns) {
                myAns.add(dice + s);
            }
        }

        return myAns;
    }

    public static ArrayList<String> boardPathOnArray(int n, int[] move) {
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        for (int i = 0; i < move.length && n - move[i] >= 0; i++) {
            ArrayList<String> recAns = boardPathOnArray(n - move[i], move);
            for (String s : recAns) {
                myAns.add(move[i] + s);
            }
        }

        return myAns;
    }

    public static ArrayList<String> mazePath_HV(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        if (sc + 1 <= ec) {
            ArrayList<String> horizontal = mazePath_HV(sr, sc + 1, er, ec);
            for (String s : horizontal)
                myAns.add("H" + s); // CPP : string(1,'H') + s;
        }

        if (sr + 1 <= er) {
            ArrayList<String> vertical = mazePath_HV(sr + 1, sc, er, ec);
            for (String s : vertical)
                myAns.add("V" + s);
        }

        return myAns;
    }

    public static ArrayList<String> mazePath_HDV(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        if (sc + 1 <= ec) {
            ArrayList<String> horizontal = mazePath_HDV(sr, sc + 1, er, ec);
            for (String s : horizontal)
                myAns.add("H" + s); // CPP : string(1,'H') + s;
        }

        if (sr + 1 <= er && sc + 1 <= ec) {
            ArrayList<String> diagonal = mazePath_HDV(sr + 1, sc + 1, er, ec);
            for (String s : diagonal)
                myAns.add("D" + s);
        }

        if (sr + 1 <= er) {
            ArrayList<String> vertical = mazePath_HDV(sr + 1, sc, er, ec);
            for (String s : vertical)
                myAns.add("V" + s);
        }

        return myAns;
    }

    public static ArrayList<String> mazePath_MultiHDV(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        for (int jump = 1; sc + jump <= ec; jump++) {
            ArrayList<String> horizontal = mazePath_MultiHDV(sr, sc + jump, er, ec);
            for (String s : horizontal)
                myAns.add("H" + jump + s);
        }

        for (int jump = 1; sc + jump <= ec && sr + jump <= er; jump++) {
            ArrayList<String> diagonal = mazePath_MultiHDV(sr + jump, sc + jump, er, ec);
            for (String s : diagonal)
                myAns.add("D" + jump + s);
        }

        for (int jump = 1; sr + jump <= er; jump++) {
            ArrayList<String> vertical = mazePath_MultiHDV(sr + jump, sc, er, ec);
            for (String s : vertical)
                myAns.add("V" + jump + s); // "V" + to_string(jump) + s
        }

        return myAns;
    }

    public static void main(String[] args) {
        // // System.out.println(subseq("abcd"));
        // for (String s : getKPC("456")) {
        // System.out.println(s);
        // }

        // for (String s : decodeWays("1230")) {
        // System.out.println(s);
        // }

        // System.out.println((int) '0');
        // System.out.println((int) '1');
        // System.out.println(('1' + '0' + ""));

        // System.out.println(getStairPath(5));

        System.out.println(mazePath_MultiHDV(0, 0, 3, 3));
    }
}