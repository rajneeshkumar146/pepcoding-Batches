#include <iostream>
#include <vector>
#include <queue>

using namespace std;

//200

void dfsIsland(int i, int j, int n, int m, vector<vector<char>> &grid, vector<vector<int>> &dir)
{
    grid[i][j] = '0';
    for (int d = 0; d < dir.size(); d++)
    {
        int r = i + dir[d][0];
        int c = j + dir[d][1];
        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1')
        {
            dfsIsland(r, c, n, m, grid, dir);
        }
    }
}

int numIslands(vector<vector<char>> &grid)
{
    if (grid.size() == 0 || grid[0].size() == 0)
        return 0;

    int count = 0;
    int n = grid.size();
    int m = grid[0].size();
    vector<vector<int>> dir{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == '1')
            {
                count++;
                dfsIsland(i, j, n, m, grid, dir);
            }
        }
    }

    return count;
}

//695

int dfsArea(int i, int j, int n, int m, vector<vector<int>> &grid, vector<vector<int>> &dir)
{
    grid[i][j] = 0;
    int area = 1;
    for (int d = 0; d < dir.size(); d++)
    {
        int r = i + dir[d][0];
        int c = j + dir[d][1];
        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
        {
            area += dfsArea(r, c, n, m, grid, dir);
        }
    }

    return area;
}

int maxAreaOfIsland(vector<vector<int>> &grid)
{
    if (grid.size() == 0 || grid[0].size() == 0)
        return 0;

    int area = 0;
    int n = grid.size();
    int m = grid[0].size();
    vector<vector<int>> dir{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 1)
            {
                area = max(area, dfsArea(i, j, n, m, grid, dir));
            }
        }
    }

    return area;
}

//463
int islandPerimeter(vector<vector<int>> &grid)
{
    if (grid.size() == 0 || grid[0].size() == 0)
        return 0;

    int nbrs = 0, count = 0;
    int n = grid.size();
    int m = grid[0].size();
    vector<vector<int>> dir{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 1)
            {
                count++;
                for (int d = 0; d < dir.size(); d++)
                {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                        nbrs++;
                }
            }
        }
    }

    return count * 4 - nbrs;
}

void surroundedRegionDFS(int i, int j, int n, int m, vector<vector<char>> &grid, vector<vector<int>> &dir)
{
    grid[i][j] = '$';
    for (int d = 0; d < dir.size(); d++)
    {
        int r = i + dir[d][0];
        int c = j + dir[d][1];

        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 'O')
            surroundedRegionDFS(r, c, n, m, grid, dir);
    }
}

void solve(vector<vector<char>> &grid)
{
    if (grid.size() == 0 || grid[0].size() == 0)
        return;

    int area = 0;
    int n = grid.size();
    int m = grid[0].size();
    vector<vector<int>> dir{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    for (int i = 0; i < n; i++)
    {
        if (grid[i][0] == 'O')
            surroundedRegionDFS(i, 0, n, m, grid, dir);
        if (grid[i][m - 1] == 'O')
            surroundedRegionDFS(i, m - 1, n, m, grid, dir);
    }

    for (int j = 0; j < m; j++)
    {
        if (grid[0][j] == 'O')
            surroundedRegionDFS(0, j, n, m, grid, dir);
        if (grid[n - 1][j] == 'O')
            surroundedRegionDFS(n - 1, j, n, m, grid, dir);
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 'O')
                grid[i][j] = 'X';
            if (grid[i][j] == '$')
                grid[i][j] = 'O';
        }
    }
}

