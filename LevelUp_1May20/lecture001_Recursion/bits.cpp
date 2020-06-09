#include <iostream>
#include <vector>
using namespace std;

int offToOn(int num, int k)
{
    int mask = (1 << k);
    return (num | mask);
}

int onToOff_01(int num, int k)
{
    int mask = (~(1 << k)); //1's compliment of mask.
    return (num & mask);
}

int onToOff_02(int num, int k)
{
    int mask = (1 << k); //1's compliment of mask.
    if ((num & mask) == 0)
        return num;
    else
        return (num ^ mask);
}

int countAllSetBits_02(unsigned_int num)
{
    int count = 0;
    while (num != 0)
    {
        if ((num & 1) != 0)
            count++;
        num >>= 1;
    }
}

//leetcode: 338===========================================

vector<int> countBits(int num)
{
    vector<int> ans(num+1,0);
    for(int i=1;i<=num;i++) ans[i]=ans[(i & (i-1))] + 1;
    return ans;
}

int main()
{
    int num = 123, k = 3;
    cout << (onToOff_01(num, k) == onToOff_02(num, k)) << endl;
    return 0;
}