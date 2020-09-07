import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

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
        count += coinChangePermutationSubSeq_01(coins,0,tar-coins[idx],ans  + coins[idx]);
      
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
        count += coinChangePermutationSingleSubSeq_01(coins,0,tar-coins[idx],vis,ans  + coins[idx]);
        vis[idx] = false;
      }
      count += coinChangePermutationSingleSubSeq_01(coins,idx+1,tar,vis,ans);
  
      return count;
    }

    //QueenCombinationAndPermutation.====================================================
    
    public static int queenCombination(boolean[] box,int idx,int qpsf,int tnq,String ans){
      if(qpsf == tnq){
        System.out.println(ans);
        return 1;
      }
      int count=0;
      for(int i = idx ; i < box.length;i++){
        count+=queenCombination(box,i + 1,qpsf+1,tnq,ans +"b"+ i + "Q"+qpsf + " ");
      }
      return count;
    }

    public static int queenPermutation(boolean[] box,int qpsf,int tnq,String ans){
      if(qpsf == tnq){
        System.out.println(ans);
        return 1;
      }

      int count=0;
      for(int i = 0 ; i < box.length;i++){
        if(!box[i]){
             box[i] = true;
             count+=queenPermutation(box,qpsf+1,tnq,ans +"b"+ i + "Q"+qpsf + " ");
             box[i] = false;
        }
     }
      return count;
    }

    
    public static int queenCombination2D(boolean[][] box,int idx,int qpsf,int tnq,String ans){
      if(qpsf == tnq){
        System.out.println(ans);
        return 1;
      }
      int count=0;
      int n = box.length;
      for(int i = idx ; i < n*n; i++){
        int r = i / n;
        int c = i % n;
        count+=queenCombination2D(box,i + 1,qpsf+1,tnq,ans +"("+ r + ","+c + ") ");
      }
      return count;
    }

    public static int queenPermutation2D(boolean[][] box,int qpsf,int tnq,String ans){
      if(qpsf == tnq){
        System.out.println(ans);
        return 1;
      }

      int count=0;
      int n = box.length;
      for(int i = 0 ; i < n*n; i++){
        int r = i / n;
        int c = i % n;
        if(!box[r][c]){
             box[r][c] = true;
             count+=queenPermutation2D(box,qpsf+1,tnq,ans +"("+ r + ","+c + ") ");
             box[r][c] = false;
        }
     }
      return count;
    }

    //Nqueen.=========================================================
    
    public static boolean isSafeToPlaceQueen(boolean[][] box,int r,int c){

      int[][] dirA = {{0,-1},{-1,0},{-1,-1},{-1,1}};
      // int[][] dirA = {{0,-1},{-1,0},{-1,-1},{-1,1},{0,1},{1,0},{1,1},{1,-1}};
    
      for(int d = 0; d < dirA.length;d++){
        for(int rad = 1 ; rad<=box.length;rad++){
          int x = r + rad*dirA[d][0];
          int y = c + rad*dirA[d][1];

          if(x >= 0 && y >= 0 && x < box.length && y < box[0].length){
            if(box[x][y]) return false;
          }else break;
        }
      }

      return true;
    }
 
    //Combination
    public static int Nqueen_01(boolean[][] box,int idx,int qpsf,int tnq,String ans){
      if(qpsf == tnq){
        System.out.println(ans);
        return 1;
      }
      int count=0;
      int n = box.length;
      for(int i = idx ; i < n*n; i++){
        int r = i / n;
        int c = i % n;
        
        if(isSafeToPlaceQueen(box, r, c)){
          box[r][c] = true;
          count+=Nqueen_01(box,i + 1,qpsf+1,tnq,ans +"("+ r + ","+c + ") ");
          box[r][c] = false;  
        }
      }
      return count;
    }
 
    // permutation
    public static int Nqueen_02(boolean[][] box,int qpsf,int tnq,String ans){
      if(qpsf == tnq){
        System.out.println(ans);
        return 1;
      }
      int count=0;
      int n = box.length;
      for(int i = 0 ; i < n*n; i++){
        int r = i / n;
        int c = i % n;
        
        if(!box[r][c] && isSafeToPlaceQueen(box, r, c)){
          box[r][c] = true;
          count+=Nqueen_02(box,qpsf+1,tnq,ans +"("+ r + ","+c + ") ");
          box[r][c] = false;  
        }
      }
      return count;
    }

    static int calls = 0;
    static boolean[] rowA,colA,diagA,adiagA;
    public static int Nqueen_03(int n,int idx,int qpsf,int tnq,String ans){
      if(qpsf == tnq){
        System.out.println(ans);
        return 1;
      }
      int count=0;
      calls++;
      for(int i = idx ; i < n*n; i++){
        int r = i / n;
        int c = i % n;
        
        if(!rowA[r] && !colA[c] && !diagA[r+c] && !adiagA[r-c+n-1]){

          rowA[r] = !rowA[r];
          colA[c] = !colA[c];
          diagA[r+c] = !diagA[r+c];
          adiagA[r-c+n-1] = !adiagA[r-c+n-1];

          count+=Nqueen_03(n,i + 1,qpsf+1,tnq,ans +"("+ r + ","+c + ") ");

          rowA[r] = !rowA[r];
          colA[c] = !colA[c];
          diagA[r+c] = !diagA[r+c];
          adiagA[r-c+n-1] = !adiagA[r-c+n-1];

        }
      }
      return count;
    }

    public static int Nqueen_04(int n,int r,int tnq,String ans){
      if(tnq == 0){
        System.out.println(ans);
        return 1;
      }

      int count=0;
      calls++;
      for(int c = 0; c < n; c++){
        if(!colA[c] && !diagA[r+c] && !adiagA[r-c+n-1]){

          colA[c] = !colA[c];
          diagA[r+c] = !diagA[r+c];
          adiagA[r-c+n-1] = !adiagA[r-c+n-1];

          count+=Nqueen_04(n,r + 1,tnq-1,ans +"("+ r + ","+c + ") ");

          colA[c] = !colA[c];
          diagA[r+c] = !diagA[r+c];
          adiagA[r-c+n-1] = !adiagA[r-c+n-1];
        }
      }

      return count;
    }

    static int colN = 0, diagN = 0, adiagN = 0 ;
    public static int Nqueen_04_Bits(int n,int r,int tnq,String ans){
      if(tnq == 0){
        System.out.println(ans);
        return 1;
      }

      int count=0;
      for(int c = 0; c < n; c++){
        if( (colN & (1<<c)) == 0 && (diagN & (1<<(r+c))) == 0 && (adiagN & (1 << (r-c+n-1) )) == 0  ){

          colN ^= (1 << c);
          diagN ^= (1 << (r+c));
          adiagN ^= (1 << (r-c+n-1));

          count+=Nqueen_04_Bits(n,r + 1,tnq-1,ans +"("+ r + ","+c + ") ");

          colN ^= (1 << c);
          diagN ^= (1 << (r+c));
          adiagN ^= (1 << (r-c+n-1));

        }
      }

      return count;
    }



    public static int Nqueen_04_subseq(int n,int r,int tnq,String ans){
      if(tnq == 0 || r >= n){
        if(tnq == 0){
          System.out.println(ans);
          return 1;
        }
        return 0;
      }

      int count=0;
      for(int c = 0; c < n;c++){
       if(!colA[c] && !diagA[r+c] && !adiagA[r-c+n-1]){
          colA[c] = !colA[c];
          diagA[r+c] = !diagA[r+c];
          adiagA[r-c+n-1] = !adiagA[r-c+n-1];

          count+=Nqueen_04_subseq(n,r + 1,tnq-1,ans +"("+ r + ","+c + ") ");

          colA[c] = !colA[c];
          diagA[r+c] = !diagA[r+c];
          adiagA[r-c+n-1] = !adiagA[r-c+n-1];
      }
    }

    count+=Nqueen_04_subseq(n,r + 1,tnq,ans);

      return count;

    }

    //WordBreak.====================================================================

    public static int wordBreak(String ques,int idx,String ans,HashSet<String> words,int len){
      if(idx == ques.length()){
        System.out.println(ans);
        return 1;
      }


      int count=0;
      for(int i= idx; i - idx <= len &&  i <= ques.length();i++){
        String s = ques.substring(idx,i);
        if(words.contains(s)){
          count += wordBreak(ques,i,ans + s + " ", words,len); 
        }  
      }
      return count;
    }

    
    public static int wordBreak_02(String ques,String ans,HashSet<String> words,int len){
      if(ques.length()==0){
        System.out.println(ans);
        return 1;
      }


      int count=0;
      for(int i= 0; i <= len &&  i <= ques.length();i++){
        String s = ques.substring(0,i);
        if(words.contains(s)){
          count += wordBreak_02(ques.substring(i),ans + s + " ", words,len); 
        }  
      }
      return count;
    }
   
    public static void wordBreak(){
      String dictionary[] = {"mobile","samsung","sam","sung",  
                            "man","mango","icecream","and",  
                            "go","i","like","ice","cream","ilikesamsung"}; 
      HashSet<String> words=new HashSet<>();
      int len = 0;
      for(String str: dictionary){
        words.add(str);
        len = Math.max(len,str.length());
      }

      String ques = "ilikesamsungandmango";
      System.out.println(wordBreak(ques,0,"",words,len));
    }


    public static void nQueen(){
      int k = 10;
      boolean[][] box = new boolean[k][k];
      int tnq = 4;
      int n = box.length;
      int m = box[0].length;
      

      // System.out.println(Nqueen_01(box,0,0,tnq,""));
      // System.out.println(Nqueen_02(box,0,tnq,""));
    
      rowA = new boolean[n];
      colA = new boolean[m];
      diagA = new boolean[n+m-1];
      adiagA = new boolean[n+m-1];
      // System.out.println(Nqueen_03(n,0,0,tnq,""));
      // System.out.println(Nqueen_04(n,0,tnq,""));
      // System.out.println(Nqueen_04_Bits(n,0,tnq,""));

      // System.out.println(calls);
       
      System.out.println(Nqueen_04_subseq(n,0,tnq,""));
    }






    // queen combination and permutation
    public static void QueenCAP(){
      // boolean[] box = new boolean[6];
      // int tnq = 3;
      // System.out.println(queenCombination(box,0,0,tnq,""));
      // System.out.println(queenPermutation(box,0,tnq,""));
    
      boolean[][] box = new boolean[4][4];
      int tnq = 4;
      System.out.println(queenCombination2D(box,0,0,tnq,""));
      // System.out.println(queenPermutation2D(box,0,tnq,""));
    
    
    }

    public static void coinChange(){
      int[] coins = {2,3,5,7};
      int tar = 10;
      
      boolean[] vis = new boolean[4];
      // System.out.println(coinChangePermutation_01(coins,tar,""));
      // System.out.println(coinChangeCombination_01(coins,0,tar,""));
      System.out.println(coinChangeCombinationSingle_01(coins,0,tar,""));
      // System.out.println(coinChangePermutationSingle_01(coins,tar,vis,""));
     

      System.out.println("=========================");

      // System.out.println(coinChangeCombinationSingleSubseq_01(coins,0,tar,""));
      // System.out.println(coinChangeCombinationSubseq_01(coins,0,tar,""));
      // System.out.println(coinChangePermutationSubSeq_01(coins,0,tar,""));
      // System.out.println(coinChangePermutationSingleSubSeq_01(coins,tar,vis,""));
      
    
    }
    
    public static void pathSet(){
        // System.out.println(mazePath_HVD(0,0,2,2));
        // System.out.println(mazePath_HVD_01(0,0,2,2,""));
        System.out.println(mazePath_HVD_jump(0,0,2,2,""));
    }

    public static void solve(){
        // pathSet();
        // KnightTour();
        // coinChange();
        // QueenCAP();
        nQueen();
        // wordBreak();
    }

    public static void main(String[] args){
        solve();
    }

    

}