#include <iostream>
#include <queue>

#define pii pair<int, int>
using namespace std;

int orangesRotting(vector<vector<int>> &grid)
{
    int n = grid.size();
    int m = grid[0].size();

    queue<pii> que;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 2)
                que.push({i, j});
        }
    }

    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            pii p = que.front();
            que.pop();

            int i = p.first;
            int j = p.second;

            if (i + 1 < n && grid[i + 1][j] == 1)
            {
                que.push({i + 1, j});
                grid[i + 1][j] = 2;
            }
            if (i - 1 >= 0 && grid[i - 1][j] == 1)
            {
                que.push({i - 1, j});
                grid[i - 1][j] = 2;
            }
            if (j + 1 < m && grid[i][j + 1] == 1)
            {
                que.push({i, j + 1});
                grid[i][j + 1] = 2;
            }
            if (j - 1 >= 0 && grid[i][j - 1] == 1)
            {
                que.push({i, j - 1});
                grid[i][j - 1] = 2;
            }
        }
        level++;
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 1)
                return -1;
        }
    }

    return level > 0 ? level - 1 : 0;
}