import java.util.Scanner;
import java.util.ArrayList;

public class l001_basic{
    public static Scanner scn=new Scanner(System.in);
    public static void main(String[] args){
      solve();
    }

    public static void solve(){
        // basic();
        questionSet();
    }

    public static void basic(){
        int a=scn.nextInt();
        int b=scn.nextInt();
        // System.out.println(fact(a));
        System.out.println(power(a,b));
    }

    public static int fact(int n){
        if(n<=1) return 1;
        return fact(n-1)*n;
    }

    public static int power(int a,int b){
        if(b==0) return 1;
        return power(a,b-1)*a;
    }

    public static int powerBtr(int a,int b){
        if(b==0) return 1;
        
        int halfAns=powerBtr(a,b/2);
        halfAns*=halfAns;
        
        return halfAns*(b%2!=0?a:1);
    }

    public static int calls(int n) {  // n=5
        if (n <= 1) {
            System.out.println("base: " + n);
            return n + 1;
        }

        int count = 0;

        System.out.println("Pre: " + n);
        count += calls(n - 1);

        System.out.println("In: " + n);

        count += calls(n - 2);
        System.out.println("Post: " + n);

        return count + 3;
    }

    //recursionWithArray.==========================================

    public static void recursionWithArray(){
        // int n=scn.nextInt();
        // int[] arr=new int[n];
        // for(int i=0;i<n;i++) arr[i]=scn.nextInt();

        int[] arr={1,2,3,4,5,6,7,8,9,10};
    }


    public static void display(int[] arr,int idx){
        if(idx==arr.length){
            return;
        }

        System.out.println(arr[idx]);
        display(arr,idx+1);
    } 

    public static int multiplyAllEle(int[] arr,int idx){
        if(idx==arr.length) return 1;
        return multiplyAllEle(arr,idx+1)*arr[idx];
    }

    public static int firstIndex(int[] arr,int idx,int data){
        if(idx==arr.length) return -1;

        if(arr[idx]==data) return idx;
        return firstIndex(arr,idx+1,data);
    }

    
    public static int lastIndex(int[] arr,int idx,int data){
        if(idx==arr.length) return -1;
        
        int ans=lastIndex(arr,idx+1,data);
        if(ans!=-1) return ans;

        return arr[idx] == data ? idx : -1; 

    }

    
    public static int[] AllIndex(int[] arr,int idx,int data,int count){
        if(idx==arr.length){
            return new int[count];
        }

        if(arr[idx]==data) count++;
        
        int[] ans=AllIndex(arr,idx+1,data,count);
        
        if(arr[idx]==data)
            ans[count-1]=idx;

        return ans;
    }

    //questionSet.==================================================

    public static void questionSet(){
        //    ArrayList<String> ans=permutation("abcd");
        //    for(String s: ans) System.out.println(s);
        // System.out.println(subseq("abc",""));
        System.out.println(permutation("aba",""));
        
    }

    public static ArrayList<String> subsequence(String str){
        if(str.length()==0){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1); 
        
        ArrayList<String> smallAns=subsequence(nstr);
        ArrayList<String> myAns=new ArrayList<>();

        // for(int i=0;i<smallAns.size();i++){
        //     String s=smallAns.get(i);
        //     myAns.add(s);
        //     myAns.add(ch+s);            
        // }

        for(String s:smallAns){
            myAns.add(s);
            myAns.add(ch+s);
        }

        return myAns;
    }

    public static int subseq(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1); 
        int count=0;

        count+=subseq(nstr,ans+ch);
        count+=subseq(nstr,ans);
        
        return count;
    }

    public static ArrayList<String> permutation(String str){
        if(str.length()==1){
            ArrayList<String> base=new ArrayList<>();
            base.add(str);
            return base;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1); 
        
        ArrayList<String> smallAns=permutation(nstr);
        ArrayList<String> myAns=new ArrayList<>();

        for(String s:smallAns){
            for(int i=0;i<=s.length();i++){
                String ans=s.substring(0,i) + ch + s.substring(i);
                myAns.add(ans);
            }
        }
        return myAns;
    }

    public static int permutation(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            String nstr=str.substring(0,i) + str.substring(i+1);
            count+=permutation(nstr,ch + ans);
        }
        return count;
    }

    public static int permutationUnique(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        boolean[] vis=new boolean[26];
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(!vis[ch-'a']){
                vis[ch-'a']=true;
                String nstr=str.substring(0,i) + str.substring(i+1);
                count+=permutationUnique(nstr,ch + ans);
            }
        }
        return count;
    }
}

int[][] dirA = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
String[] dirS = {"L", "N", "U", "E", "R", "S", "D", "W"};

class pair{
    String path;
    int len;
    
    pair(String path_,int len_){
        path=path_;
        len=len_;
    }
}

pair shortestPath(int sr, int sc, int er, int ec,int[][] board){
    if(sr==er && sc==ec){
        return new pair("",0);
    }

    board[sr][sc]=1;
    pair myAns=new pair("",board.length * board[0].length);
    
    for(int d=0;d<dirA.length;d++){
        int x = sr+ dirA[d][0];
        int y = sc+ dirA[d][1];
        
        if(x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y]==0){
            pair smallAns=shortestPath(x,y,er,ec,board);
            if(smallAns + 1 < myAns.len ){
                myAns.len = smallAns.len + 1;
                myAns.path = dirS[d] + smallAns.path;
            }
        }
    }

    board[sr][sc]=0;
    return myAns;
}