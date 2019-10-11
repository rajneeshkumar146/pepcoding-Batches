import java.util.Scanner;
import java.util.ArrayList;
public class l003_returnType{

public static Scanner scn=new Scanner(System.in);

 public static void main(String[] args){
        String str=scn.nextLine();

    //    String str="ABCD";
//    System.out.println(subseq(str));
   System.out.println(removeHi(str));

 }

 public static ArrayList<String> subseq(String str){
   if(str.length()==0){
    ArrayList<String> base=new ArrayList<>();
    base.add("");
    return base;
   }  
    
    char ch=str.charAt(0);
    String ros=str.substring(1);

    ArrayList<String> recAns=subseq(ros);

    ArrayList<String> myAns=new ArrayList<>();
    // myAns.addAll(recAns); 
    
    for(String s:recAns){
        myAns.add(s);
     }

    for(String s:recAns){
       myAns.add(ch+s);
    }

   return myAns;

 }

 public static String removeHi(String ques){
   if(ques.length()==0){
       return "";
   }
    // if(ques.length()>1 && ques.charAt(0)=='h' && ques.charAt(1)=='i')
    
    if(ques.length()>1 && ques.substring(0,2).equals("hi"))
         return removeHi(ques.substring(2));
    else{
        char ch=ques.charAt(0);
        return ch+ removeHi(ques.substring(1));
    }
 }

 public static String removeDuplicates(String ques){
    if(ques.length()==1){
        return ques;
    }
    
    char ch=ques.charAt(0);
     String recAns=removeDuplicates(ques.substring(1));
     if(ch==recAns.chatAt(0))
         return recAns;
    else
       return ch + recAns;
 }

}