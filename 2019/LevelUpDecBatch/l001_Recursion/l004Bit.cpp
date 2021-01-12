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

bool isBitSet(int n,int k){
    int mask = (1 << k);
    return (n & mask) != 0;
}