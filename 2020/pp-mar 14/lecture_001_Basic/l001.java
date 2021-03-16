import java.util.*;

public class l001 {
    public static Scanner scn = new Scanner(System.in);

    public static void printZ() {
        System.out.println("*****");
        System.out.println("   *");
        System.out.println("  *");
        System.out.println(" *");
        System.out.println("*****");
    }

    public static void test() {
        int n = 1256;
        System.out.println(n);
    }

    public static void input_test() {
        int n = scn.nextInt();
        System.out.println("please, input a number: ");
        System.out.println(n);
    }

    public static void gradingSystem() {
        int marks = scn.nextInt();

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

    public static void printOddEven(int num) {
        if (num % 2 == 0)
            System.out.println("even");
        else
            System.out.println("odd");
    }

    public static void printMultiplesOddEven() {
        int count = scn.nextInt();
        for (int i = 1; i <= count; i++) {
            int num = scn.nextInt();
            printOddEven(num);
        }
    }

    public static void printTable(int num) {
        for (int i = 1; i <= 10; i++) {
            int multiply = num * i;
            System.out.println(num + " X " + i + " = " + multiply);
        }
    }

    public static void printAllTables(int n) {
        for (int i = 1; i <= n; i++) {
            printTable(i);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        printAllTables(n);
    }

}