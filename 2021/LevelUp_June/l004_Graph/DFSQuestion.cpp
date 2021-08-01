#include <iostream>
#include <vector>

using namespace std;

//200

void dfs_numIslands(vector<vector<char>> &grid, int i, int j, vector<vector<int>> &dir)
{
    int n = grid.size(), m = grid[0].size();
    grid[i][j] = '0';
    for (int d = 0; d < dir.size(); d++)
    {
        int r = i + dir[d][0];
        int c = j + dir[d][1];

        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1')
            dfs_numIslands(grid, r, c, dir);
    }
}

int numIslands(vector<vector<char>> &grid)
{
    vector<vector<int>> dir{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    int n = grid.size(), m = grid[0].size(), components = 0;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == '1')
            {
                components++;
                dfs_numIslands(grid, i, j, dir);
            }
        }
    }

    return components;
}

int dfs_area(vector<vector<int>> &grid, int i, int j, vector<vector<int>> &dir)
{
    int n = grid.size(), m = grid[0].size(), size = 0;
    grid[i][j] = 0;
    for (int d = 0; d < dir.size(); d++)
    {
        int r = i + dir[d][0];
        int c = j + dir[d][1];

        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
            size += dfs_area(grid, r, c, dir);
    }

    return size + 1;
}

//695
int maxAreaOfIsland(vector<vector<int>> &grid)
{
    vector<vector<int>> dir{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    int n = grid.size(), m = grid[0].size(), maxArea = 0;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 1)
            {
                int area = dfs_area(grid, i, j, dir);
                maxArea = max(area, maxArea);
            }
        }
    }

    return maxArea;
}

//463
int islandPerimeter(vector<vector<int>> &grid)
{
    vector<vector<int>> dir{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    int n = grid.size(), m = grid[0].size(), onceCount = 0, nbrCount;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 1)
            {
                onceCount++;
                for (int d = 0; d < dir.size(); d++)
                {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                        nbrCount++;
                }
            }
        }
    }

    return 4 * onceCount - nbrCount;
}

//130

void dfs_surrounded(vector<vector<char>> &grid, int i, int j, vector<vector<int>> &dir)
{
    int n = grid.size(), m = grid[0].size();
    grid[i][j] = '$';
    for (int d = 0; d < dir.size(); d++)
    {
        int r = i + dir[d][0];
        int c = j + dir[d][1];

        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 'O')
        {
            dfs_surrounded(grid, r, c, dir);
        }
    }
}

void solve(vector<vector<char>> &grid)
{
    vector<vector<int>> dir{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    int n = grid.size(), m = grid[0].size();

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (i == 0 || j == 0 || i == n - 1 || j == m - 1)
            {
                if (grid[i][j] == 'O')
                    dfs_surrounded(grid, i, j, dir);
            }
        }
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 'O')
                grid[i][j] = 'X';
            else if (grid[i][j] == '$')
                grid[i][j] = 'O';
        }
    }
}

//694
