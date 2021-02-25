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

const int N = 8;
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

int printAllPath(int src, int dest, vector<bool> &vis, string psf)
{
    if (src == dest)
    {
        cout << psf << dest << endl;
        return 1;
    }

    int count = 0;
    vis[src] = true;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            count += printAllPath(e.v, dest, vis, psf + to_string(src) + " ");
    }

    vis[src] = false;
    return count;
}

class heavyPair
{
public:
    int weight = 0;
    string path = "";

    heavyPair(int weight, string path)
    {
        this->weight = weight;
        this->path = path;
    }
};

// heavy Path -> print : path and weight

heavyPair heavyPath(int src, int dest, vector<bool> &vis)
{
    if (src == dest)
    {
        heavyPair base(0, to_string(dest));
        return base;
    }

    vis[src] = true;
    heavyPair myAns(-1e8, "");

    for (Edge e : graph[src])
    {
        if (!vis[e.v])
        {
            heavyPair recAns = heavyPath(e.v, dest, vis);
            if (recAns.weight != -1e8 && recAns.weight + e.w > myAns.weight)
            {
                myAns.weight = recAns.weight + e.w;
                myAns.path = to_string(src) + " " + recAns.path;
            }
        }
    }

    vis[src] = false;
    return myAns;
}

int hamintonialPathAndCycle(int src, int osrc, int totalNoEdges, vector<bool> &vis, string psf)
{
    if (totalNoEdges == N - 1)
    {
        int idx = findEdge(osrc, src);
        if (idx != -1)
            cout << "Cycle : " + psf + to_string(src);
        else
            cout << "Path : " + psf + to_string(src);
        cout << endl;
        return 1;
    }

    vis[src] = true;
    int count = 0;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            count += hamintonialPathAndCycle(e.v, osrc, totalNoEdges + 1, vis, psf + to_string(src) + " ");
    }

    vis[src] = false;
    return count;
}

int hamintonialPathAndCycle(int src)
{
    vector<bool> vis(N, false);
    cout << hamintonialPathAndCycle(src, src, 0, vis, "") << endl;
}

void dfs(int src, vector<bool> &vis, vector<int> &ans)
{
    vis[src] = true;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            dfs(e.v, vis, ans);
    }

    ans.push_back(src);
}

// get conected components
int gcc()
{
    vector<vector<int>> res;
    vector<bool> vis(N, false);
    int components = 0;
    for (int i = 0; i < N; i++)
    {
        if (!vis[i])
        {
            vector<int> ans;
            dfs(i, vis, ans);
            res.push_back(ans);
            components++;
        }
    }

    return components;
}

void BFS_Cycle(int src, vector<bool> &vis)
{
    queue<int> que;
    que.push(src);

    int dest = 6;
    int atLevel = -1;

    bool isCycle = false;
    int level = 0;

    while (que.size() != 0)
    {
        int size = que.size();
        cout << level << " -> ";
        while (size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            cout << rvtx << " ";
            if (vis[rvtx])
            {
                isCycle = true;
                continue;
            }

            if (rvtx == dest)
            {
                atLevel = level;
            }

            vis[rvtx] = true;
            for (Edge e : graph[rvtx])
            {
                if (!vis[e.v])
                    que.push(e.v);

            }
        }
        level++;
        cout << endl;
    }

    cout << dest << " present at : " << atLevel << endl;
    cout << "isCycle : " << (boolalpha) << isCycle << endl;
}

void BFS_shortestPath(int src, vector<bool> &vis)
{
    queue<int> que;
    que.push(src);

    int dest = 6;
    int atLevel = -1;

    int level = 0;
    vis[src] = true;

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int rvtx = que.front();
            que.pop();
            for (Edge e : graph[rvtx])
            {
                if (!vis[e.v])
                {
                    vis[e.v] = true;
                    que.push(e.v);
                }

                if (e.v == dest)
                    atLevel = level + 1;
            }
        }
        level++;
    }

    cout << dest << " present at : " << atLevel << endl;
}

void BFS_printshortestPath(int src, vector<bool> &vis)
{
    queue<int> que;
    que.push(src);

    int dest = 6;
    int atLevel = -1;

    int level = 0;
    vis[src] = true;

    vector<int> par(N, -1);

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int rvtx = que.front();
            que.pop();
            for (Edge e : graph[rvtx])
            {
                if (!vis[e.v])
                {
                    vis[e.v] = true;
                    que.push(e.v);
                    par[e.v] = rvtx;
                }

                if (atLevel == -1 && e.v == dest)
                    atLevel = level + 1;
            }
        }
        level++;
    }

    cout << dest << " present at : " << atLevel << endl;
    int idx = dest;
    while (idx != -1)
    {
        cout << idx << " -> ";
        idx = par[idx];
    }
}

void BFS_GCC()
{
    vector<bool> vis(N, 0);
    int components = 0;
    for (int i = 0; i < N; i++)
    {
        if (!vis[i])
        {
            components++;
            //BFS_Cycle(i, vis);
            BFS_printshortestPath(i,vis);
        }
    }
}



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

    // addEdge(0, 6, 3);
    addEdge(7, 2, 3);
    addEdge(6, 2, 3);
    addEdge(6, 7, 3);
}

int main()
{
    constructGraph();
    // vector<bool> vis(N, false);
    // cout << printAllPath(0, 6, vis, "") << endl;
    // hamintonialPathAndCycle(0);

    return 0;
}
