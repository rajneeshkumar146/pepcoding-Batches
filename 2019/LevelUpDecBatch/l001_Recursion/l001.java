import java.util.Scanner;
import java.util.ArrayList;

public class l001 {
    public static Scanner scn = new Scanner(System.in);

    public static void printDecreasing(int n) {
        if (n == 0)
            return;

        System.out.println(n);
        printDecreasing(n - 1);
    }

    public static void printIncreasing(int n) {
        if (n == 0)
            return;

        printIncreasing(n - 1);
        System.out.println(n);

    }

    public static void printEvenOdd(int n) {
        if (n == 0)
            return;

        if (n % 2 == 0)
            System.out.println(n);
        printEvenOdd(n - 1);
        if (n % 2 != 0)
            System.out.println(n);
    }

    public static int factorial(int n) {
        return n == 0 ? 1 : factorial(n - 1) * n;
    }

    public static int power(int a, int b) {
        return b == 0 ? 1 : power(a, b - 1) * a;
    }

    public static int power1(int a, int b) {
        if (b == 0)
            return 1;
        int smallAns = power(a, b - 1);
        return smallAns * a;
    }

    public static int powerBtr(int a, int b) {
        if (b == 0)
            return 1;
        int smallAns = powerBtr(a, b / 2);
        smallAns *= smallAns;
        return b % 2 != 0 ? smallAns * a : smallAns;
    }

    public static void display(int[] arr, int idx) {
        if (idx == arr.length)
            return;

            System.out.println(arr[idx] + " ");
            display(arr, idx + 1);
    }

    public static int maximumEle(int[] arr,int idx){
        if(idx == arr.length) return -(int)1e9;
        return Math.max(arr[idx],maximumEle(arr, idx+1));
    }

    public static int minimumEle(int[] arr,int idx){
        if(idx == arr.length) return (int)1e9;
        return Math.min(arr[idx],minimumEle(arr, idx+1));
    }

    public static int findData(int[] arr,int idx,int data){
        if(idx == arr.length) return -1;
        if(arr[idx] == data) return idx;
        return findData(arr, idx + 1, data);
    }

    public static int findData2(int[] arr,int idx,int data){
        if(idx == arr.length) return -1;
        int smallAns = findData2(arr, idx + 1, data);
        if(smallAns != -1) return smallAns;

        return arr[idx] == data ?  idx : -1;
    }

    public static int[] allIndex(int[] arr,int idx,int count,int data){
        if(idx == arr.length) return new int[count];
        if(arr[idx] == data) count++;
        int[] smallAns = allIndex(arr,idx+1,count,data);
        if(arr[idx] == data) smallAns[count - 1] = idx;

        return smallAns;
    }

    public static ArrayList<String> subsequence(String str,int idx){
        if(idx == str.length()){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        
        char ch = str.charAt(idx);
        
        ArrayList<String> smallAns = subsequence(str,idx + 1);
        ArrayList<String> myAns= new ArrayList<>();
        
        for(String s : smallAns){
            myAns.add(s);
            myAns.add(ch + s);
        }
    
        return myAns;
    }

    
    static String[] codes ={".;","abc","def","ghi","jkl","mno","pqrs","tu","vwx","yz"};
    
    public static ArrayList <String> getKPC(String str,int idx) {
        if(idx == str.length()){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        
        int indexOfCode = str.charAt(idx) - '0';
        
        ArrayList<String> smallAns = getKPC(str,idx+1);
        ArrayList<String> myAns = new ArrayList<>();
        
        String code = codes[indexOfCode];
        for(int i=0;i<code.length();i++){
            for(String s : smallAns){
                myAns.add(code.charAt(i) + s);
            }
        }
        
        return myAns;
    }













    public static void main(String[] args) {
        printDecreasing(10);
    }
}