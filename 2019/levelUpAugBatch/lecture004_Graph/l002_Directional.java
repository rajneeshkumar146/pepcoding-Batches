import java.util.ArrayList;
import java.util.LinkedList;

public class l002_Directional{
    static int N = 7;
    
    @SuppressWarnings("unchecked")
    static ArrayList<Integer>[] graph = new ArrayList[N];
    public static void addEdge(int u,int v,int w){
        graph[u].add(v);
    }

    public static void display(){
        for(int i=0;i<N;i++){
            System.out.print(i + " -> ");
            for(Integer e: graph[i]){
                System.out.print(e + ", ");
            }

            System.out.println();
        }
    }


    // Using DFS.==========================================

    public static void TopoDFS(int src,boolean[] vis,ArrayList<Integer> ans){
        vis[src] = true;
        for(Integer e : graph[src]){
            if(!vis[e])
               TopoDFS(e,vis,ans);
        }

        ans.add(src);
    }

    public static void TopoDFS(){
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[N];

        for(int i = 0;i<N;i++){
            if(!vis[i]){
                TopoDFS(i,vis,ans);
            }
        }
    }

    //BFS.=============================================================
    public static void TopoBFS(){
        ArrayList<Integer> ans = new ArrayList<>();
        
        int[] indegree = new int[N];
        for(int i=0;i<N;i++){
            for(int e : graph[i])
               indegree[e]++;
        }

        LinkedList<Integer> que = new LinkedList<>();
        for(int i=0;i<N;i++) if(indegree[i]==0) que.addLast(i);

        while(que.size()!=0){
            int vtx = que.removeFirst();
            ans.add(vtx);

            for(int e : graph[vtx]){
                if(--indegree[e] == 0) que.addLast(e);
            }
        }

        if(ans.size() != N){
            System.out.println("Cycle");
        }else{
            System.out.println(ans);
        }
    }

    // Topo DFS Cycle.==============================================

    // try to find a cycle, is found return true otherwise return false.
    public static boolean TopoDFS(int src,int[] vis,ArrayList<Integer> ans){
        vis[src] = 1;
        for(Integer e : graph[src]){
            if(vis[e] == 0)
               if(TopoDFS(e,vis,ans)) return true;
            else if(vis[e] == 1) return true;
        }

        ans.add(src);
        vis[src] = 2;
        return  false;
    }

    // 0 -> not visited, 1 -> part of path, 2 -> visited and not a part of path.
    public static void TopoDFS(){
        ArrayList<Integer> ans = new ArrayList<>();
        int[] vis = new int[N];

        boolean isCycleFound = false;
        for(int i = 0;i<N;i++){
            if(vis[i] == 0){
                if(TopoDFS(i,vis,ans)){
                    isCycleFound = true;
                    break;
                }
            }
        }

        if(!isCycleFound) System.out.println(ans);
        else System.out.println("Cycle"); 
    }



}