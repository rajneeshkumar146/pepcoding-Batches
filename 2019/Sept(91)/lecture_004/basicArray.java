import java.util.Scanner;

public class basicArray {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        input(arr);
        display(arr);
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
}