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

const int N = 7;
vector<vector<Edge>> graph(N);

void addEdge(int u, int v, int w)
{
    graph[u].push_back(Edge(v, w));
    graph[v].push_back(Edge(u, w));
}

void display()
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

int findEdge(int u, int v)
{
    int idx = -1;
    for (int i = 0; i < graph[u].size(); i++)
    {
        if (graph[u][i].v == v)
        {
            idx = i;
            break;
        }
    }

    return idx;
}

void removeEdge(int u, int v)
{
    int idx1 = findEdge(u, v);
    int idx2 = findEdge(v, u);

    graph[u].erase(graph[u].begin() + idx1);
    graph[v].erase(graph[v].begin() + idx2);
}

void removeVtx(int u)
{
    for (int i = graph[u].size() - 1; i >= 0; i--)
    {
        int v = graph[u][i].v;
        removeEdge(u, v);
    }
}

bool hasPath(int src, int dest)
{
}

int printAllPath(int src, int dest)
{
}

// heavy Path -> print : path and weight

void constructGraph()
{

    // for(int i =0;i<e;i++){
    //     int u,v,w; cin >> u >>v>>w;
    //     addEdge(u,v,w);
    // }

    addEdge(0, 1, 10);
    addEdge(0, 3, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 40);
    addEdge(3, 4, 2);
    addEdge(4, 5, 2);
    addEdge(4, 6, 8);
    addEdge(5, 6, 3);
}

int main()
{
    return 0;
}
