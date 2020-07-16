import java.util.HashMap;
import java.util.ArrayList;

public class l001HM {

    public static void FreqMap(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (map.containsKey(ch))
                map.put(ch, map.get(ch) + 1);
            else
                map.put(ch, 1);
        }

        for (char ch : map.keySet()) {
            System.out.println(ch + " -> " + map.get(ch));
        }
    }

    public static void FreqMap2(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (char ch : map.keySet()) {
            System.out.println(ch + " -> " + map.get(ch));
        }
    }

    public static void FreqMap3(String str) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            map.putIfAbsent(ch, new ArrayList<Integer>());

            ArrayList<Integer> ar = map.get(ch);
            ar.add(i);
        }

        for (char ch : map.keySet()) {
            System.out.println(ch + " -> " + map.get(ch));
        }
    }

    public static void main(String[] args) {
        String str = "aasbvaabababababccc";
        FreqMap3(str);
    }

}