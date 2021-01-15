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

int main()
{
    string str = "abc";
    printSS(str, 0, "");

    vector<string> ans = subseq_03(str, 0);
    for (string s : ans)
        cout << s << " ";

    return 0;
}