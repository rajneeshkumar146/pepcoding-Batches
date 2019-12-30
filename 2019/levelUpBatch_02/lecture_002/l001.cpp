#include <iostream>
#include <vector>
using namespace std;

bool isLocationSafe(vector<vector<int>> &board, int x, int y)
{
    if (x < 0 || y < 0 || x >= board.size() || y >= board[0].size() || board[x][y] == 1)
        return false;
    return true;
}

vector<string> floodFill_returnType(int sr, int sc, int er, int ec, int len, vector<vector<int>> &board, vector<vector<int>> &dir, vector<string> &path)
{
    if (sr == er && sc == ec)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> myAns;
    board[sr][sc] = 1;
    for (int d = 0; d < dir.size(); d++)
    {
        for (int rad = 1; rad <= len; rad++)
        {
            int x = sr + rad*dir[d][0];
            int y = sc + rad*dir[d][1];
            if (isLocationSafe(board, x, y))
            {
                vector<string> recAns = floodFill_returnType(x, y, er, ec, len,board, dir, path);
                for (string s : recAns)
                {
                    myAns.push_back(path[d] + s);
                }
            }
        }
    }
    board[sr][sc] = 0;
    return myAns;
}

int floodFill_voidType(int sr, int sc, int er, int ec, int len, vector<vector<int>> &board,
                       vector<vector<int>> &dir, vector<string> &path, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return true;
    }

    board[sr][sc] = 1;
    int count = 0;
    for (int d = 0; d < dir.size(); d++)
    {
        for (int rad = 1; rad <= len; rad++)
        {
            int x = sr + rad * dir[d][0];
            int y = sc + rad * dir[d][1];
            if (isLocationSafe(board, x, y))
            {
                count += floodFill_voidType(x, y, er, ec, len, board, dir, path, ans + path[d] + to_string(rad) + " ");
            }
        }
    }

    board[sr][sc] = 0;
    return count;
}

void floodFill()
{
    vector<vector<int>> board(3, vector<int>(3, 0));
    vector<vector<int>> dir = {{0, 1}, {1, 0}, {1, 1}};
    vector<string> path = {"H", "V", "D"};

    vector<string> ans = floodFill_returnType(0, 0, 2, 2, 1, board, dir, path);
    for (string s : ans)
    {
        cout << s << endl;
    }

    //    cout<< floodFill_voidType(0, 0, 2, 2, 1, board, dir, path,"")<<endl;
}

//=============================================

void solve()
{
    floodFill();
}

int main()
{
    solve();
    return 0;
}