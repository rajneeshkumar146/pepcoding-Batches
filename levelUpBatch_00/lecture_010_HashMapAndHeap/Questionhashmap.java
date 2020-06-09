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

//Leetcode: 49 group anagram.===========================================

public List<List<String>> groupAnagrams(String[] strs) {
    HashMap<String,ArrayList<String>> map=new HashMap<>();
    for(int i=0;i<str.length;i++){
         String s=strs[i];
         int[] freq=new int[26];
         for(int i=0;i<s.length();i++)
            freq[s.charAt(i)-'a']++;
        
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<26;i++)
          if(freq[i]!=0) sb.append((char)(i+'a')+(freq[i]+""));
    
        String RLES=sb.toString();
        map.putIfAbsent(RLES,new ArrayList<>());
        map.get(RLES).add(s);
     }

     List<List<String>> ans=new ArrayList<>();
     for(String code: map.keySet()){
         ans.add(map.get(code));
     }

     return ans;
}

public int swimInWater(int[][] grid) {
    int n=grid.length;
    int ans=Math.max(grid[0][0],grid[n-1][n-1]);

    PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->{  // r,c,h
        return a[2]-b[2];
    });

     boolean[][] vis=new boolean[n][n];
     vis[0][0]=true;

     int[][] dir={{-1,0},{0,-1},{1,0},{0,1}};
     pq.add(new int[]{0,0,grid[0][0]}); 

     while(pq.size()!=0){
         int[] vtx=pq.poll();
         ans=Math.max(ans,vtx[2]);

         for(int d=0;d<4;d++){
             int x = vtx[0] + dir[d][0];
             int y = vtx[1] + dir[d][1];

             if(x>=0 && y>=0 && x < n && y < n && !vis[x][y]){
                if(x == n-1 && y == n-1) return ans;
                pq.add(new int[]{x,y,grid[x][y]});
                vis[x][y]=true;
             }
         }
     }

     return -1;
}