import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class questions {

    // Leetcode 200
    public void numISlands_DFS(char[][] grid, int sr, int sc, int dir[][]) {
        grid[sr][sc] = '0';
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == '1')
                numISlands_DFS(grid, r, c, dir);
        }

    }

    public int numIslands(char[][] grid) {

        int count = 0;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    numISlands_DFS(grid, i, j, dir);
                }
            }
        }

        return count;
    }

    // Leetcode 695
    public int maxAreaOfIsland_DFS(int[][] grid, int sr, int sc, int dir[][]) {
        grid[sr][sc] = 0;
        int count = 0;
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1)
                count += maxAreaOfIsland_DFS(grid, r, c, dir);
        }

        return count + 1;

    }

    public int maxAreaOfIsland(int[][] grid) {

        int maxArea = 0;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, maxAreaOfIsland_DFS(grid, i, j, dir));
                }
            }
        }

        return maxArea;
    }

    // Leetcode 463
    public int islandPerimeter(int[][] grid) {
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        int totalOnes = 0;
        int nbr = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    totalOnes++;
                    for (int d = 0; d < 4; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1)
                            nbr++;
                    }
                }
            }
        }

        return totalOnes * 4 - nbr;
    }

    // Leetcode 130

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

    public static boolean isBipartite_(int[][] graph,int src,int[] vis){
        // vis[x]={-1(non visited),0(visited with red),1(visited with green)}
        
        LinkedList<int[]> que=new LinkedList<>();
        que.addLast(new int[]{src,0});  // vtx, color (0 - red, 1 - green)

        while(que.size()!=0){
           int size=que.size();
           while(size-->0){
              int[] pair=que.removeFirst();    
              
              int rvtx=pair[0];
              int color=pair[1]; 

              if(vis[rvtx] != -1){
                if(color != vis[rvtx]) return false;
                continue;
              }

             vis[rvtx]=color;    
             for(int e: graph[rvtx]){
                 if(vis[e] == -1)
                   que.addLast(new int[]{e,(color+1)%2});
              }
           }
        }

        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int N=graph.length;
        int[] vis=new int[N];
        Arrays.fill(vis,-1);

        for(int i=0;i<N;i++){
            if(vis[i] == -1){
                if(!isBipartite_(graph,i,vis)){
                    return false;
                }
            }
        }

        return true;
    }


    //Leetcode 994
    public int orangesRotting(int[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        
        LinkedList<int[]> que=new LinkedList<>();
        int freshOranges = 0;
        
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(grid[i][j] == 1) freshOranges++; 
                else if (grid[i][j] == 2) que.addLast(new int[]{i,j});
            }
        }

        if(freshOranges == 0) return 0;

        int time = 0;
        while(que.size() != 0){
            int size = que.size();
            while(size-->0){

                int[] rvtx = que.removeFirst();
                int x = rvtx[0];
                int y = rvtx[1];
                
                for(int d = 0; d < 4; d++){
                    int r = x + dir[d][0];
                    int c = y + dir[d][1];

                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c]==1){
                        que.addLast(new int[]{r,c});
                        grid[r][c] = 2;
                        freshOranges--;
                    }
                }
            }

            time++;
            if(freshOranges==0) return time;
        }

        return -1;
    }

    // leetcode 1091
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


    //leetcode 542
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

       
        while(que.size()!=0){
            int size = que.size();

            while(size-->0){
                int[] rvtx = que.removeFirst();
                int x = rvtx[0];
                int y = rvtx[1];


                for(int d=0; d<4; d++){
                    int r = x + dir[d][0];
                    int c = y + dir[d][1];

                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1 && vis[r][c] == -1){
                         que.addLast(new int[]{r,c});
                         vis[r][c] = vis[x][y] + 1;
                         countOnes--;
                    }
                }
            }

            if(countOnes==0) break;
        }
    
        return vis;
    }

    public int numBusesToDestination(int[][] routes, int src, int desti) {
        int n=routes.length;

        HashMap<Integer,ArrayList<Integer>> BusStandToBus=new HashMap<>();
        
        for(int i=0;i<n;i++){
            for(int busStand : routes[i]){
                BusStandToBus.putIfAbsent(busStand,new ArrayList<>());
                BusStandToBus.get(busStand).add(i);
            }
        }

        LinkedList<Integer> que=new LinkedList<>();
        
        HashSet<Integer> vis_BusStand=new HashSet<>();
        boolean[] vis_BUS=new boolean[n];

        que.addLast(src);
        vis_BusStand.add(src);
        int level = 0;

        while(que.size()!=0){
            int size=que.size();
            while(size-->0){

                int busStand = que.removeFirst();
                
                if(busStand==desti) return level;

                for(int bus : BusStandToBus.get(busStand)){
                    
                    if(vis_BUS[bus]) continue;

                    for(int busS : routes[bus]){
                        if(!vis_BusStand.contains(busS)){
                            que.addLast(busS);
                            vis_BusStand.add(busS);
                        }
                    }

                    vis_BUS[bus]=true;
                }

            }
            level++;
        }

        return -1;
    }

    //Leetcode 207.==================================================================

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int N=numCourses;
        ArrayList<Integer>[] graph=new ArrayList[N]; //vector<vector<int>> graph(N,vector<int>());
        for(int i=0;i<N;i++) graph[i]=new ArrayList<>();
        
        LinkedList<Integer> que=new LinkedList<>();
        int[] indegree=new int[N];

        for(int[] arr: prerequisites){
            int u=arr[0];
            int v=arr[1];
            graph[u].add(v);

            indegree[v]++;
        }

        for(int i=0;i<N;i++)
            if(indegree[i]==0)que.addLast(i);
        
        ArrayList<Integer> ans=new ArrayList<>();
        while(que.size()!=0){
            int rvtx=que.removeFirst();
            ans.add(rvtx);

            for(int e: graph[rvtx]){
                if(--indegree[e]==0) que.addLast(e);
            }
        }

        if(ans.size()!=N) ans.clear();
         
        return ans.size()!=0;
        
    }
    
    //Leetcode 210
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int N=numCourses;
       ArrayList<Integer>[] graph=new ArrayList[N];
       for(int i=0;i<N;i++) graph[i]=new ArrayList<>();
       
       LinkedList<Integer> que=new LinkedList<>();
       int[] indegree=new int[N];

       for(int[] arr: prerequisites){
           int v=arr[0];
           int u=arr[1];
           graph[u].add(v);

           indegree[v]++;
       }

       for(int i=0;i<N;i++)
           if(indegree[i]==0)que.addLast(i);
       
       ArrayList<Integer> ans=new ArrayList<>();
       while(que.size()!=0){
           int rvtx=que.removeFirst();
           ans.add(rvtx);

           for(int e: graph[rvtx]){
               if(--indegree[e]==0) que.addLast(e);
           }
       }

       if(ans.size()!=N) ans.clear();
       int[] ANS=new int[ans.size()];
       int i=0;
       for(int ele: ans) ANS[i++]=ele;
       return ANS;
   }
 
   //Leetcode 207
   public static boolean isCycleInTopo(int src,boolean[] vis,boolean[] myPath,ArrayList<Integer>[] graph){
      vis[src]=myPath[src]=true;

      boolean res = false;
      for(int e: graph[src]){
          if(!vis[e]) res = res ||  isCycleInTopo(e,vis,myPath,graph);
          else if(myPath[e]) return true;
      }

      myPath[src]=false;
      return res;
   }
   
   
   public boolean canFinish(int numCourses, int[][] prerequisites) {
    int N=numCourses;
    ArrayList<Integer>[] graph=new ArrayList[N]; //vector<vector<int>> graph(N,vector<int>());
    for(int i=0;i<N;i++) graph[i]=new ArrayList<>();
       
    for(int[] arr: prerequisites){
        int u=arr[0];
        int v=arr[1];
        
        graph[u].add(v);
    }

    boolean[] vis=new boolean[N];
    boolean[] myPath=new boolean[N];
    
    for(int i=0;i<N;i++){
        if(!vis[i] && isCycleInTopo(i,vis,myPath,graph)) return false;  
    }

    return true;
   }

   //Leetcode 329 
   public int longestIncreasingPath(int[][] matrix) {
      int n = matrix.length;
      int m = matrix[0].length;

      int[][] indegree=new int[n][m];
      int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
      
      for(int i=0;i<n;i++){
          for(int j=0;j<m;j++){
              
             for(int d=0; d<4; d++){
               int r = i + dir[d][0];
               int c = j + dir[d][1];

               if(r >= 0 && c >= 0 && r < n && c < m && matrix[r][c] > matrix[i][j]){
                   indegree[r][c]++;
               }
            }
        }
    }

    LinkedList<int[]> que=new LinkedList<>();
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
          if(indegree[i][j]==0) que.addLast(new int[]{i,j});
        }
    }

    int level=0;
    while(que.size()!=0){
        int size=que.size();
        while(size-->0){
            int[] rvtx=que.removeFirst();
            int u=rvtx[0];
            int v=rvtx[1];
        
            for(int d=0; d<4; d++){
               int r = u + dir[d][0];
               int c = v + dir[d][1];

               if(r >= 0 && c >= 0 && r < n && c < m && matrix[r][c] > matrix[u][v] && --indegree[r][c]==0){
                   que.addLast(new int[]{r,c});
                }
            }
        }
        level++;
    }

    return level;    
   }


    int[] par;
   public int findPar(int u){
       if(par[u]==u) return u;
       return par[u]=findPar(par[u]);
   }
   
   public int[] findRedundantConnection(int[][] edges) {
       int N=edges.length;
       par=new int[N+1];
       for(int i=0;i<=N;i++)
        par[i]=i;
        
        for(int[] e: edges){
            int p1=findPar(e[0]);
            int p2=findPar(e[1]);
            if(p1!=p2) par[p1]=p2;
            else return e;
        }

        return new int[0];
   }

   //Leetcode 200
   public int numIslands(char[][] grid) {
       
    if(grid.length==0 || grid[0].length==0) return 0;
    int n=grid.length;
    int m=grid[0].length;

       par=new int[n*m];
       int count=0;
       for(int i=0;i<n;i++)
           for(int j=0;j<m;j++){
              if(grid[i][j]=='1')
                {   count++;
                    par[i * m + j]=i * m + j;
                }
            }

       for(int i=0;i<n;i++){
           for(int j=0;j<m;j++){
              if(grid[i][j]=='1'){
               
                int p1=findPar(i*m+j);
               
                if(j + 1 < m && grid[i][j+1] == '1'){
                      int p2=findPar(i * m + j + 1);
                      if(p1!=p2){
                         par[p2]=p1;
                         count--;
                      }
                  }

                  if(i + 1 < n && grid[i+1][j] == '1'){
                    int p2=findPar((i + 1) * m + j);
                    if(p1!=p2){
                        par[p2]=p1;
                        count--;
                    }
                }
              }
           }
       }

       return count; 
   }

   //Leetcode 695
   public int maxAreaOfIsland(int[][] grid) {
       if(grid.length==0 || grid[0].length==0) return 0;
       int n=grid.length;
       int m=grid[0].length;

       par=new int[n*m];
       size=new int[n*m];
       
       int count=0;
       for(int i=0;i<n;i++)
           for(int j=0;j<m;j++){
              if(grid[i][j] == 1)
                {   count++;
                    par[i * m + j] = i * m + j;
                    size[i*m + j] = 1;
                }
            }

       for(int i=0;i<n;i++){
           for(int j=0;j<m;j++){
              if(grid[i][j]== 1){
               
                int p1=findPar(i*m+j);
               
                if(j + 1 < m && grid[i][j+1] == 1){
                      int p2=findPar(i * m + j + 1);
                      if(p1!=p2){
                         par[p2]=p1;
                         size[p1]+=size[p2];
                         count--;
                      }
                  }

                  if(i + 1 < n && grid[i+1][j] == 1){
                    int p2=findPar((i + 1) * m + j);
                    if(p1!=p2){
                        par[p2]=p1;
                        size[p1]+=size[p2];
                        count--;
                    }
                }
              }
           }
       }

       int max=0;
       for(int i=0;i<n*m;i++){
           if(i==par[i]){
            max=Math.max(max,size[i]);
           }
       }

       return max;
   }

   //Leetcode 1061
   public String smallestEquivalentString(String A, String B, String S) {
       par=new int[26];
       for(int i=0;i<26;i++) par[i] = i;

       for(int i=0;i<A.length();i++){
           int p1=findPar(A.charAt(i)-'a');
           int p2=findPar(B.charAt(i)-'a');

           par[p1]=Math.min(p1,p2);
           par[p2]=Math.min(p1,p2);
       }

       String ans="";
       for(int i=0;i<A.length();i++){
          ans += (char)(findPar(S.charAt(i)-'a') + 'a');
       }

       return ans;
   }

   //Leetcode 305
   public List<Integer> numIslands2(int n, int m, int[][] positions) {
    par=new int[n*m];
    for(int i=0;i<n*m;i++) par[i] = i;
    int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    
    int[][] mat=new int[n][m];

    int count=0;
    List<Integer> ans=new ArrayList<>();
    for(int[] ele:positions){
        int r = ele[0];
        int c = ele[1];
        if(mat[r][c]!=1){ 
        mat[r][c] = 1;
        count++;

        int p1=findPar(r*m+c);

        for(int d=0;d<4;d++){
            int x = r + dir[d][0];
            int y = c + dir[d][1];
            if(x>=0 && y>=0 && x<n && y<m && mat[x][y]==1){
                int p2=findPar(x*m+y);
                if(p1 != p2) {
                    count--;
                    par[p2]=p1;
                }
            }
         }
        }
        ans.add(count);
    }

    return ans;
}

