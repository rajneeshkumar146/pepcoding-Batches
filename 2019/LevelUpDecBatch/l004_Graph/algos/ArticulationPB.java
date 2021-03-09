import java.util.ArrayList;
import java.util.Arrays;

public class ArticulationPB {
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

    // ====================================================================

    static int[] dis, low, AP;
    static boolean[] vis, isAP;
    static int time = 0, noOfCallsFromRoot = 0;

    public static void dfs(int src, int par, int N, ArrayList<Integer>[] graph) {
        dis[src] = low[src] = time++;
        vis[src] = true;

        for (Integer nbr : graph[src]) {
            if (!vis[nbr]) {

                if (par == -1)
                    noOfCallsFromRoot++;

                dfs(nbr, src, N, graph);

                if (dis[src] <= low[nbr]) { // why not this? : low[src] <= low[nbr]
                    AP[src]++;
                    isAP[src] = true;
                }

                if (dis[src] < low[nbr])
                    System.out.println("AP Edge: " + src + " -> " + nbr);

                low[src] = Math.min(low[src], low[nbr]);

            } else if (nbr != par) {
                low[src] = Math.min(low[src], dis[nbr]); // why not this? : low[src] = Math.min(low[src], low[nbr]);
            }
        }
    }

    public static void ArticulationPointsAndBridges(int N, ArrayList<Integer>[] graph) {
        low = new int[N];
        dis = new int[N];
        vis = new boolean[N];

        AP = new int[N];
        isAP = new boolean[N];

        int components = 0;
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                dfs(i, -1, N, graph);
                if (noOfCallsFromRoot == 1) {
                    isAP[i] = false;
                    AP[i] = 0;
                }
                noOfCallsFromRoot = 0;
                components++;
            }
        }

        // result.
        int countOfAP = 0;
        for (int i = 0; i < N; i++) {
            if (isAP[i]) {
                countOfAP++;
                System.out.println("AP: " + i + " @ " + "Increase in No Of components: " + AP[i]);
            }
        }

    }

}