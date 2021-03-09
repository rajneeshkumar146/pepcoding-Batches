import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

public class questions {

    // 994
    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
        int time = 0, freshOranges = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2)
                    que.addLast(i * m + j);
                else if (grid[i][j] == 1)
                    freshOranges++;
            }
        }

        if (freshOranges == 0)
            return 0;
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
                        freshOranges--;
                        grid[x][y] = 2;
                        que.addLast(x * m + y);
                        if (freshOranges == 0)
                            return time + 1;
                    }
                }
            }

            time++;
        }

        return -1;
    }

    int[] par;

    int findPar(int u) {
        return par[u] == -1 ? u : (par[u] = findPar(par[u]));
    }

    public int[] findRedundantConnection(int[][] edges) {
        int N = edges.length + 1;
        par = new int[N];
        Arrays.fill(par, -1);

        for (int[] edge : edges) {
            int p1 = findPar(edge[0]);
            int p2 = findPar(edge[1]);

            if (p1 != p2)
                par[p1] = p2;
            else
                return edge;

        }

        return new int[0];

    }

    public boolean isSimilar(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i) && ++count > 2)
                return false;
        }

        return true;

    }

    public int numSimilarGroups(String[] strs) {
        int count = strs.length;
        int n = strs.length;

        par = new int[n];
        for (int i = 0; i < n; i++)
            par[i] = i;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    int p1 = findPar(i);
                    int p2 = findPar(j);

                    if (p1 != p2) {
                        par[p1] = p2;
                        count--;
                    }
                }

            }

        }

        return count;
    }

    public int minCostToSupplyWater(int N, ArrayList<int[]> Edges) {
        par = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            par[i] = i;
        }

        int cost = 0;
        for (int[] edge : Edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2) {
                par[p1] = p2;
                cost += w;
            }
        }
        return cost;
    }

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<int[]> PIPES = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            PIPES.add(new int[] { 0, i + 1, wells[i] });
        }

        for (int[] p : pipes)
            PIPES.add(p);

        Collections.sort(PIPES, (a, b) -> {
            return a[2] - b[2];
        });

        return minCostToSupplyWater(n, PIPES);
    }

    public int networkDelayTime(int[][] times, int n, int k) {

        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++)
            graph[i] = new ArrayList<>();

        // {u -> {v,w} }
        for (int[] ar : times) {
            graph[ar[0]].add(new int[] { ar[1], ar[2] });
        }

        int[] dis = new int[n + 1];
        Arrays.fill(dis, (int) 1e9);
        boolean[] vis = new boolean[n + 1];

        // {vtx,wsf}
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        que.add(new int[] { k, 0 });
        dis[k] = 0;
        int NoOfEdges = 0;
        int maxvalue = 0;
        while (que.size() != 0) {
            int[] p = que.remove();
            int vtx = p[0], wsf = p[1];

            if (vis[vtx])
                continue;
            if (vtx != k)
                NoOfEdges++;

            maxvalue = Math.max(maxvalue, wsf);

            vis[vtx] = true;
            for (int[] e : graph[vtx]) {
                if (!vis[e[0]] && e[1] + wsf < dis[e[0]]) {
                    dis[e[0]] = e[1] + wsf;
                    que.add(new int[] { e[0], e[1] + wsf });
                }
            }
        }

        if (NoOfEdges != n - 1)
            return -1;

        return maxvalue;

    }

    

}