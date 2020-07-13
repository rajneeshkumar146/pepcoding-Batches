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

int N;
vector<vector<Edge>> graph;

void display()
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

void addEdge(int u, int v, int w)
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

void UnionFind(int n, vector<vector<int>> &edges)
{
    graph.resize(n, vector<Edge>());
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
            addEdge(u, v, w); // if we want to create a graph.
        }
    }

    display();
}

