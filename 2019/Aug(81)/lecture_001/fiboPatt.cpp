#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int r;
    cin >> r;

    int a = 0;
    int b = 1;
    int sum = 0;
    for (int i = 1; i <= r; i++)
    {
        for (int j = 1; j <= i; j++)
        {
            cout << a << " ";
            sum = a + b;
            a = b;
            b = sum;
        }
    }
}