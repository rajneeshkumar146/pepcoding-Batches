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

int N = 7;
vector<vector<Edge>> graph(N, vector<Edge>());

void addEdge(int u, int v, int w)
{
    graph[u].push_back(Edge(v, w));
    graph[v].push_back(Edge(u, w));
}

void display()
{
    for (int i = 0; i < N; i++)
    {
        cout << (to_string(i) + " -> ");
        for (Edge e : graph[i])
            cout << "(" + to_string(e.v) + ", " + to_string(e.w) + "), ";

        cout << endl;
    }
}

int searchVtx(int u, int v)
{
    for (int i = 0; i < graph[u].size(); i++)
    {
        Edge e = graph[u][i];
        if (e.v == v)
            return i;
    }

    return -1;
}

void removeEdge(int u, int v)
{
    int l1 = searchVtx(u, v);
    graph[u].erase(graph[u].begin() + l1);

    int l2 = searchVtx(v, u);
    graph[v].erase(graph[v].begin() + l1);
}

void removeVtx(int u)
{
    for (int i = graph[u].size() - 1; i >= 0; i--)
    {
        Edge e = graph[u][i];
        removeEdge(u, e.v);
    }
}


void constructGraph()
{
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

void solve()
{
    constructGraph();
}

int main()
{
    solve();
    return 0;
}
