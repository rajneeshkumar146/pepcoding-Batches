#include <iostream>
#include <vector>
#include <queue>

using namespace std;

class Edge
{
public:
    int v = 0;
    int w = 0;

    Edge(int v, int w)
    {
        this->v = v;
        this->w = w;
    }
};

const int N = 11;
vector<vector<Edge>> graph(N);

void addEdge(int u, int v, int w)
{
    graph[u].push_back(Edge(v, w));
}

void display()
{
    for (int i = 0; i < N; i++)
    {
        cout << to_string(i) + " -> ";
        for (Edge e : graph[i])
        {
            cout << "(" + to_string(e.v) + ", " + to_string(e.w) + ") ";
        }
        cout << endl;
    }
}

void dfs_topo(int src, vector<bool> &vis, vector<int> &ans)
{
    vis[src] = true;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            dfs_topo(e.v, vis, ans);
    }

    ans.push_back(src);
}

void topologicalOrder_DFS()
{
    vector<bool> vis(N, false);
    vector<int> ans;
    for (int i = 0; i < N; i++)
    {
        if (!vis[i])
            dfs_topo(i, vis, ans);
    }

    for (int ele : ans)
        cout << ele << " ";
}

void kahnsAlgo()
{
    vector<int> indegree(N, 0);
    for (int i = 0; i < N; i++)
        for (Edge e : graph[i])
            indegree[e.v]++;

    vector<int> ans;
    queue<int> que;

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

            for (Edge e : graph[rvtx])
            {
                if (--indegree[e.v] == 0)
                    que.push(e.v);
            }
        }

        level++;
    }

    for (int ele : ans)
        cout << ele << " ";
}

bool isCyclePresent_DFSTopo(int src, vector<int> &vis, vector<int> &ans)
{
    vis[src] = 0;
    bool res = false;
    for (Edge e : graph[src])
    {
        if (vis[e.v] == -1)
        { // unvisited
            res = res || isCyclePresent_DFSTopo(e.v, vis, ans);
        }
        else if (vis[e.v] == 0)
        {
            return true; // there is cycle.
        }
    }

    vis[src] = 1;
    ans.push_back(src);
    return res;
}

vector<int> isCyclePresent_DFS()
{
    vector<int> vis(N, -1);
    vector<int> ans;

    bool res = false;
    for (int i = 0; i < N; i++)
    {
        if (vis[i] == -1)
        {
            res = res || isCyclePresent_DFSTopo(i, vis, ans);
        }
    }

    if (res)
        ans.clear();
    return ans;
}

void dfs_SCC(vector<vector<Edge>> &ngraph, int src, vector<bool> &vis, vector<int> &ans)
{
    vis[src] = true;
    for (Edge e : ngraph[src])
    {
        if (!vis[e.v])
            dfs_SCC(ngraph, e.v, vis, ans);
    }

    ans.push_back(src);
}

//Kosaraju
void kosaraju()
{
    vector<bool> vis(N, false);
    vector<int> ans;
    for (int i = 0; i < N; i++)
    {
        if (!vis[i])
            dfs_topo(i, vis, ans);
    }

    // Graph inverse.
    vector<vector<Edge>> ngraph(N);
    for (int i = 0; i < N; i++)
    {
        for (Edge e : graph[i])
        {
            ngraph[e.v].push_back(Edge(i, 1));
        }
    }

    vis.clear();
    for (int i = ans.size() - 1; i >= 0; i--)
    {
        int ele = ans[i];
        if (!vis[ele])
        {
            vector<int> scc;
            dfs_SCC(ngraph, ele, vis, scc);

            for (int e : scc)
                cout << e << " ";
            cout << endl;
        }
    }
}

void constructGraph()
{
    addEdge(5, 0, 10);
    addEdge(4, 0, 10);
    addEdge(5, 1, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 10);
    addEdge(0, 6, 10);
    addEdge(6, 7, 10);
    addEdge(7, 3, 10);
    addEdge(4, 8, 10);
    addEdge(8, 9, 10);
    addEdge(9, 10, 10);
    addEdge(10, 3, 10);

    topologicalOrder_DFS();
}

int main()
{
    constructGraph();
    return 0;
}
