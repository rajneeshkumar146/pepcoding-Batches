import java.io.*;
import java.util.*;

class Result {
    public long dfs(ArrayList<Integer>[] graph, boolean[] vis, int src) {
        vis[src] = true;
        long count = 0;
        for (Integer e : graph[src]) {
            if (!vis[e])
                count += dfs(graph, vis, e);
        }

        return count + 1;
    }

    public long journeyToMoon(ArrayList<Integer>[] graph, int N) {
        boolean[] vis = new boolean[N];
        ArrayList<Long> population = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                population.add(dfs(graph, vis, i));
            }
        }

        long ans = 0;
        long ssf = 0; // sum so far

        for (int i = population.size() - 1; i >= 0; i--) {
            long ele = population.get(i);
            ans += ssf * ele;

            ssf += ele;
        }

        return ans;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        int Edges = scn.nextInt();
        for (int i = 0; i < Edges; i++) {
            int a = scn.nextInt(), b = scn.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        Result obj = new Result();
        long ans = obj.journeyToMoon(graph, N);
        System.out.println(ans);

    }
}
