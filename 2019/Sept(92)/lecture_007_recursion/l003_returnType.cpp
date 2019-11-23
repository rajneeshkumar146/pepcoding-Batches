#include <iostream>
#include <vector>
#include <string>

using namespace std;

//======================================

string removeHi(string str)
{
    if (str.length() == 0)
        return "";

    char ch = str[0];
    if (str.length() > 1 && str[0] == 'h' && str[1] == 'i')
        return removeHi(str.substr(2));
    else
        return ch + removeHi(str.substr(1));
}

string removeHiWithoutHit(string str)
{
    if (str.length() == 0)
        return "";

    char ch = str[0];
    if (str.length() > 1 && str[0] == 'h' && str[1] == 'i')
        if (str.length() > 2 && str[2] == 't')
            return "hit" + removeHiWithoutHit(str.substr(3));
        else
            return removeHiWithoutHit(str.substr(2));
    else
        return ch + removeHiWithoutHit(str.substr(1));
}

void removeDupli0(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return;
    }

    if (ans[ans.length() - 1] != str[0])
        ans += str[0];

    removeDupli0(str.substr(1), ans);
}

string removeDupli(string str, int vidx)
{
    if (str.length() == vidx)
    {
        return string(1, str[vidx - 1]);
    }

    if (vidx != 0 && str[vidx - 1] != str[vidx])
        return str[vidx - 1] + removeDupli(str, vidx + 1);
    else
        return removeDupli(str, vidx + 1);
}

vector<string> subseq(string str)
{
    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    char ch = str[0];
    vector<string> smallAns = subseq(str.substr(1));
    vector<string> myAns;

    for (string s : smallAns)
        myAns.push_back(s);

    for (string s : smallAns)
        myAns.push_back(ch + s);

    return myAns;
}

vector<string> permutation(string str)
{
    if (str.length() == 1)
    {
        vector<string> base;
        base.push_back(str);
        return base;
    }

    char ch = str[0];
    vector<string> recAns = permutation(str.substr(1));

    vector<string> myAns;
    for (string s : recAns)
    {
        for (int i = 0; i <= s.length(); i++)
        {
            string st = s.substr(0, i) + ch + s.substr(i);
            myAns.push_back(st);
        }
    }

    return myAns;
}

vector<string> words = {"_", "+-/", "abc", "def", "ghi",
                        "jkl", "mno",
                        "pqrs", "tuv", "wxyz", "&()%", "#@$"};

vector<string> keyPad(string str)
{

    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    char ch = str[0];
    string word1 = words[ch - '0'];
    vector<string> myAns;

    vector<string> firstRes = keyPad(str.substr(1));
    for (string s : firstRes)
    {
        for (int i = 0; i < word1.length(); i++)
        {
            myAns.push_back(word1[i] + s);
        }
    }

    if (str.length() > 1 && ch != '0')
    {
        int num = (ch - '0') * 10 + (str[1] - '0');
        if (num < 12)
        {
            string word2 = words[num];

            vector<string> secondRes = keyPad(str.substr(2));
            for (string s : secondRes)
            {
                for (int i = 0; i < word2.length(); i++)
                {
                    myAns.push_back(word2[i] + s);
                }
            }
        }
    }
    return myAns;
}

vector<string> encoding(string str)
{
    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    char ch = str[0];
    vector<string> myAns;
    if (ch == '0')
    {
        return encoding(str.substr(1));
    }

    char ch_ = (char)(ch - '1' + 'a');
    vector<string> firstRes = encoding(str.substr(1));
    for (string s : firstRes)
    {
        myAns.push_back(ch_ + s);
    }

    if (str.length() > 1)
    {
        int num = (ch - '0') * 10 + (str[1] - '0');
        if (num <= 26)
        {
            ch_ = (char)(num + 'a' - 1);
            vector<string> secondRes = encoding(str.substr(2));
            for (string s : secondRes)
            {
                myAns.push_back(ch_ + s);
            }
        }
    }

    return myAns;
}

void basic()
{
    // cout << removeHi("hihiihihhiiiiihihhiihhhhhhhhhhiiiiiiiihihihi");
    // cout<<removeHiWithoutHit("hihitihithhitiiiihihhiihhhhhhhhh")<<endl;
    // removeDupli0("aaaabbbbcccccddddefghii", "");
    // cout << removeDupli("aaaabbbbcccccddddefghii", 0) << endl;

    // vector<string> ans = permutation("abcd");
    // vector<string> ans = keyPad("108");
    vector<string> ans = encoding("1023");
    for (string s : ans)
        cout << s << endl;
}

