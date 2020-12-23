import java.util.Scanner;
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

    public static void main(String[] args){
        String str = scn.nextLine();
        allSubString(str);
    }

}