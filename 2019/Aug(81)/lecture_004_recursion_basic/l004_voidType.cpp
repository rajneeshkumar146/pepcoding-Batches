#include <iostream>
#include <vector>
#include <string>

#define vi vector<int>
#define vii vector<vi>

using namespace std;

//basic.===================================

int subsequence(string str, string ans)
{
    if (str.size() == 0)
    {
        cout << ans << " ";
        return 1;
    }

    char ch = str[0];
    string roq = str.substr(1);
    int count = 0;

    count += subsequence(roq, ans + ch); //char want to contribute.
    count += subsequence(roq, ans);      //char didnt want to contribute.

    return count;
}

int permuation(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << " ";
        return 1;
    }

    int count = 0;
    int isSet = 0;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        int mask = 1 << (ch - 'a');
        if ((isSet & mask) == 0)
        {
            string one = str.substr(0, i);
            string two = str.substr(i + 1);

            count += permuation(one + two, ans + ch);
            isSet |= mask;
        }
    }

    return count;
}

string codes[] = {"+", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wx", "yz", "*", "#"};

int keyPad(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << " ";
        return 1;
    }

    char ch = ques[0];
    string roq = ques.substr(1);
    string code = codes[ch - '0'];

    int count = 0;
    for (int i = 0; i < code.length(); i++)
    {
        count += keyPad(roq, ans + code[i]);
    }

    return count;
}

int encoding(string ques, string ans)
{
    if (ques.size() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    char ch = ques[0];
    int count = 0;

    if (ch == '0')
    {
        count += encoding(ques.substr(1), ans);
    }
    else
    {
        count += encoding(ques.substr(1), ans + (char)(ch - '1' + 'a'));

        if (ch < '3' && ques.size() > 1)
        {
            char ch1 = ques[1];
            int num = (ch - '0') * 10 + (ch1 - '0');
            if (num <= 26)
            {
                count += encoding(ques.substr(2), ans + (char)('a' + num - 1));
            }
        }
    }
    return count;
}

void basic()
{
    // << subsequence("abc", "") << endl;
    // cout << permuation("aaa", "") << endl;
    // cout << keyPad("235", "");
    cout << encoding("110028", "") << endl;
}

//pathProblem.===========================
int functionCall = 0;
string res = "";

int mazePath(int sr, int sc, int er, int ec, string ans)
{
    functionCall++;
    if (sr == er && sc == ec)
    {
        // cout<<ans<<" ";
        return 0;
    }

    int count = 0;
    int lefts = 0;
    int rights = 0;
    int diagonals = 0;
    if (sc + 1 <= ec)
    {
        lefts = mazePath(sr, sc + 1, er, ec, ans + "H");
        //  lefts+="H";
    }

    if (sr + 1 <= er)
    {
        rights = mazePath(sr + 1, sc, er, ec, ans + "V");
        //  rights+="V";
    }

    if (sr + 1 <= er && sc + 1 <= ec)
    {
        diagonals = mazePath(sr + 1, sc + 1, er, ec, ans + "D");
        //    diagonals+="D";
    }

    // string ans_="";
    // if(ans_.length()<lefts.length()) ans_=lefts;
    // if(ans_.length()<rights.length()) ans_=rights;
    // if(ans_.length()<diagonals.length()) ans_=diagonals;

    return max(max(lefts, rights), diagonals) + 1;
}

int mazePath_multi(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << " ";
        return 1;
    }

    int count = 0;
    for (int i = 1; sc + i <= ec; i++)
    {
        count += mazePath_multi(sr, sc + i, er, ec, ans + "H" + to_string(i));
    }

    for (int i = 1; sr + i <= er; i++)
    {
        count += mazePath_multi(sr + i, sc, er, ec, ans + "V" + to_string(i));
    }

    for (int i = 1; sr + i <= er && sc + i <= ec; i++)
    {
        count += mazePath_multi(sr + i, sc + i, er, ec, ans + "D" + to_string(i));
    }

    return count;
}

