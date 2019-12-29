// import java.util.*;
import java.util.Scanner;
public class l002{
    public static Scanner scn=new Scanner(System.in);
    
    public static void main(String[] args){
      solve();
    }

    public static void solve(){
        // int[] arr=new int[scn.nextInt()];
        int[] arr={2,3,5,7};
        int tar=10;
        // System.out.println(coinChgCombi_01(arr,0,tar,""));
        // System.out.println(coinChgCombi_02(arr,0,tar,""));
        // System.out.println(coinChgCombi_03(arr,0,tar,""));


        // boolean[] boxes=new boolean[16];
        // System.out.println(QueenBoxCombi_01(boxes,0,4,0,""));
        
        boolean[][] boxes=new boolean[4][4];
        // System.out.println(QueenBoxCombi_2D(boxes,0,4,0,""));
        System.out.println(QueenBoxCombi_2D_02(boxes,0,4,0,""));


    }

    public static int coinChgCombi_01(int[] arr,int idx,int tar,String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i]>=0){
                count+=coinChgCombi_01(arr,i,tar-arr[i],ans+ arr[i]+ " ");
            }
        }
        return count;

    }

    public static int coinChgCombi_02(int[] arr,int idx,int tar,String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i]>=0){
                count+=coinChgCombi_02(arr,i+1,tar-arr[i],ans+ arr[i]+ " ");
            }
        }
        return count;
    }


    public static int coinChgCombi_03(int[] arr,int i,int tar,String ans){
        if(tar==0 || i==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count=0;
        count+=coinChgCombi_03(arr,i+1,tar,ans);
        if(tar-arr[i]>=0){
            count+=coinChgCombi_03(arr,i+1,tar-arr[i],ans+ arr[i]+ " ");
        }
    
        return count;
    }
    //lqpl=last queen place location
    public static int QueenBoxCombi_01(boolean[] boxes,int lqpl,int tnq,int qpsf,String ans){
     if(qpsf==tnq){
         System.out.println(ans);
         return 1;
     }

     int count=0;
     for(int i=lqpl;i<boxes.length;i++){
         count+=QueenBoxCombi_01(boxes,i+1,tnq,qpsf+1,ans+"b"+i+ "q"+qpsf);
     }

        return count;
    }
public static boolean isQueenSafe(boolean[][] boxes,int x,int y){

    int[][] dir={{0,-1},{-1,-1},{-1,0},{-1,1}, {0,1},{1,1},{1,0},{1,-1}};
    
    for(int i=0;i<dir.length;i++){
        for(int rad=1;rad<Math.max(boxes.length,boxes[0].length);rad++){
            int r=x+rad*dir[i][0];
            int c=y+rad*dir[i][1];
                
            if(r<0 || c< 0 || r>=boxes.length || c>= boxes[0].length) break;
            if(boxes[r][c]) return false;
        }
    }
    return true;
}



    //lqpl=last queen place location
    public static int QueenBoxCombi_2D(boolean[][] boxes,int lqpl,int tnq,int qpsf,String ans){
        if(qpsf==tnq){
            System.out.println(ans);
            return 1;
        }
   
        int count=0;
        for(int i=0;i<boxes.length*boxes[0].length;i++){
            int x=i/boxes[0].length;
            int y=i%boxes[0].length;
            if(!boxes[x][y] && isQueenSafe(boxes,x,y)){
                 boxes[x][y]=true;
                count+=QueenBoxCombi_2D(boxes,i+1,tnq,qpsf+1,ans+"("+x+", "+ y+") ");
                boxes[x][y]=false; 
            }
    }
   
           return count;
       }

        //lqpl=last queen place location
    public static int QueenBoxCombi_2D_02(boolean[][] boxes,int rowno,int tnq,int qpsf,String ans){
        if(qpsf==tnq ){
            if(qpsf==tnq){
            System.out.println(ans);
            return 1;
            }
            return 0;
        }
   
        int count=0;
        for(int i=0;i<boxes[0].length;i++){
            int x=rowno;
            int y=i;
            if(isQueenSafe(boxes,x,y)){
                //  boxes[x][y]=true;
                count+=QueenBoxCombi_2D_02(boxes,rowno+1,tnq,qpsf+1,ans+"("+x+", "+ y+") ");
                // boxes[x][y]=false; 
            }
    }
   
           return count;
       }


       public static int QueenBoxCombiSub_2D(boolean[][] boxes,int lqpl,int tnq,int qpsf,String ans){
        if(qpsf==tnq || lqpl==boxes.length*boxes[0].length){
            if(qpsf==tnq){
            System.out.println(ans);
            return 1;
            }
            return 0;
        }
   
            int count=0;
            int x=lqpl/boxes[0].length;
            int y=lqpl%boxes[0].length;
            if(isQueenSafe(boxes,x,y)){
                 boxes[x][y]=true;

                count+=QueenBoxCombiSub_2D(boxes,(x+1)*boxes[0].length,tnq,qpsf+1,ans+"("+x+", "+ y+") ");
                boxes[x][y]=false; 
            }

            count+=QueenBoxCombiSub_2D(boxes,lqpl+1,tnq,qpsf,ans);
   
           return count;
       }




}