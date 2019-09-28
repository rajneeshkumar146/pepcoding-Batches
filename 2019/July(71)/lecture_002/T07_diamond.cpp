#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int n;
    cin >> n; // for java row=scn.nextInt();

    int nsp = n / 2, nst = 1;
    for (int i = 1; i <= n; i++)
    {
        for (int csp = 1; csp <= nsp; csp++)
        {
            cout << " ";
        }

        for (int cst = 1; cst <= nst; cst++)
        {
            cout << "*";
        }

        if (i <= n / 2)
        {
            nsp--;
            nst += 2;
        }
        else
        {
            nsp++;
            nst -= 2;
        }

        cout << endl;
    }

    return 0;
}