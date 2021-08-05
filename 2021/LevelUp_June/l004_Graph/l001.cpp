#include <vector>
#include <iostream>
#include <list>

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

int findEdge(vector<vector<Edge>> &graph, int u, int v)
{
    for (int i = 0; i < graph[u].size(); i++)
    {
        Edge e = graph[u][i];
        if (e.v == v)
            return i;
    }

    return -1;
}

void removeEdge(vector<vector<Edge>> &graph, int u, int v)
{
    int idx1 = findEdge(graph, u, v);
    graph[u].erase(graph[u].begin() + idx1);

    int idx2 = findEdge(graph, v, u);
    graph[v].erase(graph[v].begin() + idx2);
}

void preOrder(vector<vector<Edge>> &graph, int src, vector<bool> &vis, int wsf, string psf)
{
    cout << (to_string(src) + " -> " + (psf + to_string(src)) + "@" + to_string(wsf));
    vis[src] = true;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            preOrder(graph, e.v, vis, wsf + e.w, psf + src);
    }

    vis[src] = false;
}

class pair_
{
public:
    int heavyPath = 0;
    string psf = "";

    pair_()
    {
    }

    pair_(int heavyPath, string psf)
    {
        this->heavyPath = heavyPath;
        this->psf = psf;
    }
};

pair_ heavyPath(vector<vector<Edge>> &graph, int src, int dest, vector<bool> &vis)
{
    if (src == dest)
    {
        pair_ base(0, to_string(src) + "");
        return base;
    }

    vis[src] = true;
    pair_ myAns(-1, "");
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
        {
            pair_ recAns = heavyPath(graph, e.v, dest, vis);
            if (recAns.heavyPath != -1 && recAns.heavyPath + e.w > myAns.heavyPath)
            {
                myAns.heavyPath = recAns.heavyPath + e.w;
                myAns.psf = to_string(e.v) + recAns.psf;
            }
        }
    }

    vis[src] = false;
}

void hamintonainPathAndCycle(vector<vector<Edge>> &graph, int src)
{
}

void bfs(vector<vector<Edge>> &graph, int src, vector<bool> &vis)
{
    list<int> que;
    que.push_back(src);

    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        cout << "Level: " << level << " ->";

        while (size-- > 0)
        {
            int vtx = que.front();
            que.pop_front();

            if (vis[vtx])
            {
                cout << "cycle";
                continue;
            }

            cout << vtx << ", ";

            vis[vtx] = true;
            for (Edge e : graph[vtx])
            {
                if (!vis[e.v])
                    que.push_back(e.v);
            }
        }

        level++;
        cout << endl;
    }
}

void bfs_withoutCycle(vector<vector<Edge>> &graph, int src, vector<bool> &vis)
{
    list<int> que;
    que.push_back(src);
    vis[src] = true;

    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        cout << "Level: " << level << " ->";

        while (size-- > 0)
        {
            int vtx = que.front();
            que.pop_front();

            cout << vtx << ", ";

            for (Edge e : graph[vtx])
            {
                if (!vis[e.v])
                {
                    vis[e.v] = true;
                    que.push_back(e.v);
                }
            }
        }

        level++;
        cout << endl;
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
