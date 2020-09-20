public class l002_directedGraph{
    static int N = 7;
    static ArrayList<Integer>[] graph = new ArrayList[N];

    public static void addEdge(int u,int v){
        graph[u].add(v);
    }

    public static void display(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i < N;i++){
            sb.append(i + " -> ");
            for(int e: graph[i]){
                sb.append(e +",");
            }
            sb.append('\n');
        }

        sb.append('\n');
        System.out.println(sb.toString());
    }

    public static void topoDFS(int src,boolean[] vis,ArrayList<Integer> ans){
        vis[src] = true;
        for(int e: graph[src]){
            if(!vis[e])
               topoDFS(e.v,vis,ans);
        }

        ans.add(src);
    }

    public static void topologicalOrder(){
        boolean[] vis = new boolean[N];
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0;i<N;i++){
            if(!vis[i]) topoDFS(i,vis,ans);
        }

    }

    //Kahn's Algo. for cycle dectection.

    public static void topologicalOrder(){
        int[] indegree= new int[N];
        for(int i=0;i<N;i++){
            for(int e: graph[i]) indegree[e]++;
        }

        ArrayDeque<Integer> que = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0;i<N;i++) if(indegree[i]==0) que.addLast(i);

        while(que.size()!=0){
            int vtx = que.removeFirst();
            ans.add(vtx);

            for(int e : graph[vtx]){
                if(--indegree[e]==0)
                   que.add(e); 
            }
        }

        if(ans.size()!=N) System.out.println("Cycle: ");
        else System.out.println(ans);
    }


}