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

int n = 7;
vector<Edge *> graph[7];
// vector<vector<Edge *>> graph(7, vector<Edge *>()); //
// ArrayList<Integer>[] graph=new ArrayList[7];

void addEdge(int u, int v, int w)
{
    graph[u].push_back(new Edge(v, w));
    graph[v].push_back(new Edge(u, w));
}

void display()
{
    for (int i = 0; i < n; i++)
    {
        cout << i << " -> ";
        for (Edge *e : graph[i])
        {
            cout << "(" << e->v << ", " << e->w << ") ";
        }
        cout << endl;
    }

    cout << endl;
}

int serachVertex(int u, int v)
{
    int idx = -1;
    for (int i = 0; i < graph[u].size(); i++)
    {
        if (graph[u][i]->v == v)
        {
            idx = i;
            break;
        }
    }

    return idx;
}

void removeEdge(int u, int v)
{
    int vIdx = serachVertex(u, v);
    int uIdx = serachVertex(v, u);

    if (uIdx != -1)
    {
        graph[u].erase(graph[u].begin() + vIdx);
        graph[v].erase(graph[v].begin() + uIdx);
    }
}

void removeVertex(int u)
{
    for (int i = graph[u].size() - 1; i >= 0; i--)
    {
        int v = graph[u][i]->v;
        removeEdge(u, v);
    }
}

bool hasPath(int src, int desti, vector<bool> &vis, string ans)
{

    if (src == desti)
    {
        cout << ans + " " + to_string(desti) << endl;
        return true;
    }

    vis[src] = true;

    bool res = false;
    for (Edge *e : graph[src])
    {
        if (!vis[e->v])
            res = res || hasPath(e->v, desti, vis, ans + to_string(src) + " ");
    }

    return res;
}

int allPath(int src, int desti, vector<bool> &vis, string ans)
{

    if (src == desti)
    {
        cout << ans + to_string(desti) << endl;
        return 1;
    }
    vis[src] = true;

    int count = 0;
    for (Edge *e : graph[src])
    {
        if (!vis[e->v])
            count += allPath(e->v, desti, vis, ans + to_string(src) + " ");
    }

    vis[src] = false;

    return count;
}

void createGraph()
{
    addEdge(0, 1, 10);
    addEdge(0, 3, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 40);
    addEdge(3, 4, 2);
    addEdge(4, 5, 2);
    addEdge(4, 6, 8);
    addEdge(5, 6, 3);

    // display();
    // removeEdge(3, 2);
    // removeVertex(3);

    display();
}

void solve()
{
    createGraph();
    vector<bool> vis(n, false);
    // cout << hasPath(0, 6, vis, "") << endl;
    cout << allPath(0, 6, vis, "") << endl;
}

main()
{
    solve();
    return 0;
}