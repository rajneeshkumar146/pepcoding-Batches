// Given a matrix, source and destination index and allowed paths(u,d,r,l), print all possible paths and number
// of unique possible paths.

// Includes variation for jumps, blockades and multiple directions.

using namespace std;
#include <iostream>
#include <vector>

vector<vector<int>> dir{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; //direction array can be modified for lesser or more directions
vector<char> dirPath = {'r', 'l', 'd', 'u'};

bool isValid(int x, int y, vector<vector<int>> &board)
{
    //Determiner function to check if a given cell is valid for computation or not
    //Input; input co-ordinate, Board matrix
    //Output: True or False i.e. Valid or inValid

    return (x >= 0 && y >= 0 && x < board.size() && y < board[0].size() && !board[x][y]);
}

int FloodFill(vector<vector<int>> &board, int sx, int sy, int dx, int dy, string ans, int jump)
{
    /* Recursive function to find all possible paths
    Input: Board matrix, source co-ordinate, destination co-ordinate, answer string, max jump length.
    Output: All possible unique paths along with total unique paths possible.
*/
    if (sx == dx && sy == dy)
    { //if latest source co-ordinate reaches destination co-ordinate
        cout << ans << endl;
        return 1;
    }
    int count = 0;

    board[sx][sy] = 1; //Mark cell as visited
    for (int j = 1; j <= jump; j++)
    {
        for (int i = 0; i < dir.size(); i++)
        {
            int x = sx + j * dir[i][0];
            int y = sy + j * dir[i][1];

            if (isValid(x, y, board))
            {
                count += FloodFill(board, x, y, dx, dy, ans + " " + dirPath[i] + to_string(j), jump);
            }
        }
    }

    board[sx][sy] = 0; //Mark cell as unvisited for other paths to use it.

    return count;
}

int main()
{
    int n = 3, m = 3;
    vector<vector<int>> board{{0, 0, 1}, {0, 0, 1}, {1, 0, 0}}; //Pre-set visited cells for the blocked cells. Make all zero for simple Maze question
    cout << endl
         << FloodFill(board, 0, 0, 2, 2, "start", 2);
    return 0;
}