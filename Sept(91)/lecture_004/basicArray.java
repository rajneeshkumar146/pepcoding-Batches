import java.util.Scanner;

public class basicArray {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        input(arr);
        // display(arr);
        
        // System.out.println(find(arr,20));
        rotate(arr,-2);
    }

    public static void input(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        display(arr);
    }

    public static void display(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }

        System.out.println();

    }

    public static boolean find(int[] arr,int data){
        for(int ele:arr ){
            if(ele==data) return true;
        }

        return false;
    }

    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

 public static void reverse(int[] arr,int si,int ei){
     while(si<ei){
         swap(arr,si,ei);
         si++;
         ei--;
     }
 }

 public static void rotate(int[] arr,int r){
     r%=arr.length;
     r=r<0?r+arr.length:r;

     int[] ans=new int[arr.length];
     int k=0;
     for(int i=r;i<arr.length;i++){
      ans[k]=arr[i];
      k++;
     }

     for(int i=0;i<r;i++){
        ans[k]=arr[i];
        k++;
     }

     display(ans);

 }






}