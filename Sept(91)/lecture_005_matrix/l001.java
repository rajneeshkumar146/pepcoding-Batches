import java.util.Scanner;

public class l001 {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        // int n = scn.nextInt();
        // int m = scn.nextInt();

        // int[][] arr = new int[n][m];
        // int[][] arr1 = new int[n][m];
        // input(arr);
        // input(arr1);

        // addMatrix(arr, arr1);
        // display(arr);

        int[][] arr={{1,2,3,4,5},
                     {6,7,8,9,10},
                     {11,12,13,14,15},
                     {16,17,18,19,20},
                     {21,22,23,24,25},
                     };

        spiralDisplay(arr);

    }

    public static void input(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                arr[r][c] = scn.nextInt();
            }
        }
    }

    public static void display(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                System.out.print(arr[r][c] + " ");
            }
            System.out.println();
        }
    }

    public static void addMatrix(int[][] arr, int[][] arr1) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                arr[r][c] += arr1[r][c];
            }
            System.out.println();
        }
    }

    public static void exitPoint(int[][] arr) {
        int dir = 0;
        int r = 0;
        int c = 0;

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

            } else if (dir == 3) {
                r--;

                if (r == -1) {
                    System.out.println((r + 1) + ", " + c);
                    break;
                }
            }

        }

    }

    public static void spiralDisplay(int[][] arr) {
        int rmin = 0;
        int cmin = 0;
        int rmax = arr.length - 1;
        int cmax = arr[0].length - 1;

        int tne = arr.length * arr[0].length;
        while (tne > 0) {
            for (int i = cmin; i <= cmax && tne > 0; i++) {
                System.out.print(arr[rmin][i] + " ");
                tne--;
            }
            rmin++;

            for (int i = rmin; i <= rmax && tne > 0; i++) {
                System.out.print(arr[i][cmax] + " ");
                tne--;
            }

            cmax--;

            for (int i = cmax; i >= cmin && tne > 0; i--) {
                System.out.print(arr[rmax][i] + " ");
                tne--;
            }

            rmax--;

            for (int i = rmax; i >= rmin && tne > 0; i--) {
                System.out.print(arr[i][cmin] + " ");
                tne--;
            }
            cmin++;
            

        }
    }

}