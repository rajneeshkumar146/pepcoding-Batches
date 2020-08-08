import java.util.Scanner;
import java.util.ArrayList;

public class doubt{
    public static Scanner scn=new Scanner(System.in);
    
    public static void main(String... args){
       
        String str = scn.nextLine();
        ArrayList<Integer> list=new ArrayList<>();

        while(true){
            str=scn.nextLine();
            if(str.equals("]")) break;
            str=str.substring(5,str.length()-1);

            String[] arr=str.split(", ");
            for(int i=0;i<arr.length;i++){
                list.add(Integer.parseInt(arr[i]));
            }
        }

        System.out.println(list);   
    }
}