public class client {

    public static void test() {
        HashMap map = new HashMap();
        map.put(100, 1);
        map.put(200, 3);
        map.put(300, 7);
        map.put(400, 18);
        map.remove(400);
        map.put(200, 30);

        System.out.println(map.keySet());
    }

    public static void main(String[] args) {
        test();
    }
}