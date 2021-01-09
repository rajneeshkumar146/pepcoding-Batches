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
    }

    public static void main(String[] args) {
        int a = scn.nextInt();

    }

}