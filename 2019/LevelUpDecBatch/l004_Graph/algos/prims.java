import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class prims {

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

    public class primsPair {
        int vtx = 0;
        int par = 0;
        int wt = 0;

        public primsPair(int vtx, int par, int wt) {
            this.vtx = vtx;
            this.par = par;
            this.wt = wt;
        }
    }

    // basic
    public static void primsAlgo_01(int src, int N, boolean[] vis, ArrayList<Edge>[] graph) {
        ArrayList<Edge>[] MST = new ArrayList[N];
        for (int i = 0; i < N; i++)
            MST[i] = new ArrayList<>();

        PriorityQueue<primsPair> que = new PriorityQueue<>((a, b) -> {
            return a.wt - b.wt;
        });

        que.add(new primsPair(src, -1, 0));
        int NumberOfEdges = 0;

        // while (que.size() != 0) { // for disconnected graph and more generic way
        // while (que.size() != 0 && NumberOfEdges < N - 1) { // for disconnected graph
        while (NumberOfEdges < N - 1) { // when you know graph is connected.
            primsPair p = que.remove();
            if (vis[p.vtx])
                continue; // cycle.

            if (p.par != -1) {
                addEdge(MST, p.vtx, p.par, p.wt);
                NumberOfEdges++;
            }

            vis[p.vtx] = true;
            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v]) {
                    que.add(new primsPair(e.v, p.vtx, e.w));
                }
            }
        }
    }

    public static void primsAlgo_02(int src, int N, boolean[] vis, int[] dis, int[] par, ArrayList<Edge>[] graph) {
        ArrayList<Edge>[] MST = new ArrayList[N];
        for (int i = 0; i < N; i++)
            MST[i] = new ArrayList<>();

        PriorityQueue<primsPair> que = new PriorityQueue<>((a, b) -> {
            return a.wt - b.wt;
        });

        que.add(new primsPair(src, -1, 0));

        int NumberOfEdges = 0;
        while (NumberOfEdges < N - 1) {
            primsPair p = que.remove();
            if (vis[p.vtx])
                continue; // cycle.

            if (p.par != -1) {
                addEdge(MST, p.vtx, p.par, p.wt);
                NumberOfEdges++;
            }

            vis[p.vtx] = true;
            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v] && e.w < dis[e.v]) {
                    dis[e.v] = e.w;
                    par[e.v] = p.vtx;
                    que.add(new primsPair(e.v, p.vtx, e.w));
                }
            }
        }

        display(N, MST);
    }

    public static void prims(int N, ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[N];
        int[] dis = new int[N];
        int[] par = new int[N];

        Arrays.fill(dis, (int) 1e9);   // vector<int> dis(N,1e9);
        Arrays.fill(par, -1);

        for (int i = 0; i < N; i++) {
            if (!vis[i])
                primsAlgo_02(i, N, vis, dis, par, graph);
        }
    }

    public static void main(String[] args) {

    }

}