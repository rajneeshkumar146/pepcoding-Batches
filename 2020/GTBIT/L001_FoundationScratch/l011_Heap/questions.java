import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class questions {
    // 242
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++)
            if (freq[i] != 0)
                return false;
        return true;
    }

    // 49
    public static String RLES(String str) { // run Length Encoded String
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++)
            freq[str.charAt(i) - 'a']++;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++)
            if (freq[i] != 0) {
                sb.append((char) (i + 'a'));
                sb.append(freq[i]);
            }

        return sb.toString();
    }
    
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            String rles = RLES(s);
            map.putIfAbsent(rles, new ArrayList<>());
            map.get(rles).add(s);
        }

        List<List<String>> ans = new ArrayList<>();
        for (String key : map.keySet())
            ans.add(map.get(key));

        return ans;
    }
}