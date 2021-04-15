import java.util.ArrayList;

public class l001Basic {

    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public String toString() {
            return "(" + this.v + ", " + this.w + ") ";

        }
    }

    public static int N = 7;
    public static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display() {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print(e);
            }
            System.out.println();
        }
    }

    public static int findEdge(int u, int v) {

    }

    public static void removeEdge(int u, int v) {

    }

    public static void removeVtx(int u){

    }

    public static void main(String[] args) {
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        addEdge(3, 4, 10);
        addEdge(4, 5, 10);
        addEdge(4, 6, 10);
        addEdge(5, 6, 10);

        display();
    }

}