import java.util.ArrayList;
import java.util.Arrays;

public class kruskalAlgo {

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

    static int[] par;
    static int[] size;

    // with Path Cpmpression : alpha(n) <= 4, Inverse Akermann Function
    // without path compression : O(logn)
    public static int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    // O(1)
    public static void merge(int p1, int p2) {
        if (size[p1] > size[p2]) {
            par[p2] = p1; // merge
            size[p1] += size[p2];
        } else {
            par[p1] = p2; // merge
            size[p2] += size[p1];
        }
    }

    // Edges : {{u,v,w}.....}
    // without Path Compression and size : V + E*V
    // Path Compression: V + E(alpha(n))
    // without Path Compression: V + ELog(V)
    public static void unionFind(int N, int[][] Edges) {
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        par = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            size[i] = 1;
            par[i] = i;
        }

        for (int[] edge : Edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2) {
                merge(p1, p2);
                addEdge(graph, u, v, w);
            }
        }

        display(N, graph);
    }

    public static void solve() {
        int N = 9;
        int[][] edges = { { 0, 1, 4 }, { 0, 7, 8 } };

        // default behaviour is increasing order.
        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2]; // this - other, default behaviour.
            // return b[2] - a[2]; // other - this, reverse of default behaviour.
        });

        unionFind(N, edges);

    }

    public static void main(String[] args) {
        solve();
    }

}