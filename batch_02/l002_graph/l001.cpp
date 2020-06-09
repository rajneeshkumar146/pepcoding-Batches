#include <iostream>
#include <vector>
#include <queue>
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

int n = 7;
vector<vector<Edge *>> graph(n, vector<Edge *>());
vector<vector<Edge *>> dGraph(n, vector<Edge *>());
vector<int> dShortestPath(n, 0);

void display(vector<vector<Edge *>> &gp)
{
    for (int i = 0; i < gp.size(); i++)
    {
        cout << i << " -> ";
        for (Edge *e : gp[i])
        {
            cout << "(" << e->v << ", " << e->w << ") ";
        }
        cout << endl;
    }
}

void addEdge(vector<vector<Edge *>> &gp, int u, int v, int w)
{
    if (u < 0 || v < 0 || u >= n || v >= n)
        return;

    gp[u].push_back(new Edge(v, w));
    gp[v].push_back(new Edge(u, w));
}

void constructGraph()
{
    addEdge(graph, 0, 1, 11);
    addEdge(graph, 0, 3, 10);
    addEdge(graph, 1, 2, 20);
    addEdge(graph, 2, 3, 40);
    addEdge(graph, 3, 4, 2);
    addEdge(graph, 4, 5, 2);
    addEdge(graph, 4, 6, 3);
    addEdge(graph, 5, 6, 8);

    // addEdge(0, 6, 8);
    // addEdge(2, 5, 8);
    // addEdge(8, 9, 8);
    // addEdge(7, 9, 8);

    // display(graph);
    cout << endl;
}

void removeEdge(int u, int v)
{
    int i = 0;
    while (i < graph[u].size())
    {
        Edge *e = graph[u][i];
        if (e->v == v)
        {
            break;
        }
        i++;
    }

    int j = 0;
    while (j < graph[v].size())
    {
        Edge *e = graph[v][j];
        if (e->v == u)
        {
            break;
        }
        j++;
    }

    graph[u].erase(graph[u].begin() + i);
    graph[v].erase(graph[v].begin() + j);
}

void removeVtx(int u)
{
    while (graph[u].size() != 0)
    {
        Edge *e = graph[u][graph[u].size() - 1];
        removeEdge(u, e->v);
    }
}

void hasPath(int src, int dest, vector<bool> &vis, string psf)
{
    cout << psf << endl;
    vis[src] = true;
    for (Edge *e : graph[src])
    {
        if (!vis[e->v])
        {
            hasPath(e->v, dest, vis, psf + to_string(e->v));
        }
    }
}

int allPath(int src, int dest, vector<bool> &vis, int wsf, string psf)
{
    if (src == dest)
    {
        cout << psf + " @ " + to_string(wsf) << endl;
        return 1;
    }
    int count = 0;
    // cout << psf << endl;
    vis[src] = true;
    for (Edge *e : graph[src])
    {
        if (!vis[e->v])
        {
            count += allPath(e->v, dest, vis, wsf + e->w, psf + to_string(e->v));
        }
    }
    vis[src] = false;
    return count;
}

int swsf = 1e8;
string spsf = "";

int lwsf = -1;
string lpsf = "";

void allSolutions(int src, int desti, vector<bool> &vis, int wsf, string psf)
{
    if (src == desti)
    {
        if (wsf < swsf)
        {
            swsf = wsf;
            spsf = psf;
        }

        if (wsf > lwsf)
        {
            lwsf = wsf;
            lpsf = psf;
        }
    }

    vis[src] = true;
    for (Edge *e : graph[src])
    {
        if (!vis[e->v])
            allSolutions(e->v, desti, vis, wsf + e->w, psf + to_string(src) + "->");
    }
    vis[src] = false;
}

void dfs(int src, vector<bool> &vis)
{
    vis[src] = true;
    for (Edge *e : graph[src])
        if (!vis[e->v])
            dfs(e->v, vis);
}

void getConnectedComponents()
{
    vector<bool> vis(n, false);
    int compo = 0;
    for (int i = 0; i < n; i++)
    {
        if (!vis[i])
        {
            compo++;
            dfs(i, vis);
        }
    }
    cout << compo << endl;
}

void bfs(int src)
{
    queue<int> que;
    vector<bool> vis(n, false);

    que.push(src);
    que.push(-1);
    int cycle = 0, level = 0;
    int dest = 6;
    bool isDest = false;

    while (que.size() != 1)
    {
        int rvtx = que.front();
        que.pop();

        if (rvtx == -1)
        {
            level++;
            que.push(-1);
            continue;
        }

        if (vis[rvtx])
        {
            cout << "cycle: " << ++cycle << " @ " << rvtx << endl;
            continue;
        }

        if (rvtx == dest && !isDest)
        {
            cout << "ypiee! i got destination at lowest no of edges from src: " << level << endl;
            isDest = true;
        }

        vis[rvtx] = true;
        for (Edge *e : graph[rvtx])
            if (!vis[e->v])
                que.push(e->v);
    }
}

