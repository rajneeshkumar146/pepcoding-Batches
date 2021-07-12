import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class l002_HMBasics {
    public static void HashMapBasic() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("India", 98);
        map.put("USA", 99);
        map.put("UK", 98);
        map.put("UK", 95);

        // map.remove("UK");
        if (map.containsKey("UK"))
            System.out.println(map.get("UK"));

        // System.out.println(map);
        // ArrayList<String> keys = new ArrayList<>(map.keySet());
        // System.out.println(keys);
        for (String key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }
    }

    public static void frequency(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        // for (int i = 0; i < str.length(); i++) {
        // char ch = str.charAt(i);
        // if (map.containsKey(ch))
        // map.put(ch, map.get(ch) + 1);
        // else
        // map.put(ch, 0 + 1);
        // }

        // for (int i = 0; i < str.length(); i++) {
        // char ch = str.charAt(i);
        // map.put(ch, map.getOrDefault(ch, 0) + 1);
        // }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.putIfAbsent(ch, 0);
            map.put(ch, map.get(ch) + 1);
        }

        for (char ch : map.keySet()) {
            System.out.println(ch + " -> " + map.get(ch));
        }
    }

    public static void IndexOfChar(String str) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        // for (int i = 0; i < str.length(); i++) {
        // char ch = str.charAt(i);
        // if (!map.containsKey(ch))
        // map.put(ch, new ArrayList<>());

        // map.get(ch).add(i);
        // }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.putIfAbsent(ch, new ArrayList<>());
            map.get(ch).add(i);
        }

        for (char ch : map.keySet()) {
            System.out.println(ch + " -> " + map.get(ch));
        }
    }

    public static void intersectionWithoutDuplicate(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : arr1)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        for (int ele : arr2) {
            if (map.containsKey(ele)) {
                System.out.println(ele);
                map.remove(ele);
            }
        }
    }

    public static void intersectionWithDuplicate(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : arr1)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        for (int ele : arr2) {
            if (map.containsKey(ele)) {
                System.out.println(ele);
                map.put(ele, map.get(ele) - 1);
                if (map.get(ele) == 0)
                    map.remove(ele);
            }
        }
    }

    public static void highestFreqChar(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int maxFreq = 0;
        char ans = '\u0000';
        for (char ch : map.keySet()) {
            if (map.get(ch) > maxFreq) {
                maxFreq = map.get(ch);
                ans = ch;
            }
        }

        System.out.println(ans);
    }

    public static int longestConsecutiveSequence(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int ele : arr)
            set.add(ele);

        int len = 0, head = 0;
        for (int ele : arr) {
            if (!set.contains(ele))
                continue;

            int left = ele - 1, right = ele + 1;
            set.remove(ele);

            while (set.contains(left))
                set.remove(left--);

            while (set.contains(right))
                set.remove(right++);

            if (right - left - 1 > len) {
                len = right - left - 1;
                head = left + 1;
            }
        }

        for (int i = 0; i < len; i++) {
            System.out.println(head + i);
        }

    }

    public static void main(String[] args) {
        // HashMapBasic();
        // IndexOfChar("aaasmnavnbasvnammansvdbamnsbdsmdvbn");
        highestFreqChar("zmszeqxllzvheqwrofgcuntypejcxovtaqbnqyqlmrwitc");
    }
}