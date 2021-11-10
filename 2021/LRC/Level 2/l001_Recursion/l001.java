import java.util.ArrayList;

public class l001 {

    public static int floodFill(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS,
            String psf) {

        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c])
                count += floodFill(r, c, er, ec, vis, dir, dirS, psf + dirS[d]);
        }
        vis[sr][sc] = false;
        return count;
    }

    public static boolean floodFill_singlePath(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir,
            String[] dirS, String psf) {

        if (sr == er && sc == ec) {
            System.out.println(psf + "->" + "endingPoint");
            return true;
        }

        int count = 0;
        vis[sr][sc] = true;
        boolean res = false;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c])
                res = res || floodFill_singlePath(r, c, er, ec, vis, dir, dirS, psf + " -> " + dirS[d]);
        }

        vis[sr][sc] = false;
        return res;
    }

    public static class floodFillPair {
        String lpath = "";
        String spath = "";
        int lLen = 0;
        int sLen = (int) 1e9;
        boolean isPathFound = false;
    }

    public static floodFillPair floodFill_LS_Path(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir,
            String[] dirS) {

        if (sr == er && sc == ec) {
            floodFillPair base = new floodFillPair();
            base.isPathFound = true;
            base.sLen = 0;
            return base;
        }

        int count = 0;
        vis[sr][sc] = true;
        floodFillPair ans = new floodFillPair();
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c]) {
                floodFillPair recRes = floodFill_LS_Path(r, c, er, ec, vis, dir, dirS);
                if (recRes.isPathFound) {
                    ans.isPathFound = true;
                    if (recRes.lLen + 1 > ans.lLen) {
                        ans.lLen = recRes.lLen + 1;
                        ans.lpath = dirS[d] + recRes.lpath;
                    }

                    if (recRes.sLen + 1 < ans.sLen) {
                        ans.sLen = recRes.sLen + 1;
                        ans.spath = dirS[d] + recRes.spath;
                    }
                }
            }
        }
        vis[sr][sc] = false;
        return ans;
    }

    public static void floodFill() {
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { -1, -1 }, { -1, 1 } };
        String[] dirS = { "d", "u", "r", "l", "S", "E", "N", "W" };

        int sr = 0, sc = 0, er = 2, ec = 2;
        boolean[][] vis = new boolean[er + 1][ec + 1];
        String psf = "";

        // System.out.println(floodFill(sr, sc, er, ec, vis, dir, dirS, psf));
        // System.out.println(floodFill_singlePath(sr, sc, er, ec, vis, dir, dirS,
        // "Starting Point"));

        floodFillPair ans = floodFill_LS_Path(sr, sc, er, ec, vis, dir, dirS);
        System.out.println("Longest Path: " + ans.lpath + " @ " + ans.lLen);
        System.out.println("shortest Path: " + ans.spath + " @ " + ans.sLen);
    }

    // flood Fill With Jump.=================================================

    public static int floodFill_jump(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS,
            String psf) {

        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;
        for (int rad = 1; rad <= Math.max(er, ec); rad++)
            for (int d = 0; d < dir.length; d++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c])
                    count += floodFill_jump(r, c, er, ec, vis, dir, dirS, psf + rad + dirS[d]);
            }
        vis[sr][sc] = false;
        return count;
    }

    public static int floodFill_jump_2(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS,
            String psf) {

        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;
        for (int d = 0; d < dir.length; d++)
            for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                    if (!vis[r][c])
                        count += floodFill_jump_2(r, c, er, ec, vis, dir, dirS, psf + rad + dirS[d]);
                } else
                    break;
            }
        vis[sr][sc] = false;
        return count;
    }

    public static int floodFill_jump_3(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS,
            String psf) {

        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0], c = sc + dir[d][1];
            int rad = 1;
            while (r >= 0 && c >= 0 && r <= er && c <= ec) {
                if (!vis[r][c])
                    count += floodFill_jump_3(r, c, er, ec, vis, dir, dirS, psf + rad + dirS[d]);
                r += dir[d][0];
                c += dir[d][1];
                rad++;
            }
        }
        vis[sr][sc] = false;
        return count;
    }

    public static void floodFill_jump() {
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { -1, -1 }, { -1, 1 } };
        String[] dirS = { "d", "u", "r", "l", "S", "E", "N", "W" };

        int sr = 0, sc = 0, er = 2, ec = 2;
        boolean[][] vis = new boolean[er + 1][ec + 1];
        String psf = "";

        System.out.println(floodFill_jump_3(sr, sc, er, ec, vis, dir, dirS, psf));
    }

    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
    // https://practice.geeksforgeeks.org/problems/special-matrix4201/1
    // https://www.geeksforgeeks.org/rat-in-a-maze-with-multiple-steps-jump-allowed/?ref=rp

    // Knight Tour
    public static boolean knightTour(int sr, int sc, int[][] board, int[][] dir, int count) {

        int n = board.length, m = board[0].length;
        board[sr][sc] = count;
        if (count == n * m) {
            return true;
        }

        boolean res = false;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && board[r][c] == 0)
                res = res || knightTour(r, c, board, dir, count + 1);

        }
        if (!res)
            board[sr][sc] = 0;
        return res;
    }

    public static void main(String[] args) {
        floodFill_jump();
    }
}