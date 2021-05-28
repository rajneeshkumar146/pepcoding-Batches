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

    // 695
    public int dfs_Area(int[][] grid, int r, int c, int[][] dir) {

        grid[r][c] = 0;
        int size = 0;
        for (int d = 0; d < 4; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1)
                size += dfs_Area(grid, x, y, dir);
        }

        return size + 1;
    }

    public int maxAreaOfIsland(int[][] grid) {

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        int maxSize = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxSize = Math.max(maxSize, dfs_Area(grid, i, j, dir));
                }
            }
        }

        return maxSize;
    }
}