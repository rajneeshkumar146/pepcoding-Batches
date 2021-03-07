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

void addEdge(vector<vector<Edge>> &graph, int u, int v, int w)
{
    graph[u].push_back(Edge(v, w));
    graph[v].push_back(Edge(u, w));
}

void display(int N, vector<vector<Edge>> &graph)
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

class primsPair
{
public:
    int vtx = 0;
    int par = 0;
    int wt = 0;

    primsPair(int vtx, int par, int wt)
    {
        this->vtx = vtx;
        this->par = par;
        this->wt = wt;
    }
};

struct comp
{
    bool operator()(const primsPair &a, const primsPair &b)
    {
        return a.wt > b.wt;
    }
};

void primsAlgo_01(int src, int N, vector<int> &vis, vector<vector<Edge>> &graph)
{
    vector<vector<Edge>> MST(N);

    priority_queue<primsPair, vector<primsPair>, comp> que;
    que.push(primsPair(src, -1, 0));
    int NumberOfEdges = 0;

    // while (que.size() != 0) { // for disconnected graph and more generic way
    // while (que.size() != 0 && NumberOfEdges < N - 1) { // for disconnected graph

    // when you know graph is connected.
    while (NumberOfEdges < N - 1)
    {
        primsPair p = que.top();
        que.pop();
        if (vis[p.vtx])
            continue; // cycle.

        if (p.par != -1)
        {
            addEdge(MST, p.vtx, p.par, p.wt);
            NumberOfEdges++;
        }

        vis[p.vtx] = true;
        for (Edge e : graph[p.vtx])
        {
            if (!vis[e.v])
            {
                que.push(primsPair(e.v, p.vtx, e.w));
            }
        }
    }
}
