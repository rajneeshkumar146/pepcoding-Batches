public class questions{
    
    //Leetcode 200    
    int n=0,m=0;
    int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    public void dfsIsland(int i,int j,char[][] grid){    
        
        grid[i][j]='0';
        for(int d=0;d<dir.length;d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            
            if(r>=0 && c>=0 && r<n && c<m&& grid[r][c]=='1')
                dfsIsland(r,c,grid);
        }
    }
    
    public int numIslands(char[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        
        n = grid.length;
        m = grid[0].length;
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]=='1'){
                    count++;
                    dfsIsland(i,j,grid);
                }
            }
        }
        
        return count;
    }

    //Leetcode 695
    public int dfsIsland(int i,int j,int[][] grid){    
        
        grid[i][j]=0;
        int count=0;
        for(int d=0;d<dir.length;d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            
            if(r>=0 && c>=0 && r<n && c<m&& grid[r][c]==1)
                count+=dfsIsland(r,c,grid);
        }
        
        return count + 1;
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        
        n = grid.length;
        m = grid[0].length;
        int area=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    
                    area=Math.max(area,dfsIsland(i,j,grid));
                }
            }
        }
        
        return area;
    }

    //Leetcode 463
    public int islandPerimeter(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
      
      int n = grid.length;
      int m = grid[0].length;
      int count=0,nbrs=0;
      for(int i=0;i<n;i++){
          for(int j=0;j<m;j++){
              if(grid[i][j] == 1){
               count++;
                for(int d=0;d<dir.length;d++){
                  int r = i + dir[d][0];
                  int c = j + dir[d][1];
          
                if(r>=0 && c>=0 && r<n && c<m&& grid[r][c]==1)
                  nbrs++;
              }
            }
        }
      }
      
      return 4*count-nbrs; 
    }

    //Leetcode 130

    public void SurroundedRegion(char[][] grid, int sr, int sc, int[][] dir) {
        grid[sr][sc] = '$';
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 'O')
                SurroundedRegion(grid, r, c, dir);
        }

    }

    public void solve(char[][] board) {

        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1)
                    if (board[i][j] == 'O')
                        SurroundedRegion(board, i, j, dir);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '$')
                    board[i][j] = 'O';
            }
        }
    }

    //Leetcode 785
    public boolean isBipartite(int[][] graph,int src,int[] prevColor,int N){
        // 0 : Red, 1 : Green
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.addLast(src);
        int color = 0;
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                
                int vtx =  que.removeFirst();
                
                
                if(prevColor[vtx] !=-1 && prevColor[vtx] != color){
                    return false;
                }
                
                prevColor[vtx] = color;
                for(int e : graph[vtx]){
                    if(prevColor[e] == -1){
                        que.addLast(e);
                    }
                }
            }
            color = (color + 1) % 2;
        }
        
        return true;   
    }
    
    
    public boolean isBipartite(int[][] graph) {
        int N = graph.length;
        int[] prevColor = new int[N];
        Arrays.fill(prevColor,-1);
        
        for(int i=0;i<N;i++){
            if(prevColor[i]==-1){
                if(!isBipartite(graph,i,prevColor,N)) return false ;
            }
        }
        
        return true;
    }

    //Leetcode 994
    //Leetcode 994
    public int orangesRotting(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        
        ArrayDeque<int[]> que=new ArrayDeque<>();
        int freshOranges = 0;
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1) freshOranges++;
                else if(grid[i][j]==2) que.addLast(new int[]{i,j});
            }
        }
        
        if(freshOranges == 0) return 0;
        int time = 0;
        
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int[] rvtx = que.removeFirst();
                int r = rvtx[0];
                int c = rvtx[1];
                
                for(int d=0;d<4;d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    
                    if(x>=0 && y>=0 && x< n && y< m && grid[x][y]==1){
                        grid[x][y] = 2;
                        freshOranges--;
                        que.addLast(new int[]{x,y});
                        
                        if(freshOranges==0) return time + 1;
                    }
                }
            }
            
            time++;
        }
        
        return -1;
    }

    public int orangesRotting(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        
        ArrayDeque<Integer> que=new ArrayDeque<>();
        int freshOranges = 0;
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1) freshOranges++;
                else if(grid[i][j]==2) que.addLast(i*m+j);
            }
        }
        
        if(freshOranges == 0) return 0;
        int time = 0;
        
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int rvtx = que.removeFirst();
                int r = rvtx / m;
                int c = rvtx%m;
                
                for(int d=0;d<4;d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    
                    if(x>=0 && y>=0 && x< n && y< m && grid[x][y]==1){
                        grid[x][y] = 2;
                        freshOranges--;
                        que.addLast(x*m+y);
                        
                        if(freshOranges==0) return time + 1;
                    }
                }
            }
            
            time++;
        }
        
        return -1;
    }

    //Leetcode 542
    public int[][] updateMatrix(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return grid;
        
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        
        
        int[][] vis=new int[n][m];
        for(int[] a: vis)
          Arrays.fill(a, -1);

        LinkedList<int[]> que=new LinkedList<>();
        
        int countOnes = n*m;
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if (grid[i][j] == 0) {
                    que.addLast(new int[]{i,j});
                    vis[i][j] = 0;
                    countOnes--;
                }
            }
        }

        int level = 1;
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int[] rvtx = que.removeFirst();
                int r = rvtx[0];
                int c = rvtx[1];

                for(int d = 0; d < 4;d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if(x>=0 && y>=0 && x< n && y<m && vis[x][y] == -1){
                        vis[x][y] = level;
                        countOnes--;
                        que.addLast(new int[]{x,y});
                    }
                }
            }

            level++;
            if(countOnes == 0) break;
        }

        return vis;
    }

    //Leetcode 1091
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        
        int n = grid.length;
        int m = grid[0].length;

        if(grid[0][0] == 1 || grid[n-1][m-1] == 1) return -1;
        
        int[][] dir =  {{-1, -1}, {-1, 0}, {0, -1}, {-1, 1}, {1, -1}, {0, 1}, {1, 0}, {1, 1}};
        
        LinkedList<int[]> que=new LinkedList<>();
        que.addLast(new int[]{0,0});
        grid[0][0] = 1;
       
        int level=0;

        while(que.size() != 0){
            int size = que.size();
            while(size-->0){

                int[] rvtx = que.removeFirst();
                int x = rvtx[0];
                int y = rvtx[1];
                
                if(x == n-1 && y == m-1) return level + 1;

                for(int d = 0; d < 8; d++){
                    int r = x + dir[d][0];
                    int c = y + dir[d][1];

                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0){
                        que.addLast(new int[]{r,c});
                        grid[r][c] = 1;
                    }
                }
            }

            level++;
        }

        return -1;
    }

    //Leetcode 1020
    public int numEnclaves(int[][] arr) {
        
        if(arr.length==0 || arr[0].length==0) return 0;
        int n = arr.length;
        int m = arr[0].length;
        
        int one=0;
        ArrayDeque<Integer> que = new ArrayDeque<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                one+=arr[i][j];
                if((i==0 || j==0 || i==n-1 || j==m-1) && arr[i][j]==1 ){
                    que.addLast(i*m+j);
                    one--;
                    arr[i][j] = 0;
                }
            }
        }
        
        if(one==0) return 0;
        
        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int vtx = que.removeFirst();
                int x = vtx / m;
                int y = vtx % m;
                
                for(int d=0;d<4;d++){
                    int r = x + dir[d][0];
                    int c = y + dir[d][1];
                    
                    if(r>=0 && c>=0 && r<n&& c<m && arr[r][c] == 1){
                        arr[r][c] = 0;
                        que.addLast(r*m+c);
                        one--;
                    }  
                }
            }
            
            if(one == 0) break;
        }
        
        return one;
    }

    //Leetcode 207 
    public boolean canFinish(int N, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i=0;i<N;i++) graph[i] = new ArrayList<>();
        int[] indegree= new int[N];
        
        for(int[] a : prerequisites){
            int u = a[0];
            int v = a[1];

            indegree[v]++;
            graph[u].add(v);
        }

        ArrayDeque<Integer> que = new ArrayDeque<>();
        for(int i=0;i<N;i++) if(indegree[i]==0) que.addLast(i);

        int count = 0;
        while(que.size()!=0){
            int vtx = que.removeFirst();
            count++;
            for(int e : graph[vtx]){
                if(--indegree[e]==0)
                   que.add(e); 
            }
        }

        return count == N;
    }

    public int[] findOrder(int N, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i=0;i<N;i++) graph[i] = new ArrayList<>();
        int[] indegree= new int[N];
        
        for(int[] a : prerequisites){
            int u = a[0];
            int v = a[1];

            indegree[v]++;
            graph[u].add(v);
        }

        ArrayDeque<Integer> que = new ArrayDeque<>();
        for(int i=0;i<N;i++) if(indegree[i]==0) que.addLast(i);

        int[] ans = new int[N];
        int idx = N-1;
        while(que.size()!=0){
            int vtx = que.removeFirst();
            ans[idx--] = vtx;
            for(int e : graph[vtx]){
                if(--indegree[e]==0)
                   que.addLast(e); 
            }
        }

        if(idx != -1) return  new int[0];
        return ans;
    }

    public static boolean isCyclePresent(ArrayList<Integer>[] graph,int src,int[] vis,ArrayList<Integer> ans){
        vis[src] = 0;
        
        for(int e : graph[src]){
            if(vis[e]==-1){
               if(isCyclePresent(graph,e,vis,ans)) return true;
            }else if(vis[e]==0) return true;
        }

        vis[src] = 1;
        ans.add(src);
    }


    public int[] findOrder(int N, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i=0;i<N;i++) graph[i] = new ArrayList<>();
        
        for(int[] a : prerequisites)
            graph[a[0]].add(a[1]);

        int[] vis = new int[N];
        Arrays.fill(vis,-1);
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0;i<N;i++){
            if(vis[i]==-1) if(isCyclePresent(graph,i,vis,ans)) return new int[0];
        }

        int[] res = new int[ans.size()];
        int idx =0;
        for(int ele: ans) res[idx++] = ele;
        return ans;
    }

    //Leetcode 329 
   public int longestIncreasingPath(int[][] matrix) {
       int n = matrix.length; if(n==0) return 0;
       int m = matrix[0].length;if(m==0) return 0;

       int[][] indegree=new int[n][m];
       int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

       for(int i=0;i<n;i++){
           for(int j=0;j<m;j++){
               for(int d=0;d<4;d++){
                   int r = i + dir[d][0];
                   int c = j + dir[d][1];

                   if(r >= 0 && c >= 0 && r < n && c < m && matrix[r][c] > matrix[i][j])
                      indegree[r][c]++;
               }
           }
       }

       ArrayDeque<Integer> que = new ArrayDeque<>();
       for(int i=0;i<n;i++){
         for(int j=0;j<m;j++){
          if(indegree[i][j]==0) que.addLast(i*m+j);
         }
      } 

      int level = 0;
      while(que.size()!=0){
          int size = que.size();
          while(size-- > 0){
          
            int vtx = que.removeFirst();
            int i = vtx / m;
            int j = vtx % m;

            for(int d=0;d<4;d++){
                int r = i + dir[d][0];
                int c = j + dir[d][1];

                if(r >= 0 && c >= 0 && r < n && c < m && matrix[r][c] > matrix[i][j] && --indegree[r][c]==0)
                   que.addLast(r*m+c);
            }
          }
          level++;
      }

      return level;
   }


   //================================================

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

    public List<Integer> numIslands2(int n, int m, int[][] positions) {
       par = new int[n*m];
       for(int i=0;i<n*m;i++) par[i] = i;

       int islandCount = 0;
       int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
       
       int[][] mat = new int[n][m];
       List<Integer> ans = new ArrayList<>();

       for(int[] a: positions){
           int i = a[0];
           int j = a[1];

           if(mat[i][j]==0){
            int p1 = findPar(i*m+j);
            mat[i][j] = 1;
            islandCount++;
 
            for(int d = 0;d<4;d++){
                int r = i + dir[d][0];
                int c = j + dir[d][1];
 
                if(r >= 0 && c >= 0 && r < n && c < m && mat[r][c]==1){
                   int p2 = findPar(r*m+c);
                   
                   if(p1 != p2){
                       islandCount--;
                       par[p2] = p1;
                   }
                }
            }
           
           }
           ans.add(islandCount);
       }

       return ans;

    }

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<int[]> gp = new ArrayList<>();
        for(int[] e: pipes) gp.add(e);
        for(int i=0;i<wells.length;i++){
            gp.add(new int[]{0,i+1,wells[i]});
        }

        Collections.sort(gp,(a,b)->{
            return a[2] - b[2];
        });

        par = new int[n+1];
        for(int i=0;i<=n;i++) par[i] = i;

        int weight = 0;
        for(int[] a: gp){
            int p1 = findPar(a[0]);
            int p2 = findPar(a[1]);

            if(p1!=p2){
                par[p1] = p2;
                weight += a[2];
            }
        }

        return weight;
    }

    public class Edge{
        int v = 0;
        int w = 0;
        Edge(int v,int w){
            this.v = v;
            this.w = w;
        }
    }

    public class pair{
        int src = 0;        
        int k = 0;
        int wsf = 0;

        pair(int src,int wsf,int k){
            this.src = src;
            this.wsf = wsf;
            this.k = k;
        }
    }

    //787
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        ArrayList< Edge >[] gp = new ArrayList[n];
        for(int i=0;i<n;i++) gp[i] = new ArrayList<>();

        for(int[] a: flights) gp[a[0]].add(new Edge(a[1],a[2]));

        PriorityQueue<pair> pq = new PriorityQueue<>((a,b)->{
            return a.wsf - b.wsf;
        });

        pq.add(new pair(src,0,K+1));
        while(pq.size()!=0){
            pair vtx = pq.poll();
            
            if(vtx.k < 0) continue;
            if(vtx.src == dst) return vtx.wsf;
            for(Edge e: gp[vtx.src]){
                pq.add(new pair(e.v, vtx.wsf + e.w,vtx.k - 1));
            }
        }

        return -1;
    }

    public int findCheapestPrice(int n, int[][] flights, int s, int dst, int K) {
        // u,v,w
        ArrayList<int[]>[] gp = new ArrayList[n];
        for(int i=0;i<n;i++) gp[i] = new ArrayList<>();

        for(int[] a: flights) gp[a[0]].add(new int[]{a[1],a[2]});

        // src, wsf , k
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            return a[1] - b[1];
        });

        pq.add(new int[]{s,0,K+1});
        while(pq.size()!=0){
            int[] vtx = pq.poll();
            int src = vtx[0];
            int wsf = vtx[1];
            int k = vtx[2]; 

            if(k < 0) continue;
            if(src == dst) return wsf;
            for(int[] e: gp[src]){
                pq.add(new int[]{e[0], wsf + e[1], k - 1});
            }
        }

        return -1;
    }

    public int networkDelayTime(int[][] times, int N, int K) {

        // v,w
        ArrayList<int[]>[] gp = new ArrayList[N+1];
        for(int i=0;i<=N;i++) gp[i] = new ArrayList<>();

        for(int[] a: times) gp[a[0]].add(new int[]{a[1],a[2]});

        // src, time
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            return a[1] - b[1];
        });
        
        int[] dis = new int[N+1];
        Arrays.fill(dis, (int)1e8 );
        
        pq.add(new int[]{K,0});
        dis[K] = 0;

        while(pq.size()!=0){
            int[] vtx = pq.poll();
            int src = vtx[0];
            int time = vtx[1];

            for(int[] e: gp[src]){
                if(time+e[1] < dis[e[0]]){ 
                    dis[e[0]] = time + e[1];
                    pq.add(new int[]{e[0],time+e[1]});
                }
            }
        }
      
     int maxTime = 0;
     for(int i = 1;i<=N;i++){
         int ele = dis[i];
         if(ele == (int) 1e8) return -1;
         
         maxTime = Math.max(maxTime,ele);
     }
     return maxTime;
 }

 // for you: ------------------>> leetcode 886
 
 //Leetcode : 778
 public int swimInWater(int[][] grid) {
    if(grid.length==0 || grid[0].length == 0) return 0;
    
    int n = grid.length;
    int m = grid[0].length;
    
    
    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
        return grid[a/m][a%m] - grid[b/m][b%m];
    });
    
    int maxTime = 0;
    pq.add(0);
    
    int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    boolean[][] vis = new boolean[n][m];
    vis[0][0] = true;
    
    while(pq.size()!=0){
      int val = pq.poll();
      
      maxTime = Math.max(maxTime,grid[val/m][val%m]);
      if(val/m == n-1 && val%m == m-1) return maxTime;
      
      for(int d=0;d<4;d++){
          int r = val/m + dir[d][0];
          int c = val%m + dir[d][1];
        
          if(r>=0 && c>=0 && r< n && c< m && !vis[r][c]){
            vis[r][c] = true;  
            pq.add(r*m + c);
          }
      }
    }
    
    return -1;
}

