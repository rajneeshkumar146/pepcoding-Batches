public class l001Basics{
    public static void test1(){
        String str = "szcszxvwesz";
        String str1 = str; // O(1)

        str += 'g'; // O(n)
        char ch = str.charAt(3);
    }

    public static void test2(){
        String str = "";
        for(int i=0;i < (int)1e6;i++){
            str += i;
        }

        System.out.println(str);
    }

    public static void test3(){
        StringBuilder sb = new StringBuilder();
        sb.append('d');  // O(1)
        StringBuilder sb1 = sb; // O(1)
        StringBuilder sb2 = new StringBuilder(sb); // O(n)

        char ch = sb.charAt(3);

        String str = sb.toString(); // O(n) 
        System.out.println(str);
    }

    
    public static void test4(){
        StringBuilder str = new StringBuilder();
        for(int i=0;i < (int)1e6;i++){
            str.append(i);
        }

        System.out.println(str);
    }

    // Questions.===========================================================

    public static String compress(String str){
        if(str.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        
        char prevChar = str.charAt(0);
        int i = 1;
        while(i <= str.length()){
            int count = 1;
            while(i < str.length() && prevChar == str.charAt(i)){
                  count++;
                  prevChar = str.charAt(i);
                  i++;
            }

            sb.append(prevChar);
            sb.append(count);
            if(i == str.length()) break;
            prevChar = str.charAt(i);
            i++;
        } 

        return sb.toString();
    }

    public static String compress1(String str){
        int[] freq = new int[26];
        for(int i=0;i<str.length();i++){
            freq[str.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<freq.length;i++){
            if(freq[i] > 0){
                sb.append((char)(i + 'a'));
                sb.append(freq[i]);
            }
        }
    }

    public static String toggleString(String str){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch >= 'a' && ch <= 'z')
               sb.append((char)(ch - 'a' + 'A'));
            else sb.append((char)(ch - 'A' + 'a'));
        }

        return  sb.toString();
    }

    public static boolean isPlaindrome(String str){
        int i = 0, j = str.length() - 1;
        while(i < j){
            if(str.charAt(i++) != str.charAt(j--)) return false;
            // i++;
            // j--;
        }

        return true;
    }

    public static void PlaindromicSubstring(String str){
        for(int i = 0;i < str.length();i++){
            for(int j = i; j < str.length();j++){
                String s = str.substring(i,j+1);
                if(isPlaindrome(s)) System.out.println(s);
            }
        }
    }

    public static String consecutiveCharacter(String str){
        if(str.length() <= 1) return str;

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        for(int i = 1;i < str.length();i++){
            char ch = str.charAt(i);
            char ch0 = str.charAt(i - 1);

            sb.append(ch - ch0);
            sb.append(ch);
        }

        return sb.toString();
    }

    public static void permutation(String str){
        ArrayList<String> ans = new ArrayList<>();
        ans.add(str.charAt(0)+"");

        for(int i=1;i<str.length();i++){
            char ch = str.charAt(i);
            ArrayList<String> smallAns = new ArrayList<>();
            for(String s : ans){
                for(int j = 0;j <= s.length();j++){
                    String ns = s.substring(0,j) + ch + s.substring(j);
                    smallAns.add(ns);
                }
            }
            ans = smallAns;
        }


    }

    public static void main(String[] args){
        // test2();
        // String str = scn.nextLine();
        System.out.println(compress("aaaaabbbbbbxxxdsfdxxxxxvvvvfvvvvrrrrrtttttyghf"));
    }
}