#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

int binarySearch(vector<int> &arr, int si, int ei, int data)
{
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == data)
            return mid;
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return -1;
}

int firstIndex(vector<int> &arr, int data)
{
    if (arr[0] == data)
        return 0;

    int si = 0, ei = arr.size() - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == data)
        {
            if (arr[mid - 1] == data)
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

int lastIndex(vector<int> &arr, int data)
{
    if (arr[arr.size() - 1] == data)
        return arr.size() - 1;

    int si = 0, ei = arr.size() - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == data)
        {
            if (arr[mid + 1] == data)
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

vector<int> searchRange(vector<int> &nums, int target)
{
    if (nums.size() == 0)
        return {-1, -1};                                        // new int[]{-1,-1};
    return {firstIndex(nums, target), lastIndex(nums, target)}; // new int[]{};
}

int closesetElement(vector<int> &arr, int data)
{
    int si = 0, ei = arr.size() - 1;
    if (data < arr[si])
        return si;
    else if (data > arr[ei])
        return ei;

    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == data)
            return mid;
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return data - arr[ei] < arr[si] - data ? ei : si;
}

int perfectLocation(vector<int> &arr, int data)
{
    int si = 0, ei = arr.size();

    while (si < ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] <= data)
            si = mid + 1;
        else
            ei = mid;
    }

    return ei; // si
}

bool searchMatrix(vector<vector<int>> &matrix, int target)
{
    int n = matrix.size(), m = matrix[0].size();
    int si = 0, ei = n * m - 1;

    while (si <= ei)
    {
        int mid = (si + ei) / 2, r = mid / m, c = mid % m;
        if (matrix[r][c] == target)
            return true;
        else if (matrix[r][c] < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return false;
}

// 658
vector<int> findClosestElements(vector<int> &arr, int k, int x)
{
    int n = arr.size();
    if (x <= arr[0])
        return {arr.begin(), arr.begin() + k};
    else if (x >= arr[n - 1])
        return {arr.end() - k, arr.end()};

    int idx = perfectLocation(arr, x);
    int si = max(0, idx - k);
    int ei = min(n - 1, idx + k);

    while ((ei - si + 1) > k)
    {
        if ((x - arr[si]) > (arr[ei] - x))
            si++;
        else
            ei--;
    }

    return {arr.begin() + si, arr.begin() + ei + 1};
}


// 1 ========
 vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int,int> map;
        
        for(int i=0; i<nums.size(); i++){
            if(map.find(target-nums[i])!=map.end()){
                return {i,map[target-nums[i]]};
            } else {
                map[nums[i]]=i;
            }
        }
        return {};
        
    }


// leet 4 O(m+n) ================================================================

double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int s1=nums1.size();
        int s2=nums2.size();
        int total=s1+s2;
        
        vector<int> marr(total,0);
        
        int i=0;
        int j=0;
        int k=0;
        
        while(i<s1 && j<s2){
            // comapre nums1 and nums2
            if(nums1[i]<=nums2[j]){
                marr[k]=nums1[i];
                i++;
            } else {
                marr[k]=nums2[j];
                j++;
            }
            k++;
        }
        
        while(i<s1){
            marr[k]=nums1[i];
            i++;
            k++;
        }
        
        while(j<s2){
            marr[k]=nums2[j];
            j++;
            k++;
        }
        
        // find median
        if(total%2==0){
            int sum=(marr[total/2]+marr[(total-1)/2]);
            return sum*1.0/2.0;
        } else {
            return marr[total/2];
        }
    }

    // leetcode 4 optimized ==========================

    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        if(nums1.size()>nums2.size()){
            // swap-> making nums1 smaller 
            return findMedianSortedArrays(nums2,nums1);
        }
        
        
        int s1=nums1.size();
        int s2=nums2.size();
        int total=s1+s2;
        
        int si=0;
        int ei=s1;
        
        while(si<=ei){
            int mid=(si+ei)/2;
            
            int bmid=((total+1)/2)-mid; // for even odd both;
            
            int aright=(mid==s1) ? (int)1e9 : nums1[mid];
            int aleft=(mid==0) ? -(int)1e9 : nums1[mid-1];
            
            int bright=(bmid==s2) ? (int)1e9 : nums2[bmid];
            int bleft=(bmid==0) ? -(int)1e9 : nums2[bmid-1];
            
            // checking if partition is okay
            if(bleft<=aright && aleft<=bright){
                // find median 
                if(total%2==0){
                    int sum=max(aleft,bleft)+min(bright,aright);
                    
                    return (sum*1.0)/2.0;
                } else {
                    return max(aleft,bleft);
                }
            }
            
            if(bleft>aright){
                si=mid+1;
            } else if(aleft>bright){
                ei=mid-1;
            }
        }
        
        return -1;
    }


    // leet 2064 ====================
    bool check(int mid, int n, vector<int>& q){
        for(int e:q){
            int stores=e/mid;
            
            if(e%mid!=0) stores++;
            n-=stores;
        }
        return n>=0;
    }
    
    int minimizedMaximum(int n, vector<int>& quantities) {
        int si=1;
        int ei=1;
        
        for(int e:quantities){
            ei=max(ei,e);
        }
        
        while(si<ei){
            int mid=(si+ei)/2;
            
            if(!check(mid,n,quantities)){
                si=mid+1;
            } else {
                ei=mid;
            }
        }
        
        return si;
    }


    // rotate array by k;
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
