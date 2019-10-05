#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int n = 50;

    cout << "even: ";
    for (int i = 1; i <= n; i++)
    {
        if (i % 2 == 0)
            cout << i << " ";
    }

    cout << endl;

    cout << "odd: ";
    for (int i = 1; i <= n; i++)
    {
        if (i % 2 != 0)
            cout << i << " ";
    }
    return 0;
}