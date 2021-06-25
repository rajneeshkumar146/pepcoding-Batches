#include <vector>
#include <iostream>

using namespace std;

vector<int> map;
vector<bool> isUsed;

int StringToInteger(string str)
{
    int res = 0;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        int mapedValue = map[ch - 'a'];
        res = res * 10 + mapedValue;
    }

    return res;
}

int crypto(string &uniqueString, int idx, string &str1, string &str2, string &str3)
{
    if (idx == uniqueString.length())
    {
        int n1 = StringToInteger(str1);
        int n2 = StringToInteger(str2);
        int n3 = StringToInteger(str3);

        if (n1 + n2 == n3)
        {
            cout << " " << n1 << "\n+" << n2 << "\n------\n"
                 << n3 << endl;
            cout << endl;
            return 1;
        }

        return 0;
    }

    int count = 0;
    char ch = uniqueString[idx];
    for (int num = 0; num <= 9; num++)
    {
        if ((str1[0] == ch || str2[0] == ch || str3[0] == ch) && num == 0)
            continue;

        if (!isUsed[num])
        {
            isUsed[num] = true;
            map[ch - 'a'] = num;

            count += crypto(uniqueString, idx + 1, str1, str2, str3);

            isUsed[num] = false;
            map[ch - 'a'] = -1;
        }
    }

    return count;
}

void crypto(string str1, string str2, string str3)
{
    string str = str1 + str2 + str3;
    vector<bool> freq(26, false);
    for (int i = 0; i < str.length(); i++)
    {
        freq[str[i] - 'a'] = true;
    }

    str = "";
    for (int i = 0; i < 26; i++)
    {
        if (freq[i])
        {
            str += (char)(i + 'a');
        }
    }

    map.resize(26, -1);
    isUsed.resize(10, 0);

    cout << (crypto(str, 0, str1, str2, str3)) << endl;
}

int main()
{
    crypto("send", "more", "money");
    return 1;
}