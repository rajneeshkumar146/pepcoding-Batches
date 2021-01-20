public class l003 {

    public static int floodFill(int sr, int sc, int er, int ec, boolean[][] vis, int[][] dir, String[] dirS,
            String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        vis[sr][sc] = true;

        int n = vis.length;
        int m = vis[0].length;

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            // check r and c with in boundary
            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                count += floodFill(r, c, er, ec, vis, dir, dirS, ans + dirS[d]);
            }
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

        vis[sr][sc] = true;

        int n = vis.length;
        int m = vis[0].length;

        int count = 0;
        for (int rad = 1; rad <= Math.max(n, m); rad++)
            for (int d = 0; d < dir.length; d++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                // check r and c with in boundary
                if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                    count += floodFillJump(r, c, er, ec, vis, dir, dirS, ans + dirS[d] + rad);
                }
            }

        vis[sr][sc] = false;
        return count;
    }

    public static void displayBoard(int[][] chess) {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    // tnc : total no cells
    public static int knightMove(int sr, int sc, int move, int tnc, int[][] vis, int[][] dir) {
        if (move == tnc) {
            vis[sr][sc] = move;
            displayBoard(vis);
            vis[sr][sc] = 0;
            return 1;
        }

        vis[sr][sc] = move;

        int n = vis.length;
        int m = vis[0].length;

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            // check r and c with in boundary
            if (r >= 0 && c >= 0 && r < n && c < m && vis[r][c] == 0) {
                count += knightMove(r, c, move + 1, tnc, vis, dir);
            }
        }

        vis[sr][sc] = 0;
        return count;
    }

    public static void KnightTour() {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int sr = scn.nextInt();
        int sc = scn.nextInt();
        int[][] dir = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };
        int[][] vis = new int[n][n];
        // for(int[] d : vis) Arrays.fill(d,-1);

        knightMove(sr, sc, 1, n * n, vis, dir);

    }

    public static void main(String[] args) {
        // int[][] dir = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
        int[][] dir = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };

        String[] dirS = { "L", "D", "R", "U" };

        int n = 3, m = 3;
        boolean[][] vis = new boolean[n][m];
        System.out.println(floodFillJump(0, 0, n - 1, m - 1, vis, dir, dirS, ""));
    }

    // https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/
    // https://www.geeksforgeeks.org/count-number-ways-reach-destination-maze/
    // https://www.geeksforgeeks.org/rat-in-a-maze-with-multiple-steps-jump-allowed/?ref=rp
    // https://www.geeksforgeeks.org/rat-in-a-maze-problem-when-movement-in-all-possible-directions-is-allowed/?ref=rp
}
