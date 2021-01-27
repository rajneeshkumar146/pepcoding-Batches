#include <iostream>
#include <vector>

using namespace std;

void input(vector<int> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        cin >> arr[i];
    }
}

void print(vector<int> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        cout << arr[i] << endl;
    }
    cout << endl;
}

void countSort(vector<int> &arr, int minEle, int maxEle)
{
    vector<int> freqArray(maxEle - minEle + 1, 0);
    int shift = abs(minEle);
    for (int ele : arr)
        freqArray[ele - minEle]++;

    int itr = 0;
    for (int i = 0; i < freqArray.size(); i++)
    {
        int freq = freqArray[i];
        while (freq-- > 0)
        {
            arr[itr++] = i + minEle;
        }
    }
}

int main()
{
    int n;
    cin >> n;
    vector<int> A(n, 0);
    input(A);

    int minEle = 1e9;
    int maxEle = -1e9;
    for (int ele : A)
    {
        minEle = min(minEle, ele);
        maxEle = max(maxEle, ele);
    }

    countSort(A, minEle, maxEle);
    print(A);
    return 0;
}