#include <iostream>
#include <vector>

using namespace std;

class Edge
{
public:
    int v;
    int w;

    Edge(int v, int w)
    {
        this->v = v;
        this->w = w;
    }
};

int N = 7;
vector<vector<Edge>> graph(N, vector<Edge>());

void addEdge(int u, int v, int w)
{
    graph[u].push_back(Edge(v, w));
    graph[v].push_back(Edge(u, w));

    // Edge e=graph[3][2];
}

void display()
{
    for (int i = 0; i < N; i++)
    {
        cout << i << " -> ";
        for (Edge e : graph[i])
            cout << "(" << e.v << ", " << e.w << ") ";
        cout << endl;
    }
}

void constructor()
{
    // for (int i = 0; i < N; i++)
    // {
    //     vector<Edge> ar;
    //     graph.push_back(ar);
    // }

    addEdge(0, 1, 10);
    addEdge(0, 3, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 40);
    addEdge(3, 4, 2);
    addEdge(4, 5, 2);
    addEdge(4, 6, 8);
    addEdge(5, 6, 3);
    display();
}

int findVtx(int u, int v)
{
    int idx = -1;
    for (int i = 0; i < graph[u].size(); i++)
    {
        Edge e = graph[u][i];
        if (e.v == v)
        {
            idx = i;
            break;
        }
    }
    return idx;
}

void removeEdge(int u, int v)
{
    int idx1 = findVtx(u, v);
    int idx2 = findVtx(v, u);

    graph[u].erase(graph[u].begin() + idx1);
    graph[v].erase(graph[v].begin() + idx2);
}

void removeVtx(int u)
{
    while (graph[u].size() != 0)
    {
        int v = graph[u][graph[u].size() - 1].v;
        removeEdge(u, v);
    }
}

bool hasPath(int src, int dest, vector<bool> &vis)
{
    if (src == dest)
        return true;

    vis[src] = true; // mark;
    bool res = false;
    for (Edge e : graph[src])
    {
        if (!vis[e.v]) // unvisited
            res = res || hasPath(e.v, dest, vis);
    }

    return res;
}

int hasAllPath(int src, int dest, vector<bool> &vis, string path)
{
    if (src == dest)
    {
        cout << path << dest;
        return 1;
    }

    vis[src] = true;
    int count = 0;

    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            count += hasAllPath(e.v, dest, vis, path + to_string(src) + " ");
    }

    vis[src] = false;
    return count;
}

void solve()
{

    constructor();
    removeVtx(3);
    display();
}

int main()
{
    solve();
    return 0;
}
