import java.util.*;

public class l004_Arrays {
    public static Scanner scn = new Scanner(System.in);

    public static void test1(int n) {
        // type[] nameOfArray = new type[size]; with default value 0.
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void display1(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " "); // get
        }
    }

    public static void display2(int[] arr) {

        // forEach loop used for :
        // 1. only for get,  if you try to set value ypu get a beutifull error.
        // 2. automatically increment by 1
        // 3. always in forward direction
        // 4. range of loop : [0 , n - 1]

        for (int ele : arr) {
            System.out.print(ele + " ");
        }
    }

    public static void input1(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt(); // set
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        System.out.println(arr);
        input1(arr);
        display1(arr);
    }
}