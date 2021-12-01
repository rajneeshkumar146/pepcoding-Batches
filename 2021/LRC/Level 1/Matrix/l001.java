import java.io.*;
import java.util.*;

public class Main {

    public static void spiralDisplay(int[][] arr) {

        int n = arr.length, m = arr[0].length;
        int rmin = 0, cmin = 0, rmax = n - 1, cmax = m - 1;

        int count = n * m;
        while (count > 0) {

            for (int c = cmin; c <= cmax && count > 0; c++) {
                System.out.print(arr[rmin][c] + " ");
                count--;
            }
            rmin++;

            for (int r = rmin; r <= rmax && count > 0; r++) {
                System.out.print(arr[r][cmax] + " ");
                count--;
            }
            cmax--;

            for (int c = cmax; c >= cmin && count > 0; c--) {
                System.out.print(arr[rmax][c] + " ");
                count--;
            }
            rmax--;

            for (int r = rmax; r >= rmin && count > 0; r--) {
                System.out.print(arr[r][cmin] + " ");
                count--;
            }
            cmin++;
        }
    }

    public static void exitPoint(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int d = 0, r = 0, c = 0;

        while (true) {
            d = (d + arr[r][c]) % 4;
            if (d == 0) { // E
                c++;
                if (c == m) { // exit point
                    System.out.println(r);
                    System.out.println(c - 1);
                    break;
                }
            } else if (d == 1) { // N
                r++;
                if (r == n) { // exit point
                    System.out.println(r - 1);
                    System.out.println(c);
                    break;
                }
            } else if (d == 2) { // W
                c--;
                if (c == -1) {
                    System.out.println(r);
                    System.out.println(c + 1);
                    break;
                }
            } else { // S
                r--;
                if (r == -1) {
                    System.out.println(r + 1);
                    System.out.println(c);
                    break;
                }
            }
        }
    }

    public static void swap2D(int[][] arr, int i1, int j1, int i2, int j2) {
        int temp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = temp;
    }

    public static void transpose(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr[0].length; j++) {
                swap2D(arr, i, j, j, i);
            }
        }
    }

    public static void swapColums(int[][] arr) {
        int si = 0, ei = arr[0].length;
        while (si < ei) {
            for (int i = 0; i < arr.length; i++)
                swap2D(arr, si, i, ei, i);
        }
    }

    public static void rotate90D(int[][] arr) {
        transpose(arr);
        swapColums(arr);
    }

    public static void diagonalTraversal(int[][] arr) {
        for (int gap = 0; gap < arr[0].length; gap++) {
            for (int i = 0, j = gap; j < arr[0].length; i++, j++) {
                System.out.println(arr[i][j]);
            }
        }
    }

    public static void matrixMultiplication(int[][] arr1, int[][] arr2) {
        int n = arr1.length, m = arr1[0].length;
        int p = arr2.length, q = arr2[0].length;

        int[][] ans = new int[n][q];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < q; j++) {
                int res = 0;
                for (int k = 0; k < m; k++) {
                    res += arr1[i][k] * arr2[k][j];
                }
                ans[i][j] = res;
            }
        }
    }

    public static void serachIn2D(int[][] arr, int data) {
        int r = arr.length - 1, c = 0;
        while (r >= 0 && c < arr[0].length) {
            if (arr[r][c] > data)
                r--;
            else if (arr[r][c] < data)
                c++;
            else {
                System.out.println(r);
                System.out.println(c);
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // write your code here
    }

}