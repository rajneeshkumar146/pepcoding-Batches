import java.util.*;

public class l001 {

    private static String generateCode(String str) {
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++)
            freq[str.charAt(i) - 'a']++;

        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                code.append((char) (i + 'a'));
                code.append(freq[i]);
            }
        }

        return code.toString();
    }

    public static List<List<String>> groupAnagrams(List<String> words) {
        HashMap<String, List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();

        for (String str : words) {
            String code = generateCode(str);
            map.putIfAbsent(code, new ArrayList<>());
            if (map.get(code).size() == 0)
                ans.add(map.get(code));

            map.get(code).add(str);
        }

        return ans;
    }

    private void breakString(int val, char ch, StringBuilder code) {
        if (val < 10) {
            code.append(val);
            code.append(ch);
            return;
        }

        code.append(9);
        code.append(ch);
        breakString(val - 9, ch, code);
    }

    public String runLengthEncoding(String str) {
        StringBuilder code = new StringBuilder();
        int i = 0, n = str.length();
        while (i < n) {
            int count = 1;
            while (i + 1 < n && str.charAt(i) == str.charAt(i + 1)) {
                count++;
                i++;
            }

            breakString(count, str.charAt(i), code);
            i++;
        }
        return code.toString();
    }

    // https://www.algoexpert.io/questions/Longest%20Substring%20Without%20Duplication
    public static String longestSubstringWithoutDuplication(String s) {
        if (s.length() <= 1)
            return s;
        int n = s.length(), si = 0, ei = 0, len = 0, count = 0;
        int[] freq = new int[256];
        int idx = 0;
        while (ei < n) {
            if (freq[s.charAt(ei++)]++ == 1)
                count++;

            while (count != 0)
                if (freq[s.charAt(si++)]-- > 1)
                    count--;

            // len = Math.max(len, ei - si);
            if (ei - si > len) {
                len = ei - si;
                idx = si;
            }
        }
        return s.substring(idx, idx + len);
    }
}