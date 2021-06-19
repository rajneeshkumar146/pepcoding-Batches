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

//=================================================================

bool isSafeToPlaceQueen(vector<vector<bool>> &board, int row, int col)
{
    // vector<vector<int>> dir = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    vector<vector<int>> dir = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    int n = board.size(), m = board[0].size();
    for (int d = 0; d < dir.size(); d++)
    {
        for (int rad = 1; rad < board.size(); rad++)
        {
            int r = row + rad * dir[d][0];
            int c = col + rad * dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m)
            {
                if (board[r][c])
                    return false;
            }
            else
                break;
        }
    }

    return true;
}

int nqueen_01_combi(vector<vector<bool>> &board, int tnq, int idx, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0, n = board.size(), m = board[0].size();
    for (int i = idx; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        if (isSafeToPlaceQueen(board, r, c))
        {
            board[r][c] = true;
            count += nqueen_01_combi(board, tnq - 1, i + 1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            board[r][c] = false;
        }
    }

    return count;
}

int nqueen_01_combi_sub(vector<vector<bool>> &board, int tnq, int idx, string ans)
{
    int count = 0, n = board.size(), m = board[0].size();
    if (tnq == 0 || idx == n * m)
    {
        if (tnq == 0)
        {
            cout << ans << endl;
        }
        return tnq == 0 ? 1 : 0;
    }

    int r = idx / m;
    int c = idx % m;
    if (isSafeToPlaceQueen(board, r, c))
    {
        board[r][c] = true;
        count += nqueen_01_combi_sub(board, tnq - 1, idx + 1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
        board[r][c] = false;
    }

    count += nqueen_01_combi_sub(board, tnq, idx + 1, ans);

    return count;
}

int nqueen_01_permu(vector<vector<bool>> &board, int tnq, int idx, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0, n = board.size(), m = board[0].size();
    for (int i = idx; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        if (isSafeToPlaceQueen(board, r, c) && !board[r][c])
        {
            board[r][c] = true;
            count += nqueen_01_permu(board, tnq - 1, 0, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            board[r][c] = false;
        }
    }

    return count;
}

vector<bool> row; //boolean[] row;
vector<bool> col;
vector<bool> diag;
vector<bool> aDiag;

int calls = 0;
int nqueen_02_combi(int n, int m, int tnq, int idx, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    // calls++;
    for (int i = idx; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        if (!row[r] && !col[c] && !diag[r + c] && !aDiag[r - c + m - 1])
        {
            row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = true;
            count += nqueen_02_combi(n, m, tnq - 1, i + 1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = false;
        }
    }

    return count;
}

int nqueen_03_combi(int n, int m, int tnq, int floor, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    // calls++;
    for (int room = 0; room < m; room++)
    {
        int r = floor, c = room;
        if (!row[r] && !col[c] && !diag[r + c] && !aDiag[r - c + m - 1])
        {
            row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = true;
            count += nqueen_03_combi(n, m, tnq - 1, floor + 1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = false;
        }
    }

    return count;
}

int nqueen_03_permu(int n, int m, int tnq, int r, string ans)
{
    if (tnq == 0 || r == n)
    {
        if (tnq == 0)
            cout << ans << endl;
        return tnq == 0 ? 1 : 0;
    }

    int count = 0;
    // calls++;

    count += nqueen_03_permu(n, m, tnq, r + 1, ans);

    for (int c = 0; c < m; c++)
    {
        if (!row[r] && !col[c] && !diag[r + c] && !aDiag[r - c + m - 1])
        {
            row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = true;
            count += nqueen_03_permu(n, m, tnq - 1, 0, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = false;
        }
    }

    return count;
}

int cols = 0;
int diags = 0;
int aDiags = 0;

int nqueen_04_combi(int n, int floor)
{
    if (floor == n)
    {
        return 1;
    }

    int count = 0, m = n;
    for (int room = 0; room < n; room++)
    {
        int r = floor, c = room;
        if ((cols & (1 << c)) == 0 && (diags & (1 << (r + c))) == 0 && (aDiags & (1 << (r - c + m - 1))) == 0)
        {
            cols ^= (1 << c);
            diags ^= (1 << (r + c));
            aDiags ^= (1 << (r - c + m - 1));

            count += nqueen_04_combi(n, floor + 1);

            cols ^= (1 << c);
            diags ^= (1 << (r + c));
            aDiags ^= (1 << (r - c + m - 1));
        }
    }

    return count;
}

void nQueen()
{
    int n = 4, m = 4;
    vector<vector<bool>> board(n, vector<bool>(m, false));
    int tnq = 4;

    // cout << nqueen_01_combi(board, tnq, 0, "") << endl;
    // cout << nqueen_01_combi_sub(board, tnq, 0, "") << endl;
    // cout << nqueen_01_permu(board, tnq, 0, "") << endl;

    row.resize(n, false); // row = new boolean[n];
    col.resize(m, false);
    diag.resize(n + m - 1, false);
    aDiag.resize(n + m - 1, false);

    cout << nqueen_03_combi(n, m, tnq, 0, "") << endl;
    cout << calls << endl;
}

// 51 and 52

void queenCombination()
{
    int tnb = 4, tnq = 3;
    vector<bool> boxes(tnb, false);
    // cout << queenCombination(tnb, tnq, 0, 0, "") << endl;
    // cout << queenPermutation(boxes, tnq, 0, 0, "") << endl;

    vector<vector<bool>> board(4, vector<bool>(4, false));
    cout << queenCombination2D(board, 4, 0, "") << endl;
    //  cout << queenPermutation2D(board, 4, 0, "") << endl;
}

int main()
{
    // queenCombination();
    nQueen();
    return 0;
}
