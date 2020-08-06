public class questions{

    
    //geeks: https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1

    public static ArrayList<String> printPath(int[][] m, int n) {
        if(n==0 || m[0][0]==0 || m[n-1][n-1]==0) return new ArrayList<>();
        
        int[][] dir={{1,0},{0,-1},{0,1},{-1,0}};
        String[] dirS={"D","L","R","U"};

        ArrayList<String> res=new ArrayList<>();
        floodFill_(0,0,n-1,n-1,m,dir,dirS,"",res);
        return res;
    }


    public static int floodFill_(int sr,int sc,int er,int ec,int[][] vis,int[][] dir,String[] dirS,String ans,ArrayList<String> res){
        if(sr==er && sc==ec){
            res.add(ans);
            return 1;
        }

        vis[sr][sc] = 0;
        int count=0;

        for(int d=0;d<4;d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r >=0 && c >=0 && r <= er && c<=ec && vis[r][c] == 1){
                count+=floodFill_(r,c,er,ec,vis,dir,dirS,ans + dirS[d],res);
            }
        }

        vis[sr][sc] = 1;
        return count;
    }






}