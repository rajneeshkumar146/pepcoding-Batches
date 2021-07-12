import java.util.HashMap;
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
        //     char ch = str.charAt(i);
        //     map.put(ch, map.getOrDefault(ch, 0) + 1);
        // }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.putIfAbsent(ch,0);
            map.put(ch, map.get(ch) + 1);
        }

        for (char ch : map.keySet()) {
            System.out.println(ch + " -> " + map.get(ch));
        }
    }

    public static void IndexOfChar(String str) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        // for (int i = 0; i < str.length(); i++) {
        //     char ch = str.charAt(i);
        //     if (!map.containsKey(ch))
        //         map.put(ch, new ArrayList<>());

        //     map.get(ch).add(i);
        // }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.putIfAbsent(ch,new ArrayList<>());
            map.get(ch).add(i);
        }

        for (char ch : map.keySet()) {
            System.out.println(ch + " -> " + map.get(ch));
        }
    }

    public static void main(String[] args) {
        // HashMapBasic();
        IndexOfChar("aaasmnavnbasvnammansvdbamnsbdsmdvbn");
    }
}