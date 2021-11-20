import java.util.Scanner;

public class l006_functions {

    public static Scanner scn = new Scanner(System.in);

    // no Return and no arguments
    public static void printMessage() {
        System.out.println("Hello class!!!");
    }

    public static void printTable(int num) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(num + " X " + i + " = " + (num * i));
        }
    }

    public static void printTableInRange(int a, int b) {
        for (int i = a; i <= b; i++) {
            printTable(i);
            System.out.println("=====================");
        }
    }

    public static int sumOfTwoNumber() {
        int a = scn.nextInt();
        int b = scn.nextInt();

        return (a + b);
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if ((num % i) == 0)
                return false;
        }

        return true;
    }

    public static void allPrimeInRange(int lo, int hi) {
        for (int num = lo; num <= hi; num++) {
            if (isPrime(num)) {
                System.out.println(num);
            }
        }
    }

    public static int countDigitOfNumber(int num) {
        int count = 0;
        while (num != 0) {
            num /= 10;
            count++;
        }
        return count;
    }

    public static int reverseOfNumber(int num) {
        int ans = 0;
        while (num != 0) {
            int digit = num % 10;
            num /= 10;
            ans = ans * 10 + digit;
        }
        return ans;
    }

    public static void digitsOfNumber(int num) {
        int len = countDigitOfNumber(num) - 1;
        while (len >= 0) {
            int digit = num / (int) Math.pow(10, len);
            num = num % (int) Math.pow(10, len);
            System.out.println(digit);
            len--;
        }
    }

    public static void main(String[] args) {
        // for (int i = 0; i < 10; i++)
        // printMessage();

        int a = scn.nextInt(), b = scn.nextInt();
        // printTableInRange(a, b);
        allPrimeInRange(a, b);

        // int ans = sumOfTwoNumber();
        // System.out.println(ans);
    }

}