//815
public int numBusesToDestination(int[][] routes, int src, int desti) {
    if(src == desti) return 0;
  int n=routes.length;
  HashMap<Integer,ArrayList<Integer>> BusStandToBus=new HashMap<>();
  for(int i=0;i<n;i++){
      for(int ele: routes[i]){
          BusStandToBus.putIfAbsent(ele,new ArrayList<>());
          BusStandToBus.get(ele).add(i);
      }
  }

  boolean[] BusVis = new boolean[n];
  HashSet<Integer> busStandVis = new HashSet<>();

  ArrayDeque<Integer> que = new ArrayDeque<>();
  
  que.addLast(src);
  busStandVis.add(src);
  int count = 0;

  while(que.size()!=0){
      int size = que.size();
      while(size-->0){

          int busStand = que.removeFirst();
          
          for(int buses : BusStandToBus.get(busStand)){
              
              if(BusVis[buses]) continue;

              for(int bs : routes[buses]){
                  if(!busStandVis.contains(bs)){
                      busStandVis.add(bs);
                      que.add(bs);
                      if(bs == desti) return count + 1;
                  }
              }

              BusVis[buses] = true;
          }
      }
      count++;
  }

  return -1;
 
}


// https://www.hackerrank.com/challenges/journey-to-the-moon/problem
public class Solution {
    static int[] par;
    static int[] size;
    public static int findPar(int u){
        if(u == par[u]) return u;
        return par[u] = findPar(par[u]);
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int p = scn.nextInt();
        
        par = new int[n];
        size = new int[n];

        for(int i=0;i<n;i++) par[i] = i;

        Arrays.fill(size,1);

        for(int i=0;i<p;i++){
            int p1 = findPar(scn.nextInt());
            int p2 = findPar(scn.nextInt());

            if(p1!=p2){
                par[p1] = p2;
                size[p2]+=size[p1];
            }
        }

        long sum = 0;
        long res = 0;
        for(int i=0;i<n;i++){
            if(i==par[i]){
                res += size[i] * sum;
                sum += size[i];
            }
        }

        System.out.println(res);
    }
}

    // 685
    int[] par;
    public int findPar(int u){
       if(u == par[u]) return u;
        return par[u] = findPar(par[u]);
    }
    
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length; 
        int a = -1, b=-1, cycle = -1;   
        
        int[] actualParent = new int[n+1];
        Arrays.fill(actualParent,-1);
        
        par=new int[n+1];
        for(int i=0;i<=n;i++) par[i] = i;
        
        for(int i=0;i<n;i++){
            int p = edges[i][0];
            int c = edges[i][1];
            
            if(actualParent[c]!=-1){
                a = actualParent[c];
                b = i;
                continue;
            }
            
            actualParent[c] = i;
            int globalParent = findPar(p); 
            if(globalParent == c) cycle = i;
            else par[c] = globalParent;
        }
        
        
        if(cycle == -1) return edges[b];
        else if(b == -1) return edges[cycle];
        else return edges[a];
    }
    
    //802
    public boolean isCyclePresent(int[][] graph,int src,int[] vis) {
        vis[src] = 1;
        for(int e : graph[src]){
            if(vis[e]==0){
                if(isCyclePresent(graph,e,vis)) return true;
            }else if(vis[e] == 1) return true;
        }

        vis[src] = 2;
        return false;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] vis = new int[n+1];

        List<Integer> ans = new ArrayList<>();
        for(int i = 0;i < n;i++){
            if(vis[i] == 1) continue;
            if(vis[i]==2 || !isCyclePresent(graph,i,vis))
              ans.add(i);
        }

        return ans;
    }

    //990
    public boolean equationsPossible(String[] equations) {
        par = new int[26];
        for(int i=0;i<26;i++) par[i] = i;
        
        for(String s: equations){
            if(s.charAt(1) == '=')
                par[findPar(s.charAt(0)-'a')] = findPar(s.charAt(3)-'a');
        }
        
        for(String s: equations){
            if(s.charAt(1) == '!' && findPar(s.charAt(0)-'a') == findPar(s.charAt(3)-'a'))
                return false;
        }
        
        return true;
    }

    
    //959
    public int regionsBySlashes(String[] grid) {
        if(grid.length==0) return 0;
        
        int n = grid.length;
        int m = n + 1;
        
        par = new int[m*m];
        for(int i = 1;i<n;i++){
            for(int j = 1;j<n;j++){
                int p = i + j * m;
                par[p] = p;
            }
        }
        
        int count = 1;
        for(int i = 0;i<n;i++){
            String s = grid[i];
            for(int j = 0; j<s.length();j++){
                char ch = s.charAt(j);
                if(ch == '/'){
                    int p1 = findPar((i+1) + j*m);
                    int p2 = findPar( i  + (j + 1)*m);
                    
                    if(p1 != p2){
                        par[p1] = Math.min(p1,p2);
                        par[p2] = Math.min(p1,p2);
                    }else count++;
                }else if(ch == '\\'){
                    int p1 = findPar(i + j*m);
                    int p2 = findPar( (i+1)  + (j + 1)*m);
                    
                    if(p1 != p2){
                        par[p1] = Math.min(p1,p2);
                        par[p2] = Math.min(p1,p2);
                    }else count++;
                }
            }   
        }
        return count;
    }

    import java.util.*;
