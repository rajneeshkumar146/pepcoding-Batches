#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    cout << "entre your three numbers: " << endl;
    int a, b, c;
    cin >> a >> b >> c;

    if (a > b && a > c)
    {
        cout << "Largest No. is: " << a << endl;
    }
    else if (b > a && b > c)
    {
        cout << "Largest No. is: " << b << endl;
    }
    else
    {
        cout << "Largest No. is: " << c << endl;
    }

    return 0;
}