//Leetcode 1168
public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<int[]> pipeConnection=new ArrayList<>();
        for(int i=0;i<wells.length;i++){
            pipeConnection.add(new int[]{0,i+1,wells[i]});
        }

        for(int[] p: pipes){
            pipeConnection.add(p);
        }

        Collections.sort(pipeConnection,(a,b)->{
            return a[2]-b[2]; // this-other 
        });

        par=new int[n+1];
        for(int i=0;i<=n;i++) par[i]=i;
        int cost=0;

        for(int[] p : pipeConnection){
            int p1=findPar(p[0]);
            int p2=findPar(p[1]);

            if(p1!=p2){
                par[p1]=p2;
                cost+=p[2];
            }
        }

        return cost;
}


public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {   
    ArrayList<int[]>[] graph=new ArrayList[n];
    for(int i=0;i<n;i++) graph[i]=new ArrayList<>();

    for(int[] f: flights){
        int u = f[0];
        int v = f[1];
        int w = f[2];

        graph[u].add(new int[]{v,w});
    }

    // u , cost , k
    PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->{
        return a[1]-b[1];   // this- other
    });

    pq.add(new int[]{src,0,K + 1});

    while(pq.size()!=0){
        int[] rvtx = pq.remove();
        int u = rvtx[0];
        int cost = rvtx[1];
        int stops = rvtx[2];

        if(u == dst){
          return cost;
        }

        if(stops==0) continue;

        for(int[] e : graph[u]){
            int nbr = e[0];
            int csf = cost + e[1];
            int stopsSoFar= stops - 1;
            pq.add(new int[]{nbr,csf,stopsSoFar});
        }
    }
    return -1;
}

