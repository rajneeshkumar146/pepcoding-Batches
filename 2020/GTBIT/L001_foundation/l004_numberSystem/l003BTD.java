import java.util.Scanner;
public class l003BTD{
    //Decimal To Binary
    
    public static Scanner scn = new Scanner(System.in);
    public static long binaryToDecimal(int n){
        int res = 0, pow = 1;
        while(n != 0){
            int rem = n % 10;
            n /= 10;

            res += rem * pow;
            pow *= 2;
        }

        return res;
    }

    public static void main(String[] args){
        int n = scn.nextInt();
        System.out.println(binaryToDecimal(n));
    }
}