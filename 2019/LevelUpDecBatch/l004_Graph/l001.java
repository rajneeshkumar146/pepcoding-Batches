import java.util.ArrayList;

public class l001 {
    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static int N = 7;

    @SuppressWarnings("unchecked")
    // public static ArrayList<Edge>[] graph = new ArrayList[N];
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display() {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static int findEdge(int u, int v) {
        int idx = -1;
        for (int i = 0; i < graph[u].size(); i++) {
            if (graph[u].get(i).v == v) {
                idx = i;
                break;
            }
        }

        return idx;
    }

    public static void removeEdge(int u, int v) {
        int idx1 = findEdge(u, v);
        int idx2 = findEdge(v, u);

        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVtx(int u) {
        for (int i = graph[u].size() - 1; i >= 0; i--) {
            int v = graph[u].get(i).v;
            removeEdge(u, v);
        }
    }

    public static void constructGraph() {
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 40);
        addEdge(3, 4, 2);
        addEdge(4, 5, 2);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);
    }

}