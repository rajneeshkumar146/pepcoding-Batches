public class test{
    public static void main(String[] args){
     int[] arr=new int[10];
     input(arr);
     for (int i = 0; i < arr.length; i++) System.out.print(arr[i]+ " ");
     System.out.println();
    }


    public static void input(int[] arr){
    for (int i = 0; i < arr.length; i++) System.out.print(arr[i]+ " ");
    System.out.println();
    
    for (int i = 0; i < arr.length; i++)
        arr[i] = i * 10;
    
    for (int i = 0; i < arr.length; i++) System.out.print(arr[i]+ " ");
    System.out.println();
}
}