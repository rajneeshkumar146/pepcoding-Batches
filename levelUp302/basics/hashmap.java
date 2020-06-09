import java.util.HashMap;
import java.util.ArrayList;
public class hashmap{
    public static void main(String[] args){
       set1();
    }

    public static void set1(){
        HashMap<String,Integer> map=new HashMap<>();
        map.put("India",10000);
        map.put("USA",1000);
        map.put("china",10);
        map.put("PAK",100000);
        map.put("UK",900);

        //get
        System.out.println(map.get("USA"));

        if(map.containsKey("Usa"))
        System.out.println(map.get("Usa"));  // return null.

        //getOrDefault.
        System.out.println(map.getOrDefault("Usa",0)); 

        //putIfAbsent
        map.putIfAbsent("Usa",10);
        
        
        //print
        System.out.println(map);
        
        //keySet
        ArrayList<String> keys=new ArrayList<>(map.keySet());
        System.out.println(keys);

        //loop
        for(String key: map.keySet()){
            System.out.println(key);
        }

    }
}