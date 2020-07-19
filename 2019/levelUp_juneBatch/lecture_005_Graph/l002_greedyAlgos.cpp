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

//Djikstra Algo.============================================================

class DijiPair
{
public:
    int src, par, w, wsf;

    DijiPair(int src, int par, int w, int wsf)
    {
        this->src = src;

        this->par = par;
        this->w = w;

        this->wsf = wsf;
    }
};

class compareDijiTo
{
public:
    bool operator()(const DijiPair &self, const DijiPair &other) const
    {
        return self.wsf > other.wsf;
    }
};

int dikistra(int source, int desti, vector<bool> &vis)
{
    vector<vector<Edge>> dijiGraph(N, vector<Edge>());
    priority_queue<DijiPair, vector<DijiPair>, compareDijiTo> pq;

    vector<int> mDistanceSF(N, 1e8); // minimum distance so far

    pq.push(DijiPair(source, -1, 0, 0));
    while (pq.size() != 0)
    {
        DijiPair rvtx = pq.top();
        pq.pop();

        if (vis[rvtx.src]) // for Cycle.
            continue;

        if (rvtx.par != -1) // for construct graph
            addEdge(dijiGraph, rvtx.src, rvtx.par, rvtx.w);

        vis[rvtx.src] = true;

        for (Edge e : graph[rvtx.src])
        {
            if (!vis[e.v] && mDistanceSF[e.v] > rvtx.wsf + e.w)
            {
                mDistanceSF[e.v] = rvtx.wsf + e.w;
                pq.push(DijiPair(e.v, rvtx.src, e.w, rvtx.wsf + e.w));
            }
        }
    }

    display(dijiGraph);
    return mDistanceSF[desti];
}

void constructGraph()
{
    N = 9;
    graph.resize(N, vector<Edge>());
    vector<vector<int>> edges{
        {0, 1, 4}, {0, 7, 8}, {1, 7, 11}, {1, 2, 8}, {2, 8, 2}, {7, 8, 7}, {7, 6, 1}, {8, 6, 6}, {2, 5, 4}, {2, 3, 7}, {3, 5, 14}, {3, 4, 9}, {4, 5, 10}, {5, 6, 2}};

    for (vector<int> &e : edges)
        addEdge(graph, e[0], e[1], e[2]);

    vector<bool> vis(N, false);
    // for (int i = 0; i < N; i++)
    // {
    //     if (!vis[i])
    //         PrimsAlgo_01(0, vis);
    // }

    cout << dikistra(0, 8, vis) << endl;

    // display(graph);
}

int main()
{
    constructGraph();
    return 0;
}