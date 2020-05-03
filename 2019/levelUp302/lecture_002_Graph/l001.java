import java.util.ArrayList;
import java.util.LinkedList;

public class l001 {

	public static void main(String[] args) {
		solve();
	}

	public static class Edge {
		int v = 0;
		int w = 0;

		Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	public static int N = 7;
    public static ArrayList < Edge > [] graph;
   

	public static void addEdge(ArrayList < Edge > [] gp, int u, int v, int w) {
		gp[u].add(new Edge(v, w));
		gp[v].add(new Edge(u, w));
	}

	public static void display(ArrayList < Edge > [] gp) {

		for (int i = 0; i < gp.length; i++) {
			System.out.print(i + "-> ");

			for (Edge e: gp[i]) {
				System.out.print("(" + e.v + ", " + e.w + "), ");
			}
			System.out.println();
        }
        
        System.out.println();
	}

	public static class pair{
		int vtx;
		String psf;
		int level=0;

		pair(int vtx,String psf){
			this.vtx=vtx;
			this.psf=psf;
		}

		pair(int vtx,String psf,int level){
			this.vtx=vtx;
			this.psf=psf;
			this.level=level;
		}
	} 

	public static void BFS(int src,boolean[] vis){
		LinkedList<pair> que=new LinkedList<>();
		// LinkedList<int[]> que=new LinkedList<>();
		// que.addLast(new int[]{1,0});

		que.addLast(new pair(src,src+""));
		que.addLast(null);
		int desti=6;

		int level=0;
		while(que.size()!=1){
			pair rvtx=que.removeFirst();
			
			if(vis[rvtx.vtx]){
				System.out.println("Cycle: " + rvtx.psf);
				continue;
			}

			if(rvtx.vtx==desti){
				System.out.println("destinantion: " + rvtx.psf + " -> " + level);
			}
			
			vis[rvtx.vtx]=true;

			for(Edge e: graph[rvtx.vtx]){
				if(!vis[e.v])
				   que.addLast(new pair(e.v,rvtx.psf+ e.v));
			}

			if(que.getFirst()==null){
				level++;
				que.removeFirst();
				que.addLast(null);
			}
		}
	}

	public static void BFS_02(int src,boolean[] vis){
		LinkedList<pair> que=new LinkedList<>();
		que.addLast(new pair(src,src+"",0));
		int desti=6;

		int level=0;
		while(que.size()!=0){
			pair rvtx=que.removeFirst();
			
			if(vis[rvtx.vtx]){
				System.out.println("Cycle: " + rvtx.psf);
				continue;
			}

			if(rvtx.vtx==desti){
				System.out.println("destinantion: " + rvtx.psf + " -> " + rvtx.level);
			}
			
			vis[rvtx.vtx]=true;

			for(Edge e: graph[rvtx.vtx]){
				if(!vis[e.v])
				   que.addLast(new pair(e.v,rvtx.psf+ e.v, rvtx.level+1));
			}
		}
	}

	//leetcode 815.=======================================================
	public int numBusesToDestination(int[][] routes, int S, int T)
	{
		if (routes.length == 0)
			return -1;
		HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
		for (int i = 0; i < routes.length; i++)
		{
			for (int ele : routes[i])
			{
				map.putIfAbsent(ele,new ArrayList<>());
				map.get(ele).add(i);
			}
		}
	
		HashSet<Integer> busStandVis=new HashSet();
		boolean[] busVis=new boolean[routes.length];
	
		LinkedList<Integer> que=new LinkedList<>();
		que.addLast(S);
		int level = 0;
		busStandVis.add(S);
	
		while (que.size() != 0)
		{
			int size = que.size();
			while (size-- > 0)
			{
				int stand = que.removeFirst();
	
				if (stand == T)
					return level;
	
				for (int bus : map.get(stand))
				{
					if (busVis[bus])
						continue;
	
					for (int busStand : routes[bus])
					{
						if (!busStandVis.contains(busStand))
						{
							que.addLast(busStand);
							busStandVis.add(busStand);
						}
					}
					busVis[bus] = true;
				}
			}
			level++;
		}
	
		return -1;
	}





	public static void constructGraph() {
		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList < Edge > ();
		}

		addEdge(graph, 0, 1, 10);
		addEdge(graph, 0, 3, 10);
		addEdge(graph, 1, 2, 10);
		addEdge(graph, 2, 3, 40);
		addEdge(graph, 3, 4, 2);
		addEdge(graph, 4, 5, 2);
		addEdge(graph, 4, 6, 3);
        addEdge(graph, 5, 6, 8);
        
        display(graph);

	}

	public static void solve() {
		constructGraph();
		boolean[] vis=new boolean[N];
		// BFS(0,vis);
		BFS_02(0,vis);
        
    }

}