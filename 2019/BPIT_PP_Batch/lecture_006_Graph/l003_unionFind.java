public class l003_unionFind{
     
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

    //=======================================================================

    static int[] par;
    static int[] size;

    public static int findPar(int u){
        if(u == par[u]) return u;
        return par[u] = findPar(par[u]);
    }

    public static void merge(int p1,int p2){
        if(size[p1] < size[p2]){
            par[p1] = p2;
            size[p2] += size[p1];
        }else{
            par[p2] = p1;
            size[p1] += size[p2];
        }

    }

    // {{u,v,w}}
    public static void unionFind(int[][] gp){  
        for(int i=0;i<N;i++) graph[i] = new ArrayList<>();
        
        Arrays.sort(gp,(a,b)->{
            return a[2]-b[2]; // this - other,default, Increasing
            // return b[2]-a[2]; // other - this, reverse of default, Decreasing
        });

        for(int i=0;i<N;i++){
            par[i] = i;
        }

        for(int[] a: gp){
            int u = a[0];
            int v = a[1];
            int w = a[2];

            int p1 = findPar(u);
            int p2 = findPar(v);

            if(p1 != p2){
                addEdge(u,v,w);
                merge(p1,p2);
            }
        }
    }
}