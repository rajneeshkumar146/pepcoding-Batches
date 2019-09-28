#include <iostream>
#include <string>
#include <vector>

using namespace std;

void subsequence(string que, string ans)
{
    if (que.length() == 0)
    {
        cout << ans << " ";
        return;
    }

    char ch = que[0];
    que = que.substr(1);

    subsequence(que, ans + ch); //coming situation
    subsequence(que, ans);      //not coming situation
}

string getCode(int digit)
{
    string arr[] = {"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "uvw", "xyz", "*+#"};
    return arr[digit];
}
void keyPad(int num, string ans)
{
    if (num == 0)
    {
        cout << ans << " ";
        return;
    }
    int digit = num % 10;
    num /= 10;
    string s = getCode(digit);
    for (int i = 0; i < s.length(); i++)
    {
        keyPad(num, s[i] + ans);
    }
}

int mazePathSimple(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    if (sr + 1 <= er)
        count += mazePathSimple(sr + 1, sc, er, ec, ans + "V");

    if (sc + 1 <= ec)
        count += mazePathSimple(sr, sc + 1, er, ec, ans + "H");

    return count;
}

int mazePathDSimple(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    if (sr + 1 <= er)
        count += mazePathDSimple(sr + 1, sc, er, ec, ans + "V");

    if (sc + 1 <= ec)
        count += mazePathDSimple(sr, sc + 1, er, ec, ans + "H");

    if (sc + 1 <= ec && sr + 1 <= er)
        count += mazePathDSimple(sr + 1, sc + 1, er, ec, ans + "D");

    return count;
}

int mazePathMulti(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 1; sr + i <= er; i++)
        count += mazePathMulti(sr + i, sc, er, ec, ans + "V" + to_string(i));

    for (int i = 1; sc + i <= ec; i++)
        count += mazePathMulti(sr, sc + i, er, ec, ans + "H" + to_string(i));

    for (int i = 1; sc + i <= ec && sr + i <= er; i++)
        count += mazePathMulti(sr + i, sc + i, er, ec, ans + "D" + to_string(i));

    return count;
}

int boardPath(int sr, int dsti, string ans)
{
    if (sr == dsti)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int steps = 1; steps <= 6 && steps + sr <= dsti; steps++)
        count += boardPath(sr + steps, dsti, ans + to_string(steps));
    return count;
}

int encoding(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;

    char ch = str[0];
    string oneLength = str.substr(1);
    if (ch == '0')
    {
        count += encoding(oneLength, ans);
    }
    else
    {
        char c = (char)(ch - '1' + 'a');
        count += encoding(oneLength, ans + c);

        if (str.length() > 1)
        {
            char ch2 = str[1];
            string twoLength = str.substr(2);
            int num = (ch - '0') * 10 + (ch2 - '0');
            if (num <= 26)
            {
                count += encoding(twoLength, ans + (char)(num + 'a'));
            }
        }
    }
    return count;
}

int idx = 0;
// ,vector<int> countOfSixArray,int countOfSix
int ludoAllPAth(int sr, int dsti, string ans, bool isGameOpen, vector<int> &chaal)
{
    if (sr == dsti || idx >= chaal.size())
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;

    if (!isGameOpen && idx < chaal.size() && (chaal[idx] == 1 || chaal[idx] == 6)) //game going to open.
    {
        isGameOpen = true;
        idx++;
        count += ludoAllPAth(sr, dsti, ans, isGameOpen, chaal);
    }
    else if (!isGameOpen && idx < chaal.size()) // //not a valid move ,because move is not a intial stating chaal.
    {
        idx++;
        count += ludoAllPAth(sr, dsti, ans, isGameOpen, chaal);
    }
    else
    {
        while (isGameOpen && idx < chaal.size() && chaal[idx] + sr <= dsti)
        {

            ans += to_string(chaal[idx]) + " ";
            idx++;
            count += ludoAllPAth(sr + chaal[idx], dsti, ans, isGameOpen, chaal);
        }
    }
    return count;
}

