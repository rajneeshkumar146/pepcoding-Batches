#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int a, b;
    cout << "entre table starting point and range: ";
    cin >> a >> b;

    for (int i = 1; i <= b; i++)
    {
        cout << a << " * " << i << ": " << a * i << endl;
    }
}