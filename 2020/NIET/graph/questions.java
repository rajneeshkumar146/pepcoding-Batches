public class questions {
    public void dfs_numIsland(char[][] grid, int r, int c, int[][] dir) {
        grid[r][c] = '0';
        for (int d = 0; d < dir.length; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == '1') {
                dfs_numIsland(grid, x, y, dir);
            }
        }
    }

    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length, components = 0;
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    components++;
                    dfs_numIsland(grid, i, j, dir);
                }
            }
        }

        return components;
    }
}