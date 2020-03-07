#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

vector<int> par;
vector<int> size;

int findParent(int u)
{
    if (par[u] == u)
        return u;

    int p = findParent(par[u]);
    par[u] = p; //compression.
    return p;
}

void merge(int p1, int p2)
{
    if (size[p1] < size[p2])
    {
        par[p1] = p2;
        size[p2] += size[p1];
    }
    else
    {
        par[p2] = p1;
        size[p1] += size[p2];
    }
}

vector<int> findRedundantConnection(vector<vector<int>> &edges)
{
    // vector<int> par, size;
    for (int i = 1; i <= edges.size(); i++)
    {
        par.push_back(i);
        size.push_back(1);
    }
   
    for (vector<int> ar : edges)
    {
        int p1 = findParent(ar[0]);
        int p2 = findParent(ar[1]);

        if (p1 != p2)
        {
            merge(p1, p2);
        }
        else
        {
            return ar;
        }
    }
    
    vector<int> ans;
    return ans;
}

string smallestEquivalentString(string A, string B, string S)
{
    for (int i = 0; i < 26; i++)
    {
        par.push_back(i);
    }

    for (int i = 0; i < A.length(); i++)
    {
        int p1 = findParent(A[i] - 'a');
        int p2 = findParent(B[i] - 'a');
        int min_ = min(p1, p2);
        par[p1] = min_;
        par[p2] = min_;
    }

    string ans = "";
    for (int i = 0; i < S.length(); i++)
    {
        int p = findParent(S[i] - 'a');
        ans += (char)(p + 'a');
    }

    return ans;
}

int dir[4][2] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
int dfs(int x, int y, vector<vector<char>> &grid)
{
    grid[x][y] = '0';
    int count = 0;
    for (int d = 0; d < 4; d++)
    {
        int i = x + dir[d][0];
        int j = y + dir[d][1];
        if (i >= 0 && j >= 0 && i < grid.size() && j < grid[0].size() && grid[i][j] == '1')
        {
            count += dfs(i, j, grid);
        }
    }
    return count + 1;
}

int numIslands_01(vector<vector<char>> &grid)
{
    int n = grid.size();
    int m = grid[0].size();
    int countIslands = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == '1')
            {
                countIslands++;
                dfs(i, j, grid);
            }
        }
    }
    return countIslands;
}

int maxAreaOfIsland(vector<vector<int>> &grid)
{
    int n = grid.size();
    int m = grid[0].size();
    int maxArea = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == '1')
            {
                int area = dfs(i, j, grid);
                if (area > maxArea)
                    maxArea = area;
            }
        }
    }
    return maxArea;
}

int numIslands_01(vector<vector<char>> &grid)
{
    int n = grid.size();
    int m = grid[0].size();
    int countIslands = 0;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == '1')
                countIslands++;
            par.push_back(i * m + j);
            size.push_back(1);
        }
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == '1')
            {
                if (j + 1 < m && grid[i][j + 1] == '1')
                {
                    int p1 = findParent(i * m + j);
                    int p2 = findParent(i * m + j + 1);
                    if (p1 != p2)
                    {
                        countIslands--;
                        merge(p1, p2);
                    }
                }

                if (i + 1 < n && grid[i + 1][j] == '1')
                {
                    int p1 = findParent(i * m + j);
                    int p2 = findParent((i + 1) * m + j);
                    if (p1 != p2)
                    {
                        countIslands--;
                        merge(p1, p2);
                    }
                }
            }
        }
    }
}

int islandPerimeter(vector<vector<int>> &grid)
{
    int n = grid.size();
    int m = grid[0].size();
    int islands = 0;
    int nbrs = 0;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 1)
            {
                islands++;
                if (j + 1 < m && grid[i][j + 1] == 1)
                    nbrs++;
                if (i + 1 < n && grid[i + 1][j] == 1)
                    nbrs++;
            }
        }
    }

    return islands * 4 - 2 * nbrs;
}

int minCostToSupplyWater_leet1168(int n, vector<int> &wells, vector<vector<int>> &pipes)
{
    for (int i = 0; i < wells.size(); i++)
    {
        vector<int> ar{0, i + 1, wells[i]};
        pipes.push_back(ar);
    }

    sort(pipes.begin(), pipes.end(), [](vector<int> a, vector<int> b) {
        return a[2] < b[2];
    });

    for (int i = 0; i <= wells.size(); i++)
    {
        par.push_back(i);
        size.push_back(1);
    }

    int ans = 0;
    for (int i = 0; i < pipes.size(); i++)
    {
        vector<int> rp = pipes[i];

        int p1 = findParent(rp[0]);
        int p2 = findParent(rp[1]);

        if (p1 != p2)
        {
            merge(p1, p2);
            ans += rp[2];
        }
    }

    return ans;
}

int fastSpeed = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
}();

int mr_president()
{
    int n, edges, cost;
    cin >> n >> edges >> cost;

    vector<vector<int>> arr, myGraph;
    for (int i = 0; i < edges; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;
        vector<int> ar{a, b, c};

        arr.push_back(ar);
    }

    sort(arr.begin(), arr.end(), (vector<int> a, vector<int> b) {
        return a[2] < b[2];
    });

    for (int i = 1; i <= edges; i++)
    {
        par.push_back(i);
        size.push_back(1);
    }

    for (vector<int> ar : arr)
    {
        int p1 = findParent(ar[0]);
        int p2 = findParent(ar[1]);
        if (p1 != p2)
        {
            merge(p1, p2);
            myGraph.push_back(ar);
        }
    }

    int count = 0;
    for (int i = 1; i <= edges; i++)
    {
        if (par[i] == i)
            count++;
    }
    if (count > 1)
        return -1;

    sort(myGraph.begin(), myGraph.end(), (vector<int> a, vector<int> b) {
        return a[2] > b[2];
    });

    int amount = 0;
    for (vector<int> ar : myGraph)
    {
        amount += ar[2];
    }

    int roads = 0;

    for (vector<int> ar : myGraph)
    {
        if (amount < cost)
            break;
        amount -= ar[2] + 1;
        roads++;
    }

    return amount < cost ? roads : -1;
}

void topologicalHashMap()
{

    unordered_map<string, vector<string>> maping;
    for (int i = 0; i < n; i++)
    {
        string str1, str2;
        cin >> str1 >> str2;

        string[str1].push_back(str2);
    }

    
}

int main()
{
    return 0;
}
