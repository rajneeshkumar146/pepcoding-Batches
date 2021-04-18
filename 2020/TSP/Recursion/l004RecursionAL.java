import java.util.ArrayList;

public class l004RecursionAL {
    public static ArrayList<String> mazePath_HVD(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        if (sc + 1 <= ec) {
            ArrayList<String> horizontalPaths = mazePath_HVD(sr + 0, sc + 1, er, ec);
            for (String s : horizontalPaths) {
                myAns.add("H" + s);
            }
        }

        if (sr + 1 <= er && sc + 1 <= ec) {
            ArrayList<String> diagonalPaths = mazePath_HVD(sr + 1, sc + 1, er, ec);
            for (String s : diagonalPaths) {
                myAns.add("D" + s);
            }
        }

        if (sr + 1 <= er) {
            ArrayList<String> verticalPaths = mazePath_HVD(sr + 1, sc + 0, er, ec);
            for (String s : verticalPaths) {
                myAns.add("V" + s);
            }
        }

        return myAns;
    }

    public static ArrayList<String> mazePath_HVDMultiJump(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        for (int jump = 1; sc + jump <= ec; jump++) {
            ArrayList<String> horizontalPaths = mazePath_HVDMultiJump(sr, sc + jump, er, ec);
            for (String s : horizontalPaths) {
                myAns.add("H" + jump + s);
            }
        }

        for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++) {
            ArrayList<String> diagonalPaths = mazePath_HVDMultiJump(sr + jump, sc + jump, er, ec);
            for (String s : diagonalPaths) {
                myAns.add("D" + jump + s);
            }
        }

        for (int jump = 1; sr + jump <= er; jump++) {
            ArrayList<String> verticalPaths = mazePath_HVDMultiJump(sr + jump, sc, er, ec);
            for (String s : verticalPaths) {
                myAns.add("V" + jump + s);
            }
        }

        return myAns;
    }

    public static ArrayList<String> mazePath(int sr, int sc, int er, int ec, int[][] dir, String[] dirS) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> ans = new ArrayList<>();
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                ArrayList<String> recAns = mazePath(r, c, er, ec, dir, dirS);
                for (String s : recAns) {
                    ans.add(dirS[d] + s);
                }
            }
        }

        return ans;
    }

    public static ArrayList<String> mazePathGenric(int sr, int sc, int er, int ec, int[][] dir, String[] dirS,
            int Rad) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> ans = new ArrayList<>();
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Rad; rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                    ArrayList<String> recAns = mazePathGenric(r, c, er, ec, dir, dirS, Rad);
                    for (String s : recAns) {
                        ans.add(dirS[d] + rad + s);
                    }
                } else
                    break;
            }
        }

        return ans;
    }

    // 0 -> free cell, 1 -> blocked cell
    public static ArrayList<String> blockedMazePath(int[][] mat, int sr, int sc, int[][] dir, String[] dirS) {
        int n = mat.length;
        int m = mat[0].length;
        if (sr == n - 1 && sc == m - 1) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> ans = new ArrayList<>();
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= n - 1 && c <= m - 1 && mat[r][c] == 0) {
                ArrayList<String> recAns = blockedMazePath(mat, r, c, dir, dirS);
                for (String s : recAns) {
                    ans.add(dirS[d] + s);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] dir = { { 1, 0 }, { 1, 1 }, { 0, 1 } };
        String[] dirS = { "V", "D", "H" };
        int n = 4;
        int m = 7;
        System.out.println(mazePathGenric(0, 0, n - 1, m - 1, dir, dirS, Math.max(n, m)).size());

    }
}