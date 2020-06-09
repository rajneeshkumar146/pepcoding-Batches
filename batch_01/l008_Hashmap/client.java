public class client {

    public static void main(String[] args) {
        Hashmap<String, Integer> map = new Hashmap();
        map.put("abc", 1000);
        map.put("asd", 1001);
        map.put("abc", 1002);
        map.put("erg", 1002);
        map.put("erg", 1003);
        map.put("erg", 11111);

        System.out.println(map);
        map.put("sd", 500);

        System.out.println(map);
    }
}