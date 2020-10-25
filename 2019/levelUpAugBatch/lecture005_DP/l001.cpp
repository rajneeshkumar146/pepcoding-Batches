#include <iostream>
#include <vector>

using namespace std;

void print(vector<int> &arr)
{
    for (int ele : arr)
    {
        cout << ele << " ";
    }
    cout << endl;
}

void print2D(vector<vector<int>> &arr)
{
    for (vector<int> ar : arr)
    {
        print(ar);
    }
    cout << endl;
}

//Two Pointer Type.========================================================

int fib01(int n, vector<int> &dp)
{
    if (n <= 1)
        return dp[n] = n;

    if (dp[n] != 0)
        return dp[n];

    int a = fib01(n - 1, dp);
    int b = fib01(n - 2, dp);

    return dp[n] = a + b;
}

int fib01_DP(int N, vector<int> &dp)
{
    for (int n = 0; n <= N; n++)
    {
        if (n <= 1)
        {
            dp[n] = n;
            continue;
        }

        int a = dp[n - 1]; //fib01(n - 1, dp);
        int b = dp[n - 2]; //fib01(n - 2, dp);

        dp[n] = a + b;
    }

    return dp[N];
}

int fib01_opti(int N)
{
    int a = 0, b = 1;
    for (int n = 2; n <= N; n++)
    {
        int sum = a + b;
        a = b;
        b = sum;
    }

    return b;
}

void fibo()
{
    int n = 7;
    vector<int> dp(n + 1, 0);
    // cout << fib01(n, dp) << endl;
    cout << fib01_DP(n, dp) << endl;
    cout << fib01_opti(n) << endl;

    print(dp);
}

int mazePath(int sr, int sc, int er, int ec, vector<vector<int>> &dp)
{
    if (sr == er && sc == ec)
    {
        return dp[sr][sc] = 1;
    }

    if (dp[sr][sc] != 0)
        return dp[sr][sc];

    int count = 0;
    if (sr + 1 <= er)
        count += mazePath(sr + 1, sc, er, ec, dp);
    if (sc + 1 <= ec)
        count += mazePath(sr, sc + 1, er, ec, dp);
    if (sr + 1 <= er && sc + 1 <= ec)
        count += mazePath(sr + 1, sc + 1, er, ec, dp);

    return dp[sr][sc] = count;
}

int mazePathDP(int SR, int SC, int er, int ec, vector<vector<int>> &dp)
{
    for (int sr = er; sr >= 0; sr--)
    {
        for (int sc = ec; sc >= 0; sc--)
        {

            if (sr == er && sc == ec)
            {
                dp[sr][sc] = 1;
                continue;
            }

            int count = 0;
            if (sr + 1 <= er)
                count += dp[sr + 1][sc]; //mazePath(sr + 1, sc, er, ec, dp);
            if (sc + 1 <= ec)
                count += dp[sr][sc + 1]; //mazePath(sr, sc + 1, er, ec, dp);
            if (sr + 1 <= er && sc + 1 <= ec)
                count += dp[sr + 1][sc + 1]; //mazePath(sr + 1, sc + 1, er, ec, dp);

            dp[sr][sc] = count;
        }
    }

    return dp[SR][SC];
}

int mazePath_Multiple(int sr, int sc, int er, int ec, vector<vector<int>> &dp)
{
    if (sr == er && sc == ec)
    {
        return dp[sr][sc] = 1;
    }

    if (dp[sr][sc] != 0)
        return dp[sr][sc];

    int count = 0;
    for (int jump = 1; sr + jump <= er; jump++)
        count += mazePath_Multiple(sr + jump, sc, er, ec, dp);
    for (int jump = 1; sc + jump <= ec; jump++)
        count += mazePath_Multiple(sr, sc + jump, er, ec, dp);
    for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
        count += mazePath_Multiple(sr + jump, sc + jump, er, ec, dp);

    return dp[sr][sc] = count;
}

int mazePath_MultipleDP(int SR, int SC, int er, int ec, vector<vector<int>> &dp)
{
    for (int sr = er; sr >= 0; sr--)
    {
        for (int sc = ec; sc >= 0; sc--)
        {

            if (sr == er && sc == ec)
            {
                dp[sr][sc] = 1;
                continue;
            }

            int count = 0;
            for (int jump = 1; sr + jump <= er; jump++)
                count += dp[sr + jump][sc]; //mazePath_Multiple(sr + jump, sc, er, ec, dp);
            for (int jump = 1; sc + jump <= ec; jump++)
                count += dp[sr][sc + jump]; //mazePath_Multiple(sr, sc + jump, er, ec, dp);
            for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
                count += dp[sr + jump][sc + jump]; //mazePath_Multiple(sr + jump, sc + jump, er, ec, dp);

            dp[sr][sc] = count;
        }
    }

    return dp[SR][SC];
}

void mazePath()
{
    int n = 4;
    int m = 4;
    vector<vector<int>> dp(n, vector<int>(m, 0)); // int[][] dp = new int[n][m];
    // cout << mazePath(0, 0, n - 1, m - 1, dp) << endl;
    // cout << mazePathDP(0, 0, n - 1, m - 1, dp) << endl;

    // cout << mazePath_Multiple(0, 0, n - 1, m - 1, dp) << endl;
    cout << mazePath_MultipleDP(0, 0, n - 1, m - 1, dp) << endl;

    print2D(dp);
}

