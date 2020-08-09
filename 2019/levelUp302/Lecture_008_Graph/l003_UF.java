public class l003_UF{

    public static void addEdge(ArrayList<Integer>[] graph,int u, int v, int w) {
        graph[u].add(v);
        graph[v].add(u);
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
    
    public static void unionFind(int[][] edges,int N){
        ArrayList<Integer>[] graph=new ArryaList[N];
        for(int i=0;i<N;i++){
            graph[i]=new ArrayList<>();
        }
        
        par=new int[N];
        size=new int[N];
        
        for(int i=0;i<N;i++){
         par[i]=i;
         size[i]=1;    
        }
        
        for(int[] e: edges){
            int u=e[0];
            int v=e[1];

            int p1= findPar(u);
            int p2= findPar(v);

            if(p1!=p2){
                // merge(p1,p2);
                par[p1] = p2;  //merge
                addEdge(u,v);
            }
        }

        display(graph);
    }

    

    public static void main(String... args){
          
    }

}