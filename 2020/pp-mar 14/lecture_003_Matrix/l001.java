import java.util.*;

public class l001 {
    public static Scanner scn = new Scanner(System.in);

    public static void input(int[][] arr) {
        int n = arr.length; // no of rows
        int m = arr[0].length; // no of colums

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
    }

    public static void display(int[][] arr) {
        int n = arr.length; // no of rows
        int m = arr[0].length; // no of colums

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void display2(int[][] arr) {
        for (int[] ar : arr) {
            for (int e : ar) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    public static int maximum(int[][] arr) {
        int maxValue = -(int) 1e9;
        int n = arr.length, m = arr[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxValue = Math.max(maxValue, arr[i][j]);
            }
        }

        return maxValue;
    }

    public static int minimum(int[][] arr) {
        int minValue = (int) 1e9;
        int n = arr.length, m = arr[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                minValue = Math.min(minValue, arr[i][j]);
            }
        }

        return minValue;
    }

    public static boolean find(int[][] arr, int data) {
        boolean res = false;
        int n = arr.length, m = arr[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res = arr[i][j] == data;
                if (res)
                    return res;
            }
        }

        return res;
    }

    public static void stateOfWakanda(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        for (int j = 0; j < m; j++) {
            if (j % 2 == 0) {
                for (int i = 0; i < n; i++)
                    System.out.println(arr[i][j]);
            } else {
                for (int i = n - 1; i >= 0; i--)
                    System.out.println(arr[i][j]);
            }
        }
    }

    public static void stateOfWakanda_FB(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < m; j++)
                    System.out.println(arr[i][j]);
            } else {
                for (int j = m - 1; j >= 0; j--)
                    System.out.println(arr[i][j]);
            }
        }
    }

    //stateOfWakanda2
    public static void diagonalPrint(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        for (int gap = 0; gap < m; gap++) {
            for (int i = 0, j = gap; i < n && j < m; i++, j++) {
                System.out.println(arr[i][j]);
            }
        }
    }

    public static void FulldiagonalPrint(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        for (int gap = n-1; gap >= 1; gap--) {
            for (int i = gap, j = 0; i < n && j < m; i++, j++) {
                System.out.println(arr[i][j]);
            }
        }
        
        for (int gap = 0; gap < m; gap++) {
            for (int i = 0, j = gap; i < n && j < m; i++, j++) {
                System.out.println(arr[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[scn.nextInt()][scn.nextInt()];
        input(arr);
        display(arr);
    }
}