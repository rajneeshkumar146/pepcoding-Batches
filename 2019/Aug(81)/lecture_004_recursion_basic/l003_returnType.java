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
    //    System.out.println(mazePath(0,0,2,2));
    //    System.out.println(mazePath_multiMoves(0,0,2,2));
    System.out.println(boardPath(0,10).size());
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


   public static ArrayList<String> mazePath_multiMoves(int si,int ei,int sp,int ep){
    if(si==sp && ei==ep){
        ArrayList<String> base=new ArrayList<>();
        base.add("");
        return base;
    }

     ArrayList<String> myAns=new ArrayList<>();
    for(int jump=1;jump+ei<=ep;jump++){
    
        ArrayList<String> hRecAns=mazePath_multiMoves(si,ei+jump,sp,ep);
        
        for(String s:hRecAns){
            myAns.add("H"+jump+s);
          }
     }

     for(int jump=1;jump+si<=sp;jump++){
     ArrayList<String> VRecAns=mazePath_multiMoves(si+jump,ei,sp,ep);
     for(String s:VRecAns){
         myAns.add("V"+jump+s);
        }
    }

    for(int jump=1;si+jump<=sp && ei+jump<=ep;jump++){
        ArrayList<String> DRecAns=mazePath_multiMoves(si+jump,ei+jump,sp,ep);
        for(String s:DRecAns){
            myAns.add("D"+jump+s);
           }
       }
     return myAns;
}

public static ArrayList<String> boardPath(int si,int ei){
    if(si==ei){
        ArrayList<String> base=new ArrayList<>();
        base.add("");
        return base;
    }

    ArrayList<String> ans=new ArrayList<>();

   for(int jump=1;si+jump<=ei && jump<=6;jump++){
    ArrayList<String> recAns=boardPath(si+jump,ei);
    for(String s: recAns){
        ans.add(s+jump);
    }
   }

   return ans;
}

public static ArrayList<String> boardPath_2(int ei){
if(ei==0){
    ArrayList<String> base=new ArrayList<>();
    base.add("");
    return base;
}

ArrayList<String> ans=new ArrayList<>();

for(int jump=1;ei-jump>=0 && jump<=6;jump++){
 ArrayList<String> recAns=boardPath(ei-jump);
 for(String s: recAns){
     ans.add(s+jump);
 }
}

return ans;

}








}