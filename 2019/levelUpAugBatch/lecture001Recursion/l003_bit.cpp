#include <iostream>

using namespace std;

int offToOn_setTrue(int n, int k)
{
    int mask = (1 << k);
    return (n | mask);
}

int onToOff_setFalse(int n, int k)
{
    int mask = (~(1 << k));
    return (n & mask);
}

//191
int hammingWeight(uint32_t n)
{
    // n = 32.
    int count = 0;

    //O(n)
    // for(int i=0;i<32;i++){
    //     int mask = (1 << i);
    //     if((n & mask)!=0) count++;
    // }

    //Log(n)
    // while(n!=0){
    //     if((n & 1)!=0) count++;
    //     n>>=1;
    // }

    // O(No of set bit time)
    while (n != 0)
    {
        count++;
        n &= (n - 1);
    }

    return count;
}

bool isPowerOfTwo(int n)
{
    return n != 0 && (n & (n - 1)) == 0;
}

bool isPowerOfFour(int num)
{
    if (num > 0 && (num & (num - 1)) == 0)
    {
        int count = 0;
        while (num != 1)
        {
            num >>= 1;
            count++;
        }

        return (count & 1) == 0;
    }

    return false;
}

//136
int singleNumber(vector<int> &nums)
{
    int ans  = 0;
    for(int ele: nums) ans^=ele;
    return ans; 
}

//137
int singleNumber(vector<int> &nums){
    
    int k = 3;
    int res = 0;
    for(int i=0;i<32;i++){
        int count=0;
        int mask = (1<<i);
        for(int ele: nums){
            if((ele & mask)!=0) count++;
        }

        if(count % k != 0) res|=mask;
    }

    return res;
}

void solve()
{
    cout << offToOn_setTrue(5, 1) << endl;
}

int main()
{
    solve();
    return 0;
}