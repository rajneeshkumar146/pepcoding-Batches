public class question{

    //leetcode: 39.====================================================

    public void combinationSum(int[] arr,int idx, int target,List<List<Integer>> res, List<Integer> ans) {
        if(target==0){
            List<Integer> base=new ArrayList<>(ans);
            res.add(base);
            return;
        }

        for(int i=idx;i<arr.length;i++){
            if(target-arr[i] < 0) break;

            ans.add(arr[i]);
            combinationSum(arr,i,target-arr[i],res,ans);
            ans.remove(ans.size()-1);
        }
    }

    public List<List<Integer>> combinationSum(int[] arr, int target) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(arr); 
        combinationSum(arr,0,target,res,ans);
        return res;
    }

    //Leetcode 40.===============================================================

    public void combinationSum2(int[] arr,int idx, int target,List<List<Integer>> res, List<Integer> ans) {
        if(target==0){
            List<Integer> base=new ArrayList<>(ans);
            res.add(base);
            return;
        }

        int prev=-1;
        for(int i=idx;i<arr.length;i++){
            if(target-arr[i] < 0) break;
            if(prev==arr[i]) continue;
            prev=arr[i];
            
            ans.add(arr[i]);
            combinationSum2(arr,i+1,target-arr[i],res,ans);
            ans.remove(ans.size()-1);
        }
    }



    public List<List<Integer>> combinationSum2(int[] arr, int target) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(arr); 
        combinationSum2(arr,0,target,res,ans);
        return res;
    }

}