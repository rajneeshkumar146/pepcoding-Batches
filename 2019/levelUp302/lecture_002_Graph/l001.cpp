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
// vector<vector<pair<int,int>>> graph(N,vector<pair<int,int>>());

void addEdge(vector<vector<Edge>> &gp, int u, int v, int w)
{
    gp[u].push_back(Edge(v, w));
    gp[v].push_back(Edge(u, w));
}

int findEdge(int v1, int v2)
{
    int i = 0;
    for (i = 0; i < graph[v1].size(); i++)
    {
        Edge e = graph[v1][i];
        if (e.v == v2)
        {
            break;
        }
    }

    return i;
}

void removeEdge(int u, int v)
{

    int idx1 = findEdge(u, v);
    int idx2 = findEdge(v, u);

    graph[u].erase(graph[u].begin() + idx1);
    graph[v].erase(graph[v].begin() + idx2);
}

void removeVtx(int vtx)
{

    while (graph[vtx].size() != 0)
    {
        //int vtx2=graph[vtx][graph[vtx].size()-1];

        Edge e = graph[vtx].back();
        removeEdge(vtx, e.v);
    }
}

void display(vector<vector<Edge>> &gp)
{

    for (int i = 0; i < gp.size(); i++)
    {
        cout << i << " -> ";

        // for(int j=0;j<gp[i].size();j++){
        //     Edge* e=gp[i][j];
        // }

        for (Edge e : gp[i])
        {
            cout << "(" << e.v << ", " << e.w << "), ";
        }
        cout << endl;
    }

    cout << endl;
}

//Question.======================================================

bool hasPath(int src, int dest, vector<bool> &vis)
{
    if (src == dest)
        return true;

    vis[src] = true;
    bool res = false;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            res = res || hasPath(e.v, dest, vis);
    }

    return res;
}

int allPath(int src, int dest, vector<bool> &vis, int w, string ans)
{
    if (src == dest)
    {
        cout << ans << dest << " @ " << w << endl;
        return 1;
    }

    vis[src] = true;

    int count = 0;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            count += allPath(e.v, dest, vis, w + e.w, ans + to_string(src) + " ");
    }

    vis[src] = false;
    return count;
}

class allSolutionPair
{
public:
    int lightW = 1e7;
    int heavyW = 0;
    int ceil = 1e7;
    int floor = 0;
};

void allSolution(int src, int dest, vector<bool> &vis, int w, string ans, allSolutionPair &pair, int data)
{
    if (src == dest)
    {
        pair.heavyW = max(pair.heavyW, w);
        pair.lightW = min(pair.lightW, w);

        if (w > data)
            pair.ceil = min(pair.ceil, w);
        if (w < data)
            pair.floor = max(pair.floor, w);
        return;
    }

    vis[src] = true;

    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            allSolution(e.v, dest, vis, w + e.w, ans + to_string(src) + " ", pair,data);
    }

    vis[src] = false;
}

void preOrder(int src, vector<bool> &vis, int w, string ans)
{
    vis[src] = true;

    cout << ans << " @ " << w << endl;
    for (Edge e : graph[src])
        if (!vis[e.v])
            preOrder(e.v, vis, w + e.w, ans + to_string(e.v) + " ");

    vis[src] = false; 
}

//Basic.========================================================

void constructGraph()
{
    // for(int i=0;i<N;i++){
    //     vector<Edge*> a;
    //     graph.push_back(a);
    // }

    addEdge(graph, 0, 1, 10);
    addEdge(graph, 0, 3, 10);
    addEdge(graph, 1, 2, 10);
    addEdge(graph, 2, 3, 40);
    addEdge(graph, 3, 4, 2);
    addEdge(graph, 4, 5, 2);
    addEdge(graph, 4, 6, 3);
    addEdge(graph, 5, 6, 8);

    display(graph);
    cout << endl;
}

void set1()
{
    // removeEdge(3, 4);
    // removeVtx(3);

    vector<bool> vis(N, false);
    // cout << hasPath(0, 6, vis)<<endl;
    // cout << allPath(0, 6, vis, 0, "") << endl;
    // preOrder(0,vis,0,to_string(0)+ " ");

    allSolutionPair pair;
    allSolution(0, 6, vis, 0, "", pair, 30);
    cout << pair.heavyW << " -> " << pair.lightW << " -> " << pair.ceil << " -> " << pair.floor << " -> " << endl;

    // display(graph);
}

void solve()
{
    constructGraph();
    set1();
}

int main()
{
    solve();
    return 0;
}