#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int n;
    cin >> n;

    int a = 0, b = 1, sum = 0;

    if (n <= 1)
    {
        cout << n << endl;
        return 0;
    }

    for (int i = 2; i <= n; i++)
    {
        sum = a + b;
        a = b;
        b = sum;
    }

    cout << sum << endl;

    return 0;
}