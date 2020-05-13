#include <iostream>
#include <vector>
using namespace std;

//Leetcode: 1219.===================================================

int dirA[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
int getMaximumGold_(vector<vector<int>> &grid, int r, int c, int n, int m)
{
    grid[r][c] = -grid[r][c];
    int ans = 0;

    for (int d = 0; d < 4; d++)
    {
        int x = r + dirA[d][0];
        int y = c + dirA[d][1];
        if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] > 0)
            ans = max(ans, getMaximumGold_(grid, x, y, n, m));
    }

    grid[r][c] = -grid[r][c];
    return ans + grid[r][c];
}

int getMaximumGold(vector<vector<int>> &grid)
{
    int ans = 0;
    int n = grid.size();
    int m = grid[0].size();
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] != 0)
                ans = max(ans, getMaximumGold_(grid, i, j, n, m));
        }
    }
    return ans;
}

//Leetcode: 980.=====================================================

int uniquePathsIII_(vector<vector<int>> &grid, int r, int c, int freeCell, int n, int m)
{
    if (freeCell == 1)
        return grid[r][c] == 2 ? 1 : 0;
    
    int temp=grid[r][c];
    grid[r][c] = -1;
    int count = 0;

    for (int d = 0; d < 4; d++)
    {
        int x = r + dirA[d][0];
        int y = c + dirA[d][1];
        if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] >= 0)
            count += uniquePathsIII_(grid, x, y, freeCell - 1, n, m);
    }

    grid[r][c] = temp;
    return count;
}

int uniquePathsIII(vector<vector<int>> &grid)
{
    int n = grid.size();
    int m = grid[0].size();
    int sr, sc, freeCell = 0;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {

            if (grid[i][j] != -1)
                freeCell++;

            if (grid[i][j] == 1)
            {
                sr = i;
                sc = j;
            }
        }
    }

    return uniquePathsIII_(grid,sr,sc,freeCell,n,m);
}

//equi set.=============================================================



