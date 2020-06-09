#include <iostream>
#include <vector>

using namespace std;

int KthOFFToON(int num, int k) // on -> on and off -> on
{
    int mask = (1 << k);
    return (num | mask);
}

int KthOnToOFF(int num, int k) // on -> oFF and off -> off
{
    int mask = (~(1 << k));
    return (num & mask);
}

int noOfSetBits(int num)
{
    int bitCount = 0;
    int totalBits = 32;

    while (num != 0 && totalBits != 0)
    {
        if ((num & 1) != 0)
            bitCount++;
        num >>= 1;
        totalBits--;
    }
    return bitCount;
}

//leetcode 338
int noOfSetBits_01(int num)
{
    int bitcount = 0;
    while (num != 0)
    {
        num &= (num - 1);
        bitcount++;
    }
    return bitcount;
}

//leetcode 137
int singleNumberII(vector<int> &arr)
{
    // T: O(32n) and S: O(1)
    int singleNumber = 0;
    for (int i = 0; i < 32; i++)
    {
        int count = 0;
        int mask = (1 << i);
        for (int ele : arr)
        {
            if ((ele & mask) != 0)
                count++;
        }

        if (count % k != 0)
            singleNumber |= mask;
    }
    return singleNumber;
}

//leetcode 260.
vector<int> singleNumberIII(vector<int> &nums)
{
    int xorNum = 0;
    for (int ele : nums)
        xorNum ^= ele;

    int firstLSB = (xorNum & (-xorNum));
    int a = 0;
    int b = 0;
    for (int ele : nums)
    {
        if ((firstLSB & ele) != 0)
            a ^= ele;
        else
            b ^= ele;
    }

    return {a, b}; // java: new int[]{a,b};
}

//leetcode 231.
bool isPowerOfTwo(int n)
{
    return n > 0 && (n & (n - 1)) == 0;
}

//leetcode 342
bool isPowerOfFour(int num)
{
    if (n > 0 && !(n & (n - 1))) // is num power of 2
    {
        int count = 0;  // count of all zeros after 1.
        while (num > 1)
        {
            count++;
            nmu >>= 1;
        }

        if ((count & 1) == 0)
            return true; // count of zeros after 1 should be a even number.
    }
    
    return false;
}

int main()
{
    return 0;
}