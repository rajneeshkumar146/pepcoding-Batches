public class l002Pattern{

    public static void pattern1(int row){
        int nst = 1; // no of star.
        for(int r = 1;r <= row; r++){
            for(int cst = 1;cst <= nst;cst++){
                System.out.print("*\t");
            }
    
            nst++;
            System.out.println();
        }
    }

    public static void pattern2(int row){
        int nst = row; // no of star.
        for(int r = 1;r <= row; r++){
            for(int cst = 1;cst <= nst;cst++){
                System.out.print("*\t");
            }
    
            nst--;
            System.out.println();
        }
    }

    public static void pattern3(int row){
        int nst = 1; // no of star.
        int nsp = row - 1;
        for(int r = 1;r <= row; r++){
            for(int csp = 1;csp <= nsp;csp++){
                System.out.print("\t");
            }
            
            for(int cst = 1;cst <= nst;cst++){
                System.out.print("*\t");
            }
    
            nst++;
            nsp--;
            System.out.println();
        }
    }

    public static void pattern4(int row){
        int nst = row; // no of star.
        int nsp = 0;
        for(int r = 1;r <= row; r++){
            for(int csp = 1;csp <= nsp;csp++){
                System.out.print("\t");
            }
            
            for(int cst = 1;cst <= nst;cst++){
                System.out.print("*\t");
            }
    
            nst--;
            nsp++;
            System.out.println();
        }
    }

    public static void patter5(int row){
        int nst = 1; // no of star.
        int nsp = row / 2;
        for(int r = 1;r <= row; r++){
            for(int csp = 1;csp <= nsp;csp++){
                System.out.print("\t");
            }
    
            for(int cst = 1;cst <= nst;cst++){
                System.out.print("*\t");
            }
    
            if(r <= row / 2){
                nsp--;
                nst+=2;
            }else{
                nsp++;
                nst-=2;
            }
    
            System.out.println();
        }
    }

    public static void patter6(int row){
        int nst = (row + 1) / 2; // no of star.
        int nsp = 1;
        for(int r = 1;r <= row; r++){
            for(int cst = 1;cst <= nst;cst++){
                System.out.print("*\t");
            }

            for(int csp = 1;csp <= nsp;csp++){
                System.out.print("\t");
            }

            for(int cst = 1;cst <= nst;cst++){
                System.out.print("*\t");
            }
    
            if(r <= row / 2){
                nst--;
                nsp+=2;
            }else{
                nsp-=2;
                nst++;
            }
    
            System.out.println();
        }
    }

    public static void patter5(int row){
        int nst = 1; // no of star.
        int nsp = row / 2;
        for(int r = 1;r <= row; r++){
            for(int csp = 1;csp <= nsp;csp++){
                System.out.print("\t");
            }

            int count = r;
            if(r > (row / 2) + 1)
               count = row - r + 1;
    
            for(int cst = 1;cst <= nst;cst++){
                System.out.print(count + "\t");

                if(cst <= nst/2) count++;
                else count--;
            }
    
            if(r <= row / 2){
                nsp--;
                nst+=2;
            }else{
                nsp++;
                nst-=2;
            }
            System.out.println();
        }
    }

    public static void pattern7(int row){
        int nsp = 0;
        for(int r = 1 ; r <= row; r++){
            for(int csp = 1; csp <= nsp;csp++){
                System.out.print("\t");
            }
            System.out.println("*\t");
            nsp++;
        }
    }

    public static void pattern8(int row){
        int nsp = row - 1;
        for(int r = 1 ; r <= row; r++){
            for(int csp = 1; csp <= nsp;csp++){
                System.out.print("\t");
            }
            System.out.println("*\t");
            nsp--;
        }
    }

    public static void pattern9(int row){
        int nsp = row - 1;
        for(int i = 1 ; i <= row; i++){
            for(int j = 1; j <= row;j++){
                if( i == j || (i + j) == row + 1)
                   System.out.print("*\t");
                else
                System.out.print("\t");
            }

            System.out.println();
        }
    }

    public static void pattern10(int row){
        int nsp1 = row / 2; 
        int nsp2 = -1;
        for(int r = 1;r <= row; r++){
            for(int csp = 1;csp <= nsp1;csp++){
                System.out.print("\t");
            }

            System.out.print("*\t");

            for(int csp = 1;csp <= nsp2;csp++){
                System.out.print("\t");
            }

            if(nsp2 != -1) 
                System.out.print("*\t");

            if(r <= row / 2){
                nsp1--;
                nsp2+=2;
            }else{
                nsp2-=2;
                nsp1++;
            }
    
            System.out.println();
        }
    }

    public static void pattern11(int row){
        int nst = 1;
        int count = 1;
        for(int r = 1;r <= row; r++){
            for(int cst = 1; cst <= nst; cst++){
                System.out.print(count++);
            }

            System.out.println();
            nst++;
        }
    }

    public static void pattern12(int row){
        int nst = 1;
        int a = 0;
        int b = 1;
        for(int r = 1;r <= row; r++){
            for(int cst = 1; cst <= nst; cst++){
                System.out.print(a + "\t");

                int sum = a + b;
                a = b;
                b = sum;
            }

            System.out.println();
            nst++; 
        }
    }

    public static void pattern13(int row){
        for(int n = 0;n < row; n++){
            int val = 1;
            for(int r = 0; r <= n; r++){
                System.out.print(val + "\t");
                val = ((n - r) * val)/ ( r + 1);
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        
    }
    
}