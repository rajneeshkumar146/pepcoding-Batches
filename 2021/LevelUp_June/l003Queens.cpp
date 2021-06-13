#include <iostream>
#include <vector>

using namespace std;

// tnb : total no of boxes, tnq : total no queens
int queenCombination(int tnb, int tnq, int bno, int qno, string ans)
{
    if (qno == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = bno; i < tnb; i++)
    {
        count += queenCombination(tnb, tnq, i + 1, qno + 1, ans + "b" + to_string(i) + "q" + to_string(qno) + " ");
    }

    // count += queenCombination(tnb, tnq, bno + 1, qno, ans);

    return count;
}

int queenPermutation(vector<bool> &boxes, int tnq, int bno, int qno, string ans)
{
    if (qno == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = bno; i < boxes.size(); i++)
    {
        if (!boxes[i])
        {
            boxes[i] = true;
            count += queenPermutation(boxes, tnq, 0, qno + 1, ans + "b" + to_string(i) + "q" + to_string(qno) + " ");
            boxes[i] = false;
        }
    }

    return count;
}

int queenCombination2D(vector<vector<bool>> &board, int tnq, int bno, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0, n = board.size(), m = board[0].size();
    for (int i = bno; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        count += queenCombination2D(board, tnq - 1, i + 1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
    }

    return count;
}

int queenPermutation2D(vector<vector<bool>> &board, int tnq, int bno, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0, n = board.size(), m = board[0].size();
    for (int i = bno; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        if (!board[r][c])
        {
            board[r][c] = true;
            count += queenPermutation2D(board, tnq - 1, 0, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            board[r][c] = false;
        }
    }

    return count;
}

void queenCombination()
{
    int tnb = 5, tnq = 3;
    vector<bool> boxes(tnb, false);
    // cout << queenCombination(tnb, tnq, 0, 0, "") << endl;
    cout << queenPermutation(boxes, tnq, 0, 0, "") << endl;

    vector<vector<bool>> board(4, vector<bool>(4, false));
    // cout << queenCombination2D(board, 4, 0, "") << endl;
    //  cout << queenPermutation2D(board, 4, 0, "") << endl;
}

int main()
{
    queenCombination();
    return 0;
}
