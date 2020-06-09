#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int row;
    cin >> row; // for java row=scn.nextInt();

    for (int i = 0; i < row; i++)
    {
        int val = 1;

        for (int j = 0; j <= i; j++)
        {
            cout << val << " ";
            val = (val * (i - j)) / (j + 1);
        }
        cout << endl;
    }

    return 0;
}