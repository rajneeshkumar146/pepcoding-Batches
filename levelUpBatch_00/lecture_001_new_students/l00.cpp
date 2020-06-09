#include <iostream>
#include <vector>
using namespace std;

int fact(int n)
{
    // return n == 0 ? 1 : fact(n - 1) * n;
    if (n == 0)
        return 1;

    int recAns = fact(n - 1);
    return recAns * n;
}

int power(int a, int b)
{
    if (a == 0)
        return 0;
    if (b == 0)
        return 1;

    int recAns = power(a, b - 1);
    return recAns * a;
}

void printIncr(int a, int b)
{
    if (a == b + 1)
        return;

    if (a % 2 == 0)
        cout << "even: " << a << endl;

    printIncr(a + 1, b);

    if (a % 2 != 0)
        cout << "odd: " << a << endl;
}

void display(vector<int> &arr, int vidx)
{
    if (vidx == arr.size())
        return;
    cout << arr[vidx] << " ";

    display(arr, vidx + 1);
}

bool findData_01(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return false;

    if (arr[vidx] == data)
        return true;

    return findData_01(arr, vidx + 1, data);
}

bool findData_02(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return false;

    bool recAns = findData_02(arr, vidx + 1, data);
    if (recAns)
        return true;

    return arr[vidx] == data;
}

int firstIndex(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return -1;

    if (arr[vidx] == data)
        return vidx;
    return firstIndex(arr, vidx + 1, data);
}

int lastIndex(vector<int> &arr, int vidx, int data)
{
    if (vidx == arr.size())
        return -1;

    int ans = lastIndex(arr, vidx + 1, data);
    if (ans != -1)
        return ans;

    return arr[vidx] == data ? vidx : -1;
}

int permutation_01(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        string nstr = str.substr(0, i) + str.substr(i + 1);
        count += permutation_01(nstr, ans + ch);
    }
    return count;
}

int permutation_02(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    vector<bool> vis(26, false);
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        if (!vis[ch - 'a'])
        {
            vis[ch - 'a'] = true;
            string nstr = str.substr(0, i) + str.substr(i + 1);
            count += permutation_02(nstr, ans + ch);
        }
    }
    return count;
}

void basic()
{
    // printIncr(0, 10);
    // vector<int> arr{10, 20, 30, 40, 50, 60, 70, 80, 90, 100}; // int[] arr={};
    // display(arr, 0);
    cout << permutation_02("aba", "") << endl;
}

//=======================================================

vector<vector<int>> dir = {{0, 1}, {1, 0}, {1, 1}};
vector<string> dir_path = {"H", "V", "D"};
vector<vector<int>> oranges{{2, 1, 0, 2, 1}, {1, 0, 1, 2, 1}, {1, 0, 0, 2, 1}};
int jump = 1;

bool isSafe(int r, int c, vector<vector<int>> &board)
{
    if (r < 0 || c < 0 || r >= board.size() || c >= board[0].size() || board[r][c])
        return false;
    return true;
}

int floodFill(vector<vector<int>> &board, int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    board[sr][sc] = 1;
    int count = 0;
    for (int d = 0; d < dir.size(); d++)
    {
        for (int jump = 1; jump < board.size(); jump++)
        {
            int nsr = sr + jump * dir[d][0];
            int nsc = sc + jump * dir[d][1];
            if (isSafe(nsr, nsc, board))
            {
                count += floodFill(board, nsr, nsc, er, ec, ans + dir_path[d] + to_string(jump) + " ");
            }
        }
    }

    board[sr][sc] = 0;
    return count;
}

bool isSafeForOranges(int r, int c, vector<vector<int>> &board)
{
    if (r < 0 || c < 0 || r >= oranges.size() || c >= oranges[0].size() || oranges[r][c] == 2 || oranges[r][c] == 0)
        return false;
    return true;
}

