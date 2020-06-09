#include <iostream>
#include <climits>
using namespace std;

void ONBits(int n, int k)
{
    int mask = 1;
    mask <<= k;
    n |= mask;

    // n|=(1<<k);
}

void OFFBits(int n, int k)
{

    n &= (~(1 << k));
}

void countBits(int n)
{
    int count = 0;
    while (n != 0)
    {
        if ((n & 1) != 0)
            count++;
        n >>= 1;
    }

    cout << count << endl;
}

int main(int args, char **argv)
{
    countBits(-1);
}