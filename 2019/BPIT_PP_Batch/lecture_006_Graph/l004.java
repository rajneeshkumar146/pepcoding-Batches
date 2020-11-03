import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;
public class l004{
    public static class Edge{
        int v = 0;
        int w = 0;

        Edge(int v,int w){
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph,int u,int v,int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static void display(int N,ArrayList<Edge>[] graph){
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

    public static void prims(int src,int N,ArrayList<Edge>[] graph){
        ArrayList<Edge>[] primsGraph = new ArrayList[N];
        for(int i=0;i<N;i++) primsGraph[i] = new ArrayList<>();

        PriorityQueue<primsPair> pq = new PriorityQueue<>();
        pq.add(new primsPair(src,-1,0));
        
        boolean[] vis = new boolean[N];

        int EdgeCount = 0;
        while(EdgeCount != N-1){
            primsPair rPair = pq.remove();
            
            //cycle check
            if(vis[rPair.vtx]) continue;

            if(rPair.par!=-1){
                addEdge(primsGraph,rPair.vtx,rPair.par,rPair.weight);
                EdgeCount++;
            }

            //mark
            vis[rPair.vtx] = true;

            // insert all unvisited nbrs
            for(Edge e: graph[rPair.vtx]){
                if(!vis[e.v])
                  pq.add(new primsPair(e.v,rPair.vtx,e.w));
            }

        }

        display(N,primsGraph);
    }

    public static void prims_02(int src,int N,ArrayList<Edge>[] graph){
        ArrayList<Edge>[] primsGraph = new ArrayList[N];
        for(int i=0;i<N;i++) primsGraph[i] = new ArrayList<>();
        
        boolean[] vis = new boolean[N];
        int[] costArray = new int[N];
        Arrays.fill(costArray,(int)1e9);

        PriorityQueue<primsPair> pq = new PriorityQueue<>();
        pq.add(new primsPair(src,-1,0));
        costArray[src] = 0;

        int EdgeCount = 0;
        while(EdgeCount != N-1){
            primsPair rPair = pq.remove();
            
            //cycle check
            if(vis[rPair.vtx]) continue;
            
            if(rPair.par!=-1){
                addEdge(primsGraph,rPair.vtx,rPair.par,rPair.weight);
                EdgeCount++;
            }

            //mark
            vis[rPair.vtx] = true;

            // insert all unvisited nbrs
            for(Edge e: graph[rPair.vtx]){
                if(!vis[e.v] && e.w < costArray[e.v]){
                  costArray[e.v] = e.w;
                  pq.add(new primsPair(e.v,rPair.vtx,e.w));
                }
            }
        }

        display(N,primsGraph);
    }

    //Dijikstra.======================================================
    public static class DijiPair implements Comparable<DijiPair>{
        int vtx = 0;
        int par = 0;
        int weight = 0;
        int wsf = 0;

        DijiPair(int vtx, int par,int weight,int wsf){
            this.vtx = vtx;
            this.par = par;
            this.weight = weight;
            this.wsf = wsf;
        }

        @Override
        public int compareTo(primsPair o){
            return this.wsf - o.wsf;
        }
    }

    public static void Dijikstra(int src,int N,ArrayList<Edge>[] graph){
        ArrayList<Edge>[] dijiGraph = new ArrayList[N];
        for(int i=0;i<N;i++) dijiGraph[i] = new ArrayList<>();

        boolean[] vis = new boolean[N];
        int[] costArray = new int[N];
        Arrays.fill(costArray,(int)1e9);

        PriorityQueue<dijiPair> pq = new PriorityQueue<>();
        pq.add(new dijiPair(src,-1,0,0));
        costArray[src] = 0;
        

        int EdgeCount = 0;
        while(EdgeCount != N-1){
            dijiPair rPair = pq.remove();
            
            //cycle check
            if(vis[rPair.vtx]) continue;

            if(rPair.par!=-1){
                addEdge(dijiGraph,rPair.vtx,rPair.par,rPair.weight);
                EdgeCount++;
            }

            //mark
            vis[rPair.vtx] = true;

            // insert all unvisited nbrs
            for(Edge e: graph[rPair.vtx]){
                if(!vis[e.v] && rPair.wsf + e.w < costArray[e.v]){
                    costArray[e.v] = rPair.wsf + e.w;
                    pq.add(new dijiPair(e.v,rPair.vtx,e.w,rPair.wsf + e.w));
                }
            }

        }

        display(N,dijiGraph);
    }

    //{u,v,w}
    public static void bellmanFord(int src,int N ,ArrayList<int[]> edges){
        int[] prevdis = new int[N];
        int[] currDis = new int[N];
        Arrays.fill(prevdis,(int)1e9);
        prevdis[src] = 0;

        int vtx = 1;
        while(vtx <= N){
            for(int i=0;i<N;i++) currDis[i] = prevdis[i];
            boolean isAnyUpdate = false;
            for(int[] e: edges){
                int u = e[0], v = e[1], w = e[2];
                if(prevdis[u] + w  < currDis[v]){
                    currDis[v] = prevdis[u] + w;
                    isAnyUpdate = true;
                } 
            }

            if(!isAnyUpdate) break;
            if(isAnyUpdate && vtx == N){
                System.out.println("Negative Cycle");
            }

            prevdis = currdis;
            vtx++;
        }
    }

    //{u,v,w}
    public static void bellmanFord2(int src,int N ,ArrayList<int[]> edges){
        int[] currDis = new int[N];
        Arrays.fill(currdis,(int)1e9);
        currdis[src] = 0;

        int vtx = 1;
        while(vtx <= N){
            boolean isAnyUpdate = false;
            for(int[] e: edges){
                int u = e[0], v = e[1], w = e[2];
                if(currdis[u] + w  < currDis[v]){
                    currDis[v] = currdis[u] + w;
                    isAnyUpdate = true;
                } 
            }

            if(!isAnyUpdate) break;
            if(isAnyUpdate && vtx == N){
                System.out.println("Negative Cycle");
            }

            vtx++;
        }
    }


    public static void solve(){    
        int N = 9;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for(int i=0;i<N;i++)
          graph[i] = new ArrayList<>();
        
        addEdge(graph,0,1,4);
        addEdge(graph,0,7,8);
        addEdge(graph,1,2,8);
        addEdge(graph,1,7,11);
        addEdge(graph,2,8,2);
        addEdge(graph,2,5,4);
        addEdge(graph,2,3,7);
        addEdge(graph,8,6,6);
        addEdge(graph,8,7,7);
        addEdge(graph,6,5,2);
        addEdge(graph,5,3,14);
        addEdge(graph,5,4,10);
        addEdge(graph,3,4,9);
        addEdge(graph,7,6,1);

        display(N,graph);
        
        prims_02(0,N,graph);
    }

    public static void main(String[] args){
        solve();
    }

}