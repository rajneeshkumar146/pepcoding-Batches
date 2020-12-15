import java.util.Scanner;
public class l002DTAB{
    // Decimal To Any Base.

    public static Scanner scn = new Scanner(System.in);
    
    public static long decimalToAnyBase(int n,int b){
        long res = 0, pow = 1;
        while(n != 0){
            int rem = n % b;
            n /= b;

            res += rem * pow;
            pow *= 10;
        }

        return res;
    }

    public static void main(String[] args){
        int n = scn.nextInt();
        int b = scn.nextInt();
        System.out.println(decimalToAnyBase(n,b));
    }
}