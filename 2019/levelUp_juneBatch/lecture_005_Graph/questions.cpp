#include <iostream>
#include <vector>

using namespace std;

//Leetcode 200.=========================================================================
vector<vector<int>> dir{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
void numIslands_(vector<vector<char>> &grid, int sr, int sc)
{
    grid[sr][sc] = 0;
    for (int d = 0; d < 4; d++)
    {
        int r = sr + dir[d][0];
        int c = sc + dir[d][1];

        if (r >= 0 && c >= 0 && r < grid.size() && c < grid[0].size() && grid[r][c] == '1')
            numIslands_(grid, r, c);
    }
}

int numIslands(vector<vector<char>> &grid)
{
    if (grid.size() == 0 || grid[0].size() == 0)
        return 0;
    int count = 0;
    for (int i = 0; i < grid.size(); i++)
        for (int j = 0; j < grid[0].size(); j++)
        {
            if (grid[i][j] == '1')
            {
                count++;
                numIslands_(grid, i, j);
            }
        }

    return count;
}

//Leetcode 695
int maxAreaOfIsland_(vector<vector<int>> &grid, int sr, int sc)
{
    grid[sr][sc] = 0;
    int count = 0;
    for (int d = 0; d < 4; d++)
    {
        int r = sr + dir[d][0];
        int c = sc + dir[d][1];

        if (r >= 0 && c >= 0 && r < grid.size() && c < grid[0].size() && grid[r][c] == 1)
            count += maxAreaOfIsland_(grid, r, c);
    }
    return count + 1;
}

int maxAreaOfIsland(vector<vector<int>> &grid)
{
    if (grid.size() == 0 || grid[0].size() == 0)
        return 0;
    int maxArea = 0;
    for (int i = 0; i < grid.size(); i++)
        for (int j = 0; j < grid[0].size(); j++)
        {
            if (grid[i][j] == 1)
            {
                maxArea = max(maxArea, maxAreaOfIsland_(grid, i, j));
            }
        }

    return maxArea;
}

//Leetcode 463
int islandPerimeter(vector<vector<int>> &grid)
{
    int count = 0;
    int nbrs = 0;
    for (int i = 0; i < grid.size(); i++)
        for (int j = 0; j < grid[0].size(); j++)
        {
            if (grid[i][j] == 1)
            {
                count++;
                for (int d = 0; d < 4; d++)
                {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if (r >= 0 && c >= 0 && r < grid.size() && c < grid[0].size() && grid[r][c] == 1)
                        nbrs++;
                }
            }
        }

    return 4 * count - nbrs;
}

//Leetcode 130.

void solve_(vector<vector<char>> &grid, int sr, int sc)
{

    grid[sr][sc] = '#';
    for (int d = 0; d < 4; d++)
    {
        int r = sr + dir[d][0];
        int c = sc + dir[d][1];

        if (r >= 0 && c >= 0 && r < grid.size() && c < grid[0].size() && grid[r][c] == 'O')
            solve_(grid, r, c);
    }
}

void solve(vector<vector<char>> &grid)
{
    if (grid.size() == 0 || grid[0].size() == 0)
        return;
    int n = grid.size();
    int m = grid[0].size();

    for (int i = 0; i < grid.size(); i++)
    {
        if (grid[i][0] == 'O')
            solve_(grid, i, 0);
        if (grid[i][m - 1] == 'O')
            solve_(grid, i, m - 1);
    }

    for (int i = 0; i < grid[0].size(); i++)
    {
        if (grid[0][i] == 'O')
            solve_(grid, 0, i);
        if (grid[n - 1][i] == 'O')
            solve_(grid, n - 1, i);
    }

    for (int i = 0; i < grid.size(); i++)
    {
        for (int j = 0; j < grid[0].size(); j++)
        {
            if (grid[i][j] == 'O')
                grid[i][j] = 'X';
            else if (grid[i][j] == '#')
                grid[i][j] = 'O';
        }
    }
}
