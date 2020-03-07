#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

const int n = 9;

void dfs(vector<vector<int>> &graph, int src, vector<bool> &vis, vector<int> ans)
{
    vis[src] = true;
    for (int nbr : graph[src])
    {
        if (!vis[nbr])
            dfs(graph, nbr, vis, ans);
    }

    ans.push_back(src);
}

void kosaRajuAlgo()
{
    vector<vector<int>> graph(n, vector<int>());
    vector<bool> vis(n, false);
    vector<int> ans;
    for (int i = 0; i < n; i++)
    {
        if (!vis[i])
            dfs(graph, i, vis, ans);
    }

    vector<vector<int>> Newgraph(n, vector<int>());
    for (int u = 0; u < n; u++)
    {
        for (int v : graph[u])
        {
            Newgraph[v].push_back(u);
        }
    }

    vis.clear();
    vector<int> scc;
    while (ans.size() != 0)
    {
        int u = ans.back();
        ans.pop_back();
        if (!vis[u])
        {
            dfs(Newgraph, u, vis, scc);
            //print scc.
            //scc.clear();
        }
    }
}