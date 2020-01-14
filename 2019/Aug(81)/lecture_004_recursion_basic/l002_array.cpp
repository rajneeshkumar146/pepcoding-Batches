#include <iostream>
#include <vector>
#include <climits>

using namespace std;


void display_(vector<int>& arr,int idx)
{
    if(idx==arr.size()) return;

    cout<<arr[idx]<<" ";
    display_(arr,idx+1);

}


  void display(vector<int>& arr){
      display_(arr,0);
  }

  bool find(vector<int>& arr,int idx,int data){
      if(idx==arr.size()) return false;

      if(arr[idx]==data) return true;

      bool res=find(arr,idx+1,data);
      return res;

  }

int maximum(vector<int>& arr,int idx){
    if(idx==arr.size()) return INT_MIN;
    // if(idx==arr.size()-1) return arr[idx];

    int recMax=maximum(arr,idx+1);

    return max(arr[idx],recMAx);

}


int main(int args, char **argv)
{
    vector<int> arr={10,20,30,40,50,60,70};
    display(arr);
 }