public class l002_directedGraph{
    static int N = 7;
    static ArrayList<Integer>[] graph = new ArrayList[N];

    public static void addEdge(int u,int v){
        graph[u].add(v);
    }

    public static void display(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i < N;i++){
            sb.append(i + " -> ");
            for(int e: graph[i]){
                sb.append(e +",");
            }
            sb.append('\n');
        }

        sb.append('\n');
        System.out.println(sb.toString());
    }

    public static void topoDFS(int src,boolean[] vis,ArrayList<Integer> ans){
        vis[src] = true;
        for(int e: graph[src]){
            if(!vis[e])
               topoDFS(e.v,vis,ans);
        }

        ans.add(src);
    }

    public static void topologicalOrder(){
        boolean[] vis = new boolean[N];
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0;i<N;i++){
            if(!vis[i]) topoDFS(i,vis,ans);
        }

    }

    //Kahn's Algo. for cycle dectection.

    public static void topologicalOrder(){
        int[] indegree= new int[N];
        for(int i=0;i<N;i++){
            for(int e: graph[i]) indegree[e]++;
        }

        ArrayDeque<Integer> que = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0;i<N;i++) if(indegree[i]==0) que.addLast(i);

        while(que.size()!=0){
            int vtx = que.removeFirst();
            ans.add(vtx);

            for(int e : graph[vtx]){
                if(--indegree[e]==0)
                   que.add(e); 
            }
        }

        if(ans.size()!=N) System.out.println("Cycle: ");
        else System.out.println(ans);
    }

    int[] par;
    public int findPar(int u){
        if(par[u] == u) return u;
        return par[u] = findPar(par[u]);
    }
   
    //Leetcode 200
    public int numIslands(char[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        
        par = new int[n*m];
        int count=0;
        for(int i=0;i<n*m;i++){
            par[i] = i;
            if(grid[i/m][i%m]=='1') count++;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == '1'){
                    int p1 = findPar(i*m+j);
                    if(j+1 < m && grid[i][j+1] == '1'){
                        int p2 = findPar(i*m+j+1);
                        if(p1 != p2){
                            par[p2] = p1;
                            count--;
                        }
                    }

                    if(i+1 < n && grid[i+1][j] == '1'){
                        int p2 = findPar((i+1)*m+j);
                        if(p1 != p2){
                            par[p2] = p1;
                            count--;
                        }
                    }
                }
            }
        }
        return count;
    }

    public String smallestEquivalentString(String A, String B, String S) {
        par = new int[26];
        for(int i=0;i<26;i++) par[i] = i;
        
        for(int i=0;i<A.length();i++){
            int p1 = findPar(A.charAt(i)-'a');
            int p2 = findPar(B.charAt(i)-'a');
            
            par[p1] = Math.min(p1,p2);
            par[p2] = Math.min(p1,p2);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<S.length();i++){
            int ch = findPar(S.charAt(i)-'a');
            sb.append((char)(ch+'a'));
        }


        return sb.toString();
    }

    //Leetcode 684
    public int[] findRedundantConnection(int[][] edges) {
        int N=edges.length;
        par=new int[N+1];
        for(int i=0;i<=N;i++)
           par[i]=i;
        
        int[] ans = null;
        for(int[] e: edges){
            int p1 = findPar(e[0]);
            int p2 = findPar(e[1]);

            if(p1!=p2)
                par[p1] = p2;
            else
               {
                   ans = e;
                   break;
               }
        }

        return ans;
    }

    //Leetcode 839
    public boolean isSimilar(String a,String b){
        int count = 0;
        for(int i=0;i<a.length();i++){
            if(a.charAt(i) != b.charAt(i) && ++count > 2) return false;
        }

        return true;
    }
    
    public int numSimilarGroups(String[] A) {
        int n = A.length();
        
        par = new int[n];
        for(int i=0;i<n;i++) par[i] = i;
        
        int count = n;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(isSimilar(A[i],A[j])){
                    int p1 = findPar(i);
                    int p2 = findPar(j);

                    if(p1 != p2){
                        par[p1] = p2;
                        count--;
                    }
                }
            }
        }

        return count;
    }



}