#include <iostream>
#include <vector>
#include <list>
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

    Edge()
    {
    }
};

// vector<vector<Edge *>> graph(7, vector<Edge *>());
vector<vector<Edge *>> graph;
vector<vector<Edge *>> dgraph;

void addEdge(int u, int v, int w)
{
    if (u < 0 || v < 0 || u >= graph.size() || v >= graph.size())
        return;

    dgraph[u].push_back(new Edge(v, w));
    dgraph[v].push_back(new Edge(u, w));
}

void display()
{
    for (int i = 0; i < dgraph.size(); i++)
    {
        cout << i << " => ";
        for (int j = 0; j < dgraph[i].size(); j++)
        {
            cout << "( " << dgraph[i][j]->v << ", " << dgraph[i][j]->w << "), ";
        }
        cout << endl;
    }
}

void removeEdge(int u, int v)
{
    int i = -1;
    int j = -1;

    for (int k = 0; k < graph[u].size(); k++)
    {
        if (graph[u][k]->v == v)
        {
            i = k;
            break;
        }
    }

    for (int k = 0; k < graph[v].size(); k++)
    {
        if (graph[v][k]->v == u)
        {
            j = k;
            break;
        }
    }

    graph[u].erase(graph[u].begin() + i);
    graph[v].erase(graph[v].begin() + j);
}

void removeVtx(int u)
{
    for (int i = graph[u].size() - 1; i >= 0; i--)
    {
        removeEdge(graph[u][i]->v, u);
    }

    // graph.erase(graph.begin()+u);
}

bool hashPath(int src, int dest, vector<bool> &vis, string ans)
{
    if (src == dest)
    {
        cout << ans + to_string(dest) << endl;
        return true;
    }

    vis[src] = true; //mark
    bool res = false;

    for (int i = 0; i < graph[src].size(); i++)
    { //loop
        int nbr = graph[src][i]->v;
        if (!vis[nbr])                                                            //chek for vis
            res = res || hashPath(nbr, dest, vis, ans + to_string(src) + " -> "); //call
    }

    return res;
}

int allPath(int src, int wt, int dest, vector<bool> &vis, string ans)
{
    if (src == dest)
    {
        cout << ans + to_string(dest) << " @ " << wt << endl;
        return 1;
    }

    vis[src] = true; //mark
    int count = 0;

    for (int i = 0; i < graph[src].size(); i++)
    {
        int nbr = graph[src][i]->v;
        int w = graph[src][i]->w;
        if (!vis[nbr])                                                               //chek for vis
            count += allPath(nbr, wt + w, dest, vis, ans + to_string(src) + " -> "); //call
    }

    vis[src] = false; //unmark.
    return count;
}

void preOderPath(int src, int w, vector<bool> &vis, string ans)
{
    vis[src] = true; //mark.
    cout << to_string(src) + " -> " + ans + to_string(src) + " @ " + to_string(w) << endl;

    for (int i = 0; i < graph[src].size(); i++)
    {
        int nbr = graph[src][i]->v;
        int wt = graph[src][i]->w;
        if (!vis[nbr])                                           //chek for vis
            preOderPath(nbr, w + wt, vis, ans + to_string(src)); //call
    }

    vis[src] = false; //unmark.
}

int fwt = 100000;
string fans = "";

void lightestPath(int src, int dest, int wt, vector<bool> &vis, string ans)
{
    if (src == dest)
    {
        if (wt < fwt)
        {
            fwt = wt;
            fans = ans;
        }

        // cout<<ans<<" -> "<<wt<<endl;
        return;
    }

    vis[src] = true;
    for (Edge *e : graph[src])
    {
        int nbr = e->v;
        int w = e->w;
        if (!vis[nbr])
            lightestPath(nbr, dest, wt + w, vis, ans + to_string(nbr));
    }

    vis[src] = false;
}

class pair_path
{
public:
    int wt = 100000;
    string s = "";

    pair_path(int wt, string s)
    {
        this->wt = wt;
        this->s = s;
    }
    pair_path()
    {
    }
};

pair_path *lightestPath_01(int src, int dest, vector<bool> &vis)
{
    if (src == dest)
    {
        pair_path *obj = new pair_path(0, to_string(src) + "");
        return obj;
    }

    vis[src] = true;
    pair_path *myAns = new pair_path();

    for (Edge *e : graph[src])
    {
        int nbr = e->v;
        int w = e->w;
        if (!vis[nbr])
        {
            pair_path *recRes = lightestPath_01(nbr, dest, vis);
            if (recRes->wt + w < myAns->wt)
            {
                myAns->wt = recRes->wt + w;
                myAns->s = to_string(src) + recRes->s;
            }
        }
    }

    vis[src] = false;
    return myAns;
}

