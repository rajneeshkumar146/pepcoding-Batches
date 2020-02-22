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

void kruskalAlgoForMST(vector<vector<int>> &graph)
{
    sort(graph.begin(), graph.end(), [](vector<int> a, vector<int> b) -> {
        return a[2] < b[2];
    });
}

void 