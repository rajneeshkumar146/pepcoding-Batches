public class question{

    // Leetcode 200
    public void numIslandsDFS(char[][] grid,int r,int c,int[][] dir){
        grid[r][c] = '0';
        for(int d = 0; d < dir.length ; d++){
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if( x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == '1'){
                numIslandsDFS(grid,x,y,dir);
            }
        }

    }


    public int numIslands(char[][] grid) {
        
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        int count = 0;
        for(int i = 0; i < grid.length;i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    numIslandsDFS(grid,i,j,dir);
                    count++;
                }
            }
        }

        return count;
    }

    //Leetcode 695
    public int maxAreaOfIslandDFS(int[][] grid,int r,int c,int[][] dir){
        grid[r][c] = 0;
        int count = 0;
        for(int d = 0; d < dir.length ; d++){
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if( x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1){
                count += maxAreaOfIslandDFS(grid,x,y,dir);
            }
        }

        return count + 1;
    }


    public int maxAreaOfIsland(int[][] grid) {
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        int maxArea = 0;
        for(int i = 0; i < grid.length;i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    maxArea = Math.max(maxArea,maxAreaOfIslandDFS(grid,i,j,dir));
                }
            }
        }

        return maxArea;
    }

    //Leetcode 463
    public int islandPerimeter(int[][] grid) {
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

        int countOnes = 0;
        int CountNbrs =0;

        for(int i = 0; i < grid.length;i++){
            for(int j = 0; j < grid[0].length; j++){

                if(grid[i][j] == 1){
                    countOnes++;

                    for(int d = 0 ;d<4;d++){
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if( r>=0 && c>=0 && r<grid.length && c< grid[0].length && grid[r][c] == 1) 
                           CountNbrs++;`
                    }
                }
            }
        }

        return 4 * countOnes - CountNbrs;
    }

    //Leetcode 130
    public void SurroundedRegionDFS(char[][] grid,int r,int c,int[][] dir){
        grid[r][c] = '$';
        for(int d = 0 ;d < 4 ; d++){
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if( x>=0 && y>=0 && x < grid.length && y < grid[0].length && grid[x][y] == 'O') 
               SurroundedRegionDFS(grid,x,y,dir);
        }
    }


    public void solve(char[][] grid) {
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int i = 0; i < grid.length;i++){
            for(int j = 0; j < grid[0].length; j++){
                if(i ==0 || j == 0 || i == grid.length-1 || j == grid[0].length - 1){
                    if(grid[i][j] == 'O')
                       SurroundedRegionDFS(grid,i,j,dir);
                }

            }
        }

        for(int i = 0; i < grid.length;i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 'O') grid[i][j] = 'X';
                else if(grid[i][j] == '$') grid[i][j] = 'O';
            }
        }
    }
    
    //Leetcode 785
    public boolean isBipartite(int[][] graph,int[] markedColor,int src){  
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        
        int color = 0;
        
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                
                int vtx = que.removeFirst();
                
                if(markedColor[vtx] != -1){
                    
                    if(markedColor[vtx] != color){  // phele red tha ab white se mark krne agye.
                        return false; 
                    }    
                    continue;
                }
                
                markedColor[vtx] = color;
                for(int e: graph[vtx]){
                    if(markedColor[e] == -1){
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
        int[] markedColor = new int[N];
        Arrays.fill(markedColor, -1);
        
        boolean ans = true;
        for(int i=0;i<N;i++){
            if(markedColor[i] == -1){
                ans = isBipartite(graph,markedColor,i);
                
                if(!ans) return false;
            }
        }
        
        return true;
    }
}

//1091
public int shortestPathBinaryMatrix(int[][] grid) {
    if(grid.length == 0 ||  grid[0].length == 0) return -1;
   
    int n = grid.length;
    int m = grid[0].length;
   
    if(grid[0][0] == 1 || grid[n-1][m-1] == 1) return -1;

    LinkedList<Integer> que = new LinkedList<>();

    que.addLast((0 * m + 0));
    grid[0][0] = 1;

    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{-1,1},{1,-1}};
    
    int level = 1;
    while(que.size()!=0){
        int size = que.size();
        while(size-->0){
            int idx = que.removeFirst();

            int r = idx / m;
            int c = idx % m;

            if(r == n - 1 && c == m - 1) return level;

            for(int d = 0; d < 8 ; d++){
                int x = r + dir[d][0];
                int y = c + dir[d][1];

                if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 0){
                    grid[x][y] = 1;
                    que.addLast(x * m + y);
                }
            }
        }
        level++;
    }

    return -1;
}

// 286 Solution 1
public void wallsAndGates(int[][] rooms) {
    if(rooms.length == 0 || rooms[0].length == 0) return;
    int n = rooms.length;
    int m = rooms[0].length;
    
    LinkedList<Integer> que = new LinkedList<>();
    
    for(int i = 0;i<n;i++){
        for(int j = 0; j<m;j++){
            if(rooms[i][j] == 0){
                que.addLast(i*m + j);
            }
        }
    }
    
    int level = 0;
    int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

    while(que.size()!=0){
        int size = que.size();
        while(size-->0){
            int idx = que.removeFirst();
            int r = idx / m;
            int c = idx % m;
            
            for(int d = 0;d< 4;d++){
                
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                
                if(x>=0 && y>=0 && x< n && y<m && rooms[x][y] == 2147483647 ){
                    rooms[x][y] = level + 1; 
                    que.addLast(x*m+y);
                }
            }
        }
        level++;
    }   
}

//286 Solution 2
public void wallsAndGates(int[][] rooms) {
    if(rooms.length == 0 || rooms[0].length == 0) return;
    int n = rooms.length;
    int m = rooms[0].length;
    
    LinkedList<Integer> que = new LinkedList<>();
    boolean[][] vis = new boolean[n][m];
    
    for(int i = 0;i<n;i++){
        for(int j = 0; j<m;j++){
            if(rooms[i][j] == 0){
                que.addLast(i*m + j);
                vis[i][j] = true;
            }
        }
    }
    
    int level = 0;
    int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

    while(que.size()!=0){
        int size = que.size();
        while(size-->0){
            int idx = que.removeFirst();
            int r = idx / m;
            int c = idx % m;
            
            rooms[r][c] = level;
            for(int d = 0;d< 4;d++){
                
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                
                if(x>=0 && y>=0 && x< n && y<m && !vis[x][y] && rooms[x][y] != -1){
                    vis[x][y] = true;
                    que.addLast(x*m+y);
                }
            }
        }
        level++;
    }   
}

//Leetcode 994
public int orangesRotting(int[][] grid) {
    if(grid.length==0 || grid[0].length==0) return 0;
    
    int n = grid.length;
    int m = grid[0].length;
    
    int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    LinkedList<Integer> que = new LinkedList<>();
    int freshOranges = 0;

    for(int i = 0;i<n;i++){
        for(int j = 0;j<m;j++){
            if(grid[i][j] == 2) que.addLast(i*m+j);
            if(grid[i][j] == 1) freshOranges++;
        }
    }

    if(freshOranges == 0) return 0;
    int time = 0;
    while(que.size() != 0){
        int size = que.size();
        while(size-->0){
            int idx = que.removeFirst();
            int r = idx / m;
            int c = idx % m;

            for(int d = 0; d < 4; d++){
                int x = r + dir[d][0];
                int y = c + dir[d][1];

                if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1){
                    grid[x][y] = 2;
                    freshOranges--;
                    que.addLast(x * m + y);
                    if(freshOranges == 0) return time + 1;
                }
            }
        }
        time++;
    }

    return -1;
}


//Leetcode 296
public void BFS(int i,int j,int[][] grid,int[][] dir,int[][] dis){
    int n = grid.length;
    int m = grid[0].length;
    
    boolean[][] vis = new boolean[n][m];
    LinkedList<Integer> que = new LinkedList<>();
    que.addLast(i*m+j);
    vis[i][j] = true;
    
    int level = 0;
    while(que.size() != 0){
        int size = que.size();
        while(size-- > 0){
            int idx = que.removeFirst();
            int r = idx / m;
            int c = idx % m;
            
            dis[r][c] += level;
            
            for(int d = 0;d < 4; d++){
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                
                if(x >= 0 && y >= 0 && x < n && y < m && !vis[x][y]){
                    vis[x][y] = true;
                    que.addLast(x * m + y);
                }
            }
        }
        
        level++;
    }
}

public int minTotalDistance(int[][] grid) {
   if(grid.length == 0 || grid[0].length == 0) return 0; 
    int n = grid.length;
    int m = grid[0].length;
    
    int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    int[][] dis = new int[n][m];
    
    for(int i = 0;i<n;i++){
        for(int j = 0; j<m;j++){
            if(grid[i][j] == 1){
                BFS(i,j,grid,dir,dis);            
            }
        }
    }
    
    int ans = (int)1e8;
    for(int i = 0;i<n;i++){
        for(int j = 0; j<m;j++){
            ans = Math.min(ans , dis[i][j]);
        }
    }
    
    return ans;
}

public int getInter(ArrayList<Integer> list){
    Collections.sort(list);
    
    int res = 0;
    int i = 0;
    int j = list.size() - 1;
    while( i < j ){
        res += list.get(j--) - list.get(i++);
    }
    
    return res;
    
}

public int minTotalDistance(int[][] grid) {
   if(grid.length == 0 || grid[0].length == 0) return 0; 
    int n = grid.length;
    int m = grid[0].length;
    
    ArrayList<Integer> xpoints = new ArrayList<>();
    ArrayList<Integer> ypoints = new ArrayList<>();
    
    for(int i = 0;i<n;i++){
    for(int j = 0; j<m;j++){
        if(grid[i][j] == 1){
            xpoints.add(i);
            ypoints.add(j);
        }
     }
   }
    
    return getInter(xpoints) + getInter(ypoints);
}

// Leetcode 815


//Leetcode 207
public boolean canFinish(int N, int[][] arr){
    ArrayList<Integer>[] graph = new ArrayList[N];
    for(int i=0;i<N;i++) graph[i] = new ArrayList<>();
    
    int[] indegree = new int[N];
    for(int[] a: arr){
        indegree[a[1]]++;
        graph[a[0]].add(a[1]);
    }
    
    LinkedList<Integer> que = new LinkedList<>();
    for(int i=0;i<N;i++) if(indegree[i]==0) que.addLast(i);

    int count = 0;
    while(que.size()!=0){
        int vtx = que.removeFirst();
        count++;
        for(int e : graph[vtx]){
            if(--indegree[e] == 0) que.addLast(e);
        }
    }
    
    
    return count == N;        
}


//Leetcode 210
public int[] findOrder(int N, int[][] arr) {
    ArrayList<Integer>[] graph = new ArrayList[N];
     for(int i=0;i<N;i++) graph[i] = new ArrayList<>();
     
     int[] indegree = new int[N];
     for(int[] a: arr){
         indegree[a[1]]++;
         graph[a[0]].add(a[1]);
     }
     
     LinkedList<Integer> que = new LinkedList<>();
     for(int i=0;i<N;i++) if(indegree[i]==0) que.addLast(i);

     int[] ans = new int[N];
     int idx = N - 1;
     while(que.size()!=0){
         int vtx = que.removeFirst();
         ans[idx--] = vtx;
         
         for(int e : graph[vtx]){
             if(--indegree[e] == 0) que.addLast(e);
         }
     }
     
     
     if(idx == -1) return ans;
     return new int[0];
 }

 public int numBusesToDestination(int[][] routes, int src, int desti) {
     if(src == desti) return 0;
     int n = routes.length;

     // unorderd_map<int,vector<int>> map;
     HashMap<Integer,ArrayList<Integer>> busStandsMap = new HashMap<>();
     for(int busNo = 0;busNo < n;busNo++){
         for(int stand : routes[busNo]){
             busStandsMap.putIfAbsent(stand,new ArrayList<>());
             busStandsMap.get(stand).add(busNo);
         }
     }

     LinkedList<Integer> que = new LinkedList<>();
     HashSet<Integer> busStandVis = new HashSet<>();
     boolean[] busNoVis = new boolean[n];


     que.addLast(src);
     busStandVis.add(src);

     int level = 0;
     while(que.size()!=0){
        int size = que.size();
        
        while(size-->0){
            int busStand = que.removeFirst();
            for(int busNo : busStandsMap.get(busStand)){
                if(busNoVis[busNo]) continue;
                
                busNoVis[busNo] = true;
                for(int stand : routes[busNo]){
                    if(busStandVis.contains(stand)) continue;
                    busStandVis.add(stand);
                    que.addLast(stand);
                    if(stand == desti) return level + 1; 
                }
            }
        }
        level++;
     }

     return level;
 }

 HashSet<String> map = new HashSet<>();
    int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
    char[] dirS = {'r','d','l','u'};
    String shape = "";
    int n=0,m=0;
    
    public void dfs(int i,int j,int[][] grid){
        
        grid[i][j] = 0;
        for(int d = 0;d<4;d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            
            if(r>=0 && c>=0 && r< n && c<m && grid[r][c] == 1){
                shape += dirS[d];
                dfs(r,c,grid);
                shape += "b";
            }
        }
         
    }
    
    public int numDistinctIslands(int[][] grid) {
        if(grid.length ==0 || grid[0].length == 0) return 0;
        n = grid.length;
        m = grid[0].length;
        for(int i=0;i<n;i++){
            for(int j =0;j<m;j++){
                if(grid[i][j]==1){
                    dfs(i,j,grid);
                    map.add(shape);
                    shape = "";
                }
            }
        }
        
        return map.size();
        
    }

    // union find.================================================
    int[] par;
    int findPar(int u){
       if (u == par[u])
          return u;
       return par[u] = findPar(par[u]);
    }

//684
int[] findRedundantConnection(int[][] edges){
    int n = edges.length;
    par = new int[n+1];
    for (int i = 0; i <= n; i++)
        par[i] = i;

    for (int[] a : edges)
    {
        int p1 = findPar(a[0]);
        int p2 = findPar(a[1]);

        if (p1 != p2)
            par[p1] = p2;
        else
            return a;
    }

    return new int[0];
}

    // 1061
    int[] par;
    int findPar(int u ){
        if(par[u] == u) return u;
        return par[u] = findPar(par[u]);
    }
    
    public String smallestEquivalentString(String A, String B, String S) {
        
        par = new int[26];
        for(int i=0;i<26;i++) par[i] = i;
        
        for(int i=0;i<A.length();i++){
            int p1 = findPar(A.charAt(i) - 'a');
             int p2 = findPar(B.charAt(i) - 'a');
            
            par[p1] = Math.min(p1,p2);
            par[p2] = Math.min(p1,p2);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<S.length();i++)
        {
            int p = findPar(S.charAt(i) - 'a');
            sb.append((char)(p + 'a'));
        }
        
        return sb.toString();   
    }

    // Leetcode 305
    public List<Integer> numIslands2(int n, int m, int[][] positions) {
        // par.resize(n*m,0);
        
        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
        par = new int[n*m];
        for(int i=0;i<n*m;i++) par[i] = i;
        int count = 0;
        List<Integer> ans = new ArrayList<>();
            
        int[][] grid = new int[n][m];
        for(int[] p : positions){
            int i = p[0];
            int j = p[1];
            if(grid[i][j] == 0){
            
            grid[i][j] = 1;
            count++;
            
            int p1 = findPar(i*m+j);
            
            for(int d =0;d<4;d++){
                int r = i + dir[d][0];
                int c = j + dir[d][1];
                
                if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1){
                    int p2 = findPar(r*m+c);
                    if(p1!=p2){
                        count--;
                        par[p2] = p1;
                    }
                }
              }
            }
            ans.add(count);
        }
        
        return ans;
    }

    //839
    public static boolean isSimilar(String s,String t){
        int count = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) != t.charAt(i) && ++count > 2) return false;
        }

        return true;
    }

    public int numSimilarGroups(String[] A) {  
        int n = A.length;
        
        par = new int[n];
        for(int i=0;i<n;i++) par[i] = i;

        int count = n;
        for(int i=0 ; i < n; i++){

            int p1 = findPar(i);
            for(int j = i+1 ; j < n; j++){

                if(isSimilar(A[i],A[j])){
                    int p2 = findPar(j);
                    if(p1 != p2){
                        par[p2] = p1;
                        count--;
                    }
                }
            }
        }

        return count;
    }

    int[] par;
    public int findPar(int u){
        if(par[u] == u) return u;
        return par[u] = findPar(par[u]);
    }

    
    // 1168
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<int[]> edges = new ArrayList<>();
        for(int i=0;i<n;i++){
            edges.add(new int[]{0,i+1,wells[i]});
        }
        
        for(int[] e: pipes) edges.add(e);
        
        Collections.sort(edges,(a,b)->{
            return a[2]-b[2];
        });
        
        par = new int[n+1];
        // Arrays.fill(par,-1);
        for(int i=0;i<n+1;i++) par[i] = i;
        
        int cost = 0;
        for(int[] e : edges){
            int p1 = findPar(e[0]);
            int p2 = findPar(e[1]);
            
            if(p1 != p2){
                par[p1] = p2;
                cost += e[2];
            }
        }
        
        return cost;
    }


    //1192
    ArrayList<Integer>[] graph;
    
    int time = 0;
    int[] low;
    int[] disc;
    boolean vis[];
    
    List<List<Integer>> ans = new ArrayList<>();
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph= new ArrayList[n];
        low = new int[n];
        disc = new int[n];
        vis = new boolean[n];
        
        for(int i=0;i<n;i++) graph[i] = new ArrayList<>();
        for(List<Integer> a: connections){
            graph[a.get(0)].add(a.get(1));
            graph[a.get(1)].add(a.get(0));
        }
        
        for(int i = 0;i < n; i++){
            if(!vis[i]){
                dfs_AB(i,-1);
            }
        }
        
        return ans;
    }
    
    public void dfs_AB(int src,int par){
        low[src] = disc[src] = time++;
        vis[src] = true;
        for(int e : graph[src]){
          if(!vis[e]){
              dfs_AB(e,src);
              if(disc[src] < low[e]){
                  
                 ArrayList<Integer> smallAns = new ArrayList<>();
                 smallAns.add(src); smallAns.add(e);
                 ans.add(smallAns);
              }
              
              low[src] = Math.min(low[src],low[e]);
          }else if(e != par){
              low[src] = Math.min(low[src],disc[e]);
          }
        }
    }