int targetSum(vector<int> &arr, int vidx, int target, string ans)
{
    if (vidx == arr.size() || target == 0)
    {
        int rval = 0;
        if (target == 0)
        {
            cout << ans << endl;
            rval = 1;
        }
        return rval;
    }

    int count = 0;
    count += targetSum(arr, vidx + 1, target, ans);                                          // not contributing
    count += targetSum(arr, vidx + 1, target - arr[vidx], ans + to_string(arr[vidx]) + " "); // contributing

    return count;
}

int targetSum_02(vector<int> &arr, int vidx, int target, vector<int> &ans)
{
    if (vidx == arr.size() || target == 0)
    {
        int rval = 0;
        if (target == 0)
        {
            for (int i : ans)
                cout << i << " ";
            rval = 1;
        }
        return rval;
    }
    int count = 0;
    count += targetSum_02(arr, vidx + 1, target, ans); // contributing                             // not contributing
    ans.push_back(arr[vidx]);
    count += targetSum_02(arr, vidx + 1, target - arr[vidx], ans); // contributing
    ans.pop_back();

    return count;
}

int equiSet(vector<int> &arr, int vidx, int set1Sum, string set1, int set2Sum, string set2)
{
    if (vidx == arr.size())
    {
        if (set1Sum == set2Sum)
        {
            cout << set1 << " == " << set2 << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;

    count += equiSet(arr, vidx + 1, set1Sum + arr[vidx], set1 + to_string(arr[vidx]) + " ", set2Sum, set2);
    count += equiSet(arr, vidx + 1, set1Sum, set1, set2Sum + arr[vidx], set2 + to_string(arr[vidx]) + " ");
    return count;
}

int equiSetWithThreeOption(vector<int> &arr, int vidx, int set1Sum, string set1, int set2Sum, string set2)
{
    if (vidx == arr.size())
    {
        if (set1Sum == set2Sum)
        {
            cout << set1 << " == " << set2 << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    count += equiSetWithThreeOption(arr, vidx + 1, set1Sum, set1, set2Sum, set2);
    count += equiSetWithThreeOption(arr, vidx + 1, set1Sum + arr[vidx], set1 + to_string(arr[vidx]) + " ", set2Sum, set2);
    count += equiSetWithThreeOption(arr, vidx + 1, set1Sum, set1, set2Sum + arr[vidx], set2 + to_string(arr[vidx]) + " ");
    return count;
}

void sumTypeQuestion()
{
    vector<int> arr = {10, 20, 30, 40, 50, 60, 70};
    // cout << targetSum(arr, 0, 100, "");

    // vector<int> ans;
    // cout << targetSum_02(arr, 0, 100, )

    // cout << equiSet(arr, 0, 0, "", 0, "") << endl;
    cout << equiSetWithThreeOption(arr, 0, 0, "", 0, "") << endl;
}

//FloodFill.=====================================================================

bool isvalid(int sr, int sc, vector<vector<int>> &isvisited)
{
    if (sr >= isvisited.size() || sc >= isvisited[0].size() || sr < 0 || sc < 0 || isvisited[sr][sc] == 2 || isvisited[sr][sc] == 10)
    {
        return false;
    }
    return true;
}

int floodfill(int sr, int sc, int dr, int dc, vector<vector<int>> &isvisited, string ans)
{

    if (sr == dr && sc == dc)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    isvisited[sr][sc] = 2;

    //left.
    if (isvalid(sr, sc - 1, isvisited))
        count += floodfill(sr, sc - 1, dr, dc, isvisited, ans + "L");

    //right.
    if (isvalid(sr, sc + 1, isvisited))
        count += floodfill(sr, sc + 1, dr, dc, isvisited, ans + "R");
    // Down.
    if (isvalid(sr + 1, sc, isvisited))
        count += floodfill(sr + 1, sc, dr, dc, isvisited, ans + "D");

    //upward
    if (isvalid(sr - 1, sc, isvisited))
        count += floodfill(sr - 1, sc, dr, dc, isvisited, ans + "U");

    isvisited[sr][sc] = 0;
    return count;
}

int floodfillEightDir(int sr, int sc, int dr, int dc, vector<vector<int>> &isvisited, string ans)
{

    if (sr == dr && sc == dc)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    isvisited[sr][sc] = 2;

    //left.
    if (isvalid(sr, sc - 1, isvisited))
        count += floodfillEightDir(sr, sc - 1, dr, dc, isvisited, ans + "L");

    //right.
    if (isvalid(sr, sc + 1, isvisited))
        count += floodfillEightDir(sr, sc + 1, dr, dc, isvisited, ans + "R");
    // Down.
    if (isvalid(sr + 1, sc, isvisited))
        count += floodfillEightDir(sr + 1, sc, dr, dc, isvisited, ans + "D");

    //upward
    if (isvalid(sr - 1, sc, isvisited))
        count += floodfillEightDir(sr - 1, sc, dr, dc, isvisited, ans + "U");

    //left-up
    if (isvalid(sr - 1, sc - 1, isvisited))
        count += floodfillEightDir(sr - 1, sc - 1, dr, dc, isvisited, ans + "1");
    //left-down
    if (isvalid(sr + 1, sc - 1, isvisited))
        count += floodfillEightDir(sr + 1, sc - 1, dr, dc, isvisited, ans + "2");

    //right-up
    if (isvalid(sr - 1, sc + 1, isvisited))
        count += floodfillEightDir(sr - 1, sc + 1, dr, dc, isvisited, ans + "3");

    //right-down
    if (isvalid(sr + 1, sc + 1, isvisited))
        count += floodfillEightDir(sr + 1, sc + 1, dr, dc, isvisited, ans + "4");

    isvisited[sr][sc] = 0;
    return count;
}

int flodfillEightDireUsingDirLoop(int sr, int sc, int dr, int dc, vector<vector<int>> &isvisited, string ans)
{

    if (sr == dr && sc == dc)
    {
        cout << ans << endl;
        return 1;
    }
    char dirVal[8] = {'U', 'R', 'D', 'L', '4', '3', '2', '1'};
    int dir[8][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

    isvisited[sr][sc] = 2;
    int count = 0;
    for (int dirItr = 0; dirItr < 8; dirItr++)
    {
        int r = sr + dir[dirItr][0];
        int c = sc + dir[dirItr][1];

        if (isvalid(r, c, isvisited))
            count += flodfillEightDireUsingDirLoop(r, c, dr, dc, isvisited, ans + dirVal[dirItr] + " ");
    }

    isvisited[sr][sc] = 0;
    return count;
}

int flodfillEightDireUsingDirLoop_MultiMove(int sr, int sc, int dr, int dc, vector<vector<int>> &isvisited, string ans)
{

    if (sr == dr && sc == dc)
    {
        cout << ans << endl;
        return 1;
    }
    char dirVal[8] = {'U', 'R', 'D', 'L', '4', '3', '2', '1'};
    int dir[8][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

    isvisited[sr][sc] = 2;
    int count = 0;
    for (int rad = 1; rad < isvisited.size(); rad++)
    {
        for (int dirItr = 0; dirItr < 8; dirItr++)
        {
            int r = sr + rad * dir[dirItr][0];
            int c = sc + rad * dir[dirItr][1];

            if (isvalid(r, c, isvisited))
                count += flodfillEightDireUsingDirLoop_MultiMove(r, c, dr, dc, isvisited, ans + dirVal[dirItr] + " ");
        }
    }

    isvisited[sr][sc] = 0;
    return count;
}

void floodFillVariation()
{
    // vector<vector<bool>> isvisited(3, vector<bool>(3, false));

    vector<vector<int>> isvisited = {
        {0, 0, 0},
        {0, 10, 0},
        {10, 0, 0},
    };

    // cout << floodfill(0, 0, 2, 2, isvisited, "") << endl;
    // cout << floodfillEightDir(0, 0, 2, 2, isvisited, "") << endl;
    // cout << flodfillEightDireUsingDirLoop(0, 0, 2, 2, isvisited, "") << endl;
}

//coin change.==============================================

int coinChangePermutation_01(vector<int> &arr, int totalCoin, string ans)
{
    if (totalCoin == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        if (totalCoin - arr[i] >= 0)
        {
            count += coinChangePermutation_01(arr, totalCoin - arr[i], ans + to_string(arr[i]) + " ");
        }
    }
    return count;
}

int coinChangePermutation_02(vector<int> &arr, int totalCoin, vector<bool> &isDone, string ans)
{
    if (totalCoin == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        if (totalCoin - arr[i] >= 0 && !isDone[i])
        {
            isDone[i] = true;
            count += coinChangePermutation_02(arr, totalCoin - arr[i], isDone, ans + to_string(arr[i]) + " ");
            isDone[i] = false;
        }
    }
    return count;
}

//target on level
int coinChangePermutation_03(vector<int> &arr, int idx, int totalCoin, string ans)
{
    if (idx == arr.size() || totalCoin <= 0)
    {
        if (totalCoin == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (totalCoin - arr[idx] >= 0)
        count += coinChangePermutation_03(arr, 0, totalCoin - arr[idx], ans + to_string(arr[idx]) + " ");
    count += coinChangePermutation_03(arr, idx + 1, totalCoin, ans);

    return count;
}

int coinChangecombination_01(vector<int> &arr, int idx, int totalCoin, string ans) // coin combination with repetition.
{
    if (idx == arr.size() || totalCoin <= 0)
    {
        if (totalCoin == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    for (int i = idx; i < arr.size(); i++)
    {
        if (totalCoin - arr[i] >= 0)
        {
            count += coinChangecombination_01(arr, i, totalCoin - arr[i], ans + to_string(arr[i]) + " ");
        }
    }
    return count;
}

int coinChangecombination_02(vector<int> &arr, int idx, int totalCoin, string ans)
{
    if (idx == arr.size() || totalCoin <= 0)
    {
        if (totalCoin == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    for (int i = idx; i < arr.size(); i++)
    {
        if (totalCoin - arr[i] >= 0)
        {
            count += coinChangecombination_02(arr, i + 1, totalCoin - arr[i], ans + to_string(arr[i]) + " ");
        }
    }
    return count;
}

//target on level.
int coinChangecombination_03(vector<int> &arr, int idx, int totalCoin, string ans)
{
    if (idx == arr.size() || totalCoin <= 0)
    {
        if (totalCoin == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;

    if (totalCoin - arr[idx] >= 0)
        count += coinChangecombination_03(arr, idx, totalCoin - arr[idx], ans + to_string(arr[idx]) + " ");
    count += coinChangecombination_03(arr, idx + 1, totalCoin, ans);

    return count;
}

int coinChangecombination_04(vector<int> &arr, int idx, int totalCoin, string ans)
{
    if (idx == arr.size() || totalCoin <= 0)
    {
        if (totalCoin == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;

    if (totalCoin - arr[idx] >= 0)
        count += coinChangecombination_04(arr, idx + 1, totalCoin - arr[idx], ans + to_string(arr[idx]) + " ");
    count += coinChangecombination_04(arr, idx + 1, totalCoin, ans);

    return count;
}

void coinChange()
{
    vector<int> coins = {2, 3, 5};
    // vector<bool> isDone(coins.size(), false);
    // cout << coinChangePermutation_01(coins, 10, "") << endl;
    // cout << coinChangePermutation_02(coins, 10, isDone, "") << endl;
    // cout << coinChangePermutation_03(coins, 0, 20, "") << endl;

    // cout << coinChangecombination_01(coins, 0, 10, "") << endl;
    // cout << coinChangecombination_02(coins, 0, 10, "") << endl;
    // cout << coinChangecombination_03(coins, 0, 10, "") << endl;
    // cout << coinChangecombination_04(coins, 0, 10, "") << endl;
}

// Queen_permutation-and-combination.=========================================

//queens have choices.
int NqueenPermuatation_01(vector<bool> &boxes, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < boxes.size(); i++)
    {
        if (!boxes[i])
        {
            boxes[i] = true;
            count += NqueenPermuatation_01(boxes, qpsf + 1, tnq, ans + "q" + to_string(qpsf + 1) + "b" + to_string(i + 1));
            boxes[i] = false;
        }
    }

    return count;
}

//Boxes have choices.
int NqueenPermuatation_02(vector<bool> &boxes, int idx, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq || idx == boxes.size())
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (!boxes[idx])
    {
        boxes[idx] = true;
        count += NqueenPermuatation_02(boxes, 0, qpsf + 1, tnq, ans + "q" + to_string(qpsf + 1) + "b" + to_string(idx + 1));
        boxes[idx] = false;
    }

    count += NqueenPermuatation_02(boxes, idx + 1, qpsf, tnq, ans);
    return count;
}

//queens have choices.
int NqueenCombination_01(vector<bool> &boxes, int idx, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < boxes.size(); i++)
    {
        if (!boxes[i])
        {
            boxes[i] = true;
            count += NqueenCombination_01(boxes, i + 1, qpsf + 1, tnq, ans + "q" + to_string(qpsf + 1) + "b" + to_string(i + 1));
            boxes[i] = false;
        }
    }

    return count;
}

//Boxes have choices.
int NqueenCombination_02(vector<bool> &boxes, int idx, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq || idx == boxes.size())
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (!boxes[idx])
    {
        boxes[idx] = true;
        count += NqueenCombination_02(boxes, idx + 1, qpsf + 1, tnq, ans + "q" + to_string(qpsf + 1) + "b" + to_string(idx + 1));
        boxes[idx] = false;
    }
    count += NqueenCombination_02(boxes, idx + 1, qpsf, tnq, ans);

    return count;
}

//Boxes have choices.
int NqueenCombination_03(vector<bool> &boxes, int idx, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq || idx == boxes.size())
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (!boxes[idx])
    {
        boxes[idx] = true;
        count += NqueenCombination_03(boxes, 0, qpsf + 1, tnq, ans + "q" + to_string(qpsf + 1) + "b" + to_string(idx + 1));
        boxes[idx] = false;
        count += NqueenCombination_03(boxes, idx + 1, qpsf, tnq, ans);
    }
    return count;
}

void queenPandC()
{
    vector<bool> boxes(6, false);
    // cout << NqueenPermuatation_01(boxes, 0, 3, "") << endl;
    cout << NqueenPermuatation_02(boxes, 0, 0, 4, "") << endl;

    // cout << NqueenCombination_01(boxes, 0, 0, 3, "") << endl;
    // cout << NqueenCombination_02(boxes, 0, 0, 3, "") << endl;
    // cout << NqueenCombination_03(boxes, 0, 0, 3, "") << endl;
}

//2D_Nqueen.===============================================================================

int Nqueen2DPermutation(vector<vector<bool>> &boxes, int qpsf, int tnq, string ans)
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
        if (!boxes[r][c])
        {
            boxes[r][c] = true;
            count += Nqueen2DPermutation(boxes, qpsf + 1, tnq, ans + "q" + to_string(qpsf + 1) + "b" + to_string(i + 1) + " ");
            boxes[r][c] = false;
        }
    }

    return count;
}

int Nqueen2DCombination(vector<vector<bool>> &boxes, int vidx, int qpsf, int tnq, string ans)
{

    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = vidx; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        if (!boxes[r][c])
        {
            boxes[r][c] = true;
            count += Nqueen2DCombination(boxes, i + 1, qpsf + 1, tnq, ans + "q" + to_string(qpsf + 1) + "b" + to_string(i + 1) + " ");
            boxes[r][c] = false;
        }
    }

    return count;
}

bool isvalidLocation(vector<vector<bool>> &boxes, int sr, int sc)
{
    if (sr >= boxes.size() || sc >= boxes[0].size() || sr < 0 || sc < 0)
    {
        return false;
    }
    return true;
}

bool isValidToPlaceQueen(vector<vector<bool>> &boxes, int sr, int sc)
{
    int dir[8][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

    for (int rad = 1; rad < boxes.size(); rad++)
    {
        for (int dirItr = 0; dirItr < 8; dirItr++)
        {
            int r = sr + rad * dir[dirItr][0];
            int c = sc + rad * dir[dirItr][1];
            if (isvalidLocation(boxes, r, c) && boxes[r][c])
            {
                return false;
            }
        }
    }

    return true;
}

int Nqueen_01(vector<vector<bool>> &boxes, int vidx, int qpsf, int tnq, string ans)
{

    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = vidx; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        if (!boxes[r][c] && isValidToPlaceQueen(boxes, r, c))
        {
            boxes[r][c] = true;
            count += Nqueen_01(boxes, i + 1, qpsf + 1, tnq, ans + "q" + to_string(qpsf + 1) + "b" + to_string(i + 1) + " ");
            boxes[r][c] = false;
        }
    }

    return count;
}

int Nqueen_02(vector<vector<bool>> &boxes, int idx, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq || idx == boxes.size() * boxes[0].size())
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    int r = idx / boxes[0].size();
    int c = idx % boxes[0].size();

    if (!boxes[r][c] && isValidToPlaceQueen(boxes, r, c))
    {
        boxes[r][c] = true;
        count += Nqueen_02(boxes, idx + 1, qpsf + 1, tnq, ans + "q" + to_string(qpsf + 1) + "b" + to_string(idx + 1));
        boxes[r][c] = false;
    }
    count += Nqueen_02(boxes, idx + 1, qpsf, tnq, ans);
    return count;
}

void Nqueen()
{
    vector<vector<bool>> boxes(4, vector<bool>(4, false));
    // cout << Nqueen2DPermutation(boxes, 0, 4, "") << endl;
    // cout << Nqueen2DCombination(boxes, 0, 0, 4, "") << endl;

    // cout << Nqueen_01(boxes, 0, 0, 4, "") << endl;
}

//sudoku.============================================================

bool isValidToPlaceNumber(vector<vector<int>> &boxe, int num, int r, int c)
{
    //col
    for (int i = 0; i < boxe[0].size(); i++)
    {
        if (boxe[r][i] == num)
            return false;
    }

    //row
    for (int i = 0; i < boxe.size(); i++)
    {
        if (boxe[i][c] == num)
            return false;
    }

    int sr = (r / 3) * 3;
    int sc = (c / 3) * 3;
    // cout<<sr<<" "<<sc<<endl;
    for (int i = sr; i < sr + 3; i++)
    {
        for (int j = sc; j < sc + 3; j++)
        {
            if (boxe[i][j] == num)
                return false;
        }
    }

    return true; //we can place number.
}

void display(vector<vector<int>> &boxe)
{
    for (vector<int> ar : boxe)
    {
        for (int ele : ar)
        {
            cout << ele << " ";
        }
        cout << endl;
    }
}

int sudoku_01(vector<vector<int>> &boxe, int bno, int countOfUnfiledLocation)
{
    if (countOfUnfiledLocation == 0)
    // if (bno == 81)
    {
        display(boxe);
        return 1;
    }

    int count = 0;
    int r = bno / boxe[0].size();
    int c = bno % boxe[0].size();

    if (boxe[r][c] != 0)
        count += sudoku_01(boxe, bno + 1, countOfUnfiledLocation);
    else
    {
        for (int num = 1; num <= 9; num++)
        {
            if (isValidToPlaceNumber(boxe, num, r, c))
            {
                boxe[r][c] = num;
                count += sudoku_01(boxe, bno + 1, countOfUnfiledLocation - 1);
                boxe[r][c] = 0;
            }
        }
    }

    return count;
}

int sudoku_02(vector<vector<int>> &boxe,vector<pair<int, int>> zeros,int vidx, int countOfUnfiledLocation)
{
    if (countOfUnfiledLocation == 0)
    // if (bno == 81)
    {
        display(boxe);
        return 1;
    }

    int count = 0;
    for()
    

    return count;
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
    int countofZeros = 0;
    vector<pair<int, int>> zeros;
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (boxe[i][j] == 0)
            {
                countofZeros++;
                zeros.push_back({i, j});
            }
        }
    }

    // cout << sudoku_01(boxe, 0, countofZeros);
}

void solve()
{
    //     subsequence("abc", "");
    //     cout << endl;
    //     subsequence("123", "");

    // keyPad(123, "");
    // cout<<mazePathSimple(0,0,2,2,"")<<endl;
    // cout<<mazePathDSimple(0,0,2,2,"")<<endl;
    // cout << mazePathMulti(0, 0, 2, 2, "") << endl;

    // vector<int> chaal = {2, 3, 6, 2, 3, 1, 1, 1, 1, 1};
    // cout << ludoAllPAth(0, 10, "", false, chaal);

    // cout<<encoding("110046","")<<endl;

    // sumTypeQuestion();
    // floodFillVariation();
    // coinChange();
    // queenPandC();
    // Nqueen();
    sudoku();
}

int main(int args, char **argv)
{
    solve();
    return 0;
}
