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
    vector<int> par, size;
    for (int i = 1; i <= edges.size(); i++)
    {
        par.push_back(i);
        size.push_back(1);
    }
    vector<int> ans;
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
    return ans;
}

// void kruskalAlgoForMST(vector<vector<int>> &graph)
// {
// }

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

int main()
{
    return 0;
}
