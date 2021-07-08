import java.util.ArrayList;

public class l001Basic {

    public static void pppppp(int a, int b) {
        if (a > b)
            return;
    }

    public static void ppppp(int a, int b) {
        System.out.println(a);
        pppppp(a + 1, b);
    }

    public static void pppp(int a, int b) {
        System.out.println(a);
        ppppp(a + 1, b);
    }

    public static void ppp(int a, int b) {
        System.out.println(a);
        pppp(a + 1, b);
    }

    public static void pp(int a, int b) {
        System.out.println(a);
        ppp(a + 1, b);
    }

    public static void p(int a, int b) {
        System.out.println(a);
        pp(a + 1, b);
    }

    public static void printIncreasing(int a, int b) {
        if (a > b)
            return;
        System.out.println(a);
        printIncreasing(a + 1, b);
    }

    public static void printDecreasing(int a, int b) {
        if (a > b)
            return;

        printDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printIncreasingDecreasing(int a, int b) {
        if (a == b) {
            System.out.println(a);
            return;
        }
        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printOddEven(int a, int b) {
        if (a > b)
            return;

        if (a % 2 == 0)
            System.out.println(a);
        printOddEven(a + 1, b);
        if (a % 2 != 0)
            System.out.println(a);
    }

    public static void display(int[] arr, int idx) {
        if (idx == arr.length)
            return;

        System.out.println(arr[idx]);
        display(arr, idx + 1);
    }

    public static void displayReverse(int[] arr, int idx) {
        if (idx == arr.length)
            return;

        displayReverse(arr, idx + 1);
        System.out.println(arr[idx]);
    }

    public static int maximum(int[] arr, int idx) {
        if (idx == arr.length)
            return (int) -1e9;
        int maxSF = maximum(arr, idx + 1);
        return Math.max(maxSF, arr[idx]);

    }

    public static int minimum(int[] arr, int idx) {
        if (idx == arr.length)
            return (int) 1e9;
        int minSF = minimum(arr, idx + 1);
        return Math.min(minSF, arr[idx]);

    }

    public static boolean find(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return false;
        return arr[idx] == data || find(arr, idx + 1, data);
    }

    public static int firstIndex(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return -1;

        if (arr[idx] == data)
            return idx;
        return firstIndex(arr, idx + 1, data);

        // return arr[idx] == data ? idx : firstIndex(arr,idx +1 ,data);
    }

    public static int lastIndex(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return -1;
        int smallRes = lastIndex(arr, idx + 1, data);
        if (smallRes != -1)
            return smallRes;

        return arr[idx] == data ? idx : -1;
    }

    public static int[] allIndex(int[] arr, int idx, int data, int count) {
        if (idx == arr.length) {
            int[] base = new int[count];
            return base;
        }

        if (arr[idx] == data)
            count++;
        int[] ans = allIndex(arr, idx + 1, data, count);
        if (arr[idx] == data)
            ans[count - 1] = idx;

        return ans;
    }

    public static ArrayList<String> subSeq(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> recAns = subSeq(str, idx + 1);
        ArrayList<String> myAns = new ArrayList<>(recAns);
        for (String s : recAns)
            myAns.add(str.charAt(idx) + s);

        return myAns;
    }

    public static int subseq(String str, int idx, String ans, ArrayList<String> res) {
        if (idx == str.length()) {
            res.add(ans);
            return 1;
        }

        int count = 0;
        count += subseq(str, idx + 1, ans + str.charAt(idx), res);
        count += subseq(str, idx + 1, ans, res);

        return count;
    }

    // top to bottom
    public static ArrayList<String> mazePath_HVD(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        if (sr + 1 <= er) {
            ArrayList<String> Vertical = mazePath_HVD(sr + 1, sc, er, ec);
            for (String s : Vertical) {
                myAns.add("V" + s);
            }
        }

        if (sc + 1 <= ec && sr + 1 <= er) {
            ArrayList<String> Diagonal = mazePath_HVD(sr + 1, sc + 1, er, ec);
            for (String s : Diagonal) {
                myAns.add("D" + s);
            }

        }

        if (sc + 1 <= ec) {
            ArrayList<String> Horizontal = mazePath_HVD(sr, sc + 1, er, ec);
            for (String s : Horizontal) {
                myAns.add("H" + s);
            }
        }

        return myAns;
    }

    // bottom to top
    public static int mazePath_HVD(int sr, int sc, int er, int ec, ArrayList<String> ans, String psf) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        if (sr + 1 <= er)
            count += mazePath_HVD(sr + 1, sc, er, ec, ans, psf + "V");
        if (sr + 1 <= er && sc + 1 <= ec)
            count += mazePath_HVD(sr + 1, sc + 1, er, ec, ans, psf + "D");
        if (sc + 1 <= ec)
            count += mazePath_HVD(sr, sc + 1, er, ec, ans, psf + "H");

        return count;
    }

    public static ArrayList<String> mazePath_HVD_multi(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        for (int jump = 1; sr + jump <= er; jump++) {
            ArrayList<String> Vertical = mazePath_HVD_multi(sr + jump, sc, er, ec);
            for (String s : Vertical) {
                myAns.add("V" + jump + s);
            }
        }

        for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++) {
            ArrayList<String> Diagonal = mazePath_HVD_multi(sr + jump, sc + jump, er, ec);
            for (String s : Diagonal) {
                myAns.add("D" + jump + s);
            }
        }

        for (int jump = 1; sc + jump <= ec; jump++) {
            ArrayList<String> Horizontal = mazePath_HVD_multi(sr, sc + jump, er, ec);
            for (String s : Horizontal) {
                myAns.add("H" + jump + s);
            }
        }

        return myAns;
    }