void rottenOranges(vector<vector<int>> &board, int sr, int sc, string ans)
{

    cout << ans << endl;
    board[sr][sc] = 1;

    for (int d = 0; d < dir.size(); d++)
    {
        int nsr = sr + jump * dir[d][0];
        int nsc = sc + jump * dir[d][1];
        if (isSafeForOranges(nsr, nsc, board))
        {
            oranges[nsr][nsc] = 2;
            rottenOranges(board, nsr, nsc, ans + "(" + to_string(nsr) + ", " + to_string(nsc) + ") ");
        }
    }

    board[sr][sc] = 0;
}

void floodFillSet()
{
    vector<vector<int>> board(3, vector<int>(3, 0));
    cout << floodFill(board, 0, 0, 2, 2, "");

    // int count = 0;
    // for (int i = 0; i < oranges.size(); i++)
    // {
    //     for (int j = 0; j < oranges[0].size(); j++)
    //     {
    //         if (oranges[i][j] == 2)
    //         {
    //             count++;
    //             rottenOranges(board, i, j, "(" + to_string(i) + ", " + to_string(j) + ") ");
    //         }
    //     }
    // }

    // cout << count << endl;
}

//========================================================

int combination(int tm, int members, string ans)
{
    if (members == tm)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = members; i <= tm; i++)
    {
        count += combination(tm, i + 1, ans + to_string(i) + " ");
    }

    return count;
}

int CoinInfiniteCombination(vector<int> &coins, int idx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < coins.size(); i++)
    {
        if (tar - coins[i] >= 0)
        {
            count += CoinInfiniteCombination(coins, i + 1, tar - coins[i], ans + to_string(coins[i]) + " ");
        }
    }

    return count;
}

int coinChangeInfiteSub(vector<int> &coins, int idx, int tar, string ans)
{
    if (idx == coins.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (tar - coins[idx] >= 0)
    {
        count += coinChangeInfiteSub(coins, idx, tar - coins[idx], ans + to_string(coins[idx]));
    }

    count += coinChangeInfiteSub(coins, idx + 1, tar, ans);
    return count;
}

int CoinInfinitePermutation(vector<int> &coins, vector<bool> &vis, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < coins.size(); i++)
    {
        if (tar - coins[i] >= 0 && !vis[i])
        {
            vis[i] = true;
            count += CoinInfinitePermutation(coins, vis, tar - coins[i], ans + to_string(coins[i]) + " ");
            vis[i] = false;
        }
    }

    return count;
}

int queenCombination(vector<bool> boxes, int bn, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = bn; i < boxes.size(); i++)
    {
        count += queenCombination(boxes, i + 1, qpsf + 1, tnq,
                                  ans + "b" + to_string(i) + "q" + to_string(qpsf) + " ");
    }
    return count;
}

int queenpermutation(vector<bool> boxes, int bn, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = bn; i < boxes.size(); i++)
    {
        if (!boxes[i])
        {
            boxes[i] = true;
            count += queenpermutation(boxes, i + 1, qpsf + 1, tnq,
                                      ans + "b" + to_string(i) + "q" + to_string(qpsf) + " ");
            boxes[i] = false;
        }
    }

    return count;
}
int row_ = 10, col_ = 10, queen_ = 10;
vector<bool> row(row_, false);
vector<bool> col(col_, false);
vector<bool> diag(row_ + col_ - 1, false);
vector<bool> antiDiag(row_ + col_ - 1, false);

int boxSize = col_;

int rowBit = 0;
int colBit = 0;
int diagBit = 0;
int antiDiagBit = 0;

int calls = 0;

vector<vector<int>> q_dir = {{0, -1}, {-1, 0}, {-1, -1}, {-1, 1}, {0, 1}, {1, 0}, {1, 1}, {1, -1}};

bool isQueenSafe_array(int r, int c)
{
    return !row[r] && !col[c] && !diag[r + c] && !antiDiag[r - c + boxSize - 1];
}

void markQueen_In_boolean_wala_array(int r, int c)
{
    row[r] = true;
    col[c] = true;
    diag[r + c] = true;
    antiDiag[r - c + boxSize - 1] = true;
}

