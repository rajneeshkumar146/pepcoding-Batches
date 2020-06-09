import java.util.HashMap;
import java.util.ArrayList;
public class l001{
    public static void main(String[] args){
        // basics_01();
        // basics_02();
        freqMapINDX("asvbasnmvcsanvcsabnvc");
    }

public static void basics_01()
{
    HashMap<String, Integer> map=new HashMap<>();
    map.put("IND",1000);
    map.put("USA",10);
    map.put("CHINA",-1000);
    map.put("UK",1);
    map.put("NEPAL",90);
    map.put("NULL",9);

    System.out.println(map.get("INd"));
    // ArrayList<String> arr=new ArrayList<>(map.keySet());
    // System.out.println(arr);

    // System.out.println(map);
    // for(String s: map.keySet()){
    //     System.out.println(map.get(s));
    // }
}

public static void basics_02()
{

    HashMap<Integer, ArrayList<String>> map=new HashMap<>();
    for(int i=1;i<=10;i++){
        map.put(i,new ArrayList<>());
    }

    map.putIfAbsent(5,new ArrayList<>());

    map.get(5).add("abcd");
    map.get(5).add("abdf");
    map.get(5).add("abcdfd");
    
    map.putIfAbsent(15,new ArrayList<>());

    System.out.println(map);
    System.out.println(map.get(5));
}

public static void freqMap(String str){
    HashMap<Character, Integer> map=new HashMap<>();
    
    for(int i=0;i<str.length();i++){
        char ch=str.charAt(i);
        map.put(ch,map.getOrDefault(ch,0)+1);
    }

    System.out.println(map);
}

public static void freqMapINDX(String str){
    HashMap<Character, ArrayList<Integer>> map=new HashMap<>();
    
    for(int i=0;i<str.length();i++){
        char ch=str.charAt(i);
        map.putIfAbsent(ch,new ArrayList<>());
        map.get(ch).add(i);
    }

    System.out.println(map);
}


}