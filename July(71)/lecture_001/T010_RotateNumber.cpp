#include <iostream>
using namespace std;

int main(int args, char **argv)
{
    int n, rot;
    cin >> n >> rot;

    int len = 0;
    int temp = n;
    while (temp != 0)
    {
        temp /= 10;
        len++;
    }

    rot %= len; //if rot value is greater than len.
    rot = rot < 0 ? rot + len : rot;

    int div = 1;
    int mul = 1;
    for (int i = 1; i <= len; i++)
    {
        if (i <= rot)
            mul *= 10;
        else
            div *= 10;
    }

    int lastDigits = n / div;
    int firstDigits = n % div;

    int ans = firstDigits * mul + lastDigits;
    cout << ans << endl;
    return 0;
}