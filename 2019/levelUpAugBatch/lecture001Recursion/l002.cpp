#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> vis;

// vector<vector<int>> dir{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
// vector<string> dirS{"D", "L", "U", "R"};

vector<vector<int>> dir{{1, 0}, {0, -1}, {-1, 0}, {0, 1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
vector<string> dirS{"D", "L", "U", "R", "S", "N", "E", "W"};

int floodfill(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    vis[sr][sc] = 1;
    for (int d = 0; d < dir.size(); d++)
    {
        int r = sr + dir[d][0];
        int c = sc + dir[d][1];

        if (r >= 0 && c >= 0 && r < vis.size() && c < vis[0].size() && vis[r][c] == 0)
            count += floodfill(r, c, er, ec, ans + dirS[d]);
    }

    vis[sr][sc] = 0;
    return count;
}

int floodfill_jump(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    vis[sr][sc] = 1;
    for (int jump = 1; jump <= max(er, ec); jump++)
    {
        for (int d = 0; d < dir.size(); d++)
        {
            int r = sr + jump * dir[d][0];
            int c = sc + jump * dir[d][1];

            if (r >= 0 && c >= 0 && r < vis.size() && c < vis[0].size() && vis[r][c] == 0)
                count += floodfill_jump(r, c, er, ec, ans + dirS[d] + to_string(jump) + " ");
        }
    }

    vis[sr][sc] = 0;
    return count;
}

void pathSet()
{
    int n = 3;
    int m = 3;
    vis.resize(n, vector<int>(m, 0));
    // cout << floodfill(0, 0, 2, 2, "") << endl;
    cout << floodfill_jump(0, 0, 2, 2, "") << endl;
}


void solve()
{
    pathSet();
}

int main()
{
    solve();
}