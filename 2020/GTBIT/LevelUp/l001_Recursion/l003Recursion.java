public class l003Recursion {
    static String s1 = "send";
    static String s2 = "more";
    static String s3 = "money";

    static boolean[] usedNumber = new boolean[10];
    static int[] mapping = new int[128];

    public static int crypto(String str, int idx) {
        if(idx == str.length()){
            int x = convertStringToNumber(s1);
            int y = convertStringToNumber(s2);
            int z = convertStringToNumber(s3);

            if(x + y == z){
                return 1;
            }

            return 0;
        }

        int count = 0;
        char ch = str.charAt(idx);
        for (int i = 0; i <= 9; i++) {
            if (!usedNumber[i]) {
                usedNumber[i] = true;
                mapping[ch] = i;

                count += crypto(str, idx + 1);
                usedNumber[i] = false;
                mapping[ch] = 0;
            }
        }

        return count;
    }

    public static int crypto() {
        String str = s1 + s2 + s3;
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++)
            freq[str.charAt(i) - 'a']++;

        str = "";
        for (int i = 0; i < 26; i++)
            if (freq[i] > 0)
                str += (char) (i + 'a');

    }
}