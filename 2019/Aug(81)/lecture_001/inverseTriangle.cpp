#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int n;
    cin >> n;

    int nsp = n-1;
    int nst = 1;
    for (int r = 1; r <= n; r++)
    {
        //for spaces
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
        nst++;
        nsp--;
    }
}