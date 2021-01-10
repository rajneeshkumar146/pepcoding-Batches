import java.util.Scanner;

public class l001 {
    public static Scanner scn = new Scanner(System.in);

    public static void printSomeLines() {
        System.out.println("Hello IGDTUW");
        System.out.println("Hello IGDTUW1");
        System.out.println("Hello IGDTUW2");
        System.out.println("Hello IGDTUW3");
        System.out.println("Hello IGDTUW4");
        System.out.println("Hello IGDTUW5");
    }

    public static void printZ() {
        System.out.println("*****");
        System.out.println("   *");
        System.out.println("  *");
        System.out.println(" *");
        System.out.println("*****");
    }

    public static void printVal() {
        int a = 100;
        System.out.println("Val: " + a);
    }

    public static void gradingSystem(int marks) {
        if (marks > 90) {
            System.out.println("excellent");
        } else if (marks > 80) {
            System.out.println("good");
        } else if (marks > 70) {
            System.out.println("fair");
        } else if (marks > 60) {
            System.out.println("meets expectations");
        } else {
            System.out.println("below par");
        }
    }

    public static void oddEven(int num) {
        if (num % 2 == 0) {
            System.out.println("Even");
        } else {
            System.out.println("Odd");
        }
    }

    public static void printNumber(int n) {

        for (int i = 1; i <= n; i++) {
            System.out.println(5 * i);
        }
    }

    public static void printTable(int num) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(num + " X " + i + " = " + num * i);
        }
        System.out.println();
    }

    public static void printTableInRage(int num) {
        for (int i = 2; i <= num; i++)
            printTable(i);
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }

        return true;

    }

    public static void printAllPrimes(int low, int high) {
        for (int i = low; i <= high; i++) {
            if (isPrime(i) == true) {
                System.out.println(i);
            }
        }
    }

    public static boolean check(int a, int b, int c) {
        if (a * a + b * b == c * c)
            return true;
        else if (c * c + b * b == a * a)
            return true;
        else if (a * a + c * c == b * b)
            return true;
        else
            return false;

    }

    public static void benjaminBulbs(int n) {
        for (int i = 1; i * i <= n; i++) {
            System.out.println(i * i);
        }
    }

    public static int countOfDigits(int n) {
        int count = 0;
        while (n != 0) {
            n /= 10;
            count++;
        }

        return count;
    }

    public static void reversePrint(int n) {
        int count = 0;
        while (n != 0) {
            int rem = n % 10;
            System.out.println(rem);
            n /= 10;
        }
    }

    public static int countOfDigits(int n) {
        int count = 0;
        while (n != 0) {
            n /= 10;
            count++;
        }

        return count;
    }

    public static int rotateNumber(int n, int r) {
        int d = countOfDigits(n);
        r = (r % d + d) % d;

        int div = (int) Math.pow(10, r);
        int mul = (int) Math.pow(10, d - r);

        int a = n % div;
        int b = n / div;

        return a * mul + b;
    }

    public static void main(String[] args) {
        // int a = scn.nextInt();
        // printTableInRage(200);
        // int num = 13;
        // System.out.println(isPrime(num));
    }

}