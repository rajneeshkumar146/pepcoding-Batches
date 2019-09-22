#include <iostream>
#include <vector>
#include <string>
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
    int isSet=0;
    for (int i = 0; i < str.length(); i++)
    {
           char ch = str[i];
           int mask=1<<(ch-'a');
        if ((isSet&mask)==0)
        {
            string one = str.substr(0, i);
            string two = str.substr(i + 1);

            count += permuation(one + two, ans + ch);
            isSet|=mask;
        }
    }

    return count;
}

void basic()
{
    // cout << subsequence("abc", "") << endl;
    cout << permuation("aaa", "") << endl;
}

void solve()
{
    basic();
}

int main(int args, char **argv)
{
    solve();
    return 0;
}