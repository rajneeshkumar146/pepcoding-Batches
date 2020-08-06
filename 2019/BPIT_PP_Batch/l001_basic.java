public class l001_basic{

    public static void main(String[] args){
        solve();
    }

    public static void solve(){
    //    int sr=0,sc=0,er=2,ec=2;
    //    System.out.println(mazePath(sr,sc,er,ec,""));
    //    System.out.println(mazePathMulti(sr,sc,er,ec,""));1
    floodFill();
    }

    public static int mazePath(int sr,int sc,int er,int ec,String ans){
        if(sr==er && sc==ec){
            System.out.println(ans);
            return 1;
        }

        int count=0;

        if(sc+1<=ec)  count+=mazePath(sr,sc+1,er,ec,ans+"H");
        if(sr+1<=er)  count+=mazePath(sr+1,sc,er,ec,ans+"V");
        if(sc+1<=ec && sr+1<=er)  count+=mazePath(sr+1,sc+1,er,ec,ans+"D");
        
        return count;
    }


    public static int mazePathMulti(int sr,int sc,int er,int ec,String ans){
        if(sr==er && sc==ec){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        
        for(int jump=1; sc + jump <= ec;jump++)  count+=mazePathMulti(sr,sc+jump,er,ec,ans+"H"+jump);
        for(int jump=1;sr + jump <= er;jump++) count+=mazePathMulti(sr+jump,sc,er,ec,ans+"V"+jump);
        for(int jump=1;sc + jump <= ec && sr + jump <= er; jump++)  count+=mazePathMulti(sr+jump,sc+jump,er,ec,ans+"D"+jump);
        
        return count;

    }

    public static int floodFill_(int sr,int sc,int er,int ec,boolean[][] vis,int[][] dir,String[] dirS,String ans){
        if(sr==er && sc==ec){
            System.out.println(ans);
            return 1;
        }

        vis[sr][sc] = true;
        int count=0;
        for(int d=0;d<4;d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r >=0 && c >=0 && r <= er && c<=ec && vis[r][c]==false){
                count+=floodFill_(r,c,er,ec,vis,dir,dirS,ans + dirS[d]);
            }
        }

        vis[sr][sc] = false;
        return count;
    }

    public static int floodFillMultiJump_(int sr,int sc,int er,int ec,boolean[][] vis,int[][] dir,String[] dirS,String ans){
        if(sr==er && sc==ec){
            System.out.println(ans);
            return 1;
        }

        vis[sr][sc] = true;
        int count=0;
        
        for(int jump=1;jump<=Math.max(er,ec);jump++){
            for(int d=0;d<4;d++){
                int r = sr + jump * dir[d][0];
                int c = sc + jump * dir[d][1];
    
                if(r >=0 && c >=0 && r <= er && c<=ec && vis[r][c]==false){
                    count+=floodFillMultiJump_(r,c,er,ec,vis,dir,dirS,ans + dirS[d] + jump);
                }
            }
        }

        vis[sr][sc] = false;
        return count;
    }

    public static void floodFill(){
        int sr = 0,sc = 0,er = 3,ec = 3;
        
        int[][] dirFour={{1,0},{-1,0},{0,1},{0,-1}};
        String[] dirFourS={"D","U","R","L"};
        boolean[][] vis=new boolean[er+1][ec+1];

        // System.out.println(floodFill_(sr,sc,er,ec,vis,dirFour,dirFourS,""));
        System.out.println(floodFillMultiJump_(sr,sc,er,ec,vis,dirFour,dirFourS,""));
    }




}