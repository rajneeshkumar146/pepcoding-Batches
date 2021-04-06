import java.util.Scanner;

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

    // stateOfWakanda2
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
        for (int gap = n - 1; gap >= 1; gap--) {
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

    public static void spiralPrint(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int rmin = 0, rmax = n - 1;
        int cmin = 0, cmax = m - 1;
        int tnel = n * m;

        while (tnel > 0) {
            for (int r = rmin; r <= rmax && tnel > 0; r++) {
                System.out.println(arr[r][cmin]);
                tnel--;
            }
            cmin++;

            for (int c = cmin; c <= cmax && tnel > 0; c++) {
                System.out.println(arr[rmax][c]);
                tnel--;
            }
            rmax--;

            for (int r = rmax; r >= rmin && tnel > 0; r--) {
                System.out.println(arr[r][cmax]);
                tnel--;
            }
            cmax--;

            for (int c = cmax; c >= cmin && tnel > 0; c--) {
                System.out.println(arr[rmin][c]);
                tnel--;
            }
            rmin++;
        }
    }

    public static void exitPoint(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int dir = 0;
        int i = 0, j = 0;
        while (true) {
            dir = (dir + arr[i][j]) % 4;
            if (dir == 0) { // North
                j++;
                if (j == m) {
                    System.out.println(i + "\n" + (j - 1));
                    break;
                }
            } else if (dir == 1) { // East
                i++;
                if (i == n) {
                    System.out.println((i - 1) + "\n" + (j));
                    break;
                }
            } else if (dir == 2) { // South
                j--;
                if (j == -1) {
                    System.out.println((i) + "\n" + (j + 1));
                    break;
                }
            } else { // West
                i--;
                if (i == -1) {
                    System.out.println((i + 1) + "\n" + (j));
                    break;
                }
            }
        }
    }

    public static void swap(int[][] arr, int i1, int j1, int i2, int j2) {
        int temp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = temp;
    }

    public static void rotate90(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < m; j++) {
                swap(arr, i, j, j, i);
            }
        }

        // column shifting
        int si = 0, ei = m - 1;
        while (si < ei) {
            for (int i = 0; i < n; i++) {
                swap(arr, i, si, i, ei);
            }
            si++;
            ei--;
        }
    }

    public static int maxInCol(int[][] arr, int c) {
        int maxVal = -(int) 1e9;
        int r = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][c] > maxVal) {
                maxVal = arr[i][c];
                r = i;
            }
        }

        return r;
    }

    public static void saddlePoint(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            // find min in row
            int minVal = (int) 1e9;
            int c = -1;
            for (int j = 0; j < m; j++) {
                if (arr[i][j] < minVal) {
                    minVal = arr[i][j];
                    c = j;
                }
            }

            int r = maxInCol(arr, c);

            if (r == i) {
                System.out.println(arr[r][c]);
                flag = true;
            }
        }

        if (!flag)
            System.out.println("Invalid input");
    }

    public static void main(String[] args) {
        int[][] arr = new int[scn.nextInt()][scn.nextInt()];
        input(arr);
        display(arr);
    }
}