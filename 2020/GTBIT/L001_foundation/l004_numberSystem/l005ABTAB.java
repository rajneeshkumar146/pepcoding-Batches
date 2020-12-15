import java.util.Scanner;
public class l001ABTAB{
    public static Scanner scn = new Scanner(System.in);
    public static long anyBaseToDecimal(long n,long b){
        long res = 0, pow = 1;
        while(n != 0){
            long rem = n % 10;
            n /= 10;

            res += rem * pow;
            pow *= b;
        }

        return res;
    }

    public static long decimalToAnyBase(long n,long b){
        long res = 0, pow = 1;
        while(n != 0){
            long rem = n % b;
            n /= b;

            res += rem * pow;
            pow *= 10;
        }

        return res;
    }

    public static void main(String[] args){
        int n = scn.nextInt();
        int b1 = scn.nextInt();
        int b2 = scn.nextInt();
        long ans = anyBaseToDecimal(n,b1);
        System.out.println(decimalToAnyBase(ans,b2));
    }
}