    public static int mazePath_HVD_multi(int sr, int sc, int er, int ec, ArrayList<String> ans, String psf) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for (int jump = 1; sr + jump <= er; jump++)
            count += mazePath_HVD_multi(sr + 1, sc + 0, er, ec, ans, psf + "V" + jump);
        for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
            count += mazePath_HVD_multi(sr + 1, sc + 1, er, ec, ans, psf + "D" + jump);
        for (int jump = 1; sc + jump <= ec; jump++)
            count += mazePath_HVD_multi(sr + 0, sc + 1, er, ec, ans, psf + "H" + jump);

        return count;
    }

    public static int mazePath_HVD_2(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, ArrayList<String> ans,
            String psf) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];

            if (x >= 0 && y >= 0 && x <= er && y <= ec)
                count += mazePath_HVD_2(x, y, er, ec, dir, dirS, ans, psf + dirS[d]);
        }

        return count;
    }

    public static int floodFill(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS, ArrayList<String> ans,
            String psf) {
        int n = vis.length, m = vis[0].length;

        if (sr == n - 1 && sc == m - 1) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c])
                count += floodFill(r, c, vis, dir, dirS, ans, psf + dirS[d]);

        }

        vis[sr][sc] = false;
        return count;
    }

    public static int floodFill_multi(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS,
            ArrayList<String> ans, String psf) {
        int n = vis.length, m = vis[0].length;

        if (sr == n - 1 && sc == m - 1) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;

        for (int d = 0; d < dir.length; d++)
            for (int rad = 1; rad <= Math.max(n, m); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (!vis[r][c])
                        count += floodFill_multi(r, c, vis, dir, dirS, ans, psf + dirS[d] + rad);
                } else
                    break;
            }

        vis[sr][sc] = false;
        return count;
    }

    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
    // https://practice.geeksforgeeks.org/problems/special-matrix4201/1
    // https://www.geeksforgeeks.org/rat-in-a-maze-with-multiple-steps-jump-allowed/?ref=rp

    public static int floodFill(int sr, int sc, int[][] vis, String psf, ArrayList<String> res, int[][] dir,
            String[] dirS) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            res.add(psf);
            return 1;
        }

        vis[sr][sc] = 0; // block
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && vis[r][c] == 1) {
                count += floodFill(r, c, vis, psf + dirS[d], res, dir, dirS);
            }
        }

        vis[sr][sc] = 1; // unblock
        return count;
    }

    public static ArrayList<String> findPath(int[][] m, int n) {
        int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
        String[] dirS = { "D", "L", "R", "U" };

        ArrayList<String> res = new ArrayList<String>();
        if (m[0][0] == 0 || m[n - 1][n - 1] == 0)
            return res;

        int count = floodFill(0, 0, m, "", res, dir, dirS);
        return res;
    }

    public static class pair {
        String psf = "";
        int len = 0;

        pair(String psf, int len) {
            this.len = len;
            this.psf = psf;
        }
    }

    public static pair longestPath(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return new pair("", 0);
        }

        vis[sr][sc] = true; // blocked
        pair myAns = new pair("", -1);
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                if (!vis[r][c]) {
                    pair recAns = longestPath(r, c, vis, dir, dirS);
                    if (recAns.len != -1 && recAns.len + 1 > myAns.len) {
                        myAns.len = recAns.len + 1;
                        myAns.psf = dirS[d] + recAns.psf;
                    }
                }
            }
        }

        vis[sr][sc] = false; // unblocked
        return myAns;
    }

    public static pair shortestPath(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return new pair("", 0);
        }

        vis[sr][sc] = true; // blocked
        pair myAns = new pair("", (int)1e9);
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                if (!vis[r][c]) {
                    pair recAns = shortestPath(r, c, vis, dir, dirS);
                    if (recAns.len != (int)1e9 && recAns.len + 1 < myAns.len) {
                        myAns.len = recAns.len + 1;
                        myAns.psf = dirS[d] + recAns.psf;
                    }
                }
            }
        }

        vis[sr][sc] = false; // unblocked
        return myAns;
    }

    public static void longestShortestPath() {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        String[] dirS = { "D", "R", "U", "L" };

        boolean[][] vis = new boolean[3][3];
        // vis[1][1] = vis[1][2] = vis[2][1] = true;

        pair ans = longestPath(0, 0, vis, dir, dirS);
        System.out.println(ans.psf + " @ " + ans.len);
    }

    // =========================================================================

    public static void mazePath() {
        int sr = 0, sc = 0, er = 2, ec = 2;
        ArrayList<String> ans = new ArrayList<>();

        int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 }, { -1, 1 }, };
        String[] dirS = { "V", "H", "D", "E" };

        // System.out.println(mazePath_HVD_multi(sr, sc, er, ec));
        // System.out.println(mazePath_HVD_2(sr, sc, er, ec, dir, dirS, ans, ""));

        System.out.println(ans);
    }

    public static void floodFill() {
        int sr = 0, sc = 0, n = 3, m = 3;
        boolean[][] vis = new boolean[n][m];
        // int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1
        // }, { 0, -1 }, { -1, -1 } };
        // String[] dirS = { "U", "E", "L", "S", "D", "N", "R", "W" };

        int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 } };
        String[] dirS = { "V", "H", "D" };

        ArrayList<String> ans = new ArrayList<>();

        System.out.println(floodFill_multi(sr, sc, vis, dir, dirS, ans, ""));

        System.out.println(ans);
    }

    public static void main(String[] args) {
        longestShortestPath();
    }
}