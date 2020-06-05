import java.util.Scanner;
public class l001{
    public static Scanner scn=new Scanner(System.in);
    public static void main(String[] args){
      solve();
    }

    public static int fact(int n){
        return n<=1?1:fact(n-1)*n;
    }

    public static int power(int a,int b){
        return b==0?1:power(a,b-1)*a;
    }

    public static int powerBtr(int a,int b){
        if(b==0) return 1;
        int smallAns=powerBtr(a,b/2);
        smallAns*=smallAns;
        return b%2==0?smallAns:smallAns*a;
    }

    public static void display(int[] arr,int idx){
        if(idx==arr.length) return;
        System.out.print(arr[idx]+" ");
        display(arr,idx+1);
     }

     public static int maximum(int[] arr,int idx){
        if(idx==arr.length-1) return arr[idx];

        int max=arr[idx];
        return Math.max(max,maximum(arr,idx+1));
     }

     public static int mimimum(int[] arr,int idx){
        if(idx==arr.length-1) return arr[idx];

        int min=arr[idx];
        return Math.min(min,mimimum(arr,idx+1));
    }
    
    public static boolean find(int[] arr,int idx,int data){
        if(idx==arr.length) return false;
        if(arr[idx]==data) return true;
        return find(arr,idx+1,data);
    }

    
    public static int firstIndex(int[] arr,int idx,int data){
        if(idx==arr.length) return -1;
        if(arr[idx]==data) return idx;
        return firstIndex(arr,idx+1,data);
    }

    public static int lastIndex(int[] arr,int idx,int data){
        if(idx==arr.length) return -1;
        int res=lastIndex(arr,idx,data);
        if(res!=-1) return res;
        return arr[idx]==data?idx:-1;
    }

    public static int[] allIndex(int[] arr,int idx,int data,int count){
        if(idx==arr.length) return new int[count];

        if(arr[idx]==data) count++;
        int[] ans=allIndex(arr,idx+1,data,count);        
        if(arr[idx]==data) ans[count-1]=idx;
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

    //====================================================================================

    
    public static ArrayList<String> subseq(String str){
    if(str.length()==0){
        ArrayList<String>  base=new ArrayList<>();
        base.add("");
        return base;
    }

    char ch=str.charAt(0);
    ArrayList<String> recAns=subseq(str.substring(1));    

    ArrayList<String>  myAns=new ArrayList<>();
    for(String s: recAns){
        myAns.add(s);  
        myAns.add(ch+s);
    }

    return myAns;
}

public static String[] words={":;/", "abc", "def", "ghi", "jkl", "mno",
    "pqrs", "tuv", "wxyz", "&*%", "#@$","+-/*"};

    public static ArrayList<String> keyPad(String str){
        if(str.length()==0){
            ArrayList<String>  base=new ArrayList<>();
            base.add("");
            return base;
        }

        int ch=str.charAt(0);
        String word=words[ch-'0'];
        String nstr=str.substring(1);

        ArrayList<String> recAns=keyPad(nstr);
        ArrayList<String> myAns=new ArrayList<>();
        
        for(String s: recAns){
            for(int i=0;i<word.length();i++){
                myAns.add(word.charAt(i) + s);
            }
        }

        return myAns;
    }

    public static int keyPad(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        int ch=str.charAt(0);
        String word=words[ch-'0'];
        String nstr=str.substring(1);
        int count=0;

        for(int i=0;i<word.length();i++){
            count+=keyPad(nstr,ans+word.charAt(i));
        }

        return count;
    }

    



    public static void set2(){
        int[] arr ={1,23,45,2,67,3,23,6,8,43,567,2,457,584,6346,2,567973,523};
        display(arr,0);

        System.out.println(maximum(arr,0));
        System.out.println(mimimum(arr,0));
        System.out.println(find(arr,0,2));
        System.out.println(firstIndex(arr,0,2));
        System.out.println(lastIndex(arr,0,2,0));
    }

    public static void set1(){
        // int n=scn.nextInt();
        // System.out.println("Hello Pepcoders! -> " + n);
    
    }

    
    public static void solve(){
         set1();
    }
    
}