void bfs2(int src)
{
    queue<int> que;
    vector<bool> vis(n, false);

    que.push(src);

    bool isDest = false;
    int cycle = 0, level = 0;
    int dest = 9;

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            if (vis[rvtx])
            {
                cout << "cycle: " << ++cycle << " @ " << rvtx << endl;
                continue;
            }

            if (rvtx == dest && !isDest)
            {
                cout << "ypiee! i got destination at lowest no of edges from src: " << level << endl;
                isDest = true;
            }

            vis[rvtx] = true;
            for (Edge *e : graph[rvtx])
                if (!vis[e->v])
                    que.push(e->v);
        }
        level++;
    }
}

int hamintonainPath(int vtx, int osrc, int vtxCount, vector<bool> &vis, string ans)
{
    if (vtxCount == graph.size() - 1)
    {
        bool flag = false;
        for (Edge *e : graph[vtx])
        {
            if (e->v == osrc)
            {
                cout << "cycle: " + ans + to_string(vtx) << endl;
                flag = true;
            }
        }

        if (!flag)
            cout << "path: " + ans + to_string(vtx) << endl;
        return 1;
    }

    vis[vtx] = true;
    int count = 0;

    for (Edge *e : graph[vtx])
        if (!vis[e->v])
            count += hamintonainPath(e->v, osrc, vtxCount + 1, vis, ans + to_string(vtx) + " ");

    vis[vtx] = false;
    return count;
}

bool isBipartite_(int i, vector<int> &vis)
{
    queue<pair<int, int>> que;
    bool flag = true;
    que.push({i, 0});

    while (que.size() != 0)
    {
        pair<int, int> rvtx = que.front();
        que.pop();

        if (vis[rvtx.first] != -1)
        {
            if (vis[rvtx.first] != rvtx.second)
            {
                cout << "conflict: " << endl;
                flag = false;
            }
            continue;
        }

        vis[rvtx.first] = rvtx.second;
        for (Edge *e : graph[rvtx.first])
        {
            if (vis[e->v] == -1)
                que.push({e->v, (rvtx.second + 1) % 2});
        }
    }

    return flag;
}

void isBipartite()
{
    vector<int> vis(graph.size(), -1);
    int count = 0;

    for (int i = 0; i < graph.size(); i++)
    {
        if (vis[i] == -1)
        {

            cout << count << " " << (boolalpha) << isBipartite_(i, vis) << endl;
            count++;
        }
    }
}

class dpair
{
public:
    int vtx = 0; // u -> v
    int parent = 0;
    int wt = 0;
    int wsf = 0;

    dpair(int vtx, int parent, int wt, int wsf)
    {
        this->vtx = vtx;
        this->parent = parent;
        this->wt = wt;
        this->wsf = wsf;
    }

    bool operator<(dpair const &o) const
    {
        return this->wsf > o.wsf;
    }
};

void dijikstra(int src)
{
    priority_queue<dpair> que;
    que.push(dpair(src, -1, 0, 0));
    vector<bool> vis(n, false);

    while (que.size() != 0)
    {
        dpair rp = que.top();
        que.pop();

        if (vis[rp.vtx]) // for cycle.l
            continue;

        if (rp.parent != -1)
        {
            addEdge(dGraph, rp.vtx, rp.parent, rp.wt);
            dShortestPath[rp.vtx] = rp.wsf;
        }

        vis[rp.vtx] = true;
        for (Edge *e : graph[rp.vtx])
        {
            if (!vis[e->v])
                que.push(dpair(e->v, rp.vtx, e->w, rp.wsf + e->w));
        }
    }

    display(dGraph);
    cout << endl;
    for (int ele : dShortestPath)
    {
        cout << ele << " ";
    }
}

vector<int> low(n, -1);
vector<int> dis(n, -1);
vector<bool> ApPoints(n, false);

int time_ = 0;
int callsForRoot = 0;

void dfs_AP(int src, int par, int osrc)
{
    low[src] = dis[src] = time_++;
    for (Edge *e : graph[src])
    {
        if (dis[e->v] == -1)
        { //unVisited.

            if (src == osrc)
                callsForRoot++;

            dfs_AP(e->v, src, osrc);

            if (dis[src] <= low[e->v]) //AP point
                ApPoints[src] = true;
            if (dis[src] < low[e->v]) //AP Bridge.
                cout << "AP Edge" << src << " to " << e->v << endl;

            low[src] = min(low[src], low[e->v]);
        }
        else if (e->v != par)
        { //visited
            low[src] = min(low[src], dis[e->v]);
            // region for BackEdge.
        }
    }
}

void AP_pointsAnsBridges()
{
    dfs_AP(0, -1, 0);
    if (callsForRoot > 1)
    {
        cout << 0 << endl;
    }

    for (int i = 0; i < ApPoints.size(); i++)
    {
        if (ApPoints[i] && i != 0)
        {
            cout << i << endl;
        }
    }
}

void solve()
{
    constructGraph();
    //    removeEdge(3,4);

    // vector<bool> vis(n, false);

    //    hasPath(0,6,vis,to_string(0) + "");
    // cout << allPath(0, 6, vis, 0, to_string(0) + "") << endl;

    // allSolutions(0, 6, vis, 0, "");
    // cout << spsf << " @ " << swsf << endl;
    // cout << lpsf << " @ " << lwsf << endl;
    // bfs(0);

    // cout << hamintonainPath(0, 0, 0, vis, "") << endl;
    // isBipartite();
    // dijikstra(2);
    AP_pointsAnsBridges();
}

int main()
{
    solve();
    return 0;
}
