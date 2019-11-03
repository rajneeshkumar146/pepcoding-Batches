#include<iostream>
#include<vector>
#include<climits>
using namespace std;

void display(vector<int>& arr,int vidx){
    if(vidx==arr.size()) return;
    cout<<arr[vidx]<<" ";
    display(arr,vidx+1);
}

int maximum(vector<int>& arr,int vidx){
if(vidx==arr.size()){
   return INT_MIN;
}

  int recAns=maximum(arr,vidx+1);
  return max(arr[vidx],recAns);
}


int minimum(vector<int>& arr,int vidx){
if(vidx==arr.size()){
   return INT_MAX;
}

  int recAns=minimum(arr,vidx+1);
  return min(arr[vidx],recAns);
}

bool find(vector<int>& arr,int vidx,int data){
    if(vidx==arr.size()) return false;
    if(arr[vidx]==data) return true;

    return find(arr,vidx+1,data);

}

void inverse(vector<int>& arr,int idx){
    if(idx==arr.size()) return;
    
    int val=arr[idx];
    inverse(arr,idx+1);
    arr[val]=idx;   
}






int main(int args,char** argv){
    return 0;
}