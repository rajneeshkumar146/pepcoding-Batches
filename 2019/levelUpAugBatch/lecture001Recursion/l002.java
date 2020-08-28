import java.util.ArrayList;

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



    public static void pathSet(){
        // System.out.println(mazePath_HVD(0,0,2,2));
        // System.out.println(mazePath_HVD_01(0,0,2,2,""));
        System.out.println(mazePath_HVD_jump(0,0,2,2,""));
    }

    public static void solve(){
        pathSet();
    }

    public static void main(String[] args){
        solve();
    }

    

}