#include <iostream>
using namespace std;

int main()
{
    int a, b, c;
    cout << "please Enter three values with space: ";
    cin >> a >> b >> c;

    int maxNum = -1;
    if (a >= b && a >= c)
    {
        maxNum = a;
    }
    else if (b >= a && b >= c)
    {
        maxNum = b;
    }
    else
    {
        maxNum = c;
    }

    cout << "Maximum Number is: " << maxNum << endl;

    return 0;
}