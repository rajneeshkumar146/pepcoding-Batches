#include <iostream>
using namespace std;

int fib(int n)
{
    if (n <= 1)
        return n;

    int recAns = fib(n - 1) + fib(n - 2);
    return recAns;

    // return n <= 1 ? n : fib(n - 1) + fib(n - 2);
}

int printTreePath(int n)
{
    if (n == 1 || n == 0)
    {
        cout << "base: " << n << endl;
        return n;
    }
    int ans = 0;

    cout << "pre: " << n << endl;
    ans += printTreePath(n - 1);

    cout << "In: " << n << endl;

    ans += printTreePath(n - 2);
    cout << "post: " << n << endl;

    return ans + 3;
}

int main(int args, char **argv)
{
    // cout << fib(5) << endl;
    cout << printTreePath(5);
}