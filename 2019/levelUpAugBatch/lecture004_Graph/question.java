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