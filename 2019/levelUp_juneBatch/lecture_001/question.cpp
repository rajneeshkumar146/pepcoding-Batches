#include <iostream>
#include <vector>
using namespace std;

int n, m;
vector<vector<int>> dirA{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

//Leetcode 980:========================================================

int uniquePathsIII_(int sr, int sc, int freeCell, vector<vector<int>> &grid)
{
    if (freeCell == 1)
        return grid[sr][sc] == 2 ? 1 : 0;
    int count = 0;
    int temp = grid[sr][sc];
    grid[sr][sc] = -1; //mark visited
    for (int d = 0; d < dirA.size(); d++)
    {
        int r = sr + dirA[d][0];
        int c = sc + dirA[d][1];
        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] >= 0)
            count += uniquePathsIII_(r, c, freeCell - 1, grid);
    }

    grid[sr][sc] = temp; //mark unvisited
    return count;
}

int uniquePathsIII(vector<vector<int>> &grid)
{
    n = grid.size();
    m = grid[0].size();

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

    return uniquePathsIII_(sr, sc, freeCell, grid);
}

//Leetcode 1219:=========================================================

int getMaximumGold(int sr, int sc, vector<vector<int>> &grid)
{

    grid[sr][sc] = -grid[sr][sc];
    int maxGold = 0;
    for (int d = 0; d < dirA.size(); d++)
    {
        int r = sr + dirA[d][0];
        int c = sc + dirA[d][1];
        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] > 0)
            maxGold = max(maxGold, getMaximumGold(r, c, grid));
    }

    grid[sr][sc] = -grid[sr][sc];
    return maxGold + grid[sr][sc];
}

int getMaximumGold(vector<vector<int>> &grid)
{
    n = grid.size();
    m = grid[0].size();
    int maxGold = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] != 0)
            {
                maxGold = max(maxGold, getMaximumGold(i, j, grid));
            }
        }
    }
    return maxGold;
}
