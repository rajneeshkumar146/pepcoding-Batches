import java.util.Scanner;
public class l001Basic{
    
    public static Scanner scn = new Scanner(System.in);

    public static void test1(){
        int n = scn.nextInt(),m = scn.nextInt();

        int[][] arr = new int[n][m];

        for(int i =0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j] = scn.nextInt();
            }
        }
    
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    //===================================================================

    public static void printDiangonals(int[][] arr){
        
        for(int gap = 0; gap < arr[0].length; gap++){
            for(int i = 0, j = gap; i < arr.length && j < arr[0].length;i++,j++){
                System.out.println(arr[i][j]);
            }
        }
    }

    public static void saddlePoint(int[][] arr){
        
        int n = arr.length;
        int m = arr[0].length;
        
        for(int r = 0; r < n; r++){
            int c = 0;
            int minEle = (int)1e8;
            
            for(int j = 0; j < m; j++){
                if(arr[r][j] < minEle){
                    minEle = arr[r][j];
                    c = j;
                }
            }
            
            boolean isSaddlePoint = true;
            
            for(int i=0;i<n;i++){
                if(arr[i][c] > minEle){
                    isSaddlePoint = false;
                    break;
                }
            }
            
            if(isSaddlePoint){
                System.out.println(minEle);
                return;
            }
        }
        
        System.out.println("Invalid input");
        
    }

    public static void searchInSortedMatrix(int[][] arr,int data){
        int i = arr.length - 1, j = 0;

        while(i >= 0 && j < arr[0].length){

            if(arr[i][j] == data){
                System.out.println(i + "\n" + j);
                return;
            }else if(arr[i][j] < data) 
                j++;
            else 
                i--;
        }

        System.out.println("Not Found");
    }

    public static void spiralDislay(int[][] arr){
        
        int n = arr.length, m = arr[0].length, count = n * m;
        int rmin = 0, rmax = n - 1;
        int cmin = 0, cmax = m - 1;
         
        while(count != 0){

            for(int r = rmin; r <= rmax && count > 0; r++){
                System.out.println(arr[r][cmin]);
                count--;
            }
            cmin++;

            
            for(int c = cmin; c <= cmax && count > 0; c++){
                System.out.println(arr[rmax][c]);
                count--;
            }
            rmax--;

            for(int r = rmax; r >= rmin && count > 0; r--){
                System.out.println(arr[r][cmax]);
                count--;
            }
            cmax--;

            for(int c = cmax; c >= cmin && count > 0; c--){
                System.out.println(arr[rmin][c]);
                count--;
            }
            rmin++;
        }

    }
    

    public static void main(String[] args) throws Exception {
        int n = scn.nextInt();
        int[][] arr = new int[n][n];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
               arr[i][j] = scn.nextInt();
        }
        
        printDiangonals(arr);
    }



}