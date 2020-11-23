import java.util.HashMap;
import java.util.ArrayList;

public class l001{

    public static void set1(){
        HashMap<String,Integer> map = new HashMap<>();
        map.put("IND",10000);
        map.put("USA",1000);
        map.put("NEP",100);
        map.put("UK",990);
        map.put("IND",100000);
        map.put("PAK",10);
        map.put(null,null);
        map.put(null,2);

        // System.out.println(map.getOrDefault("INd",100));
        // map.remove("IND");
        // System.out.println(map.keySet()); // O(n)
        
        //ArrayList<String> keys = new ArrayList<>(map.keySet());
        for(String s: map.keySet()){
            System.out.println(s + " : " + map.get(s));
        }

        System.out.println(map.containsKey("INd"));

        // System.out.println(map);
    }

    public static void freqMap(String str){
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            map.put(ch,map.getOrDefault(ch,0) + 1);

            // if(map.containsKey(ch)){
            //     map.put(ch,map.get(ch) + 1);
            // }else{
            //     map.put(ch,1);
            // }
        }

        System.out.println(map);
    }

    public static void freqMap2(String str){
        HashMap<Character,ArrayList<Integer>> map = new HashMap<>();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            
            if(!map.containsKey(ch)){
                map.put(ch,new ArrayList<>());
            }
            map.get(ch).add(i);
        }

        for(Character ch : map.keySet()){
            System.out.println(map.get(ch));
        }
    }

    public static void main(String[] args){
        // set1();
        // freqMap("jskcsmjsASDaassacsacascsvsacasacasc");
        freqMap2("jskcsmjsAAASSSDaassacsmmacascsSSvsacasacccasc");
    }

}