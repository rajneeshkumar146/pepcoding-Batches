public class l005Backtracking {

    public static int mazePath(int sr, int sc, int er, int ec, String ans, int[][] dir, String[] dirS) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                count += mazePath(r, c, er, ec, ans + dirS[d], dir, dirS);
            }
        }

        return count;
    }

    // Mark
    // for all unvisited Nbr's
    // ---->call dfs for Nbr's
    // unMark

    public static int floodFill(int sr, int sc, int[][] board, String ans, int[][] dir, String[] dirS) {
        int n = board.length, m = board[0].length;
        if (sr == n - 1 && sc == m - 1) {
            System.out.println(ans);
            return 1;
        }

        board[sr][sc] = 1;

        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {
                if (board[r][c] == 0)
                    count += floodFill(r, c, board, ans + dirS[d], dir, dirS);
            }
        }

        board[sr][sc] = 0;
        return count;

    }

    // 0 -> empty cell, 1 -> blocked cell
    public static int floodFill_Jump(int sr, int sc, int[][] board, String ans, int[][] dir, String[] dirS,
            int Radius) {
        int n = board.length, m = board[0].length;
        if (sr == n - 1 && sc == m - 1) {
            System.out.println(ans);
            return 1;
        }

        board[sr][sc] = 1;

        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Radius; rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {
                    if (board[r][c] == 0)
                        count += floodFill_Jump(r, c, board, ans + rad + dirS[d], dir, dirS, Radius);
                } else
                    break;
            }
        }

        board[sr][sc] = 0;
        return count;
    }

    public static boolean floodFill_2(int sr, int sc, int[][] board, String ans, int[][] dir, String[] dirS) {
        int n = board.length, m = board[0].length;
        if (sr == n - 1 && sc == m - 1) {
            System.out.println(ans);
            return true;
        }

        board[sr][sc] = 1;

        boolean res = false;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {
                if (board[r][c] == 0)
                    res = res || floodFill_2(r, c, board, ans + dirS[d], dir, dirS);
            }
        }

        board[sr][sc] = 0;
        return res;
    }

    public static int floodFill_longestLen(int sr, int sc, int[][] board, int[][] dir) {
        int n = board.length, m = board[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return 0;
        }

        board[sr][sc] = 1;

        int longestLen = -1;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {
                if (board[r][c] == 0) {
                    int recAns = floodFill_longestLen(r, c, board, dir);
                    if (recAns != -1 && recAns + 1 > longestLen) {
                        longestLen = recAns + 1;
                    }
                }
            }
        }

        board[sr][sc] = 0;
        return longestLen;
    }

    public static int floodFill_shortestLen(int sr, int sc, int[][] board, int[][] dir) {
        int n = board.length, m = board[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return 0;
        }

        board[sr][sc] = 1;

        int shortestLen = (int) 1e9;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {
                if (board[r][c] == 0) {
                    int recAns = floodFill_shortestLen(r, c, board, dir);
                    if (recAns != (int) 1e9 && recAns + 1 < shortestLen) {
                        shortestLen = recAns + 1;
                    }
                }
            }
        }

        board[sr][sc] = 0;
        return shortestLen;
    }

    // Knight Tour
    public static boolean knightTour(int sr, int sc, int[][] board, int move, int[] dirX, int[] dirY) {
        board[sr][sc] = move; // block

        if (move == 63) {
            return true;
        }

        boolean res = false;
        for (int d = 0; d < 8; d++) {
            int r = sr + dirX[d];
            int c = sc + dirY[d];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == -1) {
                res = res || knightTour(r, c, board, move + 1, dirX, dirY);
                if (res)
                    return res;

            }
        }

        board[sr][sc] = -1; // free
        return res;
    }

    public static void knightTour() {
        int n = 8;
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = -1;

        int[] dirX = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int[] dirY = { 1, 2, 2, 1, -1, -2, -2, -1 };
        knightTour(0, 0, board, 0, dirX, dirY);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }

    }

    public static void main(String[] args) {
        // int[][] dir4 = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        // String[] dir4S = { "t", "r", "d", "l" };

        // int[][] board = { { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 } };

        // int[][] dir8 = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, -1 }, { 1,
        // -1 }, { 1, 1 }, { -1, 1 } };
        // String[] dir8S = { "u", "r", "d", "l", "n", "w", "s", "e" };

        // int n = 3, m = 3;
        // System.out.println(floodFill_2(0, 0, board, "", dir4, dir4S));
        // System.out.println(floodFill_longestLen(0, 0, board, dir4));

        knightTour();
    }

}