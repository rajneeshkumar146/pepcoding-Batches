#include <iostream>
#include <vector>
using namespace std;

int n, m;
vector<vector<int>> dirA{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
vector<string> dirS{"R", "U", "L", "D"};

int floodFillAlgo(int sr, int sc, int er, int ec, vector<vector<int>> &board, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    board[sr][sc] = 1; //mark visited
    for (int d = 0; d < dirA.size(); d++)
    {
        int r = sr + dirA[d][0];
        int c = sc + dirA[d][1];
        if (r >= 0 && c >= 0 && r <= er && c <= ec && board[r][c] == 0)
            count += floodFillAlgo(r, c, er, ec, board, ans + dirS[d]);
    }

    board[sr][sc] = 0; //mark unvisited
    return count;
}

int floodFillJumpAlgo(int sr, int sc, int er, int ec, vector<vector<int>> &board, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    board[sr][sc] = 1; //mark visited
    for (int d = 0; d < dirA.size(); d++)
    {
        for (int rad = 1; rad <= max(n, m); rad++)
        {
            int r = sr + rad * dirA[d][0];
            int c = sc + rad * dirA[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && board[r][c] == 0)
                count += floodFillJumpAlgo(r, c, er, ec, board, ans + dirS[d] + to_string(rad));
        }
    }

    board[sr][sc] = 0; //mark unvisited
    return count;
}

class floodFillPair
{
public:
    string str = "";
    int len = 0;

    floodFillPair(string str, int len)
    {
        this->str = str;
        this->len = len;
    }
};

floodFillPair longestPath(int sr, int sc, int er, int ec, vector<vector<int>> &board)
{
    if (sr == er && sc == ec)
    {
        floodFillPair base("", 0);
        return base;
    }

    floodFillPair myAns("", 0);
    board[sr][sc] = 1; //mark visited
    for (int d = 0; d < dirA.size(); d++)
    {
        for (int rad = 1; rad <= max(n, m); rad++)
        {
            int r = sr + rad * dirA[d][0];
            int c = sc + rad * dirA[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && board[r][c] == 0)
            {
                floodFillPair recAns = longestPath(r, c, er, ec, board);
                if (recAns.len + 1 > myAns.len)
                {
                    myAns.len = recAns.len + 1;
                    myAns.str = dirS[d] + to_string(rad) + recAns.str;
                }
            }
        }
    }

    board[sr][sc] = 0; //mark unvisited
    return myAns;
}

void solve()
{
    n = 3;
    m = 3;
    vector<vector<int>> board(n, vector<int>(m, 0));
    // cout << floodFillAlgo(0, 0, n - 1, m - 1, board, "") << endl;
    // cout << floodFillJumpAlgo(0, 0, n - 1, m - 1, board, "") << endl;
    cout << longestPath(0, 0, n - 1, m - 1, board).str << endl;
}

int main()
{
    solve();
    return 0;
}