#include <iostream>
#include <vector>

using namespace std;

void printSS(string str, int idx, string ans)
{
    if (idx == str.length())
    {
        cout << ans << endl;
        return;
    }

    printSS(str, idx + 1, ans + str[idx]);
    printSS(str, idx + 1, ans);
}

vector<string> subseq_03(string str, int idx)
{
    if (idx == str.length())
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> recAns = subseq_03(str, idx + 1);

    vector<string> myAns(recAns); // nahi anne ki choise dekhli.

    char ch = str[idx];
    for (string s : recAns)
    {
        myAns.push_back(ch + s);
    }

    return myAns;
}

// sr - source row
// sc - source column
// dr - destination row
// dc - destination column
vector<string> getMazePaths(int sr, int sc, int dr, int dc)
{
    if (sr == dr && sc == dc)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> myAns;
    if (sc + 1 <= dc)
    {
        vector<string> Horizontal = getMazePaths(sr, sc + 1, dr, dc);
        for (string s : Horizontal)
        {
            myAns.push_back("h" + s);
        }
    }

    if (sr + 1 <= dr)
    {
        vector<string> Vertical = getMazePaths(sr + 1, sc, dr, dc);
        for (string s : Vertical)
        {
            myAns.push_back("v" + s);
        }
    }

    return myAns;
}

// sr - source row
// sc - source column
// dr - destination row
// dc - destination column
vector<string> getMazePathsDiagonal(int sr, int sc, int dr, int dc)
{
    if (sr == dr && sc == dc)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> myAns;
    if (sc + 1 <= dc)
    {
        vector<string> Horizontal = getMazePathsDiagonal(sr, sc + 1, dr, dc);
        for (string s : Horizontal)
        {
            myAns.push_back("h" + s);
        }
    }

    if (sr + 1 <= dr)
    {
        vector<string> Vertical = getMazePathsDiagonal(sr + 1, sc, dr, dc);
        for (string s : Vertical)
        {
            myAns.push_back("v" + s);
        }
    }

    if (sr + 1 <= dr && sc + 1 <= dc)
    {
        vector<string> Diagonal = getMazePathsDiagonal(sr + 1, sc + 1, dr, dc);
        for (string s : Diagonal)
        {
            myAns.push_back("d" + s);
        }
    }

    return myAns;
}

int getMazePaths(int sr, int sc, int dr, int dc, string ans)
{
    if (sr == dr && sc == dc)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    if (sc + 1 <= dc)
        count += getMazePaths(sr, sc + 1, dr, dc, ans + 'h');
    if (sr + 1 <= dr)
        count += getMazePaths(sr + 1, sc, dr, dc, ans + 'v');

    return count;
}

vector<string> getMazePaths_multiMoves(int sr, int sc, int dr, int dc)
{
    if (sr == dr && sc == dc)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> myAns;
    for (int jump = 1; sc + jump <= dc; jump++)
    {
        vector<string> Horizontal = getMazePaths_multiMoves(sr, sc + jump, dr, dc);
        for (string s : Horizontal)
        {
            myAns.push_back("h" + to_string(jump) + s);
        }
    }

    for (int jump = 1; sr + jump <= dr; jump++)
    {
        vector<string> Vertical = getMazePaths_multiMoves(sr + jump, sc, dr, dc);
        for (string s : Vertical)
        {
            myAns.push_back("v" + to_string(jump) + s);
        }
    }

    for (int jump = 1; sr + jump <= dr && sc + jump <= dc; jump++)
    {
        vector<string> Digonal = getMazePaths_multiMoves(sr + jump, sc + jump, dr, dc);
        for (string s : Digonal)
        {
            myAns.push_back("d" + to_string(jump) + s);
        }
    }

    return myAns;
}

void getMazePaths_multiMoves(int sr, int sc, int dr, int dc, string ans)
{
    if (sr == dr && sc == dc)
    {
        cout << ans << endl;
        return;
    }

    for (int jump = 1; sc + jump <= dc; jump++)
        getMazePaths_multiMoves(sr, sc + jump, dr, dc, ans + 'h' + to_string(jump));
    for (int jump = 1; sr + jump <= dr; jump++)
        getMazePaths_multiMoves(sr + jump, sc, dr, dc, ans + 'v' + to_string(jump));
    for (int jump = 1; sr + jump <= dr && sc + jump <= dc; jump++)
        getMazePaths_multiMoves(sr + jump, sc + jump, dr, dc, ans + 'd' + to_string(jump));
}

int main()
{
    string str = "abc";
    printSS(str, 0, "");

    vector<string> ans = subseq_03(str, 0);
    for (string s : ans)
        cout << s << " ";

    return 0;
}