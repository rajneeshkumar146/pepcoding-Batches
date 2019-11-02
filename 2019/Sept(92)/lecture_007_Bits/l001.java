public class l001{
    public static void main(String[] args){
        // ON_OFF(22538924) //1010101111110101010101100
        // OFF_ON(22538924) //1010101111110101010101100
    
      //  noOfSetBits_01(-1);
      //  noOfSetBits_02(-1);
      //  noOfSetBits_03(-1);
    
       int[] arr={5,2,8,7,14,10,7,5,8,10,7,2,10,8,2,5};
      uniqueForK(arr,3);
    
    } 

    public static void oddEven(int n){

    }

    public static void ON_OFF(int n){
           
    }


    public static void OFF_ON(int n){
           
    }
 
    public static void noOfSetBits_01(int n){
          int count=0;
          for(int i=0;i<32;i++){
            int mask=(1<<i);
            if((n&mask)!=0) count++;
          }

          System.out.println(count);
    }


    public static void noOfSetBits_02(int n){
        int count=0;
        while(n!=0){
           if((n&1)!=0) count++;

           n>>>=1;
        }

        System.out.println(count);
  }

  public static void noOfSetBits_03(int n){
    int count=0;
    while(n!=0){
       n&=(n-1);
       count++;
    }

    System.out.println(count);
}

public static void unique_01(int[] arr){
    int ans=0;
    for(int i=0;i<arr.length;i++){
      ans^=arr[i];
    }

    System.out.println(ans);
}

public static void uniqueForK(int[] arr,int k){
    int[] bits=new int[32];

    for(int ele:arr){

         for(int i=0;i<32;i++){
             int mask=(1<<i);
             if((ele&mask)!=0){
                bits[i]+=1;
             }
         }
    }
    // for(int ele: bits){
    //   System.out.print(ele + " ");
    // }

    int ans=0;
    for(int i=0;i<32;i++){
      if(bits[i] % k != 0){
        ans |= (1<<i);
      }
    }

    System.out.println(ans);


}






}