int boardPath(int si, int ei, string ans)
{
    if (si == ei)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int dice = 1; dice <= 6 && si + dice <= ei; dice++)
    {
        count += boardPath(si + dice, ei, ans + to_string(dice));
    }

    return count;
}

void pathProblem()
{
    cout << mazePath(0, 0, 3, 5, "") << endl;
    cout << functionCall << endl;
    cout << res << endl;

    // cout<<mazePath_multi(0,0,2,2,"")<<endl;
    // cout<<boardPath(0,10,"")<<endl;
}

//FloodFillQuestions.=================================
bool isValidLocation(vector<vector<bool>> &isdone, int x, int y)
{
    if (x < 0 || y < 0 || x >= isdone.size() || y >= isdone[0].size() || isdone[x][y])
    {
        return false;
    }
    return true;
}

int floodfill(vector<vector<bool>> &isdone, int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    isdone[sr][sc] = true;

    if (isValidLocation(isdone, sr + 1, sc))
    {
        count += floodfill(isdone, sr + 1, sc, er, ec, ans + "D");
    }

    if (isValidLocation(isdone, sr, sc + 1))
    {
        count += floodfill(isdone, sr, sc + 1, er, ec, ans + "R");
    }

    if (isValidLocation(isdone, sr - 1, sc))
    {
        count += floodfill(isdone, sr - 1, sc, er, ec, ans + "U");
    }

    if (isValidLocation(isdone, sr, sc - 1))
    {
        count += floodfill(isdone, sr, sc - 1, er, ec, ans + "L");
    }

    isdone[sr][sc] = false;

    return count;
}

int floodfill_(vector<vector<bool>> &isdone, int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 0;
    }

    int count = 0;
    isdone[sr][sc] = true;
    int max_ = 0;
    if (isValidLocation(isdone, sr + 1, sc))
    {
        max_ = max(max_, floodfill_(isdone, sr + 1, sc, er, ec, ans + "D"));
    }

    if (isValidLocation(isdone, sr, sc + 1))
    {
        max_ = max(max_, floodfill_(isdone, sr, sc + 1, er, ec, ans + "R"));
    }

    if (isValidLocation(isdone, sr - 1, sc))
    {
        max_ = max(max_, floodfill_(isdone, sr - 1, sc, er, ec, ans + "U"));
    }

    if (isValidLocation(isdone, sr, sc - 1))
    {
        max_ = max(max_, floodfill_(isdone, sr, sc - 1, er, ec, ans + "L"));
    }

    isdone[sr][sc] = false;

    return max_ + 1;
}

int floodfill_dir(vector<vector<bool>> &isdone, int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    isdone[sr][sc] = true;

    string dir_[8] = {"R", "D", "L", "U", "a", "b", "c", "d"};
    int dir[8][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};

    for (int d = 0; d < 8; d++)
    {
        int x = sr + dir[d][0];
        int y = sc + dir[d][1];

        if (isValidLocation(isdone, x, y))
        {
            count += floodfill_dir(isdone, x, y, er, ec, ans + dir_[d]);
        }
    }

    isdone[sr][sc] = false;

    return count;
}

void flodfillQuestions()
{
    vector<vector<bool>> isdone = {
        {0, 1, 0, 0, 0},
        {0, 0, 1, 0, 1},
        {0, 0, 0, 0, 0},
        {0, 1, 0, 0, 0},
    };
    cout << floodfill_dir(isdone, 0, 0, 2, 3, "") << endl;
}

//setQuestion.===================================================

