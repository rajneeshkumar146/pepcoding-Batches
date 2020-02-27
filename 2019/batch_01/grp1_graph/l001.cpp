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
}

int main()
{
    solve();
    return 0;
}
