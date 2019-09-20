import java.util.Scanner;
public class l001_pattern{
    public static Scanner scn=new Scanner(System.in);

   public static void main(String[] args){
       int n=scn.nextInt();
    //    numberDiamond(n);
    binomial(n);
   }

   public static void numberDiamond(int n){
       int nsp=n/2;
       int nst=1;

       for(int r=1;r<=n;r++){
           for(int csp=1;csp<=nsp;csp++){
               System.out.print(" ");
           }


           int val=r;
           if(r>n/2+1){
               val=n-r+1;
           }

           for(int cst=1;cst<=nst;cst++){
               System.out.print(val);
               if(cst<=nst/2){
                   val++;
               }else val--;
            }

           if(r<=n/2){
               nst+=2;
               nsp--;
           }else{
               nst-=2;
               nsp++;
           }

           System.out.println();

       }
   }

   public static void binomial(int n){
       int nst=1;
       for(int r=0;r<n;r++){
         
        int val=1;
        for(int cst=0;cst<nst;cst++){
            System.out.print(val);
            val=(val*(r-cst))/(cst+1);
        }

        nst++;
        System.out.println();
       }

   }
 

}