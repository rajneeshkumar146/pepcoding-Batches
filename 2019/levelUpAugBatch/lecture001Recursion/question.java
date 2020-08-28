import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;

public class question{
    
    List<List<Integer>> res = new ArrayList<>(); 
    List<Integer> smallAns = new ArrayList<>();

    public void permuteUnique(int[] nums,int count,boolean[] vis) {
        if(count == nums.length){
            ArrayList<Integer> smallRes = new ArrayList<>(smallAns);
            res.add(smallRes);
            return;
        }
       
        HashSet<Integer> isUsed = new HashSet<>();

        for(int i=0;i<nums.length;i++){
            if(!vis[i] && !isUsed.contains(nums[i])){
                
                isUsed.add(nums[i]);
                vis[i] = true;
                smallAns.add(nums[i]);

                permuteUnique(nums,count+1,vis);
                
                smallAns.remove(smallAns.size()-1);
                vis[i] = false;
            }
        }
    }

    public void permuteUnique_02(int[] nums,int count,boolean[] vis) {
        if(count == nums.length){
            ArrayList<Integer> smallRes = new ArrayList<>(smallAns);
            res.add(smallRes);
            return;
        }
       
        int prev = 1e8;
        for(int i=0;i<nums.length;i++){
            if(!vis[i] && prev != nums[i]){
                
                vis[i] = true;
                smallAns.add(nums[i]);

                permuteUnique_02(nums,count+1,vis);
                
                smallAns.remove(smallAns.size()-1);
                vis[i] = false;

                prev = nums[i]
            }
        }
    }
    
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] vis = new boolean[nums.length];
        permuteUnique(nums,0,vis);
        return res;
    }
}