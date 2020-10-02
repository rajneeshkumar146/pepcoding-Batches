#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int ShortestPath(vector<int>&arr){
    vector<int>dp1(arr.size(),0);
    dp1[arr.size()-1]=0;
    for(int i=arr.size()-2;i>=0;i--){
        int Min=100000;
        for(int j=1;j<=arr[i] && j+i<arr.size();j++){
            Min=min(Min,dp1[i+j]);
        }
        dp1[i]=Min+1;
    }
    return dp1[0];
}
int MazePathDP(int sr,int sc,int er,int ec,string ans){
    if(sr==er&&sc==ec){
        cout<<ans<<endl;        
        return 1;
    }
    int count=0;
    if(sr+1<=er){
        count+=MazePathDP(sr+1,sc,er,ec,ans+"H");
    }
    if(sc+1<=ec){
        count+=MazePathDP(sr,sc+1,er,ec,ans+"V");
    }
    if(sc+1<=ec && sr+1<=er){
        count+=MazePathDP(sr+1,sc+1,er,ec,ans+"D");
    }
    return count;
}
int MazePathDP_02(vector<vector<int>>&dp,int er,int ec){
    for(int i=er;i>=0;i--){
        for(int j=ec;j>=0;j--){
            if(i==er&&j==ec){
                dp[i][j]=1;    
                continue;  
            }
            if(i+1<=er){
                dp[i][j]+=dp[i+1][j];
            } 
            if(j+1<=ec){
                dp[i][j]+=dp[i][j+1];
            } 
            if(i+1<=er&&j+1<=ec){
                dp[i][j]+=dp[i+1][j+1];
            }  
        }    
    }
    return dp[0][0];
}
int LIS(vector<int>&arr){
    vector<int>dp(arr.size(),0);
    dp[0]=1;
    int Max=1;
    for(int i=0;i<arr.size();i++){
       for(int j=0;j<i;j++){
           if(arr[i]>arr[j]&& dp[j]+1>dp[i]){
               dp[i]=dp[j]+1;
               Max=max(Max,dp[i]); 
           }
       }
    }
    return Max;
}
int LDS(vector<int>&arr){
    vector<int>dp(arr.size(),0);
    dp[0]=1;
    int Max=1;
    for(int i=arr.size()-2;i>=0;i--){
       for(int j=i+1;j<arr.size();j++){
           if(arr[i]>arr[j]&& dp[j]+1>dp[i]){
               dp[i]=dp[j]+1;
               Max=max(Max,dp[i]); 
           }
       }
    }
    return Max;
}
int main(){
    
    // int n=9;   
    // vector<int>arr={1,3,0,4,0,0,2,1,1,0};
    // // vector<vector<int>>dp(n,vector<int>(n,0));  
    // cout<<ShortestPath(arr)<<endl;  
    // //cout<<MazePathDP(0,0,2,2,"")<<endl;
    // //cout<<MazePathDP_02(dp,2,2)<<endl;
    // return 0;
    vector<int>arr={0,8,4,10,12,2,10,6,14,1,9,5,13,3,11,7,15};
    cout<<LDS(arr)<<endl;
    return 0;
}