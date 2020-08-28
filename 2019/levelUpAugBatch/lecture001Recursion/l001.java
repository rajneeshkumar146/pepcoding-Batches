import java.util.ArrayList;
public class l001{

    public static void main(String[] args){
        solve();
    }

    //Set1.======================================================================

    public static void printIncreasing(int a,int b){
        if(a == b+1) return;

        System.out.println(a);
        printIncreasing(a+1,b);
    }

    public static void printDecreasing(int a,int b){
        if(a == b+1) return;
        
        printDecreasing(a+1,b);
        System.out.println(a);
    }

    public static void printOddEven(int a,int b){
        if(a == b+1) return;
        
        if(a%2 != 0)System.out.println(a);
        printOddEven(a+1,b);
        if(a%2 == 0) System.out.println(a);
    }

    public static int fact(int n){
        return n == 0 ? 1 : fact(n-1)*n;
    }

    public static int power(int a,int b){
        return b==0 ? 1 : power(a,b-1) * a;
    }

    public static int powerBtr(int a,int b){
        if(b==0) return 1;

        int ans = powerBtr(a,b/2);
        ans *= ans;
        return b%2 != 0?ans*a:ans;
    }

    //Set2.==========================================================================
    public static void display(int[] arr,int idx){
        if(idx==arr.length) return;

        System.out.println(arr[idx]);
        display(arr,idx+1);
    }

    public static int maximum(int[] arr,int idx){
        if(idx==arr.length) return -(int)1e9;
        return Math.max(arr[idx],maximum(arr,idx + 1));
    }

    public static int minimum(int[] arr,int idx){
        if(idx==arr.length) return (int)1e9;
        return Math.min(arr[idx],minimum(arr,idx + 1));
    }

    public static boolean find(int[] arr,int idx,int data){
        if(idx==arr.length) return false;
        if(arr[idx]==data) return true;
        return find(arr,idx+1,data);
    }

    public static int fisrtIndex(int[] arr,int idx,int data){
        if(idx==arr.length) return -1;
        if(arr[idx]==data) return idx;
        return fisrtIndex(arr,idx+1,data);
    }
    
    public static int lastIndex(int[] arr,int idx,int data){
        if(idx==arr.length) return -1;
        
        int ans=lastIndex(arr,idx+1,data);
        if(ans!=-1) return ans;

        return arr[idx]==data ? idx : -1;
    }
    
    public static int[] allIndex(int[] arr,int idx,int data,int count){
        if(idx == arr.length){
            int[] baseArray = new int[count];
            return baseArray;
        }

        if(arr[idx] == data) count++;
        
        int[] smallAns = allIndex(arr,idx,data,count);

        if(arr[idx] == data) smallAns[count - 1] = idx;
        
        return smallAns;
    }

    //Set3.================================================================

    public static ArrayList<String> subsequence(String str,int idx){
        if(idx == str.length()){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }


        char ch = str.charAt(idx);
        ArrayList<String> smallAns = subsequence(str,idx + 1);

        ArrayList<String> myAns = new ArrayList<>();
        for(String s : smallAns){
            myAns.add(s);
            myAns.add(ch + s);
        }

        return myAns;
    }

    public static int subsequence(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        count += subsequence(str.substring(1),ans);
        count += subsequence(str.substring(1),ans + str.charAt(0));
         
        return count;
    }

    static String[] words = {":;/", "abc", "def", "ghi", "jkl", "mno",
    "pqrs", "tuv", "wxyz", "&*%", "#@$"};

    public static ArrayList<String> nokiaKeyPad_01(String str,int idx,String ans){
        if(idx == str.length()){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        int chi = str.charAt(idx) - '0';
        String word = words[chi];
        int count = 0;
        
        ArrayList<String> smallAns = nokiaKeyPad_01(str,idx+1,ans);

        ArrayList<String> myAns = new ArrayList<>();

        for(String s : smallAns){
            for(int i = 0;i<word.length();i++){
                myAns.add(word.charAt(i) + s);
            }
        }

        return myAns;
    }

    public static int nokiaKeyPad(String str,int idx,String ans){
        if(idx == str.length()){
            System.out.println(ans);
            return 1;
        }

        int chi = str.charAt(idx) - '0';
        String word = words[chi];
        int count = 0;

        for(int i = 0;i<word.length();i++){
            count+=nokiaKeyPad(str,idx+1,ans+word.charAt(i));
        }

        if(idx < str.length() - 1){
            int chi1 = chi * 10 + (str.charAt(idx + 1)-'0'); // Integer.parseInt(str.substring(idx,idx+2));
            if(chi1 >=10 && chi1<=11){
                word = words[chi1];
                for(int i = 0;i<word.length();i++){
                    count+=nokiaKeyPad(str,idx+2,ans+word.charAt(i));
                }
            }
        }

        return count;
    }

    public static void set3(){
        // System.out.println(subsequence("abc",0));
        // subsequence("abc","");
        System.out.println(nokiaKeyPad("345",0,""));
        System.out.println(nokiaKeyPad_01("345",0,""));
    }

    


    public static void set1(){
        printOddEven(1,10);
    }

    public static void solve(){
        // set1();
        set3();
    }
}