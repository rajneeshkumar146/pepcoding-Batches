import java.util.ArrayList;
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

        display();
    }

    public static void solve(){
        constructGraph();
        // removeVtx(3);

        boolean[] vis = new boolean[N];
        // System.out.println(allPath(0,6,vis,0,""));

        pair p = heavyWeightPath(0,6,vis);
        System.out.println(p.path + " @ "  + p.weight);
        
        // display();
    }

    public static void main(String[] args){
        solve();
    }
}