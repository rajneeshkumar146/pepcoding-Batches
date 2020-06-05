import java.util.Scanner;
import java.util.ArrayList;
public class l001{

    public static Scanner scn=new Scanner(System.in);

    public static void printDec(int a, int b){
    if (a == b + 1)
        return;
     printDec(a+1,b);
     System.out.print(a + " ");
    }

    public static void printEvenOdd(int a, int b){
        if (a == b + 1)
            return;
        if(a%2==0) System.out.println(a);
        printEvenOdd(a+1,b);
        if(a%2!=0) System.out.println(a);
    }

    public static void display(int[] arr,int idx){
        if (idx == arr.length)
          return;
          System.out.print(arr[idx] + " ");
          display(arr,idx+1);
    }

    public static int firstIdx(int[] arr,int idx,int data){
        if(idx==arr.length) return -1;
        if(arr[idx]==data) return idx;
        return firstIdx(arr,idx+1,data);
    }

    public static int lastIdx(int[] arr,int idx,int data){
        if(idx==arr.length) return -1;
        int ans=lastIdx(arr,idx+1,data);
        if(ans!=-1) return ans;

        return arr[idx]==data?idx:-1;
    }

    public static int[] allIdx(int[] arr,int idx,int data,int count){
        if(idx==arr.length) return new int[count];
        if(arr[idx]==data) count++;
        
        int[] ans=allIdx(arr,idx+1,data,count);

        if(arr[idx]==data) ans[count-1] =idx;

        return ans;
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

    public static String[] words={":;/", "abc", "def", "ghi", "jkl", "mno",
    "pqrs", "tuv", "wxyz", "&*%", "#@$"};
    public static ArrayList<String> nokiaKeyPad(String str){
        if(str.length()==0) {
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1);

        String word=words[ch-'0'];
        ArrayList<String> smallAns=nokiaKeyPad(nstr);

        ArrayList<String> myAns=new ArrayList<>();
        for(String s: smallAns){
            for(int i=0;i<word.length();i++){
                myAns.add(word.charAt(i) + s);
            }
        }

        return myAns;
    }

    public static int nokiaKeyPad_02(String str,String ans){
        if(str.length()==0) {
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

        if(str.length() > 1){
            char ch2=str.charAt(1);
            int num = (ch-'0')*10 + (ch2-'0');
            if(num >= 10 && num <= 11){
                word=words[num];
                for(int i=0;i<word.length();i++){
                    count+=nokiaKeyPad_02(str.substring(2),ans + word.charAt(i));
                }
            }
        }
       
        return count;
    }

    public static int encoding(String str,String ans){
        if(str.length()==0) {
            System.out.println(ans);
            return 1;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1);
        int count=0;
        
        count+=encoding(nstr,ans+(char)('a'+ (ch-'0')));

        if(str.length() > 1){
            char ch2=str.charAt(1);
            int num = (ch-'0')*10 + (ch2-'0');
            if(num >= 10 && num <= 25){
                count+=encoding(str.substring(2),ans+ (char)('a'+ num));
            }
        }
       
        return count;
    }



    public static void set3(){
        System.out.println(nokiaKeyPad("589"));
        System.out.println(nokiaKeyPad("589",""));
    }


    public static void set2(){
        // int[] arr=new int[10];
        int[] arr={1,45,56,2,34,23,-9,2,56,89,2,898,5};
        display(arr,0);
    }

    public static void set1(){
       // System.out.println("Hi pepCoders!!");
        int a = scn.nextInt();
        int b = scn.nextInt();
        // printDec(a,b);
        printEvenOdd(a,b);

    }
    
    public static void solve(){
        //   set1();
        //   set2();
        set3();
    }

    public static void main(String[] args){
       solve();
    }

}