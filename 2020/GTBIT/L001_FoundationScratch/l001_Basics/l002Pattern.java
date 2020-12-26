import java.util.Scanner;
public class l002Pattern{
    public static Scanner scn = new Scanner(System.in);

        
    public static void mirrorTriangle(int row){
        int nst = 1, nsp = row - 1;
        for(int r = 1; r <= row;r++){
            for(int csp = 1; csp <= nsp;csp++){
                System.out.print(" ");
            }

            for(int cst = 1; cst <= nst;cst++){
                System.out.print("*");
            }

            nst++;
            nsp--;
            System.out.println();
        }
    }
    
    //Pattern 1
    public static void triangle(int row){
        int nst = 1;
        for(int r = 1; r <= row;r++){
            for(int cst = 1; cst <= nst;cst++){
                System.out.print("*\t");
            }

            nst++;
            System.out.println();
        }
    }
    
    //Pattern 2
    public static void pattern2(int row) {
        int nst = row; // no of star.
        for (int r = 1; r <= row; r++) {
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print("*\t");
            }

            nst--;
            System.out.println();
        }
    }

    //Pattern 3
    public static void pattern3(int row) {
        int nst = 1; // no of star.
        int nsp = row - 1;
        for(int r = 1;r<=row;r++){
            for(int csp = 1;csp<=nsp;csp++){
                System.out.print("\t");
            }
            
            for(int cst = 1;cst<=nst;cst++){
                System.out.print("*\t");
            }
            
            nsp--;
            nst++;
            System.out.println();
        }
    }

        //Pattern 4
    public static void pattern4(int row) {
        int nst = row; // no of star.
        int nsp = 0;
        for(int r = 1;r<=row;r++){
            for(int csp = 1;csp<=nsp;csp++){
                System.out.print("\t");
            }
                
            for(int cst = 1;cst<=nst;cst++){
                System.out.print("*\t");
            }
                
            nsp++;
            nst--;
            System.out.println();
        }
    }

    public static void pattern7(int row) {
        for (int i = 1; i <= row; i++) {
            for(int j = 1; j <= row;j++){
                if(i == j) 
                   System.out.print("*\t");
                else System.out.print("\t");
            }
            System.out.println();
        }
    }

        
    public static void pattern8(int row) {
        for (int i = 1; i <= row; i++) {
            for(int j = 1; j <= row;j++){
                if(i + j == row + 1) 
                   System.out.print("*\t");
                else System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void pattern9(int row) {
        for (int i = 1; i <= row; i++) {
            for(int j = 1; j <= row;j++){
                if(i + j == row + 1 || i == j) 
                   System.out.print("*\t");
                else System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void pattern9_01(int row) {
        int p = row + 1;
        for (int i = 1; i <= row; i++) {
            for(int j = 1; j <= row;j++){
                if(i + j == p || i + j == p - 2 || i + j == p + 2) 
                   System.out.print("*\t");
                else System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void pattern9_02(int row) {
        int p = 0;
        for (int i = 1; i <= row; i++) {
            for(int j = 1; j <= row;j++){
                if(i - j == p || i - j == p - 2 || i - j == p + 2) 
                   System.out.print("*\t");
                else System.out.print("\t");
            }
            System.out.println();
        }
    }


    public static void pattern5(int row) {
        int nst = 1; // no of star.
        int nsp = row / 2;
        for(int r = 1;r<=row;r++){
            for(int csp = 1;csp<=nsp;csp++){
                System.out.print("\t");
            }
                
            for(int cst = 1;cst<=nst;cst++){
                System.out.print("*\t");
            }
            
            if(r <= row/2){  
               nsp--;
               nst+=2;
            }else{
                nsp++;
                nst-=2;
            }
            
            System.out.println();
        }
    }

    public static void pattern6(int row) {
        int nst = (row + 1) / 2; // no of star.
        int nsp = 1;
        for(int r = 1;r<=row;r++){
            for(int cst = 1;cst<=nst;cst++){
                System.out.print("*\t");
            }

            for(int csp = 1;csp<=nsp;csp++){
                System.out.print("\t");
            }

            for(int cst = 1;cst<=nst;cst++){
                System.out.print("*\t");
            }
            
            if(r <= row/2){  
               nsp+=2;
               nst--;
            }else{
                nsp-=2;
                nst++;
            }
            
            System.out.println();
        }
    }

    public static void pattern15(int row) {
        int nst = 1; // no of star.
        int nsp = row / 2;
        
        int val = 1;
        
        for(int r = 1;r<=row;r++){
            
            for(int csp = 1;csp<=nsp;csp++){
                System.out.print("\t");
            }
            
            for(int cst = 1;cst<=nst;cst++){
                System.out.print(val + "\t");
                if(cst <= nst/2) val++;
                else val--;
            }
            
            if(r <= row/2){  
               nsp--;
               nst+=2;
               val = r + 1;
            }else{
                nsp++;
                nst-=2;
                val = row - r;
            }
            
            System.out.println();
        }
    }

    
    public static void main(String[] args){
        int n = scn.nextInt();
        mirrorTriangle(n);
        triangle(n);
    }
}