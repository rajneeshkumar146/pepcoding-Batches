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
            if(vis[e] == 0){
               if(TopoDFS(e,vis,ans)) return true;
            }else if(vis[e] == 1) return true;
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

    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length; if(n==0) return 0;
        int m = matrix[0].length;if(m==0) return 0;
 
        int[][] indegree=new int[n][m];
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                for(int d=0;d<4;d++){
                    int x = i + dir[d][0];
                    int y = j + dir[d][1];
                    if(x>=0 && y>=0 && x < n && y < m && matrix[x][y] > matrix[i][j]) indegree[x][y]++;
                }
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
               if(indegree[i][j]==0) que.addLast(i*m+j);   

        int level = 0;
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int idx = que.removeFirst();
                int i = idx / m;
                int j = idx % m;
    
                for(int d=0;d<4;d++){
                    int x = i + dir[d][0];
                    int y = j + dir[d][1];
                    if(x>=0 && y>=0 && x < n && y < m && matrix[x][y] > matrix[i][j] && --indegree[x][y] == 0) que.addLast(x*m+y);
                }                
            }
            level++;
        }

        return  level;
    }

    //SCC.
    public static void DFS_SSC(int src,boolean[] vis,ArrayList<Integer> path){
        vis[src] = true;
        for(int e : graph[src])
            if(!vis[e])
               DFS_SSC(e,vis,path);
        
        path.add(src);
    }

    public static void DFS_SSC2(int src,ArrayList<Integer>[] ngraph,boolean[] vis){
        vis[src] = true;
        System.out.print(src+" ");

        for(int e : ngraph[src])
            if(!vis[e])
               DFS_SSC(e,vis,path);
    }

    //kosaraju algorithm
    public static void SCC(){

        //Topological Order.
        ArrayList<Integer> path = new ArrayList<>();
        boolean[] vis = new boolean[N];
        for(int i=0;i<N;i++){
            if(!vis[i])
               DFS_SSC(i,vis,path);
        }

        // Reverse graph.
        ArrayList<Integer>[] ngraph = new ArrayList[N];
        for(int i=0;i<N;i++) graph[i] = new ArrayList<>();

        for(int i=0;i<N;i++){
            for(int e: graph[i])
               ngraph[e].add(i);
        }

        //DFS over topologicalOrder.
        vis = new boolean[N];

        int count = 0;
        for(int i=path.size()-1;i>=0;i--){
            if(!vis[path.get(i)]){
                count++;
                DFS_SSC2(path.get(i),ngraph,vis);
            }
        }
    }


}