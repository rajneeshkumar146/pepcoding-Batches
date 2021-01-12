#include <iostream>
using namespace std;

int offToON(int n, int k)
{
    int mask = (1 << k);
    return (n | mask);
}

int onToOff(int n, int k)
{
    int mask = (~(1 << k));
    return (n & mask);
}

bool isBitSet(int n, int k)
{
    int mask = (1 << k);
    return (n & mask) != 0;
}

int hammingWeight(int n)
{

    int count = 0;
    for (int i = 0; i < 32; i++)
    {
        int mask = (1 << i);
        if ((n & mask) != 0)
            count++;
    }

    return count;
}

int hammingWeight(int n)
{
    int count = 0;
    int bitsCount = 0;
    while (n != 0 && bitsCount < 32)
    {
        if ((n & 1) != 0)
            count++;
        n >>= 1;
        bitsCount++;
    }

    return count;
}

int hammingWeight(int n)
{
    int count = 0;
    while (n != 0)
    {
        count++;
        n &= (n - 1);
    }

    return count;
}