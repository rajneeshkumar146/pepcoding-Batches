import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class dijikistraAlgo {
    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display(int N, ArrayList<Edge>[] graph) {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public class dijikstraPair {
        int vtx = 0;
        int wsf = 0;

        // if we wangt to create graph
        // int par = 0;
        // int wt = 0;

        public dijikstraPair(int vtx, int wsf) {
            this.vtx = vtx;
            this.wsf = wsf;
        }
    }

    // basic
    public static void dijikstra(int src, int N, ArrayList<Edge>[] graph) {
        PriorityQueue<dijikstraPair> que = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        que.add(new dijikstraPair(src, 0));
        int NumberOfEdges = 0;

        boolean[] vis = new boolean[N];
        while (NumberOfEdges < N - 1) { // when you know graph is connected.
            dijikstraPair p = que.remove();
            if (vis[p.vtx])
                continue; // cycle.

            if (p.vtx != src)
                NumberOfEdges++;

            vis[p.vtx] = true;
            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v]) {
                    que.add(new dijikstraPair(e.v, p.wsf + e.w));
                }
            }
        }
    }

    // better
    public static void dijikstra_01(int src, int N, ArrayList<Edge>[] graph) {
        PriorityQueue<dijikstraPair> que = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        que.add(new dijikstraPair(src, 0));
        int NumberOfEdges = 0;

        boolean[] vis = new boolean[N];
        int[] dis = new int[N];
        int[] par = new int[N];

        Arrays.fill(dis, (int) 1e8);
        Arrays.fill(par, -1);

        while (NumberOfEdges < N - 1) { // when you know graph is connected.
            dijikstraPair p = que.remove();
            if (vis[p.vtx])
                continue; // cycle.

            if (par[p.vtx] != -1)
                NumberOfEdges++;

            vis[p.vtx] = true;
            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v] && e.w + p.wsf < dis[e.v]) {
                    dis[e.v] = e.w + p.wsf;
                    par[e.v] = p.vtx;
                    que.add(new dijikstraPair(e.v, p.wsf + e.w));
                }
            }
        }
    }

    // Bellman Ford.
    // {{u,v,w}.....}
    public static void bellmanFord(int src, int[][] edges, int N) {
        int[] dis = new int[N];
        Arrays.fill(dis, (int) 1e9);

        dis[src] = 0;
        boolean isNegativeCycle = false;

        for (int EdgeCount = 1; EdgeCount <= N; EdgeCount++) {
            int[] ndis = new int[N];
            for (int i = 0; i < N; i++)
                ndis[i] = dis[i];

            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (dis[u] != (int) 1e9 && dis[u] + w < ndis[v]) {
                    if (EdgeCount == N) {
                        isNegativeCycle = true;
                        break;
                    }

                    ndis[v] = dis[u] + w;
                }
            }

            dis = ndis;
        }

        
    }
}