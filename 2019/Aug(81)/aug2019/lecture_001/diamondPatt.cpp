#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int n;
    cin >> n;

    int nst = 1;
    int nsp = n / 2;
    for (int r = 1; r <= n; r++)
    {

        for (int csp = 1; csp <= nsp; csp++)
        {
            cout << " ";
        }

        //for stars
        for (int cst = 1; cst <= nst; cst++)
        {
            cout << "*";
        }
        cout << endl;
        if (r <= n / 2)
        {
            nst += 2;
            nsp--;
        }
        else
        {
            nst -= 2;
            nsp++;
        }
    }
}