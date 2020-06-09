#include <iostream>
using namespace std;

void traingle(int n)
{
    int nst = 1;
    for (int row = 1; row <= n; row++)
    {
        for (int cst = 1; cst <= nst; cst++)
            cout << "*";
        cout << endl;

        nst++;
    }
}

void traingle2(int n)
{
    int nst = 1;
    int nsp = n - 1;
    for (int row = 1; row <= n; row++)
    {
        for (int csp = 1; csp <= nsp; csp++)
            cout << " ";
        for (int cst = 1; cst <= nst; cst++)
            cout << "*";
        cout << endl;

        nst++;
        nsp--;
    }
}

void diamondStarPattern(int n)
{
    int nst = 1;
    int nsp = n / 2;
    for (int row = 1; row <= n; row++)
    {
        for (int csp = 1; csp <= nsp; csp++)
            cout << " ";
        for (int cst = 1; cst <= nst; cst++)
            cout << "*";
        cout << endl;

        if (row <= n / 2)
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

void halfDiamondStarPattern(int n)
{
    int nst = 1;
    int nsp = n -1;
    for (int row = 1; row <= n; row++)
    {
        for (int csp = 1; csp <= nsp; csp++)
            cout << " ";
        for (int cst = 1; cst <= nst; cst++)
            cout << "*";
        cout << endl;

            nst += 2;
            nsp--;
        
    }
}


void solve()
{
    int n;
    cin >> n;
    //    diamondStarPattern(n);
    // traingle(n);
    // traingle2(n);
    halfDiamondStarPattern(n);
}

int main()
{
    solve();
    return 0;
}