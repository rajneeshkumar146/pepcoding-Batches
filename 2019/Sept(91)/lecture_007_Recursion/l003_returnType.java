import java.util.Scanner;
import java.util.ArrayList;
public class l003_returnType {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        // String str=scn.nextLine();

        //    String str="ABCD";
        //    System.out.println(subseq(str));
        //    System.out.println(removeHi(str));
        // System.out.println(compression("aaaaabbbbbccccdcccefghh",1));

        // System.out.println(mazePath(0,0,4,5));
        // System.out.println(mazePath_diag(0,0,2,2));
        // System.out.println(mazePath_diag_hei(0,0,4,6));
        // System.out.println(mazePath_diag_Minhei(0,0,2,2));
        System.out.println(mazePath_diag_multi(0, 0, 3, 3));
    }

    public static ArrayList < String > subseq(String str) {
        if (str.length() == 0) {
            ArrayList < String > base = new ArrayList < > ();
            base.add("");
            return base;
        }

        char ch = str.charAt(0);
        String ros = str.substring(1);

        ArrayList < String > recAns = subseq(ros);

        ArrayList < String > myAns = new ArrayList < > ();
        // myAns.addAll(recAns); 

        for (String s: recAns) {
            myAns.add(s);
        }

        for (String s: recAns) {
            myAns.add(ch + s);
        }

        return myAns;

    }

    public static String removeHi(String ques) {
        if (ques.length() == 0) {
            return "";
        }
        // if(ques.length()>1 && ques.charAt(0)=='h' && ques.charAt(1)=='i')

        if (ques.length() > 1 && ques.substring(0, 2).equals("hi"))
            return removeHi(ques.substring(2));
        else {
            char ch = ques.charAt(0);
            return ch + removeHi(ques.substring(1));
        }
    }

    public static String removeDuplicates(String ques) {
        if (ques.length() == 1) {
            return ques;
        }

        char ch = ques.charAt(0);
        String recAns = removeDuplicates(ques.substring(1));
        if (ch == recAns.charAt(0))
            return recAns;
        else
            return ch + recAns;
    }

    public static String removeHiExceptHit(String ques) {
        if (ques.length() == 0) {
            return "";
        }
        // if(ques.length()>1 && ques.charAt(0)=='h' && ques.charAt(1)=='i')

        if (ques.length() > 1 && ques.substring(0, 2).equals("hi"))
            if (ques.length() > 2 && ques.substring(0, 3).equals("hit"))
                return "hit" + removeHiExceptHit(ques.substring(3));
            else
                return removeHiExceptHit(ques.substring(2));
        else {
            char ch = ques.charAt(0);
            return ch + removeHiExceptHit(ques.substring(1));
        }
    }

    public static String compression(String str, int idx, int count) {
        if (idx == str.length()) {
            String ans = str.charAt(idx - 1) + (count > 1 ? count + "" : "");
            return ans;
        }

        if (str.charAt(idx - 1) == str.charAt(idx)) {
            return compression(str, idx + 1, count + 1);
        } else {
            String ans = str.charAt(idx - 1) + (count > 1 ? count + "" : "");
            return ans + compression(str, idx + 1, 1);
        }
    }

    public static ArrayList < String > mazePath(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList < String > base = new ArrayList < > ();
            base.add("");
            return base;
        }


        ArrayList < String > ans = new ArrayList < > ();
        if (sc + 1 <= ec) {
            ArrayList < String > Horizontal = mazePath(sr, sc + 1, er, ec);
            for (String s: Horizontal) {
                ans.add("H" + s);
            }
        }

        if (sr + 1 <= er) {
            ArrayList < String > vecrtical = mazePath(sr + 1, sc, er, ec);
            for (String s: vecrtical) {
                ans.add("V" + s);
            }
        }

        return ans;
    }


    public static ArrayList < String > mazePath_diag(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList < String > base = new ArrayList < > ();
            base.add("");
            return base;
        }


        ArrayList < String > ans = new ArrayList < > ();
        if (sc + 1 <= ec) {
            ArrayList < String > Horizontal = mazePath_diag(sr, sc + 1, er, ec);
            for (String s: Horizontal) {
                ans.add("H" + s);
            }
        }

        if (sr + 1 <= er) {
            ArrayList < String > vecrtical = mazePath_diag(sr + 1, sc, er, ec);
            for (String s: vecrtical) {
                ans.add("V" + s);
            }
        }

        if (sr + 1 <= er && sc + 1 <= ec) {
            ArrayList < String > diagonal = mazePath_diag(sr + 1, sc + 1, er, ec);
            for (String s: diagonal) {
                ans.add("D" + s);
            }
        }

        return ans;
    }


    public static ArrayList < String > mazePath_diag_multi(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList < String > base = new ArrayList < > ();
            base.add("");
            return base;
        }


        ArrayList < String > ans = new ArrayList < > ();
        for (int i = 1; sc + i <= ec; i++) {
            ArrayList < String > Horizontal = mazePath_diag_multi(sr, sc + i, er, ec);
            for (String s: Horizontal) {
                ans.add("H" + i + s);
            }
        }

        for (int i = 1; sr + i <= er; i++) {
            ArrayList < String > vecrtical = mazePath_diag_multi(sr + i, sc, er, ec);
            for (String s: vecrtical) {
                ans.add("V" + i + s);
            }
        }

        for (int i = 1; sr + i <= er && sc + i <= ec; i++) {
            ArrayList < String > diagonal = mazePath_diag_multi(sr + i, sc + i, er, ec);
            for (String s: diagonal) {
                ans.add("D" + i + s);
            }
        }

        return ans;
    }

    public static int mazePath_diag_hei(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            return 0;
        }

        int maxHei = 0;
        if (sc + 1 <= ec) {
            maxHei = Math.max(maxHei, mazePath_diag_hei(sr, sc + 1, er, ec));
        }

        if (sr + 1 <= er) {
            maxHei = Math.max(maxHei, mazePath_diag_hei(sr + 1, sc, er, ec));
        }

        if (sr + 1 <= er && sc + 1 <= ec) {
            maxHei = Math.max(maxHei, mazePath_diag_hei(sr + 1, sc + 1, er, ec));
        }

        return maxHei + 1;
    }


    public static int mazePath_diag_Minhei(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            return 0;
        }

        int minHei = (int) 1e6;
        if (sc + 1 <= ec) {
            minHei = Math.min(minHei, mazePath_diag_Minhei(sr, sc + 1, er, ec));
        }

        if (sr + 1 <= er) {
            minHei = Math.min(minHei, mazePath_diag_Minhei(sr + 1, sc, er, ec));
        }

        if (sr + 1 <= er && sc + 1 <= ec) {
            minHei = Math.min(minHei, mazePath_diag_Minhei(sr + 1, sc + 1, er, ec));
        }

        return minHei + 1;
    }





}