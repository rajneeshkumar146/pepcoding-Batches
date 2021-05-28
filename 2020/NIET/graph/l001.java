import java.util.ArrayList;
import java.util.PriorityQueue;

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

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest)
            return true;

        vis[src] = true;
        boolean res = false;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                res = res || hasPath(graph, e.v, dest, vis);
        }
        return res;
    }

    public static int printAllPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis, String ans) {
        if (src == dest) {
            System.out.println(ans + dest);
            return 1;
        }

        int count = 0;
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                count += printAllPath(graph, e.v, dest, vis, ans + src);
            }
        }
        vis[src] = false;

        return count;
    }

    public static void printpreOrder(ArrayList<Edge>[] graph, int src, int wsf, boolean[] vis, String ans) {
        System.out.println(src + " -> " + ans + src + "@" + wsf);
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                printpreOrder(graph, e.v, wsf + e.w, vis, ans + src);
        }
        vis[src] = false;
    }

    public static class pair {
        String psf = "";
        int wsf = 0;

        pair(String psf, int wsf) {
            this.wsf = wsf;
            this.psf = psf;
        }

        pair() {

        }
    }

    public static pair heavyWeightPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest) {
            return new pair(src + "", 0);
        }

        vis[src] = true;
        pair myAns = new pair("", -(int) 1e9);
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                pair recAns = heavyWeightPath(graph, e.v, dest, vis);
                if (recAns.wsf != -(int) 1e9 && recAns.wsf + e.w > myAns.wsf) {
                    myAns.wsf = recAns.wsf + e.w;
                    myAns.psf = src + recAns.psf;
                }
            }
        }
        vis[src] = false;

        return myAns;
    }

    public static pair lightWeightPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest) {
            return new pair(src + "", 0);
        }

        vis[src] = true;
        pair myAns = new pair("", (int) 1e9);
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                pair recAns = lightWeightPath(graph, e.v, dest, vis);
                if (recAns.wsf != -(int) 1e9 && recAns.wsf + e.w < myAns.wsf) {
                    myAns.wsf = recAns.wsf + e.w;
                    myAns.psf = src + recAns.psf;
                }
            }
        }
        vis[src] = false;

        return myAns;
    }

    // static class Pair implements Comparable<Pair> {
    //     int wsf;
    //     String psf;

    //     Pair(int wsf, String psf) {
    //         this.wsf = wsf;
    //         this.psf = psf;
    //     }

    //     public int compareTo(Pair o) {
    //         return this.wsf - o.wsf;
    //     }
    // }

    // static String spath;
    // static Integer spathwt = Integer.MAX_VALUE;

    // static String lpath;
    // static Integer lpathwt = Integer.MIN_VALUE;

    // static String cpath;
    // static Integer cpathwt = Integer.MAX_VALUE;

    // static String fpath;
    // static Integer fpathwt = Integer.MIN_VALUE;

    // static PriorityQueue<Pair> pq = new PriorityQueue<>();

    // public static void multisolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis, int criteria, int k,
    //         String psf, int wsf) {

    //     if (src == dest) {
    //         if (wsf < spathwt) {
    //             spathwt = wsf;
    //             spath = psf;
    //         }

    //         if (lpathwt < wsf) {
    //             lpathwt = wsf;
    //             lpath = psf;
    //         }

    //         if (wsf < criteria && wsf > fpathwt) {
    //             fpathwt = wsf;
    //             fpath = psf;
    //         }

    //         if (wsf > criteria && wsf < cpathwt) {
    //             cpathwt = wsf;
    //             cpath = psf;
    //         }

    //         pq.add(new Pair(wsf, psf));
    //         if (pq.size() > k)
    //             pq.remove();

    //         return;
    //     }

    //     vis[src] = true;
    //     for (Edge e : graph[src]) {
    //         if (!vis[e.v])
    //             multisolver(graph, e.v, dest, vis, criteria, k, psf + e.v, wsf + e.w);
    //     }
    //     vis[src] = false;
    // }

    // Get Connected Components

    public static void dfs(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                dfs(graph, e.v, vis);
        }
    }

    public static int GCC(ArrayList<Edge>[] graph) {
        int N = graph.length;
        boolean[] vis = new boolean[N];

        int components = 0;
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                components++;
                dfs(graph, i, vis);
            }
        }

        return components;
    }

    public static int dfs_hamintonianPath(ArrayList<Edge>[] graph, boolean[] vis, int src, int osrc, int count,
            String psf) {
        if (count == graph.length - 1) {
            int idx = findVtx(graph, src, osrc);
            if (idx != -1)
                System.out.println(psf + "*");
            else
                System.out.println(psf + ".");
            return 1;
        }
        int totalPaths = 0;
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                totalPaths += dfs_hamintonianPath(graph, vis, e.v, osrc, count + 1, psf + e.v);
            }
        }`
        vis[src] = false;
        return totalPaths;
    }

    // Hamintonian Path
    public static void hamintonianPath(int src, ArrayList<Edge>[] graph, int N) {
        boolean[] vis = new boolean[N];
        dfs_hamintonianPath(graph, vis, src, src, 0, src + "");
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
        addEdge(graph, 0, 6, 2);

        display(graph);

        boolean[] vis = new boolean[N];
        // System.out.println(printAllPath(graph, 0, 6, vis, ""));
        // printpreOrder(graph, 0, 0, vis, "");

        // pair ans = heavyWeightPath(graph, 0, 6, vis);
        // pair ans = lightWeightPath(graph, 0, 6, vis);
        // if (ans.wsf != -(int) 1e9)
        // System.out.println(ans.psf + "@" + ans.wsf);
        hamintonianPath(0, graph, N);
    }

    public static void main(String[] args) {
        graphConstruct();
    }
}