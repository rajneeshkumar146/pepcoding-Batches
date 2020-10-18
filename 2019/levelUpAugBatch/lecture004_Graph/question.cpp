#include <iostream>
#include <vector>
#include <queue>

using namespace std;

bool isBipartite(vector<vector<int>> &graph, vector<int> &markedColor, int src)
{
    queue<int> que;
    que.push(src);

    int color = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int vtx = que.front();
            que.pop();
            if (markedColor[vtx] != -1)
            {
                if (markedColor[vtx] != color)
                    return false;
                continue;
            }

            markedColor[vtx] = color;
            for (int e : graph[vtx])
            {
                if (markedColor[e] == -1)
                    que.push(e);
            }
        }
        color = (color + 1) % 2;
    }

    return true;
}

bool isBipartite(vector<vector<int>> &graph)
{
    int N = graph.size();
    vector<int> markedColor(N, -1);

    for (int i = 0; i < N; i++)
    {
        if (markedColor[i] == -1 && !isBipartite(graph, markedColor, i))
            return false;
    }

    return true;
}

// Leetocde 1091

//Leetcode 286

//Union Find.=================================================================

vector<int> par;
int findPar(int u)
{
    if (u == par[u])
        return u;
    return par[u] = findPar(par[u]);
}

//684
vector<int> findRedundantConnection(vector<vector<int>> &edges)
{
    int n = edges.size();
    for (int i = 0; i <= n; i++)
        par.push_back(i);

    for (vector<int> a : edges)
    {
        int p1 = findPar(a[0]);
        int p2 = findPar(a[1]);

        if (p1 != p2)
            par[p1] = p2;
        else
            return a;
    }

    return {};
}

int numIslands(vector<vector<char>> &grid)
{
    int n = grid.size();
    if (n == 0)
        return 0;
    int m = grid[0].size();
    int countIslands = 0;
    vector<vector<int>> dir = {{1, 0}, {0, 1}};
    
    vector<int> size;
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            if(grid[i][j]=='1'){
                par.push_back(i*m+j);
                size.push_back(1);
                countIslands++;
            }else{
                par.push_back(-1);
                size.push_back(0);
            }
        }
    }

    // for(int i=0;i<n*m;i++){
    //     par.push_back(i);
    // }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == '1')
            {
                int p1 = findPar(i*m+j);
                for(int d = 0;d<2;d++){
                    int x = i + dir[d][0];
                    int y = j + dir[d][1];

                    if(x >= 0 && y >=0 && x < n && y < m && grid[x][y] == '1'){
                        int p2 = findPar(x*m+y);
                        if(p1 != p2){
                            par[p2] = p1;
                            countIslands--;
                            size[p1] += size[p2];
                        }
                    }
                }
            }
        }
    }


    return countIslands;
}
