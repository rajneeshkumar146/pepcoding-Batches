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

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        
    }
    
}