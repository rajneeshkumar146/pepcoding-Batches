#include <iostream>
using namespace std;

void DecreasingOder(int n)
{
    if (n == 0)
    {
        cout << "BASE-> " << n << endl;
        return;
    }

    cout << "HI-> " << n << endl;
    DecreasingOder(n - 1);
    cout << "BYE-> " << n << endl;
}

void oddEven(int n)
{
    if (n == 0)
    {
        cout << "Base: " << n << endl;
        return;
    }
    if ((n & 1) == 0)
        cout << "Even: " << n << endl;

    oddEven(n - 1);

    if ((n & 1) != 0)
        cout << "Odd: " << n << endl;
}

int main()
{
    // DecreasingOder(10);
    oddEven(10);
    return 0;
}