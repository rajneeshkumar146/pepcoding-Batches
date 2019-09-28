import java.util.ArrayList;

public class T004_ReturnType {

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // MazePath();
        // System.out.println(stairs(0, 10).size());
        System.out.println(keyPad(589));

    }

    public static void MazePath() {
        ArrayList<String> MazePathAns = MazePathSimple(0, 0, 2, 2);
        System.out.println(MazePathAns);
        System.out.println(MazePathAns.size());

        ArrayList<String> MazePathAns2 = MazePathWithDiagonal(0, 0, 2, 2);
        System.out.println(MazePathAns2);
        System.out.println(MazePathAns2.size());

        ArrayList<String> MazePathAns3 = MazePathMultiMoves(0, 0, 2, 2);
        System.out.println(MazePathAns3);
        System.out.println(MazePathAns3.size());

    }

    public static ArrayList<String> MazePathSimple(int sr, int sc, int er, int ec) {

        if (sr == er && sc == ec) {
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;

        }

        // if(sr>er || sc>ec){
        // ArrayList<String> baseAns = new ArrayList<>();
        // return baseAns;

        // }

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

    public static ArrayList<String> MazePathWithDiagonal(int sr, int sc, int er, int ec) {

        if (sr == er && sc == ec) {
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;

        }

        ArrayList<String> myAns = new ArrayList<>();
        if (sc + 1 <= ec) {
            ArrayList<String> HorizontalAns = MazePathWithDiagonal(sr, sc + 1, er, ec);
            for (String smallAns : HorizontalAns) {
                String realAns = "H" + smallAns;
                myAns.add(realAns);
            }
        }

        if (sr + 1 <= er) {
            ArrayList<String> verticalAns = MazePathWithDiagonal(sr + 1, sc, er, ec);
            for (String smallAns : verticalAns) {
                String realAns = "V" + smallAns;
                myAns.add(realAns);
            }
        }

        if (sr + 1 <= er && sc + 1 <= ec) {
            ArrayList<String> verticalAns = MazePathWithDiagonal(sr + 1, sc + 1, er, ec);
            for (String smallAns : verticalAns) {
                String realAns = "D" + smallAns;
                myAns.add(realAns);
            }
        }

        return myAns;
    }

    public static ArrayList<String> MazePathMultiMoves(int sr, int sc, int er, int ec) {

        if (sr == er && sc == ec) {
            ArrayList<String> baseAns = new ArrayList<>();
            baseAns.add("");
            return baseAns;

        }

        ArrayList<String> myAns = new ArrayList<>();
        for (int i = 1; sc + i <= ec; i++) {
            ArrayList<String> HorizontalAns = MazePathMultiMoves(sr, sc + i, er, ec);
            for (String smallAns : HorizontalAns) {
                String realAns = "H" + i + smallAns;
                myAns.add(realAns);
            }
        }

        for (int i = 1; sr + i <= er; i++) {
            ArrayList<String> verticalAns = MazePathMultiMoves(sr + i, sc, er, ec);
            for (String smallAns : verticalAns) {
                String realAns = "V" + i + smallAns;
                myAns.add(realAns);
            }
        }

        for (int i = 1; sr + i <= er && sc + i <= ec; i++) {
            ArrayList<String> verticalAns = MazePathMultiMoves(sr + i, sc + i, er, ec);
            for (String smallAns : verticalAns) {
                String realAns = "D" + i + smallAns;
                myAns.add(realAns);
            }
        }

        return myAns;
    }

    public static ArrayList<String> subsequence(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<String>();
            base.add("");
            return base;

        }

        ArrayList<String> myAns = new ArrayList<String>();

        char ch = str.charAt(0);
        String nextState = str.substring(1);
        ArrayList<String> smallAns = subsequence(nextState); // chance to every character.
        for (String s : smallAns) {
            myAns.add(s); // not coming situation.
            myAns.add(ch + s); // coming situation.
        }

        return myAns;

    }

    public static ArrayList<String> stairs(int si, int ei) {

        if (si == ei) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();

        if (si + 1 <= ei) {
            ArrayList<String> recAns = stairs(si + 1, ei);
            for (String s : recAns) {
                myAns.add(1 + s);
            }
        }

        if (si + 2 <= ei) {
            ArrayList<String> recAns = stairs(si + 2, ei);
            for (String s : recAns) {
                myAns.add(2 + s);
            }
        }

        if (si + 3 <= ei) {
            ArrayList<String> recAns = stairs(si + 3, ei);
            for (String s : recAns) {
                myAns.add(3 + s);
            }
        }

        if (si + 4 <= ei) {
            ArrayList<String> recAns = stairs(si + 4, ei);
            for (String s : recAns) {
                myAns.add(4 + s);
            }
        }

        if (si + 5 <= ei) {
            ArrayList<String> recAns = stairs(si + 5, ei);
            for (String s : recAns) {
                myAns.add(5 + s);
            }
        }

        if (si + 6 <= ei) {
            ArrayList<String> recAns = stairs(si + 6, ei);
            for (String s : recAns) {
                myAns.add(6 + s);
            }
        }

        return myAns;

    }

    public static String getCode(int digit) {
        String[] arr = { "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "uvw", "xyz", "*+#" };
        return arr[digit];

    }

    public static ArrayList<String> keyPad(int num) {
        if (num == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        int digit = num % 10;
        num = num / 10;

        ArrayList<String> myAns = new ArrayList<>();

        ArrayList<String> recAns = keyPad(num);
        String str = getCode(digit);
        for (int i = 0; i < str.length(); i++) {
            for (String s : recAns) {
                myAns.add(s + str.charAt(i));
            }
        }
        return myAns;
    }

}