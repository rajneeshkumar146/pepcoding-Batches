import java.util.*;

public class question {

    public boolean isBipartite(int[][] graph, int src, int[] vis) {
        // -1 -> undefine, 0 -> red, 1 -> green.

        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        int color = 0;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.removeFirst();
                if (vis[rvtx] != -1) {
                    if (vis[rvtx] != color) // conflict
                        return false;

                    continue;
                }

                vis[rvtx] = color;
                for (int v : graph[rvtx]) {
                    if (vis[v] == -1) {
                        que.addLast(v);
                    }
                }
            }

            color = (color + 1) % 2;
        }

        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int N = graph.length;
        int[] vis = new int[N];
        Arrays.fill(vis, -1);

        for (int i = 0; i < N; i++) {
            if (vis[i] == -1 && !isBipartite(graph, i, vis))
                return false;
        }

        return true;
    }

    public static int spreadInfection(int src, Int timeLimit) {
        LinkedList<Integer> que = new LinkedList<>();
        boolean[] vis = new boolean[N];
        que.addLast(src);
        int time = 0, infectedCount = 1;
        vis[src] = true;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.removeFirst();

                for (Edge e : graph[rvtx]) {
                    if (!vis[e.v]) {
                        vis[e.v] = true;
                        que.add(e.v);
                        if (time + 1 < timeLimit)
                            infectedCount++;
                        else
                            return infectedCount;
                    }

                }
            }

            time++;
        }

        return infectedCount;
    }

}