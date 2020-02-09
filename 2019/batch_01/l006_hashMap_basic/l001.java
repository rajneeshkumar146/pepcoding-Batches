import java.util.HashMap;

public class l001 {

    public static void main(String[] args) {
        // basicFunctions();
        Node node = new Node(10, 100);
        System.out.println(node);

    }

    public static class Node {
        Integer key;
        Integer val;

        Node(Integer key, Integer val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return  this.key + "=" + this.val;
        }
    }

    public static void basicFunctions() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("India", 1000);
        map.put("nepal", 400);
        map.put("pakistan", -1000);
        map.put("USA", 0);

        // for(String keys: map.keySet()){
        // System.out.println(map.get(keys));
        // }

        System.out.println(map);
    }

}