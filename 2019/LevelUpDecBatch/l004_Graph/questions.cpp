#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>

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

vector<int> kahnsAlgo(int N, vector<vector<int>> &graph)
{
    vector<int> indegree(N, 0);
    for (int i = 0; i < N; i++)
        for (int e : graph[i])
            indegree[e]++;

    queue<int> que;
    vector<int> ans;
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

            ans.push_back(rvtx);
            for (int e : graph[rvtx])
            {
                if (--indegree[e] == 0)
                    que.push(e);
            }
        }

        level++;
    }

    return ans;
}

bool canFinish(int N, vector<vector<int>> &arr)
{

    vector<vector<int>> graph(N);
    for (vector<int> ar : arr)
    {
        graph[ar[0]].push_back(ar[1]);
    }

    return kahnsAlgo(N, graph).size() == N;
}

//210
vector<int> findOrder(int N, vector<vector<int>> &arr)
{
    vector<vector<int>> graph(N);
    for (vector<int> ar : arr)
    {
        graph[ar[0]].push_back(ar[1]);
    }

    vector<int> ans = kahnsAlgo(N, graph);
    if (ans.size() != N)
        return {};
    reverse(ans.begin(), ans.end());
    return ans;
}

// 207
bool isCyclePresent_DFSTopo(int src, vector<int> &vis, vector<vector<int>> &graph)
{
    vis[src] = 0;
    bool res = false;
    for (int v : graph[src])
    {
        if (vis[v] == -1) // unvisited
            res = res || isCyclePresent_DFSTopo(v, vis, graph);
        else if (vis[v] == 0)
            return true; // there is cycle.
    }

    vis[src] = 1;
    return res;
}

bool canFinish(int N, vector<vector<int>> &arr)
{
    vector<vector<int>> graph(N);
    for (vector<int> ar : arr)
    {
        graph[ar[0]].push_back(ar[1]);
    }
    vector<int> vis(N, -1);

    bool res = false;
    for (int i = 0; i < N; i++)
    {
        if (vis[i] == -1)
        {
            if (isCyclePresent_DFSTopo(i, vis, graph))
                return false;
        }
    }

    return true;
}

// 210
bool isCyclePresent_DFSTopo(int src, vector<int> &vis, vector<int> &ans, vector<vector<int>> &graph)
{
    vis[src] = 0;
    bool res = false;
    for (int v : graph[src])
    {
        if (vis[v] == -1) // unvisited
            res = res || isCyclePresent_DFSTopo(v, vis, ans, graph);
        else if (vis[v] == 0)
            return true; // there is cycle.
    }

    vis[src] = 1;
    ans.push_back(src);
    return res;
}

vector<int> findOrder(int N, vector<vector<int>> &arr)
{
    vector<vector<int>> graph(N);
    for (vector<int> ar : arr)
    {
        graph[ar[0]].push_back(ar[1]);
    }
    vector<int> vis(N, -1);
    vector<int> ans;

    bool res = false;
    for (int i = 0; i < N; i++)
        if (vis[i] == -1)
            res = res || isCyclePresent_DFSTopo(i, vis, ans, graph);

    if (res)
        ans.clear();
    return ans;
}

int longestIncreasingPath(vector<vector<int>> &matrix)
{
    int n = matrix.size();
    int m = matrix[0].size();
    vector<vector<int>> indegree(n, vector<int>(m, 0));

    vector<vector<int>> dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            for (int d = 0; d < 4; d++)
            {
                int x = i + dir[d][0];
                int y = j + dir[d][1];

                if (x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[i][j])
                    indegree[x][y]++;
            }

    queue<int> que;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            if (indegree[i][j] == 0)
                que.push(i * m + j);

    int level = 0;
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

                if (x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[r][c] && --indegree[x][y] == 0)
                    que.push(x * m + y);
            }
        }

        level++;
    }

    return level;
}

vector<int> par;
int findPar(int u)
{
    return par[u] == -1 ? u : par[u] = findPar(par[u]);
}
vector<int> findRedundantConnection(vector<vector<int>> &edges)
{
    int N = edges.size();
    par.resize(N + 1, -1); // Arrays.fill(par,-1);

    for (vector<int> &edge : edges)
    {
        int p1 = findPar(edge[0]);
        int p2 = findPar(edge[1]);

        if (p1 != p2)
        {
            par[p1] = p2;
        }
        else
        {
            return edge;
        }
    }

    return {};
}

