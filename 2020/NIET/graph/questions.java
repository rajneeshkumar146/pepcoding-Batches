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

    // 785
    public boolean isBipartite(int[][] graph, int src, int[] vis) {
        LinkedList<Integer> que = new LinkedList<>();

        que.addLast(src);
        int color = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {

                int rvtx = que.removeFirst();
                if (vis[rvtx] != -1) {
                    if (vis[rvtx] != color)
                        return false;
                    continue;
                }

                vis[rvtx] = color;
                for (int e : graph[rvtx])
                    if (vis[e] == -1)
                        que.addLast(e);

            }

            color = (color + 1) % 2;
        }

        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int[] vis = new int[graph.length];
        Arrays.fill(vis, -1);

        for (int i = 0; i < vis.length; i++) {
            if (vis[i] == -1) {
                if (!isBipartite(graph, i, vis))
                    return false;
            }
        }

        return true;
    }

    // 994
    public int orangesRotting(int[][] grid) {
        LinkedList<Integer> que = new LinkedList<>();
        int n = grid.length, m = grid[0].length;

        int freshOranges = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (grid[i][j] == 1)
                    freshOranges++;
                else if (grid[i][j] == 2)
                    que.addLast(i * m + j);

        if (freshOranges == 0)
            return 0;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        int time = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int r = idx / m;
                int c = idx % m;

                for (int d = 0; d < 4; d++) {
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        freshOranges--;
                        if (freshOranges == 0)
                            return time + 1;
                        que.addLast(x * m + y);
                    }
                }

            }

            time++;
        }

        return -1;
    }
}