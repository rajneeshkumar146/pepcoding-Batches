#include <iostream>
#include <vector>

using namespace std;

bool find(vector<int> &arr, int data)
{
    for(int ele: arr){
        if(ele==data) return true;
    }

    return false;
}

int maximum(vector<int> &arr)
{
    int max_=arr[0];
    for(int i=1;i<arr.size();i++){
        max_=max(max_,arr[i]);
    }
    return max_;
}

int minimum(vector<int> &arr)
{

    int min_=arr[0];
    for(int i=1;i<arr.size();i++){
        min_=min(min_,arr[i]);

        // min_=arr[i]<min_?arr[i]:min_;
    }
    return min_;
}

void swap_(vector<int>& arr,int i,int j){
    int temp=arr[i];
    arr[i]=arr[j];
    arr[j]=temp;
}

void reverse(vector<int>& arr,int li,int ui){
       while(li<ui){
        swap_(arr,li,ui);
           li++;
           ui--;
       }
}

void rotate(vector<int>& arr,int r){
    
     r=r%arr.size();
     if(r<0) r=r+arr.size();

    reverse(arr,0,r-1);
    reverse(arr,r,arr.size()-1);
    reverse(arr,0,arr.size()-1);

}

void input(vector<int> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        cin >> arr[i];
    }
}






int RainMAxWater(vector<int>& arr){
    int max_=0;
    int i=0;
    int j=arr.size()-1;
    while(i<j){
        if(arr[i]<=arr[j]){
            max_=max(max_,arr[i]*(j-i));
            i++;
        }else{
            max_=max(max_,arr[j]*(j-i));
            j--;
        }
    }
    return max_;
}

void display(vector<int> &arr)
{
    // for(int i=0;i<arr.size();i++){
    //         cout<<arr[i];
    //     }

    for (int ele : arr)
    {
        cout << ele << " ";
    }
    cout << endl;
}

int main(int args, char **argv)
{
    int size;
    cin >> size;
    vector<int> arr(size, 0);
    input(arr);
    display(arr);

    cout<<"Is element found: "<<(boolalpha)<<find(arr,6)<<endl;
    cout<<"Maximum Ele: "<<maximum(arr)<<endl;
    cout<<"Minimum Ele: "<<minimum(arr)<<endl;
    
    cout<<"Rotated Array: "<<endl;
    rotate(arr,-3);
    display(arr);

    return 0;
}