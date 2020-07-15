#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

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

void display(vector<vector<Edge>> &graph, int N)
{
    for (int i = 0; i < graph.size(); i++)
    {
        cout << i << " -> ";
        for (Edge e : graph[i])
            cout << "(" << e.v << ", " << e.w << ") ";
        cout << endl;
    }

    cout << endl;
}

void addEdge(vector<vector<Edge>> &graph, int u, int v, int w)
{
    graph[u].push_back(Edge(v, w));
    graph[v].push_back(Edge(u, w));
}

//=====================================================================

vector<int> par;
vector<int> size;

int findPar(int u)
{
    if (par[u] == u)
        return u;
    return par[u] = findPar(par[u]);
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

vector<vector<Edge>> UnionFind(int n, vector<vector<int>> &edges)
{
    vector<vector<Edge>> graph(n, vector<Edge>());
    for (int i = 0; i < n; i++)
    {
        par.push_back(i);
        size.push_back(1);
    }

    for (vector<int> &e : edges)
    {
        int u = e[0];
        int v = e[1];
        int w = e[2];

        int p1 = findPar(u);
        int p2 = findPar(v);

        if (p1 != p2)
        {
            merge(p1, p2);
            addEdge(graph, u, v, w); // if we want to create a graph.
        }
    }

    return graph;
}

//Kruskal Algos.===================================================================

void kruskalAlgo(vector<vector<int>> &arr, int N)
{
    //Java:
    // Arrays.sort(arr, (a, b)->{
    //         return a[2] - b[2];});

    sort(arr.begin(), arr.end(), [](vector<int> &a, vector<int> &b) {
        return a[2] < b[2];
    });

    vector<vector<Edge>> graph = UnionFind(N, arr);
    display(graph, N);
}

struct comapreTo
{
public:
    bool operator()(const vector<int> &a, const vector<int> &b) const
    {
        return a[0] > b[0];
    }
};

//Spaning Tree.
void primsAlgo(int src, vector<bool> &vis, vector<vector<Edge>> &graph, int N)
{
    // priority_queue<vector<int>, vector<vector<int>>,comapreTo> que;
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> que; // {weight,u, parent}
    que.push({0, src, -1});                                                     //{weight, u, v}

    vector<vector<Edge>> ngraph(N, vector<Edge>());
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            vector<int> rvtx = que.top();
            que.pop();

            if (vis[rvtx[1]])
                continue;

            if (rvtx[2] != -1)
                addEdge(ngraph, rvtx[1], rvtx[2], rvtx[0]); //{u, v, w}

            vis[rvtx[1]] = true;
            for (Edge e : graph[rvtx[1]])
            {
                if (!vis[e.v])
                    que.push({e.w, e.v, rvtx[1]});
            }
        }
    }

    display(ngraph, N);
}

void constructDirectedGraph()
{
    int N = 9;
    vector<vector<Edge>> graph(N, vector<Edge>());
    vector<vector<int>> edges{
        {0, 1, 4}, {0, 7, 8}, {1, 7, 11}, {1, 2, 8}, {2, 8, 2}, {7, 8, 7}, {7, 6, 1}, {8, 6, 6}, {2, 5, 4}, {2, 3, 7}, {3, 5, 14}, {3, 4, 9}, {4, 5, 10}, {5, 6, 2}};

    for (vector<int> &e : edges)
        addEdge(graph, e[0], e[1], e[2]);

    // kruskalAlgo(edges, N);

    vector<bool> vis(N, false);
    primsAlgo(0, vis, graph, N);
}

int main()
{
    constructDirectedGraph();
    return 0;
}