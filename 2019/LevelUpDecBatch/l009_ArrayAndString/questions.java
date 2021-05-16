import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class questions {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i++, j--);
        }
    }

    public static void rotateByK(int[] arr, int r) {
        int n = arr.length;
        // r %= n;
        // if (r < 0)
        // r += n;

        r = (r % n + n) % n;

        reverse(arr, 0, n - 1);
        reverse(arr, n - r, n - 1);
        reverse(arr, 0, n - r - 1);

    }

    public static void segregatePositiveAndNegative(int[] arr) {
        int n = arr.length, pt = -1, itr = 0;
        while (itr < n) {
            if (arr[itr] < 0)
                swap(arr, ++pt, itr);
            itr++;
        }
    }

    public static int max_sum(int[] arr, int n) {
        int sum = 0, sumWithIndex = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            sumWithIndex += arr[i] * i;
        }

        int maxAns = sumWithIndex;
        for (int i = 1; i < n; i++) {
            sumWithIndex = sumWithIndex - sum + arr[i - 1] * n;
            maxAns = Math.max(maxAns, sumWithIndex);
        }

        return maxAns;
    }

    // 11
    public int maxArea(int[] arr) {
        int n = arr.length, maxArea = 0, i = 0, j = n - 1;
        while (i < j) {
            int w = j - i;
            maxArea = arr[i] < arr[j] ? Math.max(maxArea, arr[i++] * w) : Math.max(maxArea, arr[j--] * w);
        }

        return maxArea;
    }

    // 003
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1)
            return s.length();

        int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
        int[] freq = new int[128]; // vector<int> freq(128,0);

        int gsi = 0, gei = 0;
        while (ei < n) {
            if (freq[s.charAt(ei++)]++ > 0)
                count++;

            while (count > 0)
                if (freq[s.charAt(si++)]-- > 1)
                    count--;

            if (ei - si > len) {
                len = ei - si;
                gsi = si;
                gei = ei;
            }
        }

        // System.out.println(s.substring(gsi,gei));

        return len;
    }

    // 003
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1)
            return s.length();

        int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
        int[] freq = new int[128]; // vector<int> freq(128,0);

        while (ei < n) {
            if (freq[s.charAt(ei)] == 1)
                count++;
            freq[s.charAt(ei)]++;
            ei++;

            while (count > 0) {
                if (freq[s.charAt(si)] == 2)
                    count--;
                freq[s.charAt(si)]--;
                si++;
            }

            len = Math.max(len, ei - si);
        }

        return len;
    }

    // 159
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() <= 2)
            return s.length();

        int n = s.length(), si = 0, ei = 0, len = 0, count = 0;
        int[] freq = new int[128];

        while (ei < n) {
            if (freq[s.charAt(ei++)]++ == 0)
                count++;

            while (count > 2) {
                if (freq[s.charAt(si++)]-- == 1)
                    count--;
            }

            len = Math.max(len, ei - si);
        }

        return len;
    }

    // 76
    public String minWindow(String s, String t) {

        int ns = s.length(), nt = t.length();
        if (ns < nt)
            return "";
        int[] freq = new int[128];
        for (int i = 0; i < nt; i++)
            freq[t.charAt(i)]++;

        int si = 0, ei = 0, count = nt, len = (int) 1e9, gsi = 0;

        while (ei < ns) {
            if (freq[s.charAt(ei++)]-- > 0)
                count--;

            while (count == 0) {
                if (ei - si < len) {
                    len = ei - si;
                    gsi = si;
                }

                if (freq[s.charAt(si++)]++ == 0)
                    count++;
            }
        }

        return len == (int) 1e9 ? "" : s.substring(gsi, gsi + len);
    }

    // 76
    public String minWindow2(String s, String t) {

        int ns = s.length(), nt = t.length();
        if (ns < nt)
            return "";
        int[] freq = new int[128];
        for (int i = 0; i < nt; i++)
            freq[t.charAt(i)]++;

        int si = 0, ei = 0, count = nt, len = (int) 1e9, gsi = 0;
        while (ei < ns) {
            if (freq[s.charAt(ei)] > 0)
                count--;
            freq[s.charAt(ei)]--;
            ei++;

            while (count == 0) {
                if (ei - si < len) {
                    len = ei - si;
                    gsi = si;
                }

                if (freq[s.charAt(si)] == 0)
                    count++;
                freq[s.charAt(si)]++;
                si++;
            }
        }

        return len == (int) 1e9 ? "" : s.substring(gsi, gsi + len);
    }

    // https://www.geeksforgeeks.org/smallest-window-contains-characters-string/
    public static String smallestWindowOfItSelf(String str) {
        if (str.length() <= 1)
            return str;
        int[] freq = new int[128];
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (freq[str.charAt(i)] == 0) {
                freq[str.charAt(i)] = 1;
                count++;
            }
        }

        int actualLen = count;
        int si = 0, ei = 0, gsi = 0, len = (int) 1e9;

        while (ei < n) {
            if (freq[str.charAt(ei++)]-- > 0)
                count--;

            while (count == 0) {
                if (ei - si < len) {
                    len = ei - si;
                    gsi = si;
                }

                if (freq[s.charAt(si++)]++ == 0)
                    count++;
            }

            if (len == actualLen)
                break;
        }

        return str.substring(gsi, gsi + len);
    }

    // 340
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s.length() <= k)
            return s.length();

        int n = s.length(), si = 0, ei = 0, len = 0, count = 0;
        int[] freq = new int[128];

        while (ei < n) {
            if (freq[s.charAt(ei++)]++ == 0)
                count++;

            while (count > k) {
                if (freq[s.charAt(si++)]-- == 1)
                    count--;
            }

            len = Math.max(len, ei - si);
        }

        return len;
    }

    // 1456
    public static boolean isVowel(Character ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public int maxVowels(String s, int k) {

        // HashSet<Character> set = new HashSet<>();
        // set.add('a');
        // set.add('e');
        // set.add('i');
        // set.add('o');
        // set.add('u');

        int n = s.length(), si = 0, ei = 0, vowelCount = 0, maxVowelCount = 0;
        while (ei < n) {
            if (isVowel(s.charAt(ei++)))
                vowelCount++;

            if (ei - si == k) {
                maxVowelCount = Math.max(maxVowelCount, vowelCount);
                if (isVowel(s.charAt(si++)))
                    vowelCount--;
            }
        }

        return maxVowelCount;
    }

    // Count all subarrays with atMost k Different Integers.
    public int atMostKDistinct(int[] arr, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int n = arr.length, si = 0, ei = 0, ans = 0;

        while (ei < n) {
            freq.put(arr[ei], freq.getOrDefault(arr[ei], 0) + 1);
            ei++;
            while (freq.size() > k) {

                freq.put(arr[si], freq.get(arr[si]) - 1);
                if (freq.get(arr[si]) == 0) {
                    freq.remove(arr[si]);
                }
                si++;
            }

            ans += ei - si;
        }

        return ans;
    }

    public int atMostKDistinct_02(int[] arr, int k) {
        int[] freq = new int[20000 + 1];
        int n = arr.length, si = 0, ei = 0, count = 0, ans = 0;

        while (ei < n) {
            if (freq[arr[ei++]]++ == 0)
                count++;

            while (count > k) {
                if (freq[arr[si++]]-- == 1)
                    count--;
            }
            ans += ei - si;
        }

        return ans;
    }

    public int subarraysWithKDistinct(int[] arr, int k) {
        return atMostKDistinct(arr, k) - atMostKDistinct(arr, k - 1);
    }

    // 1248
    public int atMostOdd(int[] arr, int k) {
        int n = arr.length, si = 0, ei = 0, count = 0, ans = 0;
        while (ei < n) {
            if ((arr[ei++] & 1) != 0)
                count++;

            while (count > k) {
                if ((arr[si++] & 1) != 0)
                    count--;
            }

            ans += ei - si;
        }

        return ans;
    }

    public int numberOfSubarrays(int[] nums, int k) {
        return atMostOdd(nums, k) - atMostOdd(nums, k - 1);
    }

    // 904
    public int totalFruit(int[] arr) {
        int n = arr.length, si = 0, ei = 0, len = 0, count = 0;
        int[] freq = new int[40000 + 1];

        while (ei < n) {
            if (freq[arr[ei++]]++ == 0)
                count++;

            while (count > 2)
                if (freq[arr[si++]]-- == 1)
                    count--;

            len = Math.max(len, ei - si);
        }

        return len;
    }

    // 930
    public int atMostSum(int[] arr, int tar) {
        int n = arr.length, si = 0, ei = 0, sum = 0, count = 0;
        while (ei < n) {
            sum += arr[ei++];

            while (sum > tar)
                sum -= arr[si++];

            count += ei - si;
        }

        return count;
    }

    public int numSubarraysWithSum(int[] arr, int tar) {
        return atMostSum(arr, tar) - (tar != 0 ? atMostSum(arr, tar - 1) : 0);
    }

    // 485
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length, si = 0, ei = 0, len = 0, count = 0;
        while (ei < n) {
            if (nums[ei++] == 0)
                count++;

            while (count == 1) {
                if (nums[si++] == 0)
                    count--;
            }

            len = Math.max(len, ei - si);
        }
        return len;
    }

    public int findMaxConsecutiveOnes_01(int[] nums) {
        int n = nums.length, si = 0, ei = 0, len = 0;
        while (ei < n) {
            if (nums[ei] == 0) {
                ei++;
                si = ei;
            } else {
                ei++;
            }

            len = Math.max(len, ei - si);
        }
        return len;
    }

    // 487
    public int findMaxConsecutiveOnes(int[] arr) {
        int n = arr.length, si = 0, ei = 0, count = 0, len = 0;

        while (ei < n) {
            if (arr[ei++] == 0)
                count++;

            while (count == 2)
                if (arr[si++] == 0)
                    count--;

            len = Math.max(len, ei - si);
        }

        return len;
    }

    public int findMaxConsecutiveOnes_01(int[] arr) {
        int n = arr.length, si = 0, ei = 0, firstZeroIndex = -1, len = 0;

        while (ei < n) {
            if (arr[ei++] == 0) {
                si = firstZeroIndex + 1;
                firstZeroIndex = ei - 1;
            }

            len = Math.max(len, ei - si);
        }

        return len;
    }

    // 1004
    public int longestOnes(int[] arr, int k) {
        int n = arr.length, si = 0, ei = 0, count = 0, len = 0;

        while (ei < n) {
            if (arr[ei++] == 0)
                count++;

            while (count > k)
                if (arr[si++] == 0)
                    count--;

            len = Math.max(len, ei - si);
        }

        return len;
    }

}