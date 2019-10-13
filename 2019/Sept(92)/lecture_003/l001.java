import java.util.Scanner;
public class l001 {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter Rows: ");
        int rows = scn.nextInt();
        // numberDiamond(rows);
        // pascalTraingle(rows);
        int[] arr=new int[rows];
        input(arr);
        display(arr);
    }

    public static void numberDiamond(int rows) {
        int nst = 1;
        int nsp = rows / 2;

        for (int r = 1; r <= rows; r++) {
            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print(" ");
            }

            int ele = r;
            if (r > (rows + 1) / 2) {
                ele = rows - r + 1;
            }

            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(ele);
                if (cst <= nst / 2)
                    ele++;
                else
                    ele--;
            }

            if (r <= rows / 2) {
                nst += 2;
                nsp--;
            } else {
                nst -= 2;
                nsp++;
            }
            System.out.println();
        }

    }

    public static void pascalTraingle(int rows){
        for(int n=0;n<=rows;n++){
            int val=1;
            for(int r=0;r<=n;r++){
               System.out.print(val+" ");
               val=(val*(n-r))/(r+1);
            }
            System.out.println();
        }
     }

     public static void fibo(int n){
         if(n<=1){
             System.out.print(n);
             return;
         }
         
         int a=0;
         int b=1;
         for(int i=0;i<n;i++){
            int sum=a+b; 
            // System.out.println(b);

             a=b;
             b=sum;

         }

         System.out.println(b);

     }

     public static void input(int[] arr){
         for(int i=0;i<arr.length;i++){
             arr[i]=scn.nextInt();
         }
     } 


     public static void display(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }

          for(int ele:arr){
            System.out.print(ele+" ");
          }

    } 





}