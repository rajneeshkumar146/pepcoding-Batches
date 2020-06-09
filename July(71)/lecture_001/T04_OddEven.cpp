#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    cout << "entre your number: " << endl;
    int a;
    cin >> a;

    if (a % 2 == 0)
        cout << "Even" << endl;
    else
        cout << "Odd" << endl;

    return 0;
}