import java.util.*;

public class leetcode {

    // 205
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

    // 409
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int len = 0;
        for (Character key : map.keySet()) {
            int val = map.get(key);
            len += (val / 2) * 2;
        }

        return len != s.length() ? len + 1 : len;
    }

    // 525
    public int findMaxLength(int[] nums) {
        int n = nums.length, sum = 0, len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            int val = (nums[i] == 0 ? -1 : nums[i]);
            sum += val;
            if (map.containsKey(sum))
                len = Math.max(len, i - map.get(sum));
            else
                map.put(sum, i);
        }
        return len;
    }

    // 560
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0, sum = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // 003
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1)
            return s.length();
        int n = s.length(), si = 0, ei = 0, len = 0, count = 0;
        int[] freq = new int[256];

        while (ei < n) {
            if (freq[s.charAt(ei++)]++ == 1)
                count++;

            while (count != 0)
                if (freq[s.charAt(si++)]-- > 1)
                    count--;

            len = Math.max(len, ei - si);
        }
        return len;
    }

}