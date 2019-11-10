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

void basic()
{
    // cout << removeHi("hihiihihhiiiiihihhiihhhhhhhhhhiiiiiiiihihihi");
    // cout<<removeHiWithoutHit("hihitihithhitiiiihihhiihhhhhhhhh")<<endl;
    // removeDupli0("aaaabbbbcccccddddefghii", "");
    // cout << removeDupli("aaaabbbbcccccddddefghii", 0) << endl;
    vector<string> ans = subseq("abcd");
    for (string s : ans)
        cout << s << " ";
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

void mazePAth()
{
    vector<string> ans = mazePathSimple(0, 0, 3, 3);

    for (string s : ans)
        cout << s << " ";
}

void solve()
{
    // basic();
    mazePAth();
}

int main()
{
    solve();
    return 0;
}