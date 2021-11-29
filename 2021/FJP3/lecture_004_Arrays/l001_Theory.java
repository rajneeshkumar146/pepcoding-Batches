import java.util.Scanner;

public class l001_Theory {
    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();   // setter
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");  // getter
        }
        System.out.println();
    }

    public static boolean findElement(int[] arr,int data){

    }

    public static int maximum(int[] arr){

    }

    public static int minimum(int[] arr){

    }



    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        takeInput(arr);
        printArray(arr);

    }
}