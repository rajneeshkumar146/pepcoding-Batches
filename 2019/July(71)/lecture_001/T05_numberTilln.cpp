#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    cout << "entre your number: " << endl;
    int a;
    cin >> a;

    for (int i = 0; i < a; i++)
    {
        cout << i << " ";
    }

    cout << endl;
    int i = 0;
    while (i < a)
    {
        cout << i << " ";
        i++;
    }

    return 0;
}