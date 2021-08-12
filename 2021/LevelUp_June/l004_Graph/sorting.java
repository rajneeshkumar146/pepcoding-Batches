import java.util.Arrays;

public class sorting {

    public static void display(int[] arr) {
        for (int e : arr)
            System.out.print(e + " ");
        System.out.println();
    }

    public static void display2D(int[][] arr) {
        for (int[] a : arr)
            display(a);
        System.out.println();
    }

    public static void sortArray(int[][] arr) {
        // {{A,B,C}}, sort on the basis of index 2
        Arrays.sort(arr, (a, b) -> {
            // return a[2] - b[2]; // this- other,default beahviour
            return b[2] - a[2]; // other - this,reverse of default beahviour
        });

        display2D(arr);
    }

    public static void main(String[] args) {
        int[][] arr = { { 1, 2, 3 }, { 1, 2, 2 }, { 4, 2, -3 }, { 10, 22, 23 } };
        sortArray(arr);
    }

}