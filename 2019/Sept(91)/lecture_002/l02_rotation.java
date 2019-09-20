import java.util.Scanner;

public class l02_rotation {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scn.nextInt();
        // int r = scn.nextInt();

        // System.out.println(rotation(n, r));
        primeTillN(n);
    }

    public static int noOfDigits(int n) {
        int count_ = 0;
        while (n != 0) {
            n /= 10;
            count_++;
        }

        return count_;
    }

    public static int rotation(int n, int r) {
        int digit = noOfDigits(n);
        r %= digit;
        // if(r<0) r+=digit;
        r = r < 0 ? r + digit : r;

        int mult = 1;
        int div = 1;

        for (int i = 1; i <= digit; i++) {
            if (i <= r) {
                mult *= 10;
            } else {
                div *= 10;
            }
        }

        int rem = n % div;
        n /= div;

        return (rem * mult + n);

    }

    public static boolean isPrime(int n) {

        boolean flag = true;// bool flag=false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                flag = false;
                break;
            }
        }
        // if (flag == true)
        // System.out.println("Prime!");
        // else {
        // System.out.println("Not Prime!");
        // }

        return flag;
    }

    public static void primeTillN(int n) {
        for (int i = 2; i <= n; i++) {
            boolean res = isPrime(i);

            if (res)
                System.out.println(i);
        }
    }

}