import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class dijkstraAndPrims{

    public static class Edge{
        int v = 0;
        int w = 0;

        public Edge(int v,int w){
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph,int u,int v,int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static void display(ArrayList<Edge>[] graph,int N){
        for(int i=0;i<N;i++){
            System.out.print(i + " -> ");
            for(Edge e: graph[i]){
                System.out.print("(" + e.v + ", " + e.w + "), ");
            }

            System.out.println();
        }
    }

    public class primsPair{
        int par = 0;
        int vtx = 0;
        int w = 0;

        primsPair(int vtx,int par,int w){
            this.par = par;
            this.vtx = vtx;
            this.w = w;
        }
    }

    public static void primsAlgo(ArrayList<Edge>[] graph,int N){
        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for(int i=0;i<N;i++) ngraph[i] = new ArrayList<>();

        boolean[] vis = new boolean[N];
        PriorityQueue<primsPair> que = new PriorityQueue<>((a,b)->{
            return a.w - b.w; // this - other
        });

        que.add(new primsPair(0,-1,0));

        int EdgesCount = 0;
        while(EdgesCount <= N - 1){
            primsPair pair = que.remove();
            
            if(vis[pair.vtx]) continue;
            
            if(pair.par != -1){
                addEdge(ngraph,pair.par,pair.vtx,pair.w);
                EdgesCount++;
            }

            vis[pair.vtx] = true;
            for(Edge e: graph[pair.vtx]){
                if(!vis[e.v]){
                    que.add(new primsPair(e.v,pair.vtx,e.w));
                }
            }
        }

        display(ngraph,N);
    }

    public static void primsAlgo(ArrayList<Edge>[] graph,int N){
        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for(int i=0;i<N;i++) ngraph[i] = new ArrayList<>();

        boolean[] vis = new boolean[N];
        int[] dis = new int[N];
        Arrays.fill(dis,(int)1e8);

        PriorityQueue<primsPair> que = new PriorityQueue<>((a,b)->{
            return a.w - b.w; // this - other
        });

        que.add(new primsPair(0,-1,0));
        dis[0] = 0;

        int EdgesCount = 1;
        while(EdgesCount <= N - 1){
            primsPair pair = que.remove();
            
            if(vis[pair.vtx]) continue;
            
            if(pair.par != -1){
                addEdge(ngraph,pair.par,pair.vtx,pair.w);
                EdgesCount++;
            }

            vis[pair.vtx] = true;
            for(Edge e: graph[pair.vtx]){
                if(!vis[e.v] && e.w < dis[e.v]){
                    dis[e.v] = e.w;
                    que.add(new primsPair(e.v,pair.vtx,e.w));
                }
            }
        }
        
        display(ngraph,N);
    }


    public class dijkstraPair{
        int vtx = 0;
        int par = 0;
        int wsf = 0;

        dijkstraPair(int vtx,int par,int wsf){
            this.vtx = vtx;
            this.par = par;
            this.wsf = wsf;
        }
    }

    public static void DijkstraAlgo(ArrayList<Edge>[] graph,int N){
        boolean[] vis = new boolean[N];
        int[] dis = new int[N];
        int[] par = new int[N];

        Arrays.fill(dis,(int)1e8);
        Arrays.fill(par,-1)

        PriorityQueue<dijkstraPair> que = new PriorityQueue<>((a,b)->{
            return a.wsf - b.wsf; // this - other
        });

        que.add(new dijkstraPair(0,-1,0));
        dis[0] = 0;

        int EdgesCount = 1;
        while(EdgesCount <= N - 1){
            dijkstraPair pair = que.remove();
            
            if(vis[pair.vtx]) continue;
            if(pair.par != -1) EdgesCount++;

            vis[pair.vtx] = true;
            par[pair.vtx] = pair.par;

            for(Edge e: graph[pair.vtx]){
                if(!vis[e.v] && pair.wsf + e.w < dis[e.v]){
                    dis[e.v] = pair.wsf + e.w;
                    que.add(new primsPair(e.v,pair.vtx,pair.wsf + e.w));
                }
            }
        }
    }

    // edge = {{u,v,w}....}
    public static void bellmanFordAlgo(int[][] edges,int N,int src){
        int[] dis = new int[N];
        Arrays.fill(dis,(int)1e8);
        dis[src] = 0;
        boolean negativeCycle = false;

        for(int i=1;i <= N;i++){
            
            boolean isAnyUpdate = false;
            
            int[] ndis = new int[N];
            for(int i=0;i<N;i++) ndis[i] = dis[i];

            for(int[] e : edges){
                if(dis[e[0]]!= (int)1e8 && dis[e[0]] + e[2] < ndis[e[1]]){
                    ndis[e[1]] = dis[e[0]] + e[2];
                    isAnyUpdate = true;
                }
            }

            dis = ndis;


            if(isAnyUpdate && i == N) negativeCycle = true;
            if(!isAnyUpdate && i < N) break;
        }
    }

    public static void bellmanFordAlgo_02(int[][] edges,int N,int src){
        int[] dis = new int[N];
        Arrays.fill(dis,(int)1e8);
        dis[src] = 0;
        boolean negativeCycle = false;

        for(int i=1;i <= N;i++){
            boolean isAnyUpdate = false;
            
            for(int[] e : edges){
                if(dis[e[0]]!= (int)1e8 && dis[e[0]] + e[2] < dis[e[1]]){
                   dis[e[1]] = dis[e[0]] + e[2];
                   isAnyUpdate = true;
                }
            }


            if(isAnyUpdate && i == N) negativeCycle = true;
            if(!isAnyUpdate && i < N) break;
        }
    }






}