// Leetcode 64
int minPathSum(int sr, int sc, int er, int ec, vector<vector<int>> &grid, vector<vector<int>> &dp)
{
    if (sr == er && sc == ec)
    {
        return dp[sr][sc] = grid[sr][sc];
    }

    if (dp[sr][sc] != 1e8)
        return dp[sr][sc];

    if (sr + 1 <= er)
        dp[sr][sc] = min(dp[sr][sc], minPathSum(sr + 1, sc, er, ec, grid, dp));
    if (sc + 1 <= ec)
        dp[sr][sc] = min(dp[sr][sc], minPathSum(sr, sc + 1, er, ec, grid, dp));

    return dp[sr][sc] += grid[sr][sc];
}

int minPathSumDP(int sr, int sc, int er, int ec, vector<vector<int>> &grid, vector<vector<int>> &dp)
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
                dp[sr][sc] = min(dp[sr][sc], dp[sr + 1][sc]);
            if (sc + 1 <= ec)
                dp[sr][sc] = min(dp[sr][sc], dp[sr][sc + 1]);

            dp[sr][sc] += grid[sr][sc];
        }
    }

    return dp[0][0];
}

int minPathSum(vector<vector<int>> &grid)
{
    if (grid.size() == 0 || grid[0].size() == 0)
        return 0;
    int n = grid.size();
    int m = grid[0].size();

    vector<vector<int>> dp(n, vector<int>(m, 1e8));
    int ans = minPathSum(0, 0, n - 1, m - 1, grid, dp);

    return ans;
}
int goldMine(int r, int c, int n, int m, vector<vector<int>> &arr, vector<vector<int>> &dp, vector<vector<int>> &dir)
{
    if (c == m - 1)
    {
        return dp[r][c] = arr[r][c];
    }

    if (dp[r][c] != 0)
        return dp[r][c];

    int maxVal = 0;
    for (int d = 0; d < 3; d++)
    {
        int x = r + dir[d][0];
        int y = c + dir[d][1];

        if (x >= 0 && y >= 0 && x < n && y < m)
        {
            maxVal = max(maxVal, goldMine(x, y, n, m, arr, dp, dir) + arr[r][c]);
        }
    }

    return dp[r][c] = maxVal;
}

int goldMineDP(int R, int C, int n, int m, vector<vector<int>> &arr, vector<vector<int>> &dp, vector<vector<int>> &dir)
{
    for (int c = C - 1; c >= 0; c--)
    {
        for (int r = R - 1; r >= 0; r--)
        {
            if (c == m - 1)
            {
                dp[r][c] = arr[r][c];
                continue;
            }

            int maxVal = 0;
            for (int d = 0; d < 3; d++)
            {
                int x = r + dir[d][0];
                int y = c + dir[d][1];

                if (x >= 0 && y >= 0 && x < n && y < m)
                {
                    maxVal = max(maxVal, dp[x][y] + arr[r][c]);
                }
            }

            dp[r][c] = maxVal;
        }
    }

    int maxVal = 0;
    for (int r = 0; r < n; r++)
    {
        maxVal = max(maxVal, dp[r][0]);
    }
}

void goldMine()
{
    vector<vector<int>> arr;
    int n = arr.size();
    int m = arr[0].size();

    vector<vector<int>> dp(n, vector<int>(m, 0));
    vector<vector<int>> dir{{-1, 1}, {0, 1}, {1, 1}};

    int maxVal = 0;
    for (int r = 0; r < n; r++)
    {
        maxVal = max(maxVal, goldMine(r, 0, n, m, arr, dp, dir));
    }

    cout << maxVal << endl;
}

int friendsPairing(int n, vector<int> &dp)
{
    if (n <= 1)
    {
        return dp[n] = 1;
    }

    if (dp[n] != 0)
        return dp[n];

    int single = friendsPairing(n - 1, dp);
    int pairUp = friendsPairing(n - 2, dp) * (n - 1);

    return dp[n] = single + pairUp;
}

int friendsPairing(int N, vector<int> &dp)
{

    int c = (int)1e9 + 7;
    for (int n = 0; n <= N; n++)
    {
        if (n <= 1)
        {
            dp[n] = 1;
            continue;
        }

        // int single = dp[n-1];//friendsPairing(n - 1, dp);
        // int pairUp = dp[n-2]*(n-1);//friendsPairing(n - 2, dp) * (n - 1);

        // dp[n] = single + pairUp;

        dp[n] = (dp[n-1] % c + (dp[n-2] % c * (n-1) % c)%c)%c;
    }

    return dp[N];
}

int minCostClimbingStairs(int n,vector<int>& cost,vector<int>& dp) {
        if(n<=1) return dp[n] = cost[n];
        
        if(dp[n]!=0) return dp[n];
        
        int val = min(minCostClimbingStairs(n-1,cost,dp),minCostClimbingStairs(n-2,cost,dp));
        
        return dp[n] = val +  ((n < cost.size()) ? cost[n] : 0);
    }
    
    int minCostClimbingStairs(vector<int>& cost) {
        if(cost.size() == 0) return 0;
        
        int n = cost.size();
        vector<int> dp(n+1,0);
        
        return minCostClimbingStairs(n,cost,dp);
}

int friendsPairing()
{
    int n = 10;
    vector<int> dp(n+1,0);

}

void twoPointer()
{
    // fibo();
    mazePath();
}

void solve()
{
    twoPointer();
}

int main()
{
    solve();
    return 0;
}