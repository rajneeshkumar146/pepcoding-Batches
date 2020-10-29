public class client{
    public static void main(String[] args){
        hashmap<String,Integer> map = new hashmap<>();
        map.put("A",45);
        map.put("B",46);
        map.put("BSD",46);
        map.put("DSDFS",46);
        map.put("DSDFS",456);
        
        System.out.println(map.getOrDefault("b",10));
    }
}