string smallestEquivalentString(string A, string B, string S)
{
    for (int i = 0; i < 26; i++)
        par.push_back(i);
    // par.resize(26,-1);

    for (int i = 0; i < A.length(); i++)
    {
        int p1 = findPar(A[i] - 'a');
        int p2 = findPar(B[i] - 'a');

        par[p1] = min(p1, p2);
        par[p2] = min(p1, p2);

        //     if(p1 < p2)
        //         par[p2] = p1;
        //     else if(p2 < p1)par[p1] = p2;
        //
    }

    string ans = "";
    for (int i = 0; i < S.length(); i++)
    {
        ans += (char)(findPar(S[i] - 'a') + 'a');
    }

    return ans;
}

//839
bool isSimilar(string &s1, string &s2)
{
    int count = 0;
    for (int i = 0; i < s1.length(); i++)
    {
        if (s1[i] != s2[i] && ++count > 2)
            return false;
    }

    return true;
}

int numSimilarGroups(vector<string> &strs)
{

    int count = strs.size();
    int n = strs.size();

    par.resize(n);
    for (int i = 0; i < n; i++)
        par[i] = i;

    for (int i = 0; i < n; i++)
    {
        for (int j = i + 1; j < n; j++)
        {
            if (isSimilar(strs[i], strs[j]))
            {
                int p1 = findPar(i);
                int p2 = findPar(j);

                if (p1 != p2)
                {
                    par[p1] = p2;
                    count--;
                }
            }
        }
    }

    return count;
}

//305
vector<int> numIslands2(int m, int n, vector<vector<int>> &positions)
{
    par.resize(m * n, -1);

    vector<vector<int>> dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    vector<vector<int>> grid(m, vector<int>(n, 0));
    int count = 0;
    vector<int> ans;
    for (vector<int> &pos : positions)
    {
        int i = pos[0];
        int j = pos[1];

        if (grid[i][j] != 1)
        {

            grid[i][j] = 1;
            count++;

            for (int d = 0; d < 4; d++)
            {
                int x = i + dir[d][0];
                int y = j + dir[d][1];

                if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 1)
                {
                    int p1 = findPar(i * n + j);
                    int p2 = findPar(x * n + y);

                    if (p1 != p2)
                    {
                        count--;
                        par[p1] = p2;
                    }
                }
            }
        }

        ans.push_back(count);
    }

    return ans;
}

//305
vector<int> numIslands2(int m, int n, vector<vector<int>> &positions)
{
    par.resize(m * n, -1);

    vector<vector<int>> dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    int count = 0;
    vector<int> ans;
    for (vector<int> &pos : positions)
    {
        int i = pos[0];
        int j = pos[1];

        if (par[i * n + j] == -1)
        {

            par[i * n + j] = (i * n + j);
            count++;

            for (int d = 0; d < 4; d++)
            {
                int x = i + dir[d][0];
                int y = j + dir[d][1];

                if (x >= 0 && y >= 0 && x < m && y < n && par[x * n + y] != -1)
                {
                    int p1 = findPar(i * n + j);
                    int p2 = findPar(x * n + y);

                    if (p1 != p2)
                    {
                        count--;
                        par[p1] = p2;
                    }
                }
            }
        }

        ans.push_back(count);
    }

    return ans;
}

vector<int> par;
int minCostToSupplyWater_(int N, vector<vector<int>> &Edges)
{

    for (int i = 0; i <= N; i++)
    {
        par.push_back(i);
    }

    int cost = 0;

    for (vector<int> &edge : Edges)
    {
        int u = edge[0], v = edge[1], w = edge[2];
        int p1 = findPar(u);
        int p2 = findPar(v);

        if (p1 != p2)
        {
            par[p1] = p2;
            cost += w;
        }
    }

    return cost;
}

int minCostToSupplyWater(int n, vector<int> &wells, vector<vector<int>> &pipes)
{
    for (int i = 0; i < wells.size(); i++)
    {
        pipes.push_back({0, i + 1, wells[i]});
    }

    sort(pipes.begin(), pipes.end(), [](vector<int> &a, vector<int> &b) {
        return a[2] < b[2];
    });

    return minCostToSupplyWater_(n, pipes);
}

