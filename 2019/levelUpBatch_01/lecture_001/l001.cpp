#include <iostream>
#include <vector>
using namespace std;

int height(int n)
{
    int h = 0;
    while (n != 0)
    {
        h++;
        n >>= 1;
    }
    return h;
}

int count = 0;
void binaryTree(int level, string ans, int n)
{
    if (level == 0 && count <= n)
    {
        cout << ans << endl;
        count++;

        return;
    }

    if (count <= n)
        binaryTree(level - 1, ans + "0", n);
    if (count <= n)
        binaryTree(level - 1, ans + "1", n);
}

vector<vector<int>> dir = {{1, 0}, {0, 1},{1,1}};
vector<string> dirPath = {"h", "v","d"};

bool isValidSpot(int r, int c, vector<vector<int>> &board)
{
    if (r < 0 || c < 0 || r >= board.size() || c >= board[0].size() || board[r][c])
        return false;
    return true;
}

int floodFill(int sr, int sc, int er, int ec, int jumpLen, string ans, vector<vector<int>> &board)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 0;
    }

    int maxHeight = 0;

    board[sr][sc] = 1;
    for (int rad = 1; rad <= jumpLen; rad++)
    {
        for (int i = 0; i < dir.size(); i++)
        {
            int x = sr + rad * dir[i][0];
            int y = sc + rad * dir[i][1];

            if (isValidSpot(x, y, board))
            {
                maxHeight = max(maxHeight, floodFill(x, y, er, ec, jumpLen, ans + dirPath[i] + to_string(rad) + " ", board));
            }
        }
    }
    board[sr][sc] = 0;
    return maxHeight + 1;
}

int main()
{
    // int n;
    // cin >> n;
    // int h = height(n);
    // binaryTree(h, "", n);
    vector<vector<int>> arr(3, vector<int>(3, 0));
    cout << floodFill(0, 0, 2, 2, arr.size() - 1, "", arr) << endl;

    return 0;
}