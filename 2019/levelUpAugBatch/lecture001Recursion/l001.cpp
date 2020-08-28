#include <iostream>
#include <vector>

using namespace std;

vector<string> subsequence(string &str, int idx)
{
    if (idx == str.length())
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> smallAns = subsequence(str, idx + 1);
    vector<string> myAns;

    for (string s : smallAns)
    {
        myAns.push_back(s);
        myAns.push_back(str[idx] + s);
    }

    return myAns;
}

int encoding(string &str, int idx, string ans)
{
    if (idx == str.length())
    {
        cout << ans << endl;
        return 1;
    }

    char ch = str[idx];
    if (ch == '0')
        return 0;

    int count = 0;

    count += encoding(str, idx + 1, ans + string(1, (ch - '0') + ('a' - 1)));

    if (idx < str.length() - 1)
    {
        int ch1 = (ch - '0') * 10 + (str[idx + 1] - '0');
        if (ch1 >= 10 && ch1 <= 26)
        {
            count += encoding(str, idx + 2, ans + string(1, ch1 + 'a' - 1));
        }
    }

    return count;
}

int permutation(string str, string ans)
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
        string ros = str.substr(0, i) + str.substr(i + 1);
        count += permutation(ros, ans + ch);
    }

    return count;
}

int permutationWithoutDupli(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    vector<bool> vis(26, 0);
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        if (!vis[ch - 'a'])
        {
            vis[ch-'a'] = true;
            string ros = str.substr(0, i) + str.substr(i + 1);
            count += permutationWithoutDupli(ros, ans + ch);
        }
    }

    return count;
}

void set3()
{

    // string str = "abc";
    // vector<string> ans = subsequence(str, 0);
    // for (string s : ans)
    //     cout << s << " ";
    // string str = "226";
    // cout << encoding(str, 0, "") << endl;

    string str = "aaa";
    cout<<permutationWithoutDupli(str,"")<<endl;

}

void solve()
{
    set3();
}

int main()
{
    solve();
    return 0;
}