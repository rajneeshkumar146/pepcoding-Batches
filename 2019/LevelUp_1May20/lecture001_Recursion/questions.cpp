#include <iostream>
#include <vector>
using namespace std;

//leetcode: 91.=========================================================

int numDecodings(string &s, int idx)
{
    if (idx == s.length())
    {
        return 1;
    }

    char ch = s[idx];
    int count = 0;
    if (ch != '0')
    {
        // char ch_ = (char)((ch - '1') + 'a');
        count += numDecodings(s, idx + 1);
    }

    if (idx + 1 < s.length())
    {
        int num = (ch - '0') * 10 + (s[idx + 1] - '0');
        if (num >= 10 && num <= 26)
        {
            // char ch_ = (char)('a' + num - 1);
            count += numDecodings(s, idx + 2);
        }
    }

    return count;
}

int numDecodings(string s)
{
    if (s.length() == 0)
        return 0;

    return numDecodings(s, 0);
}
