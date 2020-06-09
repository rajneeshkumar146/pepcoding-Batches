import java.util.Scanner;
import java.util.ArrayList;

public class l001{

    public static Scanner scn=new Scanner(System.in);

    public static void main(String[] args){
        solve();
    }

    public static void solve(){
    //    set1();
    //  set2();
    set3();
    }

    //=============================================
    public static void set1(){
        int n=scn.nextInt();
        int m=scn.nextInt();
        
        // int a=Fact(n);
        int a=power(n,m);


        System.out.println(a);
    }

    public static int Fact(int n){
       if(n<=1) return 1;

       int smallAns=Fact(n-1);
       return smallAns*n;

        // return n<=1?1:Fact(n-1)*n;
    }

    public static int power(int a,int b){
      if(b==0) return 1;

      int smallAns=power(a,b-1);
      return smallAns*a;
    }

    public static int powerBtr(int a,int b){
        if(b==0) return 1;

        int smallAns=powerBtr(a,b/2);
        smallAns*=smallAns;

        return b%2==0?smallAns:smallAns*a;
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

    //set2.====================================================
    
    public static void display(int[] arr,int vidx){
        if(vidx==arr.length) return;

        System.out.print(arr[vidx] + " ");
        display(arr,vidx+1);
    }

    public static boolean find_01(int[] arr,int vidx,int data){
        if(vidx==arr.length) return false;
        
        if(arr[vidx]==data) return true;  // work in pre area.
        return find_01(arr,vidx+1,data);
    }

    
    public static boolean find_02(int[] arr,int vidx,int data){
        if(vidx==arr.length) return false;
        
        
        boolean ans=find_02(arr,vidx+1,data);
        if(ans) return true;   //work in post area.
        else
           return arr[vidx]==data;
    }
    
    
    public static int maximum(int[] arr,int vidx){
        if(vidx==arr.length-1) return arr[vidx];
       
        int maxele=maximum(arr,vidx+1);
        return Math.max(maxele,arr[vidx]); // max(a,b); for c++
    }

    static int max_=-1;
    public static void maximum_02(int[] arr,int vidx){
        if(vidx==arr.length-1) return ;
        
        max_=Math.max(arr[vidx],max_);
        maximum_02(arr,vidx);
    }

    public static int minimum(int[] arr,int vidx){
        if(vidx==arr.length-1) return arr[vidx];
       
        int maxele=minimum(arr,vidx+1);
        return Math.min(maxele,arr[vidx]);  // min(a,b); for c++
    }

    public static void set2(){
        // int[] arr=new int[10];  
        // for(int i=0;i<arr.length;i++){
        //   arr[i]=scn.nextInt();
        // }

        int[] arr={1,234,34,7,2,1,23,4,5,6,3,2,5,7,32};
        display(arr,0);
    }

    // set3.=================================================

    public static ArrayList<String> subseq_ret(String str){
        if(str.length()==0){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }
    
        char ch=str.charAt(0);
        String nstr=str.substring(1);

        ArrayList<String> smallAns=subseq_ret(nstr);
        
        ArrayList<String> myAns=new ArrayList<>(); //vector<string> myAns(smallAns);

        for(String s:smallAns){
            myAns.add(s);
            myAns.add(ch + s);
        }

        return myAns;
    }

    public static int subseq_void(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;

        count+=subseq_void(str.substring(1),ans);
        count+=subseq_void(str.substring(1),ans+ str.charAt(0));
    
        return count;
    }
    
    public static ArrayList<String> permuation_withDupli_01(String str){
        if(str.length()==1){
            ArrayList<String> base=new ArrayList<>();
            base.add(str);
            return base;
        }

        char ch=str.charAt(0);
        ArrayList<String> SmallAns=permuation_withDupli_01(str.substring(1));
        ArrayList<String> myAns=new ArrayList<>();


        for(String s:SmallAns){
            for(int i=0;i<=s.length();i++){
                myAns.add(s.substring(0,i) + ch + s.substring(i));
            }
        }

        return myAns;
    }

    public static int permuation_withDupli_02(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }
        int count=0;

        for(int i=0;i<str.length();i++){
              char ch=str.charAt(i);
               String nstr=str.substring(0,i) + str.substring(i+1);
               count+=permuation_withDupli_02(nstr,ans+ch);
           }

        return count;
    }

    public static int permuation_withoutDupli(String str,String ans){
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
               count+=permuation_withoutDupli(nstr,ans+ch);
           }
        }

        return count;
    }


    public static void set3(){
    //   System.out.println(subseq_ret("abcd"));
    //   System.out.println(subseq_void("abc",""));

    //   System.out.println(permuation_withDupli_01("abc"));
    //   System.out.println(permuation_withDupli_02("aba",""));
      System.out.println(permuation_withoutDupli("aba",""));
    

      

    }

}