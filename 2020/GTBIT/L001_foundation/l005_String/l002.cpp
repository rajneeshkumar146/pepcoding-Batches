#include <iostream>
#include <vector>
using namespace std;

bool isPlaindrome(string str)
{
    int i = 0, j = str.length() - 1;
    while (i < j)
    {
        if (str[i++] != str[j--])
            return false;
    }

    return true;
}

void allSubString(string str)
{
    for (int i = 0; i < str.length(); i++)
    {
        for (int len = 1; i + len <= str.length(); len++)
        {
            cout << str.substr(i, len) << endl;
        }
    }
}

void printallPalindromicString(string str)
{
    for (int i = 0; i < str.length(); i++)
    {
        for (int len = 1; i + len <= str.length(); len++)
        {
            string s = str.substr(i, len);
            if (isPlaindrome(s))
                cout << s << endl;
        }
    }
}

string withoutX2(string str)
{
    string ans = "";
    for (int i = 0; i < str.length(); i++)
    {
        if (i < 2 && str[i] != 'x')
            ans += str[i];
        if (i >= 2)
            ans += str[i];
    }

    return ans;
}

vector<int> occuOfChar(string str, char ch)
{
    vector<int> arr;

    for (int i = 0; i < str.length(); i++)
    {
        if (str[i] == ch)
            arr.push_back(i);
    }

    return arr;
}

//Permutation
void appendCharInString(string str, char ch, vector<string> &ans)
{
    for (int i = 0; i <= str.length(); i++)
    {
        string s = str.substr(0, i) + ch + str.substr(i);
        ans.push_back(s);
    }
}

vector<string> permutation(string str)
{
    vector<string> ans;
    ans.push_back("");

    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];

        vector<string> smallAns;
        for (string s : ans)
            appendCharInString(s, ch, smallAns);

        ans = smallAns;
    }

    return ans;
}

//541
void reverseRange(string &s, int i, int j)
{
    while (i < j)
    {
        swap(s[i++], s[j--]);
    }
}

string reverseStr(string s, int k)
{
    if (k == 0 || k == 1 || s.length() <= 1)
        return s;

    int i = 0, n = s.length();

    while (i < n)
    {
        if (i + k - 1 < n)
        {
            reverseRange(s, i, i + k - 1);
            i += 2 * k;
        }
        else
        {
            reverseRange(s, i, n - 1);
            break;
        }
    }
    return s;
}

//387
int firstUniqChar(string s)
{

    vector<int> freq(26, 0);
    for (int i = 0; i < s.length(); i++)
        freq[s[i] - 'a']++;

    for (int i = 0; i < s.length(); i++)
    {
        if (freq[s[i] - 'a'] == 1)
            return i;
    }

    return -1;
}

int main()
{
    string str;
    cin >> str;
    // allSubString(str);

    vector<int> ans = occuOfChar(str, 'a');

    for (int ele : ans)
        cout << ele << " ";

    return 0;
}