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

    public static void main(String[] args){
        // test2();
        // String str = scn.nextLine();
        System.out.println(compress("aaaaabbbbbbxxxdsfdxxxxxvvvvfvvvvrrrrrtttttyghf"));
    }
}