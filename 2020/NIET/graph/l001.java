import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Arrays;

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
    // int wsf;
    // String psf;

    // Pair(int wsf, String psf) {
    // this.wsf = wsf;
    // this.psf = psf;
    // }

    // public int compareTo(Pair o) {
    // return this.wsf - o.wsf;
    // }
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

    // public static void multisolver(ArrayList<Edge>[] graph, int src, int dest,
    // boolean[] vis, int criteria, int k,
    // String psf, int wsf) {

    // if (src == dest) {
    // if (wsf < spathwt) {
    // spathwt = wsf;
    // spath = psf;
    // }

    // if (lpathwt < wsf) {
    // lpathwt = wsf;
    // lpath = psf;
    // }

    // if (wsf < criteria && wsf > fpathwt) {
    // fpathwt = wsf;
    // fpath = psf;
    // }

    // if (wsf > criteria && wsf < cpathwt) {
    // cpathwt = wsf;
    // cpath = psf;
    // }

    // pq.add(new Pair(wsf, psf));
    // if (pq.size() > k)
    // pq.remove();

    // return;
    // }

    // vis[src] = true;
    // for (Edge e : graph[src]) {
    // if (!vis[e.v])
    // multisolver(graph, e.v, dest, vis, criteria, k, psf + e.v, wsf + e.w);
    // }
    // vis[src] = false;
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
        }
        vis[src] = false;
        return totalPaths;
    }

    // Hamintonian Path
    public static void hamintonianPath(int src, ArrayList<Edge>[] graph, int N) {
        boolean[] vis = new boolean[N];
        dfs_hamintonianPath(graph, vis, src, src, 0, src + "");
    }

    // BFS.===========================================================

    public static void BFS(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>(); // que -> addLast, removeFirst
        que.addLast(src);

        int level = 0;
        boolean isCycle = false;
        while (que.size() != 0) {
            int size = que.size();
            System.out.print(level + " No Of Edges Required for: ");
            while (size-- > 0) {

                Integer rvtx = que.removeFirst();
                if (vis[rvtx]) {
                    isCycle = true;
                    continue;
                }

                System.out.print(rvtx + " ");

                vis[rvtx] = true;
                for (Edge e : graph[rvtx]) {
                    if (!vis[e.v])
                        que.addLast(e.v);
                }
            }
            level++;
            System.out.println();
        }

    }

    public static void BFS_forNoCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>(); // que -> addLast, removeFirst
        que.addLast(src);
        vis[src] = true;

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            System.out.print(level + " No Of Edges Required for: ");
            while (size-- > 0) {

                Integer rvtx = que.removeFirst();
                System.out.print(rvtx + " ");

                for (Edge e : graph[rvtx]) {
                    if (!vis[e.v]) {
                        vis[e.v] = true;
                        que.addLast(e.v);
                    }
                }
            }
            level++;
            System.out.println();
        }

    }

    public static boolean isBipartite(ArrayList<Edge>[] graph, int src, int[] vis) {
        LinkedList<Integer> que = new LinkedList<>(); // que -> addLast, removeFirst

        que.addLast(src);
        int color = 0; // 0 -> red, 1 -> green

        boolean isBipartite = true;

        while (que.size() != 0) {
            int size = que.size();
            while (size != 0) {
                int rvtx = que.removeFirst();

                if (vis[rvtx] != -1) {

                    if (vis[rvtx] != color)
                        isBipartite = false;
                    continue;
                }

                vis[rvtx] = color;
                for (Edge e : graph[rvtx]) {
                    if (vis[e.v] == -1)
                        que.addLast(e.v);
                }
            }

            color = (color + 1) % 2;
        }

        return isBipartite;
    }

    public static boolean isBipartit(ArrayList<Edge>[] graph) {
        int[] vis = new int[graph.length];
        Arrays.fill(vis, -1);

        for (int i = 0; i < graph.length; i++) {
            if (vis[i] == -1) {
                if (!isBipartite(graph, i, vis))
                    return false;
            }
        }

        return true;
    }

    public static class diji_pair implements Comparable<diji_pair> {
        int vtx, par, wt, wsf;
        String psf;

        diji_pair(int vtx, int par, int wt, int wsf, String psf) {
            this.vtx = vtx;
            this.par = par;
            this.wt = wt;
            this.wsf = wsf;
            this.psf = psf;
        }

        @Override
        public int compareTo(diji_pair o) {
            return this.wsf - o.wsf;
        }
    }

    public static void dijikstrAlgo_01(ArrayList<Edge>[] graph, int src) {
        int N = 7;
        ArrayList<Edge>[] myGraph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            myGraph[i] = new ArrayList<>();

        boolean[] vis = new boolean[N];
        PriorityQueue<diji_pair> pq = new PriorityQueue<>();

        pq.add(new diji_pair(src, -1, 0, 0, src + ""));

        int[] dis = new int[N];

        while (pq.size() != 0) {
            diji_pair rp = pq.remove();

            if (vis[rp.vtx])
                continue;

            if (rp.par != -1)
                addEdge(myGraph, rp.vtx, rp.par, rp.wt);

            System.out.println(rp.vtx + " via " + rp.psf + " @ " + rp.wsf);

            dis[rp.vtx] = rp.wsf;
            vis[rp.vtx] = true;
            for (Edge e : graph[rp.vtx]) {
                if (!vis[e.v])
                    pq.add(new diji_pair(e.v, rp.vtx, e.w, rp.wsf + e.w, rp.psf + e.v));
            }
        }

        display(myGraph);
        for (int ele : dis)
            System.out.print(ele + " ");
    }

    public static int[] dijikstrAlgo_forQuestionSolving(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        boolean[] vis = new boolean[N];
        PriorityQueue<diji_pair> pq = new PriorityQueue<>();

        pq.add(new diji_pair(src, -1, 0, 0, src + ""));
        int[] dis = new int[N];

        while (pq.size() != 0) {
            diji_pair rp = pq.remove();

            if (vis[rp.vtx])
                continue;

            dis[rp.vtx] = rp.wsf;
            vis[rp.vtx] = true;
            for (Edge e : graph[rp.vtx]) {
                if (!vis[e.v])
                    pq.add(new diji_pair(e.v, rp.vtx, e.w, rp.wsf + e.w, rp.psf + e.v));
            }
        }

        return dis;
    }

    public static class diji_pair2 implements Comparable<diji_pair2> {
        int vtx, wsf;

        diji_pair2(int vtx, int wsf) {
            this.vtx = vtx;
            this.wsf = wsf;

        }

        @Override
        public int compareTo(diji_pair2 o) {
            return this.wsf - o.wsf;
        }
    }

    public static int[] dijikstrAlgo_bestMethod(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        PriorityQueue<diji_pair2> pq = new PriorityQueue<>();

        int[] dis = new int[N];
        int[] par = new int[N];
        boolean[] vis = new boolean[N];

        Arrays.fill(dis, (int) 1e9);
        Arrays.fill(par, -1);

        pq.add(new diji_pair2(src, 0));
        dis[src] = 0;

        while (pq.size() != 0) {
            diji_pair2 rp = pq.remove();

            if (vis[rp.vtx])
                continue;

            vis[rp.vtx] = true;
            for (Edge e : graph[rp.vtx]) {
                if (!vis[e.v] && e.w + rp.wsf < dis[e.v]) {
                    dis[e.v] = e.w + rp.wsf;
                    par[e.v] = rp.vtx;

                    pq.add(new diji_pair2(e.v, rp.wsf + e.w));
                }
            }
        }

        return dis;
    }

    private static class primsPair {
        int vtx, wt;

        primsPair(int vtx, int wt) {
            this.vtx = vtx;
            this.wt = wt;
        }
    }

    private static void prims(ArrayList<Edge>[] graph, int src, ArrayList<Edge>[] primsGraph, boolean[] vis) {
        int N = graph.length;
        PriorityQueue<primsPair> pq = new PriorityQueue<>((a, b) -> {
            return a.wt - b.wt;
        });

        int[] dis = new int[N];
        int[] par = new int[N];
        Arrays.fill(dis, (int) 1e9);
        Arrays.fill(par, -1);

        pq.add(new primsPair(src, 0));
        dis[src] = 0;

        while (pq.size() != 0) {
            primsPair p = pq.remove();
            if (vis[p.vtx])
                continue;

            vis[p.vtx] = true;
            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v] && e.w < dis[e.v]) {
                    dis[e.v] = e.w;
                    par[e.v] = p.vtx;

                    pq.add(new primsPair(e.v, e.w));
                }
            }
        }
    }

    public static void prims(ArrayList<Edge>[] graph) {
        int N = 7;
        ArrayList<Edge>[] primsg = new ArrayList[N];
        for (int i = 0; i < N; i++)
            primsg[i] = new ArrayList<>();

        boolean[] vis = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                prims(graph, i, primsg, vis);
            }
        }

        display(primsg);
    }

    public static void graphConstruct() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 40);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 2, 5, 5);

        display(graph);

        // boolean[] vis = new boolean[N];
        // System.out.println(printAllPath(graph, 0, 6, vis, ""));
        // printpreOrder(graph, 0, 0, vis, "");

        // pair ans = heavyWeightPath(graph, 0, 6, vis);
        // pair ans = lightWeightPath(graph, 0, 6, vis);
        // if (ans.wsf != -(int) 1e9)
        // System.out.println(ans.psf + "@" + ans.wsf);
        // hamintonianPath(0, graph, N);
        // BFS(graph, 0, vis);

        System.out.println();
        dijikstrAlgo_01(graph, 0);
    }

    public static void main(String[] args) {
        graphConstruct();
    }
}