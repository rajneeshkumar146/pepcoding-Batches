import java.util.ArrayList;
import java.util.LinkedList;

public class l002_directedGraph{
    public static void main(String[] args) {
		solve();
	}

	public static int N = 8;
    public static ArrayList < Integer > [] graph;
    

	public static void display() {
		for (int i = 0; i < graph.length; i++) {
			System.out.print(i + "-> ");   // u

			for (Integer e: graph[i]) {
				System.out.print(e+ ", ");   //v
			}
			System.out.println();
        }
        
        System.out.println();
	}


    public static void constructGraph() {
		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList < Integer > ();
        }

        graph[7].add(5);
        graph[7].add(6);
        graph[5].add(4);
        graph[5].add(2);
        graph[6].add(4);
        graph[6].add(3);
        graph[2].add(1);
        graph[3].add(1);
        graph[1].add(0);
           
        display();
    }
    

    //topic.===============================================

    public static void topologiaclSort_(int src,boolean[] vis,ArrayList<Integer> ans){
        vis[src]=true;
        
        for(Integer e: graph[src])
            if(!vis[e])
              topologiaclSort_(e,vis,ans);
        
        ans.add(src);
    }

    public static void topologiaclSort(){
        boolean[] vis=new boolean[N];
        ArrayList<Integer> ans=new ArrayList<>();

        for(int i=0;i<N;i++){
            if(!vis[i])
            topologiaclSort_(i,vis,ans);
        }

        for(int i=ans.size()-1;i>=0;i--) System.out.print(ans.get(i)+ " ");
    }

    public static void KahnsAlgo(){
        int[] indegree=new int[N];
        for(int i=0;i<N;i++){
            for(Integer e: graph[i])
               indegree[e]++;
        }

        LinkedList<Integer> que=new LinkedList<>();
        for(int i=0;i<N;i++){
            if(indegree[i]==0) que.addLast(i);
        }

        ArrayList<Integer> ans=new ArrayList<>();

        while(que.size()!=0){
            int size=que.size();
            while(size--> 0){
                int rvtx=que.removeFirst();
                ans.add(rvtx);
      
                for(int e: graph[rvtx]){
                    if(--indegree[e]==0)
                       que.addLast(e);
                }
            }
        }

        if(ans.size()!=N) System.out.println("Cycle");
        else System.out.println(ans);
    }

    
    public static boolean topologiaclSortCycle_(int src,int[] vis,ArrayList<Integer> ans){ // faith: it will detect cycle.
        if(vis[src]==1) return true; //cycle.
        if(vis[src]==2) return false;// already visited vertex.

        vis[src]=1;
        boolean res=false;
        for(int e:graph[src])
            res=res || topologiaclSortCycle_(e,vis,ans);

        vis[src]=2;
        ans.add(src);
        return res;
    }

    public static void topologiaclSortCycle(){
        int[] vis=new int[N];
        ArrayList<Integer> ans=new ArrayList<>();
        
        boolean res=false;
        for(int i=0;i<N && !res;i++){
            if(vis[i]==0)
            res=res||topologiaclSortCycle_(i,vis,ans);
        }
        
        if(!res)
           for(int i=ans.size()-1;i>=0;i--) 
              System.out.print(ans.get(i)+ " ");
        else 
           System.out.print("Cycle");
    }

    public static int DFS_SCC(int src,ArrayList<Inrteger>[] ngraph,boolean[] vis,ArrayList<Integer> ans){
       vis[src]=true;
       int count=0;
       for(int e: ngraph[src]){
           if(!vis[e]){
            count+=DFS_SCC(e,ngraph,vis,ans);
           }
       }
       ans.add(src);
       return count+1;
    }

    public static void SCC(){
        boolean[] vis=new boolean[N];
        ArrayList<Integer> ans=new ArrayList<>();

        for(int i=0;i<N;i++){
            if(!vis[i])
            topologiaclSort_(i,vis,ans);
        }

        ArrayList<Inrteger>[] ngraph=new ArrayList[N];
        for(int i=0;i<N;i++) ngraph[i]=new ArrayList<>();
        
        for(int i=0;i<N;i++){
            for(int ele:graph[i]){
                ngraph[ele].add(i);
            }
        }

       vis=new Boolean[N];

       for(int i=ans.size()-1;i>=0;i--){
           if(!vis[ans.get(i)]){
            ArrayList<Integer> ans_=new ArrayList<>();
            System.out.println(DFS_SCC(ans.get(i),ngraph,vis,ans_));
            System.out.println(ans_);
           }
       }
    }



	public static void solve() {
        constructGraph();
        // topologiaclSort();
        KahnsAlgo();
    }


}