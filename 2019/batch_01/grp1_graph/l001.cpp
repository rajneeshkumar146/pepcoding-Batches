#include <iostream>
#include <vector>

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

const int n = 9;
vector<vector<Edge *>> graph(n, vector<Edge *>());

void addEdge(vector<vector<Edge *>> &g, int u, int v, int w)
{
    g[u].push_back(new Edge(v, w));
    g[v].push_back(new Edge(u, w));
}

void display(vector<vector<Edge *>> &g)
{
    for (int i = 0; i < n; i++)
    {
        cout << "vertex: " << i << ": -> ";
        for (Edge *e : g[i])
        {
            cout << "(" << e->v << ", " << e->w << ") ";
        }
        cout << endl;
    }

    cout << endl;
}

int searchVtx(int u, int v)
{

    int idx = -1;
    for (int i = 0; i < graph[u].size(); i++)
    {
        Edge *e = graph[u][i];
        if (e->v == v)
        {
            idx = i;
            break;
        }
    }

    return idx;
}

void removeEdge(int u, int v)
{
    int vidx = searchVtx(u, v);
    int uidx = searchVtx(v, u);

    graph[u].erase(graph[u].begin() + vidx);
    graph[v].erase(graph[v].begin() + uidx);
}

void removeVtx(int u)
{
    while (graph[u].size() != 0)
    {
        Edge *e = graph[u].back(); //graph[u][graph[u].size()-1];
        removeEdge(u, e->v);
    }
}

bool hasPath(int src, int desti, vector<bool> &vis)
{
    if (src == desti)
        return true;

    bool res = false;
    vis[src] = true; //mark

    for (Edge *e : graph[src])
        if (!vis[e->v])
            res = res || hasPath(e->v, desti, vis);

    return res;
}

int allPath(int src, int desti, vector<bool> &vis, string ans)
{
     if (src == desti)
    {
        cout << ans << desti << endl;
        return 1;
    }


    vis[src] = true; //mark
    int count = 0;

    for (Edge *e : graph[src])
        if (!vis[e->v])
            count += allPath(e->v, desti, vis, ans + to_string(src));

    vis[src] = false; //unMark
    return count;
}



void constructGraph()
{
    addEdge(graph, 0, 1, 10);
    addEdge(graph, 1, 2, 10);
    addEdge(graph, 0, 3, 10);
    addEdge(graph, 3, 2, 40);
    addEdge(graph, 3, 4, 2);
    addEdge(graph, 4, 5, 2);
    addEdge(graph, 4, 6, 3);
    addEdge(graph, 5, 6, 8);

    addEdge(graph, 2, 7, 5);
    addEdge(graph, 2, 8, 5);
    addEdge(graph, 7, 8, 6);
}

void solve()
{
    constructGraph();
    display(graph);

    // removeEdge(4, 3);
    // removeVtx(2);
    // display(graph);

    vector<bool> vis(n, false);
    // cout << hasPath(0, 6, vis);
    cout << allPath(0, 6, vis, "") << endl;
}

int main()
{
    solve();
    return 0;
}
