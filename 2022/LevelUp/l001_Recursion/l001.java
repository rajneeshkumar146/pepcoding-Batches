public class l001 {
    public static int mazePath(int sr, int sc, int er, int ec, String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        if (sc + 1 <= ec)
            count += mazePath(sr, sc + 1, er, ec, psf + "h"); // H
        if (sr + 1 <= er)
            count += mazePath(sr + 1, sc, er, ec, psf + "v"); // V
        if (sc + 1 <= ec && sr + 1 <= er)
            count += mazePath(sr + 1, sc + 1, er, ec, psf + "d"); // D

        return count;
    }

    public static int mazePath2(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                count += mazePath2(r, c, er, ec, dir, dirS, psf + dirS[d]);
            }

        }

        return count;
    }

    public static int mazePathJump(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                    count += mazePathJump(r, c, er, ec, dir, dirS, psf + dirS[d] + rad);
                } else
                    break;
            }
        }

        return count;
    }

    public static int floodFill(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS,
            String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }

        vis[sr][sc] = true;
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c]) {
                count += floodFill(r, c, er, ec, vis, dir, dirS, psf + dirS[d]);
            }

        }
        vis[sr][sc] = false;
        return count;
    }

    public static int floodFillJump(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS,
            String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }

        vis[sr][sc] = true;
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                    if (!vis[r][c])
                        count += floodFillJump(r, c, er, ec, vis, dir, dirS, psf + dirS[d] + rad);
                } else
                    break;
            }
        }

        vis[sr][sc] = false;
        return count;
    }

    // 63
    public int uniquePathsWithObstacles(int sr, int sc, int er, int ec, int[][] obstacleGrid, int[][] dir) {
        if (sr == er && sc == ec) {
            return 1;
        }

        obstacleGrid[sr][sc] = 1; // dosen't need to mark and unmark for this question
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec && obstacleGrid[r][c] == 0) {
                count += uniquePathsWithObstacles(r, c, er, ec, obstacleGrid, dir);
            }

        }
        obstacleGrid[sr][sc] = 0;
        return count;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1)
            return 0;
        int[][] dir = { { 0, 1 }, { 1, 0 } };

        return uniquePathsWithObstacles(0, 0, n - 1, m - 1, obstacleGrid, dir);
    }

    public static class pairSP {
        int len = (int) 1e9;
        String str = "";
    }

    // pair<int,string>
    public static pairSP shortesPath(int sr, int sc, int er, int ec, int[][] mat, int[][] dir, String[] dirS) {
        if(sr == er && sc == ec){
            pairSP base = new pairSP();
            base.len = 0;
            return base;
        }
        pairSP ans = new pairSP();
        mat[sr][sc] = 1; // block
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= sc && mat[r][c] == 0) {
                pairSP recAns = shortesPath(r, c, er, ec, mat, dir, dirS);
                if (recAns.len != (int)1e9 &&  recAns.len + 1 < ans.len) {
                    ans.len = recAns.len + 1;
                    ans.str = recAns.str + dirS[d];
                }
            }
        }

        mat[sr][sc] = 0; // unblock
        return ans;
    }

    public static void main(String[] args) {
        // System.out.println(mazePath(0, 0, 2, 2, ""));
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
        String[] dirS = { "h", "v", "d" };
        System.out.println(mazePath2(0, 0, 2, 2, dir, dirS, ""));
    }
}