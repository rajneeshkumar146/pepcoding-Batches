#include <iostream>
#include <vector>
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

int N;
vector<vector<Edge>> graph;

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

void unionFind(int n, vector<vector<int>> &edges)
{
    for (int i = 0; i < n; i++)
    {
        par.push_back(i);
        size.push_back(1);
    }

    for (vector<int> a : edges) // a = {u,v,w};
    {
        int gp1 = findPar(a[0]); // par of u
        int gp2 = findPar(a[1]); // par of v

        if (gp1 != gp2)
        {
            merge(gp1, gp2);
            addEdge(a[0], a[1], a[2]);
        }
    }
}

void KruskalAlgo(int NoOfVertex, vector<vector<int>> &edges)
{
    graph.resize(NoOfVertex, vector<Edge>());

    sort(edges.begin(), edges.end(), [](auto a, auto b) {
        return a[2] < b[2];
    });

    unionFind(NoOfVertex, edges);

    display();
}

int main()
{
    N = 9;
    vector<vector<int>> edges = {{0, 1, 4}, {0, 7, 8}, {1, 7, 11}, {1, 2, 8}, {7, 8, 7}, {7, 6, 1}, {2, 8, 2}, {8, 6, 6}, {2, 5, 4}, {2, 3, 7}, {6, 5, 2}, {3, 4, 9}, {3, 5, 14}, {5, 4, 10}};
    KruskalAlgo(9, edges);
    return 0;
}
