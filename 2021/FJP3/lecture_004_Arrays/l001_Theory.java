import java.util.Scanner;

public class l001_Theory {
    public static Scanner scn = new Scanner(System.in);

    public static void takeInput(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt(); // setter
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " "); // getter
        }
        System.out.println();
    }

    public static boolean findElement(int[] arr, int data) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == data)
                return true;
        }
        return false;
    }

    public static int maximum(int[] arr) {
        int n = arr.length, maxEle = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            maxEle = Math.max(maxEle, arr[i]);
        }

        return maxEle;
    }

    public static int minimum(int[] arr) {
        int n = arr.length, minEle = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            minEle = Math.min(minEle, arr[i]);
        }

        return minEle;
    }

    // {2,3,4,4,4,4,4,4,4,2}, firstIndex of 4 is 2
    public static int firstIndex(int[] arr, int data) {

    }

    // {2,3,4,4,4,4,4,4,4,2}, lastIndex of 4 is 8
    public static int lastIndex(int[] arr, int data) {
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        takeInput(arr);
        printArray(arr);

    }
}