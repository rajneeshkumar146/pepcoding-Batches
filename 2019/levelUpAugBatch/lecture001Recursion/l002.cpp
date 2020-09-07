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

class pathPair
{
public:
    string path;
    int len;

    pathPair(string path, int len)
    {
        this->path = path;
        this->len = len;
    }
};

pathPair floodfillShortestPath_jump(int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        pathPair p("", 0);
        return p;
    }

    int count = 0;
    vis[sr][sc] = 1;
    pathPair ans("", 1e8);
    for (int jump = 1; jump <= max(er, ec); jump++)
    {
        for (int d = 0; d < dir.size(); d++)
        {
            int r = sr + jump * dir[d][0];
            int c = sc + jump * dir[d][1];

            if (r >= 0 && c >= 0 && r < vis.size() && c < vis[0].size() && vis[r][c] == 0)
            {
                pathPair recAns = floodfillShortestPath_jump(r, c, er, ec);
                if (recAns.len + 1 < ans.len)
                {
                    ans.len = recAns.len + 1;
                    ans.path = dirS[d] + to_string(jump) + " " + recAns.path;
                }
            }
        }
    }

    vis[sr][sc] = 0;
    return ans;
}

void pathSet()
{
    int n = 3;
    int m = 3;
    vis.resize(n, vector<int>(m, 0));
    // cout << floodfill(0, 0, 2, 2, "") << endl;
    // cout << floodfill_jump(0, 0, 2, 2, "") << endl;
    pathPair ans = floodfillShortestPath_jump(0, 0, 2, 2);
    cout << ans.path << " -> " << ans.len << endl;
}

//Sudoku.=======================================================================================
vector<vector<int>> board =
    {{3, 0, 0, 0, 0, 0, 0, 0, 0},
     {5, 2, 0, 0, 0, 0, 0, 0, 0},
     {0, 8, 7, 0, 0, 0, 0, 3, 1},
     {0, 0, 3, 0, 1, 0, 0, 8, 0},
     {9, 0, 0, 8, 6, 3, 0, 0, 5},
     {0, 5, 0, 0, 9, 0, 6, 0, 0},
     {1, 3, 0, 0, 0, 0, 2, 5, 0},
     {0, 0, 0, 0, 0, 0, 0, 7, 4},
     {0, 0, 5, 2, 0, 6, 3, 0, 0}};

void print2D()
{
    for (vector<int> &a : board)
    {
        for (int ele : a)
        {
            cout << ele << " ";
        }
        cout << endl;
    }
}

bool isSafeToPlaceNumber(int r, int c, int num)
{
    //Row
    for (int i = 0; i < 9; i++)
    {
        if (board[i][c] == num)
            return false;
    }

    //Col
    for (int i = 0; i < 9; i++)
    {
        if (board[r][i] == num)
            return false;
    }

    //Mat
    r = (r / 3) * 3;
    c = (c / 3) * 3;
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (board[r + i][c + j] == num)
                return false;
        }
    }

    return true;
}

int sudokuSolver(int vidx, vector<int> &loc)
{
    if (vidx == loc.size())
    {
        print2D();
        return 1;
    }

    int idx = loc[vidx];
    int r = idx / 9;
    int c = idx % 9;

    int count = 0;
    for (int num = 1; num <= 9; num++)
    {
        if (isSafeToPlaceNumber(r, c, num))
        {
            board[r][c] = num;
            count += sudokuSolver(vidx + 1, loc);
            board[r][c] = 0;
        }
    }

    return false;
}

int rowA[9] = {0};
int colA[9] = {0};
int matA[3][3] = {0};

//Leetcode 36
bool isValidSudoku(vector<vector<char>> &board)
{
    for (int i = 0; i < board.size(); i++)
    {
        for (int j = 0; j < board[0].size(); j++)
        {
            if (board[i][j] != '.')
            {
                int mask = 1 << (board[i][j] - '0');
                if ((rowA[i] & mask) == 0 && (colA[j] & mask) == 0 && (matA[i / 3][j / 3] & mask) == 0)
                {
                    rowA[i] ^= mask;
                    colA[j] ^= mask;
                    matA[i / 3][j / 3] ^= mask;
                }
                else
                    return false;
            }
        }
    }

    return true;
}

int sudokuSolver_Best(int vidx, vector<int> &loc)
{
    if (vidx == loc.size())
    {
        print2D();
        return 1;
    }

    int idx = loc[vidx];
    int i = idx / 9;
    int j = idx % 9;

    int count = 0;
    for (int num = 1; num <= 9; num++)
    {
        int mask = 1 << num;
        if ((rowA[i] & mask) == 0 && (colA[j] & mask) == 0 && (matA[i / 3][j / 3] & mask) == 0)
        {
            rowA[i] ^= mask;
            colA[j] ^= mask;
            matA[i / 3][j / 3] ^= mask;
            board[i][j] = num;

            count += sudokuSolver_Best(vidx + 1, loc);

            board[i][j] = 0;
            rowA[i] ^= mask;
            colA[j] ^= mask;
            matA[i / 3][j / 3] ^= mask;
        }
    }

    return false;
}

void sudoku()
{
    vector<int> loc;
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (board[i][j] == 0)
            {
                loc.push_back(i * 9 + j);
            }
            else
            {
                int mask = 1 << board[i][j];
                rowA[i] ^= mask;
                colA[j] ^= mask;
                matA[i / 3][j / 3] ^= mask;
            }
        }
    }

    // sudokuSolver(0, loc);
    sudokuSolver_Best(0, loc);
}

