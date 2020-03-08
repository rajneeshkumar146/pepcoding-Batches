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

const int n = 9;
vector<Edge *> graph[n];
vector<Edge *> Newgraph[n];
// vector<vector<Edge *>> graph(7, vector<Edge *>()); //
// ArrayList<Integer>[] graph=new ArrayList[7];

void addEdge(int u, int v, int w)
{
    graph[u].push_back(new Edge(v, w));
    graph[v].push_back(new Edge(u, w));
}

void display()
{
    for (int i = 0; i < n; i++)
    {
        cout << i << " -> ";
        for (Edge *e : graph[i])
        {
            cout << "(" << e->v << ", " << e->w << ") ";
        }
        cout << endl;
    }

    cout << endl;
}

int serachVertex(int u, int v)
{
    int idx = -1;
    for (int i = 0; i < graph[u].size(); i++)
    {
        if (graph[u][i]->v == v)
        {
            idx = i;
            break;
        }
    }

    return idx;
}

void removeEdge(int u, int v)
{
    int vIdx = serachVertex(u, v);
    int uIdx = serachVertex(v, u);

    if (uIdx != -1)
    {
        graph[u].erase(graph[u].begin() + vIdx);
        graph[v].erase(graph[v].begin() + uIdx);
    }
}

void removeVertex(int u)
{
    for (int i = graph[u].size() - 1; i >= 0; i--)
    {
        int v = graph[u][i]->v;
        removeEdge(u, v);
    }
}

bool hasPath(int src, int desti, vector<bool> &vis, string ans)
{

    if (src == desti)
    {
        cout << ans + " " + to_string(desti) << endl;
        return true;
    }

    vis[src] = true;

    bool res = false;
    for (Edge *e : graph[src])
    {
        if (!vis[e->v])
            res = res || hasPath(e->v, desti, vis, ans + to_string(src) + " ");
    }

    return res;
}

int allPath(int src, int desti, vector<bool> &vis, string ans)
{

    if (src == desti)
    {
        cout << ans + to_string(desti) << endl;
        return 1;
    }
    vis[src] = true;

    int count = 0;
    for (Edge *e : graph[src])
    {
        if (!vis[e->v])
            count += allPath(e->v, desti, vis, ans + to_string(src) + " ");
    }

    vis[src] = false;

    return count;
}

void hamintonianPath(int src, int osrc, vector<bool> &vis, int count, string ans)
{
    if (count == n - 1)
    {
        int vtx = serachVertex(src, osrc);
        if (vtx != -1)
        {
            cout << "Cycle: " + ans + to_string(src) << endl;
        }
        else
        {
            cout << "Path: " + ans + to_string(src) << endl;
        }

        return;
    }

    vis[src] = true;
    for (Edge *e : graph[src])
    {
        if (!vis[e->v])
        {
            hamintonianPath(e->v, osrc, vis, count + 1, ans + to_string(src));
        }
    }
    vis[src] = false;
}

void dfs_surrounded_regions(int r, int c, int n, int m, vector<vector<char>> &board)
{
    if (board[r][c] != 'O')
        return;

    board[r][c] = '#';
    if (r - 1 >= 0)
        dfs_surrounded_regions(r - 1, c, n, m, board);
    if (r + 1 < n)
        dfs_surrounded_regions(r + 1, c, n, m, board);
    if (c - 1 >= 0)
        dfs_surrounded_regions(r, c - 1, n, m, board);
    if (c + 1 < m)
        dfs_surrounded_regions(r, c + 1, n, m, board);
}

void surrounded_regions(vector<vector<char>> &board)
{
    if (board.size() == 0)
        return;

    int n = board.size();
    int m = board[0].size();

    for (int i = 0; i < n; i++)
    {
        if (board[i][0] == 'O')
            dfs_surrounded_regions(i, 0, n, m, board);
        if (board[i][m - 1] == 'O')
            dfs_surrounded_regions(i, m - 1, n, m, board);
    }

    for (int i = 0; i < m; i++)
    {
        if (board[0][i] == 'O')
            dfs_surrounded_regions(0, i, n, m, board);
        if (board[n - 1][i] == 'O')
            dfs_surrounded_regions(n - 1, i, n, m, board);
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (board[i][j] == 'O')
                board[i][j] = 'X';
            else if (board[i][j] == '#')
                board[i][j] = 'O';
        }
    }
}

void BFS(int src, vector<bool> &vis)
{
    queue<int> que;
    que.push(src);
    int level = 0;

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            if (rvtx == 6)
            {
                cout << "destination at level: " << level << endl;
            }

            if (vis[rvtx])
            {
                cout << "Cycle wrt " + to_string(src) + " : " + to_string(rvtx) << endl;
                continue;
            }

            vis[rvtx] = true;
            for (Edge *e : graph[rvtx])
            {
                if (!vis[e->v])
                    que.push(e->v);
            }
        }
        level++;
    }
}