//=================================================

vector<string> mazePathSimple(int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> myAns;
    if (sc + 1 <= ec)
    {
        vector<string> horizontal = mazePathSimple(sr, sc + 1, er, ec);
        for (string s : horizontal)
            myAns.push_back("h" + s);
    }

    if (sr + 1 <= er)
    {
        vector<string> vertical = mazePathSimple(sr + 1, sc, er, ec);
        for (string s : vertical)
            myAns.push_back("v" + s);
    }

    return myAns;
}

vector<string> mazePath_D(int sr, int sc, int er, int ec)
{

    if (sr == er && sc == ec)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> myAns;

    for (int i = 1; sc + i <= ec; i++)
    {
        vector<string> hori = mazePath_D(sr, sc + i, er, ec);
        for (string s : hori)
        {
            myAns.push_back("H" + to_string(i) + s);
        }
    }

    for (int i = 1; sr + i <= er; i++)
    {
        vector<string> Vert = mazePath_D(sr + i, sc, er, ec);
        for (string s : Vert)
        {
            myAns.push_back("V" + to_string(i) + s);
        }
    }

    for (int i = 1; sr + i <= er && sc + i <= ec; i++)
    {
        vector<string> Diag = mazePath_D(sr + i, sc + i, er, ec);
        for (string s : Diag)
        {
            myAns.push_back("D" + to_string(i) + s);
        }
    }

    return myAns;
}

int mazePath_D_H(int sr, int sc, int er, int ec)
{

    if (sr == er && sc == ec)
    {
        return 0;
    }

    int hori = 0, Verti = 0, diag = 0;
    if (sc + 1 <= ec)
    {
        hori = mazePath_D_H(sr, sc + 1, er, ec);
    }

    if (sr + 1 <= er)
    {
        Verti = mazePath_D_H(sr + 1, sc, er, ec);
    }

    if (sr + 1 <= er && sc + 1 <= ec)
    {
        diag = mazePath_D_H(sr + 1, sc + 1, er, ec);
    }

    return max(hori, max(diag, Verti)) + 1;
}

void mazePAth()
{
    // vector<string> ans = mazePathSimple(0, 0, 3, 3);
    vector<string> ans = mazePath_D(0, 0, 3, 3);

    for (string s : ans)
        cout << s << endl;
}

//========================================

bool isSafe(int x, int y, vector<vector<bool>> &board)
{
    if (x < 0 || y < 0 || x >= board.size() || y >= board[0].size() || board[x][y])
        return false;
    return true;
}
vector<vector<int>> direction = {{0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}};
vector<string> dir = {"R", "1", "U", "2", "L", "3", "D", "4"};

int floodFill(int sr, int sc, int er, int ec, vector<vector<bool>> &board, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    board[sr][sc] = true;

    for (int d = 0; d < 8; d++)
    {
        int y = sr + direction[d][0];
        int x = sc + direction[d][1];

        if (isSafe(x, y, board))
        {
            count += floodFill(x, y, er, ec, board, ans + dir[d]);
        }
    }
    board[sr][sc] = false;
    return count;
}

void floodFill()
{
    vector<vector<bool>> board(5, vector<bool>(5, false));
    board[0][0] = true;
    board[1][2] = true;
    board[2][1] = true;
    board[2][2] = true;
    board[2][4] = true;
    board[3][1] = true;
    board[4][2] = true;
    cout << floodFill(0, 1, 4, 3, board, "");
}

bool isPlaindrome(string str, int i, int j)
{
    while (i < j)
    {
        if (str[i++] != str[j--])
            return false;
    }

    return true;
}

int minimumPalindromicCuts(string str, int i, int j)
{
    if (i == j)
        return 0;
    if (isPlaindrome(str, i, j))
        return 0;

    int min_=1000000;
    for(int cut=i;cut<j;cut++){
        int leftMinCuts=minimumPalindromicCuts(str,i,cut);
        int rightMinCuts=minimumPalindromicCuts(str,cut+1,j);
        min_=min(leftMinCuts+rightMinCuts+1,min_);
    }
    return min_;
}

void solve()
{
    // basic();
    // mazePAth();
    // floodFill();
    string str="feabccbad";
    cout<<minimumPalindromicCuts(str,0,str.length()-1)<<endl;
}

int main()
{
    solve();
    return 0;
}