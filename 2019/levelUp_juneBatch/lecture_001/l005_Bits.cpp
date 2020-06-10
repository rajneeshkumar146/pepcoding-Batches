#include <iostream>
#include <vector>
#include <unordered_map>
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

//Leetcode 136.single number=============================================

int singleNumber(vector<int> &nums)
{
    int ans = 0;
    for (int ele : nums)
    {
        ans ^= ele;
    }
    return ans;
}

//Leetcode 231 : power of two.=========================================

bool isPowerOfTwo(int n)
{
    return n != 0 && (n & (n - 1)) == 0;
}

bool isPowerOfFour(int num)
{
    if (num == 0 || (num & (num - 1) != 0))
        return false;

    int count = 0;
    int bits = 0;
    while (num > 1 && bits <= 32)
    {
        num >>= 1;
        bits++;
        count++;
    }

    return (count & 1) == 0;
}

//Leetcode 137: single number ii.

int singleNumber_BekarSolution(vector<int> &nums)
{
    unordered_map<int, int> map;
    for (int ele : nums)
        map[ele]++;
    for (pair<int, int> a : map)
    {
        if (a.second == 1)
            return a.first;
    }
    return -1;
}

int singleNumber(vector<int> &nums)
{
    int singleNumber = 0;
    for (int i = 0; i < 32; i++)
    {
        int mask = 1 << i;
        int count = 0;
        for (int ele : nums)
        {
            if ((mask & ele) != 0)
                count++;
        }

        if (count % 3 != 0)
            singleNumber |= mask;
    }

    return singleNumber;
}