//1091
int shortestPathBinaryMatrix(vector<vector<int>> &grid)
{
    if (grid.size() == 0 || grid[0].size() == 0)
        return -1;

    int n = grid.size();
    int m = n;

    if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
        return -1;

    queue<int> que;
    vector<vector<int>> dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};

    grid[0][0] = 1;
    que.push(0 * m + 0); // r * m + c

    int level = 1;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int idx = que.front();
            que.pop();
            int r = idx / m;
            int c = idx % m;

            if (r == n - 1 && c == m - 1)
            {
                return level;
            }

            for (int d = 0; d < dir.size(); d++)
            {
                int x = r + dir[d][0];
                int y = c + dir[d][1];

                if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 0)
                {
                    grid[x][y] = 1;
                    que.push(x * m + y);
                }
            }
        }
        level++;
    }

    return -1;
}

//785
bool isBipartite(vector<vector<int>> &graph, vector<int> &vis, int src)
{
    queue<int> que;
    que.push(src);

    int color = 0; // red
    bool isCycle = false;

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int rvtx = que.front();
            que.pop();
            if (vis[rvtx] != -1)
            {
                isCycle = true;
                if (vis[rvtx] != color)
                    return false;

                continue;
            }

            vis[rvtx] = color;
            for (int v : graph[rvtx])
            {
                if (vis[v] == -1)
                {
                    que.push(v);
                }
            }
        }

        color = (color + 1) % 2;
    }

    return true;
}

bool isBipartite(vector<vector<int>> &graph)
{
    int n = graph.size();
    // -1 : not visited, 0 : red, 1 : green
    vector<int> vis(n, -1);

    //         for(int i=0;i<n;i++){
    //             if(vis[i] == -1 && !isBipartite(graph,i)){
    //                 return false;
    //             }
    //         }

    //         return true;

    bool res = true;
    for (int i = 0; i < n; i++)
    {
        if (vis[i] == -1)
            res = res && isBipartite(graph, vis, i);
    }

    return res;
}

//994
int orangesRotting(vector<vector<int>> &grid)
{

    int n = grid.size();
    int m = grid[0].size();

    queue<int> que;
    vector<vector<int>> dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    int time = 0, freshOranges = 0;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 2)
                que.push(i * m + j);
            else if (grid[i][j] == 1)
                freshOranges++;
        }
    }

    if (freshOranges == 0)
        return 0;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int idx = que.front();
            que.pop();

            int r = idx / m;
            int c = idx % m;

            for (int d = 0; d < 4; d++)
            {
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1)
                {
                    freshOranges--;
                    grid[x][y] = 2;
                    que.push(x * m + y);
                    if (freshOranges == 0)
                        return time + 1;
                }
            }
        }

        time++;
    }

    return -1;
}

//286
void wallsAndGates(vector<vector<int>> &rooms)
{

    int n = rooms.size();
    int m = rooms[0].size();

    vector<vector<int>> dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    queue<int> que;
    int countOfRooms = 0, distance = 0;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            if (rooms[i][j] == 0) // gates
                que.push(i * m + j);
            else if (rooms[i][j] == 2147483647)
                countOfRooms++;

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int idx = que.front();
            que.pop();
            int r = idx / m;
            int c = idx % m;

            for (int d = 0; d < 4; d++)
            {
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                if (x >= 0 && y >= 0 && x < n && y < m && rooms[x][y] == 2147483647)
                {
                    countOfRooms--;
                    rooms[x][y] = distance + 1;
                    que.push(x * m + y);

                    if (countOfRooms == 0)
                        return;
                }
            }
        }
        distance++;
    }
}

//207
bool kahnsAlgo(int N, vector<vector<int>> &graph)
{
    vector<int> indegree(N, 0);
    for (int i = 0; i < N; i++)
        for (Edge e : graph[i])
            indegree[e.v]++;

    queue<int> que;
    int count = 0;

    for (int i = 0; i < N; i++)
        if (indegree[i] == 0)
            que.push(i);

    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            count++;
            for (Edge e : graph[rvtx])
            {
                if (--indegree[e.v] == 0)
                    que.push(e.v);
            }
        }

        level++;
    }

    return count == N;
}

bool canFinish(int numCourses, vector<vector<int>> &prerequisites)
{
}