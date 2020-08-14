#include <iostream>
#include <vector>

using namespace std;

bool compareTo(vector<int> &arr, bool isMax, int a, int b)
{
    if (isMax)
        return arr[a] > arr[b];
    else
        return arr[b] > arr[a];
}

void downHeapify(vector<int> &arr,int pi, bool isMax,int n)
{
    int maxidx = pi;
    int lci = 2 * pi + 1;
    int rci = 2 * pi + 2;

    if (lci <= n && compareTo(arr,isMax,lci, maxidx))
        maxidx = lci;
    if (rci <= n && compareTo(arr,isMax,rci, maxidx))
        maxidx = rci;

    if (maxidx != pi)
    {
        swap(arr[pi], arr[maxidx]);
        downHeapify(arr,maxidx,isMax,n);
    }
}

void solve()
{
    vector<int> arr = {10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13};
    int n = arr.size();
    bool isMax = false;
    
    for (int i = n - 1; i >= 0; i--)
    {
        downHeapify(arr,i,isMax,n-1);
    }

    int i = n-1;
    while(i>=0){
        swap(arr[0],arr[i--]);
        downHeapify(arr,0,isMax,i);
    }
    
    for(int ele: arr) cout<<ele<<" ";
    cout<<endl;
}

int main()
{
    solve();
    return 0;
}
