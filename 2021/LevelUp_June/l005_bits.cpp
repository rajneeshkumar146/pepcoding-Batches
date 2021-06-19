using namespace std;

bool isPowerOfFour(int n)
{
    if (n <= 0 || (n & (n - 1)) != 0)
        return false;

    uint32_t N = n;
    int count = 0;
    while (N != 0)
    {
        if ((N & 1) == 0)
            count++;

        N >>= 1;
    }

    return (count & 1) == 0;
}