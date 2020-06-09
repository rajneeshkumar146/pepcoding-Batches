import java.util.ArrayList;
public class client{
    public static void main(String[] args){
        // hashmap<Integer,String> map=new hashmap<>();
        // map.put(10,"abc");
        // map.put(20,"def");
        // map.put(30,"ghi");
        // map.put(40,"jkl");
        // map.put(50,"mno");

        // map.display();
        // System.out.println(map.keySet());
        freqMapOfIDX("jhjhvcjkkjjkvcjvcajskcvjkacvjsvcsvcskdc");
    }

    public static void freqMapOfIDX(String str){
        hashmap<Character,ArrayList<Integer>> map=new hashmap<>();
       
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);

            if(!map.containsKey(ch)) map.put(ch,new ArrayList<>());
           
            // map.putIfAbsent(ch,new ArrayList<>());
            map.get(ch).add(i);
        }

        for(char ch:map.keySet()){
            System.out.println(ch+ " -> " + map.get(ch));
         }
    }
}