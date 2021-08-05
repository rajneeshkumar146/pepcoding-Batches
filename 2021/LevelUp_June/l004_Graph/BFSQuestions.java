import java.util.*;

public class BFSQuestions {
    // 785
    public boolean bipartite(int[][] graph, int src, int[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        // No Color : -1 , Red : 0, Green : 1
        int color = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.removeFirst();
                if (vis[rvtx] != -1) {
                    if (color != vis[rvtx]) // conflict
                        return false;
                    continue;
                }

                vis[rvtx] = color;
                for (int v : graph[rvtx]) {
                    if (vis[v] == -1) {
                        que.addLast(v);
                    }
                }
            }

            color = (color + 1) % 2;
        }

        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] vis = new int[n]; // vector<int> vis(n,-1);
        Arrays.fill(vis, -1);

        for (int i = 0; i < n; i++) {
            if (vis[i] == -1 && !bipartite(graph, i, vis))
                return false;
        }

        return true;
    }

    // 1091
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
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

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

}