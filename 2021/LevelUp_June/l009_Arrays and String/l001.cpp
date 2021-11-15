#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

// rotate array by K
void reverse(vector<int>& nums, int i, int j){
        while(i<j){
            swap(nums[i++],nums[j--]);
        }
    }

    void rotate(vector<int>& nums, int k){
        // k,k+size, k+2*size;
        int size=nums.size();

        int rot=( k%size + size ) % size;
        // reverse first part(array a);
        reverse(nums,0,size-rot-1);
        // reverse second part(array b);
        reverse(nums,size-rot,size-1);
        // reverse whole array(a,b);
        reverse(nums,0,size-1);
    }

    // segregate positive negative 

    void segPosNeg(vector<int> arr){
        int neg_ptr=-1;
        int ptr=0;
        int size=arr.size();

        while(ptr<size){
            if(arr[ptr]<0){
                swap(arr[++neg_ptr],arr[ptr]);
            }
            ptr++;
        }
    }
    

    // segregate 0s and 1s ===============

    void segPosNeg(vector<int> arr){
        int zero_ptr=-1;
        int ptr=0;
        int size=arr.size();

        while(ptr<size){
            if(arr[ptr]==0){
                swap(arr[++zero_ptr],arr[ptr]);
            }
            ptr++;
        }
    }
    

    // leetcode 75 (segregate 0's, 1's and 2's)
     void sortColors(vector<int>& nums) {
        int p1=-1;
        int p2=0;
        int n=nums.size();
        int p3=n-1;
        
        
        while(p2<=p3){
            if(nums[p2]==0){
                swap(nums[++p1],nums[p2++]);
            } else if(nums[p2]==2) {
                swap(nums[p3--],nums[p2]);
            } else {
                p2++;
            }
        }
    }


    // https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1#

    int max_sum(int A[],int N){
        int sum=0;
        for(int i=0; i<N; i++){
            int e=A[i];
            sum+=e;
        }
        
        
        int csum=0;
        for(int i=0; i<N; i++){
            csum+=i*A[i];
        }
        
        int ans=csum;
        
        for(int i=1; i<N; i++){
            int nsum=csum-sum+N*A[i-1];
            ans=max(ans,nsum);
            csum=nsum;
        }
        return ans;
    }

// leetcode 11==========================================================

int maxArea(vector<int>& height) {
     int n=height.size();
        
        int p1=0;
        int p2=n-1;
        
        int max_area=0;
        
        while(p1<p2){
            int len=min(height[p1],height[p2]);
            int width=p2-p1;
            
            int curr_area=len*width;
            
            max_area=max(curr_area,max_area);
            
            if(height[p1]<=height[p2]){
                p1++;
            } else {
                p2--;
            }
        }
        
        return max_area;
    }

//SLIDING WINDOW  ===========================================================================================

int lengthOfLongestSubstring(string s) {
        int si=0;
        int ei=0;
        int count=0;
        
        int n=s.size();
        int len=0;
        
        vector<int> fre(128,0);
        
        while(ei<n){
            if(fre[s[ei]]==1){
                count++;
            }
            
            fre[s[ei]]++;
            ei++;
            
            while(count>0){
                if(fre[s[si]]==2){
                    count--;
                }
                
                fre[s[si]]--;
                si++;
            }
            
            len=max(len,ei-si);
        }
        
        return len;
    }

    // lintcode 928 =============================================

    int lengthOfLongestSubstringTwoDistinct(string &s) {
        // Write your code here
        int si=0;
        int ei=0;
        int count=0;
        int len=0;

        int n=s.size();
        vector<int> fre(128,0);

        while(ei<n){
            if(fre[s[ei]]==0){
                count++;
            }

            fre[s[ei]]++;
            ei++;

            while(count>2){
                if(fre[s[si]]==1){
                    count--;
                }
                fre[s[si]]--;
                si++;
            }

            len=max(len,ei-si);
        }

        return len;
    }
