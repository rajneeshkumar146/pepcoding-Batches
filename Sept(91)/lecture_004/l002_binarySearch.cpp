#include <iostream>
#include <vector>
using namespace std;

bool BS(vector<int> &arr, int data)
{
    int si = 0;
    int ei = arr.size() - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;

        if (arr[mid] == data)
        {
            cout << "Index: " << mid << endl;
            return true;
        }
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return false;
}

int BSLB(vector<int> &arr, int data)
{
    int si = 0;
    int ei = arr.size() - 1;
    // int ansidx=-1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;

        if (arr[mid] == data)
        {
            if (mid - 1 >= 0 && arr[mid - 1] == arr[mid])
                ei = mid - 1;
            else
                return mid;
        }
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return -1;
}

int BSUB(vector<int> &arr, int data)
{
    int si = 0;
    int ei = arr.size() - 1;
    // int ansidx=-1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;

        if (arr[mid] == data)
        {
            if (mid + 1 < arr.size() && arr[mid + 1] == arr[mid])
                si = mid + 1;
            else
                return mid;
        }
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return -1;
}

int BS_minDiff(vector<int> &arr, int data)
{
    if (data < arr[0])
        return arr[0];
    else if (data > arr[arr.size() - 1])
        return arr[arr.size() - 1];

    int si = 0;
    int ei = arr.size() - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;

        if (arr[mid] == data)
        {
            return mid;
        }
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    // if (ei == -1)
    //     return arr[si];
    // else if (si == arr.size())
    //     return arr[ei];
 
    if (data - arr[ei] <= arr[si] - data)
        return arr[ei];
    else
        return arr[si];
}


int BS_shoes(vector<int> &arr)
{
    if (arr.size()%2==0) return -1;
        return arr[0];

    int si = 0;
    int ei = arr.size() - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
         
         if(mid%2==0){ 
            if(arr[mid] == arr[mid+1]) ei=mid+2;
            else ei=mid;
         }else if(mid%2!=0){
             if(arr[mid]==arr[mid-1]) si=mid+1;
             else ei=mid-1;
         }else if(si==ei) return si;
    }
     return -1;
}


int main(int args, char **argv)
{
    vector<int> arr = {2, 5, 8, 8, 8, 8, 8, 8, 8, 10, 11, 15, 27, 56, 68, 76, 98};
    int data = 10;

    cout << (boolalpha) << BS(arr, data) << endl;
    cout << BSLB(arr, data) << endl;
    cout << BSUB(arr, data) << endl;
    return 0;
}
