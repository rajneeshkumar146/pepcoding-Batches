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

    }

    public static void main(String[] args) {
        // // System.out.println(subseq("abcd"));
        // for (String s : getKPC("456")) {
        // System.out.println(s);
        // }

        for (String s : decodeWays("1230")) {
            System.out.println(s);
        }

        // System.out.println((int) '0');
        // System.out.println((int) '1');
        // System.out.println(('1' + '0' + ""));
    }
}