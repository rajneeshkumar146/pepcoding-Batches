#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int n;
    cin >> n;

    int rev = 0;
    while (n != 0)
    {
        int rem = n % 10;
        n = n / 10;

        rev *= 10;  //multiply by 10 for reverse number till ith state.
        rev += rem; // rev=rev+rem;
    }

    cout << rev << endl;

    return 0;
}