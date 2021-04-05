import java.util.*;

public class lecture_001 {
    public static Scanner scn = new Scanner(System.in);

    public static void printMsg() {
        System.out.print("Hello, World");
        System.out.println("hi");
        System.out.println("friends");
    }

    public static void printOddEven(int n) {
        if (n % 2 == 0)
            System.out.println("Even");
        else
            System.out.println("odd");
    }

    public static boolean isEvenNumber(int n) {
        if (n % 2 == 0)
            return true;
        else {
            return false;
        }
    }

    public static void printNumbers(int a, int b) {
        for (int i = a; i <= b; i += 5) {
            System.out.print(i + " ");
        }
    }

    public static void printTable(int n) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " X " + i + " = " + (n * i));
        }
    }

    public static void printTableInRange(int a, int b) {
        for (int i = a; i <= b; i++) {
            printTable(i);
            System.out.println();
        }
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    public static void printPrimeNumbersInRange(int a, int b) {
        for (int i = a; i <= b; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        // int n = scn.nextInt();
        // printOddEven(n);
        // boolean ans = isEvenNumber(n);
        // System.out.println(ans);
        // System.out.println(isEvenNumber(n));
        printPrimeNumbersInRange(scn.nextInt(), scn.nextInt());
    }

}