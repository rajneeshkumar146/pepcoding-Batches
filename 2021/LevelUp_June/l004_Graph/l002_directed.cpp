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

void dfs_01(int src, vector<vector<Edge>> &graph, vector<bool> &vis, vector<int> &st)
{
    vis[src] = true;
    for (Edge &e : graph[src])
    {
        if (!vis[e.v])
        {
            dfs_01(e.v, graph, vis, st);
        }
    }

    st.push_back(src);
}

void dfs_02(int src, vector<vector<Edge>> &graph, vector<bool> &vis)
{
    vis[src] = true;
    cout << src << endl;
    for (Edge &e : graph[src])
    {
        if (!vis[e.v])
        {
            dfs_02(e.v, graph, vis);
        }
    }
}

void kosaRajuAlgo(int n, vector<vector<Edge>> &graph)
{
    vector<bool> vis(n, false);
    vector<int> st;
    for (int i = 0; i < n; i++)
    {
        if (!vis[i])
        {
            dfs_01(i, graph, vis, st);
        }
    }

    //inverse of graph
    vector<vector<Edge>> ngraph(n, vector<Edge>());
    for (int i = 0; i < n; i++)
    {
        for (Edge &e : graph[i])
        {
            Edge ne(i, e.w);
            ngraph[e.v].push_back(ne);
        }
    }

    vis.clear();
    vis.resize(n, false);

    int count = 0;
    for (int i = st.size() - 1; i >= 0; i--)
    {
        if (!vis[st[i]])
        {
            dfs_02(st[i], graph, vis);
            count++;
        }
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