import java.util.Scanner;
public class binarySubtraction{
    public static Scanner scn = new Scanner(System.in);
    
    public static long decimalSub(long n,long m){
        // m > n
        long borrow = 0, pow = 1, res = 0;
        while(n != 0 || m != 0){
            long sum = (m % 10 + borrow) - n % 10;
            n /= 10;
            m /= 10;

            if(sum < 0) {
                sum += 10;
                borrow = -1;
            }else
               borrow = 0;

            res += sum * pow;
            pow *= 10;
        }

        return res;
    }

    public static long binarySub(long n,long m){
        // m > n
        long borrow = 0, pow = 1, res = 0;
        while(n != 0 || m != 0){
            long sum = (m % 10 + borrow) - n % 10;
            n /= 10;
            m /= 10;

            if(sum < 0) {
                sum += 2;
                borrow = -1;
            }else
               borrow = 0;

            res += sum * pow;
            pow *= 10;
        }

        return res;
    }
    
    public static void main(String[] args){
        long n = scn.nextLong();
        long m = scn.nextLong();        
    }
}