#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

vector<int> par;

int findPar(int u)
{
    // if (par[u] == u)
    //     return u;
    // int op = findPar(par[u]);
    // par[u] = op;
    // return op;

    return par[u] = (par[u] == u ? u : findPar(par[u]));
}

int minCostToSupplyWater(int n, vector<int> &wells, vector<vector<int>> &pipes)
{
    for (int i = 0; i < wells.size(); i++)
    {
        pipes.push_back({0, i + 1, wells[i]});
    }

    sort(pipes.begin(), pipes.end(), [](auto &a, auto &b) {
        return a[2] < b[2];
    });

    for (int i = 0; i <= n; i++)
    {
        par.push_back(i);
    }

    int ans = 0;
    for (auto &a : pipes)
    {
        int p1 = findPar(a[0]);
        int p2 = findPar(a[1]);

        if (p1 != p2)
        {
            par[p1] = p2;
            ans += a[2];
        }
    }
    return ans;
}
