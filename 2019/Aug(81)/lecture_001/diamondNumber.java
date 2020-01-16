import java.util.Scanner;

public class diamondNumber {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int nst = 1;
        int nsp = n / 2;
        for (int r = 1; r <= n; r++) {

            for (int csp = 1; csp <= nsp; csp++) {
                System.out.print(" ");
            }

            // for stars
            int val=r;
            if(r>n/2){
                val=n-r+1;
            }

            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(val);
                if(cst<=nst/2){
                    val++;
                }else{
                    val--;
                }
            }

            if (r <= n / 2) {
                nst += 2;
                nsp--;
            } else {
                nst -= 2;
                nsp++;
            }

            System.out.println();
        }
    }
}