#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

vector<int> par;
vector<vector<int>> dir{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
int findPar(int u)
{
    return par[u] == u ? u : (par[u] = findPar(par[u]));
}

//684
vector<int> findRedundantConnection(vector<vector<int>> &edges)
{
    int n = edges.size() + 1;
    for (int i = 0; i < n; i++)
        par.push_back(i);

    for (vector<int> &e : edges)
    {
        int u = e[0], v = e[1];
        int p1 = findPar(u), p2 = findPar(v);
        if (p1 != p2)
        {
            par[p1] = p2;
        }
        else
            return e;
    }

    return {};
}

//1061

string smallestEquivalentString(string s1, string s2, string baseStr)
{
    for (int i = 0; i < 26; i++)
        par.push_back(i);
    for (int i = 0; i < s1.length(); i++)
    {
        char c1 = s1[i], c2 = s2[i];
        int p1 = findPar(c1 - 'a'), p2 = findPar(c2 - 'a');
        par[p1] = min(p1, p2);
        par[p2] = min(p1, p2);
    }

    string ans = "";
    for (char ch : baseStr)
    {
        int p = findPar(ch - 'a');
        ans += (char)(p + 'a');
    }

    return ans;
}

// 305
vector<int> numIslands2(int n, int m, vector<vector<int>> &positions)
{
    vector<vector<int>> grid(n, vector<int>(m, 0));
    for (int i = 0; i < n * m; i++)
        par.push_back(i);

    vector<int> ans;
    int count = 0;
    for (vector<int> &pos : positions)
    {
        int i = pos[0], j = pos[1];
        if (grid[i][j] == 1)
        {
            ans.push_back(count);
            continue;
        }

        grid[i][j] = 1;
        count++;
        int p1 = findPar(i * m + j);
        for (vector<int> &d : dir)
        {
            int r = i + d[0];
            int c = j + d[1];

            if (r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 1)
            {
                int p2 = findPar(r * m + c);
                if (p1 != p2)
                {
                    count--;
                    par[p2] = p1;
                }
            }
        }

        ans.push_back(count);
    }
    return ans;
}

// without using grid
vector<int> numIslands2(int n, int m, vector<vector<int>> &positions)
{
    par.resize(n * m, -1);
    vector<int> ans;
    int count = 0;
    for (vector<int> &pos : positions)
    {
        int i = pos[0], j = pos[1];
        if (par[i * m + j] != -1)
        {
            ans.push_back(count);
            continue;
        }

        par[i * m + j] = (i * m + j);
        count++;

        for (vector<int> &d : dir)
        {
            int r = i + d[0];
            int c = j + d[1];

            if (r >= 0 && r < n && c >= 0 && c < m && par[r * m + c] != -1)
            {
                int p1 = findPar(i * m + j);
                int p2 = findPar(r * m + c);
                if (p1 != p2)
                {
                    count--;
                    par[p1] = p2;
                }
            }
        }

        ans.push_back(count);
    }
    return ans;
}

//1168

int minCostToSupplyWater(int n, vector<int> &wells, vector<vector<int>> &pipes)
{
    for (int i = 0; i < wells.size(); i++)
    {
        pipes.push_back({0, i + 1, wells[i]});
    }

    sort(pipes.begin(), pipes.end(), [](vector<int> &a, vector<int> &b)
         { return a[2] < b[2]; });

    for (int i = 0; i <= n; i++)
        par.push_back(i);

    int cost = 0;
    for (vector<int> &e : pipes)
    {
        int u = e[0], v = e[1], w = e[2];
        int p1 = findPar(u), p2 = findPar(v);
        if (p1 != p2)
        {
            par[p1] = p2;
            cost += w;
        }
    }

    return cost;
}
