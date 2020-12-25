import java.util.Scanner;
import java.util.ArrayList;

public class l002{
    public static Scanner scn = new Scanner(System.in);
    public static boolean isPlaindrome(String str){
        int i = 0, j = str.length() - 1;
        while(i < j){
            if(str.charAt(i++) != str.charAt(j--)) return false;
        }

        return true;
    }

    public static void allSubString(String str){
        for(int i = 0; i < str.length(); i++){
            for(int len = 1; i + len <= str.length(); len++){
                System.out.println(str.substring(i,i + len));
            }
        }
    }

    public static String compression1(String str) {
        if(str.length() <= 1) return str;
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        
        int i = 1;
        while(i < str.length()){
            while(i < str.length() && str.charAt(i - 1) == str.charAt(i)){
                i++;
            }

            if(i < str.length()) sb.append(str.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public static String compression2(String str) {
        if(str.length() <= 1) return str;
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        
        int i = 1;
        while(i < str.length()){
            int count = 1;
            while(i < str.length() && str.charAt(i - 1) == str.charAt(i)){
                count++;
                i++;
            }

            if(count != 1) sb.append(count);
            if(i < str.length())sb.append(str.charAt(i));
            i++;
        }

        return sb.toString();
    }
    
    public static String withoutX2(String str) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<str.length();i++){
            if(i < 2 && str.charAt(i) != 'x') sb.append(str.charAt(i));
            if(i >= 2) sb.append(str.charAt(i));
        }

        return sb.toString();
    }
    
    // Toggle Case
    public static String toggleCase(String str) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch >= 'a' && ch <='z') sb.append((char)(ch - 'a' + 'A'));
            else sb.append((char)(ch - 'A' + 'a'));
        }

        return sb.toString();
    }


    // String With Difference Of Every Two Consecutive Characters
    public static String solution(String str) {
        if(str.length() <= 1) return str;
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        
        for(int i=1;i<str.length();i++){
            int diff = str.charAt(i) - str.charAt(i-1);
            char ch  = str.charAt(i);
            
            sb.append(diff);
            sb.append(ch);
        }

        return sb.toString();
    }

    public static ArrayList<Integer> occuOfChar(String str,char ch){
        ArrayList<Integer> arr = new ArrayList<>();

        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == ch) arr.add(i);
        }

        return arr;
    }

    public static ArrayList<String> subSeq(String str){

        ArrayList<String> ans = new ArrayList<>();
        ans.add("");

        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            int size = ans.size();
            for(int j = 0;j<size;j++){
                ans.add(ans.get(j) + ch);
            }
        }

        return ans;
    }

    public static boolean isPrime(int n){
        for(int i = 2;i * i <= n;i++){
            if(n % i == 0) return false;
        }
        return true;
    }

    public static void solution(ArrayList < Integer > al) {
        ArrayList<Integer> ans = new ArrayList<>();
        for(int ele : al){
            if(!isPrime(ele)) ans.add(ele);
        }
        
        al.clear();
        for(int ele : ans) al.add(ele);
    }

    public static boolean isPrime(int n){
        for(int i = 2;i * i <= n;i++){
            if(n % i == 0) return false;
        }
        return true;
    }

    public static void solution(ArrayList < Integer > al) {
        ArrayList<Integer> ans = new ArrayList<>();
        for(int ele : al){
            if(!isPrime(ele)) ans.add(ele);
        }
        
        al.clear();
        for(int ele : ans) al.add(ele);
    }

    public static void main(String[] args){
        String str = scn.nextLine();
        // allSubString(str);

        // int n = scn.nextInt(); scn.nextLine();
        // while(n-->0){
        //     System.out.println(withoutX2(scn.nextLine()));
        // }

        // ArrayList<Integer> ans = occuOfChar(str,'a');
        // System.out.println(ans);

        // for(int ele : ans) System.out.println(ele);
        System.out.println(subSeq(str));   
    }

}