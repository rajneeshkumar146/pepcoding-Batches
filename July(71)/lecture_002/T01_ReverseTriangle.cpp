#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int row;
    cin >> row;  // for java row=scn.nextInt();a

    int nst = 1;       //No. if stars,intial condition for first row.
    int nsp = row - 1; //No. of space.

    while (row-- > 0)
    {
        for (int csp = 1; csp <= nsp; csp++)
            cout << " ";

        for (int cst = 1; cst <= nst; cst++)
            cout << "*";

        nsp--;
        nst++;
        cout << endl;
    }
}