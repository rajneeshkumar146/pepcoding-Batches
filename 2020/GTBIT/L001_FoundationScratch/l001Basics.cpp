#include <iostream>
using namespace std;

int multiply(int a, int b)
{
    int c = a * b;
    return c;
}

int add(int a, int b)
{
    int c = a + b;
    return c;
}

int subtract(int a, int b)
{
    int c = b - a;
    return c;
}

int divide(int a, int b)
{
    int c = b / a;
    return c;
}

void printmessage()
{
    cout << "see you soon!" << endl;
}

void printNTime(int n)
{
    for (int i = 1; i <= n; i++)
    {
        cout << ("Hello!") << endl;
    }
}

void printTableOfN(int n)
{
    for (int i = 1; i <= 10; i++)
    {
        // cout << n << " X " << i << " = " << n * i << endl;
        cout << to_string(n) + " X " + to_string(i) + " = " + to_string(n * i) << endl;
    }
}

void printTableTillM(int m)
{
    for (int i = 1; i <= m; i++)
    {
        printTableOfN(i);
        cout << endl;
    }
}

int main()
{
    // int a, b;
    // cin >> a >> b;
    // cout << multiply(a,b) << endl;
    // cout << multiply(a,b) << endl;
    // cout << multiply(a,b) << endl;
    // cout << multiply(a,b) << endl;
    // cout << multiply(a,b) << endl;
    // cout << add(a,b) << endl;
    // printmessage();

    int n;
    cin >> n;
    // printNTime(n);
    // printTableTillM(n);
    printTableOfN(27);
    return 0;
}
