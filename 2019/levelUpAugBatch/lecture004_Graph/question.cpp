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
