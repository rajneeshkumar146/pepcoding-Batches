import java.util.HashMap;
import java.util.HashSet;

public class l001Basic{

    public static void set1(){
        HashMap<String,Integer> map = new HashMap<>();
        map.put("India",1000);
        map.put("USA",100);
        map.put("Nepal",70);
        map.put("ENG",150);
        map.put("PAK",-1000);

        System.out.println(map);

        map.put("Nepal",60);
        System.out.println(map);

        System.out.println(map.get("Russia"));
        System.out.println(map.getOrDefault("Nepal",10));
    }

    public static void getFreq(){
        String str = "ksjdbcksdcjbkjscsdsdsdcsdWDqwdsadsaskjcsdbibsdncsjk";

        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            
            // if(map.containsKey(ch)) map.put(ch,map.get(ch) + 1);
            // else map.put(ch,1);

            map.put(ch,map.getOrDefault(ch,0) + 1);
        }

        for(Character ch : map.keySet()){
            System.out.println(ch + " -> " + map.get(ch));
        }
    }

    public static void solve(){
        // set1();
        getFreq();

    }

    public static void main(String[] args){
        solve();

    }




}