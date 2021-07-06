import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class l001 {
    public static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));
    }

    public static void display(ArrayList<Edge>[] graph, int N) {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.nbr + ", " + e.wt + ") ");
            }
            System.out.println();
        }

    }

    public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {
        ArrayList<Edge> list = graph[u];
        for (int i = 0; i < list.size(); i++) {
            Edge e = list.get(i);
            if (e.nbr == v)
                return i;
        }

        return -1;
    }

    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
        int i1 = findEdge(graph, u, v);
        int i2 = findEdge(graph, v, u);

        graph[u].remove(i1);
        graph[v].remove(i2);
    }

    public static void removeVtx(ArrayList<Edge>[] graph, int u) {
        ArrayList<Edge> list = graph[u];
        for (int i = list.size() - 1; i >= 0; i--) {
            Edge e = list.get(i);
            removeEdge(graph, e.src, e.nbr);
        }
    }

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest)
            return true;

        vis[src] = true;
        boolean res = false;
        for (Edge e : graph[src])
            if (!vis[e.nbr])
                res = res || hasPath(graph, e.nbr, dest, vis);

        return res;
    }

    public static int printAllPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis, String psf) {
        if (src == dest) {
            System.out.println(psf + dest);
            return 1;
        }
        int count = 0;
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.nbr])
                count += printAllPath(graph, e.nbr, dest, vis, psf + src);
        }

        vis[src] = false;
        return count;
    }

    public static void preOrder(ArrayList<Edge>[] graph, int src, boolean[] vis, int wsf, String psf) {
        System.out.println(src + " -> " + (psf + src) + " @ " + wsf);
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.nbr])
                preOrder(graph, e.nbr, vis, wsf + e.wt, psf + src);
        }

        vis[src] = false;
    }

    public static void postOrder(ArrayList<Edge>[] graph, int src, boolean[] vis, int wsf, String psf) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.nbr])
                postOrder(graph, e.nbr, vis, wsf + e.wt, psf + e.nbr);
        }

        System.out.println(src + " -> " + psf + " @ " + wsf);
        vis[src] = false;
    }

    public static void lightestPath(ArrayList<Edge>[] graph, int src, int dest) {

        // System.out.println("Lightest Path: " + x + " of weight: " + y);
    }

    public static class pathPair {
        String psf = "";
        int wsf = -1;
    }

    public static pathPair heaviestPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest) {
            pathPair base = new pathPair();
            base.psf += src;
            base.wsf = 0;

            return base;
        }

        vis[src] = true;
        pathPair myAns = new pathPair();
        for (Edge e : graph[src]) {
            if (!vis[e.nbr]) {
                pathPair recAns = heaviestPath(graph, e.nbr, dest, vis);
                if (recAns.wsf != -1 && recAns.wsf + e.wt > myAns.wsf) {
                    myAns.psf = src + recAns.psf;
                    myAns.wsf = recAns.wsf + e.wt;
                }
            }
        }
        vis[src] = false;
        return myAns;
    }

    public static void heaviestPath(ArrayList<Edge>[] graph, int src, int dest) {
        boolean[] vis = new boolean[graph.length];
        pathPair ans = heaviestPath(graph, src, dest, vis);

        System.out.println("Heaviest Path: " + ans.psf + " of weight: " + ans.wsf);
    }

    public static class ceilFloorPair {
        int ceil = (int) 1e9;
        int floor = -(int) 1e9;

    }

    public static void ceilAndFloor(ArrayList<Edge>[] graph, int src, int data, boolean[] vis, int wsf,
            ceilFloorPair pair) {
        if (wsf > data)
            pair.ceil = Math.min(pair.ceil, wsf);
        if (wsf < data)
            pair.floor = Math.max(pair.floor, wsf);

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.nbr]) {
                ceilAndFloor(graph, e.nbr, data, vis, wsf + e.wt, pair);
            }
        }
        vis[src] = false;
    }

    public static void ceilAndFloor(ArrayList<Edge>[] graph, int src, int data) {
        ceilFloorPair pair = new ceilFloorPair();
        boolean[] vis = new boolean[graph.length];
        ceilAndFloor(graph, src, data, vis, 0, pair);
    }

    // O(E)
    public static void dfs_GCC(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.nbr])
                dfs_GCC(graph, e.nbr, vis);
        }
    }

    // O(E + V);
    public static void GCC(ArrayList<Edge>[] graph) {
        int N = graph.length, componentCount = 0;
        boolean[] vis = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                dfs_GCC(graph, i, vis);
                componentCount++;
            }
        }
        System.out.println(componentCount);
    }

    public void dfs(char[][] grid, int[][] dir, int sr, int sc) {
        grid[sr][sc] = '0';
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == '1')
                dfs(grid, dir, r, c);
        }

    }

    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length, componentCount = 0;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, dir, i, j);
                    componentCount++;
                }
            }
        }
        return componentCount;
    }

    public int dfs(int[][] grid, int[][] dir, int sr, int sc) {

        grid[sr][sc] = 0;
        int size = 0;
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1)
                size += dfs(grid, dir, r, c);
        }

        return size + 1;

    }

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length, maxSize = 0;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int s = dfs(grid, dir, i, j);
                    maxSize = Math.max(maxSize, s);
                }
            }
        }
        return maxSize;
    }

    public static void hamintonianPathCycle(ArrayList<Edge>[] graph, int osrc, int src, int EdgeCount, boolean[] vis,
            String ans) {
        if (EdgeCount == graph.length - 1) {
            int idx = findEdge(graph, src, osrc);
            if (idx != -1) {
                System.out.println(ans + src + "*");
            } else {
                System.out.println(ans + src + ".");
            }
            return;
        }

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.nbr]) {
                hamintonianPathCycle(graph, osrc, e.nbr, EdgeCount + 1, vis, ans + src);
            }

        }

        vis[src] = false;

    }

    public static void hamintonianPathCycle(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        boolean[] vis = new boolean[N];
        hamintonianPathCycle(graph, src, src, 0, vis, "");
    }

    public static void BFS(ArrayList<Edge>[] graph, int src, int dest) {
        LinkedList<Integer> que = new LinkedList<>();
        int N = graph.length;
        boolean[] vis = new boolean[N];

        que.addLast(src);
        int level = 0;

        boolean isCyclePresent = false;
        int shortestPath = -1;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.removeFirst();

                // for cycle
                if (vis[rvtx]) {
                    isCyclePresent = true;
                    continue;
                }

                if (rvtx == dest) {
                    shortestPath = level;
                }

                vis[rvtx] = true;
                for (Edge e : graph[rvtx]) {
                    if (!vis[e.nbr]) {
                        que.addLast(e.nbr);
                    }
                }
            }
        }
    }

    public static boolean cycleDetection(ArrayList<Edge>[] graph, int src, boolean[] vis) {

        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                Integer rvtx = que.removeFirst();
                if (vis[rvtx])
                    return true;

                vis[rvtx] = true;
                for (Edge e : graph[rvtx]) {
                    if (!vis[e.nbr])
                        que.addLast(e.nbr);
                }
            }
        }

        return false;
    }

    public static void cycleDetection(ArrayList<Edge>[] graph) {
        int vtces = graph.length;
        boolean[] vis = new boolean[vtces];
        boolean res = false;
        for (int i = 0; i < vtces; i++) {
            if (!vis[i])
                res = res || cycleDetection(graph, i, vis);
        }

        System.out.println(res);
    }

    public static class BFS_Pair {
        int vtx = 0;
        String psf = "";
        int wsf = 0;

        public BFS_Pair(int vtx, String psf, int wsf) {
            this.vtx = vtx;
            this.psf = psf;
            this.wsf = wsf;
        }
    }

    public static void printBFSPath(ArrayList<Edge>[] graph, int src) {
        int vtces = graph.length;
        boolean[] vis = new boolean[vtces];
        LinkedList<BFS_Pair> que = new LinkedList<>();
        que.addLast(new BFS_Pair(src, src + "", 0));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                BFS_Pair rp = que.removeFirst();
                if (vis[rp.vtx])
                    continue;

                System.out.println(rp.vtx + " -> " + rp.psf + " @ " + rp.wsf);
                vis[rp.vtx] = true;
                for (Edge e : graph[rp.vtx]) {
                    if (!vis[e.nbr])
                        que.addLast(new BFS_Pair(e.nbr, rp.psf + e.nbr, rp.wsf + e.wt));
                }
            }
        }
    }

    public static int spreadInfection(ArrayList<Edge>[] graph, int infectedPerson, int NoOfDays) {
        LinkedList<Integer> que = new LinkedList<>();
        boolean[] vis = new boolean[graph.length];

        que.addLast(infectedPerson);

        int infectedCount = 0, day = 1;
        while (que.size() != 0) {
            int size = que.size();

            if (day > NoOfDays)
                break;

            while (size-- > 0) {
                int ip = que.removeFirst(); // infectedPerson
                if (vis[ip])
                    continue;

                vis[ip] = true;
                infectedCount++;

                for (Edge e : graph[ip]) {
                    if (!vis[e.nbr])
                        que.addLast(e.nbr);
                }

            }

            day++;

        }

        return infectedCount;
    }

    public static boolean bipartite(ArrayList<Edge>[] graph, int src, int[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        int color = 0; // 0 : red, 1 : green
        boolean cycle = false, isBipartite = true;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.removeFirst();
                if (vis[rvtx] != -1) { // cycle
                    cycle = true;
                    if (vis[rvtx] != color) { // conflict
                        isBipartite = false;
                        break;
                    }

                    continue; // not any kind oo conflict
                }

                vis[rvtx] = color;

                for (Edge e : graph[rvtx]) {
                    if (vis[e.nbr] == -1) {
                        que.addLast(e.nbr);
                    }
                }
            }

            color = (color + 1) % 2;
            if (!isBipartite)
                break;
        }

        if (cycle) {
            if (isBipartite)
                System.out.println("Even Length Cycle");
            else
                System.out.println("Odd Length Cycle");
        } else if (isBipartite && !cycle) {
            System.out.println("A-Cycle and Bipartite graph");
        }

        return isBipartite;
    }

    public static void bipartite(ArrayList<Edge>[] graph) {

        int N = graph.length;
        int[] vis = new int[N];
        Arrays.fill(vis, -1);

        boolean isBipartite = true;
        for (int i = 0; i < N; i++) {
            if (vis[i] == -1) {
                isBipartite = isBipartite && bipartite(graph, i, vis);
            }
        }

        System.out.println("Overall Graph is Bipartite: " + isBipartite);
    }

    public static void construction() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        // display(graph, N);
        boolean[] vis = new boolean[N];
        // System.out.println(printAllPath(graph, 0, 6, vis, ""));
        // preOrder(graph, 0, vis, 0, "");
        // heaviestPath(graph, 0, 6);
        printBFSPath(graph, 0);
    }

    public static void main(String[] args) {
        construction();
    }
}