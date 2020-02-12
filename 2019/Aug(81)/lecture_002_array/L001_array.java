import java.util.Scanner;
   
public class L001_array{
public static Scanner scn=new Scanner(System.in);


   public static void main(String[] args){
      int n=scn.nextInt();
      int[] arr=new int[n];
      input(arr);
      display(arr);

      reverse(arr,0,arr.length-1);
      display(arr);
      
      
}

public static void input(int[] arr){
    for(int i=0;i<arr.length;i++){
      arr[i]=scn.nextInt();
    }
}


public static void display(int[] arr){
    // for(int i=0;i<arr.length;i++){
    //   System.out.print(arr[i]+" ");
    // }

    for(int i: arr){
        System.out.print(i + " ");
    }

    System.out.println();
}


public static boolean find(int[] arr,int data){
    for(int i=0;i<arr.length;i++){
       if(arr[i]==data){
           return true;
       }
    }

    return false;
}


public static int maximum(int[] arr,int data){
    int max_=arr[0];
    for(int i=0;i<arr.length;i++){
       if(arr[i]>max_){
           max_=arr[i];
       }
    }

    return max_;
}

  public static  void swap(int[] arr,int x,int y){
      int temp=arr[x];
      arr[x]=arr[y];
      arr[y]=temp;
  }

   public static void reverse(int[] arr,int si,int ei){
      
     while(si<ei){
      swap(arr,si,ei);
      si++;
      ei--;
     }
   }

   public static void rotate(int[] arr,int k){
       k %= arr.length;
       k = k<0 ? k+arr.length: k;

       reverse(arr,0,k-1);
       reverse(arr,k,arr.length-1);
       reverse(arr,0,arr.length-1);    

   }

   public static int BS(int[] arr,int data){

       int si=0;
       int ei=arr.length-1;

       while(si<=ei){
          int mid=(si+ei)/2;

          if(arr[mid]==data){
              return mid;
          }else if(arr[mid]>data)
             ei=mid-1;
           else  
              si=mid+1;
       }

       return -1;

   }

   public static int BSLB(int[] arr,int data){

       int si=0;
       int ei=arr.length-1;

       while(si<=ei){
          int mid=(si+ei)/2;

          if(arr[mid]==data){

       if(mid-1>=0 && arr[mid-1]==arr[mid])
            ei=mid-1;
        else
            return mid;
          }else if(arr[mid]>data)
             ei=mid-1;
           else  
              si=mid+1;
       }

       return -1;

   }


   public static int BSUB(int[] arr,int data){

       int si=0;
       int ei=arr.length-1;

       while(si<=ei){
          int mid=(si+ei)/2;

          if(arr[mid]==data){

       if(mid+1<arr.length && arr[mid+1]==arr[mid])
            si=mid+1;
        else
            return mid;
          }else if(arr[mid]>data)
             ei=mid-1;
           else  
              si=mid+1;
       }

       return -1;

   }


   public static int BSNearest(int[] arr,int data){

       int si=0;
       int ei=arr.length-1;

       while(si<=ei){
          int mid=(si+ei)/2;

          if(arr[mid]==data){
              return mid;
          }else if(arr[mid]>data)
             ei=mid-1;
           else  
              si=mid+1;
       }

       if(ei==-1) return si;
       else if(si==arr.length) return ei;
       else if(data-arr[ei]<=arr[si]-data)
          return ei;
        else return si;
   }

   
}