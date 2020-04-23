#include <iostream>
#include <vector>
using namespace std;

//leetcode 130=======================================================================

void surroundedRegion_DFS(int r, int c, int n, int m, vector<vector<char>> &board)
{
    if (board[r][c] != 'O')
        return;

    board[r][c] = '#';
    if (r - 1 >= 0)
        surroundedRegion_DFS(r - 1, c, n, m, board);
    if (r + 1 < n)
        surroundedRegion_DFS(r + 1, c, n, m, board);
    if (c - 1 >= 0)
        surroundedRegion_DFS(r, c - 1, n, m, board);
    if (c + 1 < m)
        surroundedRegion_DFS(r, c + 1, n, m, board);
}

void surroundedRegion(vector<vector<char>> &board)
{
    if (board.size() == 0)
        return;

    int n = board.size();
    int m = board[0].size();

    for (int i = 0; i < n; i++)
    {
        if (board[i][0] == 'O')
            surroundedRegion_DFS(i, 0, n, m, board);
        if (board[i][m - 1] == 'O')
            surroundedRegion_DFS(i, m - 1, n, m, board);
    }

    for (int i = 0; i < m; i++)
    {
        if (board[0][i] == 'O')
            surroundedRegion_DFS(0, i, n, m, board);
        if (board[n - 1][i] == 'O')
            surroundedRegion_DFS(n - 1, i, n, m, board);
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (board[i][j] == '#') // not a surrounded region.
                board[i][j] = 'O';
            else if (board[i][j] == 'O') // surrounded region.
                board[i][j] = 'X';
        }
    }
}

//leetcode 200=============================================================

void dfs_numIsLand(int r, int c, int n, int m, vector<vector<char>> &board)
{
    if (board[r][c] != '1')
        return;

    board[r][c] = '0';
    if (r - 1 >= 0)
        dfs_numIsLand(r - 1, c, n, m, board);
    if (r + 1 < n)
        dfs_numIsLand(r + 1, c, n, m, board);
    if (c - 1 >= 0)
        dfs_numIsLand(r, c - 1, n, m, board);
    if (c + 1 < m)
        dfs_numIsLand(r, c + 1, n, m, board);
}

int numIslands(vector<vector<char>> &board)
{
    if (board.size() == 0)
        return 0;

    int n = board.size();
    int m = board[0].size();
    int count = 0;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (board[i][j] == '1')
            {
                count++;
                dfs_numIsLand(i, j, n, m, board);
            }
        }
    }

    return count;
}

// leetcode 695===========================================================

int dfs_numIsLandArea(int r, int c, int n, int m, vector<vector<int>> &board)
{
    if (board[r][c] != 1)
        return 0;

    board[r][c] = 0;
    int count = 0;

    if (r - 1 >= 0)
        count += dfs_numIsLandArea(r - 1, c, n, m, board);
    if (r + 1 < n)
        count += dfs_numIsLandArea(r + 1, c, n, m, board);
    if (c - 1 >= 0)
        count += dfs_numIsLandArea(r, c - 1, n, m, board);
    if (c + 1 < m)
        count += dfs_numIsLandArea(r, c + 1, n, m, board);

    return count + 1;
}

int maxAreaOfIsland(vector<vector<int>> &grid)
{

    if (board.size() == 0)
        return 0;

    int n = board.size();
    int m = board[0].size();
    int area = 0;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (board[i][j] == 1)
            {

                area = max(area, dfs_numIsLandArea(i, j, n, m, board));
            }
        }
    }
    return area;
}

//leetcode 463======================================================

int islandPerimeter(vector<vector<int>> &grid)
{
    int totalOnes = 0;
    int commonRegion = 0;

    int n = board.size();
    int m = board[0].size();

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (board[i][j] == 1)
            {
                totalOnes++;
                if (j + 1 < m && board[i][j + 1] == 1)
                    commonRegion++;
                if (i + 1 < n && board[i + 1][j] == 1)
                    commonRegion++;
            }
        }
    }
    return (4 * totalOnes - 2 * commonRegion);
}

//Leetcode : 1091.=========================================================

int shortestPathBinaryMatrix(vector<vector<int>> &grid)
{
    int n = grid.size();
    if (n == 0)
        return -1;
    int m = grid[0].size();
    if (m == 0)
        return -1;

    if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1)
        return -1;

    int dirA[8][2] = {{-1, -1}, {-1, 0}, {0, -1}, {-1, 1}, {1, -1}, {0, 1}, {1, 0}, {1, 1}};
    queue<int> que;
    que.push(0);
    grid[0][0] = 1;

    int level = 1;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            int r = rvtx / m;
            int c = rvtx % m;

            if (r == n - 1 && c == m - 1)
                return level;

            for (int d = 0; d < 8; d++)
            {
                int x = r + dirA[d][0];
                int y = c + dirA[d][1];

                if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == 0)
                {
                    que.push((x * m + y));
                    grid[x][y] = 1;
                }
            }
        }
        level++;
    }

    return -1;
}

//leetcode 286.=============================================================

void wallsAndGates(vector<vector<int>> &grid)
{
    if (grid.size() == 0 || grid[0].size() == 0)
        return;

    int n = grid.size();
    int m = grid[0].size();

    int dirA[4][2] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    queue<int> que;

    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            if (grid[i][j] == 0)
                que.push((i * m + j));

    int level = 1;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            int r = rvtx / m;
            int c = rvtx % m;

            for (int d = 0; d < 4; d++)
            {
                int x = r + dirA[d][0];
                int y = c + dirA[d][1];

                if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == 2147483647)
                {
                    que.push((x * m + y));
                    grid[x][y] = level;
                }
            }
        }
        level++;
    }
}

//leetcode 994.===========================================================

int orangesRotting(vector<vector<int>> &grid)
{
    if (grid.size() == 0 || grid[0].size() == 0)
        return;

    int n = grid.size();
    int m = grid[0].size();

    int dirA[4][2] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    queue<int> que;

    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            if (grid[i][j] == 2)
                que.push((i * m + j));

    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            int r = rvtx / m;
            int c = rvtx % m;

            for (int d = 0; d < 4; d++)
            {
                int x = r + dirA[d][0];
                int y = c + dirA[d][1];

                if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == 1)
                {
                    que.push((x * m + y));
                    grid[x][y] = 2;
                }
            }
        }
        level++;
    }

    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            if (grid[i][j] == 1)
                return -1;

    return level;
}
