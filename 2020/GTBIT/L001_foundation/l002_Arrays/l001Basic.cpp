#include <iostream>
#include <vector>
using namespace std;

void test1()
{
    int *arr = new int[10];
    for (int i = 0; i < 20; i++)
    {
        cout << arr[i] << " ";
    }
}

void test2()
{
    int n;
    cin >> n;
    vector<int> arr(n);
    for (int i = 0; i < arr.size(); i++)
    {
        cin >> arr[i];
    }

    cout << "---------------------------\n";

    for (int i = 0; i < arr.size(); i++)
    {
        cout << arr[i] << " ";
    }
}

void test3()
{
    vector<int> arr(10, 89);
    for (int i = 0; i < arr.size(); i++)
    {
        cout << arr[i] << " ";
    }
}

// Basic Question.============================================================

int maximumEle(vector<int>& arr){
    int maxEle = -1e8;
    for(int i=0;i<arr.size();i++){
        if(arr[i] > maxEle) maxEle = arr[i];
    }

    return maxEle;
}

int minimumEle(vector<int>& arr){
    int minEle = 1e8;
    for(int i=0;i<arr.size();i++){
        if(arr[i] < minEle) minEle = arr[i];
    }

    return minEle;
}

int findEle(vector<int>& arr,int data){
    int idx = -1;
    for(int i=0;i<arr.size();i++){
        if(arr[i] == data){
            idx = i;
            break;
        }
    }

    return idx;
}

int firstIndex(vector<int>& arr,int data){
    int idx = -1;
    for(int i=0;i<arr.size();i++){
        if(arr[i] == data){
            idx = i;
            break;
        }
    }

    return idx;
}


int lastIndex(vector<int>& arr,int data){
    int idx = -1;
    for(int i = arr.size() - 1;i >= 0; i--){
        if(arr[i] == data){
            idx = i;
            break;
        }
    }

    return idx;
}


int main()
{
    int n; 
    cin >> n;
    vector<int> arr(n);
    for(int i=0;i<n;i++) cin >> arr[i];

    return 0;
}