import java.util.Scanner;
public class binaryAddition{
    public static Scanner scn = new Scanner(System.in);
    
    public static long binaryAdd(long n,long m){
        long carry = 0, pow = 1, res = 0;
        while(n != 0 || m != 0 || carry != 0){
            long sum = 0;
            sum += carry + n % 10 + m % 10;
            n /= 10;
            m /= 10;

            int ld = sum % 2;
            carry = sum / 2;
            
            res += ld * pow;
            pow *= 10;
        }

        return res;
    }
    
    public static void main(String[] args){
        long n = scn.nextLong();
        long m = scn.nextLong();

        


        
    }
}