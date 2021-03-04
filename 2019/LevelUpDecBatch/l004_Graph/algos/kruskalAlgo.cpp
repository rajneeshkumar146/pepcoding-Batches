#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

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

vector<int> par;
vector<int> size;

// with Path Cpmpression :  alpha(n)  <= 4, Inverse Akermann Function
// without path compression : O(logn)
int findPar(int u)
{
    return par[u] == u ? u : par[u] = findPar(par[u]);
}

// O(1)
void merge(int p1, int p2)
{
    if (size[p1] > size[p2])
    {
        par[p2] = p1; // merge
        size[p1] += size[p2];
    }
    else
    {
        par[p1] = p2; // merge
        size[p2] += size[p1];
    }
}

// Edges : {{u,v,w}.....}

// without Path Compression and size : V + E*V
// Path Compression: V + E(alpha(n))
// without Path Compression: V + ELog(V)
void unionFind(int N, vector<vector<int>> &Edges)
{
    vector<vector<Edge>> graph(N);

    for (int i = 0; i < N; i++)
    {
        size.push_back(1);
        par.push_back(i);
    }

    for (vector<int> &edge : Edges)
    {
        int u = edge[0], v = edge[1], w = edge[2];
        int p1 = findPar(u);
        int p2 = findPar(v);

        if (p1 != p2)
        {
            merge(p1, p2);
            addEdge(graph, u, v, w);
        }
    }

    display(N, graph);
}

void solve()
{
    int N = 9;
    vector<vector<int>> edges{{0, 1, 4}, {0, 7, 8}};

    // default behaviour is increasing order.
    sort(edges.begin(), edges.end(), [](vector<int> &a, vector<int> &b) {
        return a[2] < b[2]; // this - other of java replaced as this < other.
    });

    unionFind(N, edges);
}

int main()
{
    solve();
    return 0;
}
