import java.util.HashMap;

public class l001_basic {

    public static void basic_01() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Nepal", 233);
        map.put("UK", 45);
        map.put("Germany", 35);
        map.put("USA", 20);
        map.put("Russia", 18);
        map.put("India", 10);
        map.put("USA", 19);

        // System.out.println(map);
        // for(String keys : map.keySet()){
        // System.out.println(keys + " -> " + map.get(keys));
        // }

        String key = "USA";
        if (map.containsKey(key))
            System.out.println(map.get(key));
        else
            System.out.println("not Found");

    }

    public static void printFrequency(String str){


    }

    public static void highestFreqChar(String str){


    }

    public static void main(String[] args) {
        basic_01();

    }

}