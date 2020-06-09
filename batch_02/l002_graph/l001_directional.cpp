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

void display()
{
    for (int i = 0; i < graph.size(); i++)
    {
        cout << i << " -> ";
        for (Edge *e : graph[i])
        {
            cout << "(" << e->v << ", " << e->w << ") ";
        }
        cout << endl;
    }
}

void addEdge(int u, int v, int w)
{
    if (u < 0 || v < 0 || u >= n || v >= n)
        return;

    graph[u].push_back(new Edge(v, w));
}

void constructGraph()
{
    addEdge(0, 1, 10);
    addEdge(0, 3, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 40);
    addEdge(3, 4, 2);
    addEdge(5, 4, 2);
    addEdge(6, 4, 3);
    addEdge(6, 5, 8);

    // addEdge(0, 6, 8);
    // addEdge(2, 5, 8);
    // addEdge(8, 9, 8);
    // addEdge(7, 9, 8);

    display();
    cout << endl;
}

bool topologicalSort_(int src, vector<bool> &vis, vector<bool> &cycleDetection, vector<int> &stack)
{
    vis[src] = true;
    cycleDetection[src] = true;
    bool res = false;

    for (Edge *e : graph[src])
        if (!vis[e->v])
            res = res || topologicalSort_(e->v, vis, cycleDetection, stack);
        else if (cycleDetection[e->v])
            return true;

    stack.push_back(src);
    cycleDetection[src] = false;
    return res;
}

void topologicalSort()
{
    vector<bool> vis(graph.size(), false);
    vector<bool> cycleDetection(graph.size(), false);
    vector<int> stack;
    bool res = false;
    for (int i = 0; i < n; i++)
    {
        if (!vis[i])
            res = res || topologicalSort_(i, vis, cycleDetection, stack);
    }

    while (stack.size() != 0 && !res)
    {
        cout << stack.back() << " ";
        stack.pop_back();
    }

    cout << endl;
}

void khansAlgo()
{
    vector<int> InDegree(graph.size(), 0);
    for (int i = 0; i < graph.size(); i++)
    {
        for (Edge *e : graph[i])
        {
            InDegree[e->v]++;
        }
    }

    queue<int> que;
    for (int i = 0; i < graph.size(); i++)
    {
        if (InDegree[i] == 0)
            que.push(i);
    }

    vector<int> ans;
    while (que.size() != 0)
    {
        int rvtx = que.front();
        que.pop();
        ans.push_back(rvtx);

        for (Edge *e : graph[rvtx])
        {
            InDegree[e->v]--;
            if (InDegree[e->v] == 0)
                que.push(e->v);
        }
    }

    if (ans.size() != graph.size())
        cout << "cycle" << endl;
    else
    {
        for (int i = 0; i < ans.size(); i++)
            cout << ans[i] << " ";
    }
}

void solve()
{
    constructGraph();
    topologicalSort();
    khansAlgo();
}

int main()
{
    solve();
    return 0;
}
