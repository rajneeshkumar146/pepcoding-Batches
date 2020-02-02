import java.util.HashMap;

public class l001{

    public static void main(String[] args){
        basicFunctions();

    }


   public static void basicFunctions(){
     HashMap<String,Integer> map=new HashMap<>();
     map.put("India",1000);
     map.put("nepal",400);
     map.put("pakistan",-1000);
     map.put("USA",0);

     for(String keys: map.keySet()){
         System.out.println(map.get(keys));
     }
   }




}