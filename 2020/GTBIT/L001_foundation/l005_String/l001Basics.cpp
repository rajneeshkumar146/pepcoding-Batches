#include <iostream>
using namespace std;

void test1()
{
    string str = "abcdsdfv";
    str += 'g';
    str.push_back('o');

    string str1 = str;
    str1 += 'p';

    str = str + 'a';
    char ch = str[3];
    cout << str << " " << str1 << endl;
}

void test2()
{
    string str = "";
    for (int i = 0; i < (int)1e6; i++)
    {
        str += to_string(i);
    }

    cout << str << endl;
}

//Question=======================================================

string compress(string str)
{
    if (str.length() == 0)
        return "";
    string ans = "";

    char prevChar = str[0];
    int i = 1, count = 0;

    while (i <= str.length())
    {
        count = 1;
        while (i < str.length() && prevChar == str[i])
        {
            count++;
            prevChar = str[i];
            i++;
        }

        ans += prevChar;
        ans += to_string(count);
        if (i == str.length())
            break;
        prevChar = str[i];
        i++;
    }

    return ans;
}

int main()
{
    // test1();
    // test2();
    // string str; cin >> str;
    cout << compress("aaaaabbbbbbxxxdsfdxxxxxvvvvfvvvvrrrrrtttttyghf");
    return 0;
}