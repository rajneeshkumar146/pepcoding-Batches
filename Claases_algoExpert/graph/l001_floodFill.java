import java.util.*;

public class l001_floodFill {

    private static boolean validateLocation(int r, int c, int n, int m) {
        return r >= 0 && c >= 0 && r < n && c < m;
    }

    public static int floodFill(int sr, int sc, boolean[][] vis, int[][] dir, String[] disS, String psf) {
        int count = 0, n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            System.out.println(psf);
            return 1;
        }

        vis[sr][sc] = true;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (validateLocation(r, c, n, m) && vis[r][c] == false) {
                count += floodFill(r, c, vis, dir, disS, disS[d] + psf);
            }
        }

        vis[sr][sc] = false;
        return count;
    }

    public static int floodFill_BFS(int sr, int sc, boolean[][] vis, int[][] dir, String[] disS, String psf) {
        int count = 0, n = vis.length, m = vis[0].length;

        // {r,c}
        LinkedList<int[]> que = new LinkedList<>(); // addLast, removeFirst
        que.addLast(new int[] { 0, 0 });
        vis[0][0] = true;
        int level = 0;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int[] loc = que.removeFirst();

                if (loc[0] == n - 1 && loc[1] == m - 1)
                    return level;
                for (int[] d : dir) {
                    int r = loc[0] + d[0];
                    int c = loc[1] + d[1];

                    if (validateLocation(r, c, n, m) && vis[r][c] == false) {
                        vis[r][c] = true;
                        que.addLast(new int[] { r, c });
                    }
                }
            }
            level++;
        }

        return -1;
    }

    public static int floodFill_jump(int sr, int sc, boolean[][] vis, int[][] dir, String[] disS, String psf) {
        int count = 0, n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            System.out.println(psf);
            return 1;
        }

        vis[sr][sc] = true;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(n, m); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (validateLocation(r, c, n, m)) {
                    if (vis[r][c] == false)
                        count += floodFill(r, c, vis, dir, disS, disS[d] + psf);
                } else
                    break;
            }
        }

        vis[sr][sc] = false;
        return count;
    }

    public static void floodFill() {
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 } };
        String[] disS = { "d", "u", "r", "l", "n", "s", "e", "w" };

        int sr = 0, sc = 0;
        boolean[][] vis = new boolean[3][3];
    }

    // 63
    public int floodFill(int sr, int sc, int[][] vis, int[][] dir, int[][] dp) {
        int count = 0, n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != -1)
            return dp[sr][sc];

        vis[sr][sc] = 1;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (validateLocation(r, c, n, m) && vis[r][c] == 0) {
                count += floodFill(r, c, vis, dir, dp);
            }
        }

        vis[sr][sc] = 0;
        return dp[sr][sc] = count;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dir = { { 1, 0 }, { 0, 1 } };
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1)
            return 0;

        int[][] dp = new int[n][m];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return floodFill(0, 0, obstacleGrid, dir, dp);
    }

}