import java.io.*;
class TestClass {
    public static void main(String args[] ) throws Exception {
         chocolateJourney();
    }

    
    
    public static void dijkstra(int src,ArrayList<int[]>[] graph,int[] dis){
        // {vtx, dis}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            return a[1] - b[1];
        });

        pq.add(new int[]{src,0});
        dis[src] = 0;

        while(pq.size()!=0){
            int[] vtx = pq.remove();

            if(dis[vtx[0]] < vtx[1]) continue;

            for(int[] e: graph[vtx[0]]){
                if(vtx[1] + e[1] < dis[e[0]]){
                    dis[e[0]] = vtx[1] + e[1];
                    pq.add(new int[]{e[0],dis[e[0]]});
                }
            }
        }
    }

    public static void chocolateJourney() throws IOException{
        // Scanner scn = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        Scan scn = new Scan(System.in);
        int n=scn.scanInt();
        int m=scn.scanInt();
        int k=scn.scanInt();
        int x=scn.scanInt();
        
        boolean[]  chocolateCity = new boolean[n];
        for(int i=0;i<k;i++){
            int val = scn.scanInt();
            chocolateCity[val - 1] = true;
        }

        ArrayList<int[]>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++) graph[i] = new ArrayList<>();
        for(int i=0;i<m;i++){
            int u = scn.scanInt();
            int v = scn.scanInt();
            int d = scn.scanInt();
            u--;
            v--;
            
            graph[u].add(new int[]{v,d});
            graph[v].add(new int[]{u,d});
        }

        int src = scn.scanInt();
        int dest = scn.scanInt();
        src--;
        dest--;

        int[] sdis = new int[n];
        int[] ddis = new int[n];
        Arrays.fill(sdis,(int)1e9);
        Arrays.fill(ddis,(int)1e9);

        dijkstra(src,graph,sdis);
        dijkstra(dest,graph,ddis);

        int ans = (int)1e9;
        for(int i=0;i<n;i++){
            if(chocolateCity[i]){
                if(ddis[i] < x && sdis[i] != (int)1e9)
                  ans = Math.min(ans,sdis[i] + ddis[i]);
            }
        }
        
        if(ans!=(int)1e9)System.out.println(ans);
        else System.out.println(-1);
    }

    static class Scan {
		private InputStream in;
		private byte[] buf = new byte[1024 * 1024];
		private int index;
		private int total;

		public Scan(InputStream in) {
			this.in = in;
		}

		public int scan() throws IOException {
			if (total < 0)
				throw new InputMismatchException();
			if (index >= total) {
				index = 0;
				total = in.read(buf);
				if (total <= 0)
					return -1;
			}
			return buf[index++];
		}

		public int scanInt() throws IOException {
			int integer = 0;
			int n = scan();
			while (isWhiteSpace(n))
				n = scan();
			int neg = 1;
			if (n == '-') {
				neg = -1;
				n = scan();
			}
			while (!isWhiteSpace(n)) {
				if (n >= '0' && n <= '9') {
					integer *= 10;
					integer += n - '0';
					n = scan();
				} else
					throw new InputMismatchException();
			}
			return neg * integer;
		}

		public long scanLong() throws IOException {
			long integer = 0;
			int n = scan();
			while (isWhiteSpace(n))
				n = scan();
			int neg = 1;
			if (n == '-') {
				neg = -1;
				n = scan();
			}
			while (!isWhiteSpace(n)) {
				if (n >= '0' && n <= '9') {
					integer *= 10;
					integer += n - '0';
					n = scan();
				} else
					throw new InputMismatchException();
			}
			return neg * integer;
		}

		public double scanDouble() throws IOException {
			double doub = 0;
			int n = scan();
			while (isWhiteSpace(n))
				n = scan();
			int neg = 1;
			if (n == '-') {
				neg = -1;
				n = scan();
			}
			while (!isWhiteSpace(n) && n != '.') {
				if (n >= '0' && n <= '9') {
					doub *= 10;
					doub += n - '0';
					n = scan();
				} else
					throw new InputMismatchException();
			}
			if (n == '.') {
				n = scan();
				double temp = 1;
				while (!isWhiteSpace(n)) {
					if (n >= '0' && n <= '9') {
						temp /= 10;
						doub += (n - '0') * temp;
						n = scan();
					} else
						throw new InputMismatchException();
				}
			}
			return doub * neg;
		}

		public String scanString() throws IOException {
			StringBuilder sb = new StringBuilder();
			int n = scan();
			while (isWhiteSpace(n))
				n = scan();
			while (!isWhiteSpace(n)) {
				sb.append((char) n);
				n = scan();
			}
			return sb.toString();
		}

		private boolean isWhiteSpace(int n) {
			if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
				return true;
			return false;
		}
	}

}

    //924
    int[] par,size;
    public int findPar(int u){
        return par[u] = (par[u] == u) ? u : findPar(par[u]); 
    }
    
    public int minMalwareSpread(int[][] graph, int[] initial) {
        
        int n = graph.length;
        par = new int[n];
        size = new int[n];
        for(int i=0;i<n;i++){
            par[i] = i;
            size[i] = 1;
        }
        
        for(int i=0;i<n;i++){
            for(int j = 0;j<n;j++){
                if(i != j && graph[i][j] == 1){
                    int p1 = findPar(i);
                    int p2 = findPar(j);
                    if(p1 != p2){
                       par[p1] = p2;
                       size[p2] += size[p1];
                    }
                }
            }
        }
        
        int[] infectedInComponents = new int[n];
        
        Arrays.sort(initial);
        for(int i : initial){
            int p = findPar(i);
            infectedInComponents[p]++;
        }
        
        int maxPopulatedComponent = 0;
        int ans = initial[0];
        for(int i : initial){
            if(infectedInComponents[findPar(i)] == 1 && size[findPar(i)] > maxPopulatedComponent){
                maxPopulatedComponent = size[findPar(i)];
                ans = i;
            }
        }
        
        return ans;
        
    }

}