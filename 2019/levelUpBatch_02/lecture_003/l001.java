import java.util.*;
public class l001{

    public static void main(String[] args){
        // int[] arr={2,3,5,7};
        // boolean[] vis=new boolean[arr.length];  //default false.
        // int tar=10;
        // System.out.println(coinCombiInfi(arr,0,tar,""));
   
        boolean[][] box=new boolean[4][4];
        System.out.println(queen2DCombi(box,0,box.length,""));
        // System.out.println(queen2DCombi_02(box,0,box.length,""));
        System.out.println(calls);

        

    } 


    public static int coinCombiInfi(int[] arr,int idx,int tar,String ans){
        if(idx==arr.length || tar==0){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;

        }

        int count=0;
        if(tar-arr[idx]>=0)
            count+=coinCombiInfi(arr,0,tar-arr[idx],ans+arr[idx]);
        count+=coinCombiInfi(arr,idx+1,tar,ans);
        return count;
    }

    public static boolean isSafeToPlaceQueen(boolean[][] box,int x,int y){
        int[][] dir={{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
        for(int d=0;d<dir.length;d++){
        for(int rad=1;rad<=Math.max(box.length,box[0].length);rad++){
             int r=x+rad*dir[d][0];
             int c=y+rad*dir[d][1];
             if(r>=0 && c>=0 && r<box.length && c<box[0].length){
                  if(box[r][c]) return false;
             }else{
                 break;
             }
        }

    }

    return true;

    }

    public static int queen2DCombi(boolean[][] box,int lqpl,int tnq,String ans){
      if(tnq==0){
          System.out.println(ans);
          return 1;
      }

      int count=0;
      calls++;
      for(int i=lqpl;i<box.length * box[0].length;i++){
          int x=i/box[0].length;
          int y=i%box[0].length;
          if(!box[x][y] && isSafeToPlaceQueen(box,x,y)){
            box[x][y]=true;
            count+=queen2DCombi(box,i+1,tnq-1,ans + "("+x +", " + y + ") ");
            box[x][y]=false;
          }
        }
      return count;
         
    }
static int calls=0;
    public static int queen2DCombi_02(boolean[][] box,int row,int tnq,String ans){
      if(row==box.length || tnq==0){
        if(tnq==0){
            System.out.println(ans);
            return 1;
        }
        return 0;
      }
       
      calls++;
       int count=0;
      for(int col=0;col<box[0].length;col++){
        if(isSafeToPlaceQueen(box,row,col)){
            box[row][col]=true;
            count+=queen2DCombi_02(box,row+1,tnq-1,ans + "("+row +", " + col + ") ");
            box[row][col]=false;
        }
      }
      return count;
    }


}