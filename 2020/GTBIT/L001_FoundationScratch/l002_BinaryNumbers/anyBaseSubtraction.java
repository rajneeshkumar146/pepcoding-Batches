import java.util.Scanner;
public class anyBaseSubtraction{
    public static Scanner scn = new Scanner(System.in);

    public static long anyBaseSub(long n,long m,long b){
        // m > n
        long borrow = 0, pow = 1, res = 0;
        while(n != 0 || m != 0){
            long sum = (m % 10 + borrow) - n % 10;
            n /= 10;
            m /= 10;

            if(sum < 0) {
                sum += b;
                borrow = -1;
            }else
               borrow = 0;

            res += sum * pow;
            pow *= 10;
        }

        return res;
    }
    
    public static void main(String[] args){
        long b = scn.nextLong();
        long n = scn.nextLong();
        long m = scn.nextLong();        
        
       System.out.println(anyBaseSub(n,m,b));
    }
}