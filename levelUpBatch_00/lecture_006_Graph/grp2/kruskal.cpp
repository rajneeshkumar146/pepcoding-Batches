#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

vector<int> par, size;

int findPar(int u)
{
    return par[u] = (par[u] == u ? u : findPar(par[u]));
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
// n: no. of vertex
// m: no. of Edge
void kruskal_sol(int n, int m, vector<vector<int>> &edges)
{
    sort(edges.begin(), edges.end(), (auto &a, auto &b) {
        return a[2] < b[2];
    });

    for (int i = 0; i < n; i++)
    {
        par.push_back(i);
        size.push_back(1);
    }

    vector<vector<int>> ans;

    for (auto &e : edges)
    {
        int p1 = findPar(e[0]);
        int p2 = findPar(e[1]);

        if (p1 != p2)
        {
            merge(p1, p2);

            ans.push_back({e[0], e[1], e[2]});
            // ans.push_back(e); //??
        }
    }
}