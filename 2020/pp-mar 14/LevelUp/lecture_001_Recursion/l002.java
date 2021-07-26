import java.util.ArrayList;
import java.util.Arrays;

public class l002 {

    public int getMaximumGold(int[][] grid, int sr, int sc, int[][] dir) {
        int maxGold = 0, n = grid.length, m = grid[0].length;
        int val = grid[sr][sc];
        grid[sr][sc] = -grid[sr][sc];
        for (int d = 0; d < 4; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] > 0) {
                int recGold = getMaximumGold(grid, r, c, dir);
                if (recGold > maxGold)
                    maxGold = recGold;
            }
        }
        grid[sr][sc] = -grid[sr][sc];

        return maxGold + val;
    }

    public int getMaximumGold(int[][] grid) {
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int maxGold = 0, n = grid.length, m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] > 0) {
                    maxGold = Math.max(maxGold, getMaximumGold(grid, i, j, dir));
                }
            }
        }

        return maxGold;
    }

    // https://www.geeksforgeeks.org/gold-mine-problem/

    
}