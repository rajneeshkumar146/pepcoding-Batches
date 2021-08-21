import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;

public class l004Algos {
    public static class Edge {
        int v, w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    // O(2E) == O(E)
    public static void display(ArrayList<Edge>[] graph, int V) {
        for (int i = 0; i < V; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + "," + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static class pair {
        int src = 0;
        int par = 0;
        int w = 0;
        int wsf = 0;

        // prims
        pair(int src, int par, int w) {
            this(src, par, w, 0);
        }

        // dijikstra_Btr
        pair(int src, int wsf) {
            this(src, -1, 0, wsf);
        }

        // dijikstra
        pair(int src, int par, int w, int wsf) {
            this.src = src;
            this.par = par;
            this.w = w;
            this.wsf = wsf;
        }
    }

    public static void dijikstra(ArrayList<Edge>[] graph, int V, int src) {
        ArrayList<Edge>[] mygraph = new ArrayList[V];
        for (int i = 0; i < V; i++)
            graph[i] = new ArrayList<>();

        boolean[] vis = new boolean[V];
        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        pq.add(new pair(src, -1, 0, 0));
        while (pq.size() != 0) {
            pair p = pq.remove();
            if (vis[p.src])
                continue;

            if (p.par != -1)
                addEdge(mygraph, p.src, p.par, p.w);

            vis[p.src] = true;
            for (Edge e : graph[p.src]) {
                if (!vis[e.v])
                    pq.add(new pair(e.v, p.src, e.w, p.wsf + e.w));
            }
        }
    }

    public static void prims(ArrayList<Edge>[] graph, int V, int src) {
        ArrayList<Edge>[] mygraph = new ArrayList[V];
        for (int i = 0; i < V; i++)
            graph[i] = new ArrayList<>();

        boolean[] vis = new boolean[V];
        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.w - b.w;
        });

        pq.add(new pair(src, -1, 0, 0));
        while (pq.size() != 0) {
            pair p = pq.remove();
            if (vis[p.src])
                continue;

            if (p.par != -1)
                addEdge(mygraph, p.src, p.par, p.w);

            vis[p.src] = true;
            for (Edge e : graph[p.src]) {
                if (!vis[e.v])
                    pq.add(new pair(e.v, p.src, e.w, p.wsf + e.w));
            }
        }
    }

    public static void dijikstra_Btr(ArrayList<Edge>[] graph, int V, int src) {
        ArrayList<Edge>[] mygraph = new ArrayList[V];
        for (int i = 0; i < V; i++)
            graph[i] = new ArrayList<>();

        boolean[] vis = new boolean[V];

        int[] dis = new int[V];
        Arrays.fill(dis, (int) 1e9);

        int[] par = new int[V];
        Arrays.fill(par, -1);

        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        pq.add(new pair(src, 0));
        par[src] = -1;
        dis[src] = 0;

        while (pq.size() != 0) {
            pair p = pq.remove();
            if (vis[p.src]) // if (p.wsf >= dis[p.src])
                continue;

            if (p.par != -1)
                addEdge(mygraph, p.src, p.par, p.w);

            vis[p.src] = true;
            for (Edge e : graph[p.src]) {
                if (!vis[e.v] && e.w + p.wsf < dis[e.v]) {
                    dis[e.v] = e.w + p.wsf;
                    par[e.v] = p.src;
                    pq.add(new pair(e.v, p.wsf + e.w));
                }
            }
        }
    }

    // {{src,dest,weight}}
    public static void bellmanFord(int[][] edges, int N, int src) {
        int[] prev = new int[N];
        Arrays.fill(prev, (int) 1e9);
        prev[src] = 0;

        boolean isNegativeCycle = false;
        for (int edgeCount = 1; edgeCount <= N; edgeCount++) {
            int[] curr = new int[N];
            for (int i = 0; i < N; i++)
                curr[i] = prev[i];

            boolean isAnyUpdate = false;
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (prev[u] + w < curr[v]) {
                    curr[v] = prev[u] + w;
                    isAnyUpdate = true;
                }
            }

            if (edgeCount == N && isAnyUpdate)
                isNegativeCycle = true;

            if (!isAnyUpdate)
                break;
        }
    }

}