import java.util.Scanner;
import java.util.ArrayList;

public class l003_returnType{

   public static void main(String[] args){     
       solve();
   }

   public static void solve(){
    //    basicQuestion();
       pathProblems();
   }

   //basic.==============================

   public static void basicQuestion(){
    //    System.out.println(subsequence("1234"));
       System.out.println(permutation("1234"));
   }

   public static ArrayList<String> subsequence(String str){
     if(str.length()==0){
         ArrayList<String> base=new ArrayList<>();
         base.add("");
         return base;
     }
     
     char ch=str.charAt(0);
     String restStr=str.substring(1);

     ArrayList<String> recAns=subsequence(restStr);
     
     ArrayList<String> myAns=new ArrayList<>();
     
     for(String s:recAns){
         myAns.add(s);  //nahi ane ka option.
         myAns.add(ch + s); //anne ka option.  
     }

     return myAns;
   }

   public static ArrayList<String> permutation(String str){
     if(str.length()==0){
         ArrayList<String> base=new ArrayList<>();
         base.add("");
         return base;
     }
     
     char ch=str.charAt(0);

     String restStr=str.substring(1);

     ArrayList<String> recAns=permutation(restStr);
     ArrayList<String> myAns=new ArrayList<>();
     
     for(String s:recAns){
         for(int i=0;i<=s.length();i++){
             String one=s.substring(0,i);
             String two=s.substring(i);

             String ans=one+ch+two;
             myAns.add(ans);
         }  
     }

     return myAns;
   }

//pathProblems.==========================

   public static void pathProblems(){
       System.out.println(mazePath(0,0,2,2));
   }

   public static ArrayList<String> mazePath(int si,int ei,int sp,int ep){
       if(si==sp && ei==ep){
           ArrayList<String> base=new ArrayList<>();
           base.add("");
           return base;
       }

        ArrayList<String> myAns=new ArrayList<>();
          if(ei+1<=ep){
        ArrayList<String> hRecAns=mazePath(si,ei+1,sp,ep);
        for(String s:hRecAns){
            myAns.add('H'+s);
             }
        }

       if(si+1<=sp){
        ArrayList<String> VRecAns=mazePath(si+1,ei,sp,ep);
        for(String s:VRecAns){
            myAns.add('V'+s);
           }
       }
        return myAns;


   }








}