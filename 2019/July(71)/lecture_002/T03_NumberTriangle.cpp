#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int row;
    cin >> row; // for java row=scn.nextInt();a

    int nst = 1; //No. if stars,intial condition for first row.

    int count = 1;
    while (row-- > 0)
    {
        for (int cst = 1; cst <= nst; cst++)
        {
            cout << count<<" ";
            count++;
        }
        nst++;
        cout << endl;
    }
}