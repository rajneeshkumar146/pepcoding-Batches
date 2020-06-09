#include <iostream>
using namespace std;

int facto(int n)
{
    int ans = 1;
    for (int i = 1; i <= n; i++)
    {
        ans *= i;
    }
    return ans;
}

void ncr()
{
    int n, r;
    cin >> n >> r;

    int ans = facto(n) / (facto(n - r) * facto(r));

    cout << ans << endl;
}

int digits(int n)
{
    int len = 0;
    while (n != 0)
    {
        len++;
        n /= 10;
    }

    return len;
}

int rotate(int n, int r)
{
    int digit = digits(n);
    r %= digit;
    r = r < 0 ? r + digit : r;

    int mul = 1;
    int divd = 1;
    for (int i = 1; i <= digit; i++)
    {
        if (i <= r)
            mul *= 10;
        else
            divd *= 10;
    }

    int fdigits = n % divd;
    int sdigits = n / divd;

    return fdigits * mul + sdigits;
}



int main(int args, char **argv)
{
    int n;
    cin >> n;
    cout << facto(n);
    ncr();

    return 0;
}