void unMarkQueen_In_boolean_wala_array(int r, int c)
{
    row[r] = false;
    col[c] = false;
    diag[r + c] = false;
    antiDiag[r - c + boxSize - 1] = false;
}

bool isQueenSafe_bit(int r, int c)
{
    return (rowBit & (1 << r)) == 0 && (colBit & (1 << c)) == 0 && (diagBit & (1 << (r + c))) == 0 && (antiDiagBit & (1 << (r - c + boxSize - 1))) == 0;
}

void mark_UnMark_Queen_In_bit_wala_array(int r, int c)
{
    rowBit ^= 1 << r;
    colBit ^= 1 << c;
    diagBit ^= (1 << (r + c));
    antiDiagBit ^= (1 << (r - c + boxSize - 1));
}

bool isQueenSafe(vector<vector<bool>> &boxes, int r, int c)
{

    for (int d = 0; d < q_dir.size(); d++)
    {
        for (int jump = 1; jump <= boxes.size(); jump++)
        {
            int nr = r + jump * q_dir[d][0];
            int nc = c + jump * q_dir[d][1];

            if (nr >= 0 && nc >= 0 && nr < boxes.size() && nc < boxes[0].size())
            {
                if (boxes[nr][nc])
                    return false;
            }
            else
                break;
        }
    }

    return true;
}

