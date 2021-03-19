import java.util.*;

public class l002 {
    public static Scanner scn = new Scanner(System.in);

    public static boolean isEven(int n) {
        if (n % 2 == 0)
            return true;
        else
            return false;
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    public static void printAllPrimeInRange(int low, int heigh) {
        for (int i = low; i <= heigh; i++) {
            if (isPrime(i))
                System.out.println(i);
        }
    }

    public static int countDigits(int n) {
        int len = 0;
        while (n != 0) {
            n /= 10;
            len++;
        }

        return len;
    }

    public static void printDigitsInReverseOrder(int n) {
        while (n != 0) {
            int rem = n % 10;
            n /= 10;
            System.out.println(rem);
        }
    }

    // https://www.pepcoding.com/myClassroom/the-placement-program-pitampura-mar-14-2021/getting-started/digits-of-a-number-official/ojquestion

    public static int powerEqualDigit(int n) {
        int pow = 1;
        n /= 10;
        while (n != 0) {
            pow *= 10;
            n /= 10;
        }

        return pow;
    }

    public static void digitOfANumber(int n) {

        int pow = powerEqualDigit(n);

        while (pow != 0) {
            int quo = n / pow;
            n %= pow;
            pow /= 10;
            System.out.println(quo);
        }
    }

    public static void solve() {
        int n = scn.nextInt();
        boolean res = isPrime(n);
        if (res == true) {
            System.out.println("prime");
        } else {
            System.out.println("not prime");
        }
    }

    public static void main(String[] args) {
        int t = scn.nextInt();
        for (int i = 1; i <= t; i++) {
            solve();
        }
    }
}