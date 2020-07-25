public class questions {

    // Leetcode 200
    public void numISlands_DFS(char[][] grid, int sr, int sc, int dir[][]) {
        grid[sr][sc] = '0';
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == '1')
                numISlands_DFS(grid, r, c, dir);
        }

    }

    public int numIslands(char[][] grid) {

        int count = 0;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    numISlands_DFS(grid, i, j, dir);
                }
            }
        }

        return count;
    }

    // Leetcode 695
    public int maxAreaOfIsland_DFS(int[][] grid, int sr, int sc, int dir[][]) {
        grid[sr][sc] = 0;
        int count = 0;
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1)
                count += maxAreaOfIsland_DFS(grid, r, c, dir);
        }

        return count + 1;

    }

    public int maxAreaOfIsland(int[][] grid) {

        int maxArea = 0;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, maxAreaOfIsland_DFS(grid, i, j, dir));
                }
            }
        }

        return maxArea;
    }

    // Leetcode 463
    public int islandPerimeter(int[][] grid) {
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        int totalOnes = 0;
        int nbr = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    totalOnes++;
                    for (int d = 0; d < 4; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1)
                            nbr++;
                    }
                }
            }
        }

        return totalOnes * 4 - nbr;
    }

    // Leetcode 130

    public void SurroundedRegion(char[][] grid, int sr, int sc, int[][] dir) {
        grid[sr][sc] = '$';
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 'O')
                SurroundedRegion(grid, r, c, dir);
        }

    }

    public void solve(char[][] board) {

        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1)
                    if (board[i][j] == 'O')
                        SurroundedRegion(board, i, j, dir);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '$')
                    board[i][j] = 'O';
            }
        }
    }

    



}