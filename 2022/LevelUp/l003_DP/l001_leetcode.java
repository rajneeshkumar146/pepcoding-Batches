public class l001_leetcode {
    // 62
    public int uniquePaths(int n, int m) {
        int ER = n - 1, EC = m - 1;
        int[][] dp = new int[n][m];
        int[][] dir = { { -1, 0 }, { 0, -1 } };
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    if (r >= 0 && c >= 0 && r < n && c < m) {
                        count += dp[r][c];
                    }
                }
                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    // 63
    public int uniquePathsWithObstacles(int[][] grid) {
        int n = grid.length, m = grid[0].length, ER = n - 1, EC = m - 1;
        if (grid[0][0] == 1 || grid[ER][EC] == 1)
            return 0;
        int[][] dp = new int[n][m];
        int[][] dir = { { -1, 0 }, { 0, -1 } };
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = 1;
                    continue;
                }

                int count = 0;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0) {
                        count += dp[r][c];
                    }
                }
                dp[er][ec] = count;
            }
        }

        return dp[ER][EC];
    }

    // 396
    public int maxRotateFunction(int[] nums) {
        int sum = 0, n = nums.length, maxSum = 0, sumSF = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            sumSF += i * nums[i];
        }

        maxSum = sumSF;
        for (int i = 0; i < n; i++) {
            sumSF = sumSF - sum + n * nums[i];
            maxSum = Math.max(maxSum, sumSF);
        }

        return maxSum;
    }

    // 64
    public int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length, ER = n - 1, EC = m - 1;
        int[][] dp = new int[n][m];
        int[][] dir = { { -1, 0 }, { 0, -1 } };
        for (int er = 0; er <= ER; er++) {
            for (int ec = 0; ec <= EC; ec++) {
                if (er == 0 && ec == 0) {
                    dp[er][ec] = grid[er][ec];
                    continue;
                }

                int min = (int) 1e9;
                for (int d = 0; d < dir.length; d++) {
                    int r = er + dir[d][0];
                    int c = ec + dir[d][1];
                    if (r >= 0 && c >= 0 && r < n && c < m) {
                        min = Math.min(min, dp[r][c]);
                    }
                }
                dp[er][ec] = min + grid[er][ec];
            }
        }

        return dp[ER][EC];
    }

    // https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/1/
    
    // https://www.geeksforgeeks.org/count-the-number-of-ways-to-divide-n-in-k-groups-incrementally/

}
