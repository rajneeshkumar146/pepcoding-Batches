import java.util.Scanner;

import jdk.javadoc.internal.doclets.formats.html.SystemPropertiesWriter;

public class l001 {
    public static Scanner scn = new Scanner(System.in);

    public static void input(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
    }

    public static void display1(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void display(int[][] arr) {
        for (int[] ar : arr) {
            for (int e : ar) {
                System.out.print(e);
            }
            System.out.println();
        }
    }

    public static int maximum(int[][] arr) {
        int maxEle = -(int) 1e9;
        for (int[] ar : arr)
            for (int e : ar)
                maxEle = Math.max(maxEle, e);
        return maxEle;
    }

    public static int minimum(int[][] arr) {
        int minEle = (int) 1e9;
        for (int[] ar : arr)
            for (int e : ar)
                minEle = Math.min(minEle, e);
        return minEle;
    }

    public static boolean find(int[][] arr, int data) {
        for (int[] ar : arr)
            for (int e : ar)
                if (e == data)
                    return true;

        return false;
    }

    public static void waveTraversalLeftRight(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < m; j++) {
                    System.out.print(arr[i][j] + " ");
                }
            } else {
                for (int j = m - 1; j >= 0; j--) {
                    System.out.print(arr[i][j] + " ");
                }
            }
        }
    }

    public static void waveTraversalUpAndDown(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        for (int j = 0; j < m; j++) {
            if (j % 2 == 0) {
                for (int i = 0; i < n; i++) {
                    System.out.print(arr[i][j] + " ");
                }
            } else {
                for (int i = n - 1; i >= 0; i--) {
                    System.out.print(arr[i][j] + " ");
                }
            }
        }
    }

    public static void diagonalTraversal(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        for (int gap = 0; gap < m; gap++) {
            for (int i = 0, j = gap; i < n && j < m; i++, j++) {
                System.out.print(arr[i][j] + " ");
            }
        }
    }

    public static void rotate90(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;
 
        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < m; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }

       // reverse of columns
        int j1 = 0, j2 = m - 1;
        while(j1 < j2){
            for(int i = 0;i < n;i++){
                int temp = arr[i][j1];
                arr[i][j1] = arr[i][j2];
                arr[i][j2] = temp;
            }

            j1++;
            j2--;
        }
    }

    public static void exitPoint(int[][] arr){
        int r = 0, c= 0, dir = 0;
        while(true){
            
            dir = (dir + arr[r][c]) % 4;
            if(dir == 0){
                c++;
                if(c == arr[0].length){
                    System.out.println(r);
                    System.out.println(c-1);
                    break;
                }
            
            }else if(dir == 1){
                r++;
                
                if(r == arr.length){
                    System.out.println(r-1);
                    System.out.println(c);
                    break;
                }
            }else if(dir == 2){
                c--;
                
                if(c == -1){
                    System.out.println(r);
                    System.out.println(c+1);
                    break;
                }
            }else{
                r--;
            
                if(r == -1){
                    System.out.println(r+1);
                    System.out.println(c);
                    break;
                }
            }
        }
    }

    public static void spiral(int[][] arr){
        int n = arr.length, m = arr[0].length;
        int tne = n * m;
        int rmin = 0, cmin = 0, rmax = n-1, cmax = m-1;
        while(tne > 0){
            for(int i = rmin; i <= rmax && tne > 0; i++){
                tne--;
                System.out.println(arr[i][cmin]);
            }
            cmin++;
            
            for(int i = cmin; i <= cmax && tne > 0; i++){
                tne--;
                System.out.println(arr[rmax][i]);
            }
            rmax--;
            
            for(int i = rmax; i >= rmin && tne > 0; i--){
                tne--;
                System.out.println(arr[i][cmax]);
            }
            cmax--;
            
            for(int i = cmax; i >= cmin && tne > 0; i--){
                tne--;
                System.out.println(arr[rmin][i]);
            }
            rmin++;
        }
    }



}