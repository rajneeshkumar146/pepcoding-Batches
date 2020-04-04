import java.util.HashMap;
import java.util.ArrayList;

public class Question{
    public static void main(String[] args){
        basicFunctions();
        // freqMap("jhjhvcjkkjjkvcjvcajskcvjkacvjsvcsvcskdc");
        // freqMapOfIDX("jhjhvcjkkjjkvcjvcajskcvjkacvjsvcsvcskdc");
    }


    public static void basicFunctions(){
        HashMap<String,Integer> CoronaReport=new HashMap<>();
        
        CoronaReport.put("USA",291545);
        CoronaReport.put("SPAIN",124736);
        CoronaReport.put("ITALY",119827);
        CoronaReport.put("INDIA",3082);
        CoronaReport.put("USA",295545);

        System.out.println(CoronaReport);
        // if(CoronaReport.containsKey("USA"))
        //    System.out.println(CoronaReport.get("USA"));
        System.out.println(CoronaReport.getOrDefault("USA",100));
        System.out.println(CoronaReport.keySet());
    }

    public static void freqMap(String str){
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
           
            // if(!map.containsKey(ch))
            //     map.put(ch,1);
            // else
            //     map.put(ch,map.get(ch)+1);

            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        System.out.println(map);

        for(char ch:map.keySet()){
           System.out.println(ch+ " -> " + map.get(ch));
        }
    }

    public static void freqMapOfIDX(String str){
        HashMap<Character,ArrayList<Integer>> map=new HashMap<>();
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);

            // if(!map.containsKey(ch)) map.put(ch,new ArrayList<>());
           
            map.putIfAbsent(ch,new ArrayList<>());
            map.get(ch).add(i);
        }

        for(char ch:map.keySet()){
            System.out.println(ch+ " -> " + map.get(ch));
         }
    }

    public static void intersectionSimple(int[] one,int[] two){
        HashMap<Integer,Integer> map=new HashMap<>();
        
        for(int ele:one){
            map.put(ele,map.getOrDefault(ele,0)+1);
        }

        for(int ele: two){
            if(map.containsKey(ele)){
                System.out.print(ele+" ");
                map.remove(ele);
            }
        }
    }

    public static void intersection(int[] one,int[] two){
        HashMap<Integer,Integer> map=new HashMap<>();
        
        for(int ele:one){
            map.put(ele,map.getOrDefault(ele,0)+1);
        }

        for(int ele: two){
            if(map.containsKey(ele)){
                System.out.print(ele+" ");
                map.put(ele,map.get(ele)-1);
                if(map.get(ele)==0) map.remove(ele);
            }
        }

    }


}