void hamintonianPathCycle(int src, int osrc, int cnt, int wt, vector<bool> &vis, string ans)
{
    if (cnt == graph.size() - 1)
    {
        cout << ans + to_string(src);
        for (Edge *e : graph[src])
        {
            if (e->v == osrc)
            {
                cout << " -> Cycle";
            }
        }
        cout << endl;
        return;
    }

    //mark.
    vis[src] = true;
    for (Edge *e : graph[src])
    {
        int nbr = e->v;
        int w = e->w;
        if (!vis[nbr])
        {
            hamintonianPathCycle(nbr, osrc, cnt + 1, wt + w, vis, ans + to_string(src));
        }
    }

    vis[src] = false;
}

class partite_pair
{
public:
    int vtx = -1;
    string color = "";

    partite_pair(int vtx, string color)
    {
        this->vtx = vtx;
        this->color = color;
    }

    partite_pair()
    {
    }
};

bool bipartite(int src, vector<string> &vis)
{
    list<partite_pair> que;
    partite_pair root(src, "R");
    que.push_back(root);

    bool res = true;

    while (que.size() > 0)
    {
        //remove vtx.
        partite_pair rpair = que.front();
        que.pop_front();

        if (vis[rpair.vtx] != "")
        {
            if (!vis[rpair.vtx].compare(rpair.color))
            {
                cout << rpair.vtx << " -> "
                     << "Not bipartite" << endl;
                res = false;
            }
            else
                cout << rpair.vtx << " -> "
                     << "bipartite" << endl;

            continue;
        }

        vis[rpair.vtx] = rpair.color;
        for (Edge *e : graph[rpair.vtx])
        {
            if (vis[e->v] == "")
            {
                partite_pair npair(e->v, rpair.color.compare("R") ? "G" : "R");
                que.push_back(npair);
            }
        }
    }

    return res;
}

class dpair
{
public:
    int vtx = 0, pvtx = 0, wt = 0, wsf = 0;
    string psf = "";

    dpair(int vtx, int pvtx, int wt, int wsf, string psf)
    {
        this->vtx = vtx;
        this->pvtx = pvtx;
        this->wt = wt;
        this->wsf;
        this->psf = psf;
    }
};

bool operator<(const dpair &d1, const dpair &d2)
{
    return d1.wsf > d2.wsf;
}

void dijikstra()
{
    priority_queue<dpair> pq;
    pq.push(dpair(0, -1, 0, 0, "0"));
    vector<bool> vis(graph.size(), false);
    while (!pq.empty())
    {
        dpair rpair = pq.top();
        pq.pop();
        if (rpair.pvtx != -1)
        {
            addEdge(rpair.vtx, rpair.pvtx, rpair.wt);
        }

        vis[rpair.vtx] = true;
        for (Edge *e : graph[rpair.vtx])
        {
            if (!vis[e->v])
            {
                pq.push(dpair(e->v, rpair.vtx, e->w, rpair.wsf + e->w, rpair.psf + " " + to_string(e->v)));
            }
        }
    }

    display();
}

void solve()
{
    for (int i = 0; i < 7; i++)
    {
        vector<Edge *> ar;
        vector<Edge *> ar1;
        graph.push_back(ar);
        dgraph.push_back(ar);
    }

    addEdge(0, 1, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 40);
    addEdge(0, 3, 10);
    addEdge(3, 4, 2);
    addEdge(4, 5, 2);
    addEdge(5, 6, 8);
    addEdge(4, 6, 3);

    // addEdge(2, 5, 13);

    // addEdge(0, 1, 10);
    // addEdge(0, 2, 10);
    // addEdge(2, 3, 40);
    // addEdge(2, 4, 10);
    // addEdge(3, 4, 2);
    // addEdge(1, 3, 2);

    // removeVtx(3);

    // vector<bool> vis(7, false);
    // cout<<hashPath(0,6,vis,"")<<endl;
    // cout<<allPath(0,0,6,vis,"")<<endl;
    // preOderPath(0, 0, vis, "");

    // lightestPath(0, 6, 0, vis, "");
    // cout << fans << " -> " << fwt << endl;

    // pair_path *ans = lightestPath_01(0, 6, vis);
    // cout << ans->s << " -> " << ans->wt << endl;
    // hamintonianPathCycle(2,2,0,0,vis,"");

    // display();
    // vector<string> vis(7, "");
    // for (int i = 0; i < vis.size(); i++)
    // {
    //     if (!vis[i].compare(""))
    //         cout << (boolalpha) << bipartite(i, vis) << endl;
    // }

    dijikstra();
}

int main()
{
    solve();
    return 0;
}
