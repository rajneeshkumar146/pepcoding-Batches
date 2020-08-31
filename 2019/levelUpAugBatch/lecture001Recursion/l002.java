import java.util.ArrayList;
import java.util.Arrays;

public class l002{

    public static ArrayList<String> mazePath_HVD(int sr,int sc,int er,int ec){
       if(sr == er && sc == ec){
           ArrayList<String> base = new ArrayList<>();
           base.add("");
           return base;
       } 
    
       ArrayList<String> myAns = new ArrayList<>();
       if(sc+1<=ec){
           ArrayList<String> Horizontal = mazePath_HVD(sr,sc+1,er,ec);
           for(String s : Horizontal)
               myAns.add("H" + s);
        }

       if(sr+1<=er){
        ArrayList<String> Vertical = mazePath_HVD(sr+1,sc,er,ec);
        for(String s : Vertical)
               myAns.add("V" + s);
       }

       if(sr+1<=er && sc + 1 <= ec){
        ArrayList<String> diagonal = mazePath_HVD(sr+1,sc+1,er,ec);
        for(String s : diagonal)
               myAns.add("D" + s);
       }

       return myAns;
    }

    public static int mazePath_HVD_01(int sr,int sc,int er,int ec,String ans){
        if(sr==er && sc==ec){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        if(sc+1<=ec)
          count += mazePath_HVD_01(sr,sc+1,er,ec,ans+"H");
        if(sr+1<=er)
          count += mazePath_HVD_01(sr+1,sc,er,ec,ans+"V");
        if(sc+1<=ec && sr+1<=er)
          count += mazePath_HVD_01(sr+1,sc+1,er,ec,ans+"D"); 
        
        return count;
    }

    public static int mazePath_HVD_jump(int sr,int sc,int er,int ec,String ans){
        if(sr==er && sc==ec){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int jump = 1;sc + jump <= ec; jump++)
          count += mazePath_HVD_jump(sr,sc+jump,er,ec,ans+"H" + jump);
        for(int jump = 1;sr + jump <= er; jump++)
          count += mazePath_HVD_jump(sr+jump,sc,er,ec,ans+"V" + jump);
        for(int jump = 1;sc+jump<=ec && sr+jump<=er; jump++)
          count += mazePath_HVD_jump(sr+jump,sc+jump,er,ec,ans+"D"+jump); 
        
        return count;
    }

    static int[] xMove = {2, 1, -1, -2, -2, -1, 1, 2}; 
    static int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1}; 
    
    public static boolean KnightTour(int[][] board,int sr,int sc,int steps){
      
      board[sr][sc] = steps;
      
      if(steps == 63){
        return true;
      }

      for(int d = 0 ; d < xMove.length;d++){
        int r = sr + xMove[d];
        int c = sc + yMove[d];

        if(r>=0 && c>=0 && r < 8 && c < 8 &&  board[r][c] == -1){
          boolean res = KnightTour(board,r,c,steps + 1);
          if(res) return true;
        }
      }

      board[sr][sc] = -1;
      return false;
    }

    public static void KnightTour(){
      int[][] board = new int[8][8];
      for(int[] a : board) Arrays.fill(a,-1);

      KnightTour(board,0,0,0);
      for(int[] a : board){
        for(int ele: a){
          System.out.print(ele + " ");
        }
        System.out.println();
      }
    }

    //CoinChange.==============================================================

    public static int coinChangePermutation_01(int[] coins,int tar,String ans){
      if(tar==0){
        System.out.println(ans);
        return 1;
      }
       
      int count = 0;
      for(int i = 0;i<coins.length;i++){
        if(tar - coins[i]>=0){
          count += coinChangePermutation_01(coins,tar-coins[i],ans  + coins[i]);
        }
      }

      return count;
    }

    public static int coinChangeCombination_01(int[] coins,int idx,int tar,String ans){
      if(tar==0){
        System.out.println(ans);
        return 1;
      }
       
      int count = 0;
      for(int i = idx;i<coins.length;i++){
        if(tar - coins[i]>=0){
          count += coinChangeCombination_01(coins,i,tar-coins[i],ans  + coins[i]);
        }
      }

      return count;
    }