void GCC()
{
    vector<bool> vis(n, false);
    int count = 0;

    for (int i = 0; i < n; i++)
    {
        if (!vis[i])
        {
            BFS(i, vis);
            count++;
        }
    }

    cout << "GCC: " << count << endl;
}

bool bipartite(int src, vector<int> vis)
{
    queue<pair<int, int>> que;
    que.push({src, 0});

    while (que.size() != 0)
    {
        pair<int, int> rpair = que.front();
        que.pop();

        int u = rpair.first;
        int c = rpair.second;

        if (vis[u] != -1)
        {
            if (vis[u] != c) //conflict.
                return false;
            else
                continue;
        }

        vis[u] = c;
        for (Edge *e : graph[u])
        {
            if (vis[e->v] == -1)
            {
                que.push({e->v, (c + 1) % 2});
            }
        }
    }

    return true;
}

void bipartite()
{
    vector<int> vis(n, -1);
    for (int i = 0; i < n; i++)
    {
        if (vis[i] == -1)
        {
            cout << (boolalpha) << bipartite(i, vis) << endl;
        }
    }
}

class dPair
{
public:
    int src = 0;
    int par = 0;
    int w = 0;
    int wsf = 0;
    string psf = "";

    dPair(int src, int par, int w, int wsf, string psf)
    {
        this->src = src;
        this->par = par;
        this->w = w;
        this->wsf = wsf;
        this->psf = psf;
    }

    bool operator<(dPair const &o) const // dijikstra
    {
        return this->wsf > o.wsf;
    }

    // bool operator<(dpair_ const &o) const // prims
    // {
    //     return this->w > o.w;
    // }
};

void dijikstraAlgo(int src)
{
    vector<bool> vis(n, false);
    priority_queue<dPair> que;
    que.push(dPair(src, -1, 0, 0, to_string(src)));

    while (que.size() != 0)
    {
        dPair pair = que.top();
        que.pop();

        if (!vis[pair.src])
        {
            if (pair.par != -1)
            {
                Newgraph[pair.src].push_back(new Edge(pair.par, pair.w));
                Newgraph[pair.par].push_back(new Edge(pair.src, pair.w));
            }
        }
        else
            continue;

        vis[pair.src] = true;
        for (Edge *e : graph[pair.src])
        {
            if (!vis[e->v])
                que.push(dPair(e->v, pair.src, e->w, pair.wsf + e->w, pair.psf + to_string(e->v)));
        }
    }

    //display.
    for (int i = 0; i < n; i++)
    {
        cout << i << " -> ";
        for (Edge *e : graph[i])
        {
            cout << "(" << e->v << ", " << e->w << ") ";
        }
        cout << endl;
    }

    cout << endl;
}

vector<int> dis(n, 0);
vector<int> low(n, 0);
vector<int> par(n, -1);
vector<int> AP(n, 0);
vector<bool> vis(n, false);

int countTime = 0;
int rootCount = 0;

void dfs(int u)
{
    dis[u] = low[u] = countTime++;
    vis[u] = true;
    for (int nbr : graph[u])
    {
        if (!vis[nbr]) //unvisited
        {
            if (par[u] == -1)
                rootCount++;
            par[nbr] = u;
            dfs(nbr);

            if (dis[u] <= low[nbr])
                AP[u]++;
            // if (low[u] < low[nbr])
            //     cout << "AP Edge: " << u << " -> " << nbr << endl;
            low[u] = min(low[u], low[nbr]);
        }
        else if (par[u] != nbr) //visited
        {
            low[u] = min(low[u], dis[nbr]);
        }
    }
}

void APAndB()
{

    int src = 0;
    dfs(src)

        if (rootCount == 1) AP[src]--;
}

void createGraph()
{

    addEdge(0, 1, 10);
    addEdge(0, 3, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 40);
    addEdge(3, 4, 2);
    addEdge(4, 5, 2);
    addEdge(4, 6, 8);
    addEdge(5, 6, 3);

    addEdge(7, 8, 3);
    addEdge(2, 7, 3);
    addEdge(2, 8, 3);

    // display();
    // removeEdge(3, 2);
    // removeVertex(3);

    display();
}

void solve()
{
    createGraph();
    vector<bool> vis(n, false);
    // cout << hasPath(0, 6, vis, "") << endl;
    // cout << allPath(0, 6, vis, "") << endl;
    // BFS(0, vis);

    // GCC();
    // dijikstraAlgo(0);
}

main()
{
    solve();
    return 0;
}