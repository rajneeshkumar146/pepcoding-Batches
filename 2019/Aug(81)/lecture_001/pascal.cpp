#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int r;
    cin >> r;
    int nst = 0;
    for (int i = 0; i <= r; i++)
    {
        int val = 1;
        for (int j = 0; j <= i; j++)
        {
            cout << val;
            val = (i - j) * val / (j + 1);
        }

        cout << endl;
    }
}