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

    Edge()
    {
    }
};

// vector<vector<Edge *>> graph(7, vector<Edge *>());
vector<vector<Edge *>> graph;

void addEdge(int u, int v, int w)
{
    if (u < 0 || v < 0 || u >= graph.size() || v >= graph.size())
        return;

    graph[u].push_back(new Edge(v, w));
    graph[v].push_back(new Edge(u, w));
}

void display()
{
    for (int i = 0; i < graph.size(); i++)
    {
        cout << i << " => ";
        for (int j = 0; j < graph[i].size(); j++)
        {
            cout << "( " << graph[i][j]->v << ", " << graph[i][j]->w << "), ";
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

int allPath(int src, int dest, vector<bool> &vis, string ans)
{
    if (src == dest)
    {
        cout << ans + to_string(dest) << endl;
        return 1;
    }

    vis[src] = true; //mark
    int count = 0;

    for (int i = 0; i < graph[src].size(); i++)
    {
        int nbr = graph[src][i]->v;
        if (!vis[nbr])                                                       //chek for vis
            count += allPath(nbr, dest, vis, ans + to_string(src) + " -> "); //call
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

void solve()
{
    for (int i = 0; i < 7; i++)
    {
        vector<Edge *> ar;
        graph.push_back(ar);
    }

    addEdge(0, 1, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 40);
    addEdge(0, 3, 10);
    addEdge(3, 4, 2);
    addEdge(4, 5, 2);
    addEdge(5, 6, 8);
    addEdge(4, 6, 3);

    // removeVtx(3);

    vector<bool> vis(7, false);
    // cout<<hashPath(0,6,vis,"")<<endl;
    // cout<<allPath(0,6,vis,"")<<endl;
    preOderPath(0, 0, vis, "");

    // display();
}

int main()
{
    solve();
    return 0;
}
