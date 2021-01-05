import java.util.*;

class questions {
    public static Scanner scn = new Scanner(System.in);
    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1#
    static ArrayList<String> res;

    public static int floodFill(int sr, int sc, int er, int ec, int[][] vis, int[][] dir, String[] dirS, String ans) {
        if (sr == er && sc == ec) {
            res.add(ans);
            return 1;
        }

        int n = vis.length; // no of rows
        int m = vis[0].length; // no of col

        vis[sr][sc] = 0; // mark

        int count = 0;
        for (int d = 0; d < dir.length; d++) { // call for all unvisited nbr's
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && vis[r][c] != 0)
                count += floodFill(r, c, er, ec, vis, dir, dirS, ans + dirS[d]);
        }

        vis[sr][sc] = 1;

        return count;
    }

    public static ArrayList<String> findPath(int[][] m, int n) {
        res = new ArrayList<>();
        if (n == 0 || m[0][0] == 0 || m[n - 1][n - 1] == 0)
            return res;
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        String[] dirS = { "U", "D", "L", "R" };
        floodFill(0, 0, n - 1, n - 1, m, dir, dirS, "");
        Collections.sort(res);
        return res;
    }

    public static void display(int[][] vis) {
        for (int[] m : vis) {
            for (int ele : m)
                System.out.print(ele + " ");
            System.out.println();
        }
    }

    public static boolean dfs(int sr, int sc, int dr, int dc, int[][] dir, int[][] mat, int[][] vis) {
        if (sr == dr && sc == dc) {
            vis[sr][sc] = 1;
            display(vis);
            vis[sr][sc] = 0;
            return true;
        }

        boolean res = false;
        vis[sr][sc] = 1;
        for (int rad = 1; rad <= mat[sr][sc]; rad++) {
            for (int d = 0; d < dir.length; d++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];
                if (r >= 0 && c >= 0 && r <= dr && c <= dc)
                    res = res || dfs(r, c, dr, dc, dir, mat, vis);
            }

        }
        vis[sr][sc] = 0;
        return res;
    }

    public static void main(String[] args) {
        int t = scn.nextInt();
        while (t-- > 0) {
            int n = scn.nextInt();
            int[][] mat = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = scn.nextInt();
                }
            }

            boolean ans = false;
            if (mat[0][0] != 0) {
                int[][] vis = new int[n][n];
                int[][] dir = { { 0, 1 }, { 1, 0 } };
                ans = dfs(0, 0, n - 1, n - 1, dir, mat, vis);
            }
            if (!ans)
                System.out.println(-1);
           
        }
    }


}