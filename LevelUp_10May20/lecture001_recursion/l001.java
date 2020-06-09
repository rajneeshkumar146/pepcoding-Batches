import java.util.Scanner;
import java.util.ArrayList;

public class l001{
    public static Scanner scn=new Scanner(System.in);

    public static void main(String[] args){
        solve();
    }

    public static void solve(){
        // set1();
        pathType();
    }

    public static void set1(){
        int a=scn.nextInt();
        int b=scn.nextInt();

        // printIncreasing(a,b);
        // System.out.println(factorial(a));
        System.out.println(power(a,b));
    }

    public static void printIncreasing(int a, int b){
    if (a == b + 1)
        return;

       System.out.println(a);
       printIncreasing(a + 1, b);
    }

    public static int factorial(int n){
        if(n==0) return 1;
        return factorial(n-1)*n;
    }

    public static int power(int a,int b){
     return b==0?1: power(a,b-1)*a;
    }

    public static int powerBtr(int a,int b){
        if(b==0) return 1;
        int ans=powerBtr(a,b/2);
        ans*=ans;
        return b%2!=0?ans*a:ans;
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

    //set2.==========================================

    public static void set2(){
        // int n=scn.nextInt();
        // int[] arr=new int[n];
        // for(int i=0;i<n;i++) arr[i]=scn.nextInt();
       
        int[] arr={2,3,4,234,45,345,67,6,845,7};
    }

    public static void display(int[] arr,int vidx){
        if(vidx==arr.length) return;
        System.out.println(arr[vidx]);
        display(arr,vidx+1);
    }

    public static int firstIndex(int[] arr,int vidx,int data){
        if(vidx==arr.length) return -1;
        if(arr[vidx]==data) return vidx;
        return firstIndex(arr,vidx+1,data);
    }

    public static int lastIndex(int[] arr,int vidx,int data){
        if(vidx==arr.length) return -1;

        int res=lastIndex(arr,vidx+1,data);
        if(res!=-1) return res;
        return arr[vidx]==data?vidx:-1;
    }

    
    public static int[] allIndex(int[] arr,int idx,int data,int count){
        if(idx==arr.length){
            return new int[count];  // vector<int> base(4,0); return base;
        }

        if(arr[idx]==data) count++;
        
        int[] ans=allIndex(arr,idx+1,data,count);
        
        if(arr[idx]==data) ans[count-1]=idx;
        return ans;
    }

    //set3.=============================================

    public static void set3(){

    }

    public static ArrayList<String> subseq(String str){

        if(str.length()==0){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }


        char ch=str.charAt(0);
        String nstr=str.substring(1);

        ArrayList<String> smallAns=subseq(nstr);
        ArrayList<String> ans=new ArrayList<>();

        for(String s: smallAns){
            ans.add(s);
            ans.add(ch + s);
        }

        return ans;
    }

    public static ArrayList<String> subseq2(String str){
        if(str.length()==0){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1);

        ArrayList<String> smallAns=subseq2(nstr);
        int size=smallAns.size();
        for(int i=0;i<size;i++){
            smallAns.add(smallAns.get(i));
            smallAns.add(ch + smallAns.get(i));
        }

        return smallAns;
    }

    public static int subseq03(String que,String ans){
        if(que.length()==0){
            System.out.println(ans);
            return 1;
        }
        char ch=que.charAt(0);
        String nque=que.substring(1);

        int count=0;
        count += subseq03(nque,ans);
        count += subseq03(nque,ans + ch);
        return count;
    }

    static String[] words={":;/","abc","def","ghi","jkl","mno","pqrs","tuv","wx","yz","&*%","#@$"};
    public static ArrayList<String> nokiaKeyPad(String str){
        if(str.length()==0){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1);
        String word=words[ch-'0'];

        ArrayList<String> smallAns=nokiaKeyPad(nstr);
        ArrayList<String> myAns=new ArrayList<>();

        for(int i=0;i<word.length();i++)
            for(String s: smallAns)
              myAns.add(word.charAt(i)+ s);
        
        return myAns;
    }

    public static int nokiaKeyPad_02(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1);
        String word=words[ch-'0'];
        
        int count=0;
        for(int i=0;i<word.length();i++){
          count+=nokiaKeyPad_02(nstr,ans+word.charAt(i));
        }
        return count;
    }

    //PathType.============================================================

    public static ArrayList<String> mazePath_01(int sr,int sc,int er,int ec){
        if(sr==er && sc==ec){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> ans=new ArrayList<>();
        if(sr+1<=er){
            ArrayList<String> Vertical=mazePath_01(sr+1,sc,er,ec);
            for(String s: Vertical)
                ans.add("V"+s);
        }

        if(sc+1<=ec){
            ArrayList<String> Horizontal=mazePath_01(sr,sc+1,er,ec);
            for(String s: Horizontal)
                ans.add("H"+s);
        }

        if(sc+1<=ec && sr+1<=er){
            ArrayList<String> Diagonal=mazePath_01(sr+1,sc+1,er,ec);
            for(String s: Diagonal)
                ans.add("D"+s);
        }

        return ans;
    }

    public static int mazePath_02(int sr,int sc,int er,int ec,String ans){
        if(sr==er && sc==ec){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        if(sr+1<=er)
            count+=mazePath_02(sr+1,sc,er,ec,ans +"V");

        if(sc+1<=ec)
           count+=mazePath_02(sr,sc+1,er,ec,ans +"H");

        if(sc+1<=ec && sr+1<=er)
           count+=mazePath_02(sr+1,sc+1,er,ec,ans +"D");

        return count;
    }



    public static void pathType(){
        System.out.println(mazePath_01(0,0,2,2));
        System.out.println(mazePath_02(0,0,2,2,""));
    }
}