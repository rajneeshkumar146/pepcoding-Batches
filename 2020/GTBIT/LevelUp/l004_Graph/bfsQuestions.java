import java.util.LinkedList;
import java.util.ArrayList;

public class bfsQuestions {

    public int orangesRotting(int[][] grid) {
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        int freshOranges = 0, time = 0, n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    freshOranges++;
                else if (grid[i][j] == 2) {
                    que.addLast(i * m + j);
                    grid[i][j] = 2; // mark them visited
                }
            }
        }

        if (freshOranges == 0)
            return 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rottedOrangeIDX = que.removeFirst();
                int sr = rottedOrangeIDX / m, sc = rottedOrangeIDX % m;
                for (int d = 0; d < 4; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                        if (--freshOranges == 0)
                            return time + 1;
                        grid[r][c] = 2;
                        que.addLast(r * m + c);
                    }
                }
            }
            time++;
        }

        return -1;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;

        int n = grid.length, m = grid[0].length, shortestPath = 1;
        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1)
            return -1;

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };

        que.addLast(0);
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;

                if (sr == n - 1 && sc == m - 1)
                    return shortestPath;

                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0) {
                        grid[r][c] = 1;
                        que.addLast(r * m + c);
                    }
                }
            }
            shortestPath++;
        }

        return -1;
    }

    public int[][] updateMatrix(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return grid;

        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    vis[i][j] = true;
                    que.addLast(i * m + j);
                }
            }
        }

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;

                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                        vis[r][c] = true;
                        grid[r][c] = grid[sr][sc] + 1;
                        que.addLast(r * m + c);
                    }
                }
            }
        }

        return grid;
    }

    public void wallsAndGates(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return;

        int n = grid.length, m = grid[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    que.addLast(i * m + j);
                }
            }
        }

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;

                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 2147483647) {
                        grid[r][c] = grid[sr][sc] + 1;
                        que.addLast(r * m + c);
                    }
                }
            }
        }
    }

    // 210
    public int[] findOrder(int N, int[][] arr) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        for (int[] a : arr) {
            graph[a[0]].add(a[1]);
        }

        LinkedList<Integer> que = new LinkedList<>();
        int[] ans = new int[N];
        int[] indegree = new int[N];
        int idx = N - 1;

        // O(E)
        for (int i = 0; i < N; i++) {
            for (Integer v : graph[i]) {
                indegree[v]++;
            }
        }

        // O(V)
        for (int i = 0; i < N; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        // O(E + V)
        while (que.size() != 0) {
            int rvtx = que.removeFirst();
            ans[idx--] = rvtx;

            for (Integer v : graph[rvtx]) {
                if (--indegree[v] == 0)
                    que.addLast(v);
            }
        }

        if (idx != -1)
            ans = new int[0];

        return ans;
    }

    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        int[][] indegree = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < 4; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && matrix[i][j] > matrix[r][c])
                        indegree[i][j]++;
                }

                if (indegree[i][j] == 0)
                    que.addLast(i * m + j);
            }

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.removeFirst();
                int i = rvtx / m, j = rvtx % m;

                for (int d = 0; d < 4; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && matrix[i][j] < matrix[r][c] && --indegree[r][c] == 0)
                        que.addLast(r * m + c);

                }
            }
            level++;
        }

        return level;
    }
}