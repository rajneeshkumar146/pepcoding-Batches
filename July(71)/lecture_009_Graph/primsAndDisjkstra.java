import java.util.ArrayList;
import java.util.PriorityQueue;

public class primsAndDisjkstra {
    public static void main(String[] args) {

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            graph.add(new ArrayList<>());
        }

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 20);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        // addEdge(2, 7, 3);
        // addEdge(2, 8, 3);

        display(graph);
        primsAlgo(graph);
        DijikstraAlgo(graph);

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

    public static void primsAlgo(ArrayList<ArrayList<Edge>> graph) {
        class primsPair implements Comparable<primsPair> {
            int vtx;
            int avtx;
            int weight;

            public primsPair(int vtx, int avtx, int weight) {
                this.vtx = vtx;
                this.avtx = avtx;
                this.weight = weight;
            }

            @Override
            public int compareTo(primsPair o) {
                return this.weight - o.weight;
            }
        }

        ArrayList<ArrayList<Edge>> primsGraph = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            primsGraph.add(new ArrayList<>());
        }

        PriorityQueue<primsPair> pq = new PriorityQueue<>();
        boolean[] isVisited = new boolean[graph.size()];

        pq.add(new primsPair(0, -1, 0));
        while (!pq.isEmpty()) {
            primsPair rpair = pq.remove();
            if (isVisited[rpair.vtx])
                continue;

            if (rpair.avtx != -1) {
                addEdge(primsGraph, rpair.vtx, rpair.avtx, rpair.weight);
            }

            isVisited[rpair.vtx] = true;
            for (Edge e : graph.get(rpair.vtx)) {
                if (!isVisited[e.v2]) {
                    pq.add(new primsPair(e.v2, rpair.vtx, e.weight));
                }
            }

        }

        System.out.println();
        display(primsGraph);
    }

    public static void DijikstraAlgo(ArrayList<ArrayList<Edge>> graph) {
        class DijisktraPair implements Comparable<DijisktraPair> {
            int vtx;
            int avtx;
            int weight;
            int wsf;
            String psf = "";

            public DijisktraPair(int vtx, int avtx, int weight, int wsf, String psf) {
                this.vtx = vtx;
                this.avtx = avtx;
                this.weight = weight;
                this.wsf = wsf;
                this.psf = psf;
            }

            @Override
            public int compareTo(DijisktraPair o) {
                return this.wsf - o.wsf;
            }
        }

        ArrayList<ArrayList<Edge>> DijisktraGraph = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            DijisktraGraph.add(new ArrayList<>());
        }

        PriorityQueue<DijisktraPair> pq = new PriorityQueue<>();
        boolean[] isVisited = new boolean[graph.size()];

        pq.add(new DijisktraPair(0, -1, 0, 0, 0 + ""));
        while (!pq.isEmpty()) {
            DijisktraPair rpair = pq.remove();
            if (isVisited[rpair.vtx])
                continue;

            if (rpair.avtx != -1) {
                addEdge(DijisktraGraph, rpair.vtx, rpair.avtx, rpair.weight);
            }

            System.out.println(rpair.vtx + " -> " + rpair.psf + " @ " + rpair.wsf);

            isVisited[rpair.vtx] = true;
            for (Edge e : graph.get(rpair.vtx)) {
                if (!isVisited[e.v2]) {
                    pq.add(new DijisktraPair(e.v2, rpair.vtx, e.weight, rpair.wsf + e.weight, rpair.psf + e.v2));
                }
            }

        }

        System.out.println();
        display(DijisktraGraph);
    }

    public static void addEdge(ArrayList<ArrayList<Edge>> g, int v1, int v2, int weight) {
        g.get(v1).add(new Edge(v1, v2, weight));
        g.get(v2).add(new Edge(v2, v1, weight));
    }

    public static void display(ArrayList<ArrayList<Edge>> graph) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.size(); i++) {
            for (Edge e : graph.get(i)) {
                sb.append("(" + e.v1 + "," + e.v2 + ")" + "@" + e.weight + " ");
            }

            sb.append("\n");
        }

        System.out.print(sb.toString() + "\n");
    }

}