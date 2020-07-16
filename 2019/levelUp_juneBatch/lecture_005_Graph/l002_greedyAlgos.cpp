#include <iostream>
#include <vector>
#include <queue>

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

int N;
vector<vector<Edge>> graph;

class PrimsPair
{
public:
    int u, par, w;

    PrimsPair(int u, int par, int w)
    {
        this->u = u;
        this->par = par;
        this->w = w;
    }
};

void display(vector<vector<Edge>> &g)
{
    for (int i = 0; i < g.size(); i++)
    {
        cout << i << " -> ";
        for (Edge e : g[i])
            cout << "(" << e.v << ", " << e.w << ") ";
        cout << endl;
    }

    cout << endl;
}

void addEdge(vector<vector<Edge>> &g, int u, int v, int w)
{
    g[u].push_back(Edge(v, w));
    g[v].push_back(Edge(u, w));
}

class compareTo
{
public:
    bool operator()(const PrimsPair &self, const PrimsPair &other) const
    {
        return self.w > other.w;
    }
};

void PrimsAlgo_01(int src, vector<bool> &vis)
{
    vector<vector<Edge>> primsGraph(N, vector<Edge>());
    priority_queue<PrimsPair, vector<PrimsPair>, compareTo> que;
    que.push(PrimsPair(src, -1, 0));

    int noOfEdges = 0;
    int noOfVertex = N;
    while (noOfEdges < noOfVertex - 1)
    // while(que.size()!=0)
    {
        PrimsPair p = que.top();
        que.pop();

        if (vis[p.u])
            continue;

        if (p.par != -1)
        {
            addEdge(primsGraph, p.u, p.par, p.w);
            noOfEdges++;
        }

        vis[p.u] = true;
        for (Edge e : graph[p.u])
        {
            if (!vis[e.v])
                que.push(PrimsPair(e.v, p.u, e.w));
        }
    }

    display(primsGraph);
}

void constructDirectedGraph()
{
    N = 9;
    graph.resize(N, vector<Edge>());
    vector<vector<int>> edges{
        {0, 1, 4}, {0, 7, 8}, {1, 7, 11}, {1, 2, 8}, {2, 8, 2}, {7, 8, 7}, {7, 6, 1}, {8, 6, 6}, {2, 5, 4}, {2, 3, 7}, {3, 5, 14}, {3, 4, 9}, {4, 5, 10}, {5, 6, 2}};

    for (vector<int> &e : edges)
        addEdge(graph, e[0], e[1], e[2]);

    vector<bool> vis(N, false);
    for (int i = 0; i < N; i++)
    {
        if (!vis[i])
            PrimsAlgo_01(0, vis);
    }

    display(graph);
}

int main()
{
    constructDirectedGraph();
    return 0;
}