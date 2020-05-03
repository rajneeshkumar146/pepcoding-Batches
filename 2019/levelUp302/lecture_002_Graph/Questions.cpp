#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>
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

//leetcode 207============================================

vector<int> kahnsAlgo(int n, vector<vector<int>> &graph, vector<vector<int>> &prerequisites)
{

    vector<int> indegree(n, 0);
    for (vector<int> ar : prerequisites)
        indegree[ar[1]]++;

    queue<int> que;
    for (int i = 0; i < n; i++)
    {
        if (indegree[i] == 0)
            que.push(i);
    }

    vector<int> ans;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            ans.push_back(rvtx);
            for (int e : graph[rvtx])
            {
                if (--indegree[e] == 0)
                {
                    que.push(e);
                }
            }
        }
    }

    return ans;
}

bool canFinish(int numCourses, vector<vector<int>> &prerequisites)
{
    vector<vector<int>> graph(numCourses, vector<int>());
    for (vector<int> ar : prerequisites)
        graph[ar[0]].push_back(ar[1]);

    vector<int> ans = kahnsAlgo(numCourses, graph, prerequisites);

    return ans.size() == numCourses;
}

//Leetcode 210.=========================================================

vector<int> findOrder(int numCourses, vector<vector<int>> &prerequisites)
{
    vector<vector<int>> graph(numCourses, vector<int>());
    for (vector<int> ar : prerequisites)
        graph[ar[0]].push_back(ar[1]);

    vector<int> ans = kahnsAlgo(numCourses, graph, prerequisites);

    return ans.size() != numCourses ? {} : ans;
}

//leetcode 329==========================================================

int longestIncreasingPath(vector<vector<int>> &matrix)
{
    if (matrix.size() == 0 || matrix[0].size() == 0)
        return 0;
    vector<vector<int>> dirA = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    int n = matrix.size();
    int m = matrix[0].size();

    vector<vector<int>> indegree(n, vector<int>(m, 0));

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            for (int d = 0; d < 4; d++)
            {
                int x = i + dirA[d][0];
                int y = j + dirA[d][1];
                if (x >= 0 && x < n && y >= 0 && y < m && matrix[x][y] > matrix[i][j])
                    indegree[x][y]++;
            }
        }
    }

    queue<int> que;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (indegree[i][j] == 0)
                que.push(i * m + j);
        }
    }

    int length = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            int i = rvtx / m;
            int j = rvtx % m;

            for (int d = 0; d < 4; d++)
            {
                int x = i + dirA[d][0];
                int y = j + dirA[d][1];
                if (x >= 0 && x < n && y >= 0 && y < m && matrix[x][y] > matrix[i][j] && --indegree[x][y] == 0)
                    que.push(x * m + y);
            }
        }
        length++;
    }

    return length;
}

//leetcode 815.=======================================================

int numBusesToDestination(vector<vector<int>> &routes, int S, int T)
{
    if (routes.size() == 0)
        return -1;
    unordered_map<int, vector<int>> map;
    for (int i = 0; i < routes.size(); i++)
    {
        for (int ele : routes[i])
        {
            map[ele].push_back(i);
        }
    }

    unordered_set<int> busStandVis;
    vector<bool> busVis(routes.size(), false);

    queue<int> que;
    que.push(S);
    int level = 0;
    busStandVis.insert(S);

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int stand = que.front();
            que.pop();

            if (stand == T)
                return level;

            for (int bus : map[stand])
            {
                if (busVis[bus])
                    continue;

                for (int busStand : routes[bus])
                {
                    if (busStandVis.count(busStand) == 0)
                    {
                        que.push(busStand);
                        busStandVis.insert(busStand);
                    }
                }
                busVis[bus] = true;
            }
        }
        level++;
    }

    return -1;
}

vector<int> par;
vector<int> setSize;

int findPar(int vtx)
{
    if (par[vtx] == vtx)
        return vtx;
    return par[vtx] = findPar(par[vtx]);
}

void mergeSet(int p1, int p2)
{
    if (setSize[p1] < setSize[p2])
    {
        par[p1] = p2;
        setSize[p2] += setSize[p1];
    }
    else
    {
        par[p2] = p1;
        setSize[p1] += setSize[p2];
    }
}

//leetcode 684.=====================================================

vector<int> findRedundantConnection(vector<vector<int>> &edges)
{
    for (int i = 0; i <= edges.size(); i++)
    {
        par.push_back(i);
        setSize.push_back(1);
    }

    for (vector<int> `ar : edges)
    {
        int u = ar[0];
        int v = ar[1];
        int p1 = findPar(u);
        int p2 = findPar(v);

        if (p1 != p2)
        {
            mergeSet(p1, p2);
        }
        else
        {
            return ar;
        }
    }

    return {};
}
