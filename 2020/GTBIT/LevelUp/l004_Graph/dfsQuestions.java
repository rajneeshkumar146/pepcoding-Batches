public class dfsQuestions {

    public void dfs_NumIsland(char[][] grid, int i, int j, int[][] dir) {
        grid[i][j] = '2';
        int n = grid.length, m = grid[0].length;
        for (int d = 0; d < 4; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1')
                dfs_NumIsland(grid, r, c, dir);
        }

    }

    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length, componentCount = 0;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dfs_NumIsland(grid, i, j, dir);
                    componentCount++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '2') {
                    grid[i][j] = '1';
                }
            }
        }

        return componentCount;
    }

    public int dfs_areaIsland(int[][] grid, int[][] dir, int i, int j) {
        grid[i][j] = 0;
        int n = grid.length, m = grid[0].length;

        int size = 0;
        for (int d = 0; d < 4; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                size += dfs_areaIsland(grid, dir, r, c);
        }

        return size + 1;

    }

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length, maxSize = 0;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int s = dfs_areaIsland(grid, dir, i, j);
                    maxSize = Math.max(maxSize, s);
                }
            }
        }
        return maxSize;
    }

    // 463
    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dir = { { 1, 0 }, { 0, 1 } };

        int onceCount = 0, nbrCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    for (int d = 0; d < 2; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r < n && c < m && grid[r][c] == 1)
                            nbrCount++;
                    }
                }
            }
        }
        return 4 * onceCount - 2 * nbrCount;
    }

    // 130
    public void surrounded_DFS(char[][] grid, int i, int j, int[][] dir) {
        grid[i][j] = '$';
        int n = grid.length, m = grid[0].length;
        for (int d = 0; d < 4; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 'O')
                surrounded_DFS(grid, r, c, dir);
        }

    }

    public void surroiunded(char[][] grid) {
        int n = grid.length, m = grid[0].length;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i == 0 || j == 0 || i == n - 1 || j == m - 1) && grid[i][j] == 'O') {
                    surrounded_DFS(grid, i, j, dir);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '$')
                    grid[i][j] = 'O';
                else
                    grid[i][j] = 'X';
            }
        }
    }

    // https://www.hackerrank.com/challenges/journey-to-the-moon/problem
}