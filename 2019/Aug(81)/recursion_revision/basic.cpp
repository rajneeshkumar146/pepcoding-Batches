#include <iostream>
#include <vector>
#include <string>

using namespace std;

void increasing(int a, int b)
{
    if (a == b)
        return;

    increasing(a, b - 1);
    cout << b << endl;
}

void decreasing(int a, int b)
{

    if (a == b)
        return;

    cout << b << endl;
    decreasing(a, b - 1);
}

void increDecre(int a, int b)
{
    if (a == b)
    {
        cout << a << " ";
        return;
    }
    cout << a << " ";
    increDecre(a + 1, b);
    cout << a << " ";
}

void oddEven(int a, int b)
{
    if (a == b)
    {
        cout << a << " ";
        return;
    }
    if (a % 2 != 0)
        cout << a << " ";
    oddEven(a + 1, b);
    if (a % 2 == 0)
        cout << a << " ";
}

int printIncr(int n)
{
    if (n == 0)
        return 0;

    int tail = printIncr(n - 1);
    cout << tail + 1 << " ";
    return tail + 1;
}

vector<string> printWithSpace(string str,int idx)
{
    if (str.size()-1 == idx)
    {
        vector<string> base;
        base.push_back(string(1,str[idx]));
        return base;
    }

    char ch = str[idx];
    vector<string> recAns = printWithSpace(str,idx+1);

    vector<string> ans;
    for (string s : recAns)
    {
        ans.push_back(string(1,ch) + s);
        ans.push_back(string(1,ch) + " " + s);
    }

    return ans;
}

int main(int args, char **argv)
{
    // increasing(0, 10);
    // decreasing(0, 10);
    // increDecre(1, 10);
    // oddEven(1,10);

    // printIncr(10);
    vector<string> ans = printWithSpace("ABC",0);
    for (string s : ans)
        cout << s << " ";
    return 0;
}