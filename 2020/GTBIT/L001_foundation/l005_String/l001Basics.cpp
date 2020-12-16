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

int main()
{
    // test1();
    test2();
    return 0;
}