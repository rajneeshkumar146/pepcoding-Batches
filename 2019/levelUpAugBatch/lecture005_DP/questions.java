import java.util.Arrays;
public class questions{

public int minPathSum(int sr, int sc, int er, int ec, int[][] grid, int[][] dp)
{
    if (sr == er && sc == ec)
    {
        return dp[sr][sc] = grid[sr][sc];
    }

    if (dp[sr][sc] != (int)1e8)
        return dp[sr][sc];

    if (sr + 1 <= er)
        dp[sr][sc] = Math.min(dp[sr][sc], minPathSum(sr + 1, sc, er, ec, grid, dp));
    if (sc + 1 <= ec)
        dp[sr][sc] = Math.min(dp[sr][sc], minPathSum(sr, sc + 1, er, ec, grid, dp));

    return dp[sr][sc] += grid[sr][sc];
}

int minPathSumDP(int sr, int sc, int er, int ec, int[][] grid, int[][] dp)
{
    for (sr = er; sr >= 0; sr--)
    {
        for (sc = ec; sc >= 0; sc--)
        {  
            if (sr == er && sc == ec)
            {
                dp[sr][sc] = grid[sr][sc];
                continue;
            }

            if (sr + 1 <= er)
                dp[sr][sc] = Math.min(dp[sr][sc], dp[sr+1][sc]);
            if (sc + 1 <= ec)
                dp[sr][sc] = Math.min(dp[sr][sc], dp[sr][sc + 1]);

            dp[sr][sc] += grid[sr][sc];
        }
    }

    return dp[0][0];
}

    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
          return 0;
        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];
        for(int[] d: dp) Arrays.fill(d,(int)1e8);

        int ans = minPathSum(0, 0, n - 1, m - 1, grid, dp);

        return ans;        
    }
}