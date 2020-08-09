import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;

public class l004_Algos{
    public static class Edge {
        int v, w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static int N = 7;
    static ArrayList<Edge>[] actualgraph = new ArrayList[N];

    public static void addEdge(ArrayList<Integer>[] graph,int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display(ArrayList<Integer>[] graph) {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    static int[] size;
    public static void merge(int p1,int p2){
        if(size[p1]<size[p2]){
            par[p1]=p2;
            size[p2]+=size[p1];
        }else{
            par[p2]=p1;
            size[p1]+=size[p2];
        }
    }

    static int[] par;
    public static int findPar(int u){
        if(par[u]==u) return u;
        return par[u]=findPar(par[u]);
    }
    
    public static void Kruskal(int[][] edges,int N){
        ArrayList<Integer>[] graph=new ArryaList[N];
        for(int i=0;i<N;i++){
            graph[i]=new ArrayList<>();
        }

        // sort(edges.begin(),edges.end(),[](auto& a,auto& b){   // cpp
        //     return a[2] < b[2];
        // });

        Arrays.sort(edges,(a,b)->{
            return a[2] - b[2]; // this-other. 
        });
        
        par=new int[N];
        size=new int[N];
        
        for(int i=0;i<N;i++){
         par[i]=i;
         size[i]=1;    
        }
        
        for(int[] e: edges){
            int u = e[0];
            int v = e[1];
            int w = e[2];

            int p1= findPar(u);
            int p2= findPar(v);

            if(p1!=p2){
                // merge(p1,p2);
                par[p1] = p2;  //merge
                addEdge(graph,u,v,w);
            }
        }

        display(graph);
    }

    public static class primsPair{
        int u;
        int par;
        int weight;

        primsPair(int u,int par,int weight){
            this.u=u;
            this.par=par;
            this.weight=weight;
        }
    }


    public static void prims(int src,boolean[] vis){
        ArrayList<Integer>[] graph=new ArryaList[N];
        for(int i=0;i<N;i++){
            graph[i]=new ArrayList<>();
        }

        PriorityQueue<primsPair> pq=new PriorityQueue<>((a,b)->{
            return a.weight - b.weight; 
        });

        pq.add(new primsPair(src,-1,0));
        int NoOfEdges = 0;

        while(NoOfEdges != N-1){
            primsPair pair=pq.remove();
            
            if(vis[pair.u]) contniue;
            if(pair.par!=-1){
                addEdge(graph,pair.u.pair.par,pair.w);
                NoOfEdges++;
            }

            vis[pair.u]=true;
            for(Edge e: actualgraph[pair.u]){
                if(!vis[e.v]){
                    pq.add(new primsPair(e.v,pair.u,e.w));
                }
            }
        }
    }

    public static void prims_02(int src,boolean[] vis){
        int[] cost=new int[N];
        Arrays.fill(cost,(int)1e9);

        PriorityQueue<primsPair> pq=new PriorityQueue<>((a,b)->{
            return a.weight - b.weight; 
        });

        pq.add(new primsPair(src,-1,0));
        cost[src]=0;

        int NoOfEdges = 0;

        while(NoOfEdges != N-1){
            primsPair pair=pq.remove();
            if(vis[pair.u]) contniue;

            if(pair.par!=-1){
                addEdge(graph,pair.u.pair.par,pair.w);
                NoOfEdges++;
            }

            vis[pair.u]=true;
            for(Edge e: actualgraph[pair.u]){
                if(e.w < cost[e.v] && !vis[e.v]){
                    cost[e.v] = e.w;
                    pq.add(new primsPair(e.v,pair.u,e.w));
                }
            }
        }
    }

    public static class dijikstraPair{
        int u;
        int par;
        int weight;
        int wsf;

        dijikstraPair(int u,int par,int weight,int wsf){
            this.u=u;
            this.par=par;
            this.weight=weight;
            this.wsf=wsf;
        }
    }

    public static void dijikstra(int src,boolean[] vis){
        ArrayList<Integer>[] graph=new ArryaList[N];
        for(int i=0;i<N;i++){
            graph[i]=new ArrayList<>();
        }

        PriorityQueue<dijikstraPair> pq=new PriorityQueue<>((a,b)->{
            return a.wsf - b.wsf; 
        });

        pq.add(new dijikstraPair(src,-1,0,0));
        int NoOfEdges = 0;

        while(NoOfEdges!=N-1){
            dijikstraPair pair=pq.remove();
            
            if(vis[pair.u]) contniue;
            if(pair.par!=-1){
                addEdge(graph,pair.u.pair.par,pair.w);
                NoOfEdges++;
            }

            vis[pair.u]=true;
            for(Edge e: actualgraph[pair.u]){
                if(!vis[e.v]){
                    pq.add(new dijikstraPair(e.v, pair.u, e.w, pair.wsf + e.w));
                }
            }
        }
    }

    public static void dijikstra_02(int src,boolean[] vis){
        ArrayList<Integer>[] graph=new ArryaList[N];
        for(int i=0;i<N;i++){
            graph[i]=new ArrayList<>();
        }

        PriorityQueue<dijikstraPair> pq=new PriorityQueue<>((a,b)->{
            return a.wsf - b.wsf; 
        });

        int[] costSoFar=new int[N];
        pq.add(new dijikstraPair(src,-1,0,0));
        costSoFar[src] = 0;
        int NoOfEdges = 0;


        while(NoOfEdges != N-1){
            dijikstraPair pair=pq.remove();
            
            if(vis[pair.u]) contniue;
            if(pair.par!=-1){
                addEdge(graph,pair.u.pair.par,pair.w);
                NoOfEdges++;
            }

            vis[pair.u]=true;
            for(Edge e: actualgraph[pair.u]){
                if(!vis[e.v] && pair.wsf + e.w < costSoFar[e.v]){
                    costSoFar[e.v] = pair.wsf + e.w;
                    pq.add(new dijikstraPair(e.v, pair.u, e.w, pair.wsf + e.w));
                }
            }
        }
    }
}