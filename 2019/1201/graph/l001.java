import java.util.ArrayList;
import java.util.PriorityQueue;

public class l001{

    static class edge {
		int u=0;
		int v=0;
		int wt=0;

		edge(int a, int b, int wt) {
			this.u = a;
			this.v = b;
			this.wt = wt;
		}
	}

	static void addEdge(ArrayList<ArrayList<edge>> graph, int a, int b, int wt) {
		graph.get(a).add(new edge(a, b, wt));
		graph.get(b).add(new edge(b, a, wt));
    }
    
    public static class dpair{
        int u=0;
		int par=0;
        int wt=0;
        int wsf=0;  

		dpair(int a, int b, int wt,int wsf) {
			this.u = a;
			this.par = b;
            this.wt = wt;
            this.wsf=wsf;
        }
    } 

    public static void dijikstraAlgo(ArrayList<ArrayList<edge>> graph,int src){
        ArrayList<ArrayList<edge>> myGraph=new ArrayList<>();
        for(int i=0;i<graph.size();i++) myGraph.add(new ArrayList<edge>());

        PriorityQueue<dpair> que=new PriorityQueue<>((dpair a,dpair b)-> {
            return a.wsf-b.wsf; // min PQ on behalf of wsf
            // return b.wsf-a.wsf; // max PQ on behalf of wsf
        });

        boolean[] vis=new boolean[graph.size()];
        que.add(new dpair(src,-1,0,0));

        while(que.size()!=0){
            dpair pair=que.remove();
            
            if(vis[pair.u]) continue;   //case of cycle.
            vis[pair.u]=true;    //mark

            if(pair.par!=-1){
                addEdge(myGraph,pair.u,pair.par,pair.wt);
            }

            for(edge e: graph.get(pair.u)){
                if(!vis[e.v]){
                    que.add(new dpair(e.v,pair.u,e.wt,pair.wsf+e.wt));
                }
            }
        }


        display(myGraph);
    }

    public static void primsAlgo(ArrayList<ArrayList<edge>> graph,int src){
        ArrayList<ArrayList<edge>> myGraph=new ArrayList<>();
        for(int i=0;i<graph.size();i++) myGraph.add(new ArrayList<edge>());

        PriorityQueue<dpair> que=new PriorityQueue<>((dpair a,dpair b)-> {
            return a.wt-b.wt; // min PQ on behalf of wt
        });

        boolean[] vis=new boolean[graph.size()];
        que.add(new dpair(src,-1,0,0));

        while(que.size()!=0){
            dpair pair=que.remove();
            
            if(vis[pair.u]) continue;   //case of cycle.
            vis[pair.u]=true;    //mark

            if(pair.par!=-1){
                addEdge(myGraph,pair.u,pair.par,pair.wt);
            }

            for(edge e: graph.get(pair.u)){
                if(!vis[e.v]){
                    que.add(new dpair(e.v,pair.u,e.wt,pair.wsf+e.wt));
                }
            }
        }


        display(myGraph);
    }



static void display(ArrayList<ArrayList<edge>> graph){
		
		for(int cv = 0 ; cv < graph.size() ; cv++){
			System.out.print(cv + " -> ");
			for(int ce = 0; ce< graph.get(cv).size(); ce++){
				edge e = graph.get(cv).get(ce);
				System.out.print( "(" + e.v + "," + e.wt +"), "  );
			}
			System.out.println();
        }
        
        System.out.println();
		
    }
    
    
    public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList< ArrayList< edge > > graph = new ArrayList<>();
		for(int i = 0 ; i <= n ; i++ ){
			graph.add(new ArrayList<edge>());
        }

        for(int i=0;i<pipes.length;i++){
            int u=pipes[i][0];
            int v=pipes[i][1];
            int w=pipes[i][2];
            addEdge(graph,u,v,w);
        }

        for(int i=0;i<wells.length;i++){
            int u=0;
            int v=i;
            int w=wells[i];
            addEdge(graph,u,v,w);
        }

        int ans=0;

        PriorityQueue<dpair> que=new PriorityQueue<>((dpair a,dpair b)-> {
            return a.wt-b.wt; // min PQ on behalf of wt
        });

        boolean[] vis=new boolean[graph.size()];
        que.add(new dpair(src,-1,0,0));

        while(que.size()!=0){
            dpair pair=que.remove();
            
            if(vis[pair.u]) continue;   //case of cycle.
            vis[pair.u]=true;    //mark

            ans+=pair.wt;
            
            for(edge e: graph.get(pair.u)){
                if(!vis[e.v]){
                    que.add(new dpair(e.v,pair.u,e.wt,pair.wsf+e.wt));
                }
            }
        }

        return ans;
    }
	
	public static void solve() {
		ArrayList< ArrayList< edge > > graph = new ArrayList<>();
		for(int i = 0 ; i < 7 ; i++ ){
			graph.add(new ArrayList<edge>());
        }
        
        addEdge(graph,0,1,2);
        addEdge(graph,0,3,10);
        addEdge(graph,1,2,3);
        addEdge(graph,3,2,7);
        addEdge(graph,3,4,2);
        addEdge(graph,4,5,2);
        addEdge(graph,4,6,3);
        addEdge(graph,5,6,8);

        display(graph);
        dijikstraAlgo(graph,2);
        primsAlgo(graph,2);
	}

    public static void main(String[] args){
     solve();
    }

    public static void dfs(int src,boolean[]){
        vis[src]=true;
        for(edge e: graph.get(src))
          if(!vis[e.v])
            dfs(e.v,vis);
    }
    
    public static void bfs(int src,boolean[]){
        LinkedList<Integer> que=new LinkedList<>();
        que.addLast(src);

        while(que.size()!=0){
            int vtx=que.removeFirst();
            vis[vtx]=true;
            for(edge e: graph.get(src))
               if(!vis[e.v])
                que.addLast(e.v);

        }
    }
}