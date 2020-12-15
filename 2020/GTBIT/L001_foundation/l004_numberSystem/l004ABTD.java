import java.util.Scanner;
public class l004ABTD{
    // Decimal To Any Base.

    public static Scanner scn = new Scanner(System.in);
    
    public static long anyBaseToDecimal(int n,int b){
        long res = 0, pow = 1;
        while(n != 0){
            int rem = n % 10;
            n /= 10;

            res += rem * pow;
            pow *= b;
        }

        return res;
    }

    public static void main(String[] args){
        int n = scn.nextInt();
        int b = scn.nextInt();
        int b2 = scn.nextInt();
        System.out.println(anyBaseToDecimal(n,b));
        System.out.println(b2);
    }
}