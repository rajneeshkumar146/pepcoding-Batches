import java.util.ArrayList;

public class l001 {

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

    public static void display(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static int findVtx(ArrayList<Edge>[] graph, int u, int v) {
        int idx = -1;
        ArrayList<Edge> al = graph[u];
        for (int i = 0; i < al.size(); i++) {
            Edge e = al.get(i);
            if (e.v == v) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
        int idx1 = findVtx(graph, u, v);
        int idx2 = findVtx(graph, v, u);

        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVtx(ArrayList<Edge>[] graph, int u) {
        ArrayList<Edge> al = graph[u];
        while (al.size() != 0) {
            int v = al.get(al.size() - 1).v;
            removeEdge(graph, u, v);
        }
    }

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest) {

    }

    public static int printAllPath(ArrayList<Edge>[] graph, int src, int dest,???) {

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

        display(graph);

    }

    public static void main(String[] args) {
        graphConstruct();
    }
}