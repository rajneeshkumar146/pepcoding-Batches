#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int orangesRotting(vector<vector<int>> &grid)
{
    int n = grid.size(), m = grid[0].size();
    queue<int> que;

    int time = 0, orangeCount = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 2)
                que.push(i * m + j);
            else if (grid[i][j] == 1)
                orangeCount++;
        }
    }

    if (orangeCount == 0)
        return 0;

    vector<vector<int>> dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int idx = que.front();
            que.pop();

            int sr = idx / m, sc = idx % m;

            for (vector<int> &d : dir)
            {
                int r = sr + d[0];
                int c = sc + d[1];

                if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                {
                    grid[r][c] = 2;
                    que.push(r * m + c);
                    orangeCount--;
                    if (orangeCount == 0)
                        return time + 1;
                }
            }
        }
        time++;
    }

    return -1;
}