#include <iostream>
using namespace std;

typedef long long ll;

ll anyBaseAdd(ll n, ll m, ll b)
{
    if (n == 0 || m == 0)
        return max(n, m);

    ll carry = 0, pow = 1, res = 0;
    while (n != 0 || m != 0 || carry != 0)
    {
        ll sum = 0;
        sum += carry + n % 10 + m % 10;
        n /= 10;
        m /= 10;

        ll ld = sum % b;
        carry = sum / b;

        res += ld * pow;
        pow *= 10;
    }

    return res;
}

ll multiplyNumberWithDigit(ll n, ll d, ll b, ll pow)
{
    ll res = 0, carry = 0;
    while (n != 0)
    {
        ll sum = (n % 10) * d + carry;
        ll lastDigit = sum % b;
        carry = sum / b;

        res += lastDigit * pow;
        pow *= 10;
    }

    return res;
}

ll multiplyTwoNumbers(ll n, ll m, ll b)
{
    ll pow = 1;
    ll res = 0;
    while (m != 0)
    {
        ll lastDigit = m % 10;
        ll smallAns = multiplyNumberWithDigit(n, lastDigit, b, pow);
        res = anyBaseAdd(res, smallAns, b);
        pow *= 10;
    }
}

int main()
{
    int n,m,b;
    cin >> b >>n >>m;
    cout<<multiplyTwoNumbers(b,n,m)<<endl;
    return 0;
}