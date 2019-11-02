import java.util.Scanner;
import java.util.ArrayList;
public class l003_returnType {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        // String str=scn.nextLine();

        //    String str="ABCD";
        //    System.out.println(subseq(str));
        //    System.out.println(removeHi(str));
        // System.out.println(compression("aaaaabbbbbccccdcccefghh",1));

        // System.out.println(mazePath(0,0,4,5));
        // System.out.println(mazePath_diag(0,0,2,2));
        // System.out.println(mazePath_diag_hei(0,0,4,6));
        // System.out.println(mazePath_diag_Minhei(0,0,2,2));
        // System.out.println(mazePath_diag_multi(0, 0, 3, 3));

        // System.out.println(floodFill(0,0,2,2,new boolean[3][3]));
        // boolean[][] isdone={{false,false,false},
        //                   {false,false,true},
        //                   {false,false,false},
        //                  };

        // System.out.println(floodFill_eightCalls(0,0,2,2,new boolean[3][3]));
   
        // System.out.println(knightPath(0,0,2,2,new boolean[3][3],"(0, 0), "));
        // System.out.println(knightPath_(0,0,0,64,new boolean[8][8],new int[8][8]));
    
        // String[] keys={".","abc","def","ghi","jkl","mno","pqrs","tu","vwx","yz"};
        // System.out.println(keyPad_01("245",keys));
        
        // System.out.println(permuatation("aba"));

        // int[][] board = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
        // {0, 0, 0, 0, 0, 0, 0, 0, 0},
        // {0, 8, 7, 0, 0, 0, 0, 3, 1},
        // {0, 0, 3, 0, 1, 0, 0, 8, 0},
        // {9, 0, 0, 8, 6, 3, 0, 0, 5},
        // {0, 5, 0, 0, 9, 0, 6, 0, 0},
        // {1, 3, 0, 0, 0, 0, 2, 5, 0},
        // {0, 0, 0, 0, 0, 0, 0, 7, 4},
        // {0, 0, 5, 2, 0, 6, 3, 0, 0}};
          
