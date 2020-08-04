import java.util.ArrayList;
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

    static int N = 7;
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u, int v, int w) {
        graph[u].add(new Edge(v, w));
    }

    public static void display() {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static void dfs_topo(int src,boolean[] vis,ArrayList<Integer> st){
        vis[src]=true;
        for(Edge e: graph[src]){
            if(!vis[e.v]) dfs_topo(e.v,vis,st);
        }

        st.add(src);
    }

    public static void topologicalOrder(){
        boolean[] vis = new boolean[N];

        ArrayList<Integer> st=new Array:List<>();
        for(int i=0;i<N;i++){
            if(!vis[i])
              dfs_topo(i,vis,st);
        }

        for(int i=st.size()-1;i>=0;i--){
            System.out.println(st.get(i));
        }
    }

    public static void KahnsAlgo(){
        LinkedList<Integer> que=new LinkedList<>();
        int[] indegree=new int[N];

        for(int i=0;i<N;i++){
            for(Edge e: graph[i])
               indegree[e.v]++;
        }

        for(int i=0;i<N;i++)
            if(indegree[i]==0)que.addLast(i);
        
        ArrayList<Integer> ans=new ArrayList<>();
        while(que.size()!=0){
            int rvtx=que.removeFirst();
            ans.add(rvtx);

            for(Edge e: graph[rvtx]){
                if(--indegree[e.v]==0) que.addLast(e.v);
            }
        }

        if(ans.size()!=N) ans.clear();

        for(int ele: ans){
            System.out.print(ele);
        }
    }


    
    // ============================================================================

    public static void constructGraph() {
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 40);
        addEdge(3, 4, 2);
        addEdge(4, 5, 2);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);

        addEdge(2, 5, 10);

        display();

    }

    public static void solve() {
        boolean[] vis = new boolean[N];

    }

    public static void main(String[] args) {
        constructGraph();
        solve();

    }
