import java.util.*;

public class l001 {

    public static long totalInversionCount(long[] arr, long[] sortedArray, long si, long mid, long ei) {
        int i = (int) si, j = (int) mid + 1, k = (int) si;
        long count = 0;

        while (i <= mid && j <= ei) {
            if (arr[i] <= arr[j])
                sortedArray[k++] = arr[i++];
            else {
                sortedArray[k++] = arr[j++];
                count += mid - i + 1;
            }
        }

        while (i <= mid || j <= ei)
            sortedArray[k++] = arr[i <= mid ? i++ : j++];

        while (si <= ei)
            arr[(int) si] = sortedArray[(int) si++];

        return count;
    }

    public static long inversionCount(long[] arr, long[] sortedArray, long si, long ei) {
        if (si >= ei)
            return 0;

        long mid = (si + ei) / 2;
        long count = 0;

        count += inversionCount(arr, sortedArray, si, mid);
        count += inversionCount(arr, sortedArray, mid + 1, ei);

        count += totalInversionCount(arr, sortedArray, si, mid, ei);
        return count;
    }

    public static long inversionCount(long arr[], long N) {
        if (N == 0)
            return 0;

        long[] sortedArray = new long[(int) N];
        return inversionCount(arr, sortedArray, 0, N - 1);
    }

    // two sum O(n) space
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();
        
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(target-nums[i])){
                return new int[]{i,map.get(target-nums[i])};
            } else {
                map.put(nums[i],i);
            }
        }
        return new int[0];
        
    }

    // two sum O(1) space // leet 167 ================================================== 
    public int[] twoSum_O1(int[] nums, int target){
        Arrays.sort(nums);

        int si=0;
        int ei=nums.length-1;

        while(si<ei){
            int csum=nums[si]+nums[ei];

            if(csum==target){
                return new int[]{nums[si],nums[ei]};
            } else if(csum<target){
                si++;
            } else {
                ei--;
            }
        }

        return new int[]{};
    }

    public List<List<Integer>> allPairs(int[] nums, int tar, int si, int ei){
        List<List<Integer>> ans=new ArrayList<>();

        Arrays.sort(nums);

        // int si=0;
        // int ei=nums.length-1;

        while(si<ei){
            int csum=nums[si]+nums[ei];

            if(csum==tar){
                ans.add(Arrays.asList(nums[si],nums[ei]));

                si++;
                ei--;
                // 3 options 
                while(si<ei && nums[si]==nums[si-1]) si++;
                while(si<ei && nums[ei]==nums[ei+1]) ei--;
            } else if(csum<tar){
                si++;
            } else {
                ei--;
            }
        }

        return ans;
    }

    // three sum / leet 15 
    List<List<Integer>> 3sum(int[] nums, int si, int ei, int target){
        List<List<Integer>> ans = new ArrayList<>();

        for(int i=si; i<ei;){
            int fix=nums[i];
            
            // 2 sum
            List<List<Integer>> smallAns=allPairs(nums,target-fix,i+1,ei);

            makeAns(ans,smallAns,fix);

            i++;
            while(i<ei && nums[i]==nums[i-1]) i++;
        }

        return ans;
    }

    public void makeAns(List<List<Integer>> ans, List<List<Integer>> smallAns, int fix){
        for(List<Integer> s:smallAns){
            s.add(fix);
            ans.add(s);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return 3sum(nums,0,nums.length-1,0);
    }

    List<List<Integer>> 3sum(int[] nums, int si, int ei, int target){
        List<List<Integer>> ans = new ArrayList<>();

        for(int i=si; i<ei;){
            int fix=nums[i];
            
            // 2 sum
            List<List<Integer>> smallAns=allPairs(nums,i+1,ei,target-fix);

            makeAns(ans,smallAns,fix);

            i++;
            while(i<ei && nums[i]==nums[i-1]) i++;
        }

        return ans;
    }


    // 4 sum / leet 18 

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans=new ArrayList<>();

        for(int i=0; i<nums.length; i++){
            int fix=nums[i];

            // 3 sum call
            List<Integer> smallAns = 3sum(nums,i+1,ei,target-fix);

            makeAns(ans,smallAns,fix);

            i++;

            // to remove duplicates
            while(i<ei && nums[i]==nums[i-1]) i++;
        }

        return ans;
    }
    // generic KSum ====================

    public List<List<Integer>> KSum(int[] nums, int target, int k, int si, int ei) {
        if(k==2){
            return allPairs(nums,target,si,ei);
        }

        List<List<Integer>> ans=new ArrayList<>();

        for(int i=si; i<ei; i++){
            int fix=nums[i];

            // 3 sum call
            List<Integer> smallAns = KSum(nums,target-fix,k-1, i+1, ei);

            makeAns(ans,smallAns,fix);

            i++;

            // to remove duplicates
            while(i<ei && nums[i]==nums[i-1]) i++;
        }

        return ans;
    }
    

    // two sum count ====================================


    public int count(int[] arr1, int[] arr2, int target){
        int ans=0;

        HashMap<Integer,Integer> map=new HashMap<>();

        for(int i=0; i<arr1.length; i++){
            map.put(arr1[i],map.getOrDefault(arr1[i],0)+1);
        }

        for(int i=0; i<arr2.length; i++){
            int new_target=target-arr2[i];

            if(map.containsKey(new_target)){
                ans+=map.get(new_target);
            }
        }

        return ans;
    }

    // leet 454 

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer,Integer> map=new HashMap<>();
        
        for(int e:nums1){
            for(int f:nums2){
                map.put(e+f,map.getOrDefault(e+f,0)+1);
            }
        }
        
        int ans=0;
        for(int e:nums3){
            for(int f:nums4){
                if(map.containsKey(0-(e+f))){
                    ans+=map.get(0-(e+f));
                }
            }
        }
        
        return ans;
    }

    // leet 33 ============================================

    public int search(int[] nums, int target) {
        int si=0;
        int ei=nums.length-1;
        
        while(si<=ei){
            int mid=si+(ei-si)/2;
            
            if(nums[mid]==target) return mid;
            
            if(si==mid || nums[si]<nums[mid]){ // from si to mid, nums is sorted
                if(nums[si]<=target && nums[mid]>target){
                    ei=mid-1;
                } else {
                    si=mid+1;
                }
            } else {
                if(nums[mid]<target && nums[ei]>=target){
                    si=mid+1;
                } else {
                    ei=mid-1;
                }
            }
        }
        
        return -1;
    }

    // leet 81 ================
    public boolean search(int[] nums, int target) {
        int si=0;
        int ei=nums.length-1;
        
        
        while(si<=ei){
            int mid=(si+ei)/2;
            
            if(nums[mid]==target || nums[si]==target) return true;
            
            if(nums[si]<nums[mid]){
                if(nums[si]<=target && target<nums[mid]){
                    ei=mid-1;
                } else {
                    si=mid+1;
                }
            } else if(nums[mid]<nums[ei]){
                if(nums[mid]<target && target<=nums[ei]){
                    si=mid+1;
                } else {
                    ei=mid-1;
                }
            } else {
                si++;
            }
        }
        
        return false;
    }
