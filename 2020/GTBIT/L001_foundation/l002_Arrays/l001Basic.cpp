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


//Sum of two arrays.
void sumOfTwoArrays(vector<int>& arr1,vector<int>& arr2){
    int p = arr1.size();
    int q = arr2.size();
    int r = max(p,q) + 1;

    vector<int> ans(r, 0);
    int i = p - 1, j = q - 1, k = r - 1, carry = 0;

    while(k >= 0){
        int sum = carry;
        if(i >= 0) sum += arr1[i--];
        if(j >= 0) sum += arr2[j--];

        ans[k--] += sum % 10;
        carry = sum / 10;
    }

    for(int l = 0;l < ans.size(); l++){
        if(l == 0 && ans[l] == 0) continue;
        cout << ans[l]<<endl;
    }
}

void diffOfTwoArrays(vector<int>& arr1,vector<int>& arr2){
    int p = arr1.size();
    int q = arr2.size();
    int r = p;

    vector<int> ans(r, 0);
    int i = p - 1, j = q - 1, k = r - 1, borrow = 0;

    while(k >= 0){
        int num = borrow;
        
        if(i >= 0) num += arr1[i--];
        if(j >= 0) num -= arr2[j--];
        
        if(num < 0){
            num += 10;
            borrow = -1;
        }else borrow = 0;
        
        ans[k--] = num;
    }

    bool flag = false;
    for(int l = 0;l < ans.size(); l++){
        if(!flag && ans[l] == 0) continue;
        cout << ans[l]<<endl;
        flag = true;
    }
}

void reverse(vector<int>& arr,int i,int j){
    while(i<j){
        
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        i++;
        j--;
    }
}

void rotate(vector<int>& arr,int k){
    int n = arr.size();
    k = (k % n + n) % n;
    reverse(arr,0,n - 1);
    reverse(arr,0,k - 1);
    reverse(arr,k ,n - 1);
}

int main()
{
    int n; 
    cin >> n;
    vector<int> arr(n);
    for(int i=0;i<n;i++) cin >> arr[i];

    return 0;
}