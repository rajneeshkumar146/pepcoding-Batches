#include <iostream>
#include <vector>
using namespace std;

int OffOnBits(int x, int k)
{
    int mask = (1 << k);
    return (x | mask);
}

int onOffBits(int x, int k)
{
    int mask = (~(1 << k));
    return (x & mask);
}

int noOfOneBits_01(int x)
{
    int count = 0;
    for (int i = 0; i < 32; i++)
    {
        int mask = 1 << i;
        if ((x & mask) != 0)
            count++;
    }
    return count;
}

int noOfOneBits_02(int x)
{
    int count = 0;
    int t = 0;
    while (x != 0 && t < 32)
    {
        if ((x & 1) != 0)
            count++;
        x >>= 1;
        t++;
    }

    return count;
}

int noOfOneBits_03(int x)
{
    int count = 0;
    while (x != 0)
    {
        count++;
        x &= (x - 1);   
    }

    return count;
}
