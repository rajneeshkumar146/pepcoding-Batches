#include <iostream>

using namespace std;

void Increasing(int n)
{
    if (n == 0)
        return;

    Increasing(n - 1); //faith: n-1 to 0 in revserse order.
    cout << n << " ";
}

void decreasing(int n)
{
    if (n == 0)
        return;

    cout << n << " ";
    decreasing(n - 1); //faith: n-1 to 0.
}

void DIV1(int n)
{

    if (n == 0)
        return;

    cout << n << " ";
    DIV1(n - 1); //faith: n-1 to 0 in decreasing and revserse order.
    cout << n << " ";
}

void DIV2(int n)
{

    if (n == 1)
    {
        cout << n << " ";
        return;
    }
    cout << n << " ";
    DIV2(n - 1); //faith: n-1 to 0 in decreasing and revserse order.
    cout << n << " ";
}

void IDV(int n, int tar)
{

    if (n == tar)
    {
        cout << n << " ";
        return;
    }
    cout << n << " ";
    IDV(n + 1, tar); //faith: n+1 to tar in increasing and revserse order.
    cout << n << " ";
}

int fibo(int n)
{
    if (n <= 1)
        return n;

    int nthMinusOne = fibo(n - 1);
    int nthMinusTwo = fibo(n - 2);

    int nthFibo = nthMinusOne + nthMinusTwo;
    return nthFibo;
}

void fiboSeries(int m)
{
    for (int i = 0; i <= m; i++)
    {
        cout << fibo(i) << " ";
    }
}

int steps = 0;

int powerBekar(int a, int b)
{
    if (b == 0)
        return 1;

    steps++;
    int ans = a * powerBekar(a, b - 1);
    return ans;

    // return b != 0 ? a * powerBekar(a, b - 1) : 1;
}

int fact(int n)
{
    if (n <= 1)
        return 1;

    int ans = n * fact(n - 1);
    return ans;
}

void oddEven(int n, in0t target)
{
    if (n > target)
        return;

    if (n % 2 != 0)
    {
        cout << n << " ";
    }

    oddEven(n + 1, target);

    if (n % 2 == 0)
    {
        cout << n << " ";
    }
}

void printPath(int n)
{
    if (n <= 1)
    {
        cout << "Base -> " << n << endl;
        return;
    }

    cout << "Pre -> " << n << endl;
    printPath(n - 1);
    cout << "In -> " << n << endl;
    printPath(n - 2);
    cout << "Post -> " << n << endl;
}

void printPathWithLevel(int n, int level)
{
    if (n <= 1)
    {
        cout << "Base of " << n << "with level" << level << endl;
        return;
    }

    cout << "Pre of " << n << "with level" << level << endl;
    printPathWithLevel(n - 1, level + 1);
    cout << "In of " << n << "with level" << level << endl;
    printPathWithLevel(n - 2, level + 1);
    cout << "Post of " << n << "with level" << level << endl;
}

9

void solve()
{
    // decreasing(10);
    // cout << endl;
    // Increasing(10);
    // cout << endl;
    // DIV1(10);
    // cout << endl;
    // DIV2(10);
    // cout << endl;
    // IDV(0, 10);
    // cout << endl;

    // cout << " --> " << fibo(5) << endl;
    // fiboSeries(10);
    // cout << powerBekar(2, 10) << endl;
    // cout << steps << endl;

    // printPath(5);
    printPathWithLevel(4, 0);
}

int main()
{
    solve();
    return 0;
}