int numIslands(vector<vector<char>> &grid)
{
    int n = grid.size();
    int m = grid[0].size();

    for (int i = 0; i < n * m; i++)
        par.push_back(i);

    int oncesCount = 0;
    vector<vector<int>> dir{{1, 0}, {0, 1}};
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == '1')
            {
                oncesCount++;
                int p1 = findPar(i * m + j);
                for (int d = 0; d < 2; d++)
                {
                    int x = i + dir[d][0];
                    int y = j + dir[d][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == '1')
                    {
                        int p2 = findPar(x * m + y);
                        if (p1 != p2)
                        {
                            par[p2] = p1;
                            oncesCount--;
                        }
                    }
                }
            }
        }
    }

    return oncesCount;
}

int maxAreaOfIsland(vector<vector<int>> &grid)
{

    int n = grid.size();
    int m = grid[0].size();

    for (int i = 0; i < n * m; i++)
        par.push_back(i);

    vector<int> componentSize(n * m, 1);
    int maxArea = 0;

    vector<vector<int>> dir{{1, 0}, {0, 1}};

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 1)
            {
                int p1 = findPar(i * m + j);
                for (int d = 0; d < 2; d++)
                {
                    int x = i + dir[d][0];
                    int y = j + dir[d][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1)
                    {
                        int p2 = findPar(x * m + y);
                        if (p1 != p2)
                        {
                            par[p2] = p1;
                            componentSize[p1] += componentSize[p2];
                        }
                    }
                    maxArea = max(maxArea, componentSize[p1]);
                }
            }
            else
                componentSize[i * m + j] = 0;
        }
    }

    return maxArea;
}

long journeyToMoon(int n, vector<vector<int>> astronaut)
{
    for (int i = 0; i < n; i++)
    {
        par.push_back(i);
        size.push_back(1);
    }

    for (vector<int> &ast : astronaut)
    {
        int p1 = findPar(ast[0]);
        int p2 = findPar(ast[1]);

        if (p1 != p2)
        {
            par[p1] = p2;
            size[p2] += size[p1];
        }
    }

    long sum = 0, totalPairs = 0;
    for (int i = 0; i < n; i++)
    {
        if (par[i] == i)
        {
            totalPairs += sum * size[i];
            sum += size[i];
        }
    }

    return totalPairs;
}

// https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/
int mrPresident(int n, vector<vector<int>> &edges, long k)
{
    for (int i = 0; i <= n; i++)
        par.push_back(i);

    vector<int> mstEdgeWeight;
    long overallWeightSum = 0;
    for (vector<int> &e : edges)
    {
        int p1 = findPar(e[0]);
        int p2 = findPar(e[1]);

        if (p1 != p2)
        {
            par[p1] = p2;
            mstEdgeWeight.push_back(e[2]);
            overallWeightSum += e[2];
            n--;
        }
    }

    if (n > 1)
        return -1;
    if (overallWeightSum <= k)
        return 0;

    int transform = 0;
    for (int i = mstEdgeWeight.size() - 1; i >= 0; i--)
    {
        overallWeightSum = overallWeightSum - mstEdgeWeight[i] + 1;
        transform++;

        if (overallWeightSum <= k)
            break;
    }

    return overallWeightSum <= k ? transform : -1;
}

void mrPresident()
{
    ios_base::sync_with_stdio(false);
    long n, m, k;
    cin >> n >> m >> k;

    vector<vector<int>> edges;
    for (int i = 0; i < m; i++)
    {
        int u, v, w;
        cin >> u >> v >> w;
        edges.push_back({u, v, w});
    }

    sort(edges.begin(), edges.end(), [](vector<int> &a, vector<int> &b) {
        return a[2] < b[2];
    });

    cout << mrPresident(n, edges, k) << endl;
}

