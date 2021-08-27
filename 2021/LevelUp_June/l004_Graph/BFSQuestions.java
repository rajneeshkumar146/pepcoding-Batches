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

    // 542
    public int[][] updateMatrix(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return grid;

        int n = grid.length, m = grid[0].length;

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    que.push(i * m + j);
                    vis[i][j] = true;
                }
            }
        }

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;

                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

                    if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                        grid[r][c] = grid[sr][sc] + 1;
                        vis[r][c] = true;
                        que.addLast(r * m + c);
                    }

                }
            }
        }

        return grid;
    }

    // 1020
    // 286

    // 743
    public int dijikstra(ArrayList<int[]>[] graph, int n, int SRC) {
        int[] dis = new int[n + 1];
        Arrays.fill(dis, (int) 1e8);
        boolean[] vis = new boolean[n + 1];

        // {vtx,wsf}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        pq.add(new int[] { SRC, 0 });

        while (pq.size() != 0) {
            int[] p = pq.remove();
            int vtx = p[0], wsf = p[1];

            if (vis[vtx])
                continue;

            vis[vtx] = true;
            dis[vtx] = wsf;

            for (int[] e : graph[vtx]) {
                int v = e[0], w = e[1];
                if (!vis[v])
                    pq.add(new int[] { v, wsf + w });
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dis[i] == (int) 1e8)
                return -1;
            maxTime = Math.max(maxTime, dis[i]);
        }

        return maxTime;
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        // {vtx,w}
        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();
        for (int[] time : times) {
            graph[time[0]].add(new int[] { time[1], time[2] });
        }

        return dijikstra(graph, n, k);
    }

    // 490
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {

        int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
        LinkedList<Integer> que = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        que.add(sr * m + sc);
        vis[sr][sc] = true;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst(), i = idx / m, j = idx % m;
                for (int[] d : dir) {

                    int r = i, c = j;
                    while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
                        r += d[0];
                        c += d[1];
                    }

                    r -= d[0];
                    c -= d[1];

                    if (vis[r][c])
                        continue;

                    vis[r][c] = true;
                    que.addLast(r * m + c);
                    if (r == er && c == ec)
                        return true;
                }

            }
        }

        return false;
    }

    public static class pair implements Comparable<pair> {
        int r = 0, c = 0, steps = 0;

        pair(int r, int c, int steps) {
            this.r = r;
            this.c = c;
            this.steps = steps;
        }

        @Override
        public int compareTo(pair o) {
            return this.steps - o.steps;
        }
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        int[][] dis = new int[n][m];
        for (int[] d : dis)
            Arrays.fill(d, (int) 1e8);

        PriorityQueue<pair> que = new PriorityQueue<>();
        que.add(new pair(sr, sc, 0));
        dis[sr][sc] = 0;

        while (que.size() != 0) {
            pair p = que.remove();
            if (p.r == er && p.c == ec)
                return p.steps;
            for (int[] d : dir) {
                int r = p.r, c = p.c, steps = p.steps;
                while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
                    r += d[0];
                    c += d[1];
                    steps++;
                }

                r -= d[0];
                c -= d[1];
                steps--;

                if (steps >= dis[r][c])
                    continue;

                que.add(new pair(r, c, steps));
                dis[r][c] = steps;
            }
        }

        return -1;
    }

    // 499
    public static class pair implements Comparable<pair> {
        int r = 0, c = 0, steps = 0;
        String psf = "";

        pair(int r, int c, int steps, String psf) {
            this.r = r;
            this.c = c;
            this.steps = steps;
            this.psf = psf;
        }

        @Override
        public int compareTo(pair o) {
            if (this.steps != o.steps)
                return this.steps - o.steps;
            else
                return this.psf.compareTo(o.psf);
        }
    }

    public String findShortestWay(int[][] maze, int[] start, int[] destination) {
        int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        String[] dirS = { "d", "u", "r", "l" };
        pair[][] dis = new pair[n][m];
        for (int i = 0; i < n * m; i++)
            dis[i / m][i % m] = new pair(i / m, i % m, (int) 1e8, "");

        PriorityQueue<pair> que = new PriorityQueue<>();
        pair src = new pair(sr, sc, 0, "");

        que.add(src);
        dis[sr][sc] = src;

        while (que.size() != 0) {
            pair p = que.remove();
            for (int i = 0; i < 4; i++) {
                int[] d = dir[i];

                int r = p.r, c = p.c, steps = p.steps;
                while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0 && !(r == er && c == ec)) { // !(r == er &&
                                                                                                         // c == ec) ==
                                                                                                         // (r != er ||
                                                                                                         // c != ec)
                    r += d[0];
                    c += d[1];
                    steps++;
                }

                if (!(r == er && c == ec)) { // why it is necc. ???
                    r -= d[0];
                    c -= d[1];
                    steps--;
                }

                pair np = new pair(r, c, steps, p.psf + dirS[i]);
                if (steps > dis[r][c].steps || dis[r][c].compareTo(np) <= 0) // why this kind of check ???
                    continue;

                que.add(np);
                dis[r][c] = np;
            }
        }

        pair ans = dis[er][ec];
        return ans.steps != (int) 1e8 ? ans.psf : "impossible";
    }

}