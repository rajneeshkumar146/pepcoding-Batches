#include <iostream>
#include <vector>

using namespace std;

int eulerFunction(int n)
{
    if (n <= 1)
    {
        cout << "Base Case: " + to_string(n) << endl;
        return n;
    }

    int count = 0;

    cout << "pre: " + to_string(n) << endl;
    count += eulerFunction(n - 1);

    cout << "in: " + to_string(n) << endl;

    count += eulerFunction(n - 2);
    cout << "post: " + to_string(n) << endl;

    return count + 3;
}

void printIncreasing(int a, int b)
{
    if(a == b) return;

    cout << a << endl;
    printIncreasing(a + 1, b);
}

void display(vector<int>& arr,int idx)
{
    if(idx == arr.size()) return;
    
    cout << arr[idx] << endl;
    display(arr,idx+1);
}

void display(vector<int>& arr,int idx)
{
    if(idx == arr.size()) return;
    
    display(arr,idx+1);
    cout << arr[idx] << endl;
}

int maxOfArray(vector<int>& arr,int idx)
{
    if(idx == arr.size()) return -1e9;
    int maxOfSmallSeg = maxOfArray(arr,idx + 1);
    return max(arr[idx],maxOfSmallSeg);
}

int minOfArray(vector<int>& arr,int idx)
{
    if(idx == arr.size()) return 1e9;
    int minOfSmallSeg = minOfArray(arr,idx + 1);
    return min(arr[idx],minOfSmallSeg);
}

bool findData(vector<int>& arr,int idx,int data){
    if(idx == arr.size()) return false;
    if(arr[idx] == data) return true;
    return findData(arr,idx+1,data);
}

int lastIndex(vector<int>& arr,int idx,int data){
    if(idx == -1) return -1;
    if(arr[idx] == data) return idx;
    return lastIndex(arr,idx - 1,data);
}

int lastIndex2(vector<int>& arr,int idx,int data){
    if(idx == arr.size()) return -1;
    int ans = lastIndex(arr,idx+1,data);
    if(ans != -1) return ans;
    
    return arr[idx] == data ? idx : -1;
}

void input(vector<int>& arr){
    for(int i = 0; i < arr.size(); i++) cin >> arr[i];
}

int main()
{
    int n; cin >> n;
    vector<int> arr(n,0);
    input(arr);
    display(arr,0);
    return 0;
}
