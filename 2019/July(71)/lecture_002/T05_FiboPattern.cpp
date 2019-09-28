#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int row;
    cin >> row; // for java row=scn.nextInt();a

    int nst = 1; //No. if stars,intial condition for first row.

    int a = 1.b = 0;
    while (row-- > 0)
    {
        for (int cst = 1; cst <= nst; cst++)
        {
            cout << b << " ";
            int sum = a + b;
            a = b;
            b = sum;
        }
        nst++;
        cout << endl;
    }
}