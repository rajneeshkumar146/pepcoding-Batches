import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Arrays;

public class l002_directed {

    public static class Edge {
        int v, w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
    }

    public static void display(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static void dfs_top(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                dfs_top(graph, e.v, vis, ans);
        }

        ans.add(src);
    }

    public static void topologicalOrder(ArrayList<Edge>[] graph) {
        int N = graph.length;
        boolean[] vis = new boolean[N];

        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                dfs_top(graph, i, vis, ans);
            }
        }

        for (int i = ans.size() - 1; i >= 0; i--)
            System.out.print(ans.get(i) + " ");
    }

    public static void graphConstruct() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 2);
        // addEdge(graph, 0, 6, 2);

        display(graph);

        boolean[] vis = new boolean[N];

    }

    public static void main(String[] args) {
        graphConstruct();
    }

}