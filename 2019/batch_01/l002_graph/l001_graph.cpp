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

int allPath(int src, int wt,int dest, vector<bool> &vis, string ans)
{
    if (src == dest)
    {
        cout << ans + to_string(dest) << " @ "<<wt<<endl;
        return 1;
    }

    vis[src] = true; //mark
    int count = 0;

    for (int i = 0; i < graph[src].size(); i++)
    {
        int nbr = graph[src][i]->v;
        int w=graph[src][i]->w;
        if (!vis[nbr])                                                       //chek for vis
            count += allPath(nbr,wt+w, dest, vis, ans + to_string(src) + " -> "); //call
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

void hamintonianPathCycle(int src,int osrc,int cnt,int wt,vector<bool>& vis,string ans){
   if(cnt==graph.size()-1){
       cout<<ans+to_string(src);
       for(Edge* e:graph[src]){
           if(e->v==osrc){
               cout<<" -> Cycle";
           }
       }
      cout<<endl;
      return ;
   }

   //mark.
   vis[src]=true;
    for(Edge* e:graph[src]){
        int nbr=e->v;
        int w=e->w;
        if(!vis[nbr]){
            hamintonianPathCycle(nbr,osrc,cnt+1,wt+w,vis,ans+to_string(src));
        }
    }

   vis[src]=false;
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
    addEdge(0, 3, 10);   
    addEdge(3, 4, 2);
    addEdge(4, 5, 2);
    addEdge(5, 6, 8);
    addEdge(4, 6, 3);

    addEdge(2, 5, 13);

    // removeVtx(3);

    vector<bool> vis(7, false);
    // cout<<hashPath(0,6,vis,"")<<endl;
    // cout<<allPath(0,0,6,vis,"")<<endl;
    preOderPath(0, 0, vis, "");

    // lightestPath(0, 6, 0, vis, "");
    // cout << fans << " -> " << fwt << endl;

    // pair_path *ans = lightestPath_01(0, 6, vis);
    // cout << ans->s << " -> " << ans->wt << endl;
    // hamintonianPathCycle(2,2,0,0,vis,"");

    // display();
}

int main()
{
    solve();
    return 0;
}
