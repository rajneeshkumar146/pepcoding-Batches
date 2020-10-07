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

}