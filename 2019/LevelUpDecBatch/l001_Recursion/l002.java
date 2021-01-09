import java.util.ArrayList;

public class l002 {

    public static void main(String[] args) {
        // System.out.println(printMazePaths(0, 0, 2, 2, ""));
        floodfill();
    }

    public static int printMazePaths(int sr, int sc, int dr, int dc, String psf) {
        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        if (sc + 1 <= dc)
            count += printMazePaths(sr, sc + 1, dr, dc, psf + "h");
        if (sr + 1 <= dr && sc + 1 <= dc)
            count += printMazePaths(sr + 1, sc + 1, dr, dc, psf + "d");
        if (sr + 1 <= dr)
            count += printMazePaths(sr + 1, sc, dr, dc, psf + "v");

        return count;
    }

    public static int printMazePathsMultiJumps(int sr, int sc, int dr, int dc, String psf) {
        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        for (int j = 1; sc + j <= dc; j++)
            count += printMazePathsMultiJumps(sr, sc + j, dr, dc, psf + "h" + j);
        for (int j = 1; sr + j <= dr && sc + j <= dc; j++)
            count += printMazePathsMultiJumps(sr + j, sc + j, dr, dc, psf + "d" + j);
        for (int j = 1; sr + j <= dr; j++)
            count += printMazePathsMultiJumps(sr + j, sc, dr, dc, psf + "v" + j);

        return count;
    }

    public static int floodFill(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS,
            String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        int n = vis.length; // no of rows
        int m = vis[0].length; // no of col

        vis[sr][sc] = true; // mark

        int count = 0;
        for (int d = 0; d < dir.length; d++) { // call for all unvisited nbr's
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c])
                count += floodFill(r, c, er, ec, vis, dir, dirS, ans + dirS[d]);
        }

        vis[sr][sc] = false;

        return count;
    }

    public static int floodFillJump(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS,
            String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        int n = vis.length; // no of rows
        int m = vis[0].length; // no of col

        vis[sr][sc] = true; // mark

        int count = 0;
        for (int d = 0; d < dir.length; d++) { // call for all unvisited nbr's
            for (int rad = 1; rad <= Math.max(n, m); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (!vis[r][c])
                        count += floodFillJump(r, c, er, ec, vis, dir, dirS, ans + dirS[d] + rad);
                } else
                    break;
            }
        }

        vis[sr][sc] = false;

        return count;
    }

    public static class pair {
        int longestPathLength = 0;
        String longestPath = "";

        public pair(int longestPathLength, String longestPath) {
            this.longestPathLength = longestPathLength;
            this.longestPath = longestPath;
        }
    }

    public static pair longestPathRecu(int sr, int sc, int dr, int dc, boolean[][] vis, int[][] dir, String[] dirS) {
        if (sr == dr && sc == dc) {
            return new pair(0, "");
        }

        pair myAns = new pair(-1, "");
        vis[sr][sc] = true;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= dr && c <= dc && !vis[r][c]) {
                pair recAns = longestPathRecu(r, c, dr, dc, vis, dir, dirS);
                if (recAns.longestPathLength + 1 > myAns.longestPathLength) {
                    myAns.longestPathLength = recAns.longestPathLength + 1;
                    myAns.longestPath = dirS[d] + recAns.longestPath;
                }
            }
        }

        vis[sr][sc] = false;

        return myAns;
    }

    public static void floodfill() {
        boolean[][] vis = new boolean[3][3]; // vector<vector<bool>> vis(3,vector<bool>(3,false));
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { 0, 1 }, { 0, -1 }, { -1, 1 }, { 1, -1 } };
        String[] dirS = { "D", "U", "S", "N", "R", "L", "E", "W" };

        // int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 } };
        // String[] dirS = { "V", "H", "D" };
        System.out.println(floodFill(0, 0, 2, 2, vis, dir, dirS, ""));
    }

    // https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/
    // https://www.geeksforgeeks.org/count-number-ways-reach-destination-maze/
    // https://www.geeksforgeeks.org/rat-in-a-maze-with-multiple-steps-jump-allowed/?ref=rp
    // https://www.geeksforgeeks.org/rat-in-a-maze-problem-when-movement-in-all-possible-directions-is-allowed/?ref=rp
}

    public static void fun(ArrayList<Integer> arr) {
        System.out.println(arr.get(0));
        int val = arr.get(0) + 10;
        arr.set(0, val);
        arr.set(0, val - 10);
    }

    public static void fun1() {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        fun(arr);
        System.out.println(arr.get(0));

    }