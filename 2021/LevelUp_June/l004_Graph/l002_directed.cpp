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

void kahnsAlgo_01(int n, vector<vector<Edge>> &graph)
{
    vector<int> indegree(n, 0);
    for (vector<Edge> &edgesList : graph)
    {
        for (Edge e : edgesList)
        {
            indegree[e.v]++;
        }
    }

    queue<int> que;
    vector<vector<int>> ans;

    for (int i = 0; i < n; i++)
        if (indegree[i] == 0)
            que.push(i);

    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        vector<int> smallAns;
        while (size-- > 0)
        {
            int vtx = que.front();
            que.pop();
            smallAns.push_back(vtx);

            for (Edge e : graph[vtx])
            {
                if (--indegree[e.v] == 0)
                    que.push(e.v);
            }
        }
        ans.push_back(smallAns);
        level++;
    }

    if (ans.size() != n)
    {
        cout << ("Topological Order is not possible due to Cycle");
        ans.clear();
    }
}

void constructGraph()
{
    int V = 9;
    vector<vector<Edge>> graph(V, vector<Edge>());

    addEdge(graph, 0, 1, 10);
    addEdge(graph, 0, 3, 10);
    addEdge(graph, 1, 2, 10);
    addEdge(graph, 2, 3, 40);

    addEdge(graph, 2, 7, 2);
    addEdge(graph, 2, 8, 4);
    addEdge(graph, 7, 8, 3);

    addEdge(graph, 3, 4, 2);
    addEdge(graph, 4, 5, 2);
    addEdge(graph, 4, 6, 8);
    addEdge(graph, 5, 6, 3);
}

int main()
{
    constructGraph();
    return 0;
}