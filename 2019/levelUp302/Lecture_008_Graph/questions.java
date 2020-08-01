import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;


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




}