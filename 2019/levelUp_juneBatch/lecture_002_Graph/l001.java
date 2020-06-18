import java.util.ArrayList;

public class l001 {

    public static void main(String[] args) {
        solve();
    }

    public static class Edge {
        int v;
        int w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static int N = 7;
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));

        // Edge e = graph[3].get(2);
    }

    public static void display() {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i])
                System.out.print("(" + e.v + ", " + e.w + ") ");
            System.out.println();
        }
    }

    public static void constructor() {
        // Arrays.fill(graph, new ArrayList<Edge>());
        for (int i = 0; i < N; i++) {
            ArrayList<Edge> ar = new ArrayList<>();
            graph[i] = ar;
        }

        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 40);
        addEdge(3, 4, 2);
        addEdge(4, 5, 2);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);

        display();
    }

    public static int findVtx(int u, int v) {
        int idx = -1;
        for (int i = 0; i < graph[u].size(); i++) {
            Edge e = graph[u].get(i);
            if (e.v == v) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    public static void removeEdge(int u, int v) {
        int idx1 = findVtx(u, v);
        int idx2 = findVtx(v, u);

        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVtx(int u) {
        while (graph[u].size() != 0) {
            int v = graph[u].get(graph[u].size() - 1).v;
            removeEdge(u, v);
        }
    }

    public static boolean hasPath(int src, int dest, boolean[] vis) {
        if (src == dest)
            return true;

        vis[src] = true; // mark;
        boolean res = false;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) // unvisited
                res = res || hasPath(e.v, dest, vis);
        }

        return res;
    }

    public static int hasAllPath(int src, int dest, int wsf, boolean[] vis, String path) {
        if (src == dest) {
            System.out.println(path + dest + " @ " + wsf);
            return 1;
        }

        vis[src] = true;
        int count = 0;

        for (Edge e : graph[src]) {
            if (!vis[e.v])
                count += hasAllPath(e.v, dest, wsf + e.w, vis, path + src + " ");
        }

        for (int i = 0; i < graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            if (!vis[e.v])
                count += hasAllPath(e.v, dest, wsf + e.w, vis, path + src + " ");
        }

        vis[src] = false;
        return count;
    }

    int lw = (int) 1e7;
    static String lwp = "";

    static int hw = 0;
    static String hwp = "";

    public static void AllPath(int src, int dest, int wsf, boolean[] vis, String path) {
        if (src == dest) {
            path += dest;
            if (wsf < lw) {
                lw = wsf;
                lwp = path;
            }

            if (wsf > hw) {
                hw = wsf;
                hwp = path;
            }

            return;

        }

        vis[src] = true;

        for (Edge e : graph[src]) {
            if (!vis[e.v])
                AllPath(e.v, dest, wsf + e.w, vis, path + src + " ");
        }

        vis[src] = false;
    }

    static class allAns {
        int wop; // weight Of Path
        String path;

        public allAns(int wop, String path) {
            this.wop = wop;
            this.path = path;
        }
    }

    public static allAns lweightPath(int src, int dest, boolean[] vis) {
        if (src == dest) {
            allAns base = new allAns(0, src + "");
            return base;
        }

        vis[src] = true;
        allAns myAns = new allAns((int) 1e8, ""); // For heavyWeightPath: -1e8;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                allAns recAns = lweightPath(e.v, dest, vis);
                if (recAns.wop + e.w < myAns.wop) { // For heavyWeightPath: > ;
                    myAns.wop = recAns.wop + e.w;
                    myAns.path = src + recAns.path;
                }
            }
        }

        vis[src] = false;
        return myAns;

    }

    // first : use static variable.
    // second : without static variable.

    public static int ceilValeWithStatic(int src, int dest, boolean[] vis, int data) {
        return -1;
    }

    public static int floorValeWithStatic(int src, int dest, boolean[] vis, int data) {
        return -1;
    }

    public static int ceilVale(int src, int dest, boolean[] vis, int data) {
        return -1;
    }

    public static int floorVale(int src, int dest, boolean[] vis, int data) {
        return -1;
    }

    public static void solve() {
        constructor();
        boolean[] vis = new boolean[N];
        System.out.println(hasAllPath(0, 6, 0, vis, ""));
        // AllPath(0, 6, 0, vis, "");
        // System.out.println(hwp + " @ " + hw);
        // System.out.println(lwp + " @ " + lw);
    }

}