        // System.out.println(sudoku(board,0));
    }

    public static ArrayList < String > subseq(String str) {
        if (str.length() == 0) {
            ArrayList < String > base = new ArrayList < > ();
            base.add("");
            return base;
        }

        char ch = str.charAt(0);
        String ros = str.substring(1);

        ArrayList < String > recAns = subseq(ros);

        ArrayList < String > myAns = new ArrayList < > ();
        // myAns.addAll(recAns); 

        for (String s: recAns) {
            myAns.add(s);
        }

        for (String s: recAns) {
            myAns.add(ch + s);
        }

        return myAns;

    }

    public static String removeHi(String ques) {
        if (ques.length() == 0) {
            return "";
        }
        // if(ques.length()>1 && ques.charAt(0)=='h' && ques.charAt(1)=='i')

        if (ques.length() > 1 && ques.substring(0, 2).equals("hi"))
            return removeHi(ques.substring(2));
        else {
            char ch = ques.charAt(0);
            return ch + removeHi(ques.substring(1));
        }
    }

    public static String removeDuplicates(String ques) {
        if (ques.length() == 1) {
            return ques;
        }

        char ch = ques.charAt(0);
        String recAns = removeDuplicates(ques.substring(1));
        if (ch == recAns.charAt(0))
            return recAns;
        else
            return ch + recAns;
    }

    public static String removeHiExceptHit(String ques) {
        if (ques.length() == 0) {
            return "";
        }
        // if(ques.length()>1 && ques.charAt(0)=='h' && ques.charAt(1)=='i')

        if (ques.length() > 1 && ques.substring(0, 2).equals("hi"))
            if (ques.length() > 2 && ques.substring(0, 3).equals("hit"))
                return "hit" + removeHiExceptHit(ques.substring(3));
            else
                return removeHiExceptHit(ques.substring(2));
        else {
            char ch = ques.charAt(0);
            return ch + removeHiExceptHit(ques.substring(1));
        }
    }

    public static String compression(String str, int idx, int count) {
        if (idx == str.length()) {
            String ans = str.charAt(idx - 1) + (count > 1 ? count + "" : "");
            return ans;
        }

        if (str.charAt(idx - 1) == str.charAt(idx)) {
            return compression(str, idx + 1, count + 1);
        } else {
            String ans = str.charAt(idx - 1) + (count > 1 ? count + "" : "");
            return ans + compression(str, idx + 1, 1);
        }
    }

    public static ArrayList < String > mazePath(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList < String > base = new ArrayList < > ();
            base.add("");
            return base;
        }


        ArrayList < String > ans = new ArrayList < > ();
        if (sc + 1 <= ec) {
            ArrayList < String > Horizontal = mazePath(sr, sc + 1, er, ec);
            for (String s: Horizontal) {
                ans.add("H" + s);
            }
        }

        if (sr + 1 <= er) {
            ArrayList < String > vecrtical = mazePath(sr + 1, sc, er, ec);
            for (String s: vecrtical) {
                ans.add("V" + s);
            }
        }

        return ans;
    }


    public static ArrayList < String > mazePath_diag(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList < String > base = new ArrayList < > ();
            base.add("");
            return base;
        }


        ArrayList < String > ans = new ArrayList < > ();
        if (sc + 1 <= ec) {
            ArrayList < String > Horizontal = mazePath_diag(sr, sc + 1, er, ec);
            for (String s: Horizontal) {
                ans.add("H" + s);
            }
        }

        if (sr + 1 <= er) {
            ArrayList < String > vecrtical = mazePath_diag(sr + 1, sc, er, ec);
            for (String s: vecrtical) {
                ans.add("V" + s);
            }
        }

        if (sr + 1 <= er && sc + 1 <= ec) {
            ArrayList < String > diagonal = mazePath_diag(sr + 1, sc + 1, er, ec);
            for (String s: diagonal) {
                ans.add("D" + s);
            }
        }

        return ans;
    }


    public static ArrayList < String > mazePath_diag_multi(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList < String > base = new ArrayList < > ();
            base.add("");
            return base;
        }


        ArrayList < String > ans = new ArrayList < > ();
        for (int i = 1; sc + i <= ec; i++) {
            ArrayList < String > Horizontal = mazePath_diag_multi(sr, sc + i, er, ec);
            for (String s: Horizontal) {
                ans.add("H" + i + s);
            }
        }

        for (int i = 1; sr + i <= er; i++) {
            ArrayList < String > vecrtical = mazePath_diag_multi(sr + i, sc, er, ec);
            for (String s: vecrtical) {
                ans.add("V" + i + s);
            }
        }

        for (int i = 1; sr + i <= er && sc + i <= ec; i++) {
            ArrayList < String > diagonal = mazePath_diag_multi(sr + i, sc + i, er, ec);
            for (String s: diagonal) {
                ans.add("D" + i + s);
            }
        }

        return ans;
    }

    public static int mazePath_diag_hei(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            return 0;
        }

        int maxHei = 0;
        if (sc + 1 <= ec) {
            maxHei = Math.max(maxHei, mazePath_diag_hei(sr, sc + 1, er, ec));
        }

        if (sr + 1 <= er) {
            maxHei = Math.max(maxHei, mazePath_diag_hei(sr + 1, sc, er, ec));
        }

        if (sr + 1 <= er && sc + 1 <= ec) {
            maxHei = Math.max(maxHei, mazePath_diag_hei(sr + 1, sc + 1, er, ec));
        }

        return maxHei + 1;
    }


    public static int mazePath_diag_Minhei(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            return 0;
        }

        int minHei = (int) 1e6;
        if (sc + 1 <= ec) {
            minHei = Math.min(minHei, mazePath_diag_Minhei(sr, sc + 1, er, ec));
        }

        if (sr + 1 <= er) {
            minHei = Math.min(minHei, mazePath_diag_Minhei(sr + 1, sc, er, ec));
        }

        if (sr + 1 <= er && sc + 1 <= ec) {
            minHei = Math.min(minHei, mazePath_diag_Minhei(sr + 1, sc + 1, er, ec));
        }

        return minHei + 1;
    }


    public static ArrayList<String> floodFill(int sr,int sc,int er,int ec,boolean[][] isdone){
       if(sr==er && sc==ec){
        ArrayList<String> base=new ArrayList<>();
        base.add("");
        return base;
       }


    ArrayList<String> myAns=new ArrayList<>();
    isdone[sr][sc]=true;  
    
    if(sr-1>=0 && !isdone[sr-1][sc]){
        ArrayList<String> upward=floodFill(sr-1,sc,er,ec,isdone);
        for(String s:upward){
          myAns.add("U" + s);
        }

    }

    if(sc+1<=ec&& !isdone[sr][sc+1]){
        ArrayList<String> right=floodFill(sr,sc+1,er,ec,isdone);
        for(String s:right){
          myAns.add("R" + s);
        }
    }

    if(sr+1<=er&& !isdone[sr+1][sc]){
        ArrayList<String> down=floodFill(sr+1,sc,er,ec,isdone);
        for(String s:down){
          myAns.add("D" + s);
        }
    }

    int x=sr+0;
    int y=sc-1;
    if(y>=0&& !isdone[x][y]){
        ArrayList<String> left=floodFill(x,y,er,ec,isdone);
        for(String s:left){
          myAns.add("L" + s);
        }
    }

    isdone[sr][sc]=false;

    

    return myAns;
    }

    public static boolean isValid(int x,int y,boolean[][] isdone){
      if(x>=0 && y>=0 && x<isdone.length && y<isdone[0].length && !isdone[x][y]) return true;
      return false;
    }


    public static ArrayList<String> floodFill_eightCalls(int sr,int sc,int er,int ec,boolean[][] isdone){
        if(sr==er && sc==ec){
         ArrayList<String> base=new ArrayList<>();
         base.add("");
         return base;
        }

        int[][] dir={{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,-1},{-1,1},{1,-1}};
        String[] dirName={"D","R","U","L","1","3","4","2"};
    
        ArrayList<String> myAns=new ArrayList<>();
        
        isdone[sr][sc]=true;
        for(int d=0;d<dir.length;d++){
            int x=sr+dir[d][0];
            int y=sc+dir[d][1];
             
        if(isValid(x,y,isdone)){
            ArrayList<String> calls=floodFill_eightCalls(x,y,er,ec,isdone);
            for(String s:calls){
              myAns.add(dirName[d] + s);
            }  
          }
        }

        isdone[sr][sc]=false;

        return myAns;
    }


    public static int knightPath(int sr,int sc,int er,int ec,boolean[][] isdone,String ans){
        if(sr==er && sc==ec){
        System.out.println(ans);
         return 1;
        }

        int[][] dir={{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}};
        
        isdone[sr][sc]=true;
        int count=0;

        for(int d=0;d<dir.length;d++){
            int x=sr+dir[d][0];
            int y=sc+dir[d][1];
            
            if(isValid(x,y,isdone)){
           count+= knightPath(x,y,er,ec,isdone,ans+"("+x + ", "+ y+"), ");
           }
        }

        isdone[sr][sc]=false;

        return count;
    }


    public static boolean knightPath_(int sr,int sc,int count,int boxSize,boolean[][] isdone,int[][] ans){
        isdone[sr][sc]=true;
        ans[sr][sc]=count;
        
        if(count==boxSize-1){
            for(int[] ar:ans){
                for(int ele: ar){
                    System.out.print(ele+" ");
                }
                System.out.println();
            }

            return true;
        }

        int[][] dir={{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}};
        boolean res=false;

        for(int d=0;d<dir.length && !res;d++){
            int x=sr+dir[d][0];
            int y=sc+dir[d][1];
             
        if(isValid(x,y,isdone)){
           res=res || knightPath_(x,y,count+1,boxSize,isdone,ans);
           }
        }

        isdone[sr][sc]=false;
        ans[sr][sc]=0;
        return res;
    }

    public static ArrayList<String> keyPad_01(String ques,String[] keys){
if(ques.length()==0){
    ArrayList<String> base=new ArrayList<>();
    base.add("");
    return base;
}

    
        char ch=ques.charAt(0);
        String roq=ques.substring(1);
        int idx=ch-'0';
        String word=keys[idx];

        ArrayList<String> myAns=new ArrayList<>();
        ArrayList<String> recAns=keyPad_01(roq,keys);
        for(String s: recAns){
            for(int i=0;i<word.length();i++)
               myAns.add(word.charAt(i)+s);
        }
         return myAns;
    }

    public static ArrayList<String> permuatation(String str){

        if(str.length()==0) return new ArrayList<>();
        if(str.length()==1) {
            ArrayList<String> base=new ArrayList<>();
            base.add(str);
            return base;
        }


        char ch=str.charAt(0);
        String roq=str.substring(1);

        ArrayList<String> myAns=new ArrayList<>();
        ArrayList<String> recAns=permuatation(roq);
        
        for(String s:recAns){
            for(int i=0;i<=s.length();i++){
                String ans=s.substring(0,i) + ch + s.substring(i); 
                myAns.add(ans);
            }
        }
        return myAns;
    }

    public static ArrayList<String> encoding(String ques){
        if(ques.length()==0){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        char ch=ques.charAt(0);
        ArrayList<String> myAns=new ArrayList<>();

        if(ch=='0'){
            return encoding(ques.substring(1));
        }else{
            ArrayList<String> recAns=encoding(ques.substring(1));
            for(String s: recAns){
              char ch1=(char)('a'+ ch-'1');
                myAns.add(ch1+s);
            }
        }

        if(ques.length()>1){
            // char ch1 = ques.charAt(1);
            // int num  = (ch-'0')*10 + (ch1-'0');
             int num=Integer.parseInt(ques.substring(0,2));
            
            
            if(num<27){
                ArrayList<String> recAns=encoding(ques.substring(2));
                for(String s: recAns){
                  char ch1=(char)('a'+ num-1);
                    myAns.add(ch1+s);
                }   
            } 

        }

        return myAns;
    }


    public static boolean isValidSudoku(int[][] board,int i,int j,int num){

           //row
           for(int idx=0;idx<board.length;idx++){
              if(board[idx][j]==num){
                  return false;
              }
           }


        // col
           for(int idx=0;idx<board.length;idx++){
            if(board[i][idx]==num){
                return false;
            }
         }

         //mat
         int r=(i/3)*3;
         int c=(j/3)*3;
         for(int row=0;row<3;row++){
            for(int col=0;col<3;col++){
                if(board[r+row][c+col]==num) return false;
            }
         }


        return  true;
    }

    public static boolean sudoku(int[][] board,int vidx){
        if(vidx==board.length*board[0].length){
          for(int[] ar:board){
              for(int ele:ar){
                System.out.print(ele+" ");
              }
              System.out.println();            
          }
          System.out.println();
             return true;
         }
 
          int r=vidx/9;
          int c=vidx%9;
          boolean res=false;

          if(board[r][c]!=0){
             res=res|| sudoku(board,vidx+1);
          }else{
             for(int num=1;num<=9;num++){
                   if(isValidSudoku(board,r,c,num)){
                       board[r][c]=num;
                       res=res ||  sudoku(board,vidx+1);
                       board[r][c]=0;
                   }
               }
    }

    return res;
}
}