//Crypto.==========================================================

string s1 = "send";
string s2 = "more";
string s3 = "money";
vector<int> mapping(26, -1);
int numUsed = 0;

int stringToNumber(string str)
{
    int res = 0;
    for (int i = 0; i < str.length(); i++)
    {
        res = res * 10 + mapping[str[i] - 'a'];
    }

    return res;
}

int cryptoSolver(string str, int idx)
{
    if (idx == str.length())
    {
        int x = stringToNumber(s1);
        int y = stringToNumber(s2);
        int z = stringToNumber(s3);

        if (x + y == z)
        {
            cout << " " << x << "\n+" << y << "\n------\n"
                 << z << endl;
            cout << endl;
            return 1;
        }
        return 0;
    }

    char ch = str[idx];
    int count = 0;
    for (int num = 0; num <= 9; num++)
    {
        int mask = 1 << num;
        if ((numUsed & mask) == 0)
        {
            numUsed ^= mask;
            mapping[ch - 'a'] = num;

            count += cryptoSolver(str, idx + 1);

            numUsed ^= mask;
            mapping[ch - 'a'] = -1;
        }
    }

    return count;
}

void crypto()
{
    string str = s1 + s2 + s3;
    int freq = 0;
    for (char ch : str)
    {
        int mask = 1 << (ch - 'a');
        freq |= mask;
    }

    str = "";
    for (int i = 0; i < 26; i++)
    {
        int mask = 1 << i;
        if ((freq & mask) != 0)
            str += string(1, (char)(i + 'a'));
    }

    // cout << str << endl;
    cout << cryptoSolver(str, 0) << endl;
}

//Cross word.======================================================
vector<vector<char>> box = {{'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                            {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                            {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
                            {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                            {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                            {'+', '-', '-', '-', '-', '-', '-', '+', '+', '+'},
                            {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
                            {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
                            {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
                            {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};

bool canPlaceWordH(int r, int c, string word)
{
    int l = word.length();
    int m = box[0].size();

    if(c==0 && l < m) if(box[r][c+l] !='+') return false;
    else if(c+l==m ) if(box[r][c-1]!='+') return false;
    else
    {
        if((c-1>=0 && box[r][c-1]!='+') || (c+l < m && box[r][c+l]!='+') ) return false;
    }
    

    for (int i = 0; i < word.length(); i++)
    {
        if (c + i >= box[0].size())
            return false;

        if (box[r][c + i] != '-' && box[r][c + i] != word[i])
            return false;
    }

    return true;
}

int placeWordH(int r, int c, string word)
{
    // vector<bool> loc(word.length(), false);
    int loc = 0;
    for (int i = 0; i < word.length(); i++)
    {
        if (box[r][c + i] == '-')
        {
            box[r][c + i] = word[i];
            loc |= 1 << i;
        }
    }
    return loc;
}

void unplaceWordH(int r, int c, string word, int loc)
{
    for (int i = 0; i < word.length(); i++)
    {
        if ((loc & (1 << i)) != 0)
            box[r][c + i] = '-';
    }
}

bool canPlaceWordV(int r, int c, string word)
{
    int l = word.length();
    int n = box.size();

    if(r==0 && l < n) if(box[r+l][c] !='+') return false;
    else if(r+l==n) if(box[r-1][c]!='+') return false;
    else
    {
        if((r-1>=0 && box[r-1][c]!='+') || (r+l < n && box[r+1][c]!='+') ) return false;
    }
    

    for (int i = 0; i < word.length(); i++)
    {
        if (r + i >= box.size())
            return false;

        if (box[r + i][c] != '-' && box[r + i][c] != word[i])
            return false;
    }
    return true;
}

int placeWordV(int r, int c, string word)
{
    // vector<bool> loc(word.length(), false);
    int loc = 0;
    for (int i = 0; i < word.length(); i++)
    {
        if (box[r + i][c] == '-')
        {
            box[r + i][c] = word[i];
            loc |= 1 << i;
        }
    }
    return loc;
}

void unplaceWordV(int r, int c, string word, int loc)
{
    for (int i = 0; i < word.length(); i++)
    {
        if ((loc & (1 << i)) != 0)
            box[r + i][c] = '-';
    }
}

bool crossWord(vector<string> &words, int idx)
{
    if (idx == words.size())
    {
        return true;
    }

    string word = words[idx];
    int count = 0;
    for (int i = 0; i < box.size(); i++)
    {
        for (int j = 0; j < box[0].size(); j++)
        {

            if (box[i][j] == '-' || box[i][j] == word[0])
            {
                if (canPlaceWordH(i, j, word))
                {
                    int loc = placeWordH(i, j, word);
                    if (crossWord(words, idx + 1))
                        return true;
                    unplaceWordH(i, j, word, loc);
                }

                if (canPlaceWordV(i, j, word))
                {
                    int loc = placeWordV(i, j, word);
                    if (crossWord(words, idx + 1))
                        return true;
                    unplaceWordV(i, j, word, loc);
                }
            }
        }
    }

    return false;
}

void crossWord()
{
    vector<string> words = {"agra", "norway", "england", "gwalior"};
    crossWord(words, 0);
    for (int i = 0; i < box.size(); i++)
    {
        for (int j = 0; j < box[0].size(); j++)
        {
            cout << box[i][j] << " ";
        }
        cout << endl;
    }
}

void solve()
{
    // pathSet();
    // sudoku();
    // crypto();
    crossWord();
}

int main()
{
    solve();
}