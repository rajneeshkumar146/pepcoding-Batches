import java.util.ArrayList;
import java.util.LinkedList;
public class l001{
    
    public static class Edge{
        int v = 0;
        int w = 0;

        Edge(int v,int w){
            this.v = v;
            this.w = w;
        }
    }

    static int N = 7;
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u,int v,int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static void display(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i < N;i++){
            sb.append(i + " -> ");
            for(Edge e: graph[i]){
                sb.append("(" + e.v + ", " + e.w +") ");
            }
            sb.append('\n');
        }

        sb.append('\n');
        System.out.println(sb.toString());
    }


    public static int findEdge(int u,int v){
        int idx = -1;
        for(int i=0;i<graph[u].size();i++){
            Edge e = graph[u].get(i);
            if(e.v == v){
                idx = i;
                break;
            }
        }

        return idx;
    }

    public static void removeEdge(int u,int v){
        int idx = findEdge(u , v);
        graph[u].remove(idx);

        idx = findEdge(v , u);
        graph[v].remove(idx);
    }

    public static void removeVtx(int u){
        while(graph[u].size()!=0){
            Edge e = graph[u].get(graph[u].size()-1);
            removeEdge(u,e.v);
        }
    }

    public static boolean hasPath(int src,int dest,boolean[] vis){
        if(src == dest) return true;

        vis[src]=true;
        
        boolean res = false;
        for(Edge e: graph[src]){
            if(!vis[e.v])
              res = res || hasPath(e.v,dest,vis);
        }

        return res;
    }

    public static int allPath(int src,int dest,boolean[] vis,int weight,String ans){
        if(src == dest){
            System.out.println(ans + src + " @ " + weight);
            return 1;
        }

        vis[src]=true;
        
        int count = 0;
        for(Edge e: graph[src]){
            if(!vis[e.v])
              count += allPath(e.v,dest,vis,weight + e.w,ans + src + " ");
        }

        vis[src] = false;

        return count;
    }

    public static class pair{
        int weight = 0;
        String path = "";

        pair(int weight, String path){
            this.weight = weight;
            this.path = path;
        }
    }

    public static pair heavyWeightPath(int src,int dest,boolean[] vis){
        if(src==dest){
            return new pair(0,src+"");
        }

        pair myAns = new pair(0,"");
        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v]){
                pair recAns = heavyWeightPath(e.v,dest,vis);
                if(myAns.weight < recAns.weight + e.w){
                    myAns.weight = recAns.weight + e.w;
                    myAns.path = src + " " + recAns.path;
                }
            }
        }

        vis[src] = false;
        return myAns;
    }

    public static int hamintonianPath(int src,int osrc,int vtxCount, boolean[] vis,String ans){
        if(vtxCount == N - 1){
            int idx = findEdge(src,osrc);
            if(idx!=-1)
                System.out.println("Cycle: " + ans + src);
            else
                System.out.println("Path: " + ans + src);
            
            return 1;
        }

        vis[src] = true;
        int count = 0;

        for(Edge e: graph[src]){
            if(!vis[e.v]){
                count += hamintonianPath(e.v,osrc,vtxCount+1,vis,ans + src + " ");
            }
        }

        vis[src] = false;
        return count;
    }

    public static int dfs(int src,boolean[] vis){
        vis[src] = true;
        int count = 0;
        for(Edge e: graph[src]){
            if(!vis[e.v])
             count += dfs(e.v,vis);
        }

        return count;
    }

    //get connected Components.
    public static int GCC(){
        int count = 0;
        boolean[] vis = new boolean[N];
        
        int area = 0;
        for(int i=0;i<N;i++){
            if(!vis[i]){
                count++;
                area += dfs(i,vis);
            }
        }

        return count;
    }

    //BFS.===================================================================

    public static void BFS_01(int src,boolean[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        while(que.size()!=0){
            int vtx = que.removeFirst();
            
            if(vis[vtx]){
                System.out.print("Cycle: " + vtx);
                continue;
            }

            vis[vtx] = true; // mark
            for(Edge e: graph[vtx]){
                if(!vis[e.v])
                  que.addLast(e.v);
            }
        }
    }

    public static void BFS_01(int src,boolean[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        
        int level=0;
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int vtx = que.removeFirst();
            
                if(vis[vtx])
                    continue;
                if(vtx == 6){
                    System.out.println(level);
                    break;
                }
    
                vis[vtx] = true; // mark
                for(Edge e: graph[vtx]){
                    if(!vis[e.v])
                      que.addLast(e.v);
                }
            }
            level++;
        }
    }

    public static void BFS_02(int src,boolean[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        vis[src] = true;
        
        int level=0;
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int vtx = que.removeFirst();
            
                if(vtx == 6){
                    System.out.println(level);
                    break;
                }
    
                for(Edge e: graph[vtx]){
                    if(!vis[e.v]){
                      que.addLast(e.v);
                      vis[e.v] = true; // mark
                    }
                }
            }
            
            level++;
        }
    }

    //Prims.===========================================================

    public static class primsPair implements Comparable<primsPair>{
        int vtx = 0;
        int par = 0;
        int weight = 0;

        primsPair(int vtx, int par,int weight){
            this.vtx = vtx;
            this.par = par;
            this.weight = weight;
        }

        @Override
        public int compareTo(primsPair o){
            return this.weight - o.weight;
        }
    }

    public static void prims(int src){

    }


    //AP Bridges.===================================================

    static int[] low;
    static int[] dis;
    static boolean[] AP;
    
    static boolean[] vis;
    static int timeCount = 0;
    static int rootCount = 0;
    

    public static void DFS_APB(int src,int par){
        low[src] = dis[src] = timeCount++;
        vis[src] = true;

        for(Edge e: graph[src]){
            if(!vis[e.v]){
                
                if(par == -1) rootCount++;
                
                dfs(e.v,src);

                if(dis[src] <= low[e.v]){
                    AP[src] = true;
                }
                
                if(dis[src] < low[e.v]){
                    System.out.println("ABridge: " + src + "->" + e.v);
                }

                low[src] = Math.min(low[src],low[e.v]);
            }else if(e.v != par){
                low[src] = Math.min(low[src],dis[e.v]);
            }
        }
    }

    public static APoint_Bridges(){
        low = new int[N];
        dis = new int[N];
        AP = new boolean[N];
        vis = new boolean[N];


        for(int i=0;i<N;i++){
            if(!vis[i]) {

                DFS_APB(i,-1);

                if(rootCount == 1) AP[i] = false;
                rootCount == 0;
            }
        }
    }







    public static void constructGraph(){
        for(int i=0;i<N;i++)
          graph[i] = new ArrayList<>();
        
        addEdge(0,1,10);
        addEdge(0,3,10);
        addEdge(1,2,10);
        addEdge(2,3,40);
        addEdge(3,4,2);
        addEdge(4,5,2);
        addEdge(5,6,3);
        addEdge(4,6,8);

        addEdge(0,6,1);
        addEdge(2,5,1);

        display();
    }

    public static void solve(){
        constructGraph();
        // removeVtx(3);

        boolean[] vis = new boolean[N];
        // System.out.println(allPath(0,6,vis,0,""));

        // pair p = heavyWeightPath(0,6,vis);
        // System.out.println(p.path + " @ "  + p.weight);
        

        // System.out.println(hamintonianPath(0,0,0,vis,""));
        
        
        BFS_01(0,vis);
        // display();
    }

    public static void main(String[] args){
        solve();
    }
}