import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class AP {

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

    private static int[] low, disc, AP;
    private static int time = 0, rootCalls;
    private static boolean[] vis, APoints;

    public static void dfs(int src, int par, ArrayList<Edge>[] graph) {
        disc[src] = low[src] = time++;
        vis[src] = true;

        for (Edge e : graph[src]) {
            if (!vis[e.v]) {

                if (par == -1)
                    rootCalls++;

                dfs(e.v, src, graph);
                
                //Articulation Points
                if (disc[src] <= low[e.v]) {
                    AP[src]++;
                    APoints[src] = true;
                }
                //Articulation Bridges
                if (disc[src] < low[e.v]) {
                    System.out.println("Articulation Edge : (" + src + "," + e.v + ") ");
                }

                low[src] = Math.min(low[src], low[e.v]);

            } else if (e.v != par)
                low[src] = Math.min(low[src], disc[e.v]);
        }
    }

    public static void APB(int N, ArrayList<Edge>[] graph) {
        low = new int[N];
        disc = new int[N];
        AP = new int[N];
        vis = new boolean[N];
        APoints = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                AP[i] = -1;
                dfs(i, -1, graph);
                if (rootCalls == 1)
                    APoints[i] = false;
            }
        }

    }
}