//815
int numBusesToDestination(vector<vector<int>> &routes, int src, int dest)
{

    if (src == dest)
        return 0;
    int n = routes.size();
    unordered_map<int, vector<int>> busStandMapping;
    int busNumber = 0;
    for (vector<int> &busStandList : routes)
    {
        for (int busStand : busStandList)
        {
            busStandMapping[busStand].push_back(busNumber);
        }
        busNumber++;
    }

    unordered_set<int> isBusStandSeen;
    vector<bool> isBusSeen(n, false);

    queue<int> que;
    que.push(src);
    isBusStandSeen.insert(src);

    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int busStand = que.front();
            que.pop();

            vector<int> allBuses = busStandMapping[busStand];
            for (int busNo : allBuses)
            {
                if (isBusSeen[busNo])
                    continue;

                for (int bs : routes[busNo]) // bs is bus stand
                {
                    if (isBusStandSeen.find(bs) == isBusStandSeen.end())
                    {
                        que.push(bs);
                        isBusStandSeen.insert(bs);

                        if (bs == dest)
                            return level + 1;
                    }
                }

                isBusSeen[busNo] = true;
            }
        }
        level++;
    }

    return -1;
}

//743
int networkDelayTime(vector<vector<int>> &times, int n, int k)
{
    vector<vector<pair<int, int>>> graph(n + 1);

    // {u -> {v,w}}
    for (vector<int> &ar : times)
    {
        graph[ar[0]].push_back({ar[1], ar[2]});
    }

    vector<int> dis(n + 1, 1e9);
    vector<bool> vis(n + 1, false);

    //{wsf,vtx}
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> que;
    que.push({0, k});
    dis[k] = 0;

    int NoOfEdges = 0;
    int maxValue = 0;
    while (que.size() != 0)
    {
        pair<int, int> p = que.top();
        que.pop();
        int vtx = p.second, wsf = p.first;

        if (vis[vtx])
            continue;

        if (vtx != k)
            NoOfEdges++;

        maxValue = max(maxValue, wsf);
        vis[vtx] = true;
        for (pair<int, int> &e : graph[vtx])
        {
            if (!vis[e.first] && wsf + e.second < dis[e.first])
            {
                dis[e.first] = wsf + e.second;
                que.push({wsf + e.second, e.first});
            }
        }
    }

    if (NoOfEdges != n - 1)
        return -1;

    return maxValue;
}

//1192
vector<int> dis, low;
vector<bool> vis;
int time = 0;
vector<vector<int>> res;

void dfs(int src, int par, int n, vector<vector<int>> &graph)
{
    dis[src] = low[src] = time++;
    vis[src] = true;

    for (int nbr : graph[src])
    {
        if (!vis[nbr])
        {
            dfs(nbr, src, n, graph);

            if (dis[src] < low[nbr])
                res.push_back({src, nbr});

            low[src] = min(low[src], low[nbr]);
        }
        else if (nbr != par)
            low[src] = min(dis[nbr], low[src]);
    }
}

vector<vector<int>> criticalConnections(int n, vector<vector<int>> &connections)
{
    vector<vector<int>> graph(n);
    for (vector<int> &ar : connections)
    {
        graph[ar[0]].push_back(ar[1]);
        graph[ar[1]].push_back(ar[0]);
    }

    dis.resize(n, 0);
    low.resize(n, 0);
    vis.resize(n, false);

    dfs(0, -1, n, graph);
    return res;
}
class Edge
{
public:
    int v, w;
    Edge(int v, int w)
    {
        this->v = v;
        this->w = w;
    }
};
int findCheapestPrice(int n, vector<vector<int>> &flights, int src, int dst, int K)
{
    vector<vector<Edge>> graph(n);

    for (vector<int> &ar : flights)
    {
        graph[ar[0]].push_back(Edge(ar[1], ar[2]));
        // graph[ar[1]].push_back(Edge(ar[0],ar[2]));
    }

    // {wsf,stop,src}
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

    vector<bool> vis(n, false);
    pq.push({0, K + 1, src});
    while (pq.size() != 0)
    {
        vector<int> rvtx = pq.top();
        pq.pop();
        int vtx = rvtx[2], wsf = rvtx[0], edgeCount = rvtx[1];

        if (vtx == dst)
            return wsf;
        if (edgeCount <= 0)
            continue;

        for (Edge e : graph[vtx])
        {
            pq.push({e.w + wsf, edgeCount - 1, e.v});
        }
    }

    return -1;
}