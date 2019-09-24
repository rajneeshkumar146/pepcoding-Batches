#include <iostream>
#include <vector>
#include <string>

using namespace std;

vector<string> MazePathSimple(int sr, int sc, int er, int ec)
{

    if (sr == er && sc == ec)
    {
        vector<string> baseAns;
        baseAns.push_back("");
        return baseAns;
    }

    // if(sr>er || sc>ec){
    //     ArrayList<String> baseAns = new ArrayList<>();
    //     return baseAns;

    // }

    vector<string> myAns;
    if (sc + 1 <= ec)
    {
        vector<string> HorizontalAns = MazePathSimple(sr, sc + 1, er, ec);
        for (string smallAns : HorizontalAns)
        {
            string realAns = "H" + smallAns;
            myAns.push_back(realAns);
        }
    }

    if (sr + 1 <= er)
    {
        vector<string> verticalAns = MazePathSimple(sr + 1, sc, er, ec);
        for (string smallAns : verticalAns)
        {
            string realAns = "V" + smallAns;
            myAns.push_back(realAns);
        }
    }

    return myAns;
}

vector<string> subsequence(string str)
{
    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> myAns;

    char ch = str[0];
    string nextState = str.substr(1);
    vector<string> smallAns = subsequence(nextState); // chance to every character.
    for (string s : smallAns)
    {
        myAns.push_back(s);      // not coming situation.
        myAns.push_back(ch + s); // coming situation.
    }

    return myAns;
}

vector<string> stairs(int si, int ei)
{
    if (si == ei)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }
    vector<string> myAns;
    for (int steps = 1; steps <= 6 && si + steps <= ei; steps++)
    {
        vector<string> recAns = stairs(si + steps, ei);
        for (string s : recAns)
        {
            myAns.push_back(to_string(steps) + s);
        }
    }

    return myAns;
}

void solve3()
{
    vector<string> ans = subsequence("123");
    for (string str : ans)
    {
        cout << str << " ";
    }
}

int main(int args, char **argv)
{
    solve3();
    return 0;
}