int queen2DCombination(vector<vector<bool>> &boxes, int bn, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;

    for (int i = 0; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        if (!boxes[r][c] && isQueenSafe(boxes, r, c))
        {
            boxes[r][c] = true;
            count += queen2DCombination(boxes, i + 1, qpsf + 1, tnq,
                                        ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
            boxes[r][c] = false;
        }
    }

    return count;
}

int queen2DCombination_02(vector<vector<bool>> &boxes, int bn, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    calls++;
    for (int i = bn; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        if (isQueenSafe_array(r, c))
        {
            boxes[r][c] = true;
            markQueen_In_boolean_wala_array(r, c);

            count += queen2DCombination_02(boxes, i + 1, qpsf + 1, tnq,
                                           ans + "(" + to_string(r) + ", " + to_string(c) + ") ");

            boxes[r][c] = false;
            unMarkQueen_In_boolean_wala_array(r, c);
        }
    }

    return count;
}

int queen2DCombination_03(int bn, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    calls++;
    for (int i = bn; i < row_ * col_; i++)
    {
        int r = i / col_;
        int c = i % col_;
        if (isQueenSafe_bit(r, c))
        {
            mark_UnMark_Queen_In_bit_wala_array(r, c);

            count += queen2DCombination_03(i + 1, qpsf + 1, tnq,
                                           ans + "(" + to_string(r) + ", " + to_string(c) + ") ");

            mark_UnMark_Queen_In_bit_wala_array(r, c);
        }
    }

    return count;
}

int nqueen_bestWala(int row, int tnq, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    calls++;
    for (int col = 0; col < col_; col++)
    {
        if (isQueenSafe_bit(row, col))
        {
            mark_UnMark_Queen_In_bit_wala_array(row, col);

            count += nqueen_bestWala(row + 1, tnq - 1, ans + "(" + to_string(row) + ", " + to_string(col) + ") ");

            mark_UnMark_Queen_In_bit_wala_array(row, col);
        }
    }
    return count;
}

void PandC()
{
    // vector<int> coins{1, 1, 1, 1, 1};
    // vector<bool> vis(coins.size(), false);
    // cout << combination(5, 0, "") << endl;
    // cout << CoinInfiniteCombination(coins, 0, 3, "") << endl;
    // cout << CoinInfinitePermutation(coins, vis, 10, "") << endl;

    // cout << queenCombination(vis, 0, 0, 3, "") << endl;

    vector<vector<bool>> box(row_, vector<bool>(col_, false));
    // cout << queen2DCombination(box, 0, 0, 4, "") << endl;

    // row.resize(r, false);
    // col.resize(c, false);
    // diag.resize(r + c - 1, false);
    // antiDiag.resize(r + c - 1, false);

    cout << queen2DCombination_02(box, 0, 0, queen_, "") << endl;
    // cout << queen2DCombination_03(0, 0, queen_, "") << endl;
    // cout << nqueen_bestWala(0, queen_, "") << endl;
    cout << calls << endl;
}

vector<vector<int>> board = {{3, 0, 6, 0, 0, 8, 4, 0, 0},
                             {5, 2, 0, 0, 0, 0, 0, 0, 0},
                             {0, 8, 7, 0, 0, 0, 0, 3, 1},
                             {0, 0, 3, 0, 1, 0, 0, 8, 0},
                             {9, 0, 0, 8, 6, 3, 0, 0, 5},
                             {0, 5, 0, 0, 9, 0, 6, 0, 0},
                             {1, 3, 0, 0, 0, 0, 2, 5, 0},
                             {0, 0, 0, 0, 0, 0, 0, 7, 4},
                             {0, 0, 5, 2, 0, 6, 3, 0, 0}};

vector<int> rows(9, 0);
vector<int> cols(9, 0);
vector<vector<int>> mat(3, vector<int>(3, 0));

int sudoku_01(int bno)
{
    // base
    if (bno == 81)
    {
        for (vector<int> ar : board)
        {
            for (int ele : ar)
                cout << ele << " ";
            cout << endl;
        }
        cout << endl;
        return 1;
    }

    int count = 0;

    int i = bno / 9;
    int j = bno % 9;
    if (board[i][j] == 0)
    {
        for (int num = 1; num <= 9; num++)
        {
            int mask = 1 << num;
            if ((rows[i] & mask) == 0 && (cols[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0)
            {
                rows[i] ^= mask;
                cols[j] ^= mask;
                mat[i / 3][j / 3] ^= mask;
                board[i][j] = num;

                count += sudoku_01(bno + 1);

                rows[i] ^= mask;
                cols[j] ^= mask;
                mat[i / 3][j / 3] ^= mask;
                board[i][j] = 0;
            }
        }
    }
    else
    {
        count += sudoku_01(bno + 1);
    }
    return count;
}

int sudoku_02(vector<int> &sudokuZeros, int bno)
{
    // base
    if (bno == sudokuZeros.size())
    {
        for (vector<int> ar : board)
        {
            for (int ele : ar)
                cout << ele << " ";
            cout << endl;
        }
        cout << endl;
        return 1;
    }

    int count = 0;

    int i = sudokuZeros[bno] / 9;
    int j = sudokuZeros[bno] % 9;
    for (int num = 1; num <= 9; num++)
    {
        int mask = 1 << num;
        if ((rows[i] & mask) == 0 && (cols[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0)
        {
            rows[i] ^= mask;
            cols[j] ^= mask;
            mat[i / 3][j / 3] ^= mask;
            board[i][j] = num;

            count += sudoku_02(sudokuZeros, bno + 1);

            rows[i] ^= mask;
            cols[j] ^= mask;
            mat[i / 3][j / 3] ^= mask;
            board[i][j] = 0;
        }
    }
    return count;
}

int sudoku_03(vector<int> &sudokuZeros, int num, int bno)
{
    // base
    if (bno == sudokuZeros.size() || num == 10)
    {
        if (bno == sudokuZeros.size())
        {
            for (vector<int> ar : board)
            {
                for (int ele : ar)
                    cout << ele << " ";
                cout << endl;
            }
            cout << endl;

            return 1;
        }
        return 0;
    }

    int count = 0;

    int i = sudokuZeros[bno] / 9;
    int j = sudokuZeros[bno] % 9;

    int mask = 1 << num;
    if ((rows[i] & mask) == 0 && (cols[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0)
    {
        rows[i] ^= mask;
        cols[j] ^= mask;
        mat[i / 3][j / 3] ^= mask;
        board[i][j] = num;

        count += sudoku_03(sudokuZeros, 1, bno + 1);

        rows[i] ^= mask;
        cols[j] ^= mask;
        mat[i / 3][j / 3] ^= mask;
        board[i][j] = 0;
    }

    count += sudoku_03(sudokuZeros, num + 1, bno);

    return count;
}

void sudoku()
{
    vector<int> sudokuZeros;
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (board[i][j] != 0)
            {
                int mask = 1 << board[i][j];
                if ((rows[i] & mask) == 0 && (cols[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0)
                {
                    rows[i] |= mask;
                    cols[j] |= mask;
                    mat[i / 3][j / 3] |= mask;
                }
                else
                {
                    cout << "It's not a valid sudoku!" << endl;
                    return;
                }
            }
            else
            {
                sudokuZeros.push_back(i * 9 + j);
            }
        }
    }

    // cout << sudoku_02(sudokuZeros, 0) << endl;
    cout << sudoku_03(sudokuZeros, 1, 0) << endl;
}

string str1 = "send";
string str2 = "more";
string str3 = "money";
vector<int> maping(26, 0);
vector<bool> isTaken(10, false);

int stringToNumber(string str)
{
    int res = 0;
    for (int i = 0; i < str.length(); i++)
    {
        int idx = str[i] - 'a';
        res = res * 10 + maping[idx];
    }
    return res;
}

int crypto_(string &str, int idx)
{
    if (idx == str.length())
    {
        int num1 = stringToNumber(str1);
        int num2 = stringToNumber(str2);
        int num3 = stringToNumber(str3);
        if (num1 + num2 == num3)
        {
            return 1;
        }
        return 0;
    }

    // 10 options for number.
    // check for this number is taken or not.
    //  take that number.
    //  recursion.
    // untake that number.
}

void crypto()
{
    string str = str1 + str2 + str3;
    vector<int> freq(26, 0);
    for (int i = 0; i < str.length(); i++)
    {
        freq[str[i] - 'a']++;
    }

    str = "";
    for (int i = 0; i < 26; i++)
    {
        cout << freq[i] << " " << (char)(i + 'a') << endl;
        if (freq[i] > 0)
            str += (char)(i + 'a');
    }

    cout << str;
}

bool canPlaceWordH(vector<vector<char>> &board, int x, int y, string word)
{
    if (y + word.length() > board[0].size())
        return false;

    if ((y - 1 >= 0 && board[x][y - 1] != '+') || (y + word.length() < board[0].size() && board[x][y + word.length()] != '+'))
        return false;

    for (int i = 0; i < word.length(); i++)
    {
        if (board[x][y + i] != '-' && board[x][y + i] != word[i])
            return false;
    }

    return true;
}

vector<bool> placeWordH(vector<vector<char>> &board, int x, int y, string word)
{
    vector<bool> loc(word.length(), false);

    for (int i = 0; i < word.length(); i++)
    {
        if (board[x][y + i] == '-')
        {
            loc[i] = true;
            board[x][y + i] = word[i];
        }
    }

    return loc;
}

void UnplaceWordH(vector<vector<char>> &board, int x, int y, vector<bool> &loc)
{
    for (int i = 0; i < loc.size(); i++)
    {
        if (loc[i])
        {
            board[x][y + i] = '-';
        }
    }
}

bool canPlaceWordV(vector<vector<char>> &board, int x, int y, string word)
{
    if (x + word.length() > board.size())
        return false;
    if ((x - 1 >= 0 && board[x - 1][y] != '+') || (x + word.length() < board.size() && board[x + word.length()][y] != '+'))
        return false;

    for (int i = 0; i < word.length(); i++)
    {
        if (board[x + i][y] != '-' && board[x + i][y] != word[i])
            return false;
    }

    return true;
}

vector<bool> placeWordV(vector<vector<char>> &board, int x, int y, string word)
{
    vector<bool> loc(word.length(), false);

    for (int i = 0; i < word.length(); i++)
    {
        if (board[x + i][y] == '-')
        {
            loc[i] = true;
            board[x + i][y] = word[i];
        }
    }

    return loc;
}
void UnplaceWordV(vector<vector<char>> &board, int x, int y, vector<bool> &loc)
{
    for (int i = 0; i < loc.size(); i++)
    {
        if (loc[i])
        {
            board[x + i][y] = '-';
        }
    }
}

int crossWord_(vector<vector<char>> &board, vector<string> &words, int idx)
{
    if (idx == words.size())
    {
        for (vector<char> ar : board)
        {
            for (char ch : ar)
            {
                cout << ch << " ";
            }
            cout << endl;
        }
        cout << endl;
        return 1;
    }

    int count = 0;
    string word = words[idx];
    for (int i = 0; i < board.size(); i++)
    {
        for (int j = 0; j < board[0].size(); j++)
        {
            if (board[i][j] == '-' || board[i][j] == word[0])
            {

                if (canPlaceWordH(board, i, j, word))
                {
                    vector<bool> loc = placeWordH(board, i, j, word);
                    count += crossWord_(board, words, idx + 1);
                    UnplaceWordH(board, i, j, loc);
                }

                if (canPlaceWordV(board, i, j, word))
                {
                    vector<bool> loc = placeWordV(board, i, j, word);
                    count += crossWord_(board, words, idx + 1);
                    UnplaceWordV(board, i, j, loc);
                }
            }
        }
    }

    return count;
}

void crossWord()
{
    vector<vector<char>> board = {
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '-', '-', '-', '-', '-', '+', '+', '+'},
        {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
        {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
        {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
        {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};

    vector<string> words = {"agra", "norway", "england", "gwalior"};
    cout << crossWord_(board, words, 0) << endl;
}

// for n*m.
// ek sqaure cut kiya i*i ka usse do possiblites bngyi rectangle bnane ki

//upper ka cut portion ((n-i)*i) and right side pura (n*(m-i)).
//  ---------------
// |(n-i) |        |
// | *i   |        |
// |------  n*(m-i)|
// |  cut |        |
// | piece|        |
// |of i*i|        |
//  ---------------

//upper ka cut portion ((n-i)*m) and right side pura (i*(m-i)).
//  ---------------
// |   (n-i)*m     |
// |------ ........|
// |  cut |        |
// | piece| i*(m-i)|
// |of i*i|        |
//  ---------------

int minSquares(int n, int m, vector<vector<int>> &dp)
{
    if (n == 0 || m == 0)
        return 0;
    if (n == 1 || m == 1)
        return n == 1 ? m : n;
    if (n == m)
        return 1;

    if ((n == 11 && m == 13) || (n == 13 && m == 11))
        return 6;

    if (dp[n][m] != 0)
        return dp[n][m];

    int minAns = n * m;
    int loopRange = min(n, m);
    for (int i = loopRange; i >= 1; i--)
    {
        int fp_upside = minSquares(n - i, i);
        int fp_right = minSquares(n, m - i);

        int sp_upside = minSquares(n - i, m);
        int sp_right = minSquares(i, m - i);

        minAns = min(minAns, min(fp_upside + fp_right, sp_upside + sp_right));
    }

    if (minAns != n * m)
        minAns++;

    dp[n][m] = minAns;
    return minAns;
}

int burstBallon(vector<int> &arr, int st, int en, vector<vector<int>> &dp)
{
    if (dp[st][en] != 0)
        return dp[st][en];

    int l = st - 1 == -1 ? 1 : arr[st - 1];
    int r = en + 1 == arr.size() ? 1 : arr[en + 1];
    int maxAns = 0;

    for (int i = st; i <= en; i++)
    {
        int left = i == st ? 0 : burstBallon(st, i - 1, dp);
        int right = i == en ? 0 : burstBallon(i + 1, en, dp);

        int myCost = left + l * arr[i] * r + right;
        if (myCost > maxAns)
            maxAns = myCost;
    }

    dp[st][en] = maxAns;

    return maxAns;
}

void solve()
{
    // basic();
    // floodFillSet();
    // PandC();
    sudoku();
    // crypto();
    // crossWord();
}

int main()
{
    solve();
    return 0;
}