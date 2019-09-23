import java.util.PriorityQueue;

public class graph {

    public static class Edge {
        int v;
        int weight;

        Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    public static boolean isBipart(ArrayList<ArrayList<Edge>> graph) {
        int[] isdone = new int[graph.size()];
        Arrays.fill(isdone, -1);

        class pair {
            int vtx = 0;
            int color = 0;

            pair(int vtx, int color) {
                this.vtx = vtx;
                this.color = color;
            }
        }

        LinkedList<pair> que = new LinkedList<>();
        boolean mainFlag = true;
        for (int i = 0; i < graph.size(); i++) {
            if (isdone[i] != -1) {
                continue;
            }

            boolean flag = true;
            que.addLast(new pair(i, 0));
            while (!que.isEmpty()) {
                pair proc = que.removeFirst();

                if (isdone[proc.vtx] == -1) {
                    isdone[proc.vtx] = proc.color;
                } else if (isdone[proc.vtx] != proc.color) {
                    flag = false;
                    break;
                }

                for (Edge e : graph.get(proc.vtx)) {
                    if (isdone[e.v] == -1) {
                        que.addLast(new pair(e.v, (proc.color + 1) % 2));
                    }
                }

            }

            System.out.println(flag);
            mainFlag &= flag;
        }
        return mainFlag;

    }

    public static String dijik(int src, int desti) {
        class pair implements Comparable<pair> {
            int vtx;
            int par;
            int weight;
            int csf;
            String psf = "";

            pair(int vtx, int par, int weight, int csf, String psf) {
                this.vtx = vtx;
                this.par = par;
                this.csf = csf;
                this.weight = weight;
                this.psf = psf;
            }

            @Override
            public int compareTo(pair o) {
                return this.weight - o.weight;
            }
        }

        boolean[] isdone = new boolean[graph.size()];
        // LinkedList<pair> que=new LinkedList<>();
        PriorityQueue<pair> que = new PriorityQueue<>();
        que.add(new pair(src, -1, 0, 0, src + ""));

        ArrayList<ArrayList<Edge>> dgraph = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++)
            dgraph.add(new ArrayList<Edge>());

        while (!que.isEmpty()) {
            pair proc = que.remove();
            if (isdone[proc.vtx]){
                System.out.println("Cycle");
                continue;
            }

            isdone[proc.vtx] = true;

            if (proc.par != -1) {
                dgraph.get(proc.vtx).add(new Edge(proc.par, proc.weight));
                dgraph.get(proc.par).add(new Edge(proc.vtx, proc.weight));
            }

            if (proc.vtx == desti) {
                System.out.println(proc.csf);
                return proc.psf;
            }

            for (Edge e : graph.get(proc.vtx)) {
                if (!isdone[e.v])
                    que.add(new pair(e.v, proc.vtx, e.weight, proc.csf + e.weight, proc.psf + e.v));
            }

        }
    }

}