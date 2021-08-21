#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int orangesRotting(vector<vector<int>> &grid)
{
    int n = grid.size(), m = grid[0].size();
    queue<int> que;

    int time = 0, orangeCount = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 2)
                que.push(i * m + j);
            else if (grid[i][j] == 1)
                orangeCount++;
        }
    }

    if (orangeCount == 0)
        return 0;

    vector<vector<int>> dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int idx = que.front();
            que.pop();

            int sr = idx / m, sc = idx % m;

            for (vector<int> &d : dir)
            {
                int r = sr + d[0];
                int c = sc + d[1];

                if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                {
                    grid[r][c] = 2;
                    que.push(r * m + c);
                    orangeCount--;
                    if (orangeCount == 0)
                        return time + 1;
                }
            }
        }
        time++;
    }

    return -1;
}

//743
int dijikstra(vector<vector<pair<int,int>>> &graph, int V, int src)
{
    vector<bool> vis(V +1, false);
    vector<int> dis(V + 1,1e8);
    
    // {wsf,vtx}
    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> pq;

    pq.push({0,src});
    while (pq.size() != 0)
    {
        pair<int,int> p = pq.top();
        pq.pop();
        if (vis[p.second])
            continue;

        vis[p.second] = true;
        dis[p.second] = p.first;
        for (pair<int,int> e : graph[p.second])
        {
            if (!vis[e.first])
                pq.push({p.first + e.second,e.first});
        }
    }
        
    int maxTime = 0;
    for(int i = 1;i <= V;i++){
        if(dis[i] == 1e8) return -1;
        maxTime = max(maxTime, dis[i]);
    }
                        
    return maxTime;
                        
    
}

    
    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        // {vtx,w}
        vector<vector<pair<int,int>>> graph(n+1,vector<pair<int,int>>());
        for(vector<int>& time : times){
            graph[time[0]].push_back({time[1],time[2]});
        }
        
        return dijikstra(graph,n,k);
    }