// leetcode 4 ================ O(n+m) 
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int s1=nums1.length;
        int s2=nums2.length;
        int total=s1+s2;
        
        int[] marr=new int[total];
        
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


// leetcode 4 optimized =====================

 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length){
            // swap-> making nums1 smaller 
            return findMedianSortedArrays(nums2,nums1);
        }
        
        
        int s1=nums1.length;
        int s2=nums2.length;
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
                    int sum=Math.max(aleft,bleft)+Math.min(bright,aright);
                    
                    return (sum*1.0)/2.0;
                } else {
                    return Math.max(aleft,bleft);
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

    // leet 2064 ===============================================================
    public boolean check(int mid, int n, int[] q){
        for(int e:q){
            int stores=e/mid;
            
            if(e%mid!=0) stores++;
            n-=stores;
        }
        return n>=0;
    }
    
    public int minimizedMaximum(int n, int[] quantities) {
        int si=1;
        int ei=1;
        
        for(int e:quantities){
            ei=Math.max(ei,e);
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


    // leetcode 75 (segregate 0's, 1's and 2's)
     void sortColors(int[] nums) {
        int p1=-1;
        int p2=0;
        int n=nums.length;
        int p3=n-1;
        
        
        while(p2<=p3){
            if(nums[p2]==0){
                // swap(nums[++p1],nums[p2++]);
                p1++;
                //swap
                int t=nums[p1];
                nums[p1]=nums[p2];
                nums[p2]=t;

                p2++;
            } else if(nums[p2]==2) {
                // swap(nums[p3--],nums[p2]);
                int t=nums[p3];
                nums[p3]=nums[p2];
                nums[p2]=t;

                p3--;
            } else {
                p2++;
            }
        }
    }

    // https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1#

    int max_sum(int A[], int n)
    {
	    int total_sum=0;
	    
	    for(int e:A){
	        total_sum+=e;
	    }
	    
	    int csum=0;
	    
	    for(int i=0; i<n; i++){
	        csum+=A[i]*i;
	    }
	    
	    int max=csum;
	    
	    for(int i=1; i<n; i++){
	        int nsum=csum-(total_sum)+n*A[i-1];
	        csum=nsum;
	        
	        max=Math.max(max,csum);
	    }
	    
	    return max;
    }

    // leet 11 ============================================

    public int maxArea(int[] height) {
     int n=height.length;
        
        int p1=0;
        int p2=n-1;
        
        int max_area=0;
        
        while(p1<p2){
            int len=Math.min(height[p1],height[p2]);
            int width=p2-p1;
            
            int curr_area=len*width;
            
            max_area=Math.max(curr_area,max_area);
            
            if(height[p1]<=height[p2]){
                p1++;
            } else {
                p2--;
            }
        }
        
        return max_area;
    }


}