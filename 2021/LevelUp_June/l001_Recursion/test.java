import java.util.*;

public class test {

    static int[] map = new int[26];
    static boolean[] isUsed = new boolean[10];

    public static int StringToInteger(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int mapedValue = map[ch - 'a'];
            res = res * 10 + mapedValue;
        }

        return res;
    }

    public static int crypto(String uniqueString, int idx, String str1, String str2, String str3) {
        if (idx == uniqueString.length()) {
            int n1 = StringToInteger(str1);
            int n2 = StringToInteger(str2);
            int n3 = StringToInteger(str3);

            if (n1 + n2 == n3) {
                System.out.println("" + n1 + "\n+" + n2 + "\n------\n" + n3);
                System.out.println();
                return 1;
            }

            return 0;
        }

        int count = 0;
        char ch = uniqueString.charAt(idx);
        for (int num = 0; num <= 9; num++) {
            if ((str1.charAt(0) == ch || str2.charAt(0) == ch || str3.charAt(0) == ch) && num == 0)
                continue;

            if (!isUsed[num]) {
                isUsed[num] = true;
                map[ch - 'a'] = num;

                count += crypto(uniqueString, idx + 1, str1, str2, str3);

                isUsed[num] = false;
                map[ch - 'a'] = -1;
            }
        }

        return count;

    }

    public static void crypto(String str1, String str2, String str3) {
        String str = str1 + str2 + str3;
        boolean[] freq = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i) - 'a'] = true;
        }

        str = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i]) {
                str += (char) (i + 'a');
            }
        }

        Arrays.fill(map, -1);

        System.out.println(crypto(str, 0, str1, str2, str3));

    }

    public static void main(String[] args) {
        crypto("send", "more", "money");
    }
}