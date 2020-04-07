public class l002{
    public static void main(String[] args){
       solve();
    }


    public static void solve(){

        // floodFillCate();
        coinChange();
    }

    //=================================================================

    public static void floodFillCate(){
        int[][] board=new int[4][4];
        int ans=floodfill_Height(0,0,3,3,board);
     //    pair ansP=floodfill_LongestPath(0,0,3,3,board);
           pair ansP=floodfill_ShortestPath(0,0,3,3,board);
 
        System.out.println(ansP.str + " -> " + ansP.len);
     
    }
    
    static int[][] dir={{0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}};
    static String[] dirN={"R", "E", "U", "N", "L", "W", "D", "S"};

    public static int floodfill_Height(int sr,int sc,int er,int ec,int[][] board){
        if(sr==er && sc==ec){
            return 0;
        }

        board[sr][sc]=2;
        int maxH=0;
        for(int d=0;d<dir.length;d++){
            int r=sr+dir[d][0];
            int c=sc+dir[d][1];
            if(r>=0 && c>=0 && r<board.length && c<board[0].length && board[r][c]==0){
                int recH=floodfill_Height(r,c,er,ec,board);
                maxH=Math.max(recH,maxH);
            }
        }
        
        board[sr][sc]=0;
        return maxH+1;
    }

    static class pair{
        int len=0;
        String str="";

        pair(int len,String str){
            this.len=len;
            this.str=str;
        }
    }

    public static pair floodfill_LongestPath(int sr,int sc,int er,int ec,int[][] board){
        if(sr==er && sc==ec){
            return new pair(0,"");
        }

        board[sr][sc]=2;
        
        pair mypair=new pair(0,"");

        for(int d=0;d<dir.length;d++){
            int r=sr+dir[d][0];
            int c=sc+dir[d][1];
            if(r>=0 && c>=0 && r<board.length && c<board[0].length && board[r][c]==0){
                pair recP=floodfill_LongestPath(r,c,er,ec,board);
                 
                if(recP.len+1> mypair.len){
                    mypair.len=recP.len+1;
                    mypair.str=recP.str+dirN[d];
                }
            }
        }
        
        board[sr][sc]=0;
        return mypair;
    }

    public static pair floodfill_ShortestPath(int sr,int sc,int er,int ec,int[][] board){
        if(sr==er && sc==ec){
            return new pair(0,"");  //c++ -> INT_MAX;
        }

        board[sr][sc]=2;
        
        pair mypair=new pair((int)1e7,"");

        for(int d=0;d<dir.length;d++){
            int r=sr+dir[d][0];
            int c=sc+dir[d][1];
            if(r>=0 && c>=0 && r<board.length && c<board[0].length && board[r][c]==0){
                pair recP=floodfill_ShortestPath(r,c,er,ec,board);
                 
                if(recP.len+1 < mypair.len){
                    mypair.len=recP.len+1;
                    mypair.str=dirN[d]+recP.str;
                }
            }
        }
        
        board[sr][sc]=0;
        return mypair;
    }

    //CoinTrees.====================================================

    public static int coinChangePermuINFI_01(int[] arr,int tar,String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=0;i<arr.length;i++){
            if(tar-arr[i]>=0)
             count+=coinChangePermuINFI_01(arr,tar-arr[i],ans + arr[i]+ " ");
        }

        return count;
    }

    public static int coinChangePermu_01(int[] arr,int tar,boolean[] vis,String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=0;i<arr.length;i++){
            if(!vis[i] && tar-arr[i]>=0){
                vis[i]=true;
                count+=coinChangePermu_01(arr,tar-arr[i],vis,ans + arr[i]+ " ");
                vis[i]=false;
            }
        }
        return count;
    }

    public static int coinChangeCombinationINFI_01(int[] arr,int idx,int tar,String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i]>=0)
             count+=coinChangeCombinationINFI_01(arr,i,tar-arr[i],ans + arr[i]+ " ");
        }

        return count;
    }

    public static int coinChangeCombination_01(int[] arr,int idx,int tar,String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i]>=0)
             count+=coinChangeCombination_01(arr,i+1,tar-arr[i],ans + arr[i]+ " ");
        }

        return count;
    }



    public static void coinChange(){
        int[] arr={2,3,5,7};
        int tar=10;
        int ans=0;
        boolean[] vis=new boolean[arr.length];
        // ans=coinChangePermuINFI_01(arr,tar,"");
        ans=coinChangePermu_01(arr,tar,vis,"");

        // ans=coinChangeCombinationINFI_01(arr,0,tar,"");
        // ans=coinChangeCombination_01(arr,0,tar,"");


        System.out.println(ans);

    }

}