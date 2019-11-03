public class l002_arrayRecu{
    public static void main(String[] args){

     }


     public static int[] allIdx(int[] arr,int idx,int data,int count){
           if(idx==arr.length){
               int[] base=new int[count];
               return base;
           }

      if(arr[idx]==data){
          count++;
      }

      int[] recAns=allIdx(arr,idx+1,data,count);

      if(arr[idx]==data){
        recAns[count-1]=idx;
      }

      return recAns;


     } 




}