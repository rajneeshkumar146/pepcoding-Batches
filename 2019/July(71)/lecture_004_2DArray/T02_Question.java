import java.util.Scanner;

import sun.security.util.Length;

import java.util.ArrayList;

public class T01_Basics {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        int n = scn.nextInt();
        int m = scn.nextInt();

        int[][] arr = new int[n][m];
        inputArray(arr);

        // display(arr);
    }

    
    public static void input(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
    }

    public static void display(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void exitPoint(int[][] arr) {
        int dir = 0, r = 0, c = 0;
        while (true) {
            dir = (dir + arr[r][c]) % 4;
            if (dir == 0) {
                c++;
                if (c == arr[0].length) {
                    System.out.println(r + ", " + (c - 1));
                    break;
                }
            } else if (dir == 1) {
                r++;

                if (r == arr.length) {
                    System.out.println((r - 1) + ", " + c);
                    break;
                }
            } else if (dir == 2) {
                c--;

                if (c == -1) {

                    System.out.println(r + ", " + (c + 1));
                    break;
                }
            }

            else {
                r--;

                if (r == -1) {

                    System.out.println((r + 1) + ", " + c);
                    break;
                }
            }
        }
    }

    public static void turnByNintyDegree(int[][] arr, int rot) {
        transpose(arr);
        if (rot == 90) {
            reverseRow(arr);
        } else {
            reversecol(arr);
        }

    }

    public static void transpose(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr[0].length; j++) {
                swap(arr, i, j, j, i);
            }

        }
    }

    public static void reversecol(int[][] arr) {
        for (int c = 0; c < arr[0].length; c++) {
            int i = 0, j = arr.length - 1;
            while (i < j) {
                swap(arr, i, c, j, c);
                i++;
                j--;
            }

        }

    }

    public static void reverseRow(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            int i = 0, j = arr[0].length - 1;
            while (i < j) {
                swap(arr, r, i, r, j);
                i++;
                j--;
            }

        }

        // =======================
        // for (int rs = 0, re = arr.length - 1; rs < re; rs++, re--) {
        // for (int i = 0; i < arr[0].length; i++) {
        // swap(arr, rs, i, re, i);
        // i++;
        // j--;
        // }

        // }

    }

    public static void swap(int[][] arr, int i, int j, int i1, int j1) {
        int temp = arr[i][j];
        arr[i][j] = arr[i1][j1];
        arr[i1][j1] = temp;
    }

    public static void trace(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length && i < arr[0].length; i++) {
            sum += arr[i][i];
        }
        System.out.println(sum);
    }

    public static void matrixMultiplicatin(int[][] mat1, int[][] mat2) {
        if (mat1[0].length != mat2.length)
            return;

        int[][] ans = new int[mat1.length][mat2[0].Length];

        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                for (int k = 0; k < mat1[0].length; k++) {
                    ans[i][j] += mat1[i][k] * mat2[k][j];
                }

            }
        }

    }

}