    public static int coinChangeCombinationSingle_01(int[] coins,int idx,int tar,String ans){
      if(tar==0){
        System.out.println(ans);
        return 1;
      }
       
      int count = 0;
      for(int i = idx;i<coins.length;i++){
        if(tar - coins[i]>=0){
          count += coinChangeCombinationSingle_01(coins,i+1,tar-coins[i],ans  + coins[i]);
        }
      }

      return count;
    }

    public static int coinChangePermutationSingle_01(int[] coins,int tar,boolean[] vis,String ans){
      if(tar==0){
        System.out.println(ans);
        return 1;
      }
       
      int count = 0;
      for(int i = 0;i<coins.length;i++){
        if(!vis[i] && tar - coins[i]>=0){
          vis[i] = true;
          count += coinChangePermutationSingle_01(coins,tar-coins[i],vis,ans  + coins[i]);
          vis[i] = false;
        }
      }

      return count;
    }

    public static int coinChangeCombinationSingleSubseq_01(int[] coins,int idx,int tar,String ans){
      if(tar==0 || idx >= coins.length){
        if(tar==0){
          System.out.println(ans);
          return 1;
        }
        return 0;
      }
       
      int count = 0;
      if(tar - coins[idx]>=0)
        count += coinChangeCombinationSingleSubseq_01(coins,idx+1,tar-coins[idx],ans  + coins[idx]);
      
      count += coinChangeCombinationSingleSubseq_01(coins,idx+1,tar,ans);
  
      return count;
    }

    public static int coinChangeCombinationSubseq_01(int[] coins,int idx,int tar,String ans){
      if(tar==0 || idx >= coins.length){
        if(tar==0){
          System.out.println(ans);
          return 1;
        }
        return 0;
      }
       
      int count = 0;
      if(tar - coins[idx]>=0)
        count += coinChangeCombinationSubseq_01(coins,idx,tar-coins[idx],ans  + coins[idx]);
      
      count += coinChangeCombinationSubseq_01(coins,idx+1,tar,ans);
  
      return count;
    }

    public static int coinChangePermutationSubSeq_01(int[] coins,int idx,int tar,String ans){
      if(tar==0 || idx >= coins.length){
        if(tar==0){
          System.out.println(ans);
          return 1;
        }
        return 0;
      }
       
      int count = 0;
      if(tar - coins[idx]>=0)
        count += coinChangePermutationSubSeq_01(coins,idx+1,tar-coins[idx],ans  + coins[idx]);
      
      count += coinChangePermutationSubSeq_01(coins,idx+1,tar,ans);
  
      return count;
    }

    public static int coinChangePermutationSingleSubSeq_01(int[] coins,int idx,int tar,boolean[] vis,String ans){
      if(tar==0 || idx >= coins.length){
        if(tar==0){
          System.out.println(ans);
          return 1;
        }
        return 0;
      }
       
      int count = 0;
      if(!vis[idx] && tar - coins[idx]>=0){
        vis[idx] = true;
        count += coinChangePermutationSingleSubSeq_01(coins,idx+1,tar-coins[idx],ans  + coins[idx]);
        vis[idx] = false;
      }
      count += coinChangePermutationSingleSubSeq_01(coins,idx+1,tar,ans);
  
      return count;
    }





    public static void coinChange(){
      int[] coins = {2,3,5,7};
      int tar = 10;
      boolean[] vis = new boolean[4];
      // System.out.println(coinChangePermutation_01(coins,tar,""));
      System.out.println(coinChangeCombination_01(coins,0,tar,""));
      // System.out.println(coinChangeCombinationSingle_01(coins,0,tar,""));
      // System.out.println(coinChangePermutationSingle_01(coins,tar,vis,""));
     

      System.out.println("=========================");

      // System.out.println(coinChangeCombinationSingleSubseq_01(coins,0,tar,""));
      System.out.println(coinChangeCombinationSubseq_01(coins,0,tar,""));
    }
    
    public static void pathSet(){
        // System.out.println(mazePath_HVD(0,0,2,2));
        // System.out.println(mazePath_HVD_01(0,0,2,2,""));
        System.out.println(mazePath_HVD_jump(0,0,2,2,""));
    }

    public static void solve(){
        // pathSet();
        // KnightTour();
        coinChange();
    }

    public static void main(String[] args){
        solve();
    }

    

}