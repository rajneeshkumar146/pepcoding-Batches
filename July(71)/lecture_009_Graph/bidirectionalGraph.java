import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class bidirectionalGraph {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        for (int i = 0; i < 9; i++) {
            addVertex(i);
        }

        addEdge(0, 1, 10);
        addEdge(0, 3, 40);
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        addEdge(3, 4, 2);
        addEdge(4, 5, 2);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);

        addEdge(2, 7, 3);
        addEdge(2, 8, 3);
        // ===================================================

        // display();
        // removeVertex(3);
        // display();
        // hasPath();
        // BFSandDFS();
        // isBipitrate();
        DijikstrAlgo(0);
    }

    private static class Edge {
        int v1 = 0;
        int v2 = 0;
        int weight = 0;

        private Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
    }

    private static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

    private static int getIdx(int v1, int v2) {
        for (int i = 0; i < graph.get(v1).size(); i++) {
            Edge e = graph.get(v1).get(i);
            if (e.v2 == v2) {
                return i;
            }
        }
        return -1;
    }

    // vertex=================================================
    public static void addVertex(int v1) {
        if (graph.size() == v1) {
            graph.add(new ArrayList<>());
        }
    }

    public static void removeVertex(int v1) {
        for (int i = 0; i < graph.get(v1).size(); i++) {
            Edge e = graph.get(v1).get(i);
            int idx = getIdx(e.v2, e.v1);
            graph.get(e.v2).remove(idx);
        }

        graph.remove(v1);
    }

    // edge========================================================
    public static int totalEdges() {
        int count = 0;
        for (int i = 0; i < graph.size(); i++) {
            count += graph.get(i).size();
        }

        return count / 2;
    }

    public static boolean containsEdge(int v1, int v2) {
        if (v1 < graph.size() && v2 < graph.size() && getIdx(v1, v2) != -1) {
            return true;
        }
        return false;
    }

    public static void addEdge(int v1, int v2, int weight) {
        if (v1 < graph.size() && v2 < graph.size()) {
            graph.get(v1).add(new Edge(v1, v2, weight));
            graph.get(v2).add(new Edge(v2, v1, weight));
        }
    }

    public static void removeEdge(int v1, int v2) {
        int idx1 = getIdx(v1, v2);
        if (idx1 != -1)
            graph.get(v1).remove(idx1);

        int idx2 = getIdx(v2, v1);
        if (idx2 != -1)
            graph.get(v2).remove(idx2);
    }

    // display.======================================================

    public static void display() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.size(); i++) {
            for (Edge e : graph.get(i)) {
                sb.append("(" + e.v1 + "," + e.v2 + ")" + "@" + e.weight + " ");
            }

            sb.append("\n");
        }

        System.out.print(sb.toString() + "\n");
    }

    // hasPath.===============================================================

    public static void hasPath() {
        boolean[] isVisited = new boolean[graph.size()];
        // removeEdge(3, 4);
        // System.out.print(hasPath(0, 6, isVisited, ""));
        System.out.print(printAllPath(0, 6, isVisited, "", 0));
    }

    public static boolean hasPath(int src, int desti, boolean[] isVisited, String ans) {
        if (src == desti) {
            System.out.print(ans + " -> ");
            return true;
        }

        isVisited[src] = true;
        for (Edge e : graph.get(src)) {
            if (isVisited[e.v2])
                continue;

            boolean res = hasPath(e.v2, desti, isVisited, ans + src + " ");
            if (res) {
                return true;
            }
        }

        return false;
    }

    public static int printAllPath(int src, int desti, boolean[] isVisited, String ans, int wsf) {
        if (src == desti) {
            System.out.print(ans + src + " @ " + wsf + "\n");
            return 1;
        }

        int count = 0;
        isVisited[src] = true;
        for (Edge e : graph.get(src)) {
            if (isVisited[e.v2])
                continue;

            count += printAllPath(e.v2, desti, isVisited, ans + src + " ", wsf + e.weight);
        }

        isVisited[src] = false;
        return count;
    }

    // BFSandDFS.========================================================

    public static void BFSandDFS() {
        boolean[] isVisited = new boolean[graph.size()];
        // BFS(0, 6,isVisited);
        // BFTComponents();
        DFTComponent();
    }

    public static class BFSpair {
        int vtx = 0;
        int wsf = 0;
        String psf = "";
        int noOfEdges = 0;

        BFSpair(int vtx, int wsf, String psf, int noOfEdges) {
            this.vtx = vtx;
            this.wsf = wsf;
            this.psf = psf;
            this.noOfEdges = noOfEdges;
        }
    }

    public static void BFS(int src, int desti, boolean[] isVisited) {

        LinkedList<BFSpair> que = new LinkedList<>();

        que.addLast(new BFSpair(src, 0, src + "", 0));

        while (!que.isEmpty()) {
            BFSpair rpair = que.removeFirst();
            if (isVisited[rpair.vtx]) { // spotted cycle.
                System.out.println("Cycle : " + rpair.vtx + " via " + rpair.psf);
                continue;
            }
            isVisited[rpair.vtx] = true;

            if (rpair.vtx == desti) { // sppoted destination.
                System.out.println("Desti : " + rpair.vtx + " via " + rpair.psf);
                // return;
            }
            System.out.println(rpair.vtx + " via " + rpair.psf);

            for (Edge e : graph.get(rpair.vtx)) {
                if (!isVisited[e.v2]) { // if it is marked then leave this children.
                    que.addLast(new BFSpair(e.v2, rpair.wsf + e.weight, rpair.psf + e.v2, rpair.noOfEdges + 1));
                }
            }
        }
    }

    public static void BFTComponents() {
        boolean[] isVisited = new boolean[graph.size()];
        int components = 0;
        removeEdge(3, 4);
        for (int i = 0; i < graph.size(); i++) {
            if (!isVisited[i]) {
                BFS(i, 6, isVisited);
                System.out.println();
                components++;
            }
        }

        System.out.println(components);
    }

    public static void DFSRec(int src, boolean[] isVisited, String psf) {
        isVisited[src] = true;
        System.out.println(src + " via " + psf);

        for (Edge e : graph.get(src)) {
            if (!isVisited[e.v2])
                DFSRec(e.v2, isVisited, psf + e.v2);
        }

        isVisited[src] = false;
    }

    public static void DFTComponent() {
        boolean[] isVisited = new boolean[graph.size()];
        int components = 0;
        removeEdge(3, 4);
        for (int i = 0; i < graph.size(); i++) {
            if (!isVisited[i]) {
                DFSRec(i, isVisited, i + "");
                System.out.println();
                components++;
            }
        }

        System.out.println(components);
    }

    // cyclic_and_Bipitrate.==============================================

    public static void cyclic_and_Bipitrate() {
        System.out.println(isCycle());

        System.out.println(isConnected());
    }

    public static boolean isCycle() {
        boolean[] isVisited = new boolean[graph.size()];
        int cycleCount = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (!isVisited[i]) {
                cycleCount += noOfCyclesinThatComponent(i, isVisited);
            }
        }

        return cycleCount > 0;

    }

    public static void componentCycle() {
        boolean[] isVisited = new boolean[graph.size()];
        int compnonent = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (!isVisited[i]) {
                int cycleCount = noOfCyclesinThatComponent(i, isVisited);
                compnonent++;
                System.out.println("component no: " + compnonent + " have " + "total no of Cycle is: " + cycleCount);
            }
        }
    }

    public static boolean isConnected() {
        boolean[] isVisited = new boolean[graph.size()];
        int compnonent = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (!isVisited[i]) {
                int cycleCount = noOfCyclesinThatComponent(i, isVisited);
                compnonent++;
            }
        }

        return compnonent == 1 ? true : false;

    }

    private static int noOfCyclesinThatComponent(int src, boolean[] isVisited) {
        LinkedList<BFSpair> que = new LinkedList<>();
        que.addLast(new BFSpair(src, 0, src + "", 0));
        int count = 0;
        while (!que.isEmpty()) {
            BFSpair rpair = que.removeFirst();
            if (isVisited[rpair.vtx]) { // spotted cycle.
                count++;
                continue;
            }

            isVisited[rpair.vtx] = true;
            for (Edge e : graph.get(rpair.vtx)) {
                if (!isVisited[e.v2]) { // if it is marked then leave this children.
                    que.addLast(new BFSpair(e.v2, rpair.wsf + e.weight, rpair.psf + e.v2, rpair.noOfEdges + 1));
                }
            }
        }
        return count;
    }

    // Bipitrate.===================================================

    public static void isBipitrate() {
        removeEdge(3, 4);
        // System.out.println(isBipitrate(0));
        allBipitrate();
    }

    private static class biPitratePair {
        int vtx = 0;
        String color = "R";

        public biPitratePair(int vtx, String color) {
            this.vtx = vtx;
            this.color = color;
        }
    }

    public static void allBipitrate() {
        biPitratePair[] isVisited = new biPitratePair[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (isVisited[i] == null) {
                System.out.println(isBipitrate(i, isVisited));
            }
        }
    }

    public static boolean isBipitrate(int src, biPitratePair[] isVisited) {

        LinkedList<biPitratePair> que = new LinkedList<>();

        que.addLast(new biPitratePair(src, "R"));
        while (!que.isEmpty()) {
            biPitratePair rpair = que.removeFirst();

            if (isVisited[rpair.vtx] != null) {
                String oldColor = isVisited[rpair.vtx].color;
                String newColor = rpair.color;
                if (!oldColor.equals(newColor))
                    return false;
                continue;
            }

            isVisited[rpair.vtx] = rpair;
            for (Edge e : graph.get(rpair.vtx)) {
                if (isVisited[e.v2] == null) {
                    String color = rpair.color.equals("R") ? "G" : "R";
                    que.addLast(new biPitratePair(e.v2, color));
                }
            }

        }
        return true;
    }

    private static class BAWPair {
        int r;
        int c;
        int time;

        public BAWPair(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    public static int BombAndWaterBFS(int[][] mat) {

        boolean[][] isVisited = new boolean[mat.length][mat[0].length];
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int Time = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (isVisited[i][j] == false && mat[i][j] == 1) {
                    LinkedList<BAWPair> que = new LinkedList<>();
                    que.addLast(new BAWPair(i, j, 0));

                    while (!que.isEmpty()) {

                        BAWPair rpair = que.removeFirst();

                        Time = rpair.time;

                        for (int d = 0; d < dir.length; d++) {
                            int r = dir[d][0] + rpair.r;
                            int c = dir[d][1] + rpair.c;

                            if (r < mat.length && c < mat[0].length && r >= 0 && c >= 0 && mat[r][c] == 1
                                    && !isVisited[r][c]) {
                                isVisited[r][c] = true;
                                que.addLast(new BAWPair(r, c, rpair.time + 1));
                                // isVisited[r][c]=false;
                            }

                        }
                    }
                }

            }

        }
        return Time;

    }

    // rotOranges.===================================================

    public static int rottenOranges(int[][] orange) {

        LinkedList<BAWPair> que = new LinkedList<>();
        for (int i = 0; i < orange.length; i++) {
            for (int j = 0; j < orange[0].length; j++) {
                if (orange[i][j] == 2) {
                    que.addLast(new BAWPair(i, j, 0));
                }
            }
        }

        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int Time = 0;

        while (!que.isEmpty()) {
            BAWPair rpair = que.removeFirst();
            for (int d = 0; d < dir.length; d++) {
                int r = dir[d][0] + rpair.r;
                int c = dir[d][1] + rpair.c;

                if (r < orange.length && c < orange[0].length && r >= 0 && c >= 0 && orange[r][c] == 1) {
                    orange[r][c] = 2;
                    int prevtime = rpair.time;
                    int currTime = prevtime + 1;
                    que.addLast(new BAWPair(r, c, currTime));
                    Time = currTime;
                }

            }
        }

        for (int i = 0; i < orange.length; i++) {
            for (int j = 0; j < orange[0].length; j++) {
                if (orange[i][j] == 1) {
                    return -1;
                }
            }
        }

        return Time;
    }

    // Dijikstra_algo.=======================================

    private static class DijikstrPair implements Comparable<DijikstrPair> {
        int vtx = 0;
        int csf = Integer.MAX_VALUE;
        String psf = "";
        int noe = 0;
        int avtx=0;

        public DijikstrPair(int vtx, int avtx,int csf, String psf, int noe) {
            this.vtx = vtx;
            this.avtx=avtx;
            this.csf = csf;
            this.psf = psf;
            this.noe = noe;

        }

        @Override
        public int compareTo(DijikstrPair o) {
            return this.csf - o.csf;
        }
    }

    public static void DijikstrAlgo(int src) {
        
        // ArrayList<ArrayList<Edge>> dgraph=new ArrayList<>();


        PriorityQueue<DijikstrPair> pq = new PriorityQueue<>();
        DijikstrPair[] maping = new DijikstrPair[graph.size()];
        boolean[] isVisited = new boolean[graph.size()];

        for (int i = 0; i < graph.size(); i++) {
            DijikstrPair p = new DijikstrPair(i, Integer.MAX_VALUE, "", 0);
            if (i == src) {
                p.csf = 0;
                p.psf = i + "";
                p.avtx=-1;
            }

            maping[i] = p;
            pq.add(p);
        }

        while (!pq.isEmpty()) {
            DijikstrPair rpair = pq.remove();
            isVisited[rpair.vtx] = true;
            
        //    dgraph.addVertex(rpair.vtx);
        //     if(rpair.avtx!=-1){
        //         addEdge(dgraph,rpair.vtx,rpair.avtx,rpair.weight);
        //     }
  


            if (rpair.vtx == 6) {
                System.out.println(rpair.csf + " " + rpair.psf + " " + rpair.noe);
            }

            for (Edge e : graph.get(rpair.vtx)) {
                if (isVisited[e.v2])
                    continue;

                DijikstrPair oldPair = maping[e.v2];
                int oldcsf = oldPair.csf;
                int newcsf = rpair.csf + e.weight;
                if (newcsf < oldcsf) {
                    DijikstrPair p = new DijikstrPair(e.v2,rpair.vtx, newcsf, rpair.psf + e.v2, rpair.noe + 1);

                    maping[e.v2] = p;
                    pq.remove(oldPair);
                    pq.add(p);
                }
            }
        }
    }

}
