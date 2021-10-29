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
            List<List<Integer>> smallAns=3sum(nums,i+1,ei,target-fix);

            makeAns(ans,smallAns,fix);

            i++;
            while(i<ei && nums[i]==nums[i-1]) i++;
        }

        return ans;
    }
}