int targetSum(vector<int> &arr, int vidx, int target, string ans)
{
    if (vidx == arr.size() || target <= 0)
    {
        if (target == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    count += targetSum(arr, vidx + 1, target - arr[vidx], ans + to_string(arr[vidx]) + " ");
    count += targetSum(arr, vidx + 1, target, ans);
    return count;
}

int equiSet(vector<int> &arr, int vidx, int set1, int set2, string ans1, string ans2)
{
    if (vidx == arr.size())
    {
        if (set1 == set2)
        {
            cout << ans1 << " = " << ans2 << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;

    // count += equiSet(arr, vidx + 1, set1, set2, ans1, ans2);

    count += equiSet(arr, vidx + 1, set1 + arr[vidx], set2, ans1 + to_string(arr[vidx]) + " ", ans2);
    count += equiSet(arr, vidx + 1, set1, set2 + arr[vidx], ans1, ans2 + to_string(arr[vidx]) + " ");

    return count;
}

void setQuestion()
{
    vector<int> arr = {10, 20, 30, 40, 50, 60, 70};
    // cout << targetSum(arr, 0, 60, "") << endl;
    cout << equiSet(arr, 0, 0, 0, "", "") << endl;
}

//coinChange===============================================

int coinChange_01(vector<int> &arr, int idx, int target, string ans)
{
    if (idx == arr.size() || target == 0)
    {
        if (target == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    for (int i = idx; i < arr.size(); i++)
    {
        if (target - arr[i] >= 0)
            count += coinChange_01(arr, i, target - arr[i], ans + to_string(arr[i]) + " ");
    }

    return count;
}

int coinChange_02(vector<int> &arr, int idx, int target, string ans)
{
    if (idx == arr.size() || target == 0)
    {
        if (target == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    for (int i = idx; i < arr.size(); i++)
    {
        if (target - arr[i] >= 0)
            count += coinChange_02(arr, i + 1, target - arr[i], ans + to_string(arr[i]) + " ");
    }

    return count;
}

int coinChange_04(vector<int> &arr, int idx, int target, string ans)
{
    if (idx == arr.size() || target == 0)
    {
        if (target == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (target - arr[idx] >= 0)
        count += coinChange_04(arr, idx + 1, target - arr[idx], ans + to_string(arr[idx]) + " ");
    count += coinChange_04(arr, idx + 1, target, ans);

    return count;
}

int coinChange_03(vector<int> &arr, int idx, int target, string ans)
{
    if (idx == arr.size() || target == 0)
    {
        if (target == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (target - arr[idx] >= 0)
        count += coinChange_03(arr, idx, target - arr[idx], ans + to_string(arr[idx]) + " ");
    count += coinChange_03(arr, idx + 1, target, ans);

    return count;
}

int coinChange_P01(vector<int> &arr, int idx, int target, string ans)
{
    if (idx == arr.size() || target == 0)
    {
        if (target == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        if (target - arr[i] >= 0)
            count += coinChange_P01(arr, i, target - arr[i], ans + to_string(arr[i]) + " ");
    }

    return count;
}

int coinChange_P02(vector<int> &arr, vector<bool> &isdone, int marked, int target, string ans)
{
    if (marked == arr.size() || target == 0)
    {
        if (target == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        if (target - arr[i] >= 0 && !isdone[i])
        {
            isdone[i] = true;
            count += coinChange_P02(arr, isdone, marked + 1, target - arr[i], ans + to_string(arr[i]) + " ");
            isdone[i] = false;
        }
    }
    return count;
}

int coinChange_P03(vector<int> &arr, int idx, int target, string ans)
{
    if (idx == arr.size() || target == 0)
    {
        if (target == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;

    if (target - arr[idx] >= 0)
        count += coinChange_P03(arr, 0, target - arr[idx], ans + to_string(arr[idx]) + " ");
    count += coinChange_P03(arr, idx + 1, target, ans);

    return count;
}

int coinChange_P04(vector<int> &arr, vector<bool> &isdone, int idx, int target, string ans)
{
    if (idx == arr.size() || target == 0)
    {
        if (target == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;

    if (target - arr[idx] >= 0 && !isdone[idx])
    {
        isdone[idx] = true;
        count += coinChange_P04(arr, isdone, 0, target - arr[idx], ans + to_string(arr[idx]) + " ");
        isdone[idx] = false;
    }
    count += coinChange_P04(arr, isdone, idx + 1, target, ans);

    return count;
}

void coinChange()
{
    vector<int> arr = {2, 3, 5, 7};
    vector<bool> isdone(4, false);
    int target = 10;
    // cout << coinChange_01(arr, 0, target, "") << endl;
    // cout << coinChange_02(arr, 0, target, "") << endl;
    // cout << coinChange_03(arr, 0, target, "") << endl;

    // cout << coinChange_P01(arr, 0, target, "") << endl;
    // cout << coinChange_P02(arr, isdone, 0, target, "") << endl;
    // cout << coinChange_P03(arr, 0, target, "") << endl;
    cout << coinChange_P04(arr, isdone, 0, target, "") << endl;
}

//queenPandC.==============================================

int queenCombi(int boxes, int box, int tnq, int q, string ans)
{
    if (q == tnq + 1)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = box; i <= boxes; i++)
    {
        count += queenCombi(boxes, i + 1, tnq, q + 1,
                            ans + "b" + to_string(i) + "q" + to_string(q));
    }

    return count;
}

int queenPerm(int boxes, int isSet, int tnq, int q, string ans)
{
    if (q == tnq + 1)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i <= boxes; i++)
    {
        int mask = (1 << i);
        if ((isSet & mask) == 0)
        {
            isSet |= mask;
            count += queenPerm(boxes, isSet, tnq, q + 1,
                               ans + "b" + to_string(i) + "q" + to_string(q));
            isSet &= (~mask);
        }
    }

    return count;
}

int queenPerm_2D(vector<vector<bool>> &boxes, int tnq, int q, string ans)
{
    if (q == tnq + 1)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();

        if (!boxes[r][c])
        {
            boxes[r][c] = true;
            count += queenPerm_2D(boxes, tnq, q + 1,
                                  ans + "b" + to_string(i) + "q" + to_string(q) + " ");
            boxes[r][c] = false;
        }
    }
    return count;
}

int queenPerm_2D_sub(vector<vector<bool>> &boxes, int oneDidx, int tnq, int q, string ans)
{
    if (q == tnq + 1 || oneDidx == boxes.size() * boxes[0].size())
    {
        if (q == tnq + 1)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    int r = oneDidx / boxes[0].size();
    int c = oneDidx % boxes[0].size();

    if (!boxes[r][c])
    {
        boxes[r][c] = true;
        count += queenPerm_2D_sub(boxes, 0, tnq, q + 1,
                                  ans + "b" + to_string(oneDidx) + "q" + to_string(q) + " ");
        boxes[r][c] = false;
    }

    count += queenPerm_2D_sub(boxes, oneDidx + 1, tnq, q, ans);

    return count;
}

bool isValidSpot(int r, int c, int n, int m)
{
    if (r < 0 || c < 0 || r >= n || c >= m)
        return false;
    return true;
}

bool isQueenSafe(vector<vector<bool>> &boxes, int x, int y)
{
    int n = boxes.size();
    int m = boxes[0].size();

    int arr[4][2] = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    for (int i = 0; i < 4; i++)
    {
        for (int rad = 1; rad < max(n, m); rad++)
        {
            int r = x + rad * arr[i][0];
            int c = y + rad * arr[i][1];
            if (isValidSpot(r, c, n, m) && boxes[r][c])
                return false;
        }
    }

    return true;
}

int queenCom_2D(vector<vector<bool>> &boxes, int idx, int tnq, int qpsf, string ans, int &stop)
{
    if (qpsf == tnq)
    {
        // if (stop == 0)
        cout << ans << endl;
        stop = 1;
        return 1;
    }

    int n = boxes.size();
    int m = boxes[0].size();

    int count = 0;
    for (int i = idx; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;

        if (isQueenSafe(boxes, r, c) && count == 0)
        {
            boxes[r][c] = true;
            count += queenCom_2D(boxes, i + 1, tnq, qpsf + 1,
                                 ans + "(" + to_string(r) + ", " + to_string(c) + ")q" + to_string(qpsf + 1) + " ", stop);

            boxes[r][c] = false;
        }
    }

    return count;
}

bool nQueenSub(vector<vector<bool>> &boxes, int idx, int tnq, int qpsf, string ans)
{

    int n = boxes.size();
    int m = boxes[0].size();
    if (qpsf == tnq || idx >= n * m)
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return true;
        }
        return false;
    }

    bool flag = false;
    int r = idx / m;
    int c = idx % m;

    if (isQueenSafe(boxes, r, c))
    {
        boxes[r][c] = true;
        flag = flag || nQueenSub(boxes, idx + 1, tnq, qpsf + 1,
                                 ans + "(" + to_string(r) + ", " + to_string(c) + ")q" + to_string(qpsf + 1) + " ");
        boxes[r][c] = false;
    }

    flag = flag || nQueenSub(boxes, idx + 1, tnq, qpsf, ans);

    return flag;
}

void queenPandC()
{

    // cout << queenCombi(5, 0, 3, 1, "") << endl;
    // cout << queenPerm(5, 0, 3, 1, "") << endl;

    vector<vector<bool>> boxes(4, vector<bool>(4, 0));
    // cout << queenPerm_2D_sub(boxes, 0, 3, 1, "") << endl;
    int stop = 0;
    cout << queenCom_2D(boxes, 0, 4, 0, "", stop) << endl;
    // cout << nQueenSub(boxes, 0, 4, 0, "") << endl;
}

//sudoku.==================================

void display(vii &boxe)
{
    for (vi ar : boxe)
    {
        for (int i : ar)
            cout << i << " ";
        cout << endl;
    }
    cout << endl;
}

bool isSafeToPlaceNumber(vii &boxe, int r, int c, int val)
{

    for (int i = 0; i < 9; i++)
        if (boxe[i][c] == val)
            return false;

    for (int j = 0; j < 9; j++)
        if (boxe[r][j] == val)
            return false;

    int nr = (r / 3) * 3;
    int nc = (c / 3) * 3;

    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            if (boxe[nr + i][nc + j] == val)
                return false;

    return true;
}

int Sudoku_01(vii &boxe, int vidx)
{
    if (vidx == 81)
    {
        display(boxe);
        return 1;
    }

    int count = 0;
    int r = vidx / 9;
    int c = vidx % 9;
    if (boxe[r][c] == 0)
    {
        for (int val = 1; val <= 9; val++)
        {
            if (isSafeToPlaceNumber(boxe, r, c, val))
            {
                boxe[r][c] = val;
                count += Sudoku_01(boxe, vidx + 1);
                boxe[r][c] = 0;
            }
        }
    }
    else
    {
        count += Sudoku_01(boxe, vidx + 1);
    }

    return count;
}

int Sudoku_02(vii &boxe, int vidx, vi &row, vi &col, vii &mat)
{
    if (vidx == 81)
    {
        display(boxe);
        return 1;
    }

    int count = 0;
    int r = vidx / 9;
    int c = vidx % 9;
    if (boxe[r][c] == 0)
    {
        for (int val = 1; val <= 9; val++)
        {
            int mask = 1 << val;
            if (((row[r] & mask) == 0) &&
                ((col[c] & mask) == 0) &&
                ((mat[r / 3][c / 3] & mask) == 0))
            {
                boxe[r][c] = val;
                row[r] |= mask;
                col[c] |= mask;
                mat[r / 3][c / 3] |= mask;

                count += Sudoku_02(boxe, vidx + 1, row, col, mat);

                boxe[r][c] = 0;
                row[r] ^= mask;
                col[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;
            }
        }
    }
    else
    {
        count += Sudoku_02(boxe, vidx + 1, row, col, mat);
    }

    return count;
}

void sudoku_populate(vii &boxe, vi &row, vi &col, vii &mat)
{
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (boxe[i][j] == 0)
                continue;

            int mask = (1 << boxe[i][j]);
            row[i] |= mask;
            col[j] |= mask;
            mat[i / 3][j / 3] |= mask;
        }
    }
}

void sudoku()
{
    vector<vector<int>> boxe = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                                {0, 0, 5, 2, 0, 6, 3, 0, 0}};
    vi row(9, 0);
    vi col(9, 0);
    vii mat(3, vi(3, 0));
    sudoku_populate(boxe, row, col, mat);
    cout << Sudoku_02(boxe, 0, row, col, mat) << endl;
}

//WordBreak_Crypto.===========================================
bool isWordPresent(vector<string> &dict, string word)
{
    for (string str : dict)
    {
        if (word.compare(str) == 0)
            return true;
    }
    return false;
}

int wordBreak(vector<string> &dict, string statement, string ans)
{
    if (statement.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 1; i <= statement.length(); i++)
    {
        string str = statement.substr(0, i);
        if (isWordPresent(dict, str))
        {
            count += wordBreak(dict, statement.substr(i), ans + str + " ");
        }
    }

    return count;
}

string str1 = "send";
string str2 = "more";
string str3 = "money";

int getNumber(string str, vi &numberAgainstCHAR)
{
    int ans = 0;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        int num = numberAgainstCHAR[ch - 'a'];
        ans = ans * 10 + num;
    }

    return ans;
}

int cryptoArithmatic_01(string ques, int vidx, vi &numberAgainstCHAR, int isTaken)
{
    if (vidx == ques.length())
    {
        int num1 = getNumber(str1, numberAgainstCHAR);
        int num2 = getNumber(str2, numberAgainstCHAR);
        int num3 = getNumber(str3, numberAgainstCHAR);

        if (num1 + num2 == num3)
        {

            if ((str3.length() > str2.length() && numberAgainstCHAR[str3[0] - 'a'] == 0))
            {
                return 0;
            }

            cout << num1 << "\n"
                 << num2 << "\n+"
                 << "------\n"
                 << num3 << endl;
            cout << endl;
            return 1;
        }

        return 0;
    }

    int count = 0;
    for (int i = 0; i < 10; i++)
    {
        int mask = 1 << i;
        if ((isTaken & mask) == 0)
        { //Not Taken
            isTaken |= mask;
            numberAgainstCHAR[ques[vidx] - 'a'] = i;
            count += cryptoArithmatic_01(ques, vidx + 1, numberAgainstCHAR, isTaken);
            numberAgainstCHAR[ques[vidx] - 'a'] = 0;
            isTaken ^= mask;
        }
    }
    return count;
}

vi freqMap(string str)
{
    vi freq(26, 0);
    for (int i = 0; i < str.length(); i++)
    {
        freq[str[i] - 'a']++;
    }
    return freq;
}

void cryptoArithmatic()
{
    vi freq = freqMap(str1 + str2 + str3);
    string ques = "";
    for (int i = 0; i < freq.size(); i++)
    {
        if (freq[i] > 0)
        {
            ques += (char)(i + 'a');
        }
    }

    // for (int i = 0; i < 26; i++)
    // {
    //     cout << i << " " << freq[i] << " " << (char)(i + 'a') << endl;
    // }

    // cout<<ques;
    vi numberAgainstCHAR(26, 0);
    int isTaken = 0;
    cout << cryptoArithmatic_01(ques, 0, numberAgainstCHAR, isTaken);
}

void WordBreak_Crypto()
{
    // vector<string> dict = {"samsung", "sam", "sung", "i", "like", "ilike", "sun", "go", "samsungo"};
    // string str = "ilikelikesamsungo";
    // cout << wordBreak(dict, str, "") << endl;
    cryptoArithmatic();
}
void solve()
{
    // basic();
    // pathProblem();
    // flodfillQuestions();
    setQuestion();
    // coinChange();
    // queenPandC();
    // sudoku();
    // WordBreak_Crypto();
}

int main(int args, char **argv)
{
    solve();
    return 0;
}