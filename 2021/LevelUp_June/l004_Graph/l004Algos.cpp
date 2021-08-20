#include <vector>
#include <iostream>
#include <list>
#include <queue>

using namespace std;

class Edge
{
public:
    int v, w;

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

//  O(2E) == O(E)
void display(vector<vector<Edge>> &graph, int V)
{
    for (int i = 0; i < V; i++)
    {
        cout << (i + " -> ");
        for (Edge e : graph[i])
        {
            cout << ("(" + e.v + "," + e.w + ") ");
        }
        cout << endl;
    }
}

class pair_
{
public:
    int src = 0;
    int par = 0;
    int w = 0;
    int wsf = 0;

    // dijikstra
    pair_(int src, int par, int w, int wsf)
    {
        this->src = src;
        this->par = par;
        this->w = w;
        this->wsf = wsf;
    }

    // prims
    pair_(int src, int par, int w)
    {
        pair_(src, par, w, 0);
    }
};

struct comp
{
    bool operator()(pair_ &a, pair_ &b)
    {
        return a.wsf > b.wsf;
    }
};

void dijikstra(vector<vector<Edge>> &graph, int V, int src)
{
    vector<vector<Edge>> mygraph(V, vector<Edge>());

    vector<bool> vis(V, false);
    priority_queue<pair_, vector<pair_>, comp> pq;

    pq.push(pair_(src, -1, 0, 0));
    while (pq.size() != 0)
    {
        pair_ p = pq.top();
        pq.pop();
        if (vis[p.src])
            continue;

        if (p.par != -1)
            addEdge(mygraph, p.src, p.par, p.w);

        vis[p.src] = true;
        for (Edge e : graph[p.src])
        {
            if (!vis[e.v])
                pq.push(pair_(e.v, p.src, e.w, p.wsf + e.w));
        }
    }
}
