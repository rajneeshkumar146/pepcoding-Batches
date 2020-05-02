import java.util.HashMap;
import java.util.ArrayList;
public class Questionhashmap{



    //leetcode 349
    public int[] intersection(int[] one, int[] two) {
        HashMap<Integer,Boolean> map=new HashMap<>();
        
        ArrayList<Integer> ans=new ArrayLIst<>();
        for(int ele: one){
            map.put(ele,false);
        }

        for(int ele: two){
          if(map.containsKey(ele)){
              ans.add(ele);
              map.remove(ele);
          }
        }

        int[] res=new int[arr.size()];
        for(int i=0;i<res.length;i++){
            res[i]=ans.get(i);
        }
        return res;
   }

   public int[] intersect(int[] one, int[] two) {
    HashMap<Integer,Integer> map=new HashMap<>();
    
    ArrayList<Integer> ans=new ArrayLIst<>();
    for(int ele: one){
        map.put(ele,map.getOrDefault(ele,0)+1);
    }

    for(int ele: two){
      if(map.containsKey(ele)){
        if(map.get(ele)>0){  
           ans.add(ele);
           map.put(ele,map.get(ele)-1);
        }

        if(map.get(ele)==0)
          map.remove(ele);
      }
    }

    int[] res=new int[arr.size()];
    for(int i=0;i<res.length;i++){
        res[i]=ans.get(i);
    }
    return res;
}

//leetcode 128.======================================
public int longestConsecutive(int[] nums) {
    HashMap<Integer,Integer> map=new HashMap<>();
    
    for(int ele: nums) map.put(ele,1);
    int len=0;
    
    for(int ele: nums){
        if(!map.containsKey(ele)) continue; 
        
        map.remove(ele);
        int prevEle = ele - 1;
        int nextEle = ele + 1;

        while(map.containsKey(prevEle)) map.remove(prevEle--);
        while(map.containsKey(nextEle)) map.remove(nextEle++);

        len=Math.max(len,nextEle - prevEle -1);
    }

    return len; 
}

public int longestArithSeqLength(int[] arr) {
    int n=arr.length;
    int len=0;
    HashMap<Integer,Integer>[] dp=new HashMap[n];
    for(int i=0;i<n;i++) dp[i]=new HashMap<>();

    for(int i=0;i<n;i++){
        for(int j=0;j<i;j++){
            int diff=arr[i]-arr[j];
            dp[i].put(diff,map.getOrDefault(diff,1) + 1);
            
            len=Math.max(len,dp[i].get(diff));
        }
    }
    return len;
}