//Leetcode 743

public int networkDelayTime(int[][] times, int N, int src) {
    ArrayList<int[]>[] graph=new ArrayList[N+1];
    for(int i=0;i<=N;i++) graph[i]=new ArrayList<>();

    for(int[] f: times){
        int u = f[0];
        int v = f[1];
        int w = f[2];

        graph[u].add(new int[]{v,w});
    }

     // u , totalTime
     PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->{
        return a[1]-b[1];   // this- other
    });

    pq.add(new int[]{src,0});
    int[] bestTime=new int[N+1]; 
    Arrays.fill(bestTime,-1);

    while(pq.size()!=0){
        int[] rvtx = pq.remove();
        int u = rvtx[0];
        int time = rvtx[1];

        if(bestTime[u]!=-1) continue;
        
        bestTime[u] = time;
        for(int[] e : graph[u]){
            int nbr = e[0];
            int t = time + e[1];
            if(bestTime[nbr] == -1)
                pq.add(new int[]{nbr, t});
        }
    }

    int ans=0;
    for(int i=1 ;i<=N;i++){
        if(bestTime[i] == -1) return -1;
        else ans = Math.max(ans,bestTime[i]);
    }
    return ans;
}

//Leetcode 685

int[] unionPar;
public int findUnionPar(int u){
    if(unionPar[u] == -1) return u;
    return unionPar[u] = findUnionPar(unionPar[u]); 
}

public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int a = -1,b = -1,cycle = -1;
        
        unionPar = new int[n+1];
        Arrays.fill(unionPar,-1);

        int[] actualPar=new int[n+1];
        Arrays.fill(actualPar,-1);

        for(int i=0; i < edges.length; i++){
            int p = edges[i][0];
            int c = edges[i][1];

            if(actualPar[c] != -1){
                a = actualPar[c];
                b = i;
                continue;
            }

            actualPar[c] = i; 
            int rootInUnionTree = findUnionPar(p);
            if(rootInUnionTree == c){
                cycle = i;
            }else{
                unionPar[c] = rootInUnionTree;
            }
        }

        if(cycle == -1) return edges[b];
        else if( b == -1) return edges[cycle];

        return edges[a];
}


}