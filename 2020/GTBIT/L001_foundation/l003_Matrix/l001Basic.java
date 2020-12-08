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

    public static void main(String[] args){
        test1();
    }

}