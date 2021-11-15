class l001 {
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

    // leetcode 03=====================================

     public int lengthOfLongestSubstring(String s) {
        int si=0;
        int ei=0;
        int count=0;
        
        int n=s.length();
         
         int gsi=0;
         int gei=0;
         
        int len=0;
        
        int[] fre=new int[128];// vector<int> fre(128,0);
        
        while(ei<n){
            if(fre[s.charAt(ei)]==1){
                count++;
            }
            
            fre[s.charAt(ei)]++;
            ei++;
            
            while(count>0){
                if(fre[s.charAt(si)]==2){
                    count--;
                }
                
                fre[s.charAt(si)]--;
                si++;
            }
            
            if(len<(ei-si)){
                len=ei-si;
                gsi=si;
                gei=ei;
            }
        }
        
         
        return len;
    }

    // lintcode 928 =======================================

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // Write your code here
        int si=0;
        int ei=0;
        int count=0;
        int len=0;

        int n=s.size();
        int[] fre=new int[128];//vector<int> fre(128,0);

        while(ei<n){
            if(fre[s.charAt(ei)]==0){
                count++;
            }

            fre[s.charAt(ei)]++;
            ei++;

            while(count>2){
                if(fre[s.charAt(si)]==1){
                    count--;
                }
                fre[s.charAt(si)]--;
                si++;
            }

            len=Math.max(len,ei-si);
        }

        return len;
    }


    // lintcode 386 ===================================

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int si=0;
        int ei=0;
        int count=0;
        int len=0;

        int n=s.length();
        int[] fre=new int[128];//vector<int> fre(128,0);

        while(ei<n){
            if(fre[s.charAt(ei)]==0){
                count++;
            }

            fre[s.charAt(ei)]++;
            ei++;

            while(count>k){
                if(fre[s.charAt(si)]==1){
                    count--;
                }
                fre[s.charAt(si)]--;
                si++;
            }

            len=Math.max(len,ei-si);
        }

        return len;
    }

    // leet 1456

     public boolean isVowel(char c){
        return c=='e' || c=='a' || c=='i' || c=='o' || c=='u';
        
    }
    public int maxVowels(String s, int k) {
        int[] fre=new int[128];
        
        int si=0;
        int ei=0;
        int vowelCount=0;
        
        while(ei<k){
            if(isVowel(s.charAt(ei))){
                vowelCount++;
            }
            fre[s.charAt(ei)]++;
            ei++;
        }
        
        int ans=vowelCount;
        
        while(ei<s.length()){
            ans=Math.max(ans,vowelCount);
            
            // ei increase
            if(isVowel(s.charAt(ei))){
                vowelCount++;
            }
            fre[s.charAt(ei)]++;
            ei++;
            
            // si increase
            if(isVowel(s.charAt(si))){
                vowelCount--;
            }
            fre[s.charAt(si)]--;
            si++;
        }
        
        ans=Math.max(ans,vowelCount);
        return ans;
        
    }
    
}

