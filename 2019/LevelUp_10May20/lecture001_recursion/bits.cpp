#include <iostream>
using namespace std;

int OFFtoON(int n, int k)
{
    int mask = (1 << k);
    n = (n | mask);
    return n;
}

int ONtoOFF(int n, int k)
{
    int mask = (~(1 << k));
    n &= mask;
    return n;
}

int noOfBits_01(int num)
{
    int count = 0;
    for (int i = 0; i < 32; i++)
    {
        int mask = (1 << i);
        if ((num & mask) != 0)
            count++;
    }
    return count;
}

int noOfBits_02(int n)
{
    unsigned int num = n;
    int count = 0;
    while (num != 0)
    {
        if ((num & 1) != 0)
            count++;
        num >>= 1;
    }

    return count;
}

int noOfBits_03(int num)
{
    int count = 0;
    while (num != 0)
    {
        count++;
        num &= (num - 1);
    }

    return count;
}

void solve()
{
    cout << noOfBits_02(-1) << endl;
    cout << noOfBits_03(-1) << endl;
}

int main()
{
    solve();
    return 0;
}