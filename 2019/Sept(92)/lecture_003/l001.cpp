#include <iostream>
using namespace std;

void mirrorTriangle(int rows)
{
    int nst = 1;
    int nsp = rows - 1;

    for (int r = 1; r <= rows; r++)
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

void Triangle(int rows)
{
    int nst = 1;
    int nsp = 0;

    for (int r = 1; r <= rows; r++)
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
        nsp = 0;
        cout << endl;
    }
}

void halfDiamond(int rows)
{
    int nst = 1;
    int nsp = rows - 1;

    for (int r = 1; r <= rows; r++)
    {
        for (int csp = 1; csp <= nsp; csp++)
        {
            cout << " ";
        }

        for (int cst = 1; cst <= nst; cst++)
        {
            cout << "*";
        }

        nst += 2;
        nsp--;
        cout << endl;
    }
}

void fullDiamond(int rows)
{
    int nst = 1;
    int nsp = rows / 2;

    for (int r = 1; r <= rows; r++)
    {
        for (int csp = 1; csp <= nsp; csp++)
        {
            cout << " ";
        }

        int ele = 1;
        for (int cst = 1; cst <= nst; cst++)
        {
            cout << ele;
        }

        if (r <= rows / 2)
        {
            nst += 2;
            nsp--;
        }
        else
        {
            nst -= 2;
            nsp++;
        }
        cout<<endl;
    }
}

int main(int args, char **argv)
{
    cout << "Entre Rows: ";
    int rows;
    cin >> rows;
    // mirrorTriangle(rows);
    // halfDiamond(rows);
    fullDiamond(rows);
}