#include <iostream>
using namespace std;

void mirrorTraingle(int n)
{
    int nst = 1;
    int nsp = n - 1;

    for (int r = 1; r <= n; r++)
    {
        for (int csp = 1; csp <= nsp; csp++)
        {
            cout << " ";
        }

        for (int cst = 1; cst <= nst; cst++)
        {
            cout << "*";
        }

        nst++;
        nsp--;
        cout << endl;
    }
}

void traingle(int n)
{
    int nst = 1;

    for (int r = 1; r <= n; r++)
    {
        for (int cst = 1; cst <= nst; cst++)
        {
            cout << "*";
        }
        nst++;
        cout << endl;
    }
}

void diamond(int n)
{
    int nst = 1;
    int nsp = n / 2;

    for (int r = 1; r <= n; r++)
    {
        for (int csp = 1; csp <= nsp; csp++)
        {
            cout << " ";
        }

        for (int cst = 1; cst <= nst; cst++)
        {
            cout << "*";
        }

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
        cout << endl;
    }
}

int main(int args, char **argv)
{
    int n;
    cin >> n;

    // mirrorTraingle(n);
    // traingle(n);
    diamond(n);

    return 0;
}