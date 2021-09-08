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
        int d = 0, r = 0, c= 0;

        while(??){


            if(d == 0){  // E
                c++;
                if(c == m){  // exit point

                }

            }else if(d == 1){ //N
                r++;
                if(r == n){  // exit point

                }
            }else if(d == 2){  // W
                c--;
                if(c == -1){

                }
            }else{  // S
                r--;
                if(r == -1){

                }
            }



        }

    }

    public static void main(String[] args) throws Exception {
        // write your code here
    }

}