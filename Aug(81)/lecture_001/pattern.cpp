#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int n;
    cin >> n;

    int nst = 1;
    for (int r = 1; r <= n; r++)
    {
        //for stars
        for (int cst = 1; cst <= nst; cst++)
        {
            cout << "*";
        }
        cout << endl;
        nst++;
    }
}