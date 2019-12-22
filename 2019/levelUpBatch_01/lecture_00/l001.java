import java.util.ArrayList;
public class l001 {
    public static void main(String[] args) {
        // System.out.println(euler(5,0));
        // System.out.println(fact(5));
        System.out.println(subSeq("abc"));
    }

    public static int fact(int n){
        return n!=0?fact(n-1)*n:1;
    }

    public static int[] allIndex(int[] arr,int vidx,int data ,int count){
        if(vidx==arr.length){
            int[] baseArray=new int[count];
            return baseArray;
        }

        if(arr[vidx]==data) count++;
        int[] recRes=allIndex(arr,vidx+1,data,count);
        if(arr[vidx]==data) recRes[count-1] = vidx;


        return recRes;
    }

    public static ArrayList<String> subSeq(String str){
        if(str.length()==0) {
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        char ch=str.charAt(0); // str[0];
        ArrayList<String> recAns=subSeq(str.substring(1));

        ArrayList<String> myAns=new ArrayList<>();
        
        int size=recAns.size();
        for(int i=0;i<size;i++){
            myAns.add(recAns.get(i));
            myAns.add(recAns.get(i)+ch);
        }
        return myAns;
    }

    

    public static int euler(int n,int level){
      if(n<=1){
          System.out.println("Base: "+ n +"@" + level);
          return n;
      }
      
      System.out.println("Pre: "+ n + "@" +level);

      int a=euler(n-1,level+1);
      System.out.println("In: "+ n + "@" +level);
      int b=euler(n-2,level+1);
      
      System.out.println("Post: "+ n+ "@" +level);

      return a+b+3;
    }

    public static int euler2(int n,int level){
        if(n<=2){
            System.out.println("Base: "+ n*level);
            return n;
        } 
        System.out.println("Pre: "+ n*level);
        int a=euler2(n-1,level+1);
        
        System.out.println("In1: "+ n*level);
        int b=euler2(n-2,level+1);
        
        System.out.println("In2: "+ n*level);
        int c=euler2(n-3,level-1);

        System.out.println("Post: "+ n*level);
        return a+b+c+3;
      }


}