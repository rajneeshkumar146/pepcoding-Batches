public class T006_more {

    public static void main(String[] args) {
        solve();

    }

    public static void solve() {
        crypto();
    }

    public static void crypto() {
        String str1 = "send";
        String str2 = "more";
        String str3 = "money";

        int[] freq = new int[26];
        for (int i = 0; i < str3.length(); i++) {
            if (i < str1.length()) {
                char ch = str1.charAt(i);
                freq[ch - 'a']++;
            }

            if (i < str2.length()) {
                char ch = str2.charAt(i);
                freq[ch - 'a']++;
            }

            char ch = str3.charAt(i);
            freq[ch - 'a']++;
        }

        // unique String.
        String str = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                str += (char) (i + 'a');
            }
        }
        boolean[] numMap = new boolean[10];
        int[] freqMap = new int[26];
        System.out.print(cryptoArithmatic(str, str1, str2, str3, numMap, freqMap));
    }

    public static int cryptoArithmatic(String str, String str1, String str2, String str3, boolean[] numMap,
            int[] freqMap) {
        if (str.length() == 0) {
            if (chechCryptoIsValid(str1, str2, str3, freqMap))
                return 1;
            return 0;
        }

        char ch = str.charAt(0);
        int count = 0;
        for (int num = 9; num >= 0; num--) {
            if (!numMap[num]) {

                numMap[num] = true;
                freqMap[ch - 'a'] = num;

                count += cryptoArithmatic(str.substring(1), str1, str2, str3, numMap, freqMap);

                numMap[num] = false;
                freqMap[ch - 'a'] = 0;

            }
        }
        return count;
    }

    public static boolean chechCryptoIsValid(String str1, String str2, String str3, int[] freqMap) {

        int num1 = getNumberFromString(str1, freqMap);
        int num2 = getNumberFromString(str2, freqMap);
        int num3 = getNumberFromString(str3, freqMap);

        int start1 = freqMap[str1.charAt(0) - 'a'];
        int start2 = freqMap[str2.charAt(0) - 'a'];
        int start3 = freqMap[str3.charAt(0) - 'a'];

        if (start1 != 0 && start2 != 0 && start3 != 0 && num1 + num2 == num3) {
            System.out.println(num1 + " + " + num2 + " = " + num3);
            return true;
        }
        return false;
    }

    public static int getNumberFromString(String str, int[] freqMap) {

        int ans = 0;
        int pow = 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            ans += freqMap[str.charAt(i) - 'a'] * pow;
            pow *= 10;
        }
        return ans;
    }

    

}