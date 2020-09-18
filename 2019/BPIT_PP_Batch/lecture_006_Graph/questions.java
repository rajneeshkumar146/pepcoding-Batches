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
}