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

vector<vector<Edge *>> graph(7, vector<Edge *>());

void addEdge(int u, int v, int w)
{
    if (u < 0 || v < 0 || u >= graph.size() || v >= graph.size())
        return;

    graph[u].push_back(new Edge(v, w));
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

bool topologicalSort_(int src, vector<int> &st, vector<bool> &vis,vector<bool> &cycle)
{
    bool res=false;
    vis[src] = true;
    cycle[src]=true;
    for (Edge *e : graph[src])
    {
        if (!vis[e->v])
            res=res || topologicalSort_(e->v, st, vis,cycle);
        else if(cycle[e->v]) return true;
    }
    //post area.
    st.push_back(src);
    cycle[src]=false;
    return res;
}

void topologicalSort(){
    vector<int> st;
    vector<bool> vis(graph.size(),false);
    vector<bool> cycle(graph.size(),false);

    bool res=true;
    for(int i=0;i<graph.size() && res;i++){
        if(!vis[i]){
          if(topologicalSort_(i,st,vis,cycle)) res=false;  
        }
    }

    for(int i=st.size()-1;i>=0 && res;i--){
        cout<<st[i]<<" ";
    }
}

void solve()
{

    //graph1.
    addEdge(0, 3, 1);
    addEdge(0, 2, 1);
    addEdge(1, 2, 1);
    addEdge(1, 5, 1);
    addEdge(3, 4, 1);
    addEdge(4, 6, 1);
    addEdge(5, 6, 1);

    display();

    topologicalSort();
}

int main()
{
    solve();
    return 0;
}
