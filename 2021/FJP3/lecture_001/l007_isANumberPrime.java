import java.util.Scanner;

public class l007_isANumberPrime {

    public static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if ((num % i) == 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        while (t-- > 0) {
            if (isPrime(scn.nextInt())) {
                System.out.println("prime");
            } else {
                System.out.